package net.swordie.ms.client.field.fieldeffect;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.FieldEffectType;

/**
 * Created on 3/26/2018.
 */
public interface FieldEffect {

    FieldEffectType getType();

    void encode(OutPacket outPacket);
}
