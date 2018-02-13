package client.jobs.resistance;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.field.Field;
import client.jobs.Job;
import client.life.Mob;
import client.life.MobTemporaryStat;
import client.life.Summon;
import connection.InPacket;
import constants.JobConstants;
import enums.ChatMsgColour;
import enums.ForceAtomEnum;
import enums.MobStat;
import loaders.SkillData;
import packet.CField;
import packet.WvsContext;
import server.EventManager;
import util.Position;
import util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class Xenon extends Job {
    public static final int SUPPLY_SURPLUS = 30020232;
    public static final int MULTILATERAL_I = 30020234;
    public static final int MODAL_SHIFT = 30021236;
    public static final int LIBERTY_BOOSTERS = 30021236;
    public static final int MIMIC_PROTOCOL = 30020240;
    public static final int PROMESSA_ESCAPE = 30021235;

    public static final int CIRCUIT_SURGE = 36001002; //Buff
    public static final int PINPOINT_SALVO = 36001005; //Special Attack

    public static final int XENON_BOOSTER = 36101004; //Buff
    public static final int EFFICIENCY_STREAMLINE = 36101003; //Buff
    public static final int ION_THRUST = 36101001; //Special Attack
    public static final int PINPOINT_SALVO_REDESIGN_A = 36100010; //Special Attack Upgrade  (Passive Upgrade)

    public static final int HYBRID_DEFENSES = 36111003; //Buff
    public static final int AEGIS_SYSTEM = 36111004; //Special Buff (ON/OFF)
    public static final int AEGIS_SYSTEM_ATOM = 36110004; //Special Buff (ON/OFF)
    public static final int MANIFEST_PROJECTOR = 36111006; //Special Buff (Special Duration)
    public static final int EMERGENCY_RESUPPLY = 36111008; //Special Skill
    public static final int PINPOINT_SALVO_REDESIGN_B = 36110012; //Special Attack Upgrade  (Passive Upgrade)

    public static final int HYPOGRAM_FIELD_FORCE_FIELD = 36121002;                  //TODO Summon/Area of Effect?
    public static final int HYPOGRAM_FIELD_PENETRATE = 36121013;                    //TODO Summon/Area of Effect?
    public static final int HYPOGRAM_FIELD_SUPPORT = 36121014;                      //TODO Summon/Area of Effect?
    public static final int TEMPORAL_POD = 36121007;                                //TODO Area of Effect
    public static final int OOPARTS_CODE = 36121003; //Buff
    public static final int MAPLE_WARRIOR_XENON = 36121008; //Buff
    public static final int PINPOINT_SALVO_PERFECT_DESIGN = 36120015; //Sp. Attack Upgrade  (Passive Upgrade)

    public static final int ORBITAL_CATACLYSM = 36121052;
    public static final int AMARANTH_GENERATOR = 36121054;
    public static final int ENTANGLISH_LASH = 36121053;


    private final int MAX_SUPPLY = 20;
    private int supply;
    private int supplyProp;
    private int hybridDefenseCount;

    private int[] addedSkills = new int[]{
            SUPPLY_SURPLUS,
            MULTILATERAL_I,
            MODAL_SHIFT,
            LIBERTY_BOOSTERS,
            MIMIC_PROTOCOL,
            PROMESSA_ESCAPE,
    };

    private int[] buffs = new int[]{
            CIRCUIT_SURGE,
            XENON_BOOSTER,
            EFFICIENCY_STREAMLINE,
            HYBRID_DEFENSES,
            AEGIS_SYSTEM,
            MANIFEST_PROJECTOR,
            HYPOGRAM_FIELD_FORCE_FIELD,
            HYPOGRAM_FIELD_PENETRATE,
            HYPOGRAM_FIELD_SUPPORT,
            TEMPORAL_POD,
            OOPARTS_CODE,
            MAPLE_WARRIOR_XENON,
            ORBITAL_CATACLYSM,
            AMARANTH_GENERATOR,
    };

    public Xenon(Char chr) {
        super(chr);
        if(isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            supplyProp = SkillData.getSkillInfoById(SUPPLY_SURPLUS).getValue(prop, 1);
            supplyInterval();
        }
    }

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Summon summon;
        Field field;
        switch (skillID) {
            case CIRCUIT_SURGE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                break;
            case XENON_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case EFFICIENCY_STREAMLINE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieMhpR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHPR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMmpR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMMPR, o2);
                break;
            case HYBRID_DEFENSES:
                o1.nOption = si.getValue(prop, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EVAR, o1);
                hybridDefenseCount = si.getValue(x, slv);
                break;
            case AEGIS_SYSTEM:
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(XenonAegisSystem, o1);
                break;
            case MANIFEST_PROJECTOR:
                o1.nOption = si.getValue(y, slv);
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(ShadowPartner, o1);
                break;
            case OOPARTS_CODE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BdR, o2);
                break;
            case MAPLE_WARRIOR_XENON:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case AMARANTH_GENERATOR:
                if (supply >= 20) {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(time, slv);
                    tsm.putCharacterStatValue(AmaranthGenerator, o1);
                }
                break;

            case HYPOGRAM_FIELD_FORCE_FIELD:
            case HYPOGRAM_FIELD_PENETRATE:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAction((byte) 0);
                summon.setMoveAbility((byte) 0);
                field.spawnSummon(summon);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private void handleSupplyCost(int skillID, byte slv, SkillInfo si) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(tsm.hasStat(AmaranthGenerator)) {
            return;
        } else {
            if (si == null) {
                return;
            }
            if (si.getValue(powerCon, slv) > 0) {
                supply -= si.getValue(powerCon, slv);
                supply = Math.max(0, supply);
            }
            updateSupply();
        }
    }

    public void incrementSupply() {
        incrementSupply(1);
    }


    private void incrementSupply(int amount) {
        supply += amount;
        supply = Math.min(MAX_SUPPLY, supply);
        updateSupply();
    }

    public void supplyInterval() {
        incrementSupply();
        EventManager.addEvent(this, "supplyInterval", 4000);
    }

    private void updateSupply() {
        Option o = new Option();
        o.nOption = supply;
        chr.getTemporaryStatManager().putCharacterStatValue(SurplusSupply, o);
        chr.getTemporaryStatManager().sendSetStatPacket();
    }

    private boolean isBuff(int skillID) {
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
        if (hasHitMobs) {
            if (Util.succeedProp(supplyProp)) {
                incrementSupply();
            }
        }
        handleSupplyCost(skillID, (byte) slv, si);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case ENTANGLISH_LASH:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = skill.getSkillId();
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    mts.addStatOptionsAndBroadcast(MobStat.MagicCrash, o1);
                }
                break;
            case ORBITAL_CATACLYSM:
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
                c.write(WvsContext.temporaryStatSet(tsm));
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
        handleSupplyCost(skillID, slv, si);
        if (isBuff(skillID)) {
            handleBuff(c, inPacket, skillID, slv);
        } else {
            Option o1 = new Option();
            Option o2 = new Option();
            Option o3 = new Option();
            switch (skillID) {
                case EMERGENCY_RESUPPLY:
                    incrementSupply(si.getValue(x, slv));
                    break;
                case TEMPORAL_POD:
                    //TODO
                    break;
                case PROMESSA_ESCAPE:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = c.getChannelInstance().getField(o1.nValue);
                    chr.warp(toField);
                    break;
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        Skill hybrid = chr.getSkill(HYBRID_DEFENSES);
        Option o1 = tsm.getOptByCTSAndSkill(EVAR, HYBRID_DEFENSES);
        if (hybrid != null && o1 != null) {
            SkillInfo si = SkillData.getSkillInfoById(HYBRID_DEFENSES);
            byte slv = (byte) hybrid.getCurrentLevel();
            if (hitInfo.HPDamage > 0) {
                o1.nOption -= si.getValue(y, slv);
                hitInfo.HPDamage -= hitInfo.HPDamage * 0.05;
                tsm.putCharacterStatValue(EVAR, o1);
                tsm.sendSetStatPacket();
            } else {
                hybridDefenseCount--;
                if (hybridDefenseCount <= 0) {
                    tsm.removeStat(EVAR, false);
                    tsm.sendResetStatPacket();
                }
            }
        }
        Skill aegis = chr.getSkill(AEGIS_SYSTEM);
        if (tsm.hasStat(XenonAegisSystem) && aegis != null) {
            SkillInfo si = SkillData.getSkillInfoById(AEGIS_SYSTEM);
            byte slv = (byte) aegis.getCurrentLevel();
            if (Util.succeedProp(si.getValue(prop, slv))) {
                int mobID = hitInfo.mobID;
                ForceAtomEnum fae = ForceAtomEnum.XENON_ROCKET_1;
                int curTime = Util.getCurrentTime();
                List<ForceAtomInfo> faiList = new ArrayList<>();
                List<Integer> mobList = new ArrayList<>();
                Random random = new Random();
                for (int i = 0; i < si.getValue(x, slv); i++) {
                    int firstImpact = 5 + random.nextInt(6);
                    int secondImpact = 5 + random.nextInt(6);
                    int angle = random.nextInt(180);
                    ForceAtomInfo fai = new ForceAtomInfo(1, fae.getInc(), firstImpact, secondImpact,
                            angle, 0, curTime, 0, AEGIS_SYSTEM_ATOM, new Position(0, 0));
                    faiList.add(fai);
                    mobList.add(mobID);
                }
                c.write(CField.createForceAtom(false, 0, chr.getId(), fae.getForceAtomType(), true,
                        mobList, AEGIS_SYSTEM_ATOM, faiList, null, 0, 0,
                        null, 0, null));
            }
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id == JobConstants.JobEnum.XENON.getJobId() ||
                (id >= JobConstants.JobEnum.XENON1.getJobId() && id <= JobConstants.JobEnum.XENON4.getJobId());
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }
}
