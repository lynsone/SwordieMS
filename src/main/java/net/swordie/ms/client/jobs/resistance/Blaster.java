package net.swordie.ms.client.jobs.resistance;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.HitInfo;
import net.swordie.ms.client.character.skills.*;
import net.swordie.ms.client.field.Field;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.life.AffectedArea;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ChatMsgColour;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.connection.packet.WvsContext;

import java.util.Arrays;

import static net.swordie.ms.client.character.skills.CharacterTemporaryStat.*;
import static net.swordie.ms.client.character.skills.CharacterTemporaryStat.IndieMaxDamageOverR;
import static net.swordie.ms.client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class Blaster extends Job {

    public static final int SECRET_ASSEMBLY = 30001281;

    public static final int HAMMER_SMASH = 37111000;
    public static final int HAMMER_SMASH_CHARGE = 37110001;
    public static final int ARM_CANNON_BOOST = 37101003;
    public static final int MAPLE_WARRIOR_BLASTER = 37121006;
    public static final int HEROS_WILL_BLASTER = 37121007;
    public static final int FOR_LIBERTY_BLASTER = 37121053;
    public static final int CANNON_OVERDRIVE = 37121054;


    //Revolving Cannon
    public static final int REVOLVING_CANNON_RELOAD = 37000010;
    public static final int REVOLVING_CANNON = 37001001;
    public static final int REVOLVING_CANNON_2 = 37100008;
    public static final int REVOLVING_CANNON_3 = 37000009;

    public static final int REVOLVING_CANNON_PLUS = 37100007;
    public static final int REVOLVING_CANNON_PLUS_II = 37110007;
    public static final int REVOLVING_CANNON_PLUS_III = 37120008;

    public static final int BUNKER_BUSTER_EXPLOSION_3 = 37001002;
    public static final int BUNKER_BUSTER_EXPLOSION_4 = 37000011;
    public static final int BUNKER_BUSTER_EXPLOSION_5 = 37000012;
    public static final int BUNKER_BUSTER_EXPLOSION_6 = 37000013;


    //Blast Shield
    public static final int BLAST_SHIELD = 37000006;
    public static final int SHIELD_TRAINING = 37110008;
    public static final int SHIELD_TRAINING_II = 37120009;
    public static final int VITALITY_SHIELD = 37121005;


    //Combo Training
    public static final int COMBO_TRAINING = 37110009;
    public static final int COMBO_TRAINING_II = 37120012;



    private int[] addedSkills = new int[] {
            SECRET_ASSEMBLY,
    };

    private int[] buffs = new int[] {
            ARM_CANNON_BOOST,
            MAPLE_WARRIOR_BLASTER,
            FOR_LIBERTY_BLASTER,
            CANNON_OVERDRIVE,
    };

    private int gauge = 0;
    private int ammo = 0;
    private int lastAttack = 0;

    public Blaster(Char chr) {
        super(chr);
        if(isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            entranceCylinderState();
        }
    }

    public static int getOriginalSkill(int skillID) {
        switch(skillID) {
            case REVOLVING_CANNON_2:
            case REVOLVING_CANNON_3:
                return REVOLVING_CANNON;
        }
        return skillID; // no original skill linked with this one
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
        handleComboTraining(skillID, tsm, c);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case HAMMER_SMASH_CHARGE:
                SkillInfo hmc = SkillData.getSkillInfoById(HAMMER_SMASH);
                AffectedArea hmci = AffectedArea.getPassiveAA(chr, HAMMER_SMASH, (byte) slv);
                hmci.setMobOrigin((byte) 0);
                hmci.setPosition(chr.getPosition());
                hmci.setRect(hmci.getPosition().getRectAround(hmc.getRects().get(0)));
                hmci.setDelay((short) 5);
                chr.getField().spawnAffectedArea(hmci);
                break;
            case BUNKER_BUSTER_EXPLOSION_3:
            case BUNKER_BUSTER_EXPLOSION_4:
            case BUNKER_BUSTER_EXPLOSION_5:
            case BUNKER_BUSTER_EXPLOSION_6:

                break;
        }
    }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
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
            switch(skillID) {
                case SECRET_ASSEMBLY:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = c.getChannelInstance().getField(o1.nValue);
                    chr.warp(toField);
                    break;
                case REVOLVING_CANNON_RELOAD:
                    handleCylinderReload();
                    break;
                case REVOLVING_CANNON_3:
                case REVOLVING_CANNON_2:
                //case REVOLVING_CANNON:
                    handleAmmoCost();
                    handleGaugeIncrease();
                    c.write(UserLocal.onRWMultiChargeCancelRequest((byte)1, skillID));
                    break;
                case VITALITY_SHIELD:
                    resetBlastShield();
                    break;
                case HEROS_WILL_BLASTER:
                    tsm.removeAllDebuffs();
                    break;
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Option o1 = new Option();
        if(chr.hasSkill(BLAST_SHIELD)) {
            SkillInfo si = SkillData.getSkillInfoById(BLAST_SHIELD);
            o.nOption = 1;
            o.rOption = BLAST_SHIELD;
            o.tOption = 3;
            tsm.putCharacterStatValue(RWBarrier, o);
            c.write(WvsContext.temporaryStatSet(tsm));
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    public void resetBlastShield() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStat(RWBarrier, false);
        c.write(WvsContext.temporaryStatReset(tsm, false));
    }

    public boolean isBuff(int skillID) {
        return Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    private void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case ARM_CANNON_BOOST:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case MAPLE_WARRIOR_BLASTER:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;

            case FOR_LIBERTY_BLASTER:
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

            case CANNON_OVERDRIVE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(RWMaximizeCannon, o1);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isBlaster(id);
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    public void entranceCylinderState() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = 1;
        o.bOption = getMaxAmmo(); //ammo
        o.cOption = 0; //gauge
        tsm.putCharacterStatValue(RWCylinder, o);
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    public void handleCylinderReload() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        ammo = tsm.getOption(RWCylinder).bOption;
        gauge = tsm.getOption(RWCylinder).cOption;
        Option o = new Option();
        o.nOption = 1;
        o.bOption = getMaxAmmo(); //ammo
        o.cOption = gauge; //gauge
        tsm.putCharacterStatValue(RWCylinder, o);
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    public void handleAmmoCost() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        ammo = tsm.getOption(RWCylinder).bOption;
        gauge = tsm.getOption(RWCylinder).cOption;
        Option o = new Option();
        if (ammo > 0) {
            o.nOption = 1;
            o.bOption = ammo - 1; //ammo
            o.cOption = gauge;
            tsm.putCharacterStatValue(RWCylinder, o);
            c.write(WvsContext.temporaryStatSet(tsm));
        }
    }

    public void handleGaugeIncrease() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        ammo = tsm.getOption(RWCylinder).bOption;
        gauge = tsm.getOption(RWCylinder).cOption;
        Option o = new Option();
        if (gauge < getMaxAmmo()) {
            o.nOption = 1;
            o.cOption = gauge + 1; //gauge
            o.bOption = ammo;
            tsm.putCharacterStatValue(RWCylinder, o);
            c.write(WvsContext.temporaryStatSet(tsm));
        }
    }

    public int getMaxAmmo() {
        int maxAmmo = 3;
        if(chr.hasSkill(REVOLVING_CANNON_PLUS)) {
            maxAmmo = 4;
        }
        if(chr.hasSkill(REVOLVING_CANNON_PLUS_II)) {
            maxAmmo = 5;
        }
        if(chr.hasSkill(REVOLVING_CANNON_PLUS_III)) {
            maxAmmo = 6;
        }
        return maxAmmo;
    }

    private void handleComboTraining(int skillId, TemporaryStatManager tsm, Client c) {
        Option o = new Option();
        SkillInfo chargeInfo = SkillData.getSkillInfoById(COMBO_TRAINING);
        int amount = 1;
        if(tsm.hasStat(RWCombination)) {
            amount = tsm.getOption(RWCombination).nOption;
            if (lastAttack == skillId) {
                return;
            }
            if(amount < chargeInfo.getValue(z, 1)) {
                amount++;
            }
        }
        lastAttack = skillId;
        o.nOption = amount;
        o.rOption = COMBO_TRAINING;
        o.tOption = 10;
        tsm.putCharacterStatValue(RWCombination, o);
        c.write(WvsContext.temporaryStatSet(tsm));
    }

}