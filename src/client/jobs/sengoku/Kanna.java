package client.jobs.sengoku;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.field.Field;
import client.jobs.Job;
import client.life.AffectedArea;
import client.life.Mob;
import client.life.MobTemporaryStat;
import client.life.Summon;
import connection.InPacket;
import constants.JobConstants;
import enums.ChatMsgColour;
import enums.MobStat;
import enums.MoveAbility;
import loaders.SkillData;
import packet.CField;
import packet.WvsContext;
import util.Position;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */

public class Kanna extends Job {

    public static final int HAKU = 40020109;

    public static final int RADIANT_PEACOCK = 42101003;
    public static final int NIMBUS_CURSE = 42101005;
    public static final int HAKU_REBORN = 42101002;

    public static final int KISHIN_SHOUKAN = 42111003; //summon
    public static final int BLOSSOM_BARRIER = 42111004; //AoE
    public static final int SOUL_SHEAR = 42111002; //Reactive Skill

    public static final int MONKEY_SPIRITS = 42120003; //Passive activation summon
    public static final int BELLFLOWER_BARRIER = 42121005; //AoE
    public static final int AKATUSKI_HERO_KANNA = 42121006;
    public static final int NINE_TAILED_FURY = 42121024; //Attacking Skill + Buff
    public static final int BINDING_TEMPEST = 42121004;
    public static final int BLOSSOMING_DAWN = 42121007;

    public static final int VERITABLE_PANDEMONIUM = 42121052; //Immobility Debuff
    public static final int PRINCESS_VOW_KANNA = 42121053;
    public static final int BLACKHEARTED_CURSE = 42121054;

    //Haku Buffs
    public static final int HAKUS_GIFT = 42121020;
    public static final int FOXFIRE = 42121021;
    public static final int HAKUS_BLESSING = 42121022;
    public static final int BREATH_UNSEEN = 42121023;

    private int[] buffs = new int[]{
            HAKU_REBORN,
            RADIANT_PEACOCK,
            KISHIN_SHOUKAN,
            AKATUSKI_HERO_KANNA,
            NINE_TAILED_FURY,
            PRINCESS_VOW_KANNA,
            BLACKHEARTED_CURSE,
    };

