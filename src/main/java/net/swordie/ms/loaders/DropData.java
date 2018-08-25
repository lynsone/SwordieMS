package net.swordie.ms.loaders;

import net.swordie.ms.life.drop.DropInfo;
import net.swordie.ms.ServerConstants;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.XMLApi;

import java.io.*;
import java.util.*;

/**
 * Created on 2/21/2018.
 */
public class DropData {
    private static final Logger log = Logger.getLogger(DropData.class);

    private static Map<Integer, Set<DropInfo>> drops = new HashMap<>();

    public static void loadDropsFromWz() {
        String wzDir = ServerConstants.WZ_DIR + "/String.wz/MonsterBook.img.xml";
        File file = new File(wzDir);
        Document doc = XMLApi.getRoot(file);
        Node mainNode = XMLApi.getAllChildren(doc).get(0);
        for(Node node : XMLApi.getAllChildren(mainNode)) {
            int mobID = Integer.parseInt(XMLApi.getAttributes(node).get("name").replace(".img", ""));
            Node rewardNode = XMLApi.getFirstChildByNameBF(node, "reward");
            if(rewardNode != null) {
                for(Node reward : XMLApi.getAllChildren(rewardNode)) {
                    int rewardID = Integer.parseInt(XMLApi.getNamedAttribute(reward, "value"));
                    DropInfo dropInfo = new DropInfo(rewardID, 0, 100, 0); // default vals, 10% chance to drop
                    addDrop(mobID, dropInfo);
                }
            }
        }
    }

    public static void saveDropsToTxt(File file) {
            try(PrintWriter das = new PrintWriter(new FileOutputStream(file))) {
            for (Map.Entry<Integer, Set<DropInfo>> entry : getDrops().entrySet()) {
                int mobID = entry.getKey();
                Set<DropInfo> drops = entry.getValue();
                das.println(mobID);
                for (DropInfo di : drops) {
                    if (di.getQuestReq() == 0) {
                        das.println(String.format("%d %d", di.getItemID(), di.getChance()));
                    } else {
                        das.println(String.format("%d %d %d", di.getItemID(), di.getChance(), di.getQuestReq()));
                    }
                }
                das.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadCompleteDropsFromTxt(File file) {
        try (Scanner scanner = new Scanner(new FileInputStream(file))) {
            int mobID = 0;
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.contains("//")) {
                    continue;
                }
                String[] split = line.split(" ");
                /*
                Format:
                mobID (int)
                or:
                itemID (int) chance (int out of 1000) minCount (int) maxCount (int) questReq?
                itemID (int) chance (int out of 1000) questReq?(int)
                So, single line = mobID, else 2/3/4/5 ints
                 */
                if(split.length == 1 && split[0].equalsIgnoreCase("global")) {
                    mobID = -1;
                } else if(split.length == 1 && !split[0].equals("")) {
                    mobID = Integer.parseInt(split[0]);
                } else if(split.length >= 2) {
                    int itemID = Integer.parseInt(split[0]);
                    int chance = Integer.parseInt(split[1]);
                    int minQuant = 1;
                    int maxQuant = 1;
                    int questReq = 0;
                    if (split.length >= 4 && Util.isNumber(split[3])) { // hack to make you able to comment stuff
                        // min/max count
                        minQuant = Integer.parseInt(split[2]);
                        maxQuant = Integer.parseInt(split[3]);
                        if (split.length >= 5 && Util.isNumber(split[4])) {
                            questReq = Integer.parseInt(split[4]);
                        }
                    }
                    else if(split.length >= 3 && Util.isNumber(split[2])) {
                        questReq = Integer.parseInt(split[2]);
                    }
                    DropInfo dropInfo = new DropInfo(itemID, 0, chance, questReq, minQuant, maxQuant);
                    addDrop(mobID, dropInfo);
                }
            }
            for (DropInfo globalDrop : getDropInfoByID(-1)) {
                for (int key : getDrops().keySet()) {
                    if (key != -1) {
                        addDrop(key, globalDrop);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveDropsToDat(String dir) {
        Util.makeDirIfAbsent(dir);
        for (Map.Entry<Integer, Set<DropInfo>> entry : getDrops().entrySet()) {
            int mobID = entry.getKey();
            Set<DropInfo> drops = entry.getValue();
            File file = new File(String.format("%s/%d.dat", dir, mobID));
            try (DataOutputStream das = new DataOutputStream(new FileOutputStream(file))) {
                das.writeInt(mobID);
                das.writeShort(drops.size());
                for(DropInfo dropInfo : drops) {
                    das.writeInt(dropInfo.getItemID());
                    das.writeInt(dropInfo.getChance());
                    das.writeInt(dropInfo.getQuestReq());
                    das.writeInt(dropInfo.getMinQuant());
                    das.writeInt(dropInfo.getMaxQuant());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Set<DropInfo> getDropInfoByID(int mobID) {
        Set<DropInfo> drops = getCachedDropInfoById(mobID);
        if(drops == null) {
            File file = new File(String.format("%s/mobDrops/%d.dat", ServerConstants.DAT_DIR, mobID));
            if (file.exists()) {
                loadDropsFromFile(file);
                drops = getCachedDropInfoById(mobID);
            } else {
                // just take global drops otherwise
                drops = getDropInfoByID(-1);
            }
        }
        return drops;
    }

    private static Set<DropInfo> getCachedDropInfoById(int mobID) {
        return getDrops().getOrDefault(mobID, null);
    }

    public static void loadDropsFromFile(File file) {
        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            int mobID = dis.readInt();
            short size = dis.readShort();
            for (int i = 0; i < size; i++) {
                DropInfo dropInfo = new DropInfo();
                dropInfo.setItemID(dis.readInt());
                dropInfo.setChance(dis.readInt());
                dropInfo.setQuestReq(dis.readInt());
                dropInfo.setMinQuant(dis.readInt());
                dropInfo.setMaxQuant(dis.readInt());
                addDrop(mobID, dropInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addDrop(int mobID, DropInfo dropInfo) {
        if(!getDrops().containsKey(mobID)) {
            getDrops().put(mobID, new HashSet<>());
        }
        getDrops().get(mobID).add(dropInfo);
    }

    private static Map<Integer, Set<DropInfo>> getDrops() {
        return drops;
    }

    public static void generateTxtFromMonsterBook() {
        loadDropsFromWz();
        saveDropsToTxt(new File(ServerConstants.RESOURCES_DIR + "/mobDrops.txt"));
    }

    public static void generateDatFiles() {
        log.info("Started generating drop data.");
        long start = System.currentTimeMillis();
        loadCompleteDropsFromTxt(new File(ServerConstants.RESOURCES_DIR + "/mobDrops.txt"));
        saveDropsToDat(ServerConstants.DAT_DIR + "/mobDrops");
        log.info(String.format("Completed generating drop data in %dms.", System.currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        generateDatFiles();
    }

    public static String getLeftPaddedStr(final String in, final char padchar, final int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int x = in.length(); x < length; x++) {
            builder.append(padchar);
        }
        builder.append(in);
        return builder.toString();
    }

    public static void clear() {
        getDrops().clear();
    }
}
