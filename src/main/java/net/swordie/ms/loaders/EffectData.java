package net.swordie.ms.loaders;

import net.swordie.ms.client.character.Scene.EffectInfo;
import net.swordie.ms.client.character.Scene.Scene;
import net.swordie.ms.enums.SceneType;
import net.swordie.ms.util.XMLApi;
import org.w3c.dom.Node;

import java.util.Map;

/**
 * Created by Asura on 28-8-2018.
 */
public class EffectData {

    public static void getEffectsFromWzPath(Scene scene) {
        String xmlPath = scene.getXmlPath();
        String sceneName = scene.getSceneName();
        String sceneNumber = scene.getSceneNumber();

        Node sceneNameNode = XMLApi.getNodeByPath(xmlPath, sceneName); // Scene Name  Node

        Node sceneNumberNode = XMLApi.getFirstChildByNameBF(sceneNameNode, sceneNumber); // Scene
        for (Node individualEffect : XMLApi.getAllChildren(sceneNumberNode)) {
            EffectInfo ei = new EffectInfo();
            for(Node eiInfo : XMLApi.getAllChildren(individualEffect)) {
                Map<String, String> attributes = XMLApi.getAttributes(eiInfo);
                String name = attributes.get("name");
                String value = attributes.get("value");
                switch (name) {
                    case "type":
                        ei.setType(SceneType.getByVal(Integer.parseInt(value)));
                        break;
                    case "visual":
                        ei.setVisual(value);
                        break;
                    case "start":
                        ei.setStart(Integer.parseInt(value));
                        break;
                    case "field":
                        if (value == null) {
                            ei.setField(-1);
                        } else {
                            ei.setField(Integer.parseInt(value));
                        }
                        break;
                    case "x":
                        ei.setX(Integer.parseInt(value));
                        break;
                    case "y":
                        ei.setY(Integer.parseInt(value));
                        break;
                    case "z":
                        ei.setZ(Integer.parseInt(value));
                        break;
                }
            }
            scene.addEffectInfo(ei);
        }
    }
}
