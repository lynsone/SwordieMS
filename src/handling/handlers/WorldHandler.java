package handling.handlers;

import client.Account;
import client.Client;
import client.character.Char;
import client.character.ExtendSP;
import client.character.commands.AdminCommand;
import client.character.commands.AdminCommands;
import client.character.items.Item;
import client.character.skills.*;
import client.field.Field;
import client.field.Portal;
import client.jobs.JobManager;
import client.life.DeathType;
import client.life.Life;
import client.life.Mob;
import client.life.movement.Movement;
import connection.InPacket;
import constants.JobConstants;
import constants.SkillConstants;
import enums.ChatMsgColour;
import enums.InvType;
import enums.Stat;
import packet.CField;
import packet.Stage;
import packet.WvsContext;
import server.EventManager;
import server.Server;
import util.Position;
import util.Rect;
import util.Tuple;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static enums.InvType.*;

/**
 * Created on 12/14/2017.
 */
public class WorldHandler {
    public static void handleCharLogin(Client c, InPacket inPacket) {
        int worldId = inPacket.decodeInt();
        int charId = inPacket.decodeInt();
        Tuple<Byte, Account> info = Server.getInstance().getChannelFromTransfer(charId, worldId);
        byte channel = info.getLeft();
        Server.getInstance().getWorldById(worldId).getChannelById(channel).removeClientFromTransfer(charId);
        c.setAccount(info.getRight());
        c.setChannel(channel);
        c.setChannelInstance(Server.getInstance().getWorldById(worldId).getChannelById(channel));
        Char chr = Char.getFromDBById(charId);
        chr.setClient(c);
        c.setChr(chr);
        chr.setJobHandler(JobManager.getJobById(chr.getJob()));
        Field field = c.getChannelInstance().getField(chr.getFieldID() <= 0 ? 100000000 : chr.getFieldID());
        field.addChar(chr);
        chr.setField(field);
        c.write(WvsContext.updateEventNameTag(new int[]{}));
        c.write(Stage.setField(chr, field, c.getChannel(), false, 0, true, false,
                (byte) 0, false, 100, null, true, -1));
        c.write(WvsContext.changeSkillRecordResult(chr.getSkills(), true, false, false, false));
        c.write(CField.funcKeyMappedManInit(chr.getFuncKeyMap()));
        field.spawnLifesForChar(chr);
    }

    public static void handleMove(Client c, InPacket inPacket) {
        inPacket.decodeByte();
        inPacket.decodeBytes(13);
        Position oldPos = inPacket.decodePosition();
        inPacket.decodeInt();
        Char chr = c.getChr();
        Position oldPosition = chr.getPosition();
        List<Movement> movements = WvsContext.parseMovement(inPacket);
        for(Movement m : movements) {
            Position pos = m.getPosition();
            chr.setOldPosition(chr.getPosition());
            chr.setPosition(pos);
            chr.setMoveAction(m.getMoveAction());
        }
    }

