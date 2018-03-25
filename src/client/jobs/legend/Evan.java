package client.jobs.legend;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.field.Field;
import client.jobs.Job;
import client.life.*;
import connection.InPacket;
import constants.JobConstants;
import enums.*;
import loaders.SkillData;
import packet.CField;
import packet.WvsContext;
import util.Position;
import util.Rect;

import java.util.Arrays;
import java.util.List;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class Evan extends Job {

    public static final int INHERITED_WILL = 20010194;
    public static final int BACK_TO_NATURE = 20011293;

    public static final int MAGIC_GUARD = 22001012; //Buff

    public static final int MAGIC_BOOSTER = 22111020; //Buff
    public static final int ELEMENTAL_DECREASE = 22141016; //Buff
    public static final int PARTNERS = 22110016;

    public static final int BLESSING_OF_THE_ONYX = 22181000; //Buff
    public static final int MAPLE_WARRIOR_EVAN = 22171000; //Buff
    public static final int MAGIC_DEBRIS = 22141017;

    public static final int DRAGON_MASTER = 22171080; //Mount
    public static final int DRAGON_MASTER_2 = 22171083; //Add-on
    public static final int SUMMON_ONYX_DRAGON = 22171081; //Summon
    public static final int HEROIC_MEMORIES_EVAN = 22171082;
    public static final int ENHANCED_MAGIC_DEBRIS = 22170070;

    //Returns
    public static final int RETURN_FLASH = 22110013; //Return after Wind Skills (Mob Debuff)
    public static final int RETURN_DIVE = 22140013; //Return Dive (Buff)
    public static final int RETURN_FLAME = 22170064; //Return Flame (Flame  AoE)
    public static final int RETURN_FLAME_TILE = 22170093; //Return Flames Tile


    //Evan Attacks
    public static final int MANA_BURST_I = 22001010;
    public static final int MANA_BURST_II = 22110010;
    public static final int MANA_BURST_III = 22140010;
    public static final int MANA_BURST_IV_1 = 22170060;
    public static final int MANA_BURST_IV_2 = 22170061;
    public static final int WIND_CIRCLE = 22111011;
    public static final int THUNDER_CIRCLE = 22141011;
    public static final int EARTH_CIRCLE = 22171062;
    public static final int DARK_FOG = 22171095;

    private int prevSkill = 0;

    private int[] addedSkills = new int[] {
            INHERITED_WILL,
            BACK_TO_NATURE,
    };

    private final int[] buffs = new int[]{
            MAGIC_GUARD,
            MAGIC_BOOSTER,
            ELEMENTAL_DECREASE,
            BLESSING_OF_THE_ONYX,
            MAPLE_WARRIOR_EVAN,
            HEROIC_MEMORIES_EVAN,
            SUMMON_ONYX_DRAGON,
            DRAGON_MASTER,
            DRAGON_MASTER_2,
            RETURN_DIVE,
    };

    public Evan(Char chr) {
        super(chr);
        if(isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            //spawnMir();
        }
    }

    public void spawnMir() {
        c.write(CField.createDragon(chr));
    }

    public int getEvanSkill(int skillID) {
        switch (skillID) {
            case MANA_BURST_I:
            case MANA_BURST_II:
            case MANA_BURST_III:
            case MANA_BURST_IV_1:
            case MANA_BURST_IV_2:
            case WIND_CIRCLE:
            case THUNDER_CIRCLE:
            case EARTH_CIRCLE:
            case DARK_FOG:
                return 1;

        }
        return skillID;
    }

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.RideVehicle);
        Summon summon;
        Field field;
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case MAGIC_GUARD:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(MagicGuard, o1);


                c.write(CField.createDragon(chr));

                break;
            case MAGIC_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);



                break;
            case RETURN_DIVE:
                o1.nReason = skillID;
                o1.nValue = 1; //si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case ELEMENTAL_DECREASE:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ElementalReset, o1);
                break;
            case BLESSING_OF_THE_ONYX:
                o1.nOption = si.getValue(emad, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EMAD, o1);
                o2.nOption = si.getValue(emdd, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EMDD, o2);
                o3.nOption = si.getValue(epdd, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EPDD, o3);
                break;
            case MAPLE_WARRIOR_EVAN:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case HEROIC_MEMORIES_EVAN:
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
            case SUMMON_ONYX_DRAGON:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.STATIC.getVal());
                field.spawnSummon(summon);
                break;
            case DRAGON_MASTER:


                tsb.setNOption(1939007);
                tsb.setROption(skillID);
                tsm.putCharacterStatValue(RideVehicle, tsb.getOption());
                tsm.sendResetStatPacket();

                o1.nOption = 1;
                o1.rOption = DRAGON_MASTER;
                o1.tOption = 30;
                //tsm.putCharacterStatValue(NewFlying, o1);
                break;
            case DRAGON_MASTER_2:

                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
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
            if (getEvanSkill(skillID) != 1) {
                handlePartners(skillID);
            }
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case THUNDER_CIRCLE:
                if(hasHitMobs) {
                    c.write(CField.addWreckage(chr, attackInfo, 1));
                }
                break;
        }
    }

    public void handlePartners(int skillID) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(PARTNERS);
        Option o = new Option();
        Option o1 = new Option();
        if(tsm.getOptByCTSAndSkill(Stance, PARTNERS) == null) {
            prevSkill = skillID;
            o.nReason = PARTNERS;
            o.nValue = si.getValue(indieDamR, 1);
            o.tStart = (int) System.currentTimeMillis();
            o.tTerm = 3;
            tsm.putCharacterStatValue(IndieDamR, o);
            o1.nOption = si.getValue(stanceProp, 1);
            o1.rOption = PARTNERS;
            o1.tOption = 3;
            tsm.putCharacterStatValue(Stance, o1);
            c.write(WvsContext.temporaryStatSet(tsm));
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
            switch (skillID) {
                case BACK_TO_NATURE:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = c.getChannelInstance().getField(o1.nValue);
                    chr.warp(toField);
                    break;
                case RETURN_FLAME:
                    SkillInfo rft = SkillData.getSkillInfoById(RETURN_FLAME_TILE);
                    AffectedArea aa = AffectedArea.getPassiveAA(RETURN_FLAME_TILE, slv);
                    aa.setMobOrigin((byte) 0);
                    aa.setCharID(chr.getId());
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(rft.getRects().get(0)));
                    //aa.setRect(rect);
                    chr.getField().spawnAffectedArea(aa);
                    break;
                case RETURN_FLASH:
                    SkillInfo rflash = SkillData.getSkillInfoById(RETURN_FLASH);
                    Rect rect = new Rect(       //Skill itself doesn't give a Rect
                            new Position(
                                    chr.getPosition().deepCopy().getX() - 300,
                                    chr.getPosition().deepCopy().getY() - 300),
                            new Position(
                                    chr.getPosition().deepCopy().getX() + 300,
                                    chr.getPosition().deepCopy().getY() + 300)
                    );
                    for(Life life : chr.getField().getLifesInRect(rect)) {
                        if(life instanceof Mob && ((Mob) life).getHp() > 0) {
                            Mob mob = (Mob) life;
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            o1.nOption = rflash.getValue(x, slv);
                            o1.rOption = skillID;
                            o1.tOption = rflash.getValue(time, slv);
                            mts.addStatOptionsAndBroadcast(MobStat.AddDamParty, o1);

                        }
                    }
                    break;
                case MAGIC_DEBRIS:
                case ENHANCED_MAGIC_DEBRIS:
                    handleMagicDebris();
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
            case EVAN_NOOB:
            case EVAN1:
            case EVAN2:
            case EVAN3:
            case EVAN4:
                return true;
            default:
                return false;
        }
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    private void handleMagicDebris() {
        Field field = chr.getField();
        SkillInfo si = SkillData.getSkillInfoById(MAGIC_DEBRIS);
        Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
        List<Life> lifes = field.getLifesInRect(rect);
        for(Life life : lifes) {
            if(life instanceof Mob) {
                int mobID = ((Mob) life).getRefImgMobID(); //
                int inc = ForceAtomEnum.FAST_STAR_ORB.getInc();
                int type = ForceAtomEnum.FAST_STAR_ORB.getForceAtomType();
                ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 20, 20,
                        0, 200, (int) System.currentTimeMillis(), 1, 0,
                        new Position(life.deepCopy().getX(), life.deepCopy().getY()));
                chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                        true, mobID, MAGIC_DEBRIS, forceAtomInfo, new Rect(), 0, 300,
                        life.getPosition(), MAGIC_DEBRIS, life.getPosition()));
            }
        }
    }
}
