package net.swordie.ms.loaders;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.loaders.containerclasses.AndroidInfo;
import net.swordie.ms.util.Loader;
import net.swordie.ms.util.Saver;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.XMLApi;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EtcData {

    private static final Logger log = Logger.getLogger(EtcData.class);

    private static Map<Integer, AndroidInfo> androidInfo = new HashMap<>();
    private static final Map<Integer, Integer> familiarSkills = new HashMap<>();


    public static void loadAndroidsFromWz() {
        String wzDir = ServerConstants.WZ_DIR + "/Etc.wz/Android";
        File dir = new File(wzDir);
        for (File file : dir.listFiles()) {
            AndroidInfo ai = new AndroidInfo(Integer.parseInt(file.getName().replace(".img.xml", "")));
            Node node = XMLApi.getAllChildren(XMLApi.getRoot(file)).get(0);
            List<Node> nodes = XMLApi.getAllChildren(node);
            for (Node mainNode : nodes) {
                String mainName = XMLApi.getNamedAttribute(mainNode, "name");
                switch (mainName) {
                    case "costume":
                        for (Node n : XMLApi.getAllChildren(mainNode)) {
                            String nName = XMLApi.getNamedAttribute(n, "name");
                            switch (nName) {
                                case "face":
                                    for (Node inner : XMLApi.getAllChildren(n)) {
                                        ai.addFace(Integer.parseInt(XMLApi.getNamedAttribute(inner, "value")) % 10000);
                                    }
                                    break;
                                case "hair":
                                    for (Node inner : XMLApi.getAllChildren(n)) {
                                        ai.addHair(Integer.parseInt(XMLApi.getNamedAttribute(inner, "value")) % 10000);
                                    }
                                    break;
                                case "skin":
                                    for (Node inner : XMLApi.getAllChildren(n)) {
                                        ai.addSkin(Integer.parseInt(XMLApi.getNamedAttribute(inner, "value")) % 1000);
                                    }
                                    break;
                            }
                        }
                        break;
                }
            }
            androidInfo.put(ai.getId(), ai);
        }
    }

    public static void saveAndroidInfo(String dir) {
        Util.makeDirIfAbsent(dir);
        for (AndroidInfo ai : androidInfo.values()) {
            File file = new File(String.format("%s/%d.dat", dir, ai.getId()));
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
                dos.writeInt(ai.getId());
                dos.writeInt(ai.getHairs().size());
                for (int hair : ai.getHairs()) {
                    dos.writeInt(hair);
                }
                dos.writeInt(ai.getFaces().size());
                for (int face : ai.getFaces()) {
                    dos.writeInt(face);
                }
                dos.writeInt(ai.getSkins().size());
                for (int skin : ai.getSkins()) {
                    dos.writeInt(skin);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static AndroidInfo getAndroidInfoById(int androidId) {
        if (androidInfo.containsKey(androidId)) {
            return androidInfo.get(androidId);
        }
        return loadAndroidInfoFromFile(String.format("%s/etc/android/%d.dat", ServerConstants.DAT_DIR, androidId));
    }

    private static AndroidInfo loadAndroidInfoFromFile(String file) {
        AndroidInfo ai = null;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            ai = new AndroidInfo(dis.readInt());
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                ai.addHair(dis.readInt());
            }
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                ai.addFace(dis.readInt());
            }
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                ai.addSkin(dis.readInt());
            }
            androidInfo.put(ai.getId(), ai);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ai;
    }

    public static void generateDatFiles() {
        log.info("Started generating etc data.");
        Util.makeDirIfAbsent(ServerConstants.DAT_DIR + "/etc");
        long start = System.currentTimeMillis();
        loadAndroidsFromWz();
        saveAndroidInfo(ServerConstants.DAT_DIR + "/etc/android");
        log.info(String.format("Completed generating etc data in %dms.", System.currentTimeMillis() - start));
    }

    public static void clear() {
        androidInfo.clear();
    }

    public static void main(String[] args) {
        generateDatFiles();
    }

    private static void loadFamiliarSkillsFromWz() {
        File file = new File(String.format("%s/Etc.wz/FamiliarInfo.img.xml", ServerConstants.WZ_DIR));
        Node root = XMLApi.getRoot(file);
        Node mainNode = XMLApi.getAllChildren(root).get(0);
        List<Node> nodes = XMLApi.getAllChildren(mainNode);
        for (Node node : nodes) {
            int id = Integer.parseInt(XMLApi.getNamedAttribute(node, "name"));
            Node skillIDNode = XMLApi.getFirstChildByNameBF(node, "passive");
            if (skillIDNode != null) {
                int skillID = Integer.parseInt(XMLApi.getNamedAttribute(skillIDNode, "value"));
                familiarSkills.put(id, skillID);
            }
        }
    }


    @Saver(varName = "familiarSkills")
    private static void saveFamiliarSkills(File file) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeInt(familiarSkills.size());
            for (Map.Entry<Integer, Integer> entry : familiarSkills.entrySet()) {
                dos.writeInt(entry.getKey()); // familiar ID
                dos.writeInt(entry.getValue()); // skill ID
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Loader(varName = "familiarSkills")
    public static void loadFamiliarSkills(File file, boolean exists) {
        if (!exists) {
            loadFamiliarSkillsFromWz();
            saveFamiliarSkills(file);
        } else {
            try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
                int gradeSkillSize = dis.readInt();
                for (int j = 0; j < gradeSkillSize; j++) {
                    int familiarID = dis.readInt();
                    int skillID = dis.readInt();
                    familiarSkills.put(familiarID, skillID);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getSkillByFamiliarID(int familiarID) {
        return familiarSkills.get(familiarID);
    }
}
