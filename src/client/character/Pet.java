package client.character;

import client.life.Life;
import connection.OutPacket;

/**
 * Created on 12/20/2017.
 */
public class Pet extends Life {
    private int id;
    private int idx;
    private String name;
    private long petLockerSN;
    private int hue;
    private short giantRate;
    private boolean transformed;
    private boolean reinforced;

    public Pet(int objectId) {
        super(objectId);
    }

    public int getActiveSkillCoolTime() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getId());
        outPacket.encodeString(getName());
        outPacket.encodeLong(getPetLockerSN());
        outPacket.encodePosition(getPosition());
        outPacket.encodeByte(getMoveAction());
        outPacket.encodeShort(getFh());
        outPacket.encodeInt(getHue());
        outPacket.encodeShort(getGiantRate());
        outPacket.encodeByte(isTransformed());
        outPacket.encodeByte(isReinforced());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPetLockerSN() {
        return petLockerSN;
    }

    public void setPetLockerSN(long petLockerSN) {
        this.petLockerSN = petLockerSN;
    }

    public int getHue() {
        return hue;
    }

    public void setHue(int hue) {
        this.hue = hue;
    }

    public short getGiantRate() {
        return giantRate;
    }

    public void setGiantRate(short giantRate) {
        this.giantRate = giantRate;
    }

    public boolean isTransformed() {
        return transformed;
    }

    public void setTransformed(boolean transformed) {
        this.transformed = transformed;
    }

    public boolean isReinforced() {
        return reinforced;
    }

    public void setReinforced(boolean reinforced) {
        this.reinforced = reinforced;
    }
}
