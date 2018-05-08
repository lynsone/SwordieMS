package net.swordie.ms.world.field.fieldeffect;

import net.swordie.ms.connection.OutPacket;

/**
 * Created on 3/26/2018.
 */
public interface FieldEffect {

    FieldEffectType getType();

    void encode(OutPacket outPacket);
}