    public Kanna(Char chr) {
        super(chr);
        //getHakuFollow();
    }

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        Char chr = c.getChr();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(attackInfo.skillId);
        int skillID = 0;
        SkillInfo si = null;
        boolean hasHitMobs = attackInfo.mobAttackInfo.size() > 0;
        int slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case BINDING_TEMPEST:
            case VERITABLE_PANDEMONIUM:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = skill.getSkillId();
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }

                break;
            case NIMBUS_CURSE:
                AffectedArea aa = AffectedArea.getPassiveAA(skillID, (byte) slv);
                aa.setMobOrigin((byte) 0);
                aa.setCharID(chr.getId());
                aa.setPosition(chr.getPosition());
                aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                aa.setDelay((short) 5);
                chr.getField().spawnAffectedArea(aa);
                break;
        }
    }

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Summon summon;
        Field field;
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        switch (skillID) {
            case HAKU_REBORN:
                o1.nOption = 0;
                o1.rOption = skillID;
                o1.tOption = 30;
                tsm.putCharacterStatValue(ChangeFoxMan, o1);
                break;
            case RADIANT_PEACOCK:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                getHakuFollow();
                break;
            case KISHIN_SHOUKAN:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(true);

                int x1 = chr.getPosition().deepCopy().getX() - 500;
                int x2 = chr.getPosition().deepCopy().getX() + 500;
                summon.setKishinPositions(new Position[]{ new Position(x1, chr.getPosition().getY()),  new Position(x2, chr.getPosition().getY()) });

                summon.setMoveAbility(MoveAbility.STATIC.getVal());
                field.spawnAddSummon(summon);
                break;
            case AKATUSKI_HERO_KANNA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;
            case PRINCESS_VOW_KANNA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMaxDamageOver, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMaxDamageOver, o2);
                break;
            case BLACKHEARTED_CURSE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BlackHeartedCurse, o1);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    public boolean isBuff(int skillID) {
        return Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Char chr = c.getChr();
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        chr.chatMessage(ChatMsgColour.YELLOW, "SkillID: " + skillID);
        if (isBuff(skillID)) {
            handleBuff(c, inPacket, skillID, slv);
        } else {
            Option o1 = new Option();
            Option o2 = new Option();
            Option o3 = new Option();
            switch (skillID) {
                case BLOSSOM_BARRIER:
                case BELLFLOWER_BARRIER:
                    AffectedArea aa = AffectedArea.getPassiveAA(skillID, slv);
                    aa.setMobOrigin((byte) 0);
                    aa.setCharID(chr.getId());
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                    aa.setDelay((short) 3);
                    chr.getField().spawnAffectedArea(aa);
                    break;
                case NINE_TAILED_FURY:
                    o1.nReason = skillID;
                    o1.nValue = si.getValue(indieDamR, slv);
                    o1.tStart = (int) System.currentTimeMillis();
                    o1.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(IndieDamR, o1); //Indie
                    c.write(WvsContext.temporaryStatSet(tsm));
                    break;
                case BLOSSOMING_DAWN:
                    tsm.removeAllDebuffs();
                    break;
            }
        }
    }

    public void getHakuFollow() {
        //if(chr.hasSkill(HAKU)) {
            c.write(CField.enterFieldFoxMan(chr));
        //}
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        int foxfires = 6;
        if (tsm.hasStat(FireBarrier)) {
            if(foxfires > 1) {
                foxfires = foxfires - 1;
                }
            if(foxfires == 4 || foxfires == 3) {
                o.nOption = 2;
                tsm.putCharacterStatValue(FireBarrier, o);
                tsm.sendSetStatPacket();
            } else if(foxfires == 2) {
                o.nOption = 1;
                tsm.putCharacterStatValue(FireBarrier, o);
                tsm.sendSetStatPacket();
            } else if (foxfires == 1) {
                resetFireBarrier();
                o.nOption = 0;
                tsm.putCharacterStatValue(FireBarrier, o);
                tsm.sendSetStatPacket();
            }
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    public void resetFireBarrier() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStat(FireBarrier, false);
        c.write(WvsContext.temporaryStatReset(tsm, false));
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id == JobConstants.JobEnum.KANNA.getJobId() ||
                (id >= JobConstants.JobEnum.KANNA1.getJobId() && id <= JobConstants.JobEnum.KANNA4.getJobId());
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    public static void hakuFoxFire(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(FOXFIRE);
        int slv = si.getCurrentLevel();
        Option o1 = new Option();

        o1.nOption = 6;
        o1.rOption = FOXFIRE;
        o1.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(FireBarrier, o1);
        chr.write(WvsContext.temporaryStatSet(tsm));
    }

    public static void hakuHakuBlessing(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(HAKUS_BLESSING);
        int slv = si.getCurrentLevel();
        Option o1 = new Option();

        o1.nReason = HAKUS_BLESSING;
        o1.nValue = si.getValue(indiePdd, slv);
        o1.tStart = (int) System.currentTimeMillis();
        o1.tTerm = si.getValue(time, slv);
        tsm.putCharacterStatValue(IndiePDD, o1);
        chr.write(WvsContext.temporaryStatSet(tsm));
    }

    public static void hakuBreathUnseen(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(BREATH_UNSEEN);
        int slv = si.getCurrentLevel();
        Option o1 = new Option();
        Option o2 = new Option();

        o1.nOption = si.getValue(prop, slv);
        o1.rOption = BREATH_UNSEEN;
        o1.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(Stance, o1);
        o2.nOption = si.getValue(x, slv);
        o2.rOption = BREATH_UNSEEN;
        o2.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(IgnoreMobpdpR, o2);
        chr.write(WvsContext.temporaryStatSet(tsm));
    }
}