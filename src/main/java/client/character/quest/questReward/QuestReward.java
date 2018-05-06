package client.character.quest.questReward;

import client.character.Char;
import loaders.DatSerializable;

/**
 * Created on 3/2/2018.
 */
public interface QuestReward extends DatSerializable {

    /**
     * Gives the reward of this QuestReward to a {@link Char}
     * @param chr The Char to give the reward to.
     */
    void giveReward(Char chr);
}
