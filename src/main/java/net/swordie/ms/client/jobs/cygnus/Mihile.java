package net.swordie.ms.client.jobs.cygnus;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.*;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ChatMsgColour;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;

import java.util.Arrays;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class Mihile extends Job {

    public static final int ROYAL_GUARD = 51001006; //Special Buff/Attack
    public static final int ROYAL_GUARD_2 = 51001007;
    public static final int ROYAL_GUARD_3 = 51001008;
    public static final int ROYAL_GUARD_4 = 51001009;
    public static final int ROYAL_GUARD_5 = 51001010;

    public static final int SWORD_BOOSTER = 51101003; //Buff
    public static final int RALLY = 51101004; //Buff

    public static final int ENDURING_SPIRIT = 51111004; //Buff
    public static final int SOUL_LINK = 51111008; //Special Buff (ON/OFF)
    public static final int MAGIC_CRASH = 51111005; //Special Skill (Debuff Mobs)
    public static final int ADVANCED_ROYAL_GUARD = 51110009; //Upgrade on Royal Guard

    public static final int ROILING_SOUL = 51121006; //Buff (ON/OFF)
    public static final int FOUR_POINT_ASSAULT = 51121007; //Special Attack (Accuracy Debuff)
    public static final int RADIANT_CROSS = 51121009; //Special Attack (Accuracy Debuff)    Creates an Area of Effect
    public static final int RADIANT_CROSS_AA = 51120057; //Area of Effect,  After Radiant Cross
    public static final int CALL_OF_CYGNUS_MIHILE = 51121005; //Buff

    //Final Attack
    public static final int FINAL_ATTACK_MIHILE = 51100002;
    public static final int ADVANCED_FINAL_ATTACK_MIHILE = 51120002;


    public static final int CHARGING_LIGHT = 51121052;
    public static final int QUEEN_OF_TOMORROW = 51121053;
    public static final int SACRED_CUBE = 51121054;

    private int[] buffs = new int[] {
            ROYAL_GUARD,
            ROYAL_GUARD_2,
            ROYAL_GUARD_3,
            ROYAL_GUARD_4,
            ROYAL_GUARD_5,
            SWORD_BOOSTER,
            RALLY,
            ENDURING_SPIRIT,
            SOUL_LINK,
            ROILING_SOUL,
            CALL_OF_CYGNUS_MIHILE,
            QUEEN_OF_TOMORROW,
            SACRED_CUBE,
    };

    public Mihile(Char chr) {
        super(chr);
    }

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (skillID) {
            case ROYAL_GUARD:   //BuffStat 'ShieldAttack'  has something to do with this skill
            case ROYAL_GUARD_2:
            case ROYAL_GUARD_3:
            case ROYAL_GUARD_4:
            case ROYAL_GUARD_5:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 1;
                tsm.putCharacterStatValue(RoyalGuardPrepare, o1);
                break;
            case SWORD_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case RALLY:
                o1.nValue = si.getValue(indiePad, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                break;
            case ENDURING_SPIRIT: // PDDR(DEF%) = x  |  AsrR & TerR = y & z
                o1.nValue = si.getValue(indiePddR, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePDDR, o1);
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o2);
                o3.nOption = si.getValue(z, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(TerR, o3);
                break;
            case SOUL_LINK: // (ON/OFF)
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(MichaelSoulLink, o1);
                o2.nOption = 1;
                o2.rOption = skillID;
                o2.tOption = 0;
                tsm.putCharacterStatValue(BMageAura, o2);
                // dot = healing duration
                // indieDamR = dmg% per member
                // q = receive 20%s of party's dmg which can be nullified with Royal Guard
                // s = HP% recovery
                // w = DEF% from enduring Spirit
                // x = Att/M.att%
                // y = AsrR
                // z = 4000

                // TODO
                break;
            case ROILING_SOUL: // (ON/OFF)
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(Enrage, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = 0;
                tsm.putCharacterStatValue(DamR, o2);
                o3.nOption = si.getValue(y, slv);
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(IncCriticalDamMin, o3);
                break;
            case CALL_OF_CYGNUS_MIHILE:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;

            case QUEEN_OF_TOMORROW:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMaxDamageOverR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMaxDamageOverR, o2);
                break;

            case SACRED_CUBE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMhpR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHPR, o2);
                o3.nOption = si.getValue(x, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamageReduce, o3);
                o4.nOption = 1;
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamAbsorbShield, o4);
                break;
        }
        tsm.sendSetStatPacket();
        super.handleBuff(c, inPacket, skillID, slv);
    }

    private void handleRoyalGuard(TemporaryStatManager tsm, Client c) { //TempStat  Shield Attack is Effect
        Option o = new Option();
        Option o1 = new Option();
        Option o2 = new Option();
        SkillInfo rgi = SkillData.getSkillInfoById(ROYAL_GUARD);
        int amount = 1;
        if(tsm.hasStat(RoyalGuardState)) {
            amount = tsm.getOption(RoyalGuardState).xOption;
            if(amount < royalGuardMaxCounter()) {
                amount++;
            }
        }
        o2.nOption = 1;
        o2.rOption = ROYAL_GUARD_2;
        o2.tOption = 4;
        tsm.putCharacterStatValue(NotDamaged, o2);

        o.nOption = 1;
        o.xOption = amount;
        o.bOption = 4;
        o.rOption = ROYAL_GUARD;
        o.tOption = 12;
        tsm.putCharacterStatValue(RoyalGuardState, o);
        o1.nOption = getRoyalGuardAttPower();
        o1.rOption = ROYAL_GUARD;
        o1.tOption = 12;
        tsm.putCharacterStatValue(PAD, o1);
        tsm.sendSetStatPacket();
    }

    private int royalGuardMaxCounter() {
        int num = 3;
        if(chr.hasSkill(ROYAL_GUARD)) {
            num = 3;
        }
        if(chr.hasSkill(ADVANCED_ROYAL_GUARD)) {
            num = 5;
        }
        return num;
    }

    private int getRoyalGuardAttPower() {
        int pad = 0;
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(ROYAL_GUARD);
        byte slv = (byte) si.getCurrentLevel();
        if(tsm.getOption(RoyalGuardState).xOption == 1) {
            pad = 10;
        }
        if(tsm.getOption(RoyalGuardState).xOption == 2) {
            pad = 15;
        }
        if(tsm.getOption(RoyalGuardState).xOption == 3) {
            pad = 20;
        }
        if(tsm.getOption(RoyalGuardState).xOption == 4) {
            pad = 25;
        }
        if(tsm.getOption(RoyalGuardState).xOption == 5) {
            pad = 35;
        }
        return pad;
    }

    private void handleRoyalGuardAttack() {
        c.write(UserLocal.royalGuardAttack(true));
    }


    public boolean isBuff(int skillID) {
        return super.isBuff(skillID) || Arrays.stream(buffs).anyMatch(b -> b == skillID);
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
            case FOUR_POINT_ASSAULT:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = si.getValue(x, slv);
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.ACC, o1);
                    }
                }
                break;
            case RADIANT_CROSS:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(y, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = si.getValue(x, slv);
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.ACC, o1);


                    }
                }
                if(chr.hasSkill(RADIANT_CROSS_AA)) {
                    SkillInfo rca = SkillData.getSkillInfoById(RADIANT_CROSS_AA); //TODO stay forever, need to dissapear after 7s
                    AffectedArea aa = AffectedArea.getAffectedArea(chr, attackInfo);
                    aa.setMobOrigin((byte) 0);
                    aa.setSkillID(RADIANT_CROSS_AA);
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(rca.getRects().get(0)));
                    if (chr.isLeft()) {
                        aa.setFlip(false);
                    } else {
                        aa.setFlip(true);
                    }
                    aa.setDelay((short) 7); //spawn delay
                    chr.getField().spawnAffectedArea(aa);
                }
                break;
            case RADIANT_CROSS_AA:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                MobTemporaryStat mts = mob.getTemporaryStat();
                    if(mob.isBoss()) {
                        o1.nOption = si.getValue(x, slv);
                        o1.rOption = skill.getSkillId();
                        o1.tOption = (si.getValue(time, slv) / 2);
                        mts.addStatOptionsAndBroadcast(MobStat.ACC, o1);
                    } else {
                        o1.nOption = si.getValue(x, slv);
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.ACC, o1);
                    }
                }
                break;
            case CHARGING_LIGHT:
                o1.nReason = skillID;
                o1.nValue = 10;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                tsm.sendSetStatPacket();
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
        super.handleSkill(c, skillID, slv, inPacket);
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
                case MAGIC_CRASH:
                    Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
                    if(!chr.isLeft()) {
                        rect = rect.moveRight();
                    }
                    for(Life life : chr.getField().getLifesInRect(rect)) {
                        if(life instanceof Mob && ((Mob) life).getHp() > 0) {
                            Mob mob = (Mob) life;
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            if(Util.succeedProp(si.getValue(prop, slv))) {
                                mts.removeBuffs();
                                o1.nOption = 1;
                                o1.rOption = skillID;
                                o1.tOption = si.getValue(time, slv);
                                mts.addStatOptionsAndBroadcast(MobStat.MagicCrash, o1);
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(RoyalGuardPrepare)) {
            handleRoyalGuardAttack();
            handleRoyalGuard(tsm, c);
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id == JobConstants.JobEnum.NAMELESS_WARDEN.getJobId() || JobConstants.isMihile(id);
    }

    @Override
    public int getFinalAttackSkill() {
        if(Util.succeedProp(getFinalAttackProc())) {
            int fas = 0;
            if (chr.hasSkill(FINAL_ATTACK_MIHILE)) {
                fas = FINAL_ATTACK_MIHILE;
            }
            if (chr.hasSkill(ADVANCED_FINAL_ATTACK_MIHILE)) {
                fas = ADVANCED_FINAL_ATTACK_MIHILE;
            }
            return fas;
        } else {
            return 0;
        }
    }

    private Skill getFinalAtkSkill(Char chr) {
        Skill skill = null;
        if(chr.hasSkill(FINAL_ATTACK_MIHILE)) {
            skill = chr.getSkill(FINAL_ATTACK_MIHILE);
        }
        if(chr.hasSkill(ADVANCED_FINAL_ATTACK_MIHILE)) {
            skill = chr.getSkill(ADVANCED_FINAL_ATTACK_MIHILE);
        }
        return skill;
    }

    private int getFinalAttackProc() {
        Skill skill = getFinalAtkSkill(chr);
        if (skill == null) {
            return 0;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) chr.getSkill(skill.getSkillId()).getCurrentLevel();

        return si.getValue(prop, slv);
    }
}
