package client.jobs.adventurer;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.field.Field;
import client.field.Foothold;
import client.jobs.Job;
import client.life.AffectedArea;
import client.life.Mob;
import client.life.MobTemporaryStat;
import client.life.Summon;
import connection.InPacket;
import constants.JobConstants;
import enums.ForceAtomEnum;
import enums.MobStat;
import enums.Stat;
import loaders.SkillData;
import packet.CField;
import packet.WvsContext;
import util.Position;
import util.Rect;
import util.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static client.character.skills.SkillStat.*;
import static client.character.skills.CharacterTemporaryStat.*;


/**
 * Created on 12/14/2017.
 */
public class Archer extends Job {

    public static final int SOUL_ARROW_BOW = 3101004;
    public static final int ARROW_BOMB = 3101005;
    public static final int BOW_BOOSTER = 3101002;
    public static final int QUIVER_CARTRIDGE = 3101009;
    public static final int QUIVER_CARTRIDGE_ATOM = 3100010;
    public static final int FLAME_SURGE = 3111003;
    public static final int PHOENIX = 3111005;
    public static final int RECKLESS_HUNT_BOW = 3111011;
    public static final int FOCUSED_FURY = 3110012;
    public static final int ARROW_PLATTER = 3111013;
    public static final int EVASION_BOOST = 3110007;
    public static final int PUPPET = 3111002;
    public static final int SHARP_EYES_BOW = 3121002;
    public static final int ILLUSION_STEP_BOW = 3121007;
    public static final int ENCHANTED_QUIVER = 3121016;
    public static final int BINDING_SHOT = 3121014;
    public static final int MAPLE_WARRIOR_BOW = 3121000;
    public static final int MAPLE_WARRIOR_XBOW = 3221000;

    private QuiverCartridge quiverCartridge;

    private int[] buffs = new int[]{
            BOW_BOOSTER,
            SOUL_ARROW_BOW,
            QUIVER_CARTRIDGE,
            PHOENIX,
            RECKLESS_HUNT_BOW,
            SHARP_EYES_BOW,
            ILLUSION_STEP_BOW,
            ENCHANTED_QUIVER,
            MAPLE_WARRIOR_BOW,
            MAPLE_WARRIOR_XBOW,
    };

