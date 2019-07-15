package net.swordie.ms.handlers.user;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.damage.DamageSkinSaveData;
import net.swordie.ms.client.character.damage.DamageSkinType;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.items.EquipAttribute;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.QuestConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.loaders.containerclasses.MakingSkillRecipe;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Randomizer;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.container.Tuple;
import net.swordie.ms.world.field.Field;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SkillHandler {

    private static final Logger log = Logger.getLogger(SkillHandler.class);



    @Handler(op = InHeader.USER_FORCE_ATOM_COLLISION)
    public static void handleForceAtomCollision(Client c, InPacket inPacket) {
        int size = inPacket.decodeInt();
        int idk2 = inPacket.decodeInt();
        for (int i = 0; i < size; i++) {
            int idk3 = inPacket.decodeInt();
            byte idk4 = inPacket.decodeByte();
            int mobID = inPacket.decodeInt();
            Mob mob = (Mob) c.getChr().getField().getLifeByObjectID(mobID);
            if (mob != null) {
//            mob.damage((long) 133337);
//            c.write(FieldPacket.damaged(mobID, (long) 133337, mob.getTemplateId(), (byte) 1, (int) mob.getHp(), (int) mob.getMaxHp()));
            }
        }
    }


    @Handler(op = InHeader.USER_ACTIVATE_DAMAGE_SKIN)
    public static void handleUserActivateDamageSkin(Client c, InPacket inPacket) {
        int damageSkin = inPacket.decodeInt();
        Char chr = c.getChr();
        chr.setDamageSkin(damageSkin);
//        c.write(User.setDamageSkin(chr));
    }

    @Handler(op = InHeader.USER_ACTIVATE_DAMAGE_SKIN__PREMIUM)
    public static void handleUserActivateDamageSkinPremium(Client c, InPacket inPacket) {
        int damageSkin = inPacket.decodeInt();
        Char chr = c.getChr();
        chr.setPremiumDamageSkin(damageSkin);
//        c.write(User.setPremiumDamageSkin(chr));
    }


    @Handler(op = InHeader.USER_DAMAGE_SKIN_SAVE_REQUEST)
    public static void handleUserDamageSkinSaveRequest(Char chr, InPacket inPacket) {
        byte typeVal = inPacket.decodeByte();
        short skinId = inPacket.decodeShort();
        DamageSkinType dst = DamageSkinType.getByVal(typeVal);
        if (dst == null || dst.getVal() >= DamageSkinType.Res_Success.getVal()) {
            log.error("Unknown DamageSkinType " + dst);
            return;
        }
        DamageSkinSaveData curSkin = chr.getDamageSkin();
        Account acc = chr.getAccount();
        QuestManager qm = chr.getQuestManager();
        switch (dst) {
            case Req_Active:
                DamageSkinSaveData dssd = acc.getDamageSkinBySkinID(skinId);
                if (dssd == null) {
                    chr.write(UserLocal.damageSkinSaveResult(dst, DamageSkinType.Res_Fail_Unknown, chr));
                    return;
                }
                if (curSkin != null && dssd.getDamageSkinID() == curSkin.getDamageSkinID()) {
                    chr.write(UserLocal.damageSkinSaveResult(dst, DamageSkinType.Res_Fail_AlreadyActive, chr));
                    return;
                }
                chr.setDamageSkin(dssd);
                chr.write(UserLocal.damageSkinSaveResult(dst, DamageSkinType.Res_Success, chr));

                Quest q = qm.getQuests().getOrDefault(QuestConstants.DAMAGE_SKIN, null);
                if (q == null) {
                    q = new Quest(QuestConstants.DAMAGE_SKIN, QuestStatus.Started);
                    qm.addQuest(q);
                }
                q.setQrValue(dssd.getDamageSkinID() + "");
                chr.write(WvsContext.questRecordMessage(q));
                break;
        }
    }

    @Handler(op = InHeader.USER_CREATE_AURA_BY_GRENADE)
    public static void handleUserCreateAuraByGrenade(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int objID = inPacket.decodeInt();
        int skillID = SkillConstants.getActualSkillIDfromSkillID(inPacket.decodeInt());
        if (!chr.hasSkill(skillID)) {
            chr.getOffenseManager().addOffense("Tried creating an aura by grenade with unavailable skill.", 0, skillID);
            return;
        }
        Position position = inPacket.decodePosition();
        byte isLeft = inPacket.decodeByte();
        SkillInfo fci = SkillData.getSkillInfoById(skillID);
        int slv = chr.getSkill(skillID).getCurrentLevel();
        AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, (byte) slv);
        aa.setPosition(position);
        aa.setSkillID(skillID);
        aa.setSlv((byte) slv);
        aa.setRect(aa.getPosition().getRectAround(fci.getRects().get(0)));
        chr.getField().spawnAffectedArea(aa);
    }

    @Handler(op = InHeader.USER_SKILL_USE_REQUEST)
    public static void handleUserSkillUseRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Field field = chr.getField();
        if (GameConstants.getMaplerunnerField(field.getId()) == -1 &&
                ((field.getFieldLimit() & FieldOption.SkillLimit.getVal()) > 0 ||
                        (field.getFieldLimit() & FieldOption.MoveSkillOnly.getVal()) > 0)) {
            chr.dispose();
            return;
        }
        inPacket.decodeInt(); // crc
        int skillID = inPacket.decodeInt();
        byte slv = inPacket.decodeByte();
        if (chr.applyMpCon(skillID, slv) && (chr.checkAndSetSkillCooltime(skillID) || chr.hasSkillCDBypass())) {
            chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillUse(skillID, slv, 0)));
            log.debug("SkillID: " + skillID);
            c.getChr().chatMessage(ChatType.Mob, "SkillID: " + skillID);
            Job sourceJobHandler = c.getChr().getJobHandler();
            SkillInfo si = SkillData.getSkillInfoById(skillID);
            if (si.isMassSpell() && sourceJobHandler.isBuff(skillID) && chr.getParty() != null) {
                Rect r = si.getFirstRect();
                if (r != null) {
                    Rect rectAround = chr.getRectAround(r);
                    for (PartyMember pm : chr.getParty().getOnlineMembers()) {
                        if (pm.getChr() != null
                                && pm.getFieldID() == chr.getFieldID()
                                && rectAround.hasPositionInside(pm.getChr().getPosition())) {
                            Char ptChr = pm.getChr();
                            Effect effect = Effect.skillAffected(skillID, slv, 0);
                            if (ptChr != chr) { // Caster shouldn't get the Affected Skill Effect
                                chr.getField().broadcastPacket(
                                        UserRemote.effect(ptChr.getId(), effect)
                                        , ptChr);
                                ptChr.write(UserPacket.effect(effect));
                            }
                            sourceJobHandler.handleSkill(pm.getChr().getClient(), skillID, slv, inPacket);
                        }
                    }
                }
            } else {
                sourceJobHandler.handleSkill(c, skillID, slv, inPacket);
            }
        }
       chr.dispose();
    }

    @Handler(op = InHeader.SET_SON_OF_LINKED_SKILL_REQUEST)
    public static void handleSetSonOfLinkedSkillRequest(Char chr, InPacket inPacket) {
        int skillID = inPacket.decodeInt();
        int sonID = inPacket.decodeInt();
        short jobID = chr.getJob();
        Account acc = chr.getAccount();
        Char son = acc.getCharacters().stream().filter(c -> c.getId() == sonID).findAny().orElse(null);
        // remove old link skill if another with the same skill exists
        acc.getLinkSkills().stream()
                .filter(ls -> SkillConstants.getOriginalOfLinkedSkill(ls.getLinkSkillID()) == skillID)
                .findAny()
                .ifPresent(oldLinkSkill -> acc.removeLinkSkillByOwnerID(oldLinkSkill.getOwnerID()));
        // if the skill is not null and we expect this link skill id from the given job
        int linkSkillID = SkillConstants.getLinkSkillByJob(jobID);
        if (son != null && SkillConstants.getOriginalOfLinkedSkill(linkSkillID) == skillID) {
            acc.addLinkSkill(chr, sonID, linkSkillID);
            chr.write(WvsContext.setSonOfLinkedSkillResult(LinkedSkillResultType.SetSonOfLinkedSkillResult_Success,
                    son.getId(), son.getName(), skillID, null));
        } else {
            chr.write(WvsContext.setSonOfLinkedSkillResult(LinkedSkillResultType.SetSonOfLinkedSkillResult_Fail_Unknown,
                    0, null, 0, null));
        }
    }

    @Handler(op = InHeader.USER_THROW_GRENADE)
    public static void handleUserThrowGrenade(Char chr, InPacket inPacket) {
        Position pos = inPacket.decodePositionInt();
        Position pos2 = inPacket.decodePositionInt();
        int keyDown = inPacket.decodeInt();
        int skillID = inPacket.decodeInt();
        int bySummonedID = inPacket.decodeInt(); // slv according to ida, but let's just take that server side
        boolean left = inPacket.decodeByte() != 0;
        int attackSpeed = inPacket.decodeInt();
        int grenadeID = inPacket.decodeInt();
        Skill skill = chr.getSkill(SkillConstants.getLinkedSkill(skillID));
        int slv = skill == null ? 0 : skill.getCurrentLevel();
        boolean success = true;
        if (SkillData.getSkillInfoById(SkillConstants.getActualSkillIDfromSkillID(skillID)).hasCooltime()) {
            if (chr.hasSkillOnCooldown(skillID)) {
                success = false;
            } else {
                chr.setSkillCooldown(skillID, (byte) slv);
            }
        }
        if (success) {
            chr.getField().broadcastPacket(UserRemote.throwGrenade(chr.getId(), grenadeID, pos, keyDown, skillID,
                    bySummonedID, slv, left, attackSpeed), chr);
            Job jobHandler = chr.getJobHandler();
            jobHandler.handleSkill(chr.getClient(), skillID, (byte) slv, inPacket);
        }
    }

    @Handler(op = InHeader.USER_DESTROY_GRENADE)
    public static void handleUserDestroyGrenade(Char chr, InPacket inPacket) {
        int grenadeID = inPacket.decodeInt();
        byte unk = inPacket.decodeByte();
        int skillID = inPacket.decodeInt();
        chr.getField().broadcastPacket(UserRemote.destroyGrenade(chr.getId(), grenadeID), chr);
    }

    @Handler(op = InHeader.USER_B2_BODY_REQUEST)
    public static void handleB2BodyRequest(Char chr, InPacket inPacket) {
        short type = inPacket.decodeShort();
        int ownerCID = inPacket.decodeInt();
        int bodyIdCounter = inPacket.decodeInt();
        Position offsetPos = inPacket.decodePosition();
        int skillID = inPacket.decodeInt();
        boolean isLeft = inPacket.decodeByte() != 0;
        inPacket.decodeByte();
        inPacket.decodeShort();
        inPacket.decodeShort();
        inPacket.decodeShort();
        Position forcedPos = inPacket.decodePositionInt();
    }

    @Handler(op = InHeader.MAKING_SKILL_REQUEST)
    public static void handleMakingSkillRequest(Char chr, InPacket inPacket) {
        int recipeID = inPacket.decodeInt();
        MakingSkillRecipe msr = SkillData.getRecipeById(recipeID);
        if (chr == null || msr == null || !msr.isAbleToBeUsedBy(chr)) {
            return;
        }
        List<Tuple<Integer, Integer>> itemResult = new ArrayList<>();
        for (Tuple<Integer, Integer> repice : msr.getIngredient()) {
            int itemID = repice.getLeft();
            int count = repice.getRight();
            if (chr.hasItemCount(itemID, count)) {
                chr.consumeItem(itemID, count);
                itemResult.add(new Tuple<>(itemID, -count));
            } else {
                chr.write(UserLocal.noticeMsg("You need more materials.", true));
                return;
            }
        }
        int reqSkillID = msr.getReqSkillID();
        Item crafted = null;
        MakingSkillRecipe.TargetElem target = new MakingSkillRecipe.TargetElem();
        MakingSkillResult result = MakingSkillResult.CRAFTING_FAILED;
        if (Randomizer.nextInt(100) < MakingSkillRecipe.getSuccessProb(reqSkillID, msr.getRecommandedSkillLevel(), chr.getMakingSkillLevel(reqSkillID)) || recipeID / 10000 <= 9201) {
            int rand = Randomizer.nextInt(100);
            List<MakingSkillRecipe.TargetElem> targets = msr.getTarget();
            while (true) {
                target = targets.get(Randomizer.rand(0, targets.size() - 1));
                if (target.getProbWeight() >= rand) {
                    break;
                } else {
                    rand = Randomizer.nextInt(100);
                }
            }
            crafted = ItemData.getItemDeepCopy(target.getItemID(), Randomizer.isSuccess(chr.getMakingSkillLevel(reqSkillID) * 2));
            if (crafted == null) {
                chr.getField().broadcastPacket(FieldPacket.makingSkillResult(chr.getId(), recipeID, MakingSkillResult.UNKNOWN_ERROR, target, 0));
                return;
            }
            crafted.setQuantity(target.getCount());
            result = MakingSkillResult.SUCESS_COOL;
            if (ItemConstants.isEquip(target.getItemID())) {
                ((Equip) crafted).addAttribute(EquipAttribute.Crafted);
                crafted.setOwner(chr.getName());
                crafted.setQuantity(1);// equipment shouldn't be more than one
            }
            if (msr.getExpiredPeriod() > 0) {
                crafted.setDateExpire(FileTime.fromLong(System.currentTimeMillis() + ((long) msr.getExpiredPeriod() * 60 * 1000)));
            }
            if (msr.isNeedOpenItem()) {
                chr.removeSkillAndSendPacket(recipeID);
            }
        }

        boolean success = result != MakingSkillResult.CRAFTING_FAILED;
        int incSkillProficiency = msr.getIncProficiency(chr, success);
        if (crafted != null) {
            chr.addItemToInventory(crafted);
            itemResult.add(new Tuple<>(crafted.getItemId(), crafted.getQuantity()));
        }
        chr.addMakingSkillProficiency(recipeID, incSkillProficiency);
        chr.addStatAndSendPacket(Stat.fatigue, msr.getIncFatigability());
        if (success) {
            Stat trait = Stat.craftEXP;
            switch (reqSkillID) {
                case 92000000:
                    trait = Stat.senseEXP;
                    break;
                case 92010000:
                    trait = Stat.willEXP;
                    break;
            }
            chr.addTraitExp(trait, (int) Math.pow(2, chr.getMakingSkillLevel(reqSkillID) + 2));
        }
        chr.getField().broadcastPacket(FieldPacket.makingSkillResult(chr.getId(), recipeID, result, target, incSkillProficiency));
        chr.write(UserPacket.effect(Effect.gainQuestItem(itemResult)));
    }


    @Handler(op = InHeader.USER_CREATE_AREA_DOT_REQUEST)
    public static void handleUserCreateAreaDoTRequest(Char chr, InPacket inPacket) {
        inPacket.decodeInt(); // unk
        int skillId = inPacket.decodeInt();
        int unk = inPacket.decodeInt(); // unk

        short loopSize = inPacket.decodeShort();
        for (int i = 0; i < loopSize; i++) {
            Rect rect = inPacket.decodeIntRect();
        }
        chr.getJobHandler().handleSkill(chr.getClient(), skillId, (byte) chr.getSkillLevel(skillId), inPacket);
    }


}
