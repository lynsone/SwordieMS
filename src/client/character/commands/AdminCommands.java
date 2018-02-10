package client.character.commands;

import client.character.Char;
import client.character.items.Equip;
import client.character.items.Item;
import client.character.skills.*;
import client.field.Field;
import client.field.Portal;
import client.jobs.adventurer.Pirate;
import client.jobs.resistance.WildHunter;
import client.jobs.resistance.WildHunterInfo;
import client.life.Life;
import client.life.Mob;
import connection.OutPacket;
import constants.JobConstants.JobEnum;
import enums.ForceAtomEnum;
import enums.InvType;
import enums.Stat;
import handling.OutHeader;
import loaders.*;
import packet.CField;
import packet.Stage;
import packet.WvsContext;
import util.Position;
import util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static enums.ChatMsgColour.GAME_NOTICE;
import static enums.ChatMsgColour.YELLOW;

/**
 * Created on 12/22/2017.
 */
public class AdminCommands {

    public static class Test extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            WildHunterInfo wi = new WildHunterInfo();
            wi.setIdx((byte) 1);
            wi.setRidingType((byte) 1);
            chr.write(WvsContext.wildHunterInfo(wi));
//            new TemporaryStatManager(null).encodeForLocal(null);
            CharacterTemporaryStat cts = CharacterTemporaryStat.RideVehicle;

            OutPacket outPacket = new OutPacket(OutHeader.TEMPORARY_STAT_SET);

//            tsm.encodeForLocal(outPacket);
            // Start encodeForLocal
            int[] mask = new int[CharacterTemporaryStat.length];
            mask[cts.getPos()] |= cts.getVal();
            for(int i = 0; i < mask.length; i++) {
                outPacket.encodeInt(mask[i]);
            }
            System.out.println("[Out]\t| " + outPacket);

            short size = 0;
            outPacket.encodeShort(0);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0); // nKey
                outPacket.encodeByte(0); // bEnable
            }
            outPacket.encodeByte(0); // defenseAtt
            outPacket.encodeByte(0); // defenseState
            outPacket.encodeByte(0); // pvpDamage
            outPacket.encodeInt(0); // viperCharge
            // Start TSTS encode
            outPacket.encodeBytes(new byte[Integer.parseInt(args[2])]);
            outPacket.encodeInt(1);
            outPacket.encodeInt(80001001);
            outPacket.encodeByte(1);
            outPacket.encodeByte(0);
            outPacket.encodeBytes(new byte[Integer.parseInt(args[1])]);
//            outPacket.encodeShort(1);
            // End TSTS encode
            // End  encodeForLocal
            outPacket.encodeInt(0); // indie?
            outPacket.encodeShort(0); // invalid value => "Failed to use the skill for an unknown reason"
            outPacket.encodeByte(0);
            outPacket.encodeByte(0);
            outPacket.encodeByte(0);




