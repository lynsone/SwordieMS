package client.character.commands;

import client.Client;
import client.character.Char;
import client.character.items.Equip;
import client.character.items.Item;
import client.character.skills.*;
import client.field.Field;
import client.field.Portal;
import client.jobs.nova.Kaiser;
import client.life.Life;
import client.life.Mob;
import connection.OutPacket;
import constants.JobConstants.JobEnum;
import enums.*;
import handling.OutHeader;
import loaders.*;
import packet.CField;
import packet.MobPool;
import packet.Stage;
import packet.WvsContext;
import util.Position;
import util.Rect;
import util.Util;

import java.text.NumberFormat;
import java.util.*;

import static client.character.skills.CharacterTemporaryStat.Morph;
import static enums.ChatMsgColour.*;
import static enums.InventoryOperation.ADD;

/**
 * Created on 12/22/2017.
 */
public class AdminCommands {

    public static class Test extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            for(InvType invType : InvType.values()) {
                chr.chatMessage(YELLOW, invType.toString());
                for(Item item : chr.getInventoryByType(invType).getItems()) {
                    item.setInvType(invType);
                    String name = StringData.getItemStringById(item.getItemId());
                    chr.chatMessage(YELLOW, String.format("%s, %d, %d", name, item.getItemId(), item.getId()));
                }
            }
        }
    }

    public static class TestCTS extends AdminCommand {

        public static void execute(Char chr, String[] args) {

//            WildHunterInfo wi = new WildHunterInfo();
//            wi.setIdx((byte) 1);
//            wi.setRidingType((byte) 1);
//            chr.write(WvsContext.wildHunterInfo(wi));
//            new TemporaryStatManager(null).encodeForLocal(null);
            CharacterTemporaryStat cts = CharacterTemporaryStat.Morph;
//            CharacterTemporaryStat cts2 = CharacterTemporaryStat.Speed;
//
            OutPacket outPacket = new OutPacket(OutHeader.TEMPORARY_STAT_SET);

//            tsm.encodeForLocal(outPacket);
            // Start encodeForLocal
            int[] mask = new int[CharacterTemporaryStat.length];
            mask[cts.getPos()] |= cts.getVal();
            for (int i = 0; i < mask.length; i++) {
                outPacket.encodeInt(mask[i]);
            }
            System.out.println("[Out]\t| " + outPacket);

            outPacket.encodeShort(1); // n                            //Short / Int
            outPacket.encodeInt(Kaiser.FINAL_TRANCE); // r
            outPacket.encodeInt(30000); // t

            //outPacket.encodeInt(0);

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
//            outPacket.encodeBytes(new byte[Integer.parseInt(args[2])]);
//            outPacket.encodeInt(1);
//            outPacket.encodeInt(80001001);
//            outPacket.encodeByte(1);
//            outPacket.encodeByte(0);
//            outPacket.encodeBytes(new byte[Integer.parseInt(args[1])]);
//            outPacket.encodeShort(1);
            // End TSTS encode
            // End  encodeForLocal
            outPacket.encodeInt(0); // indie?
            outPacket.encodeShort(0); // invalid value => "Failed to use the skill for an unknown reason"
            outPacket.encodeByte(0);
            outPacket.encodeByte(0);
            outPacket.encodeByte(0);
            outPacket.encodeByte(0); // movement enhancing
//            outPacket.encodeBytes(new byte[Integer.parseInt(args[1])]);
            chr.write(outPacket);


        }
    }

    public static class checkID extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            chr.chatMessage(GM_BLUE_CHAT, "your charID = " + chr.getId() + " \r\nYour accID = " + chr.getAccId());
        }
    }

    public static class NP extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            Rect rect = new Rect(
                    new Position(
                            chr.getPosition().deepCopy().getX() - 30,
                            chr.getPosition().deepCopy().getY() - 30),
                    new Position(
                            chr.getPosition().deepCopy().getX() + 30,
                            chr.getPosition().deepCopy().getY() + 30)
            );
            chr.chatMessage(GENERAL_CHAT_WHITE, "~~~~~~~~~~");
            chr.chatMessage(GM_BLUE_CHAT, "Current Map: " + NumberFormat.getNumberInstance(Locale.US).format(chr.getFieldID()));
            chr.chatMessage(GM_BLUE_CHAT, ".");
            for (Portal portal : chr.getField().getclosestPortal(rect)) {
                chr.chatMessage(GM_BLUE_CHAT, "Portal Name: " + portal.getName());
                chr.chatMessage(GM_BLUE_CHAT, "Portal ID: " + NumberFormat.getNumberInstance(Locale.US).format(portal.getId()));
                chr.chatMessage(GM_BLUE_CHAT, ".");
            }
            chr.chatMessage(GENERAL_CHAT_WHITE, "~~~~~~~~~~");
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

    public static class ProItem extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if (args.length < 5) {
                chr.chatMessage(GAME_NOTICE, "Needs more args! <id> <Stat> <Attack> <Flame stats>");
            }
            int id = Integer.parseInt(args[1]);
            int stat = Integer.parseInt(args[2]);
            int atk = Integer.parseInt(args[3]);
            int flames = Integer.parseInt(args[4]);
            Equip equip = ItemData.getEquipDeepCopyFromId(id);
            equip.setBaseStat(EquipBaseStat.iStr, stat);
            equip.setBaseStat(EquipBaseStat.iDex, stat);
            equip.setBaseStat(EquipBaseStat.iInt, stat);
            equip.setBaseStat(EquipBaseStat.iLuk, stat);
            equip.setBaseStat(EquipBaseStat.iPAD, atk);
            equip.setBaseStat(EquipBaseStat.iMAD, atk);
            equip.setBaseStat(EquipBaseStat.bdr, flames);
            equip.setBaseStat(EquipBaseStat.imdr, flames);
            equip.setBaseStat(EquipBaseStat.damR, flames);
            equip.setBaseStat(EquipBaseStat.statR, flames);

            chr.addItemToInventory(InvType.EQUIP, equip, false);
            chr.getClient().write(WvsContext.inventoryOperation(true, false,
                    ADD, (short) equip.getBagIndex(), (byte) 1,
                    0, equip));

        }
    }

    public static class GetItem extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if (Util.isNumber(args[1])) {

                int id = Integer.parseInt(args[1]);
                Equip equip = ItemData.getEquipDeepCopyFromId(id);
                if (equip == null) {
                    Item item = ItemData.getItemDeepCopy(id);
                    if (item == null) {
                        chr.chatMessage(YELLOW, String.format("Could not find an item with id %d", id));
                        return;
                    }
                    chr.addItemToInventory(item.getInvType(), item, false);
                    short quant = 1;
                    if (args.length > 2) {
                        quant = Short.parseShort(args[2]);
                    }
                    item.setQuantity(quant);
                    chr.getClient().write(WvsContext.inventoryOperation(true, false,
                            ADD, (short) item.getBagIndex(), (byte) -1, 0, item));
                } else {
                    chr.addItemToInventory(InvType.EQUIP, equip, false);
                    chr.getClient().write(WvsContext.inventoryOperation(true, false,
                            ADD, (short) equip.getBagIndex(), (byte) -1, 0, equip));
                }
            } else {
                StringBuilder query = new StringBuilder();
                int size = args.length;
                short quant = 0;
                if (Util.isNumber(args[size - 1])) {
                    size--;
                    quant = Short.parseShort(args[size]);
                }
                for (int i = 1; i < size; i++) {
                    query.append(args[i].toLowerCase()).append(" ");
                }
                query = new StringBuilder(query.substring(0, query.length() - 1));
                Map<Integer, String> map = StringData.getItemStringByName(query.toString());
                if (map.size() == 0) {
                    chr.chatMessage(YELLOW, "No items found for query " + query);
                }
                for (Map.Entry<Integer, String> entry : map.entrySet()) {
                    int id = entry.getKey();
                    Item item = ItemData.getEquipDeepCopyFromId(id);
                    if (item != null) {
                        Equip equip = (Equip) item;
                        if(equip.getItemId() < 1000000) {
                            continue;
                        }
                        chr.addItemToInventory(equip);
                        chr.getClient().write(WvsContext.inventoryOperation(true, false,
                                ADD, (short) equip.getBagIndex(), (byte) -1, 0, equip));
                        return;
                    }
                    item = ItemData.getItemDeepCopy(id);
                    if (item == null) {
                        continue;
                    }
                    item.setQuantity(quant);
                    chr.addItemToInventory(item);
                    chr.getClient().write(WvsContext.inventoryOperation(true, false,
                            ADD, (short) item.getBagIndex(), (byte) -1, 0, item));
                    return;
                }
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
            chr.getClient().write(WvsContext.inventoryOperation(true, false,
                    ADD, (short) item.getBagIndex(), (byte) -1, 0, item));
            chr.getClient().write(WvsContext.inventoryOperation(true, false,
                    ADD, (short) item2.getBagIndex(), (byte) -1, 0, item2));
            chr.getClient().write(WvsContext.inventoryOperation(true, false,
                    ADD, (short) item3.getBagIndex(), (byte) -1, 0, item3));
            chr.getClient().write(WvsContext.inventoryOperation(true, false,
                    ADD, (short) item4.getBagIndex(), (byte) -1, 0, item4));
        }
    }

    public static class Done extends AdminCommand {
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
            chr.getClient().write(WvsContext.inventoryOperation(true, false,
                    ADD, (short) item.getBagIndex(), (byte) -1, 0, item));
            chr.getClient().write(WvsContext.inventoryOperation(true, false,
                    ADD, (short) item2.getBagIndex(), (byte) -1, 0, item2));
            chr.getClient().write(WvsContext.inventoryOperation(true, false,
                    ADD, (short) item3.getBagIndex(), (byte) -1, 0, item3));
            chr.getClient().write(WvsContext.inventoryOperation(true, false,
                    ADD, (short) item4.getBagIndex(), (byte) -1, 0, item4));


            int num = 1000;
            int hp = 250000;
            int lv = 200;
            chr.setStat(Stat.hp, hp);
            chr.setStat(Stat.mhp, hp);
            chr.setStat(Stat.mp, hp);
            chr.setStat(Stat.mmp, hp);
            chr.setStat(Stat.str, (short) num);
            chr.setStat(Stat.dex, (short) num);
            chr.setStat(Stat.inte, (short) num);
            chr.setStat(Stat.luk, (short) num);
            chr.setStat(Stat.level, (short) lv);
            Map<Stat, Object> stats = new HashMap<>();
            stats.put(Stat.hp, hp);
            stats.put(Stat.mhp, hp);
            stats.put(Stat.mp, hp);
            stats.put(Stat.mmp, hp);
            stats.put(Stat.str, (short) num);
            stats.put(Stat.dex, (short) num);
            stats.put(Stat.inte, (short) num);
            stats.put(Stat.luk, (short) num);
            stats.put(Stat.level, (byte) lv);
            stats.put(Stat.exp, (long) 0);
            chr.getClient().write(WvsContext.statChanged(stats));
        }
    }

    public static class HyperTP extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int hyperTP = 5040004;
            Item hyperTP2 = ItemData.getItemDeepCopy(hyperTP);
            chr.addItemToInventory(hyperTP2.getInvType(), hyperTP2, false);
            chr.getClient().write(WvsContext.inventoryOperation(true, false,
                    ADD, (short) hyperTP2.getBagIndex(), (byte) -1, 0, hyperTP2));
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

    public static class Str extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setStat(Stat.str, (short) num);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.str, (short) num);
                chr.getClient().write(WvsContext.statChanged(stats));
            }
        }
    }

    public static class Dex extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setStat(Stat.dex, (short) num);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.dex, (short) num);
                chr.getClient().write(WvsContext.statChanged(stats));
            }
        }
    }

    public static class Int extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setStat(Stat.inte, (short) num);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.inte, (short) num);
                chr.getClient().write(WvsContext.statChanged(stats));
            }
        }
    }

    public static class Luk extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setStat(Stat.luk, (short) num);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.luk, (short) num);
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

    public static class Morph extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int morphID = Integer.parseInt(args[1]);
            if (args.length < 2) {
                chr.chatMessage(GAME_NOTICE, "Needs more args! <id>");
            }
            Client c = chr.getClient();
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            Option o1 = new Option();
            o1.nOption = morphID;
            o1.rOption = Kaiser.FINAL_TRANCE;
            tsm.putCharacterStatValue(Morph, o1);
            c.write(WvsContext.temporaryStatSet(tsm));
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
            chr.getClient().write(MobPool.mobStatSet(mob, (short) 0));
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
                    true, mobs, 142110011, fais, null, 0, 0, null, 142110011, mob.getPosition()));

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
                    if (map.size() == 0) {
                        chr.chatMessage(YELLOW, "No skills found for query " + query);
                    }
                    for (Map.Entry<Integer, SkillStringInfo> entry : map.entrySet()) {
                        id = entry.getKey();
                        ssi = entry.getValue();
                        if (SkillData.getSkillInfoById(id) != null) {
                            chr.chatMessage(YELLOW, "Id: " + id);
                            chr.chatMessage(YELLOW, "Name: " + ssi.getName());
                            chr.chatMessage(YELLOW, "Desc: " + ssi.getDesc());
                            chr.chatMessage(YELLOW, "h: " + ssi.getH());
                        }
                    }
                }
            } else if ("item".equalsIgnoreCase(args[1])) {
                int id;
                String name;
                if (isNumber) {
                    id = Integer.parseInt(query);
                    name = StringData.getItemStringById(id);
                    if (name == null) {
                        chr.chatMessage(YELLOW, "Cannot find item " + id);
                        return;
                    }
                    chr.chatMessage(YELLOW, "Name: " + name);
                } else {
                    Map<Integer, String> map = StringData.getItemStringByName(query);
                    if (map.size() == 0) {
                        chr.chatMessage(YELLOW, "No items found for query " + query);
                    }
                    for (Map.Entry<Integer, String> entry : map.entrySet()) {
                        id = entry.getKey();
                        name = entry.getValue();
                        Item item = ItemData.getEquipDeepCopyFromId(id);
                        if (item == null) {
                            item = ItemData.getItemDeepCopy(id);
                        }
                        if (item == null) {
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
