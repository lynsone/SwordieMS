package enums;

public enum PortalType {
    // http://mapleref.wikia.com/wiki/Portal
    StartPoint(0),
    PortalInvisible(1),
    PortalVisible(2),
    PortalCollision(3),
    PortalChangable(4),
    PortalChangableInvisible(5),
    TownPortalPoint(6),
    PortalScript(7),
    PortalScriptInvisible(8),
    PortalCollisionScript(9),
    PortalHidden(10),
    PortalScriptHidden(11),
    PortalCollisionJump(12),
    PortalCollisionCustom(13),
    PortalCollisionInvisibleChangable(14) // ?
    ;
    private byte val;

    PortalType(byte val) {
        this.val = val;
    }

    PortalType(int val) {
        this((byte) val);
    }

    public byte getVal() {
        return val;
    }
}