//            outPacket.encodeInt(0); // ?
//            if(tsm.hasNewMovingEffectingStat()) {
//                outPacket.encodeByte(0);
//            }


            chr.write(outPacket);
        }
    }


    public static class Spawn extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int id = Integer.parseInt(args[1]);
            int count = 1;
            if (args.length > 2) {
                count = Integer.parseInt(args[2]);
            }
            for (int i = 0; i < count; i++) {
                Mob mob = MobData.getMobDeepCopyById(id);
                Field field = chr.getField();
                Position pos = chr.getPosition();
                mob.setPosition(pos.deepCopy());
                mob.setPrevPos(pos.deepCopy());
                mob.setPosition(pos.deepCopy());
                mob.getForcedMobStat().setMaxMP(Integer.MAX_VALUE);
                mob.setMaxHp(Integer.MAX_VALUE);
                mob.setHp(Integer.MAX_VALUE);
                mob.setNotRespawnable(true);
                if (mob.getField() == null) {
                    mob.setField(field);
                }
                field.spawnLife(mob, null);

                System.out.println("Mob has id " + mob.getObjectId());
            }
        }
    }

    public static class GetItem extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int id = Integer.parseInt(args[1]);
            Equip equip = ItemData.getEquipDeepCopyFromId(id);
            if (equip == null) {
                Item item = ItemData.getItemDeepCopy(id);
                chr.addItemToInventory(item.getInvType(), item, false);
                short quant = 1;
                if (args.length > 2) {
                    quant = Short.parseShort(args[2]);
                }
                item.setQuantity(quant);
                chr.getClient().write(WvsContext.inventoryOperation(chr, true, false,
                        (byte) 0, (short) item.getBagIndex(), (byte) -1, item.getInvType(), (byte) item.getQuantity(),
                        0, item));
            } else {
                chr.addItemToInventory(InvType.EQUIP, equip, false);
                chr.getClient().write(WvsContext.inventoryOperation(chr, true, false,
                        (byte) 0, (short) equip.getBagIndex(), (byte) -1, equip.getInvType(), (byte) 1,
                        0, equip));
            }
        }
    }

    public static class GetProjectiles extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int subi = 2070000;
            int arrowBow = 2060000;
            int arrowXBow = 2061001;
            int bullet = 2330000;
            Item item = ItemData.getItemDeepCopy(subi);
            chr.addItemToInventory(item.getInvType(), item, false);
            Item item2 = ItemData.getItemDeepCopy(arrowBow);
            chr.addItemToInventory(item.getInvType(), item2, false);
            Item item3 = ItemData.getItemDeepCopy(arrowXBow);
            chr.addItemToInventory(item.getInvType(), item3, false);
            Item item4 = ItemData.getItemDeepCopy(bullet);
            chr.addItemToInventory(item.getInvType(), item4, false);
            item.setQuantity(2000);
            item2.setQuantity(2000);
            item3.setQuantity(2000);
            item4.setQuantity(2000);
            chr.getClient().write(WvsContext.inventoryOperation(chr, true, false,
                    (byte) 0, (short) item.getBagIndex(), (byte) -1, item.getInvType(), (byte) item.getQuantity(),
                    0, item));
            chr.getClient().write(WvsContext.inventoryOperation(chr, true, false,
                    (byte) 0, (short) item2.getBagIndex(), (byte) -1, item2.getInvType(), (byte) item2.getQuantity(),
                    0, item2));
            chr.getClient().write(WvsContext.inventoryOperation(chr, true, false,
                    (byte) 0, (short) item3.getBagIndex(), (byte) -1, item3.getInvType(), (byte) item3.getQuantity(),
                    0, item3));
            chr.getClient().write(WvsContext.inventoryOperation(chr, true, false,
                    (byte) 0, (short) item4.getBagIndex(), (byte) -1, item4.getInvType(), (byte) item4.getQuantity(),
                    0, item4));
        }
    }

    public static class Job extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            short id = Short.parseShort(args[1]);
            JobEnum job = JobEnum.getJobById(id);
            if (job != null) {
                chr.setJob(id);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.subJob, id);
                chr.getClient().write(WvsContext.statChanged(stats, true, (byte) -1, (byte) 0, (byte) 0, (byte) 0, false, 0, 0));
            }
        }
    }

    public static class Sp extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setSpToCurrentJob(num);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.sp, chr.getAvatarData().getCharacterStat().getExtendSP());
                chr.getClient().write(WvsContext.statChanged(stats, true, (byte) -1, (byte) 0, (byte) 0, (byte) 0, false, 0, 0));
            }
        }
    }

    public static class Ap extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setStat(Stat.ap, (short) num);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.ap, (short) num);
                chr.getClient().write(WvsContext.statChanged(stats));
            }
        }
    }

    public static class Hp extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setStat(Stat.hp, num);
                chr.setStat(Stat.mhp, num);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.hp, num);
                stats.put(Stat.mhp, num);
                chr.getClient().write(WvsContext.statChanged(stats));
            }
        }
    }

    public static class Mp extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setStat(Stat.mp, num);
                chr.setStat(Stat.mmp, num);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.mp, num);
                stats.put(Stat.mmp, num);
                chr.getClient().write(WvsContext.statChanged(stats));
            }
        }
    }

    public static class Level extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setStat(Stat.level, (short) num);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.level, (byte) num);
                stats.put(Stat.exp, (long) 0);
                chr.getClient().write(WvsContext.statChanged(stats));
            }
        }
    }

    public static class Heal extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int maxHp = chr.getAvatarData().getCharacterStat().getMaxHp();
            int maxMp = chr.getAvatarData().getCharacterStat().getMaxMp();
            chr.getAvatarData().getCharacterStat().setHp(maxHp);
            chr.getAvatarData().getCharacterStat().setMp(maxMp);
            Map<Stat, Object> stats = new HashMap<>();
            stats.put(Stat.hp, maxHp);
            stats.put(Stat.mp, maxMp);
            chr.getClient().write(WvsContext.statChanged(stats));
        }
    }

    public static class TestTempStat extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            List<Life> lifes = chr.getField().getLifes();
            Life l = lifes.get(lifes.size() - 1);
            if (l == null || !(l instanceof Mob)) {
                return;
            }
            Mob mob = (Mob) l;
            chr.getClient().write(CField.mobStatSet(mob, (short) 0));
        }
    }

    public static class SetMap extends AdminCommand {
        public static void execute(Char chr, String[] args) {

            Field field = chr.getField();
            Field toField = chr.getClient().getChannelInstance().getField(Integer.parseInt(args[1]));
            chr.setField(toField);
            Portal toPortal = toField.getPortalByID(0);
            field.removeChar(chr);
            toField.addChar(chr);
            chr.getClient().write(Stage.setField(chr, toField, chr.getClient().getChannel(), false, 0, false, chr.hasBuffProtector(),
                    (byte) toPortal.getId(), false, 100, null, false, -1));
            toField.spawnLifesForChar(chr);
        }
    }

    public static class Atom extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int charID = chr.getId();
            ForceAtomInfo forceAtomInfo1 = new ForceAtomInfo(142110011, ForceAtomEnum.KINESIS_ORB_REAL.getInc(), 3, 3, 0, 0, (int) System.currentTimeMillis(), 1,
                    142110011, new Position());
            ForceAtomInfo forceAtomInfo2 = new ForceAtomInfo(142110011, ForceAtomEnum.KINESIS_ORB_REAL.getInc(), 3, 3, 0, 0, (int) System.currentTimeMillis(), 1,
                    142110011, new Position());
            List<ForceAtomInfo> fais = new ArrayList<>();
            fais.add(forceAtomInfo1);
            fais.add(forceAtomInfo2);

            Mob mob = (Mob) chr.getField().getLifes().get(chr.getField().getLifes().size() - 1);
            List<Integer> mobs = new ArrayList<>();
            int mobID = mob.getObjectId();
            mobs.add(mobID);
            chr.getClient().write(CField.createForceAtom(false, -1, chr.getId(), ForceAtomEnum.KINESIS_ORB_REAL.getForceAtomType(),
                    true, mobs, 142110011, fais,null, 0, 0, null, 142110011, mob.getPosition()));

        }
    }

    public static class GetSkill extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if (args.length < 4) {
                chr.chatMessage(GAME_NOTICE, "Needs more args! <id> <cur> <max>");
                return;
            }
            int id = Integer.parseInt(args[1]);
            int cur = Integer.parseInt(args[2]);
            int max = Integer.parseInt(args[3]);
            Skill skill = chr.getSkill(Integer.parseInt(args[1]));
            if (skill == null) {
                skill = SkillData.getSkillDeepCopyById(Integer.parseInt(args[1]));
            }
            if (skill == null) {
                chr.chatMessage(YELLOW, "No such skill found.");
                return;
            }
            skill.setCurrentLevel(cur);
            skill.setMasterLevel(max);
            List<Skill> list = new ArrayList<>();
            list.add(skill);
            chr.addSkill(skill);
            chr.getClient().write(WvsContext.changeSkillRecordResult(list, true, false, false, false));
        }
    }

    public static class MaxSkills extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            for (Skill skill : chr.getSkills()) {
                byte maxLevel = (byte) skill.getMaxLevel();
                skill.setCurrentLevel(maxLevel);
                skill.setMasterLevel(maxLevel);
                List<Skill> list = new ArrayList<>();
                list.add(skill);
                chr.addSkill(skill);
                chr.getClient().write(WvsContext.changeSkillRecordResult(list, true, false, false, false));
            }
        }
    }

    public static class Lookup extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if (args.length < 3) {
                chr.chatMessage(GAME_NOTICE, "Needs more args! <what to lookup> <id>");
                return;
            }
            String query = "";
            for (int i = 2; i < args.length; i++) {
                query += args[i].toLowerCase() + " ";
            }
            query = query.substring(0, query.length() - 1);
            System.out.println("Query: " + query);
            boolean isNumber = Util.isNumber(query);
            if ("skill".equalsIgnoreCase(args[1])) {
                SkillStringInfo ssi;
                int id;
                if (isNumber) {
                    id = Integer.parseInt(query);
                    ssi = StringData.getSkillStringById(id);
                    if (ssi == null) {
                        chr.chatMessage(YELLOW, "Cannot find skill " + id);
                        return;
                    }
                    chr.chatMessage(YELLOW, "Name: " + ssi.getName());
                    chr.chatMessage(YELLOW, "Desc: " + ssi.getDesc());
                    chr.chatMessage(YELLOW, "h: " + ssi.getH());
                } else {
                    Map<Integer, SkillStringInfo> map = StringData.getSkillStringByName(query);
                    if(map.size() == 0) {
                        chr.chatMessage(YELLOW, "No skills found for query " + query);
                    }
                    for (Map.Entry<Integer, SkillStringInfo> entry : map.entrySet()) {
                        id = entry.getKey();
                        ssi = entry.getValue();
                        if(SkillData.getSkillInfoById(id) != null) {
                            chr.chatMessage(YELLOW, "Id: " + id);
                            chr.chatMessage(YELLOW, "Name: " + ssi.getName());
                            chr.chatMessage(YELLOW, "Desc: " + ssi.getDesc());
                            chr.chatMessage(YELLOW, "h: " + ssi.getH());
                        }
                    }
                }
            } else if("item".equalsIgnoreCase(args[1])) {
                int id;
                String name;
                if(isNumber) {
                    id = Integer.parseInt(query);
                    name = StringData.getItemStringById(id);
                    if(name == null) {
                        chr.chatMessage(YELLOW, "Cannot find item " + id);
                        return;
                    }
                    chr.chatMessage(YELLOW, "Name: " + name);
                } else {
                    Map<Integer, String> map = StringData.getItemStringByName(query);
                    if(map.size() == 0) {
                        chr.chatMessage(YELLOW, "No items found for query " + query);
                    }
                    for (Map.Entry<Integer, String> entry : map.entrySet()) {
                        id = entry.getKey();
                        name = entry.getValue();
                        Item item = ItemData.getEquipDeepCopyFromId(id);
                        if (item == null) {
                            item = ItemData.getItemDeepCopy(id);
                        }
                        if(item == null) {
                            continue;
                        }
                        chr.chatMessage(YELLOW, "Id: " + id);
                        chr.chatMessage(YELLOW, "Name: " + name);
                    }
                }
            }
        }
    }

    public static class Mesos extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            long mesos = Long.parseLong(args[1]);
           chr.addMoney(mesos);
        }
    }

}
