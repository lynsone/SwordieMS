package loaders;

import client.life.Npc;
import client.shop.BuyLimitInfo;
import client.shop.NpcShopDlg;
import client.shop.NpcShopItem;
import constants.ServerConstants;
import dsl.SWEntity;
import dsl.SWParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import util.FileTime;
import util.Util;
import util.XMLApi;

import java.io.*;
import java.util.*;

/**
 * Created on 2/19/2018.
 */
public class NpcData {

    private static Set<Npc> npcs = new HashSet<>();
    private static Map<Integer, NpcShopDlg> shops = new HashMap<>();

    private static Map<Integer, NpcShopDlg> getShops() {
        return shops;
    }

    private static void addShop(int id, NpcShopDlg nsd) {
        getShops().put(id, nsd);
    }

    private static void loadNpcsFromWz() {
        String wzDir = ServerConstants.WZ_DIR + "\\Npc.wz";
        for(File file : new File(wzDir).listFiles()) {
            Npc npc = new Npc(-1);
            Node node = XMLApi.getRoot(file);
            Node mainNode = XMLApi.getAllChildren(node).get(0);
            int id = Integer.parseInt(XMLApi.getNamedAttribute(mainNode, "name")
                    .replace(".xml", "").replace(".img", ""));
            npc.setTemplateId(id);
            Node scriptNode = XMLApi.getFirstChildByNameBF(mainNode, "script");
            if(scriptNode != null) {
                for (Node idNode : XMLApi.getAllChildren(scriptNode)) {
                    String scriptIDString = XMLApi.getNamedAttribute(idNode, "name");
                    if(!Util.isNumber(scriptIDString)) {
                        continue;
                    }
                    int scriptID = Integer.parseInt(XMLApi.getNamedAttribute(idNode, "name"));
                    Node scriptValueNode = XMLApi.getFirstChildByNameDF(idNode, "script");
                    if(scriptValueNode != null) {
                        String scriptName = XMLApi.getNamedAttribute(scriptValueNode, "value");
                        npc.getScripts().put(scriptID, scriptName);
                    }
                }
            }
            getBaseNpcs().add(npc);
        }
    }