    public static void handleChat(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        inPacket.decodeInt();
        String msg = inPacket.decodeString();
        if (msg.length() > 0 && msg.charAt(0) == '@') {
            if (msg.equalsIgnoreCase("@check")) {
                WvsContext.dispose(c, c.getChr());
                chr.chatMessage(ChatMsgColour.YELLOW, String.format("X=%d, Y=%d", chr.getPosition().getX(), chr.getPosition().getY()));
            } else if(msg.equalsIgnoreCase("@save")){
                chr.updateDB();
            }
        } else if (msg.charAt(0) == AdminCommand.getPrefix()) {
            for (Class clazz : AdminCommands.class.getClasses()) {
                if(!(AdminCommand.getPrefix()+clazz.getSimpleName()).equalsIgnoreCase(msg.split(" ")[0])) {
                    continue;
                }
                try {
                    AdminCommand adminCommand = (AdminCommand) clazz.getConstructor().newInstance();
                    Method method = clazz.getDeclaredMethod("execute", Char.class, String[].class);
                    String[] split = msg.split(" ");
                    method.invoke(adminCommand, c.getChr(), split);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void handleInventoryOperation(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        inPacket.decodeInt(); // update tick
        InvType invType = InvType.getInvTypeByVal(inPacket.decodeByte());
        short oldPos = inPacket.decodeShort();
        short newPos = inPacket.decodeShort();
        short quantity = inPacket.decodeShort();
        InvType invTypeFrom = invType == EQUIP ? oldPos < 0 ? EQUIPPED : EQUIP : invType;
        InvType invTypeTo = invType == EQUIP ?  newPos < 0 ? EQUIPPED : EQUIP : invType;
        Item item = chr.getInventoryByInvType(invTypeFrom).getItemBySlot(oldPos);
        if(item == null) {
            return;
        }
        chr.chatMessage(ChatMsgColour.YELLOW, "ItemID = " + item.getItemId() + ", Inventory ID = " + item.getInventoryId());
        item.setBagIndex(newPos);
        if(invType == EQUIP && invTypeFrom != invTypeTo) {
            if(invTypeFrom == EQUIPPED) {
                chr.unequip(item);
            } else {
                chr.equip(item);
            }
//            Inventory to = chr.getInventoryByInvType(invTypeTo);
//            item.setInventoryId(to.getId());
        }
        chr.chatMessage(ChatMsgColour.YELLOW, "ItemID = " + item.getItemId() + ", Inventory ID = " + item.getInventoryId());
         // TODO dropping items
        c.write(WvsContext.inventoryOperation(c.getChr(), true, false, (byte) 2, oldPos, newPos, invType, quantity,
                0, item));


    }

    public static void handleMagicAttack(Client c, InPacket inPacket) {
        AttackInfo attackInfo = new AttackInfo();
        boolean fieldKey = inPacket.decodeByte() == 1;
        byte mask = inPacket.decodeByte();
        byte hits = (byte) (mask & 0xF);
        byte mobCount = (byte) (mask >>> 4);
        int skillId = inPacket.decodeInt();
        byte slv = inPacket.decodeByte();
        inPacket.decodeInt(); // crc
        int keyDown = -1;
        if (SkillConstants.isKeyDownSkill(skillId)) {
            keyDown = inPacket.decodeInt();
        }
        inPacket.decodeByte(); // some zero byte
        byte idk = inPacket.decodeByte();
        short maskie = inPacket.decodeShort();
        boolean left = ((maskie >> 15) & 1) != 0;
        short attackAction = (short) (maskie & 0x7FFF);
        inPacket.decodeInt(); // another crc (GETCRC32Svr<long>(&a[*n], 0x405u))
        byte attackActionType = inPacket.decodeByte();
        byte idk0 = 0;
        if (SkillConstants.isEvanForceSkill(skillId)) {
            idk0 = inPacket.decodeByte();
        }
        byte mask2 = inPacket.decodeByte();
        byte attackSpeed = (byte) (mask2 & 0xFFFF);
        byte reduceCount = (byte) (mask2 >>> 4);
        int psdTargetPlus = inPacket.decodeInt();
        int id = inPacket.decodeInt();
        attackInfo.fieldKey = fieldKey;
        attackInfo.hits = hits;
        attackInfo.mobCount = mobCount;
        attackInfo.skillId = skillId;
        attackInfo.slv = slv;
        attackInfo.keyDown = keyDown;
        attackInfo.idk = idk;
        attackInfo.left = left;
        attackInfo.attackAction = attackAction;
        attackInfo.attackActionType = attackActionType;
        attackInfo.idk0 = idk0;
        attackInfo.attackSpeed = attackSpeed;
        attackInfo.reduceCount = reduceCount;
        attackInfo.psdTargetPlus = psdTargetPlus;
        attackInfo.someId = id;
        for (int i = 0; i < mobCount; i++) {
            MobAttackInfo mai = new MobAttackInfo();
            int mobId = inPacket.decodeInt();
            byte idk1 = inPacket.decodeByte();
            byte idk2 = inPacket.decodeByte();
            byte idk3 = inPacket.decodeByte();
            byte idk4 = inPacket.decodeByte();
            byte idk5 = inPacket.decodeByte();
            int templateID = inPacket.decodeInt();
            byte calcDamageStatIndex = inPacket.decodeByte();
            short rcDstX = inPacket.decodeShort();
            short rectRight = inPacket.decodeShort();
            short oldPosX = inPacket.decodeShort(); // ?
            short oldPosY = inPacket.decodeShort(); // ?
            short hpPerc = inPacket.decodeByte();
            byte bIdk6;
            short sIdk6;
            if(skillId == 80001835) {
                bIdk6 = inPacket.decodeByte();
            } else {
                sIdk6 = inPacket.decodeShort();
            }
            short size = attackInfo.hits;
            int[] damages = new int[size];
            for (int j = 0; j < size; j++) {
                damages[j] = inPacket.decodeInt();
            }
            int mobUpDownYRange = inPacket.decodeInt();
            inPacket.decodeInt(); // mob crc
            // Begin PACKETMAKER::MakeAttackInfoPacket
            byte type = inPacket.decodeByte();
            String currentAnimationName = "";
            int animationDeltaL = 0;
            String[] hitPartRunTimes = new String[0];
            if (type == 1) {
                currentAnimationName = inPacket.decodeString();
                animationDeltaL = inPacket.decodeInt();
                int hitPartRunTimesSize = inPacket.decodeInt();
                hitPartRunTimes = new String[hitPartRunTimesSize];
                for (int j = 0; j < hitPartRunTimesSize; j++) {
                    hitPartRunTimes[j] = inPacket.decodeString();
                }
            } else if (type == 2) {
                currentAnimationName = inPacket.decodeString();
                animationDeltaL = inPacket.decodeInt();
            }
            // End PACKETMAKER::MakeAttackInfoPacket
            mai.mobId = mobId;
            mai.idk1 = idk1;
            mai.idk2 = idk2;
            mai.idk3 = idk3;
            mai.idk4 = idk4;
            mai.idk5 = idk5;
            mai.templateID = templateID;
            mai.calcDamageStatIndex = calcDamageStatIndex;
            mai.rcDstX = rcDstX;
            mai.rectRight = rectRight;
            mai.oldPosX = oldPosX;
            mai.oldPosY = oldPosY;
            mai.hpPerc = hpPerc;
            mai.damages = damages;
            mai.mobUpDownYRange = mobUpDownYRange;
            mai.type = type;
            mai.currentAnimationName = currentAnimationName;
            mai.animationDeltaL = animationDeltaL;
            mai.hitPartRunTimes = hitPartRunTimes;
            attackInfo.mobAttackInfo.add(mai);
        }
        if (skillId > 27111303) {
            if (skillId == 27121052 || skillId == 80001837) {
                int x = inPacket.decodeShort();
                int y = inPacket.decodeShort();
                attackInfo.x = x;
                attackInfo.y = y;
            }
        } else if (skillId != 32111016) {
            short forcedX = inPacket.decodeShort();
            short forcedY = inPacket.decodeShort();
            boolean dragon = inPacket.decodeByte() != 0;
            attackInfo.forcedX = forcedX;
            attackInfo.forcedY = forcedY;
            if(dragon) {
                short rcDstRight = inPacket.decodeShort();
                short rectRight = inPacket.decodeShort();
                short x = inPacket.decodeShort();
                short y = inPacket.decodeShort();
                inPacket.decodeByte(); // always 0
                inPacket.decodeByte(); // -1
                inPacket.decodeByte(); // 0
                attackInfo.rcDstRight = rcDstRight;
                attackInfo.rectRight = rectRight;
                attackInfo.x = x;
                attackInfo.y = y;
            }
            if(skillId == 12100029) {
                int option = inPacket.decodeInt();
                attackInfo.option = option;
            } else {
                switch(skillId) {
                    case 2121003:
                        byte size = inPacket.decodeByte();
                        int[] idkArr = new int[size];
                        for (int i = 0; i < size; i++) {
                            idkArr[i] = inPacket.decodeInt();
                        }
                        attackInfo.idkArr = idkArr;
                        break;
                    case 2111003: // poison mist
                        byte force = inPacket.decodeByte();
                        short forcedXSh = inPacket.decodeShort();
                        short forcedYSh = inPacket.decodeShort();
                        attackInfo.force = force;
                        attackInfo.forcedXSh = forcedXSh;
                        attackInfo.forcedYSh = forcedYSh;
                        break;
                    case 80001835: // unreachable?
                        byte sizeB = inPacket.decodeByte();
                        int[] idkArr2 = new int[sizeB];
                        short[] shortArr2 = new short[sizeB];
                        for (int i = 0; i < sizeB; i++) {
                            idkArr2[i] = inPacket.decodeInt();
                            shortArr2[i] = inPacket.decodeShort();
                        }
                        short delay = inPacket.decodeShort();
                        attackInfo.idkArr = idkArr2;
                        attackInfo.shortArr = shortArr2;
                        attackInfo.delay = delay;
                }
            }
        }
        handleAttack(c, attackInfo);
    }

    private static void handleAttack(Client c, AttackInfo attackInfo) {
        c.getChr().chatMessage(ChatMsgColour.YELLOW, "SkillID: " + attackInfo.skillId);
        Field field = c.getChr().getField();
        for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) field.getLifeByObjectID(mai.mobId);
            long totalDamage = 0;
            for(int dmg : mai.damages) {
                totalDamage += dmg;
            }
            mob.damage(totalDamage);
        }
        c.getChr().getJobHandler().handleAttack(c, attackInfo);
    }


    public static void handleChangeFieldRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        byte idk = inPacket.decodeByte();
        int idk1 = inPacket.decodeInt();
        int x = inPacket.decodeShort();
        int y = inPacket.decodeShort();
        String portalName = inPacket.decodeString();
        Field field = chr.getField();
        Portal portal = field.getPortalByName(portalName);
        Field toField = c.getChannelInstance().getField(portal.getTargetMapId());
        chr.setField(toField);
        Portal toPortal = toField.getPortalByName(portal.getTargetPortalName());
        field.removeChar(chr);
        toField.addChar(chr);
        c.write(Stage.setField(chr, toField, c.getChannel(), false, 0, false, chr.hasBuffProtector(),
                (byte) toPortal.getId(), false, 100, null, false, -1));
        toField.spawnLifesForChar(chr);
    }

    public static void handleSkillRecordUpdateRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        inPacket.decodeInt(); // tick
        int skillID = inPacket.decodeInt();
        int amount = inPacket.decodeInt();
        Skill skill = chr.getSkill(skillID, true);
        byte jobLevel = (byte) JobConstants.getJobLevel((short) skill.getRootId());
        Map<Stat, Object> stats = null;
        if(JobConstants.isExtendSpJob(chr.getJob())) {
            ExtendSP esp = chr.getAvatarData().getCharacterStat().getExtendSP();
            int currentSp = esp.getSpByJobLevel(jobLevel);
            if (currentSp >= amount) {
                int curLevel = skill.getCurrentLevel();
                int max = skill.getMaxLevel();
                int newLevel = curLevel + amount > max ? max : curLevel + amount;
                skill.setCurrentLevel(newLevel);
                esp.setSpToJobLevel(jobLevel, currentSp - amount);
                stats = new HashMap<>();
                stats.put(Stat.sp, esp);
            }
        } else {
            int currentSp = chr.getAvatarData().getCharacterStat().getSp();
            if(currentSp >= amount) {
                int curLevel = skill.getCurrentLevel();
                int max = skill.getMaxLevel();
                int newLevel = curLevel + amount > max ? max : curLevel + amount;
                skill.setCurrentLevel(newLevel);
                chr.getAvatarData().getCharacterStat().setSp(currentSp - amount);
                stats = new HashMap<>();
                stats.put(Stat.sp, chr.getAvatarData().getCharacterStat().getSp());
            }
        }
        if(stats != null) {
            c.write(WvsContext.statChanged(stats, true));
            List<Skill> skills = new ArrayList<>();
            skills.add(skill);
            chr.addSkill(skill);
            c.write(WvsContext.changeSkillRecordResult(skills, true, false, false, false));
        }
    }

