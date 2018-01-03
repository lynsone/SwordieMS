package client.character.commands;

import client.character.Char;
import client.character.items.Equip;
import client.character.items.Inventory;
import client.field.Field;
import client.life.Life;
import client.life.Mob;
import connection.OutPacket;
import constants.JobConstants.JobEnum;
import enums.Stat;
import loaders.ItemData;
import loaders.MobData;
import packet.CField;
import packet.WvsContext;
import util.Position;
import util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 12/22/2017.
 */
public class AdminCommands {

    public static class PacketCommand extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            OutPacket outPacket = new OutPacket();

//            String[] fin = new String[args.length - 1];
//            System.arraycopy(args, 1, fin, 0, args.length - 1);
//            StringBuilder sb = new StringBuilder();
//            for(String s : fin) {
//                sb.append(s);
//            }
//            outPacket.encodeBytes(Util.getByteArrayByString(sb.toString()));
            outPacket.encodeShort(720);
            outPacket.encodeShort(Short.parseShort(args[1]));
            outPacket.encodeString("Ayyyyy");

            chr.getClient().write(outPacket);
        }
    }


    public static class SpawnMob extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int id = Integer.parseInt(args[1]);
            Mob mob = MobData.getMobDeepCopyById(id);
            Field field = chr.getField();
            Position pos = chr.getPosition();
            mob.setPosition(pos.deepCopy());
            mob.setPrevPos(pos.deepCopy());
            mob.setPosition(pos.deepCopy());
            mob.setNotRespawnable(true);
            field.spawnLife(mob, null);

            System.out.println("Mob has id " + mob.getObjectId());
        }
    }

    public static class Item extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int id = Integer.parseInt(args[1]);
            Equip item = ItemData.getEquipDeepCopyFromId(id);
            if(item == null) {

            } else {
                chr.addItemToInventory(Inventory.Type.EQUIP, item);
                chr.getClient().write(WvsContext.inventoryOperation(chr, false, false,
                        (byte) 0, (short) item.getBagIndex(), (byte) -1, item.getInvType(), (byte) 1,
                        0, item));
            }
        }
    }

    public static class Job extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            short id = Short.parseShort(args[1]);
            JobEnum job = JobEnum.getJobById(id);
            if(job != null) {
                chr.setJob(id);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.subJob, id);
                chr.getClient().write(WvsContext.statChanged(stats, false, (byte) -1, (byte) 0, (byte) 0, (byte) 0, false, 0, 0));
            }
        }
    }

    public static class Sp extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if(num >= 0) {
                chr.setSpToCurrentJob(num);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.sp, chr.getAvatarData().getCharacterStat().getExtendSP());
                chr.getClient().write(WvsContext.statChanged(stats, false, (byte) -1, (byte) 0, (byte) 0, (byte) 0, false, 0, 0));
            }
        }
    }

    public static class Ap extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if(num >= 0) {
                chr.setStat(Stat.ap, (short) num);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.ap, (short) num);
                chr.getClient().write(WvsContext.statChanged(stats, false));
            }
        }
    }

    public static class Hp extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if(num >= 0) {
                chr.setStat(Stat.hp, (short) num);
                chr.setStat(Stat.mhp, (short) num);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.hp, num);
                stats.put(Stat.mhp, num);
                chr.getClient().write(WvsContext.statChanged(stats, false));
            }
        }
    }

    public static class Mp extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if(num >= 0) {
                chr.setStat(Stat.mp, (short) num);
                chr.setStat(Stat.mhp, (short) num);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.mp, num);
                stats.put(Stat.mmp, num);
                chr.getClient().write(WvsContext.statChanged(stats, false));
            }
        }
    }

    public static class Level extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if(num >= 0) {
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.level, (byte) num);
                chr.getClient().write(WvsContext.statChanged(stats, false));
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
            chr.getClient().write(WvsContext.statChanged(stats, true));
        }
    }

    public static class TestTempStat extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            List<Life> lifes = chr.getField().getLifes();
            Life l = lifes.get(lifes.size() - 1);
            if(l == null || !(l instanceof Mob)) {
                return;
            }
            Mob mob = (Mob) l;
            chr.getClient().write(CField.mobStatSet(mob, (short) 0));
        }
    }
}
