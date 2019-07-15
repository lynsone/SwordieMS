package net.swordie.ms.handlers.user;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.skills.*;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.adventurer.Archer;
import net.swordie.ms.client.jobs.adventurer.BeastTamer;
import net.swordie.ms.client.jobs.cygnus.BlazeWizard;
import net.swordie.ms.client.jobs.legend.Aran;
import net.swordie.ms.client.jobs.legend.Luminous;
import net.swordie.ms.client.jobs.nova.Kaiser;
import net.swordie.ms.client.jobs.resistance.Demon;
import net.swordie.ms.client.jobs.resistance.WildHunterInfo;
import net.swordie.ms.client.jobs.sengoku.Kanna;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.QuestConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.PsychicLock;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.life.FieldAttackObj;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.AddRangeOnOff;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.StopForceAtomInfo;
import static net.swordie.ms.enums.StealMemoryType.REMOVE_STEAL_MEMORY;
import static net.swordie.ms.enums.StealMemoryType.STEAL_SKILL;

public class JobSkillHandler {

    private static final Logger log = Logger.getLogger(JobSkillHandler.class);


    @Handler(op = InHeader.CREATE_KINESIS_PSYCHIC_AREA)
    public static void handleCreateKinesisPsychicArea(Char chr, InPacket inPacket) {
        PsychicArea pa = new PsychicArea();
        pa.action = inPacket.decodeInt();
        pa.actionSpeed = inPacket.decodeInt();
        pa.localPsychicAreaKey = inPacket.decodeInt();
        pa.psychicAreaKey = inPacket.decodeInt();
        pa.skillID = inPacket.decodeInt();
        pa.slv = inPacket.decodeShort();
        pa.duration = inPacket.decodeInt();
        pa.isLeft = inPacket.decodeByte() != 0;
        pa.skeletonFilePathIdx = inPacket.decodeShort();
        pa.skeletonAniIdx = inPacket.decodeShort();
        pa.skeletonLoop = inPacket.decodeShort();
        pa.start = inPacket.decodePositionInt();
        pa.success = true;
        if (!chr.hasSkillWithSlv(pa.skillID, pa.slv)) {
            return;
        }
        chr.write(FieldPacket.createPsychicArea(chr.getId(), pa));
        chr.write(UserLocal.doActivePsychicArea(pa));
        chr.getField().broadcastPacket(UserLocal.enterFieldPsychicInfo(chr.getId(), null, Collections.singletonList(pa)), chr);
        chr.chatMessage(ChatType.Mob, "SkillID: " + pa.skillID + " (Psychic Area)");
    }

    @Handler(op = InHeader.RELEASE_PSYCHIC_AREA)
    public static void handleReleasePsychicArea(Char chr, InPacket inPacket) {
        int localPsychicAreaKey = inPacket.decodeInt();
        chr.getField().broadcastPacket(FieldPacket.releasePsychicArea(localPsychicAreaKey));
    }

    @Handler(op = InHeader.CREATE_PSYCHIC_LOCK)
    public static void handleCreatePsychicLock(Char chr, InPacket inPacket) {
        Field f = chr.getField();
        PsychicLock pl = new PsychicLock();
        pl.skillID = inPacket.decodeInt();
        pl.slv = inPacket.decodeShort();
        pl.action = inPacket.decodeInt();
        pl.actionSpeed = inPacket.decodeInt();
        int i = 1;
        while (inPacket.decodeByte() != 0) {
            PsychicLockBall plb = new PsychicLockBall();
            plb.localKey = inPacket.decodeInt();
            plb.psychicLockKey = inPacket.decodeInt();
            plb.psychicLockKey = i++;
            int mobID = inPacket.decodeInt();
            Life life = f.getLifeByObjectID(mobID);
            plb.mob = life == null ? null : (Mob) life;
            plb.stuffID = inPacket.decodeShort();
            plb.usableCount = inPacket.decodeShort();
            plb.posRelID = inPacket.decodeByte();
            plb.start = inPacket.decodePositionInt();
            plb.rel = inPacket.decodePositionInt();
            pl.psychicLockBalls.add(plb);
        }
        if (!chr.hasSkillWithSlv(pl.skillID, pl.slv)) {
            return;
        }
        chr.getField().broadcastPacket(UserLocal.enterFieldPsychicInfo(chr.getId(), pl, null), chr);
        chr.write(UserPacket.createPsychicLock(chr.getId(), pl));
    }

