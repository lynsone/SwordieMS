package client.jobs.adventurer;

import client.Client;
import client.character.Char;
import client.character.skills.*;
import client.jobs.Job;
import client.life.Mob;
import client.life.MobTemporaryStat;
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
import static enums.ChatMsgColour.YELLOW;


/**
 * Created on 12/14/2017.
 */
public class Archer extends Job {

    public static final int SOUL_ARROW_BOW = 3101004;
    public static final int ARROW_BOMB = 3101005;
    public static final int BOW_BOOSTER = 3101002;
    public static final int QUIVER_CARTRIDGE = 3101009;
    public static final int FLAME_SURGE = 3111003;
    public static final int PHOENIX = 3111005;
    public static final int RECKLESS_HUNT_BOW = 3111011;
    public static final int PUPPET = 3111002;
    public static final int SHARP_EYES_BOW = 3121002;
    public static final int ILLUSION_STEP = 3121007;
    public static final int ENCHANTED_QUIVER = 3121016;
    public static final int MAPLE_WARRIOR_BOW = 3121000;
    public static final int MAPLE_WARRIOR_XBOW = 3221000;

    private QuiverCartridge quiverCartridge;

    private int[] buffs = new int[]{
            SOUL_ARROW_BOW,
            QUIVER_CARTRIDGE,
            PHOENIX,
            RECKLESS_HUNT_BOW,
            SHARP_EYES_BOW,
            ILLUSION_STEP,
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
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case ARROW_BOMB:
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
        }
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
                case 1:
                    if(Util.succeedProp(si.getValue(w, slv))) {
                        quiverCartridge.decrementAmount();
                        int maxHP = chr.getStat(Stat.mhp);
                        int addHP = (int) (maxHP * 0.03);
                        int curHP = chr.getStat(Stat.hp);
                        int newHP = curHP + addHP > maxHP ? maxHP : curHP + addHP;
                        chr.setStat(Stat.hp, (short) newHP);
                        Map<Stat, Object> stats = new HashMap<>();
                        stats.put(Stat.hp, newHP);
                        c.write(WvsContext.statChanged(stats, true));
                    }
                    break;
                case 2:
                    mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                    quiverCartridge.decrementAmount();
                    break;
                case 3:
                    if(Util.succeedProp(si.getValue(u, slv))) {
                        quiverCartridge.decrementAmount();
                        int inc = ForceAtomEnum.BM_ARROW.getInc();
                        int type = ForceAtomEnum.BM_ARROW.getForceAtomType();
                        ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 15, 15,
                                0, 0, (int) System.currentTimeMillis(), 1, 0,
                                new Position());
                        chr.getClient().write(CField.createForceAtom(false, 0, chr.getId(), type,
                                true, mobId, mobId, forceAtomInfo, new Rect(), 0, 300,
                                mob.getPosition(), 0, mob.getPosition()));
                        break;
                    }
            }
        }
        tsm.putCharacterStatValue(QuiverCatridge, quiverCartridge.getOption());
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    public class QuiverCartridge{
        private int blood; // 1
        private int poison; // 2
        private int magic; // 3
        private int type = 1;
        private Char chr;

        public QuiverCartridge(Char chr) {
            blood = getMaxNumberOfArrows(chr, (byte) 1);
            poison = getMaxNumberOfArrows(chr, (byte) 2);
            magic = getMaxNumberOfArrows(chr, (byte) 3);
            type = 1;
            this.chr = chr;
        }

        public void decrementAmount() {
            switch(type) {
                case 1:
                    blood--;
                    if(blood == 0) {
                        blood = getMaxNumberOfArrows(chr, 1);
                        incType();
                    }
                    break;
                case 2:
                    poison--;
                    if(poison == 0) {
                        poison = getMaxNumberOfArrows(chr, 2);
                        incType();
                    }
                    break;
                case 3:
                    magic--;
                    if(magic == 0) {
                        magic = getMaxNumberOfArrows(chr, 3);
                        incType();
                    }
                    break;
            }
        }

        public void incType() {
            type = (type % 3) + 1;
            chr.chatMessage(YELLOW, "New type: " + type);
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
    public void handleSkill(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int skillID = inPacket.decodeInt();
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if(skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        chr.chatMessage(YELLOW, "SkillID: " + skillID);
        byte slv = inPacket.decodeByte();
        if (isBuff(skillID)) {
            handleBuff(c, inPacket, skillID, slv);
        } else {
            switch(skillID) {
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
                o1.nOption = 10; //si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SoulArrow, o1);
                o2.nOption = si.getValue(epad, slv);
                o2.nOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EPAD, o2);
                break;
            case BOW_BOOSTER:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tStart = curTime;
                o1.tTerm = si.getValue(time, slv);
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
                ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, 1, 15, 15, 0, 0,
                        (int) System.currentTimeMillis(), 1, 0, new Position(0, 0));
                Mob mob = (Mob) chr.getField().getLifes().get( chr.getField().getLifes().size() - 1);
                int mobId = mob.getObjectId();
                chr.getClient().write(CField.createForceAtom(false, 0, chr.getId(), 10, true, mobId, mobId, forceAtomInfo,
                        new Rect(), 0, 300, mob.getPosition(), 0, mob.getPosition()));
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
