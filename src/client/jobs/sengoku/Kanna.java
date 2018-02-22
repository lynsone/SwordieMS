package client.jobs.sengoku;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.field.Field;
import client.jobs.Job;
import client.life.AffectedArea;
import client.life.Summon;
import connection.InPacket;
import constants.JobConstants;
import enums.ChatMsgColour;
import loaders.SkillData;
import packet.WvsContext;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */

//TODO 38s on SET_FIELD

public class Kanna extends Job {

    public static final int HAKU = 40020109;

    public static final int RADIANT_PEACOCK = 42101003;

    public static final int KISHIN_SHOUKAN = 42111003; //summon
    public static final int BLOSSOM_BARRIER = 42111004; //AoE
    public static final int SOUL_SHEAR = 42111002; //Reactive Skill

    public static final int MONKEY_SPIRITS = 42120003; //Passive activation summon
    public static final int BELLFLOWER_BARRIER = 42121005; //AoE
    public static final int AKATUSKI_HERO_KANNA = 42121006;
    public static final int NINE_TAILED_FURY = 42121024; //Attacking Skill + Buff

    public static final int VERITABLE_PANDEMONIUM = 42121052; //Immobility Debuff
    public static final int PRINCESS_VOW_KANNA = 42121053;
    public static final int BLACKHEARTED_CURSE = 32121054;

    private Summon haku;

    private int[] buffs = new int[]{
            RADIANT_PEACOCK,
            KISHIN_SHOUKAN, //summon
            AKATUSKI_HERO_KANNA,
            NINE_TAILED_FURY,
            PRINCESS_VOW_KANNA,
            BLACKHEARTED_CURSE,
    };

    public Kanna(Char chr) {
        super(chr);
    }

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {

    }

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Summon summon;
        Field field;
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        switch (skillID) {
            case RADIANT_PEACOCK:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case KISHIN_SHOUKAN:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(true);
                summon.setMoveAbility((byte) 0);
                field.spawnSummon(summon);
                break;

            case AKATUSKI_HERO_KANNA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;

            case PRINCESS_VOW_KANNA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMaxDamageOver, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMaxDamageOver, o2);
                break;

            case BLACKHEARTED_CURSE:
                //TODO
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
                case BLOSSOM_BARRIER:
                case BELLFLOWER_BARRIER:
                    AffectedArea aa = AffectedArea.getPassiveAA(skillID, slv);
                    aa.setMobOrigin((byte) 0);
                    aa.setCharID(chr.getId());
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                    aa.setDelay((short) 3);
                    chr.getField().spawnAffectedArea(aa);
                    break;
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id == JobConstants.JobEnum.KANNA.getJobId() ||
                (id >= JobConstants.JobEnum.KANNA1.getJobId() && id <= JobConstants.JobEnum.KANNA4.getJobId());
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }


    private void getHakuSummon(int skillID, byte slv, Char chr) {
        if (chr.hasSkill(HAKU)) {
            Field field;
            haku = Summon.getSummonBy(c.getChr(), skillID, slv);
            field = c.getChr().getField();
            haku.setFlyMob(true);
            haku.setMoveAbility((byte) 0);
            field.spawnSummon(haku);
        }
    }

}