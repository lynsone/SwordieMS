package client.life;

/**
 * Created on 4/21/2018.
 */
public class Reactor extends Life {

    private byte state;
    private boolean flip;
    private String name;
    private int ownerID;
    private int properEventIdx;

    public Reactor(int objectId) {
        super(objectId);
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public boolean isFlip() {
        return flip;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public int getProperEventIdx() {
        return properEventIdx;
    }

    public void setProperEventIdx(int properEventIdx) {
        this.properEventIdx = properEventIdx;
    }
}
