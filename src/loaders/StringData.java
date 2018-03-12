package loaders;

import client.character.skills.Skill;
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
    public static Map<Integer, String> itemStrings = new HashMap<>();

    public static void loadItemStringsFromWz() {
        long start = System.currentTimeMillis();
        String wzDir = ServerConstants.WZ_DIR + "/String.wz/";
        String[] files = new String[]{"Cash", "Consume", "Eqp", "Ins", "Pet", "Etc"};
        for(String fileDir : files) {
            File file = new File(wzDir + fileDir + ".img.xml");
            Document doc = XMLApi.getRoot(file);
            Node node = doc;
            List<Node> nodes = XMLApi.getAllChildren(node);
            for (Node topNode : nodes) {
                if(!fileDir.equalsIgnoreCase("eqp") &&
                        !fileDir.equalsIgnoreCase("etc")) {
                    for (Node mainNode : XMLApi.getAllChildren(topNode)) {
                        int id = Integer.parseInt(XMLApi.getNamedAttribute(mainNode, "name"));
                        String name = XMLApi.getNamedAttribute(XMLApi.getFirstChildByNameBF(mainNode, "name"), "value");
                        itemStrings.put(id, name);
                    }
                } else if(fileDir.equalsIgnoreCase("etc")) {
                    for (Node category : XMLApi.getAllChildren(topNode)) {
                        for (Node mainNode : XMLApi.getAllChildren(category)) {
                            int id = Integer.parseInt(XMLApi.getNamedAttribute(mainNode, "name"));
                            if(XMLApi.getFirstChildByNameBF(mainNode, "name") != null) {
                                String name = XMLApi.getNamedAttribute(XMLApi.getFirstChildByNameBF(mainNode, "name"), "value");
                                itemStrings.put(id, name);
                            }
                        }
                    }
                } else {
                    for(Node n : XMLApi.getAllChildren(topNode)) {
                        for (Node category : XMLApi.getAllChildren(n)) {
                            for (Node mainNode : XMLApi.getAllChildren(category)) {
                                int id = Integer.parseInt(XMLApi.getNamedAttribute(mainNode, "name"));
                                String name = XMLApi.getNamedAttribute(XMLApi.getFirstChildByNameBF(mainNode, "name"), "value");
                                itemStrings.put(id, name);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("[Info] Loaded item strings from wz in " + (System.currentTimeMillis() - start) + "ms.");
    }

    public static void loadStringsFromWz() {
        long start = System.currentTimeMillis();
        String wzDir = ServerConstants.WZ_DIR + "/String.wz/Skill.img.xml";
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
        System.out.println("[Info] Loaded skill strings in " + (System.currentTimeMillis() - start) + "ms.");
    }

    public static Map<Integer, SkillStringInfo> getSkillString() {
        return skillString;
    }

    public static void generateDatFiles() {
        loadStringsFromWz();
        loadItemStringsFromWz();
        saveSkillStrings(ServerConstants.DAT_DIR + "/strings");
        saveItemStrings(ServerConstants.DAT_DIR + "/strings");
    }

    private static void saveSkillStrings(String dir) {
        Util.makeDirIfAbsent(dir);
//        String fileDir = dir + "/skills";
//        Util.makeDirIfAbsent(fileDir);
        try {
            File file = new File(dir + "/skills.dat");
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            dataOutputStream.writeInt(getSkillString().size());
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

    public static void loadSkillStrings() {
        long start = System.currentTimeMillis();
        File file = new File(ServerConstants.DAT_DIR + "/strings/skills.dat");
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            int size = dataInputStream.readInt();
            for (int i = 0; i < size; i++) {
                int id = dataInputStream.readInt();
                SkillStringInfo ssi = new SkillStringInfo();
                ssi.setName(dataInputStream.readUTF());
                ssi.setDesc(dataInputStream.readUTF());
                ssi.setH(dataInputStream.readUTF());
                getSkillString().put(id, ssi);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[Info] Loaded skill strings from data file in " + (System.currentTimeMillis() - start) + "ms.");
    }

    private static void saveItemStrings(String dir) {
        Util.makeDirIfAbsent(dir);
        try {
            File file = new File(dir + "/items.dat");
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            dataOutputStream.writeInt(itemStrings.size());
            for(Map.Entry<Integer, String> entry : itemStrings.entrySet()) {
                int id = entry.getKey();
                String ssi = entry.getValue();
                dataOutputStream.writeInt(id);
                dataOutputStream.writeUTF(ssi);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadItemStrings() {
        long start = System.currentTimeMillis();
        File file = new File(ServerConstants.DAT_DIR + "/strings/items.dat");
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            int size = dataInputStream.readInt();
            for (int i = 0; i < size; i++) {
                int id = dataInputStream.readInt();
                String name = dataInputStream.readUTF();
                itemStrings.put(id, name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[Info] Loaded item strings from data file in " + (System.currentTimeMillis() - start) + "ms.");
    }

    public static void main(String[] args) {
//        generateDatFiles();
        loadSkillStrings();
        loadItemStrings();
    }


    public static SkillStringInfo getSkillStringById(int id) {
        for(int key : getSkillString().keySet()) {
            if(key == id) {
                return getSkillString().get(key);
            }
        }
        return null;
    }

    public static String getItemStringById(int id) {
        for(int key : itemStrings.keySet()) {
            if(key == id) {
                return itemStrings.get(key);
            }
        }
        return null;
    }

    public static Map<Integer, String> getItemStringByName(String query) {
        query = query.toLowerCase();
        Map<Integer, String> res = new HashMap<>();
        for (Map.Entry<Integer, String> entry : itemStrings.entrySet()) {
            int id = entry.getKey();
            String ssi = entry.getValue();
            if(ssi == null) {
                continue;
            }
            String ssName = ssi.toLowerCase();
            if (ssName.contains(query)) {
                res.put(id, ssi);
            }
        }
        return res;
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
