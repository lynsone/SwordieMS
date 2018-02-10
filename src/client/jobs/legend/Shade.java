package client.jobs.legend;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.jobs.Job;
import client.life.Mob;
import client.life.MobTemporaryStat;
import connection.InPacket;
import constants.JobConstants;
import enums.ChatMsgColour;
import enums.ForceAtomEnum;
import enums.MobStat;
import loaders.SkillData;
import packet.CField;
import packet.WvsContext;
import util.Position;
import util.Rect;
import util.Util;

import java.util.Arrays;
import java.util.Random;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class Shade extends Job {
    public static final int SPIRIT_BOND_I = 20050285;
    public static final int FOX_TROT = 20051284;

    public static final int FOX_SPIRITS = 25101009; //Buff (ON/OFF)
    public static final int FOX_SPIRITS_ATOM = 25100010;
    public static final int FOX_SPIRITS_ATOM_2 = 25120115; //Upgrade
    public static final int GROUND_POUND_FIRST = 25101000; //Special Attack (Slow Debuff)
    public static final int GROUND_POUND_SECOND = 25100001; //Special Attack (Slow Debuff)

    public static final int SUMMON_OTHER_SPIRIT = 25111209; //Passive Buff (Icon)
    public static final int SPIRIT_TRAP = 25111206; //Summon

    public static final int SPIRIT_WARD = 25121209; //Special Buff
    public static final int MAPLE_WARRIOR_SH = 25121108; //Buff
    public static final int BOMB_PUNCH_FINAL = 25121003; //Special Attack (Stun Debuff)
    public static final int DEATH_MARK = 25121006; //Special Attack (Mark Debuff)
    public static final int SOUL_SPLITTER = 25121007; //Special Attack (Split)

    private int[] addedSkills = new int[] {
            SPIRIT_BOND_I,
            FOX_TROT,
    };

    private final int[] buffs = new int[]{
            FOX_SPIRITS,
            SUMMON_OTHER_SPIRIT,
            SPIRIT_TRAP,
            SPIRIT_WARD,
            MAPLE_WARRIOR_SH,
    };

    public Shade(Char chr) {
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
            case FOX_SPIRITS:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(ChangeFoxMan, o1);
                break;
            case SUMMON_OTHER_SPIRIT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SpiritGuard, o1);
                break;
            case SPIRIT_WARD:
                // TODO (needs a handler, i believe)
                break;
            case MAPLE_WARRIOR_SH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case SPIRIT_TRAP:
                // TODO AoE
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private void handleFoxSpirits(int skillID, byte slv, AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(ChangeFoxMan)) {
            SkillInfo si = SkillData.getSkillInfoById(FOX_SPIRITS_ATOM);
            int anglenum = new Random().nextInt(90) + 10; //randomisation
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                int TW1prop = 100;//  SkillData.getSkillInfoById(FOX_SPIRITS_ATOM).getValue(prop, slv); //TODO Change
                if (Util.succeedProp(TW1prop)) {
                    if(chr.hasSkill(25120110)) {
                        int mobID = mai.mobId;
                        int inc = ForceAtomEnum.RED_RABBIT_ORB.getInc(); //4th Job
                        int type = ForceAtomEnum.RED_RABBIT_ORB.getForceAtomType(); //4th Job
                        ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 20, 40,
                                anglenum, 0, (int) System.currentTimeMillis(), 1, 0,
                                new Position());
                        chr.getClient().write(CField.createForceAtom(false, 0, chr.getId(), type,
                                true, mobID, FOX_SPIRITS_ATOM_2, forceAtomInfo, new Rect(), 0, 300,
                                mob.getPosition(), FOX_SPIRITS_ATOM_2, mob.getPosition()));
                    } else {
                        int mobID = mai.mobId;
                        int inc = ForceAtomEnum.BLUE_RABBIT_ORB.getInc(); //2nd Job
                        int type = ForceAtomEnum.BLUE_RABBIT_ORB.getForceAtomType(); //2nd Job
                        ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 20, 40,
                                anglenum, 0, (int) System.currentTimeMillis(), 1, 0,
                                new Position());
                        chr.getClient().write(CField.createForceAtom(false, 0, chr.getId(), type,
                                true, mobID, FOX_SPIRITS_ATOM, forceAtomInfo, new Rect(), 0, 300,
                                mob.getPosition(), FOX_SPIRITS_ATOM, mob.getPosition()));
                    }
                }
            }
        }
    }

    private void handleFoxSpiritMobToMob(int skillID, byte slv, AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(ChangeFoxMan)) {
            SkillInfo si = SkillData.getSkillInfoById(FOX_SPIRITS_ATOM);
            //int anglenum = new Random().nextInt(90) + 10; //randomisation
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                int TW1prop = 60;//  SkillData.getSkillInfoById(FOX_SPIRITS_ATOM).getValue(prop, slv); //TODO Change
                if (Util.succeedProp(TW1prop)) {
                    int mobID = mai.mobId;
                    int inc = ForceAtomEnum.BLUE_RABBIT_ORB.getInc(); //2nd Job
                    int type = ForceAtomEnum.BLUE_RABBIT_ORB.getForceAtomType(); //2nd Job
                    ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 20, 40,
                            0, 0, (int) System.currentTimeMillis(), 1, 0,
                            new Position());
                    chr.getClient().write(CField.createForceAtom(true, mobID, mobID, type, //TODO  atm Player -> Mob     |      Change to Mob -> Mob
                            true, mobID, FOX_SPIRITS_ATOM, forceAtomInfo, new Rect(), 0, 300,
                            mob.getPosition(), FOX_SPIRITS_ATOM, mob.getPosition()));
                }
            }
        }
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
            if (skillID == FOX_SPIRITS_ATOM_2) { //TODO
                handleFoxSpiritMobToMob(skillID, slv, attackInfo);
            } else if (skillID == FOX_SPIRITS_ATOM){ //TODO
                return;
            }
            else {
                handleFoxSpirits(skillID, slv, attackInfo);
            }
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case GROUND_POUND_FIRST:
            case GROUND_POUND_SECOND:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = si.getValue(y, slv);
                    o1.rOption = skill.getSkillId();
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                }
                break;
            case BOMB_PUNCH_FINAL:
                if (Util.succeedProp(si.getValue(prop, slv))) {
                    for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case DEATH_MARK:
                // TODO
                break;
            case SOUL_SPLITTER:
                // TODO
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private boolean isBuff(int skillID) {
        return Arrays.stream(buffs).anyMatch(b -> b == skillID);
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
            case SHADE:
            case SHADE1:
            case SHADE2:
            case SHADE3:
            case SHADE4:
                return true;
            default:
                return false;
        }
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }
}
