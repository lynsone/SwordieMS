package client.character.commands;

import client.character.Char;
import client.character.items.Equip;
import client.character.items.Inventory;
import client.character.skills.ForceAtomInfo;
import client.character.skills.Skill;
import client.field.Field;
import client.field.Portal;
import client.life.Life;
import client.life.Mob;
import connection.OutPacket;
import constants.JobConstants.JobEnum;
import enums.Stat;
import handling.OutHeader;
import loaders.ItemData;
import loaders.MobData;
import loaders.SkillData;
import packet.CField;
import packet.Stage;
import packet.WvsContext;
import util.Position;

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

    public static class PacketCommand extends AdminCommand {

        public static void execute(Char chr, String[] args) {

//            String[] fin = new String[args.length - 1];
//            System.arraycopy(args, 1, fin, 0, args.length - 1);
//            StringBuilder sb = new StringBuilder();
//            for(String s : fin) {
//                sb.append(s);
//            }
//            outPacket.encodeBytes(Util.getByteArrayByString(sb.toString()));

//            Option option = new Option();
//            option.nOption = 0;
//            option.rOption = 37110009;
//            option.cOption = 2;
//            chr.getTemporaryStatManager().putCharacterStatValue(CharacterTemporaryStat.RWCombination, option);
//            chr.getClient().write(WvsContext.temporaryStatSet(chr.getTemporaryStatManager()));
            OutPacket outPacket = new OutPacket(OutHeader.EXPLOSION_ATTACK);

            int skillID = 37000008;
            Position pos = chr.getPosition();
            int mobID = chr.getField().getLifes().get(chr.getField().getLifes().size() - 1).getObjectId();
            int count = 1;

            chr.getClient().write(WvsContext.explosionAttack(skillID, pos, mobID, count));
        }
    }


    public static class Spawn extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int id = Integer.parseInt(args[1]);
            int count = 1;
            if(args.length > 2) {
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
                if(mob.getField() == null) {
                    mob.setField(field);
                }
                field.spawnLife(mob, null);

                System.out.println("Mob has id " + mob.getObjectId());
            }
        }
    }

    public static class Item extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int id = Integer.parseInt(args[1]);
            Equip item = ItemData.getEquipDeepCopyFromId(id);
            if(item == null) {

            } else {
                chr.addItemToInventory(Inventory.Type.EQUIP, item, false);
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
                chr.getClient().write(WvsContext.statChanged(stats, true, (byte) -1, (byte) 0, (byte) 0, (byte) 0, false, 0, 0));
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
                chr.getClient().write(WvsContext.statChanged(stats, true, (byte) -1, (byte) 0, (byte) 0, (byte) 0, false, 0, 0));
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
                chr.setStat(Stat.mmp, (short) num);
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
                chr.setStat(Stat.level, (short) num);
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
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, 1, 3, 3, 0, 0, (int) System.currentTimeMillis(), 1,
                    0, new Position());
            Mob mob = (Mob) chr.getField().getLifes().get( chr.getField().getLifes().size() - 1);
            int mobId = mob.getObjectId();
            chr.getClient().write(CField.createForceAtom(false, 0, mobId, 2, true, mobId, mobId, forceAtomInfo,
                    null, 0, 300, mob.getPosition(), 0, mob.getPosition()));

        }
    }

    public static class GetSkill extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if(args.length < 4) {
                chr.chatMessage(GAME_NOTICE, "Needs more args! <id> <cur> <max>");
                return;
            }
            int id = Integer.parseInt(args[1]);
            int cur = Integer.parseInt(args[2]);
            int max = Integer.parseInt(args[3]);
            Skill skill = chr.getSkill(Integer.parseInt(args[1]));
            if(skill == null) {
                skill = SkillData.getSkillDeepCopyById(Integer.parseInt(args[1]));
            }
            if(skill == null) {
                chr.chatMessage(YELLOW, "No such skill found.");
                return;
            }
            skill.setCurrentLevel(cur);
            skill.setMasterLevel(max);
            List<Skill> list = new ArrayList<>();
            list.add(skill);
            chr.addSkill(skill);
            chr.getClient().write(WvsContext.changeSkillRecordResult(list, false, false, false, false));
        }
    }
 }
