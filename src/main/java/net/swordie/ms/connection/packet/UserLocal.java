package net.swordie.ms.connection.packet;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.damage.DamageSkinType;
import net.swordie.ms.client.character.skills.LarknessManager;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.ChatMsgColour;
import net.swordie.ms.enums.MessageType;
import net.swordie.ms.enums.StealMemoryType;
import net.swordie.ms.enums.StylishKillType;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.pet.Pet;
import net.swordie.ms.util.Position;

import java.util.Set;

/**
 * Created on 1/2/2018.
 */
public class UserLocal {

    public static OutPacket chatMsg(ChatMsgColour colour, String msg) {
        OutPacket outPacket = new OutPacket(OutHeader.CHAT_MSG);

        outPacket.encodeShort(colour.getVal());
        outPacket.encodeString(msg);

        return outPacket;
    }

    public static OutPacket jaguarActive(boolean active) {
        OutPacket outPacket = new OutPacket(OutHeader.JAGUAR_ACTIVE);

        outPacket.encodeByte(active);

        return outPacket;
    }

    public static OutPacket jaguarSkill(int skillID) {
        OutPacket outPacket = new OutPacket(OutHeader.JAGUAR_SKILL);

        outPacket.encodeInt(skillID);

        return outPacket;
    }

    public static OutPacket incLarknessReponse(LarknessManager larknessManager) {
        OutPacket outPacket = new OutPacket(OutHeader.INC_LARKNESS_RESPONSE);

        larknessManager.encode(outPacket);

        return outPacket;
    }

    public static OutPacket onRoyalGuardAttack(boolean attack) {
        OutPacket outPacket = new OutPacket(OutHeader.ROYAL_GUARD_ATTACK);

        outPacket.encodeByte(attack);

        return outPacket;
    }

    public static OutPacket onRWMultiChargeCancelRequest(byte unk, int skillID) {
        OutPacket outPacket = new OutPacket(OutHeader.SKILL_USE_RESULT);

        outPacket.encodeByte(unk);
        outPacket.encodeInt(skillID);

        return outPacket;
    }