    public Archer(Char chr) {
        super(chr);
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
            handleQuiverCartridge(c, chr.getTemporaryStatManager(), attackInfo, slv);
            handleFocusedFury();
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case ARROW_BOMB:
            case PHOENIX:
                if (Util.succeedProp(si.getValue(prop, slv))) {
                    for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skillID;
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case FLAME_SURGE:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    AffectedArea aa = AffectedArea.getAffectedArea(attackInfo);
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    aa.setMobOrigin((byte) 0);
                    aa.setCharID(chr.getId());
                    int x = mob.getX();
                    int y = mob.getY();
                    Foothold fh = mob.getCurFoodhold();
                    aa.setPosition(new Position(x, y));
                    Rect rect = si.getRects().get(0);
                    if(rect.getLeft() > fh.getX1()) {
                        rect.setLeft(fh.getX1());
                    } else if(rect.getRight() > fh.getX2()) {
                        rect.setRight(fh.getX2());
                    }
                    aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                    chr.getField().spawnAffectedArea(aa);
                }
                break;
            case BINDING_SHOT:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o2.nOption = -si.getValue(x, slv);
                    o2.rOption = skillID;
                    o2.tOption = si.getValue(time, slv);
//                    mts.addStatOptions(MobStat.Re) // TODO hp recovery?
                    o1.nOption = si.getValue(s, slv);
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                }
                break;
        }
    }

    private void handleFocusedFury() {
        if(!chr.hasSkill(FOCUSED_FURY)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = tsm.getOptByCTSAndSkill(AsrR, FOCUSED_FURY);
        if(o == null) {
            o = new Option();
            o.nOption = 0;
            o.rOption = FOCUSED_FURY;
        }
        Skill skill = chr.getSkill(FOCUSED_FURY);
        byte slv = (byte) skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(FOCUSED_FURY);
        o.nOption = Math.min(o.nOption + si.getValue(x, slv), 100);
        o.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(AsrR, o);
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private void handleQuiverCartridge(Client c, TemporaryStatManager tsm, AttackInfo attackInfo, int slv) {
        Char chr = c.getChr();
        if (quiverCartridge == null) {
            return;
        }
        Skill skill = chr.hasSkill(ENCHANTED_QUIVER) ? chr.getSkill(ENCHANTED_QUIVER)
                : chr.getSkill(QUIVER_CARTRIDGE);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            MobTemporaryStat mts = mob.getTemporaryStat();
            int mobId = mai.mobId;
            switch (quiverCartridge.getType()) {
                case 1: // Blood
                    if(Util.succeedProp(si.getValue(w, slv))) {
                        quiverCartridge.decrementAmount();
                        int maxHP = chr.getStat(Stat.mhp);
                        int addHP = (int) (maxHP * 0.03);
                        int curHP = chr.getStat(Stat.hp);
                        int newHP = curHP + addHP > maxHP ? maxHP : curHP + addHP;
                        chr.setStat(Stat.hp, (short) newHP);
                        Map<Stat, Object> stats = new HashMap<>();
                        stats.put(Stat.hp, newHP);
                        c.write(WvsContext.statChanged(stats, false));
                    }
                    break;
                case 2: // Poison
                    mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                    quiverCartridge.decrementAmount();
                    break;
                case 3: // Magic
                    if(Util.succeedProp(si.getValue(u, slv))) {
                        quiverCartridge.decrementAmount();
                        int inc = ForceAtomEnum.BM_ARROW.getInc();
                        int type = ForceAtomEnum.BM_ARROW.getForceAtomType();
                        ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 15, 15,
                                0, 0, (int) System.currentTimeMillis(), 1, 0,
                                new Position());
                        chr.getClient().write(CField.createForceAtom(false, 0, chr.getId(), type,
                                true, mobId, QUIVER_CARTRIDGE_ATOM, forceAtomInfo, new Rect(), 0, 300,
                                mob.getPosition(), 0, mob.getPosition()));
                    }
                    break;
            }
        }
        tsm.putCharacterStatValue(QuiverCatridge, quiverCartridge.getOption());
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    public enum QCType {
        BLOOD(1),
        POISON(2),
        MAGIC(3),
        ;
        private byte val;

        QCType(int val) {
            this.val = (byte) val;
        }

        public byte getVal() {
            return val;
        }
    }

    public class QuiverCartridge{

        private int blood; // 1
        private int poison; // 2
        private int magic; // 3
        private int type;
        private Char chr;

        public QuiverCartridge(Char chr) {
            blood = getMaxNumberOfArrows(chr, QCType.BLOOD.getVal());
            poison = getMaxNumberOfArrows(chr, QCType.POISON.getVal());
            magic = getMaxNumberOfArrows(chr, QCType.MAGIC.getVal());
            type = 1;
            this.chr = chr;
        }

        public void decrementAmount() {
            if(chr.getTemporaryStatManager().hasStat(AdvancedQuiver)) {
                return;
            }
            switch(type) {
                case 1:
                    blood--;
                    if(blood == 0) {
                        blood = getMaxNumberOfArrows(chr, QCType.BLOOD.getVal());
                        incType();
                    }
                    break;
                case 2:
                    poison--;
                    if(poison == 0) {
                        poison = getMaxNumberOfArrows(chr, QCType.POISON.getVal());
                        incType();
                    }
                    break;
                case 3:
                    magic--;
                    if(magic == 0) {
                        magic = getMaxNumberOfArrows(chr, QCType.MAGIC.getVal());
                        incType();
                    }
                    break;
            }
        }

        public void incType() {
            type = (type % 3) + 1;
        }

        public int getTotal() {
            return blood * 10000 + poison * 100 + magic;
        }

        public Option getOption() {
            Option o = new Option();
            o.nOption = getTotal();
            o.rOption = QUIVER_CARTRIDGE;
            o.xOption = type;
            return o;
        }

        public int getType() {
            return type;
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
        if (isBuff(skillID)) {
            handleBuff(c, inPacket, skillID, slv);
        } else {
            switch(skillID) {
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        if(hitInfo.HPDamage == 0 && hitInfo.MPDamage == 0) {
            // Dodged
            if(chr.hasSkill(EVASION_BOOST)) {
                Skill skill = chr.getSkill(EVASION_BOOST);
                byte slv = (byte) skill.getCurrentLevel();
                SkillInfo si = SkillData.getSkillInfoById(EVASION_BOOST);
                TemporaryStatManager tsm = chr.getTemporaryStatManager();
                Option o = new Option();
                o.nOption = 100;
                o.rOption = EVASION_BOOST;
                o.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CriticalBuff, o);
                c.write(WvsContext.temporaryStatSet(tsm));
            }
        }
    }

    private void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        int curTime = (int) System.currentTimeMillis();
        switch (skillID) {
            case SOUL_ARROW_BOW:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SoulArrow, o1);
                o2.nOption = si.getValue(epad, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EPAD, o2);
                o1.nOption = 1; //si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NoBulletConsume, o3);
                break;
            case BOW_BOOSTER:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tStart = curTime;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case QUIVER_CARTRIDGE:
                if(quiverCartridge == null) {
                    quiverCartridge = new QuiverCartridge(chr);
                } else
                if(tsm.hasStat(QuiverCatridge)) {
                    quiverCartridge.incType();
                }
                o1 = quiverCartridge.getOption();
                tsm.putCharacterStatValue(QuiverCatridge, o1);
                break;
            case PHOENIX:
                Summon summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                Field field = c.getChr().getField();
                summon.setCharLevel((byte) chr.getStat(Stat.level));
                summon.setPosition(chr.getPosition().deepCopy());
                summon.setMoveAction((byte) 1);
                summon.setCurFoothold((short) field.findFootHoldBelow(summon.getPosition()).getId());
                summon.setMoveAbility((byte) 1);
                summon.setAssistType((byte) 1);
                summon.setEnterType((byte) 1);
                summon.setBeforeFirstAttack(false);
                summon.setTemplateId(skillID);
                summon.setAttackActive(true);
                summon.setFlyMob(true);
                field.spawnSummon(summon);
                break;
            case RECKLESS_HUNT_BOW:
                o1.nValue = -si.getValue(x, slv);
                o1.nReason = skillID;
                tsm.putCharacterStatValue(IndiePADR, o1);
                tsm.putCharacterStatValue(IndieMADR, o1);
                o2.nValue = si.getValue(indieDamR, slv);
                o2.nReason = skillID;
                tsm.putCharacterStatValue(IndieDamR, o2);
                o3.nOption = si.getValue(padX, slv);
                o3.rOption = skillID;
                tsm.putCharacterStatValue(PAD, o3);
                break;
            case SHARP_EYES_BOW:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CriticalBuff, o1);
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
//                o2.mOption = si.getValue(x, slv); mOption is for the hyper passive
                tsm.putCharacterStatValue(SharpEyes, o2);
                break;
            case ILLUSION_STEP_BOW:
                o1.nOption = si.getValue(dex, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DEX, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EVAR, o2);
                break;
            case MAPLE_WARRIOR_BOW:
            case MAPLE_WARRIOR_XBOW:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case ENCHANTED_QUIVER:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AdvancedQuiver, o1);
                break;


        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private boolean isBuff(int skillID) {
        return Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById(id);
        switch(job) {
            case BOWMAN:
            case HUNTER:
            case RANGER:
            case BOWMASTER:
            case CROSSBOWMAN:
            case SNIPER:
            case MARKSMAN:
                return true;
            default:
                return false;
        }
    }

    public int getMaxNumberOfArrows(Char chr, int type) {
        int num = 0;
        Skill firstSkill = chr.getSkill(QUIVER_CARTRIDGE);
        Skill secondSkill = chr.getSkill(ENCHANTED_QUIVER);
        if(secondSkill != null && secondSkill.getCurrentLevel() > 0) {
            num = 20;

        } else if(firstSkill != null && firstSkill.getCurrentLevel() > 0) {
            num = 10;
        }
        return type == 3 ? num * 2 : num; // Magic Arrow has 2x as many arrows
    }
}
