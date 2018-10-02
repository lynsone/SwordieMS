package net.swordie.ms.client.jobs.legend;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.LarknessManager;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.User;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Luminous extends Job {
    public static final int SUNFIRE = 20040216;
    public static final int ECLIPSE = 20040217;
    public static final int EQUILIBRIUM = 20040219;
    public static final int EQUILIBRIUM2 = 20040220; //test
    public static final int INNER_LIGHT = 20040221;
    public static final int FLASH_BLINK = 20041222;
    public static final int CHANGE_LIGHT_DARK = 20041239;

    public static final int MAGIC_BOOSTER = 27101004; //Buff
    public static final int BLACK_BLESSING = 27100003;

    public static final int SHADOW_SHELL = 27111004; //Buff
    public static final int RAY_OF_REDEMPTION = 27111101; // Attack + heals party members
    public static final int DUSK_GUARD = 27111005; //Buff
    public static final int PHOTIC_MEDITATION = 27111006; //Buff
    public static final int LUNAR_TIDE = 27110007;

    public static final int DARK_CRESCENDO = 27121005; //Buff
    public static final int ARCANE_PITCH = 27121006; //Buff
    public static final int MAPLE_WARRIOR_LUMI = 27121009; //Buff
    public static final int ENDER = 27121303;
    public static final int DARKNESS_MASTERY = 27120008;
    public static final int HEROS_WILL_LUMI = 27121010;

    public static final int EQUALIZE = 27121054;
    public static final int HEROIC_MEMORIES_LUMI = 27121053;
    public static final int ARMAGEDDON = 27121052; //Stun debuff

    private int[] addedSkills = new int[] {
            EQUILIBRIUM,
            //EQUILIBRIUM2,
            CHANGE_LIGHT_DARK
    };

    private final int[] buffs = new int[]{
            MAGIC_BOOSTER,
            SHADOW_SHELL,
            DUSK_GUARD,
            PHOTIC_MEDITATION,
            RAY_OF_REDEMPTION,
            DARK_CRESCENDO,
            ARCANE_PITCH,
            MAPLE_WARRIOR_LUMI,
            HEROIC_MEMORIES_LUMI,
            EQUALIZE,
    };

    private long darkCrescendoTimer;

    public Luminous(Char chr) {
        super(chr);
        if(chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }

            if (chr.getTemporaryStatManager().getLarknessManager() == null) {
                chr.getTemporaryStatManager().setLarknessManager(new LarknessManager(chr));
            }
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isLuminous(id);
    }



    // Buff related methods --------------------------------------------------------------------------------------------

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case MAGIC_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case SHADOW_SHELL:
                o1.nOption = 3;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                // no bOption for Luminous' AntiMagicShell
                tsm.putCharacterStatValue(AntiMagicShell, o1);
                break;
            case DUSK_GUARD:
                o1.nValue = si.getValue(indieMdd, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMDD, o1);
                o2.nValue = si.getValue(indiePdd, slv);
                o2.nReason = skillID;
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePDD, o2);
                break;
            case PHOTIC_MEDITATION:
                o1.nOption = si.getValue(emad, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EMAD, o1);
                break;
            case DARK_CRESCENDO:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                o1.mOption = 1;
                tsm.putCharacterStatValue(StackBuff, o1);
                darkCrescendoTimer = System.currentTimeMillis() + (si.getValue(time, slv) * 1000);
                break;
            case ARCANE_PITCH:
                o1.nOption = si.getValue(y, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ElementalReset, o1);
                break;
            case MAPLE_WARRIOR_LUMI:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case EQUALIZE:
                LarknessManager lm = tsm.getLarknessManager();
                lm.changeMode();
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(Larkness, o1);
                EventManager.addEvent(this::changeMode, getMoreEquilibriumTime(), TimeUnit.SECONDS);
                break;

            case HEROIC_MEMORIES_LUMI:
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
        }
        tsm.sendSetStatPacket();
    }

    public boolean isBuff(int skillID) {
        return super.isBuff(skillID) || Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    private void changeLarknessState(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        LarknessManager lm = tsm.getLarknessManager();
        if(SkillConstants.isLarknessLightSkill(skillId)) {
            lm.addGauge(2500, false);
        } else if(SkillConstants.isLarknessDarkSkill(skillId)) {
            lm.addGauge(2500, true);
        }
    }

    private void giveLunarTideBuff() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(chr.hasSkill(LUNAR_TIDE)) {
            Option o1 = new Option();
            Option o2 = new Option();
            SkillInfo lti = SkillData.getSkillInfoById(LUNAR_TIDE);
            Skill skill = chr.getSkill(LUNAR_TIDE);
            byte slv = (byte) skill.getCurrentLevel();
            int maxMP = c.getChr().getStat(Stat.mmp);
            int curMP = c.getChr().getStat(Stat.mp);
            int maxHP = c.getChr().getStat(Stat.mhp);
            int curHP = c.getChr().getStat(Stat.hp);
            double ratioHP = ((double)curHP/maxHP);
            double ratioMP = ((double)curMP)/maxMP;

            //if (ratioHP > ratioMP) {
            if(ratioHP > ratioMP) {
                //Crit Rate      HP is Greater than MP
                o1.nOption = 2;
                o1.rOption = LUNAR_TIDE;
                o1.tOption = 0;
                tsm.putCharacterStatValue(LifeTidal, o1);
                o2.nOption = lti.getValue(prop, slv);     //only gives 10% for w/e reason but the SkillStat is correct
                o2.rOption = LUNAR_TIDE;
                o2.tOption = 0;
                tsm.putCharacterStatValue(CriticalBuff, o2);
            } else {
                //Damage         MP is Greater than HP
                o1.nOption = 1;
                o1.rOption = LUNAR_TIDE;
                o1.tOption = 0;
                tsm.putCharacterStatValue(LifeTidal, o1);
                o2.nOption = lti.getValue(x, slv);
                o2.rOption = LUNAR_TIDE;
                o2.tOption = 0;
                tsm.putCharacterStatValue(DamR, o2);
            }
            tsm.sendSetStatPacket();
        }
    }

    public static void changeBlackBlessingCount(Client c, boolean increment) {
        Char chr = c.getChr();
        Option o = new Option();
        Option o2 = new Option();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        int amount = 1;
        if(tsm.hasStat(BlessOfDarkness)) {
            amount = tsm.getOption(BlessOfDarkness).nOption;

            if(increment) {
                if (amount < 3) {
                    amount++;
                }
            } else {
                if (amount > 0) {
                    amount--;
                }
            }
        }

        int orbmad;
        switch (amount) {
            case 1:
                orbmad = 15;
                break;
            case 2:
                orbmad = 24;
                break;
            case 3:
                orbmad = 30;
                break;
            default:
                orbmad = 0;
                break;
        }

        if(amount > 0) {
            o.nOption = amount;
            o.rOption = BLACK_BLESSING;
            o.tOption = 0;
            tsm.putCharacterStatValue(BlessOfDarkness, o);
            o2.nOption = orbmad;
            o2.rOption = BLACK_BLESSING;
            o2.tOption = 0;
            tsm.putCharacterStatValue(MAD, o2);
            tsm.sendSetStatPacket();
        } else {
            tsm.removeStatsBySkill(BLACK_BLESSING);
            tsm.sendResetStatPacket();
        }
    }

    public void changeMode() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        LarknessManager lm = tsm.getLarknessManager();
        Option o = new Option();
        o.nOption = 1;
        o.rOption = lm.isDark() ? ECLIPSE : SUNFIRE;
        tsm.putCharacterStatValue(Larkness, o);
        tsm.sendSetStatPacket();
    }

    public int getMoreEquilibriumTime() {
        int eqTime = 20;
        SkillInfo eqi = SkillData.getSkillInfoById(DARKNESS_MASTERY);
        if(chr.hasSkill(DARKNESS_MASTERY)) {
            eqTime += eqi.getValue(time, eqi.getCurrentLevel());
            eqTime += 5;
        }
        return eqTime;
    }



    // Attack related methods ------------------------------------------------------------------------------------------

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
        if(chr.getJob() != 2004) { // Beginner Luminous
            changeLarknessState(skillID);
        }
        int crescendoProp = getCrescendoProp();
        if (hasHitMobs) {
            if(!tsm.hasStat(Larkness)) {
                LarknessManager lm = tsm.getLarknessManager();
                Option o = new Option();
                o.nOption = 1;
                o.rOption = lm.isDark() ? ECLIPSE : SUNFIRE;
                tsm.putCharacterStatValue(Larkness, o);
                tsm.sendSetStatPacket();
            }
            // Dark Crescendo
            if (tsm.hasStat(StackBuff)) {
                if (skill != null && Util.succeedProp(crescendoProp)) {
                    incrementDarkCrescendo(tsm);
                }
            }
        }
        giveLunarTideBuff();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case ARMAGEDDON:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = skill.getSkillId();
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                break;
            case RAY_OF_REDEMPTION:
                chr.heal(chr.getMaxHP()); // 800% Recovery
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void incrementDarkCrescendo(TemporaryStatManager tsm) {
        Option o = new Option();
        Option o1 = new Option();
        SkillInfo crescendoInfo = SkillData.getSkillInfoById(DARK_CRESCENDO);
        Skill skill = chr.getSkill(DARK_CRESCENDO);
        byte slv = (byte) skill.getCurrentLevel();
        int amount = 1;
        if(tsm.hasStat(StackBuff)) {
            amount = tsm.getOption(StackBuff).mOption;
            if(amount < getMaxDarkCrescendoStack()) {
                amount++;
            }
        }
        o.setInMillis(true);
        o.nOption = (amount * crescendoInfo.getValue(damR, slv));
        o.rOption = DARK_CRESCENDO;
        o.tOption = (int) (darkCrescendoTimer - System.currentTimeMillis());
        o.mOption = amount;
        tsm.putCharacterStatValue(StackBuff, o);
        tsm.sendSetStatPacket();
    }

    private int getCrescendoProp() {
        Skill skill = null;
        if (chr.hasSkill(DARK_CRESCENDO)) {
            skill = chr.getSkill(DARK_CRESCENDO);
        }
        return skill == null ? 0 : SkillData.getSkillInfoById(DARK_CRESCENDO).getValue(prop, skill.getCurrentLevel());
    }

    private int getMaxDarkCrescendoStack() {
        Skill skill = null;
        if (chr.hasSkill(DARK_CRESCENDO)) {
            skill = chr.getSkill(DARK_CRESCENDO);
        }
        return skill == null ? 0 : SkillData.getSkillInfoById(skill.getSkillId()).getValue(x, skill.getCurrentLevel());
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }



    // Skill related methods -------------------------------------------------------------------------------------------

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
        super.handleSkill(c, skillID, slv, inPacket);
        Char chr = c.getChr();
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        LarknessManager lm = tsm.getLarknessManager();
        if(skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        chr.chatMessage(ChatType.Mob, "SkillID: " + skillID);
        if (isBuff(skillID)) {
            handleBuff(c, inPacket, skillID, slv);
        } else {
            Option o1 = new Option();
            Option o2 = new Option();
            Option o3 = new Option();
            switch(skillID) {
                case ECLIPSE:
                case SUNFIRE:
                case CHANGE_LIGHT_DARK:

                    lm.changeMode();
                    o1.nOption = 1;
                    o1.rOption = EQUILIBRIUM;
//                    o1.tOption = SkillData.getSkillInfoById(EQUILIBRIUM).getValue(time, 1);
                    tsm.putCharacterStatValue(Larkness, o1);
                    EventManager.addEvent(this::changeMode, getMoreEquilibriumTime(), TimeUnit.SECONDS);
                    break;
                case HEROS_WILL_LUMI:
                    tsm.removeAllDebuffs();
                    break;
            }
            tsm.sendSetStatPacket();
        }
    }



    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        if(tsm.getOption(Larkness).rOption == EQUILIBRIUM) {
            return;
        } else {
            if (tsm.hasStat(BlessOfDarkness) && chr.hasSkill(BLACK_BLESSING) && hitInfo.hpDamage > 0) {
                Skill skill = chr.getSkill(BLACK_BLESSING);
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                byte slv = (byte) skill.getCurrentLevel();
                changeBlackBlessingCount(c, false); // deduct orbs as player gets hit
                int dmgAbsorbed = si.getValue(x, slv);
                hitInfo.hpDamage = (int) (hitInfo.hpDamage * ((double) dmgAbsorbed / 100));
            }
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void handleMobDebuffSkill(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(tsm.hasStat(AntiMagicShell)) {
            tsm.removeAllDebuffs();
            deductShadowShell();
        }

    }

    private void deductShadowShell() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(!chr.hasSkill(SHADOW_SHELL)) {
            return;
        }
        Skill skill = chr.getSkill(SHADOW_SHELL);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();
        Option o = new Option();
        if (tsm.hasStat(AntiMagicShell)) {
            int shadowShellCount = tsm.getOption(AntiMagicShell).nOption;

            if(shadowShellCount > 0) {
                shadowShellCount--;
            }

            if(shadowShellCount <= 0) {
                tsm.removeStatsBySkill(skill.getSkillId());
                tsm.sendResetStatPacket();
            } else {
                o.nOption = shadowShellCount;
                o.rOption = skill.getSkillId();
                o.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AntiMagicShell, o);
                tsm.sendSetStatPacket();
            }
            chr.write(User.effect(Effect.skillSpecial(skill.getSkillId())));
            chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillSpecial(skill.getSkillId())));
        }
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        chr.getAvatarData().getCharacterStat().setPosMap(927020080);
    }
}