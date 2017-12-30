package loaders;

import client.field.Field;
import client.field.Foothold;
import client.field.Portal;
import constants.ServerConstants;
import enums.PortalType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import util.Loader;
import util.XMLApi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on 12/21/2017.
 */
public class FieldData {

    private static List<Field> fields = new ArrayList<>();

    @Loader(varName = "fields")
    public static void loadFields(File file, boolean exists) {
        if(!exists) {
            loadFieldInfoFromWz();
            saveFields(file);
        } else {
            try {
                DataInputStream dataOutputStream = new DataInputStream(new FileInputStream(file));
                short fieldSize = dataOutputStream.readShort();
                for(int i = 0; i < fieldSize; i++) {
                    Field field = new Field(dataOutputStream.readInt(), -1);
                    field.setTown(dataOutputStream.readBoolean());
                    field.setSwim(dataOutputStream.readBoolean());
                    field.setReturnMap(dataOutputStream.readInt());
                    field.setForcedReturn(dataOutputStream.readInt());
                    field.setMobRate(dataOutputStream.readDouble());
                    field.setFly(dataOutputStream.readBoolean());
                    field.setOnFirstUserEnter(dataOutputStream.readUTF());
                    field.setOnUserEnter(dataOutputStream.readUTF());
                    field.setReactorShuffle(dataOutputStream.readBoolean());
                    field.setExpeditionOnly(dataOutputStream.readBoolean());
                    field.setPartyOnly(dataOutputStream.readBoolean());
                    field.setNeedSkillForFly(dataOutputStream.readBoolean());
                    field.setFixedMobCapacity(dataOutputStream.readInt());
                    field.setCreateMobInterval(dataOutputStream.readInt());
                    field.setTimeOut(dataOutputStream.readInt());
                    field.setTimeLimit(dataOutputStream.readInt());
                    field.setLvLimit(dataOutputStream.readInt());
                    field.setLvForceMove(dataOutputStream.readInt());
                    field.setConsumeItemCoolTime(dataOutputStream.readInt());
                    field.setLink(dataOutputStream.readInt());
                    short fhSize = dataOutputStream.readShort();
                    for(int j = 0; j < fhSize; j++) {
                        Foothold fh = new Foothold(
                                dataOutputStream.readInt(), dataOutputStream.readInt(), dataOutputStream.readInt()
                        );
                        fh.setX1(dataOutputStream.readInt());
                        fh.setY1(dataOutputStream.readInt());
                        fh.setX2(dataOutputStream.readInt());
                        fh.setY2(dataOutputStream.readInt());
                        fh.setNext(dataOutputStream.readInt());
                        fh.setPrev(dataOutputStream.readInt());
                        fh.setForce(dataOutputStream.readInt());
                        fh.setForbidFallDown(dataOutputStream.readBoolean());
                        field.addFoothold(fh);
                    }
                    short portalSize = dataOutputStream.readShort();
                    for(int j = 0; j < portalSize; j++) {
                        Portal p = new Portal(dataOutputStream.readInt());
                        p.setType(PortalType.getTypeByInt(dataOutputStream.readInt()));
                        p.setName(dataOutputStream.readUTF());
                        p.setTargetMapId(dataOutputStream.readInt());
                        p.setTargetPortalName(dataOutputStream.readUTF());
                        p.setX(dataOutputStream.readInt());
                        p.setY(dataOutputStream.readInt());
                        p.setHorizontalImpact(dataOutputStream.readInt());
                        p.setVerticalImpact(dataOutputStream.readInt());
                        p.setScript(dataOutputStream.readUTF());
                        p.setOnlyOnce(dataOutputStream.readBoolean());
                        p.setDelay(dataOutputStream.readInt());
                        field.addPortal(p);
                    }
                    getFields().add(field);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveFields(File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            dataOutputStream.writeShort(getFields().size());
            for(Field field : getFields()) {
                dataOutputStream.writeInt(field.getId());
                dataOutputStream.writeBoolean(field.isTown());
                dataOutputStream.writeBoolean(field.isSwim());
                dataOutputStream.writeInt(field.getReturnMap());
                dataOutputStream.writeInt(field.getForcedReturn());
                dataOutputStream.writeDouble(field.getMobRate());
                dataOutputStream.writeBoolean(field.isFly());
                dataOutputStream.writeUTF(field.getOnFirstUserEnter());
                dataOutputStream.writeUTF(field.getOnUserEnter());
                dataOutputStream.writeBoolean(field.isReactorShuffle());
                dataOutputStream.writeBoolean(field.isExpeditionOnly());
                dataOutputStream.writeBoolean(field.isPartyOnly());
                dataOutputStream.writeBoolean(field.isNeedSkillForFly());
                dataOutputStream.writeInt(field.getFixedMobCapacity());
                dataOutputStream.writeInt(field.getCreateMobInterval());
                dataOutputStream.writeInt(field.getTimeOut());
                dataOutputStream.writeInt(field.getTimeLimit());
                dataOutputStream.writeInt(field.getLvLimit());
                dataOutputStream.writeInt(field.getLvForceMove());
                dataOutputStream.writeInt(field.getConsumeItemCoolTime());
                dataOutputStream.writeInt(field.getLink());
                dataOutputStream.writeShort(field.getFootholds().size());
                for(Foothold fh : field.getFootholds()) {
                    dataOutputStream.writeInt(fh.getId());
                    dataOutputStream.writeInt(fh.getLayerId());
                    dataOutputStream.writeInt(fh.getGroupId());
                    dataOutputStream.writeInt(fh.getX1());
                    dataOutputStream.writeInt(fh.getY1());
                    dataOutputStream.writeInt(fh.getX2());
                    dataOutputStream.writeInt(fh.getY2());
                    dataOutputStream.writeInt(fh.getNext());
                    dataOutputStream.writeInt(fh.getPrev());
                    dataOutputStream.writeInt(fh.getForce());
                    dataOutputStream.writeBoolean(fh.isForbidFallDown());
                }
                dataOutputStream.writeShort(field.getPortals().size());
                for(Portal p : field.getPortals()) {
                    dataOutputStream.writeInt(p.getId());
                    dataOutputStream.writeInt(p.getType().getVal());
                    dataOutputStream.writeUTF(p.getName());
                    dataOutputStream.writeInt(p.getTargetMapId());
                    dataOutputStream.writeUTF(p.getTargetPortalName());
                    dataOutputStream.writeInt(p.getX());
                    dataOutputStream.writeInt(p.getY());
                    dataOutputStream.writeInt(p.getHorizontalImpact());
                    dataOutputStream.writeInt(p.getVerticalImpact());
                    dataOutputStream.writeUTF(p.getScript());
                    dataOutputStream.writeBoolean(p.isOnlyOnce());
                    dataOutputStream.writeInt(p.getDelay());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadFieldInfoFromWz() {
        String wzDir = ServerConstants.WZ_DIR + "\\Map.wz\\Map";
        File dir = new File(wzDir);
        File[] files = dir.listFiles();
        for (File file : files) {
            if(file.listFiles() == null) {
                continue;
            }
            for (File mapFile : file.listFiles()) {
                Document doc = XMLApi.getRoot(mapFile);
                Node node = XMLApi.getAllChildren(doc).get(0);
                if (node == null) {
                    continue;
                }
                int id = Integer.parseInt(XMLApi.getAttributes(node).get("name").replace(".img", ""));
                Field field = new Field(id, -1);
                Node infoNode = XMLApi.getFirstChildByNameBF(node, "info");
                for(Node n : XMLApi.getAllChildren(infoNode)) {
                    Map<String, String> attr = XMLApi.getAttributes(n);
                    String name = attr.get("name");
                    String value = attr.get("value");
                    if(name.equalsIgnoreCase("town")) {
                        field.setTown(Integer.parseInt(value) != 0);
                    }
                    if(name.equalsIgnoreCase("swim")) {
                        field.setSwim(Integer.parseInt(value) != 0);
                    }
                    if(name.equalsIgnoreCase("returnMap")) {
                        field.setReturnMap(Integer.parseInt(value));
                    }
                    if(name.equalsIgnoreCase("forcedReturn")) {
                        field.setForcedReturn(Integer.parseInt(value));
                    }
                    if(name.equalsIgnoreCase("mobRate")) {
                        field.setMobRate(Double.parseDouble(value));
                    }
                    if(name.equalsIgnoreCase("fly")) {
                        field.setFly(Integer.parseInt(value) != 0);
                    }
                    if(name.equalsIgnoreCase("onFirstUserEnter")) {
                        field.setOnFirstUserEnter(value);
                    }
                    if(name.equalsIgnoreCase("onUserEnter")) {
                        field.setOnUserEnter(value);
                    }
                    if(name.equalsIgnoreCase("reactorShuffle")) {
                        field.setReactorShuffle(Integer.parseInt(value) != 0);
                    }
                    if(name.equalsIgnoreCase("expeditionOnly")) {
                        field.setExpeditionOnly(Integer.parseInt(value) != 0);
                    }
                    if(name.equalsIgnoreCase("partyOnly")) {
                        field.setPartyOnly(Integer.parseInt(value) != 0);
                    }
                    if(name.equalsIgnoreCase("isNeedSkillForFly")) {
                        field.setNeedSkillForFly(Integer.parseInt(value) != 0);
                    }
                    if(name.equalsIgnoreCase("fixedMobCapacity")) {
                        field.setFixedMobCapacity(Integer.parseInt(value));
                    }
                    if(name.equalsIgnoreCase("createMobInterval")) {
                        field.setCreateMobInterval(Integer.parseInt(value));
                    }
                    if(name.equalsIgnoreCase("timeOut")) {
                        field.setTimeOut(Integer.parseInt(value));
                    }
                    if(name.equalsIgnoreCase("timeLimit")) {
                        field.setTimeLimit(Integer.parseInt(value));
                    }
                    if(name.equalsIgnoreCase("lvLimit")) {
                        field.setLvLimit(Integer.parseInt(value));
                    }
                    if(name.equalsIgnoreCase("lvForceMove")) {
                        field.setLvForceMove(Integer.parseInt(value));
                    }
                    if(name.equalsIgnoreCase("consumeItemCoolTime")) {
                        field.setConsumeItemCoolTime(Integer.parseInt(value));
                    }
                    if(name.equalsIgnoreCase("link")) {
                        field.setLink(Integer.parseInt(value));
                    }
                }
                Node fhNode = XMLApi.getFirstChildByNameBF(node, "foothold");
                if(fhNode != null) {
                    for (Node layerIDNode : XMLApi.getAllChildren(fhNode)) {
                        int layerID = Integer.parseInt(XMLApi.getNamedAttribute(layerIDNode, "name"));
                        for (Node groupIDNode : XMLApi.getAllChildren(layerIDNode)) {
                            int groupID = Integer.parseInt(XMLApi.getNamedAttribute(groupIDNode, "name"));
                            for (Node idNode : XMLApi.getAllChildren(groupIDNode)) {
                                int fhId = Integer.parseInt(XMLApi.getNamedAttribute(idNode, "name"));
                                Foothold fh = new Foothold(fhId, layerID, groupID);
                                for (Node n : XMLApi.getAllChildren(idNode)) {
                                    String name = XMLApi.getNamedAttribute(n, "name");
                                    String value = XMLApi.getNamedAttribute(n, "value");
                                    if (name.equalsIgnoreCase("x1")) {
                                        fh.setX1(Integer.parseInt(value));
                                    }
                                    if (name.equalsIgnoreCase("y1")) {
                                        fh.setY1(Integer.parseInt(value));
                                    }
                                    if (name.equalsIgnoreCase("x2")) {
                                        fh.setX2(Integer.parseInt(value));
                                    }
                                    if (name.equalsIgnoreCase("y2")) {
                                        fh.setY2(Integer.parseInt(value));
                                    }
                                    if (name.equalsIgnoreCase("next")) {
                                        fh.setNext(Integer.parseInt(value));
                                    }
                                    if (name.equalsIgnoreCase("prev")) {
                                        fh.setPrev(Integer.parseInt(value));
                                    }
                                    if (name.equalsIgnoreCase("force")) {
                                        fh.setForce(Integer.parseInt(value));
                                    }
                                    if (name.equalsIgnoreCase("forbidFallDown")) {
                                        fh.setForbidFallDown(Integer.parseInt(value) != 0);
                                    }
                                }
                                field.addFoothold(fh);
                            }
                        }
                    }
                }
                Node portalNode = XMLApi.getFirstChildByNameBF(node, "portal");
                if(portalNode != null) {
                    for(Node idNode : XMLApi.getAllChildren(portalNode)) {
                        int portalId = Integer.parseInt(XMLApi.getNamedAttribute(idNode, "name"));
                        Portal portal = new Portal(portalId);
                        for (Node n : XMLApi.getAllChildren(idNode)) {
                            String name = XMLApi.getNamedAttribute(n, "name");
                            String value = XMLApi.getNamedAttribute(n, "value");
                            if (name.equalsIgnoreCase("pt")) {
                                portal.setType(PortalType.getTypeByInt(Integer.parseInt(value)));
                            }
                            if (name.equalsIgnoreCase("pn")) {
                                portal.setName(value);
                            }
                            if (name.equalsIgnoreCase("tm")) {
                                portal.setTargetMapId(Integer.parseInt(value));
                            }
                            if (name.equalsIgnoreCase("tn")) {
                                portal.setTargetPortalName(value);
                            }
                            if (name.equalsIgnoreCase("x")) {
                                portal.setX(Integer.parseInt(value));
                            }
                            if (name.equalsIgnoreCase("y")) {
                                portal.setY(Integer.parseInt(value));
                            }
                            if (name.equalsIgnoreCase("horizontalImpact")) {
                                portal.setHorizontalImpact(Integer.parseInt(value));
                            }
                            if (name.equalsIgnoreCase("verticalImpact")) {
                                portal.setVerticalImpact(Integer.parseInt(value));
                            }
                            if (name.equalsIgnoreCase("script")) {
                                portal.setScript(value);
                            }
                            if (name.equalsIgnoreCase("onlyOnce")) {
                                portal.setOnlyOnce(Integer.parseInt(value) != 0);
                            }
                            if (name.equalsIgnoreCase("delay")) {
                                portal.setDelay(Integer.parseInt(value));
                            }
                        }
                        field.addPortal(portal);
                        if(portal.getType() == null) {
                            System.out.println();
                        }
                    }
                }

                getFields().add(field);
            }
        }
    }

    public static List<Field> getFields() {
        return fields;
    }

    public static Field getFieldById(int id) {
        return getFields().stream().filter(f -> f.getId() == id).findAny().orElse(null);
    }
}
