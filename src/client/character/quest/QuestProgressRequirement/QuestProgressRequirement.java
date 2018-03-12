package client.character.quest.QuestProgressRequirement;

import loaders.DatSerializable;

/**
 * Created on 3/2/2018.
 */
public interface QuestProgressRequirement extends DatSerializable {

    /**
     * Returns whether this progress requirement has been completed by the player.
     * @return Completeness.
     */
    boolean isComplete();
}
