package loaders;

import client.character.skills.SkillStat;
import constants.ServerConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import util.Triple;
import util.Tuple;
import util.Util;
import util.XMLApi;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static enums.ChatMsgColour.YELLOW;

/**
 * Created on 1/11/2018.
 */
public class StringData {
    public static Map<Integer, SkillStringInfo> skillString = new HashMap<>();

    public static void loadStringsFromWz() {
        long start = System.currentTimeMillis();
        String wzDir = ServerConstants.WZ_DIR + "\\String.wz\\Skill.img.xml";
        File file = new File(wzDir);
        Document doc = XMLApi.getRoot(file);
        Node node = doc;
        List<Node> nodes = XMLApi.getAllChildren(node);
        for (Node topNode : nodes) {
            for(Node mainNode : XMLApi.getAllChildren(topNode)) {
                Node bookNameNode = XMLApi.getFirstChildByNameBF(mainNode, "bookName");
                if (bookNameNode != null) {
                    continue;
                }
                SkillStringInfo ssi = new SkillStringInfo();
                Node nameNode = XMLApi.getFirstChildByNameBF(mainNode, "name");
                if (nameNode != null) {
                    ssi.setName(XMLApi.getNamedAttribute(nameNode, "value"));
                }
                Node descNode = XMLApi.getFirstChildByNameBF(mainNode, "desc");
                if (descNode != null) {
                    ssi.setDesc(XMLApi.getNamedAttribute(descNode, "value"));
                }
                Node hNode = XMLApi.getFirstChildByNameBF(mainNode, "h");
                if (hNode != null) {
                    ssi.setH(XMLApi.getNamedAttribute(hNode, "value"));
                } else {
                    Node h1Node = XMLApi.getFirstChildByNameBF(mainNode, "h1");
                    if (h1Node != null) {
                        ssi.setH(XMLApi.getNamedAttribute(h1Node, "value"));
                    }
                }
                skillString.put(Integer.parseInt(XMLApi.getNamedAttribute(mainNode, "name")), ssi);
            }
        }
        System.out.println("Loaded skill strings in " + (System.currentTimeMillis() - start) + "ms.");
    }

    public static Map<Integer, SkillStringInfo> getSkillString() {
        return skillString;
    }

    public static void generateDat() {
        loadStringsFromWz();
        saveSkillStrings(ServerConstants.DAT_DIR + "\\strings");
    }

    private static void saveSkillStrings(String dir) {
        Util.makeDirIfAbsent(dir);
        File file = new File(dir + "\\skills");
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            dataOutputStream.writeShort(getSkillString().size());
            for(Map.Entry<Integer, SkillStringInfo> entry : getSkillString().entrySet()) {
                int id = entry.getKey();
                SkillStringInfo ssi = entry.getValue();
                dataOutputStream.writeInt(id);
                dataOutputStream.writeUTF(ssi.getName() == null ? "" : ssi.getName());
                dataOutputStream.writeUTF(ssi.getDesc());
                dataOutputStream.writeUTF(ssi.getH());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        generateDat();
    }


    public static SkillStringInfo getSkillStringById(int id) {
        for(int key : getSkillString().keySet()) {
            if(key == id) {
                return getSkillString().get(key);
            }
        }
        return null;
    }

    public static Map<Integer, SkillStringInfo> getSkillStringByName(String query) {
        Map<Integer, SkillStringInfo> res = new HashMap<>();
        for (Map.Entry<Integer, SkillStringInfo> entry : StringData.getSkillString().entrySet()) {
            int id = entry.getKey();
            SkillStringInfo ssi = entry.getValue();
            if(ssi.getName() == null) {
                continue;
            }
            String ssName = ssi.getName().toLowerCase();
            if (ssName.contains(query)) {
                res.put(id, ssi);
            }
        }
        return res;
    }
}
