package client.field.fieldeffect;

import client.life.Mob;
import connection.OutPacket;
import enums.FieldEffectType;

/**
 * Created on 3/26/2018.
 */
public class MobHPTagFieldEffect implements FieldEffect {

    private Mob mob;
    private int color;
    private int bgColor;

    public MobHPTagFieldEffect(Mob mob, int color, int bgColor) {
        this.mob = mob;
        this.color = color;
        this.bgColor = bgColor;
    }

    @Override
    public FieldEffectType getType() {
        return FieldEffectType.MobHPTag;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(mob.getTemplateId());
        outPacket.encodeInt((int) mob.getHp());
        outPacket.encodeInt((int) Math.min(Integer.MAX_VALUE, mob.getMaxHp()));
        outPacket.encodeByte(color);
        outPacket.encodeByte(bgColor);
    }
}
