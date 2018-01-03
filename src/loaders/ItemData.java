package loaders;

import client.character.items.Equip;
import constants.ServerConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import util.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on 11/17/2017.
 */
public class ItemData {
    public static List<Integer> itemIds = new ArrayList<>();
    public static List<Equip> equips = new ArrayList<>();
    public static List<Integer> equipItemIds = new ArrayList<>();

//    @Loader(varName = "itemIds")
    public static void loadItemIDs() {
        String wzDir = ServerConstants.WZ_DIR + "\\Item.wz";
        String[] subMaps = new String[]{"Cash", "Consume", "Etc", "Install", "Pet", "Special"};
        for (String subMap : subMaps) {
            File subDir = new File(wzDir + "\\" + subMap);
            File[] files = subDir.listFiles();
            for (File file : files) {
                if (file.getName().contains("MaplePoint")) {
                    continue;
                }
                Document doc = XMLApi.getRoot(file);
                Node node = doc.getFirstChild();
                if (!subMap.equals("Pet")) {
                    List<Node> nodes = XMLApi.getAllChildren(node);
                    for (Node n : nodes) {
                        Map<String, String> attributes = XMLApi.getAttributes(n);
                        String name = attributes.get("name");
                        if (name != null) {
                            itemIds.add(Integer.parseInt(name));
                        }

                    }
                } else {
                    itemIds.add(Integer.valueOf(
                            node.getAttributes().getNamedItem("name").getNodeValue().replace(".img", "")));
                }
            }

        }
    }

    /**
     * Creates a new Equip given an itemId.
     * @param itemId The itemId of the wanted equip.
     * @return A deep copy of the default values of the corresponding Equip, or null if there is no equip with itemId
     * <code>itemId</code>.
     */
    public static Equip getEquipDeepCopyFromId(int itemId) {
        Equip ret = getEquipById(itemId);
        if(ret != null) {
            ret = ret.deepCopy();
        }
        return ret;
    }

    public static Equip getEquipById(int itemId) {
        return getEquips().stream().filter(eq -> eq.getItemId() == itemId).findFirst().orElse(getEquipFromFile(itemId));
    }

    private static Equip getEquipFromFile(int itemId) {
        String fieldDir = ServerConstants.DAT_DIR + "\\equips\\" + itemId + ".dat";
        File file = new File(fieldDir);
        if(!file.exists()) {
            System.err.println("Could not find equip " + itemId);
            return null;
        } else {
            return readEquipFromFile(file);
        }
    }

