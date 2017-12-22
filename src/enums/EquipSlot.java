package enums;

/**
 * Created on 11/23/2017.
 */
public enum EquipSlot {
    ACCESSORY(-1),
    ANDROID(-1),
    CAP(-1),
    CAPE(-1),
    COAT(-1),
    DRAGON(-1),
    FACE(-1),
    GLOVE(-1),
    LONG_COAT(-1),
    MECHANIC(-1),
    PANTS(-1),
    PET(-1),
    RING(-1),
    SHIELD(-1),
    SHOES(-1),
    TOTEM(-1),
    WEAPON(-1)


    ;

    private byte val;

    EquipSlot(byte val) {
        this.val = val;
    }

    EquipSlot(int val) {
        this((byte) val);
    }
}
