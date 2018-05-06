package client.character.items;

/**
 * Created on 1/25/2018.
 */
public enum ItemState {
    NOT_ENHANCABLE(0),
    ENHANCABLE(0x100),
    ;

    private int val;

    ItemState(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
