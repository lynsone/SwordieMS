package loaders;

import client.character.skills.Skill;
import client.character.skills.SkillInfo;
import client.character.skills.SkillStat;
import constants.ServerConstants;
import org.apache.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import util.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 12/20/2017.
 */
public class SkillData {

    private static Map<Integer, SkillInfo> skills = new HashMap<>();
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    @Saver(varName = "skills")
    public static void saveSkills(File file) {
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            dataOutputStream.writeShort(skills.size());
            for(Map.Entry<Integer, SkillInfo> entry : skills.entrySet()) {
                SkillInfo si = entry.getValue();
                dataOutputStream.writeInt(si.getSkillId());
                dataOutputStream.writeInt(si.getRootId());
                dataOutputStream.writeInt(si.getMaxLevel());
                dataOutputStream.writeInt(si.getMasterLevel());
                dataOutputStream.writeInt(si.getFixLevel());
                dataOutputStream.writeBoolean(si.isInvisible());
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Loader(varName = "skills")
    public static void loadSkills(File file, boolean exists) {
        if(!exists) {
            loadSkillsFromWz();
            saveSkills(file);
        } else {
            DataInputStream dataInputStream;
            try {
                dataInputStream = new DataInputStream(new FileInputStream(file));
                short size = dataInputStream.readShort();
                for (int i = 0; i < size; i++) {
                    SkillInfo skillInfo = new SkillInfo();
                    skillInfo.setSkillId(dataInputStream.readInt());
                    skillInfo.setRootId(dataInputStream.readInt());
                    skillInfo.setMaxLevel(dataInputStream.readInt());
                    skillInfo.setMasterLevel(dataInputStream.readInt());
                    skillInfo.setFixLevel(dataInputStream.readInt());
                    skillInfo.setInvisible(dataInputStream.readBoolean());
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
        skill.setMasterLevel(si.getMasterLevel());
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

    public static void main(String[] args) {
        loadSkillsFromWz();
    }
}