    public static void handleMeleeAttack(Client c, InPacket inPacket) {
        AttackInfo ai = new AttackInfo();
        ai.fieldKey = inPacket.decodeByte() == 1;
        byte mask = inPacket.decodeByte();
        ai.hits = (byte) (mask & 0xF);
        ai.mobCount = (byte) (mask >>> 4);
        ai.skillId = inPacket.decodeInt();
        ai.slv = inPacket.decodeByte();
        ai.addAttackProc = inPacket.decodeByte();
        inPacket.decodeInt(); // crc
        int skillID = ai.skillId;
        if(SkillConstants.isKeyDownSkill(skillID) || SkillConstants.isSuperNovaSkill(skillID)) {
            ai.keyDown = inPacket.decodeInt();
        }
        if(SkillConstants.isRushBombSkill(skillID) || skillID == 5300007 || skillID == 27120211 || skillID == 14111023) {
            ai.grenadeId = inPacket.decodeInt();
        }
        if(SkillConstants.isZeroSkill(skillID)) {
            ai.zero = inPacket.decodeByte();
        }
        if(SkillConstants.isUsercloneSummonedAbleSkill(skillID)) {
            ai.bySummonedID = inPacket.decodeInt();
        }
        byte idk = inPacket.decodeByte();
        ai.idk = inPacket.decodeByte();
        short maskie = inPacket.decodeShort();
        ai.left = ((maskie >> 15) & 1) != 0;
        ai.attackAction = (short) (maskie & 0x7FFF);
        inPacket.decodeInt(); // crc
        ai.attackActionType = inPacket.decodeByte();
        ai.idk0 = inPacket.decodeByte();
        int idkInt = inPacket.decodeInt();
        ai.ptTarget.setY(inPacket.decodeInt());
        ai.finalAttackLastSkillID = inPacket.decodeInt();
        if(ai.finalAttackLastSkillID > 0) {
            ai.finalAttackByte = inPacket.decodeByte();
        }
        if(skillID == 5111009) {
            ai.ignorePCounter = inPacket.decodeByte() != 0;
        }
        /*if ( v1756 )
          {
            COutPacket::Encode2(&a, v1747);
            if ( v674 || is_noconsume_usebullet_melee_attack(v669) )
              COutPacket::Encode4(&a, v1748);
          }*/
        if(skillID == 25111005) {
            ai.spiritCoreEnhance = inPacket.decodeInt();
        }
        for (int i = 0; i < ai.mobCount; i++) {
            MobAttackInfo mai = new MobAttackInfo();
            int mobId = inPacket.decodeInt();
            byte idk1 = inPacket.decodeByte();
            byte idk2 = inPacket.decodeByte();
            byte idk3 = inPacket.decodeByte();
            byte idk4 = inPacket.decodeByte();
            byte idk5 = inPacket.decodeByte();
            int templateID = inPacket.decodeInt();
            byte calcDamageStatIndex = inPacket.decodeByte();
            short rcDstX = inPacket.decodeShort();
            short rectRight = inPacket.decodeShort();
            short idk6 = inPacket.decodeShort();
            short oldPosX = inPacket.decodeShort(); // ?
            short oldPosY = inPacket.decodeShort(); // ?
            int[] damages = new int[ai.hits];
            for (int j = 0; j < ai.hits; j++) {
                damages[j] = inPacket.decodeInt();
            }
            int mobUpDownYRange = inPacket.decodeInt();
            inPacket.decodeInt(); // crc
            boolean isResWarriorLiftPress = false;
            if(skillID == 37111005) {
                isResWarriorLiftPress = inPacket.decodeByte() != 0;
            }
            // Begin PACKETMAKER::MakeAttackInfoPacket
            byte type = inPacket.decodeByte();
            String currentAnimationName = "";
            int animationDeltaL = 0;
            String[] hitPartRunTimes = new String[0];
            if (type == 1) {
                currentAnimationName = inPacket.decodeString();
                animationDeltaL = inPacket.decodeInt();
                int hitPartRunTimesSize = inPacket.decodeInt();
                hitPartRunTimes = new String[hitPartRunTimesSize];
                for (int j = 0; j < hitPartRunTimesSize; j++) {
                    hitPartRunTimes[j] = inPacket.decodeString();
                }
            } else if (type == 2) {
                currentAnimationName = inPacket.decodeString();
                animationDeltaL = inPacket.decodeInt();
            }
            // End PACKETMAKER::MakeAttackInfoPacket
            mai.mobId = mobId;
            mai.idk1 = idk1;
            mai.idk2 = idk2;
            mai.idk3 = idk3;
            mai.idk4 = idk4;
            mai.idk5 = idk5;
            mai.templateID = templateID;
            mai.calcDamageStatIndex = calcDamageStatIndex;
            mai.rcDstX = rcDstX;
            mai.rectRight = rectRight;
            mai.oldPosX = oldPosX;
            mai.oldPosY = oldPosY;
            mai.idk6 = idk6;
            mai.damages = damages;
            mai.mobUpDownYRange = mobUpDownYRange;
            mai.type = type;
            mai.currentAnimationName = currentAnimationName;
            mai.animationDeltaL = animationDeltaL;
            mai.hitPartRunTimes = hitPartRunTimes;
            mai.isResWarriorLiftPress = isResWarriorLiftPress;
            ai.mobAttackInfo.add(mai);
        }
        if(skillID == 61121052 || skillID == 36121052 || SkillConstants.isScreenCenterAttackSkill(skillID)) {
            ai.ptTarget.setX(inPacket.decodeShort());
            ai.ptTarget.setY(inPacket.decodeShort());
        } else {
            if(SkillConstants.isSuperNovaSkill(skillID)) {
                ai.ptAttackRefPoint.setX(inPacket.decodeShort());
                ai.ptAttackRefPoint.setY(inPacket.decodeShort());
            }
            if(skillID == 101000102) {
                ai.idkPos.setX(inPacket.decodeShort());
                ai.idkPos.setY(inPacket.decodeShort());
            }
            ai.pos.setX(inPacket.decodeShort());
            ai.pos.setY(inPacket.decodeShort());
            if(SkillConstants.isAranFallingStopSkill(skillID)) {
                ai.fh = inPacket.decodeByte();
            }
            if(skillID == 21120019 || skillID == 37121052) {
                ai.teleportPt.setX(inPacket.decodeInt());
                ai.teleportPt.setY(inPacket.decodeInt());
            }
            if(skillID == 61121105 || skillID == 61121222 || skillID == 24121052) {
                ai.Vx = inPacket.decodeShort();
                short x, y;
                for (int i = 0; i < ai.Vx; i++) {
                    x = inPacket.decodeShort();
                    y = inPacket.decodeShort();
                }
            }
            if(skillID == 101120104) {
                // CUser::EncodeAdvancedEarthBreak
                // TODO
            }
            if(skillID == 14111006 && ai.grenadeId != 0) {
                ai.grenadePos.setX(inPacket.decodeShort());
                ai.grenadePos.setY(inPacket.decodeShort());
            }
        }
        handleAttack(c, ai);
    }

