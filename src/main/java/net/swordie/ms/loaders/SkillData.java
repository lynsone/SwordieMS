package net.swordie.ms.loaders;

import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.SkillStat;
import net.swordie.ms.ServerConstants;
import net.swordie.ms.life.mob.skill.MobSkillStat;
import org.apache.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import net.swordie.ms.util.*;

import java.io.*;
import java.util.*;

/**
 * Created on 12/20/2017.
 */
public class SkillData {

    private static Map<Integer, SkillInfo> skills = new HashMap<>();
    private static Map<Integer, Map<Integer, Integer>> eliteMobSkills = new HashMap<>();
    private static Map<Short, Map<Short, MobSkillInfo>> mobSkillInfos = new HashMap<>();
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    @Saver(varName = "skills")
    public static void saveSkills(File file) {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            dataOutputStream.writeShort(skills.size());
            for(Map.Entry<Integer, SkillInfo> entry : skills.entrySet()) {
                SkillInfo si = entry.getValue();
                dataOutputStream.writeInt(si.getSkillId());
                dataOutputStream.writeInt(si.getRootId());
                dataOutputStream.writeInt(si.getMaxLevel());
                dataOutputStream.writeInt(si.getMasterLevel());
                dataOutputStream.writeInt(si.getFixLevel());
                dataOutputStream.writeBoolean(si.isInvisible());
                dataOutputStream.writeBoolean(si.isMassSpell());
                dataOutputStream.writeInt(si.getType());
                dataOutputStream.writeUTF(si.getElemAttr());
                dataOutputStream.writeInt(si.getHyper());
                dataOutputStream.writeInt(si.getHyperStat());
                dataOutputStream.writeInt(si.getVehicleId());
                dataOutputStream.writeInt(si.getReqTierPoint());
                dataOutputStream.writeBoolean(si.isNotCooltimeReset());
                dataOutputStream.writeBoolean(si.isNotIncBuffDuration());
                dataOutputStream.writeShort(si.getSkillStatInfo().size());
                for(Map.Entry<SkillStat, String> ssEntry : si.getSkillStatInfo().entrySet()) {
                    dataOutputStream.writeUTF(ssEntry.getKey().toString());
                    if (ssEntry.getValue() == null) {
                        dataOutputStream.writeUTF("");
                    } else {
                        dataOutputStream.writeUTF(ssEntry.getValue());
                    }
                }
                dataOutputStream.writeShort(si.getRects().size());
                for (Rect r : si.getRects()) {
                    dataOutputStream.writeInt(r.getLeft());
                    dataOutputStream.writeInt(r.getTop());
                    dataOutputStream.writeInt(r.getRight());
                    dataOutputStream.writeInt(r.getBottom());
                }
                dataOutputStream.writeShort(si.getPsdSkills().size());
                for (int i : si.getPsdSkills()) {
                    dataOutputStream.writeInt(i);
                }
                dataOutputStream.writeShort(si.getReqSkills().size());
                for (Map.Entry<Integer, Integer> reqSkill : si.getReqSkills().entrySet()) {
                    dataOutputStream.writeInt(reqSkill.getKey());
                    dataOutputStream.writeInt(reqSkill.getValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Loader(varName = "skills")
    public static void loadSkills(File file, boolean exists) {
        if(!exists) {
            loadSkillsFromWz();
            saveSkills(file);
        } else {
            try(DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
                short size = dataInputStream.readShort();
                for (int i = 0; i < size; i++) {
                    SkillInfo skillInfo = new SkillInfo();
                    skillInfo.setSkillId(dataInputStream.readInt());
                    skillInfo.setRootId(dataInputStream.readInt());
                    skillInfo.setMaxLevel(dataInputStream.readInt());
                    skillInfo.setMasterLevel(dataInputStream.readInt());
                    skillInfo.setFixLevel(dataInputStream.readInt());
                    skillInfo.setInvisible(dataInputStream.readBoolean());
                    skillInfo.setMassSpell(dataInputStream.readBoolean());
                    skillInfo.setType(dataInputStream.readInt());
                    skillInfo.setElemAttr(dataInputStream.readUTF());
                    skillInfo.setHyper(dataInputStream.readInt());
                    skillInfo.setHyperStat(dataInputStream.readInt());
                    skillInfo.setVehicleId(dataInputStream.readInt());
                    skillInfo.setReqTierPoint(dataInputStream.readInt());
                    skillInfo.setNotCooltimeReset(dataInputStream.readBoolean());
                    skillInfo.setNotIncBuffDuration(dataInputStream.readBoolean());
                    short ssSize = dataInputStream.readShort();
                    for (int j = 0; j < ssSize; j++) {
                        skillInfo.addSkillStatInfo(SkillStat.getSkillStatByString(
                                dataInputStream.readUTF()), dataInputStream.readUTF());
                    }
                    short rectSize = dataInputStream.readShort();
                    for (int j = 0; j < rectSize; j++) {
                        int left = dataInputStream.readInt();
                        int top = dataInputStream.readInt();
                        int right = dataInputStream.readInt();
                        int bottom = dataInputStream.readInt();
                        skillInfo.addRect(new Rect(left, top, right, bottom));
                    }
                    short psdSize = dataInputStream.readShort();
                    for (int j = 0; j < psdSize; j++) {
                        skillInfo.addPsdSkill(dataInputStream.readInt());
                    }
                    short reqSkillSize = dataInputStream.readShort();
                    for (int j = 0; j < reqSkillSize; j++) {
                        skillInfo.addReqSkill(dataInputStream.readInt(), dataInputStream.readInt());
                    }
                    getSkillInfos().put(skillInfo.getSkillId(), skillInfo);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void loadSkillsFromWz(){
        String wzDir = ServerConstants.WZ_DIR + "/Skill.wz";
        File dir = new File(wzDir);
        File[] files = dir.listFiles();
        for (File file : files) {
            Document doc = XMLApi.getRoot(file);
            Node node = doc;
            if(node == null) {
                continue;
            }
            List<Node> nodes = XMLApi.getAllChildren(node);
            for (Node mainNode : nodes) {
                Map<String, String> attributes = XMLApi.getAttributes(mainNode);
                String name = attributes.get("name").replace(".img","");
                int rootId;
                if(Util.isNumber(name)) {
                    rootId = Integer.parseInt(name);
                } else {
                    log.error(name + " is not a number.");
                    continue;
                }
                Node skillChild = XMLApi.getFirstChildByNameBF(mainNode, "skill");
                for(Node skillNode : XMLApi.getAllChildren(skillChild)) {
                    Map<String, String> skillAttributes = XMLApi.getAttributes(skillNode);
                    String skillIdName = skillAttributes.get("name").replace(".img","");
                    int skillId;
                    if(Util.isNumber(skillIdName)) {
                        SkillInfo skill = new SkillInfo();
                        skill.setRootId(rootId);
                        if(Util.isNumber(skillIdName)) {
                            skillId = Integer.parseInt(skillIdName);
                            skill.setSkillId(skillId);
                        } else {
                            log.error(name + " is not a number.");
                            continue;
                        }
                        // start main level info
                        Node masterLevel = XMLApi.getFirstChildByNameBF(skillNode, "masterLevel");
                        int masterLevelInt = -1;
                        if(masterLevel != null) {
                            masterLevelInt = Integer.parseInt(XMLApi.getAttributes(masterLevel).get("value"));
                        }
                        skill.setMasterLevel(masterLevelInt);
                        Node fixLv = XMLApi.getFirstChildByNameBF(skillNode, "fixLevel");
                        int fixLevel = -1;
                        if(fixLv != null) {
                            fixLevel = Integer.parseInt(XMLApi.getAttributes(fixLv).get("value"));
                        }
                        skill.setFixLevel(fixLevel);
                        Node invis = XMLApi.getFirstChildByNameBF(skillNode, "invisible");
                        boolean invisible = false;
                        if(invis != null) {
                            invisible = Integer.parseInt(XMLApi.getAttributes(invis).get("value")) == 1;
                        }
                        skill.setInvisible(invisible);
                        Node massSpellNode = XMLApi.getFirstChildByNameBF(skillNode, "massSpell");
                        boolean massSpell = false;
                        if(massSpellNode != null) {
                            massSpell = Integer.parseInt(XMLApi.getAttributes(massSpellNode).get("value")) == 1;
                        }
                        skill.setMassSpell(massSpell);
                        Node typeNode = XMLApi.getFirstChildByNameBF(skillNode, "type");
                        int type = 0;
                        if(typeNode != null) {
                            type = Integer.parseInt(XMLApi.getAttributes(typeNode).get("value"));
                        }
                        skill.setType(type);
                        Node topPsdSkillNode = XMLApi.getFirstChildByNameBF(skillNode, "psdSkill");
                        if(topPsdSkillNode != null) {
                            for (Node psdSkillNode : XMLApi.getAllChildren(topPsdSkillNode)) {
                                skill.addPsdSkill(Integer.parseInt(XMLApi.getAttributes(psdSkillNode).get("name")));
                            }
                        }
                        Node elemAttrNode = XMLApi.getFirstChildByNameBF(skillNode, "elemAttr");
                        if(elemAttrNode != null) {
                            skill.setElemAttr(XMLApi.getNamedAttribute(elemAttrNode, "value"));
                        } else {
                            skill.setElemAttr("");
                        }
                        Node hyperNode = XMLApi.getFirstChildByNameBF(skillNode, "hyper");
                        if(hyperNode != null) {
                            skill.setHyper(Integer.parseInt(XMLApi.getNamedAttribute(hyperNode, "value")));
                        }
                        Node hyperStatNode = XMLApi.getFirstChildByNameBF(skillNode, "hyperStat");
                        if(hyperStatNode != null) {
                            skill.setHyperStat(Integer.parseInt(XMLApi.getNamedAttribute(hyperStatNode, "value")));
                        }
                        Node vehicle = XMLApi.getFirstChildByNameBF(skillNode, "vehicleID");
                        int vehicleId = 0;
                        if(vehicle != null) {
                            vehicleId = Integer.parseInt(XMLApi.getAttributes(vehicle).get("value"));
                        }
                        skill.setVehicleId(vehicleId);
                        Node notCooltimeResetNode = XMLApi.getFirstChildByNameBF(skillNode, "notCooltimeReset");
                        if(notCooltimeResetNode != null) {
                            skill.setNotCooltimeReset(Integer.parseInt(XMLApi.getAttributes(notCooltimeResetNode).get("value")) != 0);
                        }
                        Node notIncBuffDurationNode = XMLApi.getFirstChildByNameBF(skillNode, "notIncBuffDuration");
                        if(notIncBuffDurationNode != null) {
                            skill.setNotIncBuffDuration(Integer.parseInt(XMLApi.getAttributes(notIncBuffDurationNode).get("value")) != 0);
                        }
                        Node reqNode = XMLApi.getFirstChildByNameBF(skillNode, "req");
                        if (reqNode != null) {
                            for (Node reqChild : XMLApi.getAllChildren(reqNode)) {
                                String childName = XMLApi.getNamedAttribute(reqChild, "name");
                                String childValue = XMLApi.getNamedAttribute(reqChild, "value");
                                if ("reqTierPoint".equalsIgnoreCase(childName)) {
                                    skill.setReqTierPoint(Integer.parseInt(childValue));
                                } else if (Util.isNumber(childName)) {
                                    skill.addReqSkill(Integer.parseInt(childName), Integer.parseInt(childValue));
                                }
                            }
                        }
                        // end main level info
                        // start "common" level info
                        Node common = XMLApi.getFirstChildByNameBF(skillNode, "common");
                        Map<String, String> values = new HashMap<>();
                        if(common != null) {
                            for(Node commonNode : XMLApi.getAllChildren(common)) {
                                Map<String, String> commonAttr = XMLApi.getAttributes(commonNode);
                                String nodeName = commonAttr.get("name");
                                if(nodeName.contains("lt") && nodeName.length() <= 3) {
                                    Node rbNode = XMLApi.getFirstChildByNameBF(common, nodeName.replace("lt", "rb"));
                                    int left = Integer.parseInt(XMLApi.getNamedAttribute(commonNode, "x"));
                                    int top = Integer.parseInt(XMLApi.getNamedAttribute(commonNode, "y"));
                                    int right = Integer.parseInt(XMLApi.getNamedAttribute(rbNode, "x"));
                                    int bottom = Integer.parseInt(XMLApi.getNamedAttribute(rbNode, "y"));
                                    skill.addRect(new Rect(left, top, right, bottom));
                                } else {
                                    values.put(commonAttr.get("name"), commonAttr.get("value"));
                                }
                            }
                        }
                        for(Map.Entry<String, String> entry : values.entrySet()) {
                            String statName = entry.getKey();
                            String statValue = entry.getValue();
                            if(statName.equals("maxLevel")) {
                                skill.setMaxLevel(Integer.parseInt(statValue));
                            } else {
                                SkillStat skillStat = SkillStat.getSkillStatByString(statName);
                                if(skillStat == null) {
                                    log.warn("Unknown SkillStat " + statName);
                                } else {
                                    skill.addSkillStatInfo(skillStat, statValue);
                                }
                            }
                        }
                        // end "common" level info
                        skills.put(skillId, skill);
                    }
                }
            }
        }
    }

    public static Map<Integer, SkillInfo> getSkillInfos() {
        return skills;
    }

    public static SkillInfo getSkillInfoById(int skillId) {
        return getSkillInfos().get(skillId);
    }

    public static Skill getSkillDeepCopyById(int skillId) {
        SkillInfo si = getSkillInfoById(skillId);
        if(si == null) {
            return null;
        }
        Skill skill = new Skill();
        skill.setSkillId(si.getSkillId());
        skill.setRootId(si.getRootId());
        skill.setMasterLevel(si.getMaxLevel());
//        skill.setMasterLevel(si.getMasterLevel()); // for now, maybe always?
        skill.setMaxLevel(si.getMaxLevel());
        if(si.getMasterLevel() <= 0) {
            skill.setMasterLevel(skill.getMaxLevel());
        }
        if(si.getFixLevel() > 0) {
            skill.setCurrentLevel(si.getFixLevel());
        } else {
            skill.setCurrentLevel(0);
        }
        return skill;
    }

    public static List<Skill> getSkillsByJob(short id) {
        List<Skill> res = new ArrayList<>();
        getSkillInfos().forEach((key, si) -> {
            if (si.getRootId() == id && !si.isInvisible()) {
                res.add(getSkillDeepCopyById(key));
            }
        });
        return res;
    }

    public static Map<Short, Map<Short, MobSkillInfo>> getMobSkillInfos() {
        return mobSkillInfos;
    }

    public static void addMobSkillInfo(MobSkillInfo msi) {
        getMobSkillInfos().computeIfAbsent(msi.getId(), k -> new HashMap<>());
        Map<Short, MobSkillInfo> msiLevelMap = getMobSkillInfos().get(msi.getId());
        msiLevelMap.put(msi.getLevel(), msi);
        getMobSkillInfos().put(msi.getId(), msiLevelMap);
    }

    private static void loadEliteMobSkillsFromWZ() {
        String wzDir = ServerConstants.WZ_DIR + "/Skill.wz/EliteMobSkill.img.xml";
        File file = new File(wzDir);
        Node root = XMLApi.getRoot(file);
        Node mainNode = XMLApi.getAllChildren(root).get(0);
        List<Node> nodes = XMLApi.getAllChildren(mainNode);
        for(Node node : nodes) {
            int grade = Integer.parseInt(XMLApi.getNamedAttribute(node, "name"));
            for (Node skillNode : XMLApi.getAllChildren(node)) {
                Node skillIdNode = XMLApi.getFirstChildByNameBF(skillNode, "skill");
                Node skillLevelNode = XMLApi.getFirstChildByNameBF(skillNode, "level");
                int skillID = Integer.parseInt(XMLApi.getNamedAttribute(skillIdNode, "value"));
                int skillLevel = Integer.parseInt(XMLApi.getNamedAttribute(skillLevelNode, "value"));
                addEliteMobSkill(grade, skillID, skillLevel);
            }
        }
    }

    private static void addEliteMobSkill(int grade, int skillID, int skillLevel) {
        if (!eliteMobSkills.containsKey(grade)) {
            eliteMobSkills.put(grade, new HashMap<>());
        }
        eliteMobSkills.get(grade).put(skillID, skillLevel);
    }

    @Saver(varName = "eliteMobSkills")
    private static void saveEliteMobSkills(File file) {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeInt(eliteMobSkills.size());
            for (Map.Entry<Integer, Map<Integer, Integer>> entry : eliteMobSkills.entrySet()) {
                dos.writeInt(entry.getKey()); // grade
                dos.writeInt(entry.getValue().size());
                for (Map.Entry<Integer, Integer> innerEntry : eliteMobSkills.get(entry.getKey()).entrySet()) {
                    dos.writeInt(innerEntry.getKey()); // skillID
                    dos.writeInt(innerEntry.getValue()); // skillLevel
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Loader(varName = "eliteMobSkills")
    public static void loadEliteMobSkills(File file, boolean exists) {
        if(!exists) {
            loadEliteMobSkillsFromWZ();
            saveEliteMobSkills(file);
        } else {
            try(DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
                int gradeSize = dis.readInt();
                for (int i = 0; i < gradeSize; i++) {
                    int grade = dis.readInt();
                    int gradeSkillSize = dis.readInt();
                    for (int j = 0; j < gradeSkillSize; j++) {
                        int skillID = dis.readInt();
                        int skillLevel = dis.readInt();
                        addEliteMobSkill(grade, skillID, skillLevel);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Map<Integer, Integer> getEliteMobSkillsByGrade(int grade) {
        return eliteMobSkills.getOrDefault(grade, null);
    }

    public static void loadMobSkillsFromWz() {
        String wzDir = ServerConstants.WZ_DIR + "/Skill.wz/MobSkill.img.xml";
        File file = new File(wzDir);
        Node root = XMLApi.getRoot(file);
        Node mainNode = XMLApi.getAllChildren(root).get(0);
        List<Node> nodes = XMLApi.getAllChildren(mainNode);
        Set<String> unks = new HashSet<>();
        for(Node node : nodes) {
            short skillID = Short.parseShort(XMLApi.getNamedAttribute(node, "name"));
            for(Node levelNode : XMLApi.getAllChildren(XMLApi.getFirstChildByNameBF(node, "level"))) {
                short level = Short.parseShort(XMLApi.getNamedAttribute(levelNode, "name"));
                MobSkillInfo msi = new MobSkillInfo();
                msi.setId(skillID);
                msi.setLevel(level);
                for(Node skillStatNode : XMLApi.getAllChildren(levelNode)) {
                    String name = XMLApi.getNamedAttribute(skillStatNode, "name");
                    String value = XMLApi.getNamedAttribute(skillStatNode, "value");
                    String x = XMLApi.getNamedAttribute(skillStatNode, "x");
                    String y = XMLApi.getNamedAttribute(skillStatNode, "y");
                    if(Util.isNumber(name)) {
                        msi.addIntToList(Integer.parseInt(value));
                        continue;
                    }
                    switch(name) {
                        case "x":
                            msi.putMobSkillStat(MobSkillStat.x, value);
                            break;
                        case "mpCon":
                            msi.putMobSkillStat(MobSkillStat.mpCon, value);
                            break;
                        case "interval":
                        case "inteval":
                            msi.putMobSkillStat(MobSkillStat.interval, value);
                            break;
                        case "hp":
                        case "HP":
                            msi.putMobSkillStat(MobSkillStat.hp, value);
                            break;
                        case "info":
                            msi.putMobSkillStat(MobSkillStat.info, value);
                            break;
                        case "y":
                            msi.putMobSkillStat(MobSkillStat.y, value);
                            break;
                        case "lt":
                            msi.setLt(new Position(Integer.parseInt(x), Integer.parseInt(y)));
                            break;
                        case "rb":
                            msi.setRb(new Position(Integer.parseInt(x), Integer.parseInt(y)));
                            break;
                        case "lt2":
                            msi.setLt2(new Position(Integer.parseInt(x), Integer.parseInt(y)));
                            break;
                        case "rb2":
                            msi.setRb2(new Position(Integer.parseInt(x), Integer.parseInt(y)));
                            break;
                        case "lt3":
                            msi.setLt3(new Position(Integer.parseInt(x), Integer.parseInt(y)));
                            break;
                        case "rb3":
                            msi.setRb3(new Position(Integer.parseInt(x), Integer.parseInt(y)));
                            break;
                        case "limit":
                            msi.putMobSkillStat(MobSkillStat.limit, value);
                            break;
                        case "broadCastScreenMsg":
                            msi.putMobSkillStat(MobSkillStat.broadCastScreenMsg, value);
                            break;
                        case "w":
                            msi.putMobSkillStat(MobSkillStat.w, value);
                            break;
                        case "z":
                            msi.putMobSkillStat(MobSkillStat.z, value);
                            break;
                        case "parsing":
                            msi.putMobSkillStat(MobSkillStat.parsing, value);
                            break;
                        case "prop":
                            msi.putMobSkillStat(MobSkillStat.prop, value);
                            break;
                        case "ignoreResist":
                            msi.putMobSkillStat(MobSkillStat.ignoreResist, value);
                            break;
                        case "count":
                            msi.putMobSkillStat(MobSkillStat.count, value);
                            break;
                        case "time":
                            msi.putMobSkillStat(MobSkillStat.time, value);
                            break;
                        case "targetAggro":
                            msi.putMobSkillStat(MobSkillStat.targetAggro, value);
                            break;
                        case "fieldScript":
                            msi.putMobSkillStat(MobSkillStat.fieldScript, value);
                            break;
                        case "elemAttr":
                            msi.putMobSkillStat(MobSkillStat.elemAttr, value);
                            break;
                        case "delay":
                            msi.putMobSkillStat(MobSkillStat.delay, value);
                            break;
                        case "rank":
                            msi.putMobSkillStat(MobSkillStat.rank, value);
                            break;
                        case "HPDeltaR":
                            msi.putMobSkillStat(MobSkillStat.HPDeltaR, value);
                            break;
                        case "summonEffect":
                            msi.putMobSkillStat(MobSkillStat.summonEffect, value);
                            break;
                        case "y2":
                            msi.putMobSkillStat(MobSkillStat.y2, value);
                            break;
                        case "q":
                            msi.putMobSkillStat(MobSkillStat.q, value);
                            break;
                        case "q2":
                            msi.putMobSkillStat(MobSkillStat.q2, value);
                            break;
                        case "s2":
                            msi.putMobSkillStat(MobSkillStat.s2, value);
                            break;
                        case "u":
                            msi.putMobSkillStat(MobSkillStat.u, value);
                            break;
                        case "u2":
                            msi.putMobSkillStat(MobSkillStat.u2, value);
                            break;
                        case "v":
                            msi.putMobSkillStat(MobSkillStat.v, value);
                            break;
                        case "z2":
                            msi.putMobSkillStat(MobSkillStat.z2, value);
                            break;
                        case "w2":
                            msi.putMobSkillStat(MobSkillStat.w2, value);
                            break;
                        case "skillAfter":
                            msi.putMobSkillStat(MobSkillStat.skillAfter, value);
                            break;
                        case "x2":
                            msi.putMobSkillStat(MobSkillStat.x2, value);
                            break;
                        case "script":
                            msi.putMobSkillStat(MobSkillStat.script, value);
                            break;
                        case "attackSuccessProp":
                            msi.putMobSkillStat(MobSkillStat.attackSuccessProp, value);
                            break;
                        case "bossHeal":
                            msi.putMobSkillStat(MobSkillStat.bossHeal, value);
                            break;
                        case "face":
                            msi.putMobSkillStat(MobSkillStat.face, value);
                            break;
                        case "callSkill":
                            msi.putMobSkillStat(MobSkillStat.callSkill, value);
                            break;
                        case "level":
                            msi.putMobSkillStat(MobSkillStat.level, value);
                            break;
                        case "linkHP":
                            msi.putMobSkillStat(MobSkillStat.linkHP, value);
                            break;
                        case "timeLimitedExchange":
                            msi.putMobSkillStat(MobSkillStat.timeLimitedExchange, value);
                            break;
                        case "summonDir":
                            msi.putMobSkillStat(MobSkillStat.summonDir, value);
                            break;
                        case "summonTerm":
                            msi.putMobSkillStat(MobSkillStat.summonTerm, value);
                            break;
                        case "castingTime":
                            msi.putMobSkillStat(MobSkillStat.castingTime, value);
                            break;
                        case "subTime":
                            msi.putMobSkillStat(MobSkillStat.subTime, value);
                            break;
                        case "reduceCasting":
                            msi.putMobSkillStat(MobSkillStat.reduceCasting, value);
                            break;
                        case "additionalTime":
                            msi.putMobSkillStat(MobSkillStat.additionalTime, value);
                            break;
                        case "force":
                            msi.putMobSkillStat(MobSkillStat.force, value);
                            break;
                        case "targetType":
                            msi.putMobSkillStat(MobSkillStat.targetType, value);
                            break;
                        case "forcex":
                            msi.putMobSkillStat(MobSkillStat.forcex, value);
                            break;
                        case "sideAttack":
                            msi.putMobSkillStat(MobSkillStat.sideAttack, value);
                            break;
                        case "afterEffect":
                        case "rangeGap":
                            msi.putMobSkillStat(MobSkillStat.rangeGap, value);
                            break;
                        case "noGravity":
                            msi.putMobSkillStat(MobSkillStat.noGravity, value);
                            break;
                        case "notDestroyByCollide":
                            msi.putMobSkillStat(MobSkillStat.notDestroyByCollide, value);
                            break;
                        case "effect":
                        case "mob":
                        case "mob0":
                        case "hit":
                        case "affected":
                        case "affectedOtherSkill":
                        case "crash":
                        case "effectToUser":
                        case "affected_after":
                        case "fixDamR":
                        case "limitMoveSkill":
                        case "tile":
                        case "footholdRect":
                        case "targetMobType":
                        case "areaWarning":
                        case "arType":
                        case "tremble":
                        case "otherSkill":
                        case "etcEffect":
                        case "etcEffect1":
                        case "etcEffect2":
                        case "etcEffect3":
                        case "bombInfo":
                        case "affected_pre":
                        case "fixDamR_BT":
                        case "affectedPhase":
                        case "screen":
                        case "notMissAttack":
                        case "ignoreEvasion":
                        case "fadeinfo":
                        case "randomTarget":
                        case "option_linkedMob":
                        case "affected0":
                        case "summonOnce":
                        case "head":
                        case "mobGroup":
                        case "exceptRange":
                        case "exchangeAttack":
                        case "range":
                        case "addDam":
                        case "special":
                        case "target":
                        case "fixedPos":
                        case "fixedDir":
                        case "i52":
                        case "start":
                        case "cancleType":
                        case "succeed":
                        case "failed":
                        case "during":
                        case "castingBarHide":
                        case "skillCancelAlways":
                        case "cancleDamage":
                        case "cancleDamageMultiplier":
                        case "bounceBall":
                        case "info2":
                        case "regen":
                        case "kockBackD":
                        case "areaSequenceDelay":
                        case "areaSequenceRandomSplit":
                        case "accelerationEffect":
                        case "repeatEffect":
                        case "brightness":
                        case "brightnessDuration":
                        case "success":
                        case "fail":
                        case "affected_S":
                        case "appear":
                        case "affected_XS":
                        case "disappear":
                        case "command":
                        case "damIncPos": // May be useful
                        case "option_poison": // ?
                        case "phaseUserCount": // I think this is done client side (users hit mapped to phase?)
                            break;
                        default:
                            if(!unks.contains(name)) {
                                log.warn(String.format("Unkown MobSkillStat %s with value %s (skill %d level %d)", name, value, skillID, level));
                                unks.add(name);
                            }
                            break;
                    }
                }
                addMobSkillInfo(msi);
            }
        }

    }

    public static void saveMobSkillsToDat(String dir) {
        Util.makeDirIfAbsent(dir);
        for(Map.Entry<Short, Map<Short, MobSkillInfo>> entry : getMobSkillInfos().entrySet()) {
            short id = entry.getKey();
            for(Map.Entry<Short, MobSkillInfo> entry2 : entry.getValue().entrySet()) {
                short level = entry2.getKey();
                MobSkillInfo msi = entry2.getValue();
                File file = new File(String.format("%s/%d-%d.dat", dir, id, level));
                try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
                    dos.writeShort(msi.getId());
                    dos.writeShort(msi.getLevel());
                    // wow this is ugly
                    // but it's just a de/encoder, so who cares
                    boolean hasLt = msi.getLt() != null;
                    dos.writeBoolean(hasLt);
                    if(hasLt) {
                        dos.writeInt(msi.getLt().getX());
                        dos.writeInt(msi.getLt().getY());
                    }
                    boolean hasRb = msi.getRb() != null;
                    dos.writeBoolean(hasRb);
                    if(hasRb) {
                        dos.writeInt(msi.getRb().getX());
                        dos.writeInt(msi.getRb().getY());
                    }
                    boolean hasLt2 = msi.getLt2() != null;
                    dos.writeBoolean(hasLt2);
                    if(hasLt2) {
                        dos.writeInt(msi.getLt2().getX());
                        dos.writeInt(msi.getLt2().getY());
                    }
                    boolean hasRb2 = msi.getRb2() != null;
                    dos.writeBoolean(hasRb2);
                    if(hasRb2) {
                        dos.writeInt(msi.getRb2().getX());
                        dos.writeInt(msi.getRb2().getY());
                    }
                    boolean hasLt3 = msi.getLt3() != null;
                    dos.writeBoolean(hasLt3);
                    if(hasLt3) {
                        dos.writeInt(msi.getLt3().getX());
                        dos.writeInt(msi.getLt3().getY());
                    }
                    boolean hasRb3 = msi.getRb3() != null;
                    dos.writeBoolean(hasRb3);
                    if(hasRb3) {
                        dos.writeInt(msi.getRb3().getX());
                        dos.writeInt(msi.getRb3().getY());
                    }
                    dos.writeShort(msi.getMobSkillStats().size());
                    for(Map.Entry<MobSkillStat, String> msiString : msi.getMobSkillStats().entrySet()) {
                        dos.writeByte(msiString.getKey().ordinal());
                        dos.writeUTF(msiString.getValue());
                    }
                    dos.writeShort(msi.getInts().size());
                    for(int i : msi.getInts()) {
                        dos.writeInt(i);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static MobSkillInfo loadMobSkillFromFile(File f) {
        MobSkillInfo msi = null;
        try(DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
            msi = new MobSkillInfo();
            msi.setId(dis.readShort());
            msi.setLevel(dis.readShort());
            boolean hasPos = dis.readBoolean();
            if(hasPos) {
                msi.setLt(new Position(dis.readInt(), dis.readInt()));
            }
            hasPos = dis.readBoolean();
            if(hasPos) {
                msi.setRb(new Position(dis.readInt(), dis.readInt()));
            }
            hasPos = dis.readBoolean();
            if(hasPos) {
                msi.setLt2(new Position(dis.readInt(), dis.readInt()));
            }
            hasPos = dis.readBoolean();
            if(hasPos) {
                msi.setRb2(new Position(dis.readInt(), dis.readInt()));
            }
            hasPos = dis.readBoolean();
            if(hasPos) {
                msi.setLt3(new Position(dis.readInt(), dis.readInt()));
            }
            hasPos = dis.readBoolean();
            if(hasPos) {
                msi.setRb3(new Position(dis.readInt(), dis.readInt()));
            }
            short size = dis.readShort();
            for (int i = 0; i < size; i++) {
               MobSkillStat mss = MobSkillStat.values()[dis.readByte()];
               String value = dis.readUTF();
               msi.putMobSkillStat(mss, value);
            }
            size = dis.readShort();
            for (int i = 0; i < size; i++) {
                msi.addIntToList(dis.readInt());
            }
            addMobSkillInfo(msi);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msi;
    }

    private static MobSkillInfo getMobSkillInfoByIdAndLevel(short id, short level) {
        Map<Short, MobSkillInfo> innerMap = getMobSkillInfos().get(id);
        if(innerMap == null || innerMap.get(level) == null) {
            return loadMobSkillInfoByIdAndLevel(id, level);
        } else  {
            return innerMap.get(level);
        }
    }

    public static MobSkillInfo getMobSkillInfoByIdAndLevel(int id, int level) {
        return getMobSkillInfoByIdAndLevel((short) id, (short) level);
    }

    private static MobSkillInfo loadMobSkillInfoByIdAndLevel(short id, short level) {
        File file = new File(String.format("%s/mobSkills/%d-%d.dat", ServerConstants.DAT_DIR, id, level));
        if(file.exists()) {
            return loadMobSkillFromFile(file);
        } else {
            log.error(String.format("Could not load mob skill %d (level %d).", id, level));
            return null;
        }
    }

    public static void generateDatFiles() {
        log.info("Started generating mob skill data.");
        long start = System.currentTimeMillis();
        loadMobSkillsFromWz();
        saveMobSkillsToDat(ServerConstants.DAT_DIR + "/mobSkills");
        log.info(String.format("Completed generating mob skill data in %dms.", System.currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        generateDatFiles();
    }

    public static void clear() {
        getMobSkillInfos().clear();
    }
}
