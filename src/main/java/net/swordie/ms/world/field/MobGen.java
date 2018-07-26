package net.swordie.ms.world.field;

import net.swordie.ms.life.Life;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.util.Position;

/**
 * @author Sjonnie
 * Created on 7/26/2018.
 */
public class MobGen extends Life {

    private Mob mob;

    public MobGen(int templateId) {
        super(templateId);
    }

    public Mob getMob() {
        return mob;
    }

    public void setMob(Mob mob) {
        this.mob = mob;
    }

    /**
     * Spawns a Mob at the position of this MobGen.
     */
    public void spawnMob(Field field) {
        Mob mob = getMob().deepCopy();
        Position pos = mob.getHomePosition();
        mob.setPosition(pos.deepCopy());
        mob.setHomePosition(pos.deepCopy());
        field.spawnLife(mob, null);
        mob.broadcastSpawnPacket(null);
    }

    public MobGen deepCopy() {
        MobGen mobGen = new MobGen(getTemplateId());
        if (getMob() != null) {
            mobGen.setMob(getMob().deepCopy());
        }
        return mobGen;
    }
}
