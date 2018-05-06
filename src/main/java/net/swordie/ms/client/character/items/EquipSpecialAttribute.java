package net.swordie.ms.client.character.items;

/**
 * Created on 1/25/2018.
 */
public enum EquipSpecialAttribute {
    NO_DESTROY_EXCEPT_ENHANCE(0x1),
    ALWAYS_TIER_UP(0x2),
    ALWAYS_SCROLL_SUCCEED(0x4),
    TRACE(0x80)

    ;
    private int val;

    EquipSpecialAttribute(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
