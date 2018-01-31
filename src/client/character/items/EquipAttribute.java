package client.character.items;

/**
 * Created on 1/25/2018.
 */
public enum EquipAttribute {
    LOCKED(0x1),
    TRADABLE(0x8), // or Untradable?
    UNTRADABLE_AFTER_TRANSACTION(0x10),
    PROTECTION_SCROLL(0x100),
    LUCKY_DAY(0x200),
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
