package client.jobs.nova;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.field.Field;
import client.jobs.Job;
import client.life.Life;
import client.life.Mob;
import client.life.MobTemporaryStat;
import connection.InPacket;
import constants.JobConstants;
import enums.ChatMsgColour;
import enums.ForceAtomEnum;
import enums.MobStat;
import loaders.SkillData;
import packet.CField;
import packet.UserLocal;
import packet.WvsContext;
import util.Position;
import util.Rect;
import util.Util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class AngelicBuster extends Job {
    public static final int DRESS_UP = 60011222;
    public static final int SOUL_BUSTER = 60011216;
    public static final int HYPER_COORDINATE = 60011221;
    public static final int GRAPPLING_HEART = 60011218;
    public static final int DAY_DREAMER = 60011220;
    public static final int TRUE_HEART_INHERITANCE = 60010217;

    public static final int AB_NORMAL_ATTACK = 60011216; //TODO Recharge Attack

    public static final int STAR_BUBBLE = 65001100; //TODO Recharge Attack
    public static final int MELODY_CROSS = 65001002; //Buff

    public static final int LOVELY_STING = 65101100; //TODO Recharge Attack + (Detonate Debuff)
    public static final int PINK_PUMMEL = 65101001; //TODO Recharge Attack
    public static final int POWER_TRANSFER = 65101002; //Buff

    public static final int SOUL_SEEKER = 65111100; //TODO Recharge Attack
    public static final int SOUL_SEEKER_ATOM = 65111007;
    public static final int SHINING_STAR_BURST = 65111101; //TODO Recharge Attack
    public static final int HEAVENLY_CRASH = 65111002; //TODO Recharge Attack
    public static final int IRON_BLOSSOM = 65111004; //Buff

    public static final int CELESTIAL_ROAR = 65121100; //TODO Recharge Attack + (Stun Debuff)
    public static final int TRINITY = 65121101; //TODO Recharge Attack
                                    //65121101 - Trinity -combo count-
    public static final int FINALE_RIBBON = 65121002; //TODO Recharge Attack + (DmgUp Debuff)
    public static final int STAR_GAZER = 65121004; //Buff
    public static final int NOVA_WARRIOR_AB = 65121009; //Buff
    public static final int SOUL_SEEKER_EXPERT = 65121011; //ON/OFF Buff

    public static final int PRETTY_EXALTATION = 65121054;
    public static final int FINAL_CONTRACT = 65121053;

    private int[] addedSkills = new int[] {
            DRESS_UP,
            SOUL_BUSTER,
            HYPER_COORDINATE,
            GRAPPLING_HEART,
            DAY_DREAMER,
            TRUE_HEART_INHERITANCE,
    };

    private final int[] buffs = new int[]{
            MELODY_CROSS,
            POWER_TRANSFER,
            IRON_BLOSSOM,
            STAR_GAZER,
            NOVA_WARRIOR_AB,
            SOUL_SEEKER_EXPERT,
            PRETTY_EXALTATION,
            FINAL_CONTRACT,
    };

    public AngelicBuster(Char chr) {
        super(chr);
        if(isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
        }
    }

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case MELODY_CROSS:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieBooster, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                o2.nOption = si.getValue(mhpX, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EMHP, o2);
                break;
            case POWER_TRANSFER:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PowerTransferGauge, o1);
                break;
            case IRON_BLOSSOM:
                o1.nOption = si.getValue(prop, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o1);
                break;
            case STAR_GAZER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SharpEyes, o1); //Changed IncCriticalDamMax to SharpEyes
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IncCriticalDamMin, o2);
                break;
            case NOVA_WARRIOR_AB:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case SOUL_SEEKER_EXPERT:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(AngelicBursterSoulSeeker, o1);
                break;
            case PRETTY_EXALTATION: //TODO extra 15% on Soul Seeker Expert
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieIgnoreMobpdpR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieBDR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBDR, o2);
                break;
            case FINAL_CONTRACT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CriticalBuff, o1);
                o2.nOption = si.getValue(asrR, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o2);
                tsm.putCharacterStatValue(TerR, o2);
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieStance, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStance, o1);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private void handleSoulSeekerExpert(int skillID, byte slv, AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(AngelicBursterSoulSeeker)) {
            SkillInfo si = SkillData.getSkillInfoById(SOUL_SEEKER_EXPERT);
            int anglenum;
            if (new Random().nextBoolean()) {
                anglenum = 50;
            } else {
                anglenum = 130;
            }
            int delaynum = new Random().nextInt(50); //Random delay between 0~90ms
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                int TW1prop = 100;//  SkillData.getSkillInfoById(SOUL_SEEKER_EXPERT).getValue(prop, slv);   //TODO Change
                if (Util.succeedProp(TW1prop)) {
                        int mobID = mai.mobId;
                        int inc = ForceAtomEnum.AB_ORB.getInc();
                        int type = ForceAtomEnum.AB_ORB.getForceAtomType();
                        ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 20, 40,
                                anglenum, delaynum, (int) System.currentTimeMillis(), 1, 0,
                                new Position(5, 0)); //Slightly behind the player
                        chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                                true, mobID, SOUL_SEEKER_ATOM, forceAtomInfo, new Rect(), 0, 300,
                                mob.getPosition(), SOUL_SEEKER_ATOM, mob.getPosition()));
                }
            }
        }
    }

    private void handleSoulSeeker() {
        Field field = chr.getField();
        SkillInfo si = SkillData.getSkillInfoById(SOUL_SEEKER);
        Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
        List<Life> lifes = field.getLifesInRect(rect);
        for(Life life : lifes) {
            if(life instanceof Mob) {
                int anglenum = new Random().nextInt(10);
                int mobID = ((Mob) life).getRefImgMobID(); //
                int inc = ForceAtomEnum.AB_ORB.getInc();
                int type = ForceAtomEnum.AB_ORB.getForceAtomType();
                ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 20, 40,
                        anglenum, 250, (int) System.currentTimeMillis(), 1, 0,
                        new Position(0, -100));
                chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                        true, mobID, SOUL_SEEKER_ATOM, forceAtomInfo, new Rect(), 0, 300,
                        life.getPosition(), SOUL_SEEKER_ATOM, life.getPosition()));
            }
        }
    }


    private void handleSoulSeekerReCreation(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(SOUL_SEEKER);
        int anglenum = new Random().nextInt(360);
        int delaynum = new Random().nextInt(150);
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            int TW1prop = 100;// TODO
            if (Util.succeedProp(TW1prop)) {
                int mobID = mai.mobId;

                int inc = ForceAtomEnum.AB_ORB_RECREATION.getInc();
                int type = ForceAtomEnum.AB_ORB_RECREATION.getForceAtomType();
                ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 30, 2,
                        anglenum, delaynum, (int) System.currentTimeMillis(), 1, 0,
                        new Position());
                chr.getField().broadcastPacket(CField.createForceAtom(true, chr.getId(), mobID, type,
                        true, mobID, SOUL_SEEKER_ATOM, forceAtomInfo, new Rect(), 0, 300,
                        mob.getPosition(), SOUL_SEEKER_ATOM, mob.getPosition()));
            }
        }
    }


    public boolean isBuff(int skillID) {
        return Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        Char chr = c.getChr();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(attackInfo.skillId);
        int skillID = 0;
        SkillInfo si = null;
        boolean hasHitMobs = attackInfo.mobAttackInfo.size() > 0;
        byte slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = (byte) skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        if(hasHitMobs) {

            if (attackInfo.skillId == SOUL_SEEKER_ATOM) {
                handleSoulSeekerReCreation(attackInfo);
            }

            if (attackInfo.skillId != SOUL_SEEKER_ATOM) {
                handleSoulSeekerExpert(skillID, slv, attackInfo);
            }

            if (Util.succeedProp(getRechargeProp(skillID, slv))) {
                c.write(UserLocal.onEffectRechargeAB());
                c.write(UserLocal.onResetStateForOffSkill());
            }

        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case AB_NORMAL_ATTACK:
                handleSoulSeekerExpert(60011216, slv, attackInfo);
                break;
            case TRINITY:

                break;
            case LOVELY_STING:      //Unknown Debuff
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        //mts.addStatOptionsAndBroadcast(MobStat.SoulExplosion, o1); //TODO Look for exact Debuff
                    }
                }
                break;
            case FINALE_RIBBON:     //DmgUp Debuff
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.AddDamParty, o1); //TODO Check if this is the Correct MobStat
                    }
                }
                break;
            case CELESTIAL_ROAR:    //Stun Debuff
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
        }
    }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
        Char chr = c.getChr();
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if(skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        chr.chatMessage(ChatMsgColour.YELLOW, "SkillID: " + skillID);
        if (isBuff(skillID)) {
            handleBuff(c, inPacket, skillID, slv);
        } else {
            Option o1 = new Option();
            Option o2 = new Option();
            Option o3 = new Option();
            switch(skillID) {
                case SOUL_SEEKER:
                    handleSoulSeeker();
                    handleSoulSeeker();
                    break;
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

    }

    @Override
    public boolean isHandlerOfJob(short id) {
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById(id);
        switch (job) {
            case ANGELIC_BUSTER:
            case ANGELIC_BUSTER1:
            case ANGELIC_BUSTER2:
            case ANGELIC_BUSTER3:
            case ANGELIC_BUSTER4:
                return true;
            default:
                return false;
        }
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    public int getRechargeProp(int skillID, byte slv) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        int primerecharge = 90;//  si.getValue(OnActive, slv);
        int recharge = primerecharge;

        if (chr.hasSkill(65000003)) {
            SkillInfo ah1 = SkillData.getSkillInfoById(65000003);
            recharge += ah1.getValue(x, slv);
        }
        if (skillID == CELESTIAL_ROAR && tsm.hasStat(AngelicBursterSoulSeeker)) {
            SkillInfo sse = SkillData.getSkillInfoById(SOUL_SEEKER_EXPERT);
            recharge += sse.getValue(z, slv);
        }
        if (primerecharge > 0) {
            return recharge;
        } else {
            return 0;
        }
    }
}
