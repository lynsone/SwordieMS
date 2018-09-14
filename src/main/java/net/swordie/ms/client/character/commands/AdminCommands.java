package net.swordie.ms.client.character.commands;

import net.swordie.ms.Server;
import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.damage.DamageSkinSaveData;
import net.swordie.ms.client.character.damage.DamageSkinType;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.items.PetItem;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.StolenSkill;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.nova.Kaiser;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.JobConstants.JobEnum;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.npc.Npc;
import net.swordie.ms.loaders.*;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.Portal;
import org.apache.log4j.LogManager;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static net.swordie.ms.enums.ChatType.*;
import static net.swordie.ms.enums.InventoryOperation.ADD;

/**
 * Created on 12/22/2017.
 */
public class AdminCommands {
    static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    public static class Test extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            PetItem item = (PetItem) chr.getCashInventory().getItemBySlot((short) 1);
            item.setDateExpire(FileTime.fromDate(LocalDateTime.now().plusHours(3)));
            item.setDateDead(item.getDateExpire());
            item.updateToChar(chr);
            System.out.println(item.getDateExpire().toLocalDateTime().toInstant(ZoneOffset.UTC));
        }
    }

    public static class ShowInvInfo extends AdminCommand {

        public static void execute(Char chr, String[] args) {

            chr.chatMessage(Mob, "------------------------------------------------------------");
            for (InvType invType : InvType.values()) {
                chr.chatMessage(Mob, invType.toString());
                for (Item item : chr.getInventoryByType(invType).getItems()) {
                    item.setInvType(invType);
                    String name = StringData.getItemStringById(item.getItemId());
                    chr.chatMessage(Mob, String.format("%s, %d, %d, %d, %s", name, item.getItemId(), item.getId(),
                            item.getBagIndex(), item.getInvType().toString()));
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
            log.debug("[Out]\t| " + outPacket);

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
//            outPacket.encodeArr(new byte[Integer.parseInt(args[2])]);
//            outPacket.encodeInt(1);
//            outPacket.encodeInt(80001001);
//            outPacket.encodeByte(1);
//            outPacket.encodeByte(0);
//            outPacket.encodeArr(new byte[Integer.parseInt(args[1])]);
//            outPacket.encodeShort(1);
            // End TSTS encode
            // End  encodeForLocal
            outPacket.encodeInt(0); // indie?
            outPacket.encodeShort(0); // invalid value => "Failed to use the skill for an unknown reason"
            outPacket.encodeByte(0);
            outPacket.encodeByte(0);
            outPacket.encodeByte(0);
            outPacket.encodeByte(0); // movement enhancing
//            outPacket.encodeArr(new byte[Integer.parseInt(args[1])]);
            chr.write(outPacket);


        }
    }

    public static class CheckID extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            chr.chatMessage(SpeakerChannel, "your charID = " + chr.getId() + " \r\nYour accID = " + chr.getAccId());
        }
    }

    public static class GetPhantomStolenSkills extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            chr.getStolenSkills().stream().sorted(Comparator.comparing(StolenSkill::getPosition))
                    .forEach(ss ->
                            chr.chatMessage(GroupFriend, "[StolenSkills]  Skill ID: "+ ss.getSkillid() +" on Position: "+ ss.getPosition() +" with Current level: "+ ss.getCurrentlv()));
        }
    }

    public static class StealSkillList extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            Set<Skill> skillSet = new HashSet<>();

            //Warriors
            int[] skillIds = new int[] {
                    //Hero
                    1101006, //Rage
                    1101011, //Brandish
                    1101012, //Combo Fury
                    1101013, //Combo Attack

                    1111014, //Shout
                    1111012, //Rush
                    1111010, //Intrepid Slash
                    1111008, //Shout

                    1121008, //Raging Blow
                    1121016, //Magic Crash(Hero)

                    1121054, //Cry Valhalla

                    //Paladin
                    1201011, //Flame Charge
                    1201012, //Blizzard Charge
                    1201013, //Close Combat

                    1211013, //Threaten
                    1211014, //Parashock Guard
                    1211012, //Rush
                    1211011, //Combat order
                    1211010, //HP Recovery
                    1211008, //Lightning Charge

                    1221016, //Guardian
                    1221014, //Magic Crash(Paladin)
                    1221011, //Heaven's Hammer
                    1221009, //Blast

                    1221054, //Sacrosanctity

                    //Dark Knight
                    1301007, //Hyper body
                    1301006, //Iron will
                    1301012, //Spear Sweep
                    1301013, //Evil Eye

                    1311015, //Cross Surge
                    1311011, //La Mancha Spear,
                    1311012, //Rush

                    1321012, //Dark Impale
                    1321013, //Gungnir's Descent
                    1321014, //Magic Crash(Dark Knight)

                    1321054, //Dark Thirst



                    2001002, //Magic Guard
                    //Mage FP
                    2101010, //Ignite
                    2101005, //Poison breath
                    2101004, //Flame Orb
                    2101001, //Meditation(FP)

                    2111002, //Explosion
                    2111003, //Poison mist

                    2121011, //Flame Haze
                    2121007, //Meteor Shower
                    2121006, //Paralyze

                    2121054, //Inferno Aura

                    //MageIL
                    2201008, //Cold Beam
                    2201005, //Thunder Bolt
                    2201001, //Meditation(IL)

                    2211010, //Glacier Chain

                    2221007, //Blizzard
                    2221012, //Frozen Orb
                    2221006, //Chain Lightning

                    2221054, //Absolute Zero Aura

                    //Bishop
                    2301004, //Bless
                    2301005, //Holy Arrow
                    2301002, //Heal

                    2311001, //Dispel
                    2311003, //Holy Symbol
                    2311004, //Shining Ray
                    2311011, //Holy Fountain
                    2311009, //Holy Magic Shell

                    2321008, //Genesis
                    2321007, //Angel Ray
                    2321006, //Resurrection
                    2321005, //Adv Blessing

                    2321054, //Righteously Indignant



                    //Bowmaster
                    3101008, //Covering Fire
                    3101005, //Arrowbomb

                    3111011, //Reckless Hunt: Bow
                    3111010, //Hookshot
                    3111003, //Flame Surge
                    3111013, //Arrow Blaster

                    3121004, //Hurricane
                    3121015, //Arrow Stream
                    3121002, //Sharp Eyes
                    3121014, //Blinding Shot

                    3121054, //Concentration

                    //Marksman
                    3201008, //Net Toss

                    3211008, //Dragon Breath
                    3211009, //Explosive Bolt
                    3211010, //Hookshot
                    3211011, //Pain Killer
                    3211012, //Reckless Hunt: XBow

                    3221007, //Snipe
                    3221006, //Illusion Step
                    3221002, //Sharp Eyes
                    3221001, //Piercing Arrow

                    3221054, //BullsEye Shot



                    4001003, //Dark Sight
                    4001005, //Haste
                    //Night Lord
                    4101011, //Sin Mark
                    4101010, //Gust Charm
                    4101008, //Shuriken Burst

                    4111013, //Shade Splitter
                    4111015, //Shade Splitter
                    4111010, //Triple Throw
                    4111003, //Shadow Web

                    4121017, //Showdown
                    4121016, //Sudden Raid (NL)
                    4121015, //Frailty Curse
                    4121013, //Quad Star

                    4121054, //Bleed Dart

                    //Shadower
                    4201012, //Svg Blow
                    4201011, //Meso Guard
                    4201004, //Steal

                    4211011, //Midnight Carnival
                    4211006, //Meso Explosion
                    4211002, //Phase Dash

                    4221014, //Assassinate
                    4221010, //Sudden Raid(Shad)
                    4221007, //Bstep
                    4221006, //Smoke screen

                    4221054, //Flip of the Coin

                    //Dual Blade
                    4301003, //Self Haste

                    4311003, //Slash Storm
                    4311002, //Fatal Blow

                    4321006, //Flying Assaulter
                    4321004, //Upper Stab
                    4321002, //FlashBang

                    4331011, //Blade Ascension
                    4331006, //Chains of Hell

                    4341011, //Sudden Raid (DB)
                    4341009, //Phantom Blow
                    4341004, //Blade Fury
                    4341002, //Final Cut

                    4341054, //Blade Clone



                    5001005, //Dash
                    //Bucc
                    5101004, //Corkscrew Blow

                    5111007, //Roll of the Dice
                    5111006, //Shock wave
                    5111009, //Spiral Assault
                    5111015, //Static Thumper
                    5111012, //Static Thumper

                    5121013, //Nautilus Strike
                    5121010, //Time Leap
                    5121009, //Speed Infusion
                    5121020, //octopunch
                    5121015, //Crossbones

                    5121054, //Stimulating Conversation

                    //Corsair
                    5201012, //Scurvy Summons
                    5201011, //Wings
                    5201006, //Recoil Shot
                    5201001, //Rapid blast

                    5211007, //Roll of the Dice
                    5211011, //All Aboard
                    5211009, //Cross cut Blast
                    5211010, //Blackboot bill
                    5211014, //Octo Cannon

                    5221018, //Jolly Roger
                    5221015, //Parrotargetting
                    5221016, //Brain scrambler
                    5221013, //Nautilus Strike
                    5221017, //Eigh-legs Easton
                    5221022, //Broadside

                    5221054, //Whaler's potion

                    //Cannon Master
                    5011001, //Cannon Strike

                    5301003, //Monkey Magic
                    5301001, //Barrel Bomb
                    5301000, //Scatter Shot

                    5311004, //Barrel Roulette
                    5311003, //Cannon Jump
                    5311005, //Luck of the Die
                    5311010, //Monkey Fury
                    5311002, //Monkey Wave
                    5311000, //Cannon Spike

                    5321012, //Cannon Barrage
                    5321010, //Pirate Spirit
                    5321004, //Monkey Militia
                    5321003, //Anchor Aweigh
                    5321001, //Nautilus Strike
                    5321000, //Cannon Bazooka

                    5321054, //BuckShot
            };

            for(int skillId : skillIds) {
                Skill skill = SkillData.getSkillDeepCopyById(skillId);
                if(skill == null) {
                    continue;
                }
                skillSet.add(skill);
            }

            chr.write(UserLocal.resultStealSkillList(skillSet, 4, 1, 2412));
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
            chr.chatMessage(Normal, "~~~~~~~~~~");
            chr.chatMessage(SpeakerChannel, "Current Map: " + NumberFormat.getNumberInstance(Locale.US).format(chr.getFieldID()));
            chr.chatMessage(SpeakerChannel, "Current ReturnMap: " + NumberFormat.getNumberInstance(Locale.US).format(chr.getField().getReturnMap()));
            chr.chatMessage(SpeakerChannel, "");
            for (Portal portal : chr.getField().getClosestPortal(rect)) {
                chr.chatMessage(SpeakerChannel, "Portal Name: " + portal.getName());
                chr.chatMessage(SpeakerChannel, "Portal ID: " + NumberFormat.getNumberInstance(Locale.US).format(portal.getId()));
                chr.chatMessage(SpeakerChannel, "Portal target map: " + NumberFormat.getNumberInstance(Locale.US).format(portal.getTargetMapId()));
                chr.chatMessage(SpeakerChannel, "Portal script: " + portal.getScript());
                chr.chatMessage(SpeakerChannel, ".");
            }
            chr.chatMessage(Normal, "~~~~~~~~~~");
        }
    }

    public static class Stats extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int strength = chr.getStat(Stat.str);
            int dexterity = chr.getStat(Stat.dex);
            int intellect = chr.getStat(Stat.inte);
            int luck = chr.getStat(Stat.luk);
            int hp = chr.getStat(Stat.hp);
            int mhp = chr.getStat(Stat.mhp);
            int mp = chr.getStat(Stat.mp);
            int mmp = chr.getStat(Stat.mmp);
            double hpratio = (((double) hp) / mhp) * 100;
            double mpratio = (((double) mp) / mmp) * 100;
            DecimalFormat formatNumbers = new DecimalFormat("##.00");
            NumberFormat addDeci = NumberFormat.getNumberInstance(Locale.US);
            chr.chatMessage(Notice2, "STR: " + addDeci.format(strength) + "  DEX: " + addDeci.format(dexterity) + "  INT: " + addDeci.format(intellect) + "  LUK: " + addDeci.format(luck));
            chr.chatMessage(Notice2, "HP: " + addDeci.format(hp) + " / " + addDeci.format(mhp) + " (" + formatNumbers.format(hpratio) + "%)   MP: " + addDeci.format(mp) + " / " + addDeci.format(mmp) + " (" + formatNumbers.format(mpratio) + "%)");
        }
    }

    public static class Spawn extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int id = Integer.parseInt(args[1]);
            int count = 1;
            if (args.length > 2) {
                count = Integer.parseInt(args[2]);
            }
            int hp = 0;
            if (args.length > 3) {
                hp = Integer.parseInt(args[3]);
            }
            for (int i = 0; i < count; i++) {
                Mob mob = MobData.getMobDeepCopyById(id);
                if (mob == null) {
                    chr.chatMessage("Could not find a mob with that ID.");
                    return;
                }
                Field field = chr.getField();
                Position pos = chr.getPosition();
                mob.setPosition(pos.deepCopy());
                mob.setPrevPos(pos.deepCopy());
                mob.setPosition(pos.deepCopy());
                mob.getForcedMobStat().setMaxMP(Integer.MAX_VALUE);
                if (hp > 0) {
                    mob.setMaxHp(hp);
                    mob.setHp(hp);
                }
                mob.setNotRespawnable(true);
                if (mob.getField() == null) {
                    mob.setField(field);
                }
                field.spawnLife(mob, null);
            }
        }
    }

    public static class NPC extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int id = Integer.parseInt(args[1]);
            Npc npc = NpcData.getNpcDeepCopyById(id);
            if (npc == null) {
                chr.chatMessage("Could not find an npc with that ID.");
                return;
            }
            Field field = chr.getField();
            Position pos = chr.getPosition();
            npc.setPosition(pos.deepCopy());
            npc.setCy(chr.getPosition().getY());
            npc.setRx0(chr.getPosition().getX() + 50);
            npc.setRx1(chr.getPosition().getX() - 50);
            npc.setFh(chr.getFoothold());
            npc.setNotRespawnable(true);
            if (npc.getField() == null) {
                npc.setField(field);
            }
            field.spawnLife(npc, null);
            log.debug("npc has id " + npc.getObjectId());
        }
    }

    public static class TestDrop extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int id = Integer.parseInt(args[1]);
            int count = 1;
            if (args.length > 2) {
                count = Integer.parseInt(args[2]);
            }
            for (int i = 0; i < count; i++) {
                Mob mob = MobData.getMobDeepCopyById(id);
                if (mob == null) {
                    chr.chatMessage("Could not find a mob with that ID.");
                    return;
                }
                Field field = chr.getField();
                Position pos = chr.getPosition();
                mob.setPosition(pos.deepCopy());
                mob.setPrevPos(pos.deepCopy());
                mob.setPosition(pos.deepCopy());
                mob.getForcedMobStat().setMaxMP(3);
                mob.setMaxHp(3);
                mob.setHp(3);
                mob.setNotRespawnable(true);
                if (mob.getField() == null) {
                    mob.setField(field);
                }
                field.spawnLife(mob, null);

                log.debug("Mob has id " + mob.getObjectId());
            }
        }
    }

    public static class ProItem extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if (args.length < 5) {
                chr.chatMessage(Notice2, "Needs more args! <id> <Stat> <Attack> <Flame stats>");
                return;
            }
            int id = Integer.parseInt(args[1]);
            int stat = Integer.parseInt(args[2]);
            int atk = Integer.parseInt(args[3]);
            int flames = Integer.parseInt(args[4]);
            Equip equip = ItemData.getEquipDeepCopyFromID(id, false);
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
                Equip equip = ItemData.getEquipDeepCopyFromID(id, true);
                if (equip == null) {
                    Item item = ItemData.getItemDeepCopy(id, true);
                    if (item == null) {
                        chr.chatMessage(Mob, String.format("Could not find an item with id %d", id));
                        return;
                    }
                    short quant = 1;
                    if (args.length > 2) {
                        quant = Short.parseShort(args[2]);
                    }
                    item.setQuantity(quant);
                    chr.addItemToInventory(item);
                } else {
                    chr.addItemToInventory(InvType.EQUIP, equip, false);
                }
            } else {
                StringBuilder query = new StringBuilder();
                int size = args.length;
                short quant = 1;
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
                    chr.chatMessage(Mob, "No items found for query " + query);
                }
                for (Map.Entry<Integer, String> entry : map.entrySet()) {
                    int id = entry.getKey();
                    Item item = ItemData.getEquipDeepCopyFromID(id, true);
                    if (item != null) {
                        Equip equip = (Equip) item;
                        if (equip.getItemId() < 1000000) {
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
            int[] projectiles = new int[]{
                    2070000,
                    2060000,
                    2061000,
                    2330000
            };
            for (int projectile : projectiles) {
                Item item = ItemData.getItemDeepCopy(projectile);
                chr.addItemToInventory(item.getInvType(), item, false);
                item.setQuantity(1000);
                chr.getClient().write(WvsContext.inventoryOperation(true, false,
                        ADD, (short) item.getBagIndex(), (byte) -1, 0, item));
            }
        }
    }

    public static class Done extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int[] projectiles = new int[]{
                    2070000,
                    2060000,
                    2061000,
                    2330000
            };
            for (int projectile : projectiles) {
                Item item = ItemData.getItemDeepCopy(projectile);
                chr.addItemToInventory(item.getInvType(), item, false);
                item.setQuantity(1000);
                chr.getClient().write(WvsContext.inventoryOperation(true, false,
                        ADD, (short) item.getBagIndex(), (byte) -1, 0, item));
            }

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

    public static class SetInt extends AdminCommand {
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
                chr.getJobHandler().handleLevelUp();
                chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.levelUpEffect()));
            }
        }
    }
    public static class LevelUntil extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            short level = chr.getLevel();
            while (level < num) {
                level++;
                chr.setStat(Stat.level, level);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.level, (byte) level);
                stats.put(Stat.exp, (long) 0);
                chr.getClient().write(WvsContext.statChanged(stats));
                chr.getJobHandler().handleLevelUp();
                chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.levelUpEffect()));
            }
        }
    }

    public static class Heal extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int maxHp = chr.getTotalStat(BaseStat.mhp);
            int maxMp = chr.getTotalStat(BaseStat.mmp);
            chr.getAvatarData().getCharacterStat().setHp(maxHp);
            chr.getAvatarData().getCharacterStat().setMp(maxMp);
            Map<Stat, Object> stats = new HashMap<>();
            stats.put(Stat.hp, maxHp);
            stats.put(Stat.mp, maxMp);
            chr.getClient().write(WvsContext.statChanged(stats));
        }
    }

    public static class CurHp extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setStat(Stat.hp, num);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.hp, num);
                chr.getClient().write(WvsContext.statChanged(stats));
            }
        }
    }

    public static class CurMp extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setStat(Stat.mp, num);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.mp, num);
                chr.getClient().write(WvsContext.statChanged(stats));
            }
        }
    }

    public static class Invincible extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            Client c = chr.getClient();
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            Option o1 = new Option();
            if (tsm.getOption(CharacterTemporaryStat.NotDamaged).nOption == 3) {
                tsm.removeStat(CharacterTemporaryStat.NotDamaged, false);
                chr.chatMessage(Notice2, "You are no longer invincible.");
            } else {
                o1.nOption = 3;
                tsm.putCharacterStatValue(CharacterTemporaryStat.NotDamaged, o1);
                tsm.sendSetStatPacket();
                chr.chatMessage(Notice2, "You are invincible.");
            }
        }
    }

    public static class Morph extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int morphID = Integer.parseInt(args[1]);
            if (args.length < 2) {
                chr.chatMessage(Notice2, "Needs more args! <id>");
            }
            Client c = chr.getClient();
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            Option o1 = new Option();
            o1.nOption = morphID;
            o1.rOption = Kaiser.FINAL_TRANCE;
            tsm.putCharacterStatValue(CharacterTemporaryStat.Morph, o1);
            tsm.sendSetStatPacket();
        }
    }

    public static class TestTempStat extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            List<Life> lifes = new ArrayList<>(chr.getField().getLifes().values());
            Life l = lifes.get(lifes.size() - 1);
            if (!(l instanceof Mob)) {
                return;
            }
            Mob mob = (Mob) l;
            chr.getClient().write(MobPool.statSet(mob, (short) 0));
        }
    }

    public static class SetMap extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if (args.length > 1 && Util.isNumber(args[1])) {
                Field toField = chr.getOrCreateFieldByCurrentInstanceType(Integer.parseInt(args[1]));
                if (toField != null) {
                    chr.warp(toField);
                } else {
                    chr.chatMessage(Notice2, "Could not find a field with id " + args[1]);
                }
            } else {
                chr.chatMessage("Please input a number as first argument.");
            }
        }
    }

    public static class SetPortal extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int portalID = Integer.parseInt(args[1]);
            Portal portal = chr.getField().getPortalByID(portalID);
            if (portal == null) {
                chr.chatMessage(Notice2, "Portal does not exist.");
                return;
            }
            Position position = new Position(portal.getX(), portal.getY());
            chr.write(CField.teleport(position, chr));
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
            chr.getField().broadcastPacket(CField.createForceAtom(false, -1, chr.getId(), ForceAtomEnum.KINESIS_ORB_REAL.getForceAtomType(),
                    true, mobs, 142110011, fais, null, 0, 0, null, 142110011, mob.getPosition()));

        }
    }

    public static class GetSkill extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if (args.length < 4) {
                chr.chatMessage(Notice2, "Needs more args! <id> <cur> <max>");
                return;
            }
            int id = Integer.parseInt(args[1]);
            int cur = Integer.parseInt(args[2]);
            int max = Integer.parseInt(args[3]);
            chr.addSkill(id, cur, max);
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
                chr.chatMessage(Notice2, "Needs more args! <what to lookup> <id/(part of) name>");
                chr.chatMessage(Notice2, "Possible lookup types are: item, skill, mob, npc, map");
                return;
            }
            StringBuilder query = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
                query.append(args[i].toLowerCase()).append(" ");
            }
            query = new StringBuilder(query.substring(0, query.length() - 1));
            chr.chatMessage("Query: " + query);
            boolean isNumber = Util.isNumber(query.toString());
            if ("skill".equalsIgnoreCase(args[1])) {
                SkillStringInfo ssi;
                int id;
                if (isNumber) {
                    id = Integer.parseInt(query.toString());
                    ssi = StringData.getSkillStringById(id);
                    if (ssi == null) {
                        chr.chatMessage(Mob, "Cannot find skill " + id);
                        return;
                    }
                    SkillInfo skillInfo = SkillData.getSkillInfoById(id);
                    chr.chatMessage(Mob, "Name: " + ssi.getName());
                    chr.chatMessage(Mob, "Desc: " + ssi.getDesc());
                    chr.chatMessage(Mob, "h: " + ssi.getH());
                    chr.chatMessage(Mob, "type: " + skillInfo.getType());
                } else {
                    Map<Integer, SkillStringInfo> map = StringData.getSkillStringByName(query.toString());
                    if (map.size() == 0) {
                        chr.chatMessage(Mob, "No skills found for query " + query);
                    }
                    for (Map.Entry<Integer, SkillStringInfo> entry : map.entrySet()) {
                        id = entry.getKey();
                        ssi = entry.getValue();
                        SkillInfo si = SkillData.getSkillInfoById(id);
                        if (si != null) {
                            chr.chatMessage(Mob, "Id: " + id);
                            chr.chatMessage(Mob, "Name: " + ssi.getName());
                            chr.chatMessage(Mob, "Desc: " + ssi.getDesc());
                            chr.chatMessage(Mob, "h: " + ssi.getH());
                            chr.chatMessage(Mob, "type: " + si.getType());
                        }
                    }
                }
            } else {
                String queryType = args[1].toLowerCase();
                int id;
                String name;
                if (isNumber) {
                    id = Integer.parseInt(query.toString());
                    switch (queryType) {
                        case "item":
                            name = StringData.getItemStringById(id);
                            break;
                        case "mob":
                            name = StringData.getMobStringById(id);
                            break;
                        case "npc":
                            name = StringData.getNpcStringById(id);
                            break;
                        case "map":
                            name = StringData.getMapStringById(id);
                            break;
                        default:
                            chr.chatMessage("Unknown query type " + queryType);
                            return;
                    }
                    if (name == null) {
                        chr.chatMessage(Mob, "Cannot find " + queryType + " " + id);
                        return;
                    }
                    chr.chatMessage(Mob, "Name: " + name);
                } else {
                    Map<Integer, String> map;
                    switch (queryType) {
                        case "item":
                            map = StringData.getItemStringByName(query.toString());
                            break;
                        case "mob":
                            map = StringData.getMobStringByName(query.toString());
                            break;
                        case "npc":
                            map = StringData.getNpcStringByName(query.toString());
                            break;
                        case "map":
                            map = StringData.getMapStringByName(query.toString());
                            break;
                        default:
                            chr.chatMessage("Unknown query type " + queryType);
                            return;
                    }
                    if (map.size() == 0) {
                        chr.chatMessage(Mob, "No " + queryType + "s found for query " + query);
                    }
                    for (Map.Entry<Integer, String> entry : map.entrySet()) {
                        id = entry.getKey();
                        name = entry.getValue();
                        if (queryType.equalsIgnoreCase("item")) {
                            Item item = ItemData.getEquipDeepCopyFromID(id, false);
                            if (item == null) {
                                item = ItemData.getItemDeepCopy(id);
                            }
                            if (item == null) {
                                continue;
                            }
                        }
                        chr.chatMessage(Mob, "Id: " + id);
                        chr.chatMessage(Mob, "Name: " + name);
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

    public static class GoTo extends AdminCommand {
        public static void execute(Char chr, String[] args) {

            HashMap<String, Integer> gotomaps = new HashMap<>();
            gotomaps.put("ardent", 910001000);
            gotomaps.put("ariant", 260000100);
            gotomaps.put("amherst", 1010000);
            gotomaps.put("amoria", 680000000);
            gotomaps.put("aqua", 860000000);
            gotomaps.put("aquaroad", 230000000);
            gotomaps.put("boatquay", 541000000);
            gotomaps.put("cwk", 610030000);
            gotomaps.put("edelstein", 310000000);
            gotomaps.put("ellin", 300000000);
            gotomaps.put("ellinia", 101000000);
            gotomaps.put("ellinel", 101071300);
            gotomaps.put("elluel", 101050000);
            gotomaps.put("elnath", 211000000);
            gotomaps.put("ereve", 130000000);
            gotomaps.put("florina", 120000300);
            gotomaps.put("fm", 910000000);
            gotomaps.put("future", 271000000);
            gotomaps.put("gmmap", 180000000);
            gotomaps.put("happy", 209000000);
            gotomaps.put("harbor", 104000000);
            gotomaps.put("henesys", 100000000);
            gotomaps.put("herbtown", 251000000);
            gotomaps.put("kampung", 551000000);
            gotomaps.put("kerning", 103000000);
            gotomaps.put("korean", 222000000);
            gotomaps.put("leafre", 240000000);
            gotomaps.put("ludi", 220000000);
            gotomaps.put("malaysia", 550000000);
            gotomaps.put("mulung", 250000000);
            gotomaps.put("nautilus", 120000000);
            gotomaps.put("nlc", 600000000);
            gotomaps.put("omega", 221000000);
            gotomaps.put("orbis", 200000000);
            gotomaps.put("pantheon", 400000000);
            gotomaps.put("pinkbean", 270050100);
            gotomaps.put("phantom", 610010000);
            gotomaps.put("perion", 102000000);
            gotomaps.put("rien", 140000000);
            gotomaps.put("showatown", 801000000);
            gotomaps.put("singapore", 540000000);
            gotomaps.put("sixpath", 104020000);
            gotomaps.put("sleepywood", 105000000);
            gotomaps.put("southperry", 2000000);
            gotomaps.put("tot", 270000000);
            gotomaps.put("twilight", 273000000);
            gotomaps.put("tynerum", 301000000);
            gotomaps.put("zipangu", 800000000);
            gotomaps.put("pianus", 230040420);
            gotomaps.put("horntail", 240060200);
            gotomaps.put("chorntail", 240060201);
            gotomaps.put("griffey", 240020101);
            gotomaps.put("manon", 240020401);
            gotomaps.put("zakum", 280030000);
            gotomaps.put("czakum", 280030001);
            gotomaps.put("pap", 220080001);
            gotomaps.put("oxquiz", 109020001);
            gotomaps.put("ola", 109030101);
            gotomaps.put("fitness", 109040000);
            gotomaps.put("snowball", 109060000);
            gotomaps.put("boss", 682020000);
            gotomaps.put("dojo", 925020001);
            gotomaps.put("pq", 910002000);
            gotomaps.put("h", 100000000);
            gotomaps.put("gollux", 863010000);
            gotomaps.put("lotus", 350060300);
            gotomaps.put("damien", 105300303);
            gotomaps.put("ursus", 970072200);
            gotomaps.put("pno", 811000008);
            gotomaps.put("cygnus", 271040000);
            gotomaps.put("ra", 105200000);
            gotomaps.put("goldenbeach", 914200000);
            gotomaps.put("ardentmill", 910001000);
            gotomaps.put("oz", 992000000);

            if (args.length == 1) {
                chr.chatMessage(Notice2, "List of locations: " + gotomaps.keySet());
            } else if (gotomaps.containsKey(args[1])) {
                Field toField = chr.getClient().getChannelInstance().getField(gotomaps.get(args[1]));
                Portal portal = chr.getField().getDefaultPortal();
                chr.warp(toField, portal);
            } else if (args[1].equals("locations")) {
                chr.chatMessage(Notice2, "Use !goto <location>");
                StringBuilder sb = new StringBuilder();
                for (String s : gotomaps.keySet()) {
                    sb.append(s).append(",  ");
                }
                chr.chatMessage(Notice2, sb.substring(0, sb.length()));
            } else {
                chr.chatMessage(Notice2, "Map does not exist.");
            }
        }
    }

    public static class ClearCache extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            chr.getScriptManager().dispose(false);
            Server.getInstance().clearCache();
            chr.chatMessage("Cache has been cleared.");
        }
    }

    public static class SaveMap extends AdminCommand {
        private static HashMap<String, Integer> quickmaps = new HashMap<>();

        public static void execute(Char chr, String[] args) {
            int mapid = chr.getFieldID();
            if (args.length < 1 && !args[1].equalsIgnoreCase("list")) {
                chr.chatMessage(BlackOnWhite, "Incorrect Syntax: !SaveMap <save/go> <key>");
                chr.chatMessage(BlackOnWhite, "To see the list of saved maps, use: !SaveMap list");
            }
            if (args[1].equalsIgnoreCase("save")) {
                String key = args[2];
                quickmaps.put(key, mapid);
                chr.chatMessage(BlackOnWhite, "[SaveMap] Map: " + mapid + " has been saved as key '" + key + "'.");
            } else if (args[1].equalsIgnoreCase("go")) {
                String key = args[2];
                if (quickmaps.get(key) == null) {
                    chr.chatMessage(BlackOnWhite, "[SaveMap] There is no map saved as key '" + args[2] + "'.");
                    return;
                }
                Field toField = chr.getOrCreateFieldByCurrentInstanceType((quickmaps.get(key)));
                Portal portal = chr.getField().getDefaultPortal();
                chr.warp(toField, portal);
            } else if (args[1].equalsIgnoreCase("list")) {
                Set keys = quickmaps.keySet();
                chr.chatMessage(BlackOnWhite, "[SaveMap] " + quickmaps.size() + " saved maps.");
                for (Object maps : keys) {
                    chr.chatMessage(BlackOnWhite, "[SaveMap] Stored map: " + quickmaps.get(maps) + " as '" + maps + "'.");
                }
            } else {
                chr.chatMessage(BlackOnWhite, "Incorrect Syntax: !SaveMap <save/go> <key>");
                chr.chatMessage(BlackOnWhite, "To see the list of saved maps, use: !SaveMap list");
            }
        }
    }

    public static class WarriorEquips extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int[] warEquips = new int[]{
                    1302000,
                    1312000,
                    1322000,
                    1402000,
                    1412000,
                    1422000,
                    1432000,
                    1442000,
                    1542000,
                    1232000,
                    1582000,
                    1353400,
                    1352500,
            };
            for (int warEquip : warEquips) {
                Item item = ItemData.getItemDeepCopy(warEquip);
                chr.addItemToInventory(item.getInvType(), item, false);
                chr.getClient().write(WvsContext.inventoryOperation(true, false,
                        ADD, (short) item.getBagIndex(), (byte) -1, 0, item));
            }
        }
    }

    public static class MageEquips extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int[] mageEquips = new int[]{
                    1382000,
                    1372000,
                    1552000,
                    1252000,
                    1262000,
                    1353200,
            };
            for (int mageEquip : mageEquips) {
                Item item = ItemData.getItemDeepCopy(mageEquip);
                chr.addItemToInventory(item.getInvType(), item, false);
                chr.getClient().write(WvsContext.inventoryOperation(true, false,
                        ADD, (short) item.getBagIndex(), (byte) -1, 0, item));
            }
        }
    }

    public static class ArcherEquips extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int[] archerEquips = new int[]{
                    1452000,
                    1462000,
                    1522000,
                    1352004,
            };
            for (int archerEquip : archerEquips) {
                Item item = ItemData.getItemDeepCopy(archerEquip);
                chr.addItemToInventory(item.getInvType(), item, false);
                chr.getClient().write(WvsContext.inventoryOperation(true, false,
                        ADD, (short) item.getBagIndex(), (byte) -1, 0, item));
            }
        }
    }

    public static class ThiefEquips extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int[] thiefEquips = new int[]{
                    1472000,
                    1332000,
                    1342000,
                    1242000,
                    1362000,
                    1352100
            };
            for (int thiefEquip : thiefEquips) {
                Item item = ItemData.getItemDeepCopy(thiefEquip);
                chr.addItemToInventory(item.getInvType(), item, false);
                chr.getClient().write(WvsContext.inventoryOperation(true, false,
                        ADD, (short) item.getBagIndex(), (byte) -1, 0, item));
            }
        }
    }

    public static class PirateEquips extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int[] pirateEquips = new int[]{
                    1482000,
                    1353100,
                    1492000,
                    1222000,
                    1352600,
                    1532000,
                    1242000,
            };
            for (int pirateEquip : pirateEquips) {
                Item item = ItemData.getItemDeepCopy(pirateEquip);
                chr.addItemToInventory(item.getInvType(), item, false);
                chr.getClient().write(WvsContext.inventoryOperation(true, false,
                        ADD, (short) item.getBagIndex(), (byte) -1, 0, item));
            }
        }
    }

    public static class ClearInv extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            short startIndex = Short.parseShort(args[1]);
            short endIndex = Short.parseShort(args[2]);
            if (args.length < 2) {
                chr.chatMessage(Notice2, "Syntax Error: !ClearInv [Start Index] [End Index]");
                return;
            }
            for (int i = startIndex; i < endIndex; i++) {
                Item removeItem = chr.getInventoryByType(InvType.EQUIP).getItemBySlot((short) i);
                chr.getInventoryByType(InvType.EQUIP).removeItem(removeItem);
                chr.dispose();
            }
            chr.chatMessage(Notice2, "Please change channel, as this Command is still shit right now. ");
        }
    }

    public static class MobInfo extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            Rect rect = new Rect(
                    chr.getPosition().deepCopy().getX() - 100,
                    chr.getPosition().deepCopy().getY() - 100,
                    chr.getPosition().deepCopy().getX() + 100,
                    chr.getPosition().deepCopy().getY() + 100
            );
            Mob mob = chr.getField().getMobs().stream().filter(m -> rect.hasPositionInside(m.getPosition())).findFirst().orElse(null);
            Char controller = chr.getField().getLifeToControllers().getOrDefault(mob, null);
            if (mob != null) {
                chr.chatMessage(SpeakerChannel, String.format("Mob ID: %s | Template ID: %s | HP: %s/%s | MP: %s/%s | Left: %s | Controller: %s",
                        NumberFormat.getNumberInstance(Locale.US).format(mob.getObjectId()),
                        NumberFormat.getNumberInstance(Locale.US).format(mob.getTemplateId()),
                        NumberFormat.getNumberInstance(Locale.US).format(mob.getHp()),
                        NumberFormat.getNumberInstance(Locale.US).format(mob.getMaxHp()),
                        NumberFormat.getNumberInstance(Locale.US).format(mob.getMp()),
                        NumberFormat.getNumberInstance(Locale.US).format(mob.getMaxMp()),
                        mob.isLeft(),
                        controller == null ? "null" : chr.getName()
                        )
                );
            } else {
                chr.chatMessage(SpeakerChannel, "Could not find mob.");
            }
        }
    }

    public static class CompleteQuest extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            chr.getQuestManager().completeQuest(Integer.parseInt(args[1]));
        }
    }

    public static class RemoveQuest extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            chr.getQuestManager().removeQuest(Integer.parseInt(args[1]));
        }
    }

    public static class SetHonor extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 2) {
                chr.chatMessage(SpeakerChannel, "Format: !sethonor <honor exp>");
                return;
            }
            int honor = Integer.parseInt(args[1]);
            chr.setHonorExp(honor);
            chr.write(WvsContext.characterHonorExp(honor));
        }
    }

    public static class StartQuest extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 2) {
                chr.chatMessage(SpeakerChannel, "Format: !startquest <quest id>");
                return;
            }
            Quest q = QuestData.createQuestFromId(Integer.parseInt(args[1]));
            if (q != null) {
                chr.getQuestManager().addQuest(q);
            } else {
                chr.chatMessage("Could not find quest with id " + args[1]);
            }
        }
    }

    public static class BypassSkillCD extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (!chr.hasSkillCDBypass()) {
                chr.setSkillCDBypass(true);
                chr.chatMessage(Notice2, "Skill Cooldown bypass: ON");
            } else {
                chr.setSkillCDBypass(false);
                chr.chatMessage(Notice2, "Skill Cooldown bypass: OFF");
            }
            chr.dispose();
        }
    }

    public static class ToggleDamageCap extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            boolean on = tsm.hasStat(CharacterTemporaryStat.IndieMaxDamageOver) && tsm.getOptByCTSAndSkill(CharacterTemporaryStat.IndieMaxDamageOver, 0).nValue != 0;
            Option o = new Option();
            o.nValue = on ? 0 : 1950000000;
            tsm.putCharacterStatValue(CharacterTemporaryStat.IndieMaxDamageOver, o);
            tsm.sendSetStatPacket();
            chr.chatMessage("Damage cap removed: " + (o.nValue != 0));
        }
    }

    public static class Shop extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            chr.getScriptManager().openShop(1011100);
        }
    }

    public static class ReloadCS extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            Server.getInstance().initCashShop();
        }
    }

    public static class Ban extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 5) {
                chr.chatMessage(SpeakerChannel, "Not enough args! Use !ban <name> <amount> <min/hour/day/year> <reason>");
            }
            String name = args[1];
            int amount = Integer.parseInt(args[2]);
            String amountType = args[3].toLowerCase();
            StringBuilder builder = new StringBuilder();
            for (int i = 4; i < args.length; i++) {
                builder.append(args[i] + " ");
            }
            String reason = builder.toString();
            reason = reason.substring(0, reason.length() - 1); // gets rid of the last space
            if (reason.length() > 255) {
                chr.chatMessage(SpeakerChannel, "That ban reason is too long.");
                return;
            }
            Char banChr = Server.getInstance().getWorldById(chr.getClient().getWorldId()).getCharByName(name);
            boolean online = true;
            if (banChr == null) {
                online = false;
                banChr = Char.getFromDBByName(name);
                if (banChr == null) {
                    chr.chatMessage(SpeakerChannel, "Could not find that character.");
                    return;
                }
            }
            Account banAccount = banChr.getAccount();
            LocalDateTime banDate = LocalDateTime.now();
            switch (amountType) {
                case "m":
                case "min":
                case "mins":
                    banDate = banDate.plusMinutes(amount);
                    break;
                case "h":
                case "hour":
                case "hours":
                    banDate = banDate.plusHours(amount);
                    break;
                case "d":
                case "day":
                case "days":
                    banDate = banDate.plusDays(amount);
                    break;
                case "y":
                case "year":
                case "years":
                    banDate = banDate.plusYears(amount);
                    break;
                default:
                    chr.chatMessage(SpeakerChannel, String.format("Unknown date type %s", amountType));
                    break;
            }
            banAccount.setBanExpireDate(FileTime.fromDate(banDate));
            banAccount.setBanReason(reason);
            chr.chatMessage(SpeakerChannel, String.format("Character %s has been banned. Expire date: %s", name, banDate));
            if (online) {
                banChr.write(WvsContext.returnToTitle());
            }
        }
    }

    public static class KillMobs extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            List<Mob> mobs = new ArrayList<>(chr.getField().getMobs());
            for (Mob mob : mobs) {
                mob.die();
            }
        }
    }

    public static class FP extends AdminCommand { // FindPortal
        public static void execute(Char chr, String[] args) {
            if (args.length < 1) {
                chr.chatMessage(SpeakerChannel, "Invalid args. Use !findportal <id/name>");
            }
            Field field = chr.getField();
            Portal portal;
            String query = args[1];
            if (Util.isNumber(query)) {
                portal = field.getPortalByID(Integer.parseInt(query));
            } else {
                portal = field.getPortalByName(query);
            }
            if (portal == null) {
                chr.chatMessage(SpeakerChannel, "Was not able to find portal " + query);
                return;
            }
            chr.chatMessage(SpeakerChannel, "Portal Name: " + portal.getName());
            chr.chatMessage(SpeakerChannel, "Portal ID: " + NumberFormat.getNumberInstance(Locale.US).format(portal.getId()));
            chr.chatMessage(SpeakerChannel, "Portal target map: " + NumberFormat.getNumberInstance(Locale.US).format(portal.getTargetMapId()));
            chr.chatMessage(SpeakerChannel, "Portal position: " + portal.getX() + ", " + portal.getY());
            chr.chatMessage(SpeakerChannel, "Portal script: " + portal.getScript());
            chr.chatMessage(SpeakerChannel, ".");
            log.info(portal.getScript());
        }
    }

    public static class showBuffs extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            Set<Integer> buffs = new HashSet<>();
            for (List<Option> options : tsm.getCurrentStats().values()) {
                for (Option o : options) {
                    if (o.rOption != 0) {
                        buffs.add(o.rOption);
                    } else {
                        buffs.add(o.nReason);
                    }
                }
            }
            StringBuilder sb = new StringBuilder("Current buffs: ");
            for (int id : buffs) {
                String skillName = StringData.getSkillStringById(id) != null ? StringData.getSkillStringById(id).getName() : "Unknown Skill ID";
                sb.append(skillName).append(" (").append(id).append("), ");
            }
            chr.chatMessage(sb.toString().substring(0, sb.toString().length() - 2));
        }
    }

}
