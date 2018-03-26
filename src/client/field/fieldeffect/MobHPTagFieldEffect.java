package client.field.fieldeffect;

import client.life.Mob;
import connection.OutPacket;
import enums.FieldEffectType;

/**
 * Created on 3/26/2018.
 */
public class MobHPTagFieldEffect implements FieldEffect {

    private Mob mob;

    public MobHPTagFieldEffect(Mob mob) {
        this.mob = mob;
    }

    @Override
    public FieldEffectType getType() {
        return FieldEffectType.MobHPTag;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(mob.getTemplateId());
        int maxHP = (int) Math.min(Integer.MAX_VALUE, mob.getMaxHp());
        double ratio = mob.getMaxHp() / (double) Integer.MAX_VALUE;
        outPacket.encodeInt(ratio > 1 ? (int) (mob.getHp() / ratio) : (int) mob.getHp());
        outPacket.encodeInt(maxHP);
        outPacket.encodeByte(mob.getHpTagColor());
        outPacket.encodeByte(mob.getHpTagBgcolor());
    }
}
