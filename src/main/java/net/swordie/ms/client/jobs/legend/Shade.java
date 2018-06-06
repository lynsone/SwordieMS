package net.swordie.ms.client.jobs.legend;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.*;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ChatMsgColour;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.connection.packet.CField;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class Shade extends Job {
    public static final int SPIRIT_BOND_I = 20050285;
    public static final int FOX_TROT = 20051284;

    public static final int FOX_SPIRITS = 25101009; //Buff (ON/OFF)                 //HiddenPossesion TempStat
    public static final int FOX_SPIRITS_INIT = 25100009;
    public static final int FOX_SPIRITS_ATOM = 25100010;
    public static final int FOX_SPIRITS_ATOM_2 = 25120115; //Upgrade
    public static final int GROUND_POUND_FIRST = 25101000; //Special Attack (Slow Debuff)
    public static final int GROUND_POUND_SECOND = 25100001; //Special Attack (Slow Debuff)

    public static final int SUMMON_OTHER_SPIRIT = 25111209; //Passive Buff (Icon)
    public static final int SPIRIT_TRAP = 25111206; //Tile
    public static final int WEAKEN = 25110210; //Passive Debuff

    public static final int SPIRIT_WARD = 25121209; //Special Buff
    public static final int MAPLE_WARRIOR_SH = 25121108; //Buff
    public static final int BOMB_PUNCH = 25121000;
    public static final int BOMB_PUNCH_FINAL = 25120003; //Special Attack (Stun Debuff)
    public static final int DEATH_MARK = 25121006; //Special Attack (Mark Debuff)
    public static final int SOUL_SPLITTER = 25121007; //Special Attack (Split)
    public static final int FIRE_FOX_SPIRIT_MASTERY = 25120110;
    public static final int HEROS_WILL_SH = 25121211;

    public static final int HEROIC_MEMORIES_SH = 25121132;
    public static final int SPIRIT_BOND_MAX = 25121131;

    private int[] addedSkills = new int[] {
            SPIRIT_BOND_I,
            FOX_TROT,
    };

    private final int[] buffs = new int[]{
            FOX_SPIRITS,
            SUMMON_OTHER_SPIRIT,
            SPIRIT_WARD,
            MAPLE_WARRIOR_SH,
            HEROIC_MEMORIES_SH,
            SPIRIT_BOND_MAX,
    };

    public Shade(Char chr) {
        super(chr);
        if(chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
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
        switch (skillID) {
            case FOX_SPIRITS:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(HiddenPossession, o1);
                break;
            case SUMMON_OTHER_SPIRIT:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ReviveOnce, o1);
                break;
            case SPIRIT_WARD:
                o1.nOption = 3;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SpiritGuard, o1);
                break;
            case MAPLE_WARRIOR_SH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case HEROIC_MEMORIES_SH:
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
            case SPIRIT_BOND_MAX:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePad, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o2);
                o3.nReason = skillID;
                o3.nValue = si.getValue(indieBDR, slv);
                o3.tStart = (int) System.currentTimeMillis();
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBDR, o3);
                o4.nReason = skillID;
                o4.nValue = -1; //Booster
                o4.tStart = (int) System.currentTimeMillis();
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o4);
                o5.nReason = skillID;
                o5.nValue = si.getValue(indieIgnoreMobpdpR, slv);
                o5.tStart = (int) System.currentTimeMillis();
                o5.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o5);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private void handleFoxSpirits(int skillID) {    //
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(HiddenPossession)) {
            SkillInfo si = SkillData.getSkillInfoById(FOX_SPIRITS_ATOM);
            Field field = chr.getField();
            //Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));

            Rect rect = new Rect(
                    new Position(
                            chr.getPosition().getX() - 750,
                            chr.getPosition().getY() - 750),
                    new Position(
                            chr.getPosition().getX() + 750,
                            chr.getPosition().getY() + 750)
            );

            List<Mob> mobs = chr.getField().getBossMobsInRect(rect);
            Mob mob = Util.getRandomFromList(mobs);
            int anglenum = 305;// new Random().nextInt(270);
            int mobID = mob.getObjectId();

            int atomid = FOX_SPIRITS_ATOM;
            int inc = ForceAtomEnum.RABBIT_ORB.getInc();
            int type = ForceAtomEnum.RABBIT_ORB.getForceAtomType();

            if(skillID == FIRE_FOX_SPIRIT_MASTERY) {
                atomid = FOX_SPIRITS_ATOM_2;
                inc = ForceAtomEnum.FLAMING_RABBIT_ORB.getInc();
                type = ForceAtomEnum.FLAMING_RABBIT_ORB.getForceAtomType();
            }
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 15, 7,
                    anglenum, 400, (int) System.currentTimeMillis(), 1, 0,
                    new Position(chr.isLeft() ? 0 : -50, -50));
            chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                    true, mobID, atomid, forceAtomInfo, new Rect(), 0, 300,
                    mob.getPosition(), atomid, mob.getPosition()));
        }
    }

    private void handleFoxSpiritsReCreation(int skillID, byte slv, AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(FOX_SPIRITS_ATOM);
        int anglenum = new Random().nextInt(360);
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            int TW1prop = 70;  // Recreation %
            if (Util.succeedProp(TW1prop)) {
                int mobID = mai.mobId;
                if(chr.hasSkill(FIRE_FOX_SPIRIT_MASTERY)) {
                    int inc = ForceAtomEnum.FLAMING_RABBIT_ORB_RECREATION.getInc(); //4th Job
                    int type = ForceAtomEnum.FLAMING_RABBIT_ORB_RECREATION.getForceAtomType(); //4th Job
                    ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 25, 4,
                            anglenum, 100, (int) System.currentTimeMillis(), 1, 0,
                            new Position());
                    chr.getField().broadcastPacket(CField.createForceAtom(true, chr.getId(), mobID, type,
                            true, mobID, FOX_SPIRITS_ATOM_2, forceAtomInfo, new Rect(), 0, 300,
                            mob.getPosition(), FOX_SPIRITS_ATOM_2, mob.getPosition()));
                } else {
                    int inc = ForceAtomEnum.RABBIT_ORB_RECREATION.getInc();
                    int type = ForceAtomEnum.RABBIT_ORB_RECREATION.getForceAtomType();
                    ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 25, 4,
                            anglenum, 100, (int) System.currentTimeMillis(), 1, 0,
                            new Position());
                    chr.getField().broadcastPacket(CField.createForceAtom(true, chr.getId(), mobID, type,
                            true, mobID, FOX_SPIRITS_ATOM, forceAtomInfo, new Rect(), 0, 300,
                            mob.getPosition(), FOX_SPIRITS_ATOM, mob.getPosition()));
                }
            }
        }
    }

    public void handleWeaken(AttackInfo attackInfo, byte slv) {
        if(chr.hasSkill(WEAKEN)) {
            Option o1 = new Option();
            Option o2 = new Option();
            Option o3 = new Option();
            SkillInfo si = SkillData.getSkillInfoById(WEAKEN);
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if (Util.succeedProp(si.getValue(prop, slv))) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = si.getValue(x, slv);
                    o1.rOption = WEAKEN;
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Weakness, o1);
                    o2.nOption = si.getValue(y, slv);
                    o2.rOption = WEAKEN;
                    o2.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.ACC, o2);
                    o3.nOption = si.getValue(z, slv);
                    o3.rOption = WEAKEN;
                    o3.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.EVA, o3);
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
            if(attackInfo.skillId == FOX_SPIRITS_ATOM || attackInfo.skillId == FOX_SPIRITS_ATOM_2) {
                handleFoxSpiritsReCreation(skillID, slv, attackInfo);
            }
            handleWeaken(attackInfo, slv);
            handleDeathMarkDoTHeal(attackInfo);
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
                    o1.nOption = -si.getValue(y, slv);
                    o1.rOption = skill.getSkillId();
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                }
                break;
            case BOMB_PUNCH_FINAL:
                SkillInfo bpi = SkillData.getSkillInfoById(BOMB_PUNCH);
                byte bombPunchslv = (byte) chr.getSkill(BOMB_PUNCH).getCurrentLevel();
                if (Util.succeedProp(bpi.getValue(prop, bombPunchslv))) {
                    for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = BOMB_PUNCH;
                        o1.tOption = bpi.getValue(time, bombPunchslv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case DEATH_MARK:
                int healrate = si.getValue(x, slv);
                chr.heal((int) (chr.getMaxHP() / ((double)100 / healrate)));
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = skill.getSkillId();
                    o1.tOption = si.getValue(dotTime, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.DebuffHealing, o1);
                    mts.createAndAddBurnedInfo(chr, skill, 1);
                }
                break;
            case SOUL_SPLITTER:     // TODO
                int duration = si.getValue(time, slv); //Split Duration & Timer on when to removeLife
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);

                    //Spawns soul split mob
                    if(!mob.isSplit()) {
                        mob.soulSplitMob(chr, mob, duration, skill);
                    }
                }
                break;
        }
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
                case SPIRIT_TRAP:
                    SkillInfo fci = SkillData.getSkillInfoById(skillID);
                    AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                    aa.setMobOrigin((byte) 0);
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(fci.getRects().get(0)));
                    aa.setDelay((short) 4);
                    chr.getField().spawnAffectedArea(aa);
                    break;
                case FIRE_FOX_SPIRIT_MASTERY:
                case FOX_SPIRITS_INIT:
                    handleFoxSpirits(skillID);
                    break;
                case HEROS_WILL_SH:
                    tsm.removeAllDebuffs();
                    break;
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {   //TODO Needs to not Reset the Timer when Hit
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        if (tsm.hasStat(SpiritGuard)) {
            if (tsm.getOption(SpiritGuard).nOption == 3) {
                o.nOption = 2;
                o.rOption = SPIRIT_WARD;
                o.tOption = 30;
                tsm.putCharacterStatValue(SpiritGuard, o);
                tsm.sendSetStatPacket();
            } else if (tsm.getOption(SpiritGuard).nOption == 2) {
                o.nOption = 1;
                o.rOption = SPIRIT_WARD;
                o.tOption = 30;
                tsm.putCharacterStatValue(SpiritGuard, o);
                tsm.sendSetStatPacket();
            } else if (tsm.getOption(SpiritGuard).nOption == 1) {
                resetSpiritGuard();
            }
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    public void resetSpiritGuard() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStat(SpiritGuard, false);
        c.write(WvsContext.temporaryStatReset(tsm, false));
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isShade(id);
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    public void handleDeathMarkDoTHeal(AttackInfo attackInfo) {
        Skill skill = chr.getSkill(DEATH_MARK);
        byte slv = (byte) skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int healrate = si.getValue(x, slv);
        for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            MobTemporaryStat mts = mob.getTemporaryStat();
            if(mts.getBurnBySkill(DEATH_MARK) != null) {
                long totaldmg = Arrays.stream(mai.damages).sum();
                chr.heal((int) (chr.getMaxHP() / ((double) 100 / healrate)));
            }
        }
    }
}