    public static void handleBodyAttack(Client c, InPacket inPacket) {
        AttackInfo ai = new AttackInfo();
        ai.fieldKey = inPacket.decodeByte() != 0;
        byte mask = inPacket.decodeByte();
        ai.hits = (byte) (mask & 0xF);
        ai.mobCount = (byte) (mask >>> 4);
        ai.skillId = inPacket.decodeInt();
        ai.slv = inPacket.decodeByte();
        inPacket.decodeInt(); // crc
        ai.areaPAD = inPacket.decodeByte() >>> 3;
        byte nul = inPacket.decodeByte(); // encoded as 0
        ai.left = ((inPacket.decodeShort() >>> 15) & 1) != 0;
        ai.attackCount = inPacket.decodeInt();
        ai.attackSpeed = inPacket.decodeByte(); // encoded as 0
        ai.wt = inPacket.decodeInt();
        ai.ar01Mad = inPacket.decodeInt(); // only done if mage skill
        byte idk2 = inPacket.decodeByte();

        if(ai.skillId > 0) {
            for (int i = 0; i < ai.mobCount; i++) {
                MobAttackInfo mai = new MobAttackInfo();
                mai.mobId = inPacket.decodeInt();
                mai.idkInt = inPacket.decodeInt();
                mai.calcDamageStatIndex = inPacket.decodeByte();
                mai.templateID = inPacket.decodeInt();
                mai.rect = new Rect(inPacket.decodePosition(), inPacket.decodePosition());
                mai.idk6 = inPacket.decodeShort();
                mai.idk1 = inPacket.decodeByte();
                int[] damages = new int[ai.hits];
                for (int j = 0; j < ai.hits; j++) {
                    damages[j] = inPacket.decodeInt();
                }
                mai.damages = damages;
                mai.mobUpDownYRange = inPacket.decodeInt();
                inPacket.decodeInt(); // crc
                // Begin PACKETMAKER::MakeAttackInfoPacket
                byte type = inPacket.decodeByte();
                String currentAnimationName = "";
                int animationDeltaL = 0;
                String[] hitPartRunTimes = new String[0];
                if (type == 1) {
                    currentAnimationName = inPacket.decodeString();
                    animationDeltaL = inPacket.decodeInt();
                    int hitPartRunTimesSize = inPacket.decodeInt();
                    hitPartRunTimes = new String[hitPartRunTimesSize];
                    for (int j = 0; j < hitPartRunTimesSize; j++) {
                        hitPartRunTimes[j] = inPacket.decodeString();
                    }
                } else if (type == 2) {
                    currentAnimationName = inPacket.decodeString();
                    animationDeltaL = inPacket.decodeInt();
                }
                // End PACKETMAKER::MakeAttackInfoPacket
                mai.type = type;
                mai.currentAnimationName = currentAnimationName;
                mai.animationDeltaL = animationDeltaL;
                mai.hitPartRunTimes = hitPartRunTimes;
                ai.mobAttackInfo.add(mai);
            }
        }
        ai.pos = inPacket.decodePosition();
        handleAttack(c, ai);
    }

