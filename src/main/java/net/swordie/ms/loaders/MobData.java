package net.swordie.ms.loaders;

import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.ServerConstants;
import net.swordie.ms.life.drop.DropInfo;
import net.swordie.ms.life.mob.ForcedMobStat;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.skill.MobSkill;
import net.swordie.ms.life.mob.MobTemporaryStat;
import org.apache.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.XMLApi;

import java.io.*;
import java.util.*;

import static net.swordie.ms.life.mob.MobStat.*;

/**
 * Created on 12/30/2017.
 */
public class MobData {
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    private static Map<Integer, Mob> mobs = new HashMap<>();

    public static Map<Integer, Mob> getMobs() {
        return mobs;
    }

    public static void addMob(Mob mob) {
        getMobs().put(mob.getTemplateId(), mob);
    }

    public static void generateDatFiles() {
        log.info("Started generating mob data.");
        long start = System.currentTimeMillis();
        loadMobsFromWz();
        QuestData.linkMobData();
        saveToFile(ServerConstants.DAT_DIR + "/mobs");
        log.info(String.format("Completed generating mob data in %dms.", System.currentTimeMillis() - start));
    }

    public static Mob getMobById(int id) {
        return getMobs().getOrDefault(id, loadMobFromFile(id));
    }

    public static Mob getMobDeepCopyById(int id) {
        Mob from = getMobById(id);
        Mob copy = null;
        if (from != null) {
            copy = from.deepCopy();
        }
        return copy;
    }