    private static Equip readEquipFromFile(File file) {
        Equip equip = null;
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            int itemId = dataInputStream.readInt();
            String islot = dataInputStream.readUTF();
            String vslot = dataInputStream.readUTF();
            short rJob = dataInputStream.readShort();
            short rLevel = dataInputStream.readShort();
            short rStr = dataInputStream.readShort();
            short rDex = dataInputStream.readShort();
            short rInt = dataInputStream.readShort();
            short rLuk = dataInputStream.readShort();
            short rPop = dataInputStream.readShort();
            short iStr = dataInputStream.readShort();
            short iDex = dataInputStream.readShort();
            short iInt = dataInputStream.readShort();
            short iLuk = dataInputStream.readShort();
            short iPDD = dataInputStream.readShort();
            short iMDD = dataInputStream.readShort();
            short iMaxHp = dataInputStream.readShort();
            short iMaxMp = dataInputStream.readShort();
            short iPad = dataInputStream.readShort();
            short iMad = dataInputStream.readShort();
            short iEva = dataInputStream.readShort();
            short iAcc = dataInputStream.readShort();
            short iCraft = dataInputStream.readShort();
            short iSpeed = dataInputStream.readShort();
            short iJump = dataInputStream.readShort();
            short damR = dataInputStream.readShort();
            short statR = dataInputStream.readShort();
            short tuc = dataInputStream.readShort();
            int charmEXP = dataInputStream.readInt();
            int setItemID = dataInputStream.readInt();
            int price = dataInputStream.readInt();
            int attackSpeed = dataInputStream.readInt();
            boolean cash = dataInputStream.readBoolean();
            boolean expireOnLogout = dataInputStream.readBoolean();
            boolean exItem = dataInputStream.readBoolean();
            boolean notSale = dataInputStream.readBoolean();
            boolean only = dataInputStream.readBoolean();
            boolean tradeBlock = dataInputStream.readBoolean();
            boolean equipTradeBlock = dataInputStream.readBoolean();
            boolean fixedPotential = dataInputStream.readBoolean();
            short optionLength = dataInputStream.readShort();
            List<Integer> options = new ArrayList<>(optionLength);
            for (int i = 0; i < optionLength; i++) {
                options.add(dataInputStream.readInt());
            }
            for (int i = 0; i < 7 - optionLength; i++) {
                options.add(0);
            }
            int fixedGrade = dataInputStream.readInt();
            int specialGrade = dataInputStream.readInt();
            equip = new Equip(itemId, -1, -1, new FileTime(-1), -1,
                    null, new FileTime(-1), 0, (short) 0, (short) 0, iStr, iDex, iInt,
                    iLuk, iMaxHp, iMaxMp, iPad, iMad, iPDD, iMDD, iAcc, iEva, iCraft,
                    iSpeed, iJump, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0,
                    (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, damR, statR, (short) 0, (short) 0,
                    (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, rStr, rDex, rInt,
                    rLuk, rLevel, rJob, rPop, cash,
                    islot, vslot, fixedGrade, options, specialGrade, fixedPotential, tradeBlock, only,
                    notSale, attackSpeed, price, tuc, charmEXP, expireOnLogout, setItemID, exItem, equipTradeBlock, "");
            equips.add(equip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return equip;
    }

    @Saver(varName = "equips")
    public static void saveEquips(String dir) {
        Util.makeDirIfAbsent(dir);
        DataOutputStream dataOutputStream;
        try {
            for (Equip equip : getEquips()) {
                dataOutputStream = new DataOutputStream(new FileOutputStream(dir + "\\" + equip.getItemId() + ".dat"));
                dataOutputStream.writeInt(equip.getItemId());
                dataOutputStream.writeUTF(equip.getiSlot());
                dataOutputStream.writeUTF(equip.getvSlot());
                dataOutputStream.writeShort(equip.getrJob());
                dataOutputStream.writeShort(equip.getrLevel());
                dataOutputStream.writeShort(equip.getrStr());
                dataOutputStream.writeShort(equip.getrDex());
                dataOutputStream.writeShort(equip.getrInt());
                dataOutputStream.writeShort(equip.getrLuk());
                dataOutputStream.writeShort(equip.getrPop());
                dataOutputStream.writeShort(equip.getiStr());
                dataOutputStream.writeShort(equip.getiDex());
                dataOutputStream.writeShort(equip.getiInt());
                dataOutputStream.writeShort(equip.getiLuk());
                dataOutputStream.writeShort(equip.getiPDD());
                dataOutputStream.writeShort(equip.getiMDD());
                dataOutputStream.writeShort(equip.getiMaxHp());
                dataOutputStream.writeShort(equip.getiMaxMp());
                dataOutputStream.writeShort(equip.getiPad());
                dataOutputStream.writeShort(equip.getiMad());
                dataOutputStream.writeShort(equip.getiEva());
                dataOutputStream.writeShort(equip.getiAcc());
                dataOutputStream.writeShort(equip.getiCraft());
                dataOutputStream.writeShort(equip.getiSpeed());
                dataOutputStream.writeShort(equip.getiJump());
                dataOutputStream.writeShort(equip.getDamR());
                dataOutputStream.writeShort(equip.getStatR());
                dataOutputStream.writeShort(equip.getTuc());
                dataOutputStream.writeInt(equip.getCharmEXP());
                dataOutputStream.writeInt(equip.getSetItemID());
                dataOutputStream.writeInt(equip.getPrice());
                dataOutputStream.writeInt(equip.getAttackSpeed());
                dataOutputStream.writeBoolean(equip.isCash());
                dataOutputStream.writeBoolean(equip.isExpireOnLogout());
                dataOutputStream.writeBoolean(equip.isExItem());
                dataOutputStream.writeBoolean(equip.isNotSale());
                dataOutputStream.writeBoolean(equip.isOnly());
                dataOutputStream.writeBoolean(equip.isTradeBlock());
                dataOutputStream.writeBoolean(equip.isEquipTradeBlock());
                dataOutputStream.writeBoolean(equip.isFixedPotential());
                dataOutputStream.writeShort(equip.getOptions().size());
                for(int i : equip.getOptions()) {
                    dataOutputStream.writeInt(i);
                }
                dataOutputStream.writeInt(equip.getFixedGrade());
                dataOutputStream.writeInt(equip.getSpecialGrade());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Loader(varName = "equips")
    public static void loadEquips(File file, boolean exists) {
        if(!exists) {
            loadEquipsFromWz();
            saveEquips(ServerConstants.DAT_DIR + "\\equips");
        } else {
            try {
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
                short size = dataInputStream.readShort();
                for (short kappa = 0; kappa < size; kappa++) {
                    int itemId = dataInputStream.readInt();
                    String islot = dataInputStream.readUTF();
                    String vslot = dataInputStream.readUTF();
                    short rJob = dataInputStream.readShort();
                    short rLevel = dataInputStream.readShort();
                    short rStr = dataInputStream.readShort();
                    short rDex = dataInputStream.readShort();
                    short rInt = dataInputStream.readShort();
                    short rLuk = dataInputStream.readShort();
                    short rPop = dataInputStream.readShort();
                    short iStr = dataInputStream.readShort();
                    short iDex = dataInputStream.readShort();
                    short iInt = dataInputStream.readShort();
                    short iLuk = dataInputStream.readShort();
                    short iPDD = dataInputStream.readShort();
                    short iMDD = dataInputStream.readShort();
                    short iMaxHp = dataInputStream.readShort();
                    short iMaxMp = dataInputStream.readShort();
                    short iPad = dataInputStream.readShort();
                    short iMad = dataInputStream.readShort();
                    short iEva = dataInputStream.readShort();
                    short iAcc = dataInputStream.readShort();
                    short iCraft = dataInputStream.readShort();
                    short iSpeed = dataInputStream.readShort();
                    short iJump = dataInputStream.readShort();
                    short damR = dataInputStream.readShort();
                    short statR = dataInputStream.readShort();
                    short tuc = dataInputStream.readShort();
                    int charmEXP = dataInputStream.readInt();
                    int setItemID = dataInputStream.readInt();
                    int price = dataInputStream.readInt();
                    int attackSpeed = dataInputStream.readInt();
                    boolean cash = dataInputStream.readBoolean();
                    boolean expireOnLogout = dataInputStream.readBoolean();
                    boolean exItem = dataInputStream.readBoolean();
                    boolean notSale = dataInputStream.readBoolean();
                    boolean only = dataInputStream.readBoolean();
                    boolean tradeBlock = dataInputStream.readBoolean();
                    boolean equipTradeBlock = dataInputStream.readBoolean();
                    boolean fixedPotential = dataInputStream.readBoolean();
                    short optionLength = dataInputStream.readShort();
                    List<Integer> options = new ArrayList<>(optionLength);
                    for (int i = 0; i < optionLength; i++) {
                        options.add(dataInputStream.readInt());
                    }
                    for (int i = 0; i < 7 - optionLength; i++) {
                        options.add(0);
                    }
                    int fixedGrade = dataInputStream.readInt();
                    int specialGrade = dataInputStream.readInt();
                    Equip equip = new Equip(itemId, -1, -1, new FileTime(-1), -1,
                            null, new FileTime(-1), 0, (short) 0, (short) 0, iStr, iDex, iInt,
                            iLuk, iMaxHp, iMaxMp, iPad, iMad, iPDD, iMDD, iAcc, iEva, iCraft,
                            iSpeed, iJump, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0,
                            (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, damR, statR, (short) 0, (short) 0,
                            (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, rStr, rDex, rInt,
                            rLuk, rLevel, rJob, rPop, cash,
                            islot, vslot, fixedGrade, options, specialGrade, fixedPotential, tradeBlock, only,
                            notSale, attackSpeed, price, tuc, charmEXP, expireOnLogout, setItemID, exItem, equipTradeBlock, "");
                    equips.add(equip);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadEquipsFromWz() {
        String wzDir = ServerConstants.WZ_DIR + "\\Character.wz";
        String[] subMaps = new String[]{"Accessory", "Android", "Cap", "Cape", "Coat", "Dragon", "Face", "Glove",
            "Longcoat", "Mechanic", "Pants", "PetEquip", "Ring", "Shield", "Shoes", "Totem", "Weapon"};
        for (String subMap : subMaps) {
            File subDir = new File(wzDir + "\\" + subMap);
            File[] files = subDir.listFiles();
            for (File file : files) {
                Document doc = XMLApi.getRoot(file);
                Node node = doc;
                List<Node> nodes = XMLApi.getAllChildren(node);
                for (Node mainNode : nodes) {
                    Map<String, String> attributes = XMLApi.getAttributes(mainNode);
                    String name = attributes.get("name");
                    int itemId = -1;
                    if (name != null) {
                        itemId = Integer.parseInt(attributes.get("name").replace(".img", ""));
                        String islot = "";
                        String vslot = "";
                        int reqJob = 0;
                        int reqLevel = 0;
                        int reqStr = 0;
                        int reqDex = 0;
                        int reqInt = 0;
                        int reqLuk = 0;
                        int incStr = 0;
                        int incDex = 0;
                        int incInt = 0;
                        int incLuk = 0;
                        int incPDD = 0;
                        int incMDD = 0;
                        int incPAD = 0;
                        int incMAD = 0;
                        int charmEXP = 0;
                        int incMHP = 0;
                        int incMMP = 0;
                        int incACC = 0;
                        int incEVA = 0;
                        int incCraft = 0;
                        int incSpeed = 0;
                        int incJump = 0;
                        int tuc = 0;
                        int price = 0;
                        int attackSpeed = 0;
                        int damR = 0;
                        int statR = 0;
                        int reqPop = 0;
                        int setItemID = 0;
                        boolean cash = false;
                        boolean expireOnLogout = false;
                        boolean notSale = false;
                        boolean only = false;
                        boolean tradeBlock = false;
                        boolean fixedPotential = false;
                        boolean exItem = false;
                        boolean equipTradeBlock = false;
                        List<Integer> options = new ArrayList<>(7);
                        int fixedGrade = 0;
                        int specialGrade = 0;
                        for(Node n : XMLApi.getAllChildren(XMLApi.getFirstChildByNameBF(mainNode, "info"))) {
                            attributes = XMLApi.getAttributes(n);
                            boolean hasISlot = attributes.get("name").equals("islot");
                            if(hasISlot) {
                                islot = attributes.get("value");
                            }
                            boolean hasVSlot = attributes.get("name").equals("vslot");
                            if(hasVSlot) {
                                vslot = attributes.get("value");
                            }
                            boolean hasReqJob = attributes.get("name").equals("reqJob");
                            if(hasReqJob) {
                                reqJob = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasReqLevel = attributes.get("name").equals("reqLevel");
                            if(hasReqLevel) {
                                reqLevel = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasReqStr = attributes.get("name").equals("reqSTR");
                            if(hasReqStr) {
                                reqStr = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasReqDex = attributes.get("name").equals("reqDex");
                            if(hasReqDex) {
                                reqDex = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasReqInt = attributes.get("name").equals("reqInt");
                            if(hasReqInt) {
                                reqInt = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasReqLuk = attributes.get("name").equals("reqLuk");
                            if(hasReqLuk) {
                                reqLuk = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasreqPOP = attributes.get("name").equals("reqPOP");
                            if(hasreqPOP) {
                                reqPop = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasIncStr = attributes.get("name").equals("incStr");
                            if(hasIncStr) {
                                incStr = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasincDex = attributes.get("name").equals("incDex");
                            if(hasincDex) {
                                incDex = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasincInt = attributes.get("name").equals("reqincInt");
                            if(hasincInt) {
                                incInt = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasincLuk = attributes.get("name").equals("incLuk");
                            if(hasincLuk) {
                                incLuk = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasincPDD = attributes.get("name").equals("incPDD");
                            if(hasincPDD) {
                                incPDD = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasincMDD = attributes.get("name").equals("incMDD");
                            if(hasincMDD) {
                                incMDD = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasincMHP = attributes.get("name").equals("incMHP");
                            if(hasincMHP) {
                                incMHP = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasincMMP = attributes.get("name").equals("incMMP");
                            if(hasincMMP) {
                                incMMP = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasincPAD = attributes.get("name").equals("incPAD");
                            if(hasincPAD) {
                                incPAD = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasincMAD = attributes.get("name").equals("incMAD");
                            if(hasincMAD) {
                                incMAD = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasincEVA = attributes.get("name").equals("incEVA");
                            if(hasincEVA) {
                                incEVA = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasincACC = attributes.get("name").equals("incACC");
                            if(hasincACC) {
                                incACC = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasincSpeed = attributes.get("name").equals("incSpeed");
                            if(hasincSpeed) {
                                incSpeed = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasincJump = attributes.get("name").equals("incJump");
                            if(hasincJump) {
                                incJump = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasdamR = attributes.get("name").equals("damR");
                            if(hasdamR) {
                                damR = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasstatR = attributes.get("name").equals("statR");
                            if(hasstatR) {
                                statR = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hastuc = attributes.get("name").equals("tuc");
                            if(hastuc) {
                                tuc = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hassetItemID = attributes.get("name").equals("setItemID");
                            if(hassetItemID) {
                                setItemID = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasprice = attributes.get("name").equals("price");
                            if(hasprice) {
                                price = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasattackSpeed = attributes.get("name").equals("attackSpeed");
                            if(hasattackSpeed) {
                                attackSpeed = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hascash = attributes.get("name").equals("cash");
                            if(hascash) {
                                cash = Integer.parseInt(attributes.get("value")) == 1;
                            }
                            boolean hasexpireOnLogout = attributes.get("name").equals("expireOnLogout");
                            if(hasexpireOnLogout) {
                                expireOnLogout = Integer.parseInt(attributes.get("value")) == 1;
                            }
                            boolean hasexItem = attributes.get("name").equals("exItem");
                            if(hasexItem) {
                                exItem = Integer.parseInt(attributes.get("value")) == 1;
                            }
                            boolean hasnotSale = attributes.get("name").equals("notSale");
                            if(hasnotSale) {
                                notSale = Integer.parseInt(attributes.get("value")) == 1;
                            }
                            boolean hasonly = attributes.get("name").equals("only");
                            if(hasonly) {
                                only = Integer.parseInt(attributes.get("value")) == 1;
                            }
                            boolean hastradeBlock = attributes.get("name").equals("tradeBlock");
                            if(hastradeBlock) {
                                tradeBlock = Integer.parseInt(attributes.get("value")) == 1;
                            }
                            boolean hasequipTradeBlock = attributes.get("name").equals("equipTradeBlock");
                            if(hasequipTradeBlock) {
                                equipTradeBlock = Integer.parseInt(attributes.get("value")) == 1;
                            }
                            boolean hasfixedPotential = attributes.get("name").equals("fixedPotential");
                            if(hasfixedPotential) {
                                fixedPotential = Integer.parseInt(attributes.get("value")) == 1;
                            }
                            boolean hasOptions = attributes.get("name").equals("option");
                            if(hasOptions) {
                                for(Node whichOptionNode : XMLApi.getAllChildren(n)) {
                                    attributes = XMLApi.getAttributes(whichOptionNode);
                                    int index = Integer.parseInt(attributes.get("name"));
                                    Node optionNode = XMLApi.getFirstChildByNameBF(whichOptionNode, "option");
                                    Map<String, String> optionAttr = XMLApi.getAttributes(optionNode);
                                    options.set(index, Integer.parseInt(optionAttr.get("value")));
                                }
                            }
                            for (int i = 0; i < 7 - options.size(); i++) {
                                options.add(0);
                            }

                            boolean hasfixedGrade = attributes.get("name").equals("fixedGrade");
                            if(hasfixedGrade) {
                                fixedGrade = Integer.parseInt(attributes.get("value"));
                            }
                            boolean hasspecialGrade = attributes.get("name").equals("specialGrade");
                            if(hasspecialGrade) {
                                specialGrade = Integer.parseInt(attributes.get("value"));
                            }
                        }
                        Equip equip = new Equip(itemId, -1, -1, new FileTime(-1), -1,
                                null, new FileTime(-1), 0, (short) 0, (short) 0, (short) incStr, (short) incDex, (short) incInt,
                                (short) incLuk, (short) incMHP, (short) incMMP, (short) incPAD, (short) incMAD, (short) incPDD, (short) incMDD, (short) incACC, (short) incEVA, (short) incCraft,
                                (short) incSpeed, (short) incJump, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0,
                                (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) damR, (short) statR, (short) 0, (short) 0,
                                (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) reqStr, (short) reqDex, (short) reqInt,
                                (short) reqLuk, (short) reqLevel, (short) reqJob, (short) reqPop, cash,
                                islot, vslot, fixedGrade, options, specialGrade, fixedPotential, tradeBlock, only,
                                notSale, attackSpeed, price, tuc, charmEXP, expireOnLogout, setItemID, exItem, equipTradeBlock, "");
                        equips.add(equip);
                    }

                }
            }

        }
    }

    public static List<Equip> getEquips() {
        return equips;
    }

    public static void init() {
        for(Equip equip : getEquips()) {
            equipItemIds.add(equip.getItemId());
        }
    }

    public static void generateDatFiles(){
        loadEquipsFromWz();
        saveEquips(ServerConstants.DAT_DIR + "\\equips");
    }
}
