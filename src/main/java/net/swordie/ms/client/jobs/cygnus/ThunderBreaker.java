package net.swordie.ms.client.jobs.cygnus;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.*;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatBase;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.enums.TSIndex;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ChatMsgColour;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.util.Util;

import java.util.Arrays;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class ThunderBreaker extends Job {

    public static final int IMPERIAL_RECALL = 10001245;
    public static final int ELEMENTAL_EXPERT = 10000250;
    public static final int ELEMENTAL_SLASH = 10001244;
    public static final int NOBLE_MIND = 10000202;
    public static final int ELEMENTAL_SHIFT = 10001254;
    public static final int ELEMENTAL_SHIFT2 = 10000252;
    public static final int ELEMENTAL_HARMONY_STR = 10000246;

    public static final int LIGHTNING_ELEMENTAL = 15001022; //Buff (Charge) //Stackable Charge

    public static final int KNUCKLE_BOOSTER = 15101022; //Buff

    public static final int GALE = 15111022; //Special Attack (Charge)
    public static final int LINK_MASTERY = 15110025; //Special Passive TODO  (Activates upon linking Attack skills, gains Icon)

    public static final int ARC_CHARGER = 15121004; //Buff
    public static final int SPEED_INFUSION = 15121005; //Buff
    public static final int CALL_OF_CYGNUS_TB = 15121000; //Buff
    public static final int TYPHOON = 15120003;

    public static final int GLORY_OF_THE_GUARDIANS_TB = 15121053;
    public static final int PRIMAL_BOLT = 15121054;

    private int[] addedSkills = new int[] {
            ELEMENTAL_HARMONY_STR,
            IMPERIAL_RECALL,
            ELEMENTAL_EXPERT,
            ELEMENTAL_SLASH,
            NOBLE_MIND,
            ELEMENTAL_SHIFT,
            ELEMENTAL_SHIFT2
    };

    private int[] buffs = new int[] {
            LIGHTNING_ELEMENTAL,
            KNUCKLE_BOOSTER,
            LINK_MASTERY,
            ARC_CHARGER,
            SPEED_INFUSION,
            CALL_OF_CYGNUS_TB,
            GLORY_OF_THE_GUARDIANS_TB,
            PRIMAL_BOLT,
    };

    private int lastAttackSkill = 0;

    public ThunderBreaker(Char chr) {
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
            case LIGHTNING_ELEMENTAL:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CygnusElementSkill, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IgnoreMobpdpR, o2);
                break;
            case KNUCKLE_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case ARC_CHARGER: //TODO   y = - y seconds Cooltime per Lightning Buff
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ShadowPartner, o1);
                break;
            case SPEED_INFUSION:
                TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.PartyBooster);
                tsb.setNOption(-1);
                tsb.setROption(skillID);
                tsb.setExpireTerm(1);
                tsm.putCharacterStatValue(PartyBooster, tsb.getOption());
                break;
            case CALL_OF_CYGNUS_TB:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;

            case LINK_MASTERY:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamR, o1);
                break;

            case GLORY_OF_THE_GUARDIANS_TB:
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

            case PRIMAL_BOLT: //TODO
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(StrikerHyperElectric, o1);

                o2.nReason = skillID;
                o2.nValue = si.getValue(indieDamR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o2);
                break;

        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private void handleLinkMastery(int skillId, TemporaryStatManager tsm, Client c) {
        Option o = new Option();
        SkillInfo linkInfo = SkillData.getSkillInfoById(15110025);
        if (lastAttackSkill == skillId) {
            return;
        } else {
            lastAttackSkill = skillId;
            o.nOption = linkInfo.getValue(x, linkInfo.getCurrentLevel());
            o.rOption = 15110025;
            o.tOption = 10;
            tsm.putCharacterStatValue(DamR, o);
            c.write(WvsContext.temporaryStatSet(tsm));
        }
    }

    private void handleLightning(TemporaryStatManager tsm) {
        Option o = new Option();
        Skill skill = chr.getSkill(LIGHTNING_ELEMENTAL);
        SkillInfo leInfo = SkillData.getSkillInfoById(skill.getSkillId());
        SkillInfo pbInfo = SkillData.getSkillInfoById(PRIMAL_BOLT);
        byte slv = (byte) skill.getCurrentLevel();
        int amount = 1;
        if(tsm.hasStat(IgnoreTargetDEF)) {
            amount = tsm.getOption(IgnoreTargetDEF).mOption;
            if(amount < getMaxCharge()) {
                amount++;
            }
        }
        o.nOption = (tsm.hasStat(StrikerHyperElectric) ? (pbInfo.getValue(x, slv)) : (leInfo.getValue(x, slv))) * amount;
        o.mOption = amount;
        o.rOption = LIGHTNING_ELEMENTAL;
        o.tOption = leInfo.getValue(y, leInfo.getCurrentLevel());
        tsm.putCharacterStatValue(IgnoreTargetDEF, o);
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private int getChargeProp() {
        int prop = 0;
        if (chr.hasSkill(15001022)) { //Lightning Elemental
            prop += 10;
        }
        if (chr.hasSkill(15000023)) { //Electrified
            prop += 20;
        }
        if (chr.hasSkill(15100025)) { //Lightning Boost
            prop += 20;
        }
        if (chr.hasSkill(15110026)) { //Light Lord
            prop += 30;
        }
        if (chr.hasSkill(15120008)) { //Thunder God
            prop += 20;
        }
        return prop;
    }

    private int getMaxCharge() {
        int num = 0;
        if (chr.hasSkill(15001022)) { //Lightning Elemental
            num += 1;
        }
        if (chr.hasSkill(15000023)) { //Electrified
            num += 1;
        }
        if (chr.hasSkill(15100025)) { //Lightning Boost
            num += 1;
        }
        if (chr.hasSkill(15110026)) { //Light Lord
            num += 1;
        }
        if (chr.hasSkill(15120008)) { //Thunder God
            num += 1;
        }
        return num;
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
        int slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        int chargeProp = getChargeProp();
        if (tsm.hasStat(CygnusElementSkill)) {
            if (hasHitMobs && Util.succeedProp(chargeProp)) {
                handleLightning(tsm);
            }
        }
        if(chr.hasSkill(15110025)) {
            if (hasHitMobs) {
                handleLinkMastery(skill.getSkillId(), tsm, c);
            }
        }
        chr.chatMessage(ChatMsgColour.GUILD_PURPLE, "Attack Speed: "+attackInfo.attackSpeed);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case GALE:
            case TYPHOON: //TODO
                int chargeStack = tsm.getOption(IgnoreTargetDEF).mOption;
                if((tsm.getOptByCTSAndSkill(IndieDamR, GALE) == null) || (tsm.getOptByCTSAndSkill(IndieDamR, TYPHOON) == null)) {
                    o1.nReason = skillID;
                    o1.nValue = chargeStack * si.getValue(y, slv);
                    o1.tStart = (int) System.currentTimeMillis();
                    o1.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(IndieDamR, o1); //Indie
                    c.write(WvsContext.temporaryStatSet(tsm));
                }
                break;
        }
    }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
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
                case IMPERIAL_RECALL:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = chr.getOrCreateFieldByCurrentInstanceType(o1.nValue);
                    chr.warp(toField);
                    break;
            }
        }
    }
    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isThunderBreaker(id);
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }
}