    private static void saveToFile(String dir) {
        Util.makeDirIfAbsent(dir);
        for (Mob mob : getMobs().values()) {
            File file = new File(dir + "/" + mob.getTemplateId() + ".dat");
            try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
                ForcedMobStat fms = mob.getForcedMobStat();
                MobTemporaryStat mts = mob.getTemporaryStat();
                dataOutputStream.writeInt(mob.getTemplateId());
                dataOutputStream.writeInt(fms.getLevel());
                dataOutputStream.writeInt(mob.getFirstAttack());
                dataOutputStream.writeInt(mts.getNewOptionsByMobStat(BodyAttack) != null ? mts.getNewOptionsByMobStat(BodyAttack).nOption : 0);
                dataOutputStream.writeLong(fms.getMaxHP());
                dataOutputStream.writeLong(fms.getMaxMP());
                dataOutputStream.writeInt(fms.getPad());
                dataOutputStream.writeInt(fms.getPdr());
                dataOutputStream.writeInt(fms.getMad());
                dataOutputStream.writeInt(fms.getMdr());
                dataOutputStream.writeInt(fms.getAcc());
                dataOutputStream.writeInt(fms.getEva());
                dataOutputStream.writeInt(fms.getPushed());
                dataOutputStream.writeLong(fms.getExp());
                dataOutputStream.writeInt(mob.getSummonType());
                dataOutputStream.writeInt(mob.getCategory());
                dataOutputStream.writeUTF(mob.getMobType());
                dataOutputStream.writeInt(mob.getLink());
                dataOutputStream.writeInt(fms.getSpeed());
                dataOutputStream.writeDouble(mob.getFs());
                dataOutputStream.writeUTF(mob.getElemAttr());
                dataOutputStream.writeInt(mob.getHpTagColor());
                dataOutputStream.writeInt(mob.getHpTagBgcolor());
                dataOutputStream.writeBoolean(mob.isHPgaugeHide());
                dataOutputStream.writeBoolean(mob.isBoss());
                dataOutputStream.writeBoolean(mob.isUndead());
                dataOutputStream.writeBoolean(mob.isNoRegen());
                dataOutputStream.writeBoolean(mob.isInvincible());
                dataOutputStream.writeBoolean(mob.isHideName());
                dataOutputStream.writeBoolean(mob.isHideHP());
                dataOutputStream.writeBoolean(mob.isChangeable());
                dataOutputStream.writeBoolean(mob.isNoFlip());
                dataOutputStream.writeBoolean(mob.isTower());
                dataOutputStream.writeBoolean(mob.isPartyBonusMob());
                dataOutputStream.writeBoolean(mob.isUseReaction());
                dataOutputStream.writeBoolean(mob.isPublicReward());
                dataOutputStream.writeBoolean(mob.isMinion());
                dataOutputStream.writeBoolean(mob.isForward());
                dataOutputStream.writeBoolean(mob.isRemoteRange());
                dataOutputStream.writeBoolean(mob.isIgnoreFieldOut());
                dataOutputStream.writeBoolean(mob.isIgnoreMoveImpact());
                dataOutputStream.writeBoolean(mob.isSkeleton());
                dataOutputStream.writeBoolean(mob.isHideUserDamage());
                dataOutputStream.writeBoolean(mob.isIndividualReward());
                dataOutputStream.writeBoolean(mob.isNotConsideredFieldSet());
                dataOutputStream.writeBoolean(mob.isNoDoom());
                dataOutputStream.writeBoolean(mob.isUseCreateScript());
                dataOutputStream.writeBoolean(mob.isBlockUserMove());
                dataOutputStream.writeBoolean(mob.isKnockback());
                dataOutputStream.writeBoolean(mob.isRemoveQuest());
                dataOutputStream.writeBoolean(mob.isOnFieldSetSummon());
                dataOutputStream.writeBoolean(mob.isUserControll());
                dataOutputStream.writeBoolean(mob.isNoDebuff());
                dataOutputStream.writeBoolean(mob.isTargetFromSvr());
                dataOutputStream.writeUTF(mob.getChangeableMobType());
                dataOutputStream.writeInt(mob.getRareItemDropLevel());
                dataOutputStream.writeInt(mob.getHpRecovery());
                dataOutputStream.writeInt(mob.getMpRecovery());
                dataOutputStream.writeInt(mob.getMbookID());
                dataOutputStream.writeInt(mob.getChaseSpeed());
                dataOutputStream.writeInt(mob.getExplosiveReward());
                dataOutputStream.writeInt(mob.getCharismaEXP());
                dataOutputStream.writeInt(mob.getFlySpeed());
                dataOutputStream.writeInt(mob.getWp());
                dataOutputStream.writeInt(mob.getSummonEffect());
                dataOutputStream.writeInt(mob.getFixedDamage());
                dataOutputStream.writeInt(mob.getRemoveAfter());
                dataOutputStream.writeInt(mob.getBodyDisease());
                dataOutputStream.writeInt(mob.getBodyDiseaseLevel());
                dataOutputStream.writeInt(mob.getPoint());
                dataOutputStream.writeInt(mob.getPartyBonusR());
                dataOutputStream.writeInt(mob.getPassiveDisease());
                dataOutputStream.writeInt(mob.getCoolDamage());
                dataOutputStream.writeInt(mob.getCoolDamageProb());
                dataOutputStream.writeInt(mob.getDamageRecordQuest());
                dataOutputStream.writeInt(mob.getSealedCooltime());
                dataOutputStream.writeInt(mob.getWillEXP());
                dataOutputStream.writeUTF(mob.getFixedMoveDir());
                dataOutputStream.writeShort(mob.getQuests().size());
                for (int i : mob.getQuests()) {
                    dataOutputStream.writeInt(i);
                }
                dataOutputStream.writeShort(mob.getRevives().size());
                for (int i : mob.getRevives()) {
                    dataOutputStream.writeInt(i);
                }
                dataOutputStream.writeShort(mob.getSkills().size());
                for (MobSkill ms : mob.getSkills()) {
                    dataOutputStream.writeInt(ms.getSkillID());
                    dataOutputStream.writeInt(ms.getSkill());
                    dataOutputStream.writeByte(ms.getAction());
                    dataOutputStream.writeInt(ms.getLevel());
                    dataOutputStream.writeInt(ms.getEffectAfter());
                    dataOutputStream.writeInt(ms.getSkillAfter());
                    dataOutputStream.writeByte(ms.getPriority());
                    dataOutputStream.writeBoolean(ms.isOnlyFsm());
                    dataOutputStream.writeBoolean(ms.isOnlyOtherSkill());
                    dataOutputStream.writeBoolean(ms.isDoFirst());
                    dataOutputStream.writeBoolean(ms.isAfterDead());
                    dataOutputStream.writeInt(ms.getSkillForbid());
                    dataOutputStream.writeInt(ms.getAfterAttack());
                    dataOutputStream.writeInt(ms.getAfterAttackCount());
                    dataOutputStream.writeInt(ms.getAfterDelay());
                    dataOutputStream.writeInt(ms.getFixDamR());
                    dataOutputStream.writeInt(ms.getPreSkillIndex());
                    dataOutputStream.writeInt(ms.getPreSkillCount());
                    dataOutputStream.writeInt(ms.getCastTime());
                    dataOutputStream.writeInt(ms.getCoolTime());
                    dataOutputStream.writeInt(ms.getDelay());
                    dataOutputStream.writeInt(ms.getUseLimit());
                    dataOutputStream.writeUTF(ms.getInfo());
                    dataOutputStream.writeUTF(ms.getText());
                    dataOutputStream.writeUTF(ms.getSpeak());
                }
                dataOutputStream.writeInt(mts.getNewOptionsByMobStat(PImmune) != null ? mts.getNewOptionsByMobStat(PImmune).nOption : 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Mob loadMobFromFile(int id) {
        File file = new File(ServerConstants.DAT_DIR + "/mobs/" + id + ".dat");
        if (!file.exists()) {
            return null;
        }
        Mob mob = null;
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            mob = new Mob(dataInputStream.readInt());
            ForcedMobStat fms = mob.getForcedMobStat();
            fms.setLevel(dataInputStream.readInt());
            mob.setFirstAttack(dataInputStream.readInt());
            Option bodyOpt = new Option();
            bodyOpt.nOption = dataInputStream.readInt();
//            mts.addStatOptions(BodyAttack, bodyOpt);
            fms.setMaxHP(dataInputStream.readLong());
            fms.setMaxMP(dataInputStream.readLong());
            fms.setPad(dataInputStream.readInt());
            fms.setPdr(dataInputStream.readInt());
            fms.setMad(dataInputStream.readInt());
            fms.setMdr(dataInputStream.readInt());
            fms.setAcc(dataInputStream.readInt());
            fms.setEva(dataInputStream.readInt());
            fms.setPushed(dataInputStream.readInt());
            fms.setExp(dataInputStream.readLong());
            mob.setSummonType(dataInputStream.readInt());
            mob.setCategory(dataInputStream.readInt());
            mob.setMobType(dataInputStream.readUTF());
            mob.setLink(dataInputStream.readInt());
            fms.setSpeed(dataInputStream.readInt());
            mob.setFs(dataInputStream.readDouble());
            mob.setElemAttr(dataInputStream.readUTF());
            mob.setHpTagColor(dataInputStream.readInt());
            mob.setHpTagBgcolor(dataInputStream.readInt());
            mob.setHPgaugeHide(dataInputStream.readBoolean());
            mob.setBoss(dataInputStream.readBoolean());
            mob.setUndead(dataInputStream.readBoolean());
            mob.setNoRegen(dataInputStream.readBoolean());
            mob.setInvincible(dataInputStream.readBoolean());
            mob.setHideName(dataInputStream.readBoolean());
            mob.setHideHP(dataInputStream.readBoolean());
            mob.setChangeable(dataInputStream.readBoolean());
            mob.setNoFlip(dataInputStream.readBoolean());
            mob.setTower(dataInputStream.readBoolean());
            mob.setPartyBonusMob(dataInputStream.readBoolean());
            mob.setUseReaction(dataInputStream.readBoolean());
            mob.setPublicReward(dataInputStream.readBoolean());
            mob.setMinion(dataInputStream.readBoolean());
            mob.setForward(dataInputStream.readBoolean());
            mob.setRemoteRange(dataInputStream.readBoolean());
            mob.setIgnoreFieldOut(dataInputStream.readBoolean());
            mob.setIgnoreMoveImpact(dataInputStream.readBoolean());
            mob.setSkeleton(dataInputStream.readBoolean());
            mob.setHideUserDamage(dataInputStream.readBoolean());
            mob.setIndividualReward(dataInputStream.readBoolean());
            mob.setNotConsideredFieldSet(dataInputStream.readBoolean());
            mob.setNoDoom(dataInputStream.readBoolean());
            mob.setUseCreateScript(dataInputStream.readBoolean());
            mob.setBlockUserMove(dataInputStream.readBoolean());
            mob.setKnockback(dataInputStream.readBoolean());
            mob.setRemoveQuest(dataInputStream.readBoolean());
            mob.setOnFieldSetSummon(dataInputStream.readBoolean());
            mob.setUserControll(dataInputStream.readBoolean());
            mob.setNoDebuff(dataInputStream.readBoolean());
            mob.setTargetFromSvr(dataInputStream.readBoolean());
            mob.setChangeableMobType(dataInputStream.readUTF());
            mob.setRareItemDropLevel(dataInputStream.readInt());
            mob.setHpRecovery(dataInputStream.readInt());
            mob.setMpRecovery(dataInputStream.readInt());
            mob.setMbookID(dataInputStream.readInt());
            mob.setChaseSpeed(dataInputStream.readInt());
            mob.setExplosiveReward(dataInputStream.readInt());
            mob.setCharismaEXP(dataInputStream.readInt());
            mob.setFlySpeed(dataInputStream.readInt());
            mob.setWp(dataInputStream.readInt());
            mob.setSummonEffect(dataInputStream.readInt());
            mob.setFixedDamage(dataInputStream.readInt());
            mob.setRemoveAfter(dataInputStream.readInt());
            mob.setBodyDisease(dataInputStream.readInt());
            mob.setBodyDiseaseLevel(dataInputStream.readInt());
            mob.setPoint(dataInputStream.readInt());
            mob.setPartyBonusR(dataInputStream.readInt());
            mob.setPassiveDisease(dataInputStream.readInt());
            mob.setCoolDamage(dataInputStream.readInt());
            mob.setCoolDamageProb(dataInputStream.readInt());
            mob.setDamageRecordQuest(dataInputStream.readInt());
            mob.setSealedCooltime(dataInputStream.readInt());
            mob.setWillEXP(dataInputStream.readInt());
            mob.setFixedMoveDir(dataInputStream.readUTF());
            short size = dataInputStream.readShort();
            for (int i = 0; i < size; i++) {
                mob.addQuest(dataInputStream.readInt());
            }
            size = dataInputStream.readShort();
            for (int i = 0; i < size; i++) {
                mob.addRevive(dataInputStream.readInt());
            }
            short skillSize = dataInputStream.readShort();
            for (int i = 0; i < skillSize; i++) {
                MobSkill ms = new MobSkill();
                ms.setSkillID(dataInputStream.readInt());
                ms.setSkill(dataInputStream.readInt());
                ms.setAction(dataInputStream.readByte());
                ms.setLevel(dataInputStream.readInt());
                ms.setEffectAfter(dataInputStream.readInt());
                ms.setSkillAfter(dataInputStream.readInt());
                ms.setPriority(dataInputStream.readByte());
                ms.setOnlyFsm(dataInputStream.readBoolean());
                ms.setOnlyOtherSkill(dataInputStream.readBoolean());
                ms.setDoFirst(dataInputStream.readBoolean());
                ms.setAfterDead(dataInputStream.readBoolean());
                ms.setSkillForbid(dataInputStream.readInt());
                ms.setAfterAttack(dataInputStream.readInt());
                ms.setAfterAttackCount(dataInputStream.readInt());
                ms.setAfterDelay(dataInputStream.readInt());
                ms.setFixDamR(dataInputStream.readInt());
                ms.setPreSkillIndex(dataInputStream.readInt());
                ms.setPreSkillCount(dataInputStream.readInt());
                ms.setCastTime(dataInputStream.readInt());
                ms.setCoolTime(dataInputStream.readInt());
                ms.setDelay(dataInputStream.readInt());
                ms.setUseLimit(dataInputStream.readInt());
                ms.setInfo(dataInputStream.readUTF());
                ms.setText(dataInputStream.readUTF());
                ms.setSpeak(dataInputStream.readUTF());
                mob.addSkill(ms);
            }

            Option pImmuneOpt = new Option();
            pImmuneOpt.nOption = dataInputStream.readInt();
//            mts.addStatOptions(PImmune, pImmuneOpt);

            mob.setAppearType((byte) -2); // new spawn
            mob.setAfterAttack(-1);
            mob.setCurrentAction(-1);
            mob.setEliteGrade(-1);
            mob.setMoveAction((byte) 5); // normal monster?
            mob.setHp(fms.getMaxHP());
            mob.setMaxHp(fms.getMaxHP());
            mob.setMp(fms.getMaxMP());
            mob.setMaxMp(fms.getMaxMP());
            mob.setDrops(DropData.getDropInfoByID(mob.getTemplateId()));
            for (DropInfo di : mob.getDrops()) {
                di.generateNextDrop();
            }
            addMob(mob);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mob;
    }


    public static void loadMobsFromWz() {
        String wzDir1 = ServerConstants.WZ_DIR + "/Mob.wz";
        String wzDir2 = ServerConstants.WZ_DIR + "/Mob2.wz";
        File dir1 = new File(wzDir1);
        File[] files1 = dir1.listFiles();
        File dir2 = new File(wzDir2);
        File[] files2 = dir2.listFiles();
        List<File> files = new ArrayList<>();
        files.addAll(Arrays.asList(files1));
        files.addAll(Arrays.asList(files2));
        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }
            Document doc = XMLApi.getRoot(file);
            Node node = XMLApi.getAllChildren(doc).get(0);
            if (node == null) {
                continue;
            }
            int id = Integer.parseInt(XMLApi.getNamedAttribute(node, "name").replace(".img", ""));
            Node infoNode = XMLApi.getFirstChildByNameBF(node, "info");
            Mob mob = new Mob(id);
            ForcedMobStat fms = mob.getForcedMobStat();
            MobTemporaryStat mts = mob.getTemporaryStat();
            for (Node n : XMLApi.getAllChildren(infoNode)) {
                String name = XMLApi.getNamedAttribute(n, "name");
                String value = XMLApi.getNamedAttribute(n, "value");
                switch (name) {
                    case "level":
                    case "Level":
                        fms.setLevel(Integer.parseInt(value));
                        break;
                    case "firstAttack":
                    case "firstattack":
                        mob.setFirstAttack((int) Double.parseDouble(value));
                        break;
                    case "bodyAttack":
                    case "bodyattack": // ...
                        Option bodyOpt = new Option();
                        bodyOpt.nOption = Integer.parseInt(value);
                        mts.addStatOptions(BodyAttack, bodyOpt);
                        break;
                    case "maxHP":
                    case "finalmaxHP":
                        if (Util.isNumber(value)) {
                            fms.setMaxHP(Long.parseLong(value));
                        } else {
                            fms.setMaxHP(1337);
                        }
                        break;
                    case "maxMP":
                        fms.setMaxMP(Integer.parseInt(value));
                        break;
                    case "PADamage":
                        fms.setPad(Integer.parseInt(value));
                        break;
                    case "PDDamage":
//                            mts.addStatOptions(PDR, "nPDR", Integer.parseInt(value));
                        break;
                    case "PDRate":
                        fms.setPdr(Integer.parseInt(value));
                        break;
                    case "MADamage":
                        fms.setMad(Integer.parseInt(value));
                        break;
                    case "MDDamage":
//                            mts.addStatOptions(PDR, "nMDR", Integer.parseInt(value));
                        break;
                    case "MDRate":
                        fms.setMdr(Integer.parseInt(value));
                        break;
                    case "acc":
                        fms.setAcc(Integer.parseInt(value));
                        break;
                    case "eva":
                        fms.setEva(Integer.parseInt(value));
                        break;
                    case "pushed":
                        fms.setPushed(Integer.parseInt(value));
                        break;
                    case "exp":
                        fms.setExp(Integer.parseInt(value));
                        break;
                    case "summonType":
                        mob.setSummonType(Integer.parseInt(value));
                        break;
                    case "category":
                        mob.setCategory(Integer.parseInt(value));
                        break;
                    case "mobType":
                        mob.setMobType(value);
                        break;
                    case "link":
                        mob.setLink(Integer.parseInt(value));
                        break;
                    case "speed":
                    case "Speed":
                        fms.setSpeed(Integer.parseInt(value));
                        break;
                    case "fs":
                        mob.setFs(Double.parseDouble(value));
                        break;
                    case "elemAttr":
                        mob.setElemAttr(value);
                        break;
                    case "hpTagColor":
                        mob.setHpTagColor(Integer.parseInt(value));
                        break;
                    case "hpTagBgcolor":
                        mob.setHpTagBgcolor(Integer.parseInt(value));
                        break;
                    case "HPgaugeHide":
                        mob.setHPgaugeHide(Integer.parseInt(value) == 1);
                        break;
                    case "boss":
                        mob.setBoss(Integer.parseInt(value) == 1);
                        break;
                    case "undead":
                    case "Undead":
                        mob.setUndead(Integer.parseInt(value) == 1);
                        break;
                    case "noregen":
                        mob.setNoRegen(Integer.parseInt(value) == 1);
                        break;
                    case "invincible":
                    case "invincibe": // neckson pls
                        mob.setInvincible(Integer.parseInt(value) == 1);
                        break;
                    case "hideName":
                    case "hidename":
                        mob.setHideName(Integer.parseInt(value) == 1);
                        break;
                    case "hideHP":
                        mob.setHideHP(Integer.parseInt(value) == 1);
                        break;
                    case "changeableMob":
                        mob.setChangeable(Integer.parseInt(value) == 1);
                        break;
                    case "noFlip":
                        mob.setNoFlip(Integer.parseInt(value) == 1);
                        break;
                    case "tower":
                        mob.setTower(Integer.parseInt(value) == 1);
                        break;
                    case "partyBonusMob":
                        mob.setPartyBonusMob(Integer.parseInt(value) == 1);
                        break;
                    case "useReaction":
                        mob.setUseReaction(Integer.parseInt(value) == 1);
                        break;
                    case "publicReward":
                        mob.setPublicReward(Integer.parseInt(value) == 1);
                        break;
                    case "minion":
                        mob.setMinion(Integer.parseInt(value) == 1);
                        break;
                    case "forward":
                        mob.setForward(Integer.parseInt(value) == 1);
                        break;
                    case "isRemoteRange":
                        mob.setIsRemoteRange(Integer.parseInt(value) == 1);
                        break;
                    case "ignoreFieldOut":
                        mob.setIgnoreFieldOut(Integer.parseInt(value) == 1);
                        break;
                    case "ignoreMoveImpact":
                        mob.setIgnoreMoveImpact(Integer.parseInt(value) == 1);
                        break;
                    case "skeleton":
                        mob.setSkeleton(Integer.parseInt(value) == 1);
                        break;
                    case "hideUserDamage":
                        mob.setHideUserDamage(Integer.parseInt(value) == 1);
                        break;
                    case "individualReward":
                        mob.setIndividualReward(Integer.parseInt(value) == 1);
                        break;
                    case "notConsideredFieldSet":
                        mob.setNotConsideredFieldSet(Integer.parseInt(value) == 1);
                        break;
                    case "noDoom":
                        mob.setNoDoom(Integer.parseInt(value) == 1);
                        break;
                    case "useCreateScript":
                        mob.setUseCreateScript(Integer.parseInt(value) == 1);
                        break;
                    case "blockUserMove":
                        mob.setBlockUserMove(Integer.parseInt(value) == 1);
                        break;
                    case "knockback":
                        mob.setKnockback(Integer.parseInt(value) == 1);
                        break;
                    case "removeQuest":
                        mob.setRemoveQuest(Integer.parseInt(value) == 1);
                        break;
                    case "onFieldSetSummon":
                        mob.setOnFieldSetSummon(Integer.parseInt(value) == 1);
                        break;
                    case "userControll":
                        mob.setUserControll(Integer.parseInt(value) == 1);
                        break;
                    case "noDebuff":
                        mob.setNoDebuff(Integer.parseInt(value) == 1);
                        break;
                    case "targetFromSvr":
                        mob.setTargetFromSvr(Integer.parseInt(value) == 1);
                        break;
                    case "changeableMob_Type":
                        mob.setChangeableMobType(value);
                        break;
                    case "rareItemDropLevel":
                        mob.setRareItemDropLevel(Integer.parseInt(value));
                        break;
                    case "hpRecovery":
                        mob.setHpRecovery(Integer.parseInt(value));
                        break;
                    case "mpRecovery":
                        mob.setMpRecovery(Integer.parseInt(value));
                        break;
                    case "mbookID":
                        mob.setMbookID(Integer.parseInt(value));
                        break;
                    case "chaseSpeed":
                        mob.setChaseSpeed(Integer.parseInt(value));
                        break;
                    case "explosiveReward":
                        mob.setExplosiveReward(Integer.parseInt(value));
                        break;
                    case "charismaEXP":
                        mob.setCharismaEXP(Integer.parseInt(value));
                        break;
                    case "flyspeed":
                    case "flySpeed":
                    case "FlySpeed":
                        mob.setFlySpeed(Integer.parseInt(value));
                        break;
                    case "wp":
                        mob.setWp(Integer.parseInt(value));
                        break;
                    case "summonEffect":
                        mob.setSummonEffect(Integer.parseInt(value));
                        break;
                    case "fixedDamage":
                        mob.setFixedDamage(Integer.parseInt(value));
                        break;
                    case "removeAfter":
                        mob.setRemoveAfter(Integer.parseInt(value));
                        break;
                    case "bodyDisease":
                        mob.setBodyDisease(Integer.parseInt(value));
                        break;
                    case "bodyDiseaseLevel":
                        mob.setBodyDiseaseLevel(Integer.parseInt(value));
                        break;
                    case "point":
                        mob.setPoint(Integer.parseInt(value));
                        break;
                    case "partyBonusR":
                        mob.setPartyBonusR(Integer.parseInt(value));
                        break;
                    case "PassiveDisease":
                        mob.setPassiveDisease(Integer.parseInt(value));
                        break;
                    case "coolDamage":
                        mob.setCoolDamage(Integer.parseInt(value));
                        break;
                    case "coolDamageProb":
                        mob.setCoolDamageProb(Integer.parseInt(value));
                        break;
                    case "damageRecordQuest":
                        mob.setDamageRecordQuest(Integer.parseInt(value));
                        break;
                    case "sealedCooltime":
                        mob.setSealedCooltime(Integer.parseInt(value));
                        break;
                    case "willEXP":
                        mob.setWillEXP(Integer.parseInt(value));
                        break;
                    case "fixedMoveDir":
                        mob.setFixedMoveDir(value);
                        break;
                    case "PImmune":
                        Option immOpt = new Option();
                        immOpt.nOption = Integer.parseInt(value);
                        mts.addStatOptions(PImmune, immOpt);
                        break;
                    case "patrol":
                        mob.setPatrolMob(true);
                        break;
                    case "revive":
                        for (Node reviveNode : XMLApi.getAllChildren(n)) {
                            mob.addRevive(Integer.parseInt((XMLApi.getNamedAttribute(reviveNode, "value"))));
                        }
                        break;
                    case "skill":
                        for (Node skillIDNode : XMLApi.getAllChildren(n)) {
                            MobSkill mobSkill = new MobSkill();
                            mobSkill.setSkillID(Integer.parseInt(XMLApi.getNamedAttribute(skillIDNode, "name")));
//                            Node firstAttackNode = XMLApi.getFirstChildByNameBF(skillIDNode, "firstAttack");
//                            if(firstAttackNode != null) {
//                                mob.setFirstAttack(Integer.parseInt(XMLApi.getNamedAttribute(firstAttackNode, "value")) != 0);
//                                if(Integer.parseInt(XMLApi.getNamedAttribute(firstAttackNode, "value")) > 1) {
//                                    System.err.printf("Firstattack = %d", Integer.parseInt(XMLApi.getNamedAttribute(firstAttackNode, "value")));
//                                }
//                            }
                            for (Node skillInfoNode : XMLApi.getAllChildren(skillIDNode)) {
                                String skillNodeName = XMLApi.getNamedAttribute(skillInfoNode, "name");
                                String skillNodeValue = XMLApi.getNamedAttribute(skillInfoNode, "value");
                                switch (skillNodeName) {
                                    case "skill":
                                        mobSkill.setSkill(Integer.parseInt(skillNodeValue));
                                        break;
                                    case "action":
                                        mobSkill.setAction(Byte.parseByte(skillNodeValue));
                                        break;
                                    case "level":
                                        mobSkill.setLevel(Integer.parseInt(skillNodeValue));
                                        break;
                                    case "effectAfter":
                                        if (!skillNodeValue.equals("")) {
                                            mobSkill.setEffectAfter(Integer.parseInt(skillNodeValue));
                                        }
                                        break;
                                    case "skillAfter":
                                        mobSkill.setSkillAfter(Integer.parseInt(skillNodeValue));
                                        break;
                                    case "priority":
                                        mobSkill.setPriority(Byte.parseByte(skillNodeValue));
                                        break;
                                    case "onlyFsm":
                                        mobSkill.setOnlyFsm(Integer.parseInt(skillNodeValue) != 0);
                                        break;
                                    case "onlyOtherSkill":
                                        mobSkill.setOnlyOtherSkill(Integer.parseInt(skillNodeValue) != 0);
                                        break;
                                    case "doFirst":
                                        mobSkill.setDoFirst(Integer.parseInt(skillNodeValue) != 0);
                                        break;
                                    case "afterDead":
                                        mobSkill.setAfterDead(Integer.parseInt(skillNodeValue) != 0);
                                        break;
                                    case "skillForbid":
                                        mobSkill.setSkillForbid(Integer.parseInt(skillNodeValue));
                                        break;
                                    case "afterAttack":
                                        mobSkill.setAfterAttack(Integer.parseInt(skillNodeValue));
                                        break;
                                    case "afterAttackCount":
                                        mobSkill.setAfterAttackCount(Integer.parseInt(skillNodeValue));
                                        break;
                                    case "afterDelay":
                                        mobSkill.setAfterDelay(Integer.parseInt(skillNodeValue));
                                        break;
                                    case "fixDamR":
                                        mobSkill.setFixDamR(Integer.parseInt(skillNodeValue));
                                        break;
                                    case "preSkillIndex":
                                        mobSkill.setPreSkillIndex(Integer.parseInt(skillNodeValue));
                                        break;
                                    case "preSkillCount":
                                        mobSkill.setPreSkillCount(Integer.parseInt(skillNodeValue));
                                        break;
                                    case "castTime":
                                        mobSkill.setCastTime(Integer.parseInt(skillNodeValue));
                                        break;
                                    case "cooltime":
                                        mobSkill.setCoolTime(Integer.parseInt(skillNodeValue));
                                        break;
                                    case "delay":
                                        mobSkill.setDelay(Integer.parseInt(skillNodeValue));
                                        break;
                                    case "useLimit":
                                        mobSkill.setUseLimit(Integer.parseInt(skillNodeValue));
                                        break;
                                    case "info":
                                        mobSkill.setInfo(skillNodeValue);
                                        break;
                                    case "text":
                                        mobSkill.setText(skillNodeValue);
                                        break;
                                    case "speak":
                                        mobSkill.setSpeak(skillNodeValue);
                                        break;
                                    default:
                                        log.warn(String.format("Unknown skill node %s with value %s", skillNodeName, skillNodeValue));
                                }
                            }
                            mob.addSkill(mobSkill);
                        }
                        break;
                    case "selfDestruction":
                        // TODO Maybe more info?
                        mob.setSelfDestruction(true);
                        break;
                    case "speak":
                    case "thumbnail":
                    case "attack":
                    case "ban":
                    case "default":
                    case "defaultHP":
                    case "defaultMP":
                    case "passive":
                    case "firstAttackRange":
                    case "nonLevelCheckEVA":
                    case "nonLevelCheckACC":
                    case "changeImg":
                    case "showNotRemoteDam":
                    case "buff":
                    case "escort":
                    case "damagedBySelectedMob":
                    case "damagedByMob":
                    case "getCP":
                    case "loseItem":
                    case "0":
                    case "onlyNormalAttack":
                    case "notConsiderFieldSet":
                    case "overSpeed":
                    case "ignoreMovable":
                    case "jsonLoad":
                    case "fixedBodyAttackDamageR":
                    case "adjustLayerZ":
                    case "damagedBySelectedSkill":
                    case "option_damagedByMob":
                    case "bodyKnockBack":
                    case "straightMoveDir":
                    case "onlyHittedByCommonAttack":
                    case "invincibleAttack":
                    case "noChase":
                    case "notAttack":
                    case "alwaysInAffectedRect":
                    case "firstShowHPTag":
                    case "pointFPSMode":
                    case "11":
                    case "prevLinkMob":
                    case "option_skeleton":
                    case "lifePoint":
                    case "defenseMob":
                    case "forceChaseEscort":
                    case "damageModification":
                    case "randomFlyingBoss":
                    case "randomFlyingMob":
                    case "stalking":
                    case "minimap":
                    case "removeOnMiss":
                    case "fieldEffect":
                    case "onceReward":
                    case "onMobDeadQR":
                    case "peddlerMob":
                    case "peddlerDamR":
                    case "rewardSprinkle":
                    case "rewardSprinkleCount":
                    case "rewardSprinkleSpeed":
                    case "ignorMoveImpact":
                    case "dropItemPeriod":
                    case "hideMove":
                    case "atom":
                    case "smartPhase":
                    case "trans":
                    case "chaseEffect":
                    case "dualGauge":
                    case "noReturnByDead":
                    case "AngerGauge":
                    case "ChargeCount":
                    case "upperMostLayer":
                    case "cannotEvade":
                    case "phase":
                    case "doNotRemove":
                    case "healOnDestroy":
                    case "debuffobj":
                    case "obtacle":
                    case "mobZone":
                    case "weapon":
                    case "forcedFlip":
                    case "buffStack":
                    case "001":
                    case "002":
                    case "003":
                    case "004":
                    case "005":
                    case "006":
                    case "007":
                    case "008":
                    case "009":
                    case "010":
                    case "011":
                    case "onlySelectedSkill":
                    case "finalAdjustedDamageRate":
                    case "battlePvP":
                    case "mobJobCategory":
                    case "considerUserCount":
                    case "randomMob":
                    case "dieHeight":
                    case "dieHeightTime":
                    case "notChase":
                    case "fixedStat":
                    case "allyMob":
                    case "linkMob":
                    case "skelAniMixRate":
                    case "mobZoneObjID":
                    case "mobZoneObjType":
                    case "holdRange":
                    case "targetChaseTime":
                    case "copyCharacter":
                    case "disable":
                    case "underObject":
                    case "1582":
                    case "peddlerCount":
                    case "bodyAttackInfo":
                    case "mobZoneType":
                    case "largeDamageRecord":
                    case "considerUserCounter":
                    case "damageByObtacleAtom":
                    case "info":
                    case "cantPassByTeleport":
                    case "250000":
                    case "forward_direction":
                        break;
                    default:
                        log.warn(String.format("Unkown property %s with value %s.", name, value));
                }
                getMobs().put(mob.getTemplateId(), mob);
            }
        }
    }

    public static void main(String[] args) {
        generateDatFiles();
    }

    public static void clear() {
        getMobs().clear();
    }
}
