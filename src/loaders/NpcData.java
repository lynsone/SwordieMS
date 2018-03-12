package loaders;

import client.life.Npc;
import constants.ServerConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import util.Util;
import util.XMLApi;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created on 2/19/2018.
 */
public class NpcData {

    private static Set<Npc> npcs = new HashSet<>();

    private static void loadNpcsFromWz() {
        String wzDir = ServerConstants.WZ_DIR + "\\Npc.wz";
        for(File file : new File(wzDir).listFiles()) {
            Npc npc = new Npc(-1);
            Node node = XMLApi.getRoot(file);
            Node mainNode = XMLApi.getAllChildren(node).get(0);
            int id = Integer.parseInt(XMLApi.getNamedAttribute(mainNode, "name")
                    .replace(".xml", "").replace(".img", ""));
            System.out.println(id);
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

    public static void generateDatFiles() {
        loadNpcsFromWz();
        saveNpcsToDat(ServerConstants.DAT_DIR + "\\npc");
    }

    public static Set<Npc> getBaseNpcs() {
        return npcs;
    }

    public static void main(String[] args) {
        generateDatFiles();
    }
}