    public static void handleAbilityPointDistribute(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        if(chr.getStat(Stat.ap) <= 0) {
            return;
        }
        inPacket.decodeInt(); // tick
        short stat = inPacket.decodeShort();
        Stat charStat = Stat.getByVal(stat);
        short amount = 1;
        if(charStat == Stat.mmp || charStat == Stat.mhp) {
            amount = 20;
        }
        chr.addStat(charStat, amount);
        chr.addStat(Stat.ap, (short) -1);
        Map<Stat, Object> stats = new HashMap<>();
        stats.put(charStat, chr.getStat(charStat));
        stats.put(Stat.ap, chr.getStat(Stat.ap));
        c.write(WvsContext.statChanged(stats, false));
        WvsContext.dispose(c, chr);
    }

    public static void handleAutoAbilityPointDistribute(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        inPacket.decodeInt(); // tick
        int type = inPacket.decodeInt();
        Stat charStat = null;
        short amount;
        if(type == 1) {
            charStat = Stat.getByVal(inPacket.decodeShort());
        } else if(type == 2) {
            inPacket.decodeInt();
            inPacket.decodeInt();
            inPacket.decodeInt();
            charStat = Stat.getByVal(inPacket.decodeShort());
        }
        inPacket.decodeInt();
        inPacket.decodeShort();
        amount = inPacket.decodeShort();
        short addStat = amount;
        if(chr.getStat(Stat.ap) < amount) {
            return;
        }
        if(charStat == Stat.mmp || charStat == Stat.mhp) {
            addStat *= 20;
        }
        chr.addStat(charStat, addStat);
        chr.addStat(Stat.ap, (short) -amount);
        Map<Stat, Object> stats = new HashMap<>();
        stats.put(charStat, chr.getStat(charStat));
        stats.put(Stat.ap, chr.getStat(Stat.ap));
        c.write(WvsContext.statChanged(stats, false));
        WvsContext.dispose(c, chr);
    }