    public static OutPacket onSetOffStateForOffSkill(int skillID) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_OFF_STATE_FOR_OFF_SKILL);

        outPacket.encodeInt(skillID);

        return outPacket;
    }

    public static OutPacket onResetStateForOffSkill() {
        OutPacket outPacket = new OutPacket(OutHeader.RESET_STATE_FOR_OFF_SKILL);

        outPacket.encodeInt(0);

        return outPacket;

    }

    public static OutPacket onEffectRechargeAB() {
        OutPacket outPacket = new OutPacket(OutHeader.EFFECT);

        outPacket.encodeByte(0x31);
        outPacket.encodeByte(1);

        return outPacket;
    }

    public static OutPacket modHayatoCombo(int amount) {
        OutPacket outPacket = new OutPacket(OutHeader.MOD_HAYATO_COMBO);

        outPacket.encodeInt(amount);

        return outPacket;
    }

    public static OutPacket incJudgementStack(byte amount) {
        OutPacket outPacket = new OutPacket(OutHeader.INC_JUDGEMENT_STACK_RESPONSE);

        outPacket.encodeByte(amount);

        return outPacket;
    }

    public static OutPacket changeStealMemoryResult(byte type, int stealManagerJobID, int position, int skillid, int stealSkillLv, int stealSkillMaxLv) {
        OutPacket outPacket = new OutPacket(OutHeader.CHANGE_STEAL_MEMORY_RESULT);
        StealMemoryType smType = StealMemoryType.getByVal(type);

        outPacket.encodeByte(1); //Set Excl Request
        outPacket.encodeByte(smType.getVal());    //Type

        switch (smType) {
            case STEAL_SKILL:
                outPacket.encodeInt(stealManagerJobID); //jobId  1~5 | 1 = 1stJob , 2 = 2ndJob ... ..
                outPacket.encodeInt(position); //impecMemSkillID // nPOS  0,1,2,3
                outPacket.encodeInt(skillid);//MagicCrash(Hero) //skill
                outPacket.encodeInt(stealSkillLv);   //StealSkill Lv
                outPacket.encodeInt(stealSkillMaxLv);   //StealSkill Max Lv
                break;
            case NO_TARGETS:
            case FAILED_UNK_REASON:
                break;
            case REMOVE_STEAL_MEMORY:
                outPacket.encodeInt(stealManagerJobID);
                outPacket.encodeInt(position);
                outPacket.encodeByte(0);
                break;
            case REMOVE_MEMORY_ALL_SLOT:
                outPacket.encodeInt(skillid);
                break;
            case REMOVE_ALL_MEMORY:
                break;
        }

        return outPacket;
    }

    public static OutPacket resultSetStealSkill(boolean set, int impecMemSkilLID, int skillId) {
        OutPacket outPacket = new OutPacket(OutHeader.RESULT_SET_STEAL_SKILL);

        outPacket.encodeByte(1); //Set Excl Request
        outPacket.encodeByte(set); //bSet
        outPacket.encodeInt(impecMemSkilLID); //impecMemSkilLID
        if(set) {
            outPacket.encodeInt(skillId); //skill Id
        }
        return outPacket;
    }

    public static OutPacket resultStealSkillList(Client c, int nPhantomStealResult, int targetChrId, int targetJobId) {
        OutPacket outPacket = new OutPacket(OutHeader.RESULT_STEAL_SKILL_LIST);
        Char chr = c.getChr();

        Char targetChr = chr.getField().getCharByID(targetChrId);
        Set<Skill> targetSkillsList = targetChr.getSkills();

        outPacket.encodeByte(0); //Set Excl Request
        outPacket.encodeInt(targetChrId);
        outPacket.encodeInt(nPhantomStealResult); //   Gets a check  if == 4,   else:   nPhantomStealWrongResult
        if(nPhantomStealResult == 4) {
            outPacket.encodeInt(targetJobId);
            outPacket.encodeInt(targetSkillsList.size());

            for (Skill skills : targetSkillsList) {
                // if v9 (index??) > 0
                outPacket.encodeInt(skills.getId());
            }
        }

        return outPacket;
    }

    public static OutPacket damageSkinSaveResult(DamageSkinType req, DamageSkinType res, Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.DAMAGE_SKIN_SAVE_RESULT);

        outPacket.encodeByte(req.getVal());
        if (req.getVal() <= 2) {
            outPacket.encodeByte(res.getVal());
            if (res == DamageSkinType.DamageSkinSave_Success) {
                chr.encodeDamageSkins(outPacket);
            }
        } else if (req == DamageSkinType.DamageSkinSaveReq_SendInfo) {
            chr.encodeDamageSkins(outPacket);
        }
        return outPacket;
    }

    public static OutPacket explosionAttack(int skillID, Position position, int mobID, int count) {
        OutPacket outPacket = new OutPacket(OutHeader.EXPLOSION_ATTACK);

        outPacket.encodeInt(skillID); //skillID
        outPacket.encodePositionInt(position); //Position
        outPacket.encodeInt(mobID); //MobID
        outPacket.encodeInt(count); //Count

        return outPacket;
    }

    public static OutPacket petActivateChange(int charID, Pet pet, boolean active, byte removedReason) {
        OutPacket outPacket = new OutPacket(OutHeader.PET_ACTIVATED);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(pet.getIdx());
        outPacket.encodeByte(active);
        if(active) {
            outPacket.encodeByte(true); // init
            pet.encode(outPacket);
        } else {
            outPacket.encodeByte(removedReason);
        }

        return outPacket;
    }

    public static OutPacket comboCounter(byte type, int comboCount, int mobID) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);
        outPacket.encodeByte(MessageType.STYLISH_KILL_MESSAGE.getVal());
        StylishKillType smType = StylishKillType.getByVal(type);

        outPacket.encodeByte(type); //1 for Combo   |  0 for MultiKill

        switch (smType) {
            case COMBO: //Combo Kill Message
                outPacket.encodeInt(comboCount); //count
                outPacket.encodeInt(mobID); //mobID
                break;

            case MULTI_KILL: //MultiKill Pop-up
                outPacket.encodeLong(comboCount); //nBonus
                outPacket.encodeInt(mobID); //count
                break;
        }

        return outPacket;
    }
}
