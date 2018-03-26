package client.field.fieldeffect;

import connection.OutPacket;
import enums.FieldEffectType;

/**
 * Created on 3/26/2018.
 */
public interface FieldEffect {

    FieldEffectType getType();

    void encode(OutPacket outPacket);
}