    public static void handleMoveLife(Client c, InPacket inPacket) {
        Field field = c.getChr().getField();
        int objectID = inPacket.decodeInt();
        Life life = field.getLifeByObjectID(objectID);
        if(life == null) {
            return;
        }
        byte idk0 = inPacket.decodeByte();
        short moveID = inPacket.decodeShort();
        boolean usedSkill = inPacket.decodeByte() != 0;
        byte skill = inPacket.decodeByte();
        int idk1 = inPacket.decodeInt();
        byte idk2 = inPacket.decodeByte();
        // for: short, short

        byte idk3 = inPacket.decodeByte();
        // for: short

        // ?
        byte idk4 = inPacket.decodeByte();

        // ?
        int idk5 = inPacket.decodeInt();

        // ?
        int idk6 = inPacket.decodeInt();
        int idk7 = inPacket.decodeInt();

        // ?
        int idk8 = inPacket.decodeInt();
        byte idk9 = inPacket.decodeByte();
        int encodedGatherDuration = inPacket.decodeInt();
        Position pos = inPacket.decodePosition();
        Position vPos = inPacket.decodePosition();
        Position oldPos = life.getPosition();
        List<Movement> movements = WvsContext.parseMovement(inPacket);
        int skillID = 0;
        int slv = 0;
        if(movements.size() > 0) {
            if(life instanceof Mob) {
                c.write(CField.mobCtrlAck((Mob) life, true, moveID, skillID, (byte) slv));
            }
            for(Movement m : movements) {
                Position p = m.getPosition();
                life.setPosition(p);
                life.setMoveAction(m.getMoveAction());
            }
        }
    }

    public static void handleTemporaryStatResetRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int skillId = inPacket.decodeInt();
        Map<CharacterTemporaryStat, Option> removedMap = new HashMap<>();
        for(CharacterTemporaryStat cts : tsm.getCurrentStats().keySet()) {
            if(tsm.getOption(cts).rOption == skillId || tsm.getOption(cts).nReason == skillId) {
                removedMap.put(cts, tsm.getOption(cts));
            }
        }
        removedMap.forEach((cts, opt) -> tsm.removeStat(cts, false));
        c.write(WvsContext.temporaryStatReset(chr.getTemporaryStatManager(), false));
    }

    public static void handleKeymapUpdateRequest(Client c, InPacket inPacket) {
        inPacket.decodeInt(); // 0
        int size = inPacket.decodeInt();
        for (int i = 0; i < size; i++) {
            int index = inPacket.decodeInt();
            byte type = inPacket.decodeByte();
            int value = inPacket.decodeInt();
            c.getChr().getFuncKeyMap().putKeyBinding(index, type, value);
        }
    }
}
