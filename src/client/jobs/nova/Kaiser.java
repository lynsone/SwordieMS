package client.jobs.nova;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.items.Item;
import client.character.skills.*;
import client.field.Field;
import client.jobs.Job;
import client.life.AffectedArea;
import client.life.Mob;
import client.life.MobTemporaryStat;
import client.life.Summon;
import connection.InPacket;
import constants.JobConstants;
import constants.SkillConstants;
import enums.ChatMsgColour;
import enums.MobStat;
import enums.MoveAbility;
import loaders.SkillData;
import packet.WvsContext;
import util.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class Kaiser extends Job {

    public static final int VERTICAL_GRAPPLE = 60001218;
    public static final int TRANSFIGURATION = 60000219; //Morph Gauge (SmashStack)
    public static final int DRAGON_LINK = 60001225;

    public static final int TEMPEST_BLADES_THREE = 61101002;    //TODO Special Buff (w/ Icon) 3
    public static final int TEMPEST_BLADES_THREE_FF = 61110211;
    public static final int BLAZE_ON = 61101004; //Buff

    public static final int FINAL_FORM_THIRD = 61111008; //Buff 3rd Job
    public static final int STONE_DRAGON = 61111002; //Summon (Speed Debuff)
    public static final int STONE_DRAGON_FINAL_FORM = 61111220; //Summon (Speed Debuff)
    public static final int CURSEBITE = 61111003; //Buff

    public static final int FINAL_FORM_FOURTH = 61120008; //Buff 4rd Job
    public static final int TEMPEST_BLADES_FIVE = 61120007;     //TODO  Special Buff (w/ Icon) 5
    public static final int TEMPEST_BLADES_FIVE_FF = 61121217;
    public static final int GRAND_ARMOR = 61121009; //Buff
    public static final int NOVA_WARRIOR_KAISER = 61121014; //Buff
    public static final int NOVA_TEMPERANCE_KAISER = 61121015;

    public static final int FINAL_TRANCE = 61121053;
    public static final int KAISERS_MAJESTY = 61121054;


    //Attacking Skills
    public static final int DRAGON_SLASH_1 = 61001000; //First Swing
    public static final int DRAGON_SLASH_2 = 61001004; //2nd Swing
    public static final int DRAGON_SLASH_3 = 61001005; //Last Swing
    public static final int DRAGON_SLASH_1_FINAL_FORM = 61120219; //Swing Final Form

    public static final int FLAME_SURGE = 61001101;
    public static final int FLAME_SURGE_FINAL_FORM = 61111215;


    public static final int IMPACT_WAVE = 61101100;
    public static final int IMPACT_WAVE_FINAL_FORM = 61111216;
    public static final int PIERCING_BLAZE = 61101101; //Special Attack (Stun Debuff)
    public static final int PIERCING_BLAZE_FINAL_FORM = 61111217;


    public static final int WING_BEAT = 61111100; //Special Attack (Speed Debuff)
    public static final int WING_BEAT_FINAL_FORM = 61111111;
    public static final int PRESSURE_CHAIN = 61111101; //Special Attack (Stun Debuff)
    public static final int PRESSURE_CHAIN_FINAL_FORM = 61111219;


    public static final int GIGA_WAVE = 61121100; //Special Attack (Speed Debuff)
    public static final int GIGA_WAVE_FINAL_FORM = 61121201;
    public static final int INFERNO_BREATH = 61121105;
    public static final int INFERNO_BREATH_FINAL_FORM = 61121222;
    public static final int DRAGON_BARRAGE = 61121102;
    public static final int DRAGON_BARRAGE_FINAL_FORM = 61121203;
    public static final int BLADE_BURST = 61121104;
    public static final int BLADE_BURST_FINAL_FORM = 61121221;


    //Realign Skills        //TODO
    public static final int REALIGN_ATTACKER_MODE = 60001217; //Unlimited Duration
    public static final int REALIGN_DEFENDER_MODE = 60001216; //Unlimited Duration

    public static final int REALIGN_ATTACKER_MODE_I = 61100008;
    public static final int REALIGN_DEFENDER_MODE_I = 61100005;

    public static final int REALIGN_ATTACKER_MODE_II = 61110010;
    public static final int REALIGN_DEFENDER_MODE_II = 61110005;

    public static final int REALIGN_ATTACKER_MODE_III = 61120013;
    public static final int REALIGN_DEFENDER_MODE_III = 61120010;

    private final int[] addedSkills = new int[]{
            REALIGN_ATTACKER_MODE,
            REALIGN_DEFENDER_MODE,
            VERTICAL_GRAPPLE,
            TRANSFIGURATION,
            DRAGON_LINK,
    };

    private final int[] buffs = new int[]{
            REALIGN_ATTACKER_MODE,
            REALIGN_DEFENDER_MODE,
            TEMPEST_BLADES_THREE,
            TEMPEST_BLADES_THREE_FF,
            BLAZE_ON,
            FINAL_FORM_THIRD,
            STONE_DRAGON,
            STONE_DRAGON_FINAL_FORM,
            CURSEBITE,
            FINAL_FORM_FOURTH,
            TEMPEST_BLADES_FIVE,
            TEMPEST_BLADES_FIVE_FF,
            GRAND_ARMOR,
            NOVA_WARRIOR_KAISER,
            FINAL_TRANCE,
            KAISERS_MAJESTY,
    };

    public Kaiser(Char chr) {
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
        Option o4 = new Option();
        Option o5 = new Option();
        Option o6 = new Option();
        Summon summon;
        Field field;
        Item item = chr.getEquippedInventory().getItemBySlot((short) 11);
        int weaponID = item.getItemId();
        switch (skillID) {
            case REALIGN_ATTACKER_MODE:
                handleRealignAttackSkill();
                break;
            case REALIGN_DEFENDER_MODE:
                handleRealignDefendSkill();
                break;
            case BLAZE_ON:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case CURSEBITE:
                o1.nOption = si.getValue(asrR, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o1);
                o2.nOption = si.getValue(terR, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(TerR, o2);
                break;
            case GRAND_ARMOR:
                // w = party dmg taken  v = self dmg taken
                o1.nOption = si.getValue(w, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamageReduce, o1); //TODO DamageReduce Party-wide
                o2.nOption = si.getValue(v, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamageReduce, o2);
                break;
            case NOVA_WARRIOR_KAISER:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case TEMPEST_BLADES_THREE:
            case TEMPEST_BLADES_THREE_FF:
                o1.nOption = 5;
                o1.rOption = skillID;
                o1.tOption = 0;
                StopForceAtom stopForceAtom2 = new StopForceAtom();
                List<Integer> angles = Arrays.asList(0, 0, 0);
                stopForceAtom2.setCount(3);
                stopForceAtom2.setIdx(0);
                stopForceAtom2.setWeaponId(weaponID);
                stopForceAtom2.setAngleInfo(angles);
                tsm.setStopForceAtom(stopForceAtom2);
                tsm.putCharacterStatValue(StopForceAtomInfo, o1);
                break;
            case TEMPEST_BLADES_FIVE:
            case TEMPEST_BLADES_FIVE_FF:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                StopForceAtom stopForceAtom = new StopForceAtom();
                List<Integer> angles2 = Arrays.asList(0, 0, 0, 0, 0);
                stopForceAtom.setCount(5);
                stopForceAtom.setIdx(0);
                stopForceAtom.setWeaponId(weaponID);
                stopForceAtom.setAngleInfo(angles2);
                tsm.setStopForceAtom(stopForceAtom);
                tsm.putCharacterStatValue(StopForceAtomInfo, o1);
                break;

            case FINAL_FORM_THIRD:
                o6.nOption = 1200;
                o6.rOption = skillID;
                o6.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Morph, o6);
                o1.nOption = si.getValue(cr, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CriticalBuff, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePMdR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePMdR, o2);
                o3.nOption = si.getValue(jump, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o3);
                o4.nOption = si.getValue(prop, slv);
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o4);
                o5.nOption = si.getValue(speed, slv);
                o5.rOption = skillID;
                o5.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o5);
                resetGauge(c, tsm);
                break;

            case FINAL_TRANCE:
            case FINAL_FORM_FOURTH:
                o6.nOption = 1201;
                o6.rOption = skillID;
                o6.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Morph, o6);
                o1.nOption = si.getValue(cr, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CriticalBuff, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePMdR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePMdR, o2);
                o3.nOption = si.getValue(jump, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o3);
                o4.nOption = si.getValue(prop, slv);
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o4);
                o5.nOption = si.getValue(speed, slv);
                o5.rOption = skillID;
                o5.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o5);
                resetGauge(c, tsm);
                break;

            case KAISERS_MAJESTY:   //Lowers Range for w/e reason
                o1.nReason = skillID;
                o1.nValue = -1;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                o2.nReason = skillID;
                o2.nValue = (-1 * si.getValue(indiePad, slv));
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o2);
                //TODO Cooldown Reset
                break;
            case STONE_DRAGON:
            case STONE_DRAGON_FINAL_FORM:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAction(MoveAbility.STATIC.getVal());
                summon.setMoveAbility((byte) 0);
                field.spawnSummon(summon);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private int getGaugeIncrement(int skillID) {
        HashMap<Integer, Integer> hashMapIncrement = new HashMap<>();
        hashMapIncrement.put(DRAGON_SLASH_1, 1);
        hashMapIncrement.put(DRAGON_SLASH_2, 3);
        hashMapIncrement.put(DRAGON_SLASH_3, 4);
        hashMapIncrement.put(DRAGON_SLASH_1_FINAL_FORM, 1);

        hashMapIncrement.put(FLAME_SURGE, 2);
        hashMapIncrement.put(FLAME_SURGE_FINAL_FORM, 2);

        hashMapIncrement.put(IMPACT_WAVE, 5);
        hashMapIncrement.put(IMPACT_WAVE_FINAL_FORM, 0);

        hashMapIncrement.put(PIERCING_BLAZE, 5);
        hashMapIncrement.put(PIERCING_BLAZE_FINAL_FORM, 0);

        hashMapIncrement.put(WING_BEAT, 2);
        hashMapIncrement.put(WING_BEAT_FINAL_FORM, 1);

        hashMapIncrement.put(PRESSURE_CHAIN, 8);
        hashMapIncrement.put(PRESSURE_CHAIN_FINAL_FORM, 0);

        hashMapIncrement.put(GIGA_WAVE, 8);
        hashMapIncrement.put(GIGA_WAVE_FINAL_FORM, 0);

        hashMapIncrement.put(INFERNO_BREATH, 14);
        hashMapIncrement.put(INFERNO_BREATH_FINAL_FORM, 0);

        hashMapIncrement.put(DRAGON_BARRAGE, 6);
        hashMapIncrement.put(DRAGON_BARRAGE_FINAL_FORM, 0);

        hashMapIncrement.put(BLADE_BURST, 6);
        hashMapIncrement.put(BLADE_BURST_FINAL_FORM, 0);

        return hashMapIncrement.get(skillID);
    }

    private void handleMorphGauge(int skillId, TemporaryStatManager tsm, Client c, int increment) {
        Option o = new Option();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        SkillInfo gaugeInfo = SkillData.getSkillInfoById(60000219);
        int amount = 1;
        int stage = 0;
        if(chr.hasSkill(60000219)) {
            amount = tsm.getOption(SmashStack).nOption;
                if (amount <= (getKaiserGauge(chr))) {
                    if(amount + increment > getKaiserGauge(chr)) {
                        amount = getKaiserGauge(chr);
                    } else {
                        amount = tsm.getOption(SmashStack).nOption + increment;
                    }
                }
                if (amount >= gaugeInfo.getValue(s, 1)) {
                    stage = 1;
                }
                if (amount >= (gaugeInfo.getValue(v, 1)) - 1) {
                    stage = 2;
                }
        }
        o.nOption = amount;
        o.rOption = 0;
        o.tOption = 0;
        tsm.putCharacterStatValue(SmashStack, o);
        o1.nOption = (stage * gaugeInfo.getValue(prop, 1));
        o1.rOption = 0;
        o1.tOption = 0;
        tsm.putCharacterStatValue(Stance, o1);
        o2.nOption = (stage * gaugeInfo.getValue(psdJump, 1));
        o2.rOption = 0;
        o2.tOption = 0;
        tsm.putCharacterStatValue(Jump, o2);
        o3.nOption = (stage * gaugeInfo.getValue(psdSpeed, 1));
        o3.rOption = 0;
        o3.tOption = 0;
        tsm.putCharacterStatValue(Speed, o3);
        o4.nReason = 0;
        o4.nValue = (stage * gaugeInfo.getValue(actionSpeed, 1));
        o4.tStart = (int) System.currentTimeMillis();
        o4.tTerm = 0;
        tsm.putCharacterStatValue(IndieBooster, o4); //Indie
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private void resetGauge(Client c, TemporaryStatManager tsm) {
        tsm.removeStat(SmashStack, false);
        c.write(WvsContext.temporaryStatReset(tsm, false));
    }

    private int getKaiserGauge(Char chr) {
        int maxGauge;
        switch (chr.getJob()) {
            case 6100:
                maxGauge = SkillData.getSkillInfoById(60000219).getValue(s, 1);
                break;
            case 6110:
                maxGauge = SkillData.getSkillInfoById(60000219).getValue(u, 1);
                break;
            case 6111:
            case 6112:
                maxGauge = SkillData.getSkillInfoById(60000219).getValue(v, 1);
                break;
            default:
                maxGauge = 0;
        }
        return maxGauge;
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
        if(hasHitMobs) {
            handleMorphGauge(SkillConstants.getActualSkillIDfromSkillID(skillID), tsm, c, getGaugeIncrement(attackInfo.skillId));
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case PIERCING_BLAZE:
                SkillInfo pbi = SkillData.getSkillInfoById(PIERCING_BLAZE);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(pbi.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skillID;
                        o1.tOption = pbi.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case PIERCING_BLAZE_FINAL_FORM:
                SkillInfo pbffi = SkillData.getSkillInfoById(PIERCING_BLAZE_FINAL_FORM);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = pbffi.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                break;
            case WING_BEAT:
                SkillInfo wbi = SkillData.getSkillInfoById(WING_BEAT);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(wbi.getValue(prop, slv))) {     //TODO creates NPE
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skillID;
                        o1.tOption = 5;
                        mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                    }
                }
                break;
            case WING_BEAT_FINAL_FORM:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = 5;
                    mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                }
                break;
            case PRESSURE_CHAIN:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skillID;
                        o1.tOption = 3;
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case PRESSURE_CHAIN_FINAL_FORM:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = 3;
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                break;
            case GIGA_WAVE:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = -30;
                        o1.rOption = skillID;
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                    }
                }
                break;
            case GIGA_WAVE_FINAL_FORM:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = -30;
                    o1.rOption = GIGA_WAVE;
                    o1.tOption = 4;
                    mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                }
                break;

            case STONE_DRAGON:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skillID;
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                    }
                }
                break;
            case STONE_DRAGON_FINAL_FORM:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skillID;
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                    }
                }
                break;

            case INFERNO_BREATH:
            case INFERNO_BREATH_FINAL_FORM:
                SkillInfo rca = SkillData.getSkillInfoById(INFERNO_BREATH);
                AffectedArea aa = AffectedArea.getAffectedArea(attackInfo);
                aa.setMobOrigin((byte) 0);
                aa.setCharID(chr.getId());
                aa.setSkillID(INFERNO_BREATH);
                aa.setPosition(chr.getPosition());
                aa.setRect(aa.getPosition().getRectAround(rca.getRects().get(0)));
                aa.setDelay((short) 7); //spawn delay
                chr.getField().spawnAffectedArea(aa);
                break;
        }
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
                case NOVA_TEMPERANCE_KAISER:
                    tsm.removeAllDebuffs();
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
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById(id);
        switch (job) {
            case KAISER:
            case KAISER1:
            case KAISER2:
            case KAISER3:
            case KAISER4:
                return true;
            default:
                return false;
        }
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    public void handleRealignAttackSkill() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        int[] realignattack = new int[] {
                REALIGN_ATTACKER_MODE,
                REALIGN_ATTACKER_MODE_I,
                REALIGN_ATTACKER_MODE_II,
                REALIGN_ATTACKER_MODE_III,
        };
        int zPadX = 0;
        int zCr = 0;
        int zBdR = 0;
        for(int i = 0; i<realignattack.length; i++) {
            if (chr.hasSkill(realignattack[i])) {
                Skill skill = chr.getSkill(realignattack[i]);
                byte slv = (byte) skill.getCurrentLevel();
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                zPadX += si.getValue(padX, slv);
                zCr += si.getValue(cr, slv);
                zBdR += si.getValue(bdR, slv);
            }
        }
        o1.nOption = zPadX;
        o1.rOption = REALIGN_ATTACKER_MODE;
        tsm.putCharacterStatValue(PAD, o1);
        o2.nOption = zCr;
        o2.rOption = REALIGN_ATTACKER_MODE;
        tsm.putCharacterStatValue(CriticalBuff, o2);
        o3.nOption = zBdR;
        o3.rOption = REALIGN_ATTACKER_MODE;
        tsm.putCharacterStatValue(HayatoBoss, o3);
        tsm.sendSetStatPacket();
    }

    public void handleRealignDefendSkill() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        int[] realigndefend = new int[] {
                REALIGN_DEFENDER_MODE,
                REALIGN_DEFENDER_MODE_I,
                REALIGN_DEFENDER_MODE_II,
                REALIGN_DEFENDER_MODE_III,
        };
        int zDef = 0;
        int zAcc = 0;
        int zMHPR = 0;
        for(int i = 0; i<realigndefend.length; i++) {
            if (chr.hasSkill(realigndefend[i])) {
                Skill skill = chr.getSkill(realigndefend[i]);
                byte slv = (byte) skill.getCurrentLevel();
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                zDef += si.getValue(pddX, slv);
                zAcc += si.getValue(accX, slv);
                zMHPR += si.getValue(mhpR, slv);
            }
        }
        o1.nOption = zDef;
        o1.rOption = REALIGN_DEFENDER_MODE;
        tsm.putCharacterStatValue(PDD, o1);
        tsm.putCharacterStatValue(MDD, o1);
        o2.nOption = zAcc;
        o2.rOption = REALIGN_DEFENDER_MODE;
        tsm.putCharacterStatValue(ACC, o2);
        o3.nOption = zMHPR;
        o3.rOption = REALIGN_DEFENDER_MODE;
        tsm.putCharacterStatValue(HayatoHPR, o3);
        tsm.sendSetStatPacket();
    }
}