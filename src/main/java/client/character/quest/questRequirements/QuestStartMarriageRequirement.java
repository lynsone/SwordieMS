package client.character.quest.questRequirements;

import client.character.Char;
import loaders.DatSerializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created on 3/2/2018.
 */
public class QuestStartMarriageRequirement implements QuestStartRequirement {
    @Override
    public boolean hasRequirements(Char chr) {
        return chr.isMarried();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {

    }

    @Override
    public DatSerializable load(DataInputStream dis) throws IOException {
        return new QuestStartMarriageRequirement();
    }
}
