package net.swordie.ms.client.character.items;

/**
 * Created on 20/9/2018.
 */
public enum RequiredJob {
    AnyJob(0),
    Warrior(1),
    Magician(2),
    Bowman(4),
    Thief(8),
    Pirate(16);

    private int val;

    RequiredJob(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