    public static void saveNpcsToDat(String dir) {
        Util.makeDirIfAbsent(dir);
        for(Npc npc : getBaseNpcs()) {
            File file = new File(dir + "\\" + npc.getTemplateId() + ".dat");
            try {
                DataOutputStream das = new DataOutputStream(new FileOutputStream(file));
                das.writeInt(npc.getTemplateId());
                das.writeShort(npc.getScripts().size());
                npc.getScripts().forEach((key, val) -> {
                    try {
                        das.writeInt(key);
                        das.writeUTF(val);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Npc getNpc(int id) {
        return getBaseNpcs().stream().filter(npc -> npc.getTemplateId() == id).findFirst().orElse(null);
    }

    public static Npc getNpcDeepCopyById(int id) {
        Npc res = getNpc(id);
        if (res == null) {
            File file = new File(ServerConstants.DAT_DIR + "\\npc\\" + id + ".dat");
            if(file.exists()) {
                res = loadNpcFromDat(file);
                getBaseNpcs().add(res);
            }
        }
        return res.deepCopy();
    }

    private static Npc loadNpcFromDat(File file) {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            Npc npc = new Npc(-1);
            npc.setTemplateId(dis.readInt());
            short size = dis.readShort();
            for (int i = 0; i < size; i++) {
                int id = dis.readInt();
                String val = dis.readUTF();
                npc.getScripts().put(id, val);
            }
            getBaseNpcs().add(npc);
            return npc;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void loadShopsFromSw() {
        File file = new File(String.format("%s/shops.sw", ServerConstants.RESOURCES_DIR));
        SWEntity entity = new SWParser().parse(file);
        for(Map.Entry<String, SWEntity> shopNodeWhatever : entity.getEntities().entrySet()) {
            int id = Integer.parseInt(shopNodeWhatever.getKey());
            SWEntity shop = shopNodeWhatever.getValue();
            NpcShopDlg nsd = new NpcShopDlg();
            nsd.setShopID(id);
            for(Map.Entry<String, String> property : shop.getPropertyValues().entrySet()) {
                String key = property.getKey();
                String value = property.getValue();
                switch(key.toLowerCase()) {
                    case "template":
                    case "templateid":
                    case "npctemplateid":
                        nsd.setNpcTemplateID(Integer.parseInt(value));
                        break;
                    case "selectnpcitemid":
                        nsd.setSelectNpcItemID(Integer.parseInt(value));
                        break;
                    case "starcoin":
                        nsd.setStarCoin(Integer.parseInt(value));
                        break;
                    case "shopverno":
                        nsd.setShopVerNo(Integer.parseInt(value));
                        break;
                }
            }
            for(Map.Entry<String, SWEntity> itemEntry : shop.getEntities().entrySet()) {
                SWEntity itemEntity = itemEntry.getValue();
                NpcShopItem nsi = new NpcShopItem();
                for(Map.Entry<String, String> itemProps : itemEntity.getPropertyValues().entrySet()) {
                    String key = itemProps.getKey();
                    String value = itemProps.getValue();
                    switch(key) {
                        case "id":
                        case "itemid":
                            nsi.setItemID(Integer.parseInt(value));
                            break;
                        case "price":
                            nsi.setPrice(Integer.parseInt(value));
                            break;
                        case "tokenitemid":
                            nsi.setTokenItemID(Integer.parseInt(value));
                            break;
                        case "tokenprice":
                            nsi.setTokenPrice(Integer.parseInt(value));
                            break;
                        case "pointquestid":
                            nsi.setPointQuestID(Integer.parseInt(value));
                            break;
                        case "pointprice":
                            nsi.setPointPrice(Integer.parseInt(value));
                            break;
                        case "starcoin":
                            nsi.setStarCoin(Integer.parseInt(value));
                            break;
                        case "questexid":
                            nsi.setQuestExID(Integer.parseInt(value));
                            break;
                        case "questexkey":
                            nsi.setQuestExKey(value);
                            break;
                        case "questexvalue":
                            nsi.setQuestExValue(Integer.parseInt(value));
                            break;
                        case "itemperiod":
                            nsi.setItemPeriod(Integer.parseInt(value));
                            break;
                        case "levellimited":
                            nsi.setLevelLimited(Integer.parseInt(value));
                            break;
                        case "showlevmin":
                            nsi.setShowLevMin(Integer.parseInt(value));
                            break;
                        case "showlevmax":
                            nsi.setShowLevMax(Integer.parseInt(value));
                            break;
                        case "questid":
                            nsi.setQuestID(Integer.parseInt(value));
                            break;
                        case "tabindex":
                            nsi.setTabIndex(Integer.parseInt(value));
                            break;
                        case "worldblock":
                            nsi.setWorldBlock(Boolean.parseBoolean(value));
                            break;
                        case "potentialgrade":
                            nsi.setPotentialGrade(Integer.parseInt(value));
                            break;
                        case "buylimit":
                            nsi.setBuyLimit(Integer.parseInt(value));
                            break;
                        case "quantity":
                            nsi.setQuantity((short) Integer.parseInt(value));
                            break;
                        case "unitprice":
                            nsi.setUnitPrice(Integer.parseInt(value));
                            break;
                        case "maxperslot":
                            nsi.setMaxPerSlot((short) Integer.parseInt(value));
                            break;
                        case "discountperc":
                            nsi.setDiscountPerc(Integer.parseInt(value));
                            break;
                    }
                }
                nsd.addItem(nsi);
            }
            if(nsd.getNpcTemplateID() == 0) {
                nsd.setNpcTemplateID(id);
            }
            addShop(id, nsd);
        }
    }
    
    private static NpcShopDlg loadNpcShopDlgFromFile(File file) {
        NpcShopDlg nsd = null;
        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            nsd = new NpcShopDlg();
            nsd.setShopID(dis.readInt());
            nsd.setSelectNpcItemID(dis.readInt());
            nsd.setNpcTemplateID(dis.readInt());
            nsd.setStarCoin(dis.readInt());
            nsd.setShopVerNo(dis.readInt());
            short size = dis.readShort();
            for(int i = 0; i < size; i++) {
                NpcShopItem nsi = new NpcShopItem();
                nsi.setItemID(dis.readInt());
                nsi.setPrice(dis.readInt());
                nsi.setTokenItemID(dis.readInt());
                nsi.setTokenPrice(dis.readInt());
                nsi.setPointQuestID(dis.readInt());
                nsi.setPointPrice(dis.readInt());
                nsi.setStarCoin(dis.readInt());
                nsi.setQuestExID(dis.readInt());
                nsi.setQuestExKey(dis.readUTF());
                nsi.setQuestExValue(dis.readInt());
                nsi.setItemPeriod(dis.readInt());
                nsi.setLevelLimited(dis.readInt());
                nsi.setShowLevMin(dis.readInt());
                nsi.setShowLevMin(dis.readInt());
                nsi.setQuestID(dis.readInt());
                boolean hasStart = dis.readBoolean();
                if(hasStart) {
                    nsi.setSellStart(new FileTime(dis.readInt(), dis.readInt()));
                }
                boolean hasEnd = dis.readBoolean();
                if(hasEnd) {
                    nsi.setSellEnd(new FileTime(dis.readInt(), dis.readInt()));
                }
                nsi.setTabIndex(dis.readInt());
                nsi.setWorldBlock(dis.readBoolean());
                nsi.setPotentialGrade(dis.readInt());
                nsi.setBuyLimit(dis.readInt());
                boolean hasBli = dis.readBoolean();
                if(hasBli) {
                    BuyLimitInfo bli = new BuyLimitInfo();
                    bli.setType(dis.readByte());
                    int size2 = dis.readInt();
                    for(int j = 0; j < size2; j++) {
                        bli.getDates().add(new FileTime(dis.readInt(), dis.readInt()));
                    }
                    nsi.setBuyLimitInfo(bli);
                }
                nsi.setQuantity(dis.readShort());
                nsi.setUnitPrice(dis.readLong());
                nsi.setMaxPerSlot(dis.readShort());
                nsi.setDiscountPerc(dis.readInt());
                nsd.addItem(nsi);
            }
            nsd.getItems().sort(Comparator.comparingInt(NpcShopItem::getItemID));
            addShop(nsd.getShopID(), nsd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nsd;
    } 
    
    private static void saveShopsToDat(String dir) {
        Util.makeDirIfAbsent(dir);
        for(NpcShopDlg nsd : getShops().values()) {
            File file = new File(String.format("%s/%d.dat", dir, nsd.getShopID()));
            try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
                dos.writeInt(nsd.getShopID());
                dos.writeInt(nsd.getSelectNpcItemID());
                dos.writeInt(nsd.getNpcTemplateID());
                dos.writeInt(nsd.getStarCoin());
                dos.writeInt(nsd.getShopVerNo());
                dos.writeShort(nsd.getItems().size());
                for(NpcShopItem nsi : nsd.getItems()) {
                    dos.writeInt(nsi.getItemID());
                    dos.writeInt(nsi.getPrice());
                    dos.writeInt(nsi.getTokenItemID());
                    dos.writeInt(nsi.getTokenPrice());
                    dos.writeInt(nsi.getPointQuestID());
                    dos.writeInt(nsi.getPointPrice());
                    dos.writeInt(nsi.getStarCoin());
                    dos.writeInt(nsi.getQuestExID());
                    dos.writeUTF(nsi.getQuestExKey());
                    dos.writeInt(nsi.getQuestExValue());
                    dos.writeInt(nsi.getItemPeriod());
                    dos.writeInt(nsi.getLevelLimited());
                    dos.writeInt(nsi.getShowLevMin());
                    dos.writeInt(nsi.getShowLevMax());
                    dos.writeInt(nsi.getQuestID());
                    dos.writeBoolean(nsi.getSellStart() != null);
                    if(nsi.getSellStart() != null) {
                        dos.writeInt(nsi.getSellStart().getLowDateTime());
                        dos.writeInt(nsi.getSellStart().getHighDateTime());
                    }
                    dos.writeBoolean(nsi.getSellEnd() != null);
                    if(nsi.getSellEnd() != null) {
                        dos.writeInt(nsi.getSellEnd().getLowDateTime());
                        dos.writeInt(nsi.getSellEnd().getHighDateTime());
                    }
                    dos.writeInt(nsi.getTabIndex());
                    dos.writeBoolean(nsi.isWorldBlock());
                    dos.writeInt(nsi.getPotentialGrade());
                    dos.writeInt(nsi.getBuyLimit());
                    BuyLimitInfo bli = nsi.getBuyLimitInfo();
                    dos.writeBoolean(bli != null);
                    if(bli != null) {
                        dos.writeByte(bli.getType());
                        dos.writeInt(bli.getDates().size());
                        for(FileTime ft : bli.getDates()) {
                            dos.writeInt(ft.getLowDateTime());
                            dos.writeInt(ft.getHighDateTime());
                        }
                    }
                    dos.writeShort(nsi.getQuantity());
                    dos.writeLong(nsi.getUnitPrice());
                    dos.writeShort(nsi.getMaxPerSlot());
                    dos.writeInt(nsi.getDiscountPerc());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static NpcShopDlg getShopById(int id) {
        return getShops().getOrDefault(id, loadShopByID(id));
    }

    private static NpcShopDlg loadShopByID(int id) {
        File file = new File(String.format("%s/shop/%d.dat", ServerConstants.DAT_DIR, id));
        NpcShopDlg nsd = null;
        if(file.exists()) {
            loadNpcShopDlgFromFile(file);
        }
        return nsd;
    }

    public static void generateDatFiles() {
//        loadNpcsFromWz();
//        saveNpcsToDat(ServerConstants.DAT_DIR + "/npc");
        loadShopsFromSw();
        saveShopsToDat(ServerConstants.DAT_DIR + "/shop");
    }

    public static Set<Npc> getBaseNpcs() {
        return npcs;
    }

    public static void main(String[] args) {
        generateDatFiles();
    }

    public static void clear() {
        getBaseNpcs().clear();
        getShops().clear();
    }
}