    @Handler(op = InHeader.RELEASE_PSYCHIC_LOCK)
    public static void handleReleasePsychicLock(Char chr, InPacket inPacket) {
        int skillID = inPacket.decodeInt();
        short slv = inPacket.decodeShort();
        if (!chr.hasSkillWithSlv(skillID, slv)) {
            return;
        }
        short count = inPacket.decodeShort();
        int id = inPacket.decodeInt();
        int mobID = inPacket.decodeInt();
        if (mobID != 0) {
            List<Integer> l = new ArrayList<>();
            l.add(mobID);
            chr.write(FieldPacket.releasePsychicLockMob(l));
        } else {
            chr.write(FieldPacket.releasePsychicLock(id));
        }
    }

    @Handler(op = InHeader.REQUEST_ARROW_PLATTER_OBJ)
    public static void handleRequestArrowPlatterObj(Char chr, InPacket inPacket) {
        boolean flip = inPacket.decodeByte() != 0;
        Position position = inPacket.decodePositionInt(); // ignoring this, we just take the char's info we know
        int skillID = Archer.ARROW_PLATTER;
        Skill skill = chr.getSkill(skillID);
        if (skill != null && skill.getCurrentLevel() > 0) {
            Field field = chr.getField();
            Set<FieldAttackObj> currentFaos = field.getFieldAttackObjects();
            // remove the old arrow platter
            currentFaos.stream()
                    .filter(fao -> fao.getOwnerID() == chr.getId() && fao.getTemplateId() == 1)
                    .findAny().ifPresent(field::removeLife);
            SkillInfo si = SkillData.getSkillInfoById(skillID);
            int slv = skill.getCurrentLevel();
            FieldAttackObj fao = new FieldAttackObj(1, chr.getId(), chr.getPosition().deepCopy(), flip);
            field.spawnLife(fao, chr);
            field.broadcastPacket(FieldAttackObjPool.objCreate(fao), chr);
            ScheduledFuture sf = EventManager.addEvent(() -> field.removeLife(fao.getObjectId(), true),
                    si.getValue(SkillStat.u, slv), TimeUnit.SECONDS);
            field.addLifeSchedule(fao, sf);
            field.broadcastPacket(FieldAttackObjPool.setAttack(fao.getObjectId(), 0));
        }
        chr.dispose();
    }


    @Handler(op = InHeader.USER_FLAME_ORB_REQUEST)
    public static void handleUserFlameOrbRequest(Char chr, InPacket inPacket) {
        int skillID = inPacket.decodeInt();
        byte slv = inPacket.decodeByte();
        short dir = inPacket.decodeShort();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        int range = si.getValue(SkillStat.range, slv);
        ForceAtomEnum fae;
        switch (skillID) {
            case BlazeWizard.FINAL_ORBITAL_FLAME:
                fae = ForceAtomEnum.ORBITAL_FLAME_4;
                skillID = BlazeWizard.FINAL_ORBITAL_FLAME_ATOM;
                break;
            case BlazeWizard.GRAND_ORBITAL_FLAME:
                fae = ForceAtomEnum.ORBITAL_FLAME_3;
                skillID = BlazeWizard.GRAND_ORBITAL_FLAME_ATOM;
                break;
            case BlazeWizard.GREATER_ORBITAL_FLAME:
                fae = ForceAtomEnum.ORBITAL_FLAME_2;
                skillID = BlazeWizard.GREATER_ORBITAL_FLAME_ATOM;
                break;
            default:
                fae = ForceAtomEnum.ORBITAL_FLAME_1;
                skillID = BlazeWizard.ORBITAL_FLAME_ATOM;
                break;
        }

        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStatBySkillId(BlazeWizard.ORBITAL_FLAME_RANGE)) {
            range = tsm.getOptByCTSAndSkill(AddRangeOnOff, BlazeWizard.ORBITAL_FLAME_RANGE).nOption;
        }

