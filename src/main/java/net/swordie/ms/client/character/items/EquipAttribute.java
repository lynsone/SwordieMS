package net.swordie.ms.client.character.items;

/**
 * Created on 1/25/2018.
 */
public enum EquipAttribute {
    LOCKED(0x1),
    PREVENT_SLIPPING(0x2),
    PREVENT_COLDNESS(0x4),
    UNTRADABLE(0x8),
    UNTRADABLE_AFTER_TRANSACTION(0x10),
    NO_NON_COMBAT_STAT_GAIN(0x20),

    CRAFTED(0x80),
    PROTECTION_SCROLL(0x100),
    LUCKY_DAY(0x200),

    TRADED_ONCE_WITHIN_ACCOUNT(0x1000),
    UPGRADE_COUNT_PROTECTION(0x2000),
    SCROLL_PROTECTION(0x4000),
    RETURN_SCROLL(0x8000),

    ;
    private int val;

    EquipAttribute(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