        int curTime = Util.getCurrentTime();
        int angle = 0; // left
        int firstImpact = 5;
        int secondImpact = 21;
        switch (dir) {
            case 1: // right
                angle = 180;
                break;
            case 2: // up
                angle = 270;
                firstImpact = 11;
                secondImpact = 13;
                range /= 1.5;
                break;
            case 3: // down
                angle = 90;
                firstImpact = 11;
                secondImpact = 13;
                range /= 1.5;
                break;
        }
        ForceAtomInfo fai = new ForceAtomInfo(1, fae.getInc(), firstImpact, secondImpact,
                angle, 0, curTime, si.getValue(SkillStat.mobCount, slv), skillID, new Position(0, 0));
        List<ForceAtomInfo> faiList = new ArrayList<>();
        faiList.add(fai);
        chr.getField().broadcastPacket(FieldPacket.createForceAtom(false, 0, chr.getId(), fae.getForceAtomType(), false,
                new ArrayList<>(), skillID, faiList, null, dir, range, null, 0, null));
    }

    @Handler(op = InHeader.ZERO_TAG)
    public static void handleZeroTag(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int newTF = inPacket.decodeInt();
        int oldTF = inPacket.decodeInt();
        chr.swapZeroState();
    }

    @Handler(op = InHeader.REQUEST_SET_BLESS_OF_DARKNESS)
    public static void handleRequestSetBlessOfDarkness(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        if (JobConstants.isLuminous(chr.getJob())) {
            Luminous.changeBlackBlessingCount(c, true);
        }
    }

    @Handler(op = InHeader.RW_MULTI_CHARGE_CANCEL_REQUEST)
    public static void handleRWMultiChargeCancelRequest(Client c, InPacket inPacket) {
        byte unk = inPacket.decodeByte();
        int skillid = inPacket.decodeInt();

        c.write(UserLocal.rwMultiChargeCancelRequest(unk, skillid));
    }

    @Handler(op = InHeader.FOX_MAN_ACTION_SET_USE_REQUEST)
    public static void handleFoxManActionSetUseRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        if (!chr.hasSkill(Kanna.HAKU) && !chr.hasSkill(Kanna.HAKU_REBORN)) {
            return;
        }
        inPacket.decodeInt(); // tick
        byte skillNumber = inPacket.decodeByte(); //bSkill Number
        // more packet, but seems useless
        switch (skillNumber) {
            case 3:
                Kanna.hakuFoxFire(chr);
                break;
            case 4:
                Kanna.hakuHakuBlessing(chr);
                break;
            case 5:
                Kanna.hakuBreathUnseen(chr);
                break;
        }
    }

    @Handler(op = InHeader.USER_CREATE_HOLIDOM_REQUEST)
    public static void handleUserCreateHolidomRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Field field = chr.getField();

        inPacket.decodeInt(); //tick
        inPacket.decodeByte(); //unk
        int skillID = inPacket.decodeInt();
        inPacket.decodeInt(); //unk

        if (field.getAffectedAreas().stream().noneMatch(ss -> ss.getSkillID() == skillID)) {
            chr.getOffenseManager().addOffense(String.format("Character %d tried to heal from Holy Fountain (%d) whilst there isn't any on the field.", chr.getId(), skillID));
            return;
        }
        c.getChr().heal((int) (c.getChr().getMaxHP() / ((double) 100 / 40)));
    }

    @Handler(op = InHeader.REQUEST_DEC_COMBO)
    public static void handleRequestDecCombo(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        if (JobConstants.isAran(chr.getJob())) {
            Aran aranJobHandler = ((Aran) c.getChr().getJobHandler());
            aranJobHandler.setCombo(aranJobHandler.getCombo() - 10);
        }
    }

    @Handler(op = InHeader.REQUEST_SET_HP_BASE_DAMAGE)
    public static void handleRequestSetHpBaseDamage(Char chr, InPacket inPacket) {
        if (JobConstants.isDemonAvenger(chr.getJob())) {
            ((Demon) chr.getJobHandler()).sendHpUpdate();
        }
    }

    @Handler(op = InHeader.USER_REQUEST_FLYING_SWORD_START)
    public static void handleUserRequestFlyingSwordStart(Char chr, InPacket inPacket) {
        if (JobConstants.isKaiser(chr.getJob())) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            int maxCount = 3;
            if (Kaiser.getTempBladeSkill(chr, tsm) == Kaiser.TEMPEST_BLADES_FIVE || Kaiser.getTempBladeSkill(chr, tsm) == Kaiser.TEMPEST_BLADES_FIVE_FF) {
                maxCount = 5;
            }
            int mobCount = inPacket.decodeInt();
            int lastMobID = 0;
            int mobid = 0;

            for (int i = 0; i < mobCount; i++) {
                mobid = inPacket.decodeInt();

                Mob mob = (Mob) chr.getField().getLifeByObjectID(mobid);
                int inc = ForceAtomEnum.KAISER_WEAPON_THROW_1.getInc();
                int type = ForceAtomEnum.KAISER_WEAPON_THROW_1.getForceAtomType();

                switch (tsm.getOption(StopForceAtomInfo).nOption) {
                    case 3:
                        inc = ForceAtomEnum.KAISER_WEAPON_THROW_MORPH_1.getInc();
                        type = ForceAtomEnum.KAISER_WEAPON_THROW_MORPH_1.getForceAtomType();
                        break;
                    case 2:
                        inc = ForceAtomEnum.KAISER_WEAPON_THROW_2.getInc();
                        type = ForceAtomEnum.KAISER_WEAPON_THROW_2.getForceAtomType();
                        break;
                    case 4:
                        inc = ForceAtomEnum.KAISER_WEAPON_THROW_MORPH_2.getInc();
                        type = ForceAtomEnum.KAISER_WEAPON_THROW_MORPH_2.getForceAtomType();
                        break;
                }

                ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 25, 30,
                        0, 10 * i, (int) System.currentTimeMillis(), 1, 0,
                        new Position());
                chr.getField().broadcastPacket(FieldPacket.createForceAtom(false, 0, chr.getId(), type,
                        true, mob.getObjectId(), Kaiser.TEMPEST_BLADES_FIVE_FF, forceAtomInfo, new Rect(), 0, 300,
                        mob.getPosition(), Kaiser.TEMPEST_BLADES_FIVE_FF, mob.getPosition()));

                lastMobID = mobid;
            }


            for (int i = mobCount; i < maxCount; i++) {

                Mob mob = (Mob) chr.getField().getLifeByObjectID(lastMobID);
                int inc = ForceAtomEnum.KAISER_WEAPON_THROW_1.getInc();
                int type = ForceAtomEnum.KAISER_WEAPON_THROW_1.getForceAtomType();

                switch (tsm.getOption(StopForceAtomInfo).nOption) {
                    case 3:
                        inc = ForceAtomEnum.KAISER_WEAPON_THROW_MORPH_1.getInc();
                        type = ForceAtomEnum.KAISER_WEAPON_THROW_MORPH_1.getForceAtomType();
                        break;
                    case 2:
                        inc = ForceAtomEnum.KAISER_WEAPON_THROW_2.getInc();
                        type = ForceAtomEnum.KAISER_WEAPON_THROW_2.getForceAtomType();
                        break;
                    case 4:
                        inc = ForceAtomEnum.KAISER_WEAPON_THROW_MORPH_2.getInc();
                        type = ForceAtomEnum.KAISER_WEAPON_THROW_MORPH_2.getForceAtomType();
                        break;
                }

                ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 25, 30,
                        0, 12 * i, (int) System.currentTimeMillis(), 1, 0,
                        new Position());
                chr.getField().broadcastPacket(FieldPacket.createForceAtom(false, 0, chr.getId(), type,
                        true, mob.getObjectId(), Kaiser.TEMPEST_BLADES_FIVE_FF, forceAtomInfo, new Rect(), 0, 300,
                        mob.getPosition(), Kaiser.TEMPEST_BLADES_FIVE_FF, mob.getPosition()));
            }

            tsm.removeStat(StopForceAtomInfo, false);
            tsm.sendResetStatPacket();
        }
    }


    @Handler(op = InHeader.USER_REQUEST_STEAL_SKILL_LIST)
    public static void handleUserRequestStealSkillList(Client c, InPacket inPacket) {
        int targetChrID = inPacket.decodeInt();

        Char chr = c.getChr();
        Char targetChr = chr.getField().getCharByID(targetChrID);
        Set<Skill> targetSkillsList = targetChr.getSkills();

        chr.write(UserLocal.resultStealSkillList(targetSkillsList, 4, targetChrID, targetChr.getJob()));
        chr.dispose();
    }

    @Handler(op = InHeader.USER_REQUEST_STEAL_SKILL_MEMORY)
    public static void handleUserRequestStealSkillMemory(Client c, InPacket inPacket) {
        int stealSkillID = inPacket.decodeInt();
        int targetChrID = inPacket.decodeInt();
        boolean add = inPacket.decodeByte() != 0;   // 0 = add  |  1 = remove

        Char chr = c.getChr();
        Char targetChr = c.getChr().getField().getCharByID(targetChrID);

        Skill stolenSkill = SkillData.getSkillDeepCopyById(stealSkillID);
        int stealSkillMaxLv = stolenSkill.getMasterLevel();
        int stealSkillCurLv = targetChr == null ? stealSkillMaxLv : targetChr.getSkill(stealSkillID).getCurrentLevel(); //TODO this is for testing,  needs to be:    targetChr.getSkillID(stealSkillID).getCurrentLevel();

        if (!add) {
            // /Add Stolen Skill

            if (chr.getStolenSkillBySkillId(stealSkillID) != null) {
                chr.chatMessage("You already have this stolen skill.");
                chr.dispose();
                return;
            }

            int position = StolenSkill.getFirstEmptyPosition(chr, stealSkillID);
            if (position == -1) {
                chr.chatMessage("Could not continue with stealing skills due to an unknown error.");
                chr.dispose();
                return;
            }
            StolenSkill.setSkill(chr, stealSkillID, position, (byte) stealSkillCurLv);

            int positionPerTab = StolenSkill.getPositionForTab(position, stealSkillID);
            c.write(UserLocal.changeStealMemoryResult(STEAL_SKILL.getVal(), SkillConstants.getStealSkillManagerTabFromSkill(stealSkillID), positionPerTab, stealSkillID, stealSkillCurLv, stealSkillMaxLv));
        } else {
            //Remove Stolen Skill
            int position = StolenSkill.getPositionPerTabFromStolenSkill(chr.getStolenSkillBySkillId(stealSkillID));
            StolenSkill.removeSkill(chr, stealSkillID);
            c.write(UserLocal.changeStealMemoryResult(REMOVE_STEAL_MEMORY.getVal(), SkillConstants.getStealSkillManagerTabFromSkill(stealSkillID), position, stealSkillID, stealSkillCurLv, stealSkillMaxLv));
        }
        chr.dispose();
    }

    @Handler(op = InHeader.USER_REQUEST_SET_STEAL_SKILL_SLOT)
    public static void handleUserRequestSetStealSkillSlot(Client c, InPacket inPacket) {
        int impeccableSkillID = inPacket.decodeInt();
        int stealSkillID = inPacket.decodeInt();

        ChosenSkill.setChosenSkill(c.getChr(), stealSkillID, impeccableSkillID);
        c.write(UserLocal.resultSetStealSkill(true, impeccableSkillID, stealSkillID));
        c.getChr().dispose();
    }

    @Handler(op = InHeader.ENTER_OPEN_GATE_REQUEST)
    public static void handleEnterOpenGateRequest(Char chr, InPacket inPacket) {
        int chrId = inPacket.decodeInt();
        Position position = inPacket.decodePosition();
        byte gateId = inPacket.decodeByte();

        // Probably needs remote player position handling
        chr.dispose(); // Necessary as going through the portal will stuck you
    }

    @Handler(op = InHeader.USER_SET_DRESS_CHANGED_REQUEST)
    public static void handleUserSetDressChangedRequest(Char chr, InPacket inPacket) {
        boolean on = inPacket.decodeByte() != 0;
//        chr.write(UserLocal.setDressChanged(on, true)); // causes client to send this packet again
    }

    @Handler(op = InHeader.BEAST_TAMER_REGROUP_REQUEST)
    public static void handleBeastTamerRegroupRequest(Char chr, InPacket inPacket) {
        byte unk = inPacket.decodeByte();
        int skillId = inPacket.decodeInt();

        if (skillId == BeastTamer.REGROUP) {
            BeastTamer.beastTamerRegroup(chr);
        } else {
            log.error(String.format("Unhandled Beast Tamer Request %d", skillId));
            chr.chatMessage(String.format("Unhandled Beast Tamer Request %d", skillId));
        }
    }

    @Handler(op = InHeader.USER_JAGUAR_CHANGE_REQUEST)
    public static void handleUserJaguarChangeRequest(Char chr, InPacket inPacket) {
        final int questID = QuestConstants.WILD_HUNTER_JAGUAR_STORAGE_ID;
        Quest quest = chr.getQuestManager().getQuestById(questID);
        if (quest == null) {
            return;
        }
        quest.convertQRValueToProperties();
        int fromID = inPacket.decodeInt();
        int toID = inPacket.decodeInt();
        String value = quest.getProperty("" + (toID + 1));
        if (value != null) {
            WildHunterInfo whi = chr.getWildHunterInfo();
            whi.setIdx((byte) toID);
            whi.setRidingType((byte) toID);
            chr.write(WvsContext.wildHunterInfo(whi));
        } else {
            chr.chatMessage("You do not have that jaguar.");
        }
    }
}
