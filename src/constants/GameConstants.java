package constants;

/**
 * Created on 1/23/2018.
 */
public class GameConstants {
    public static final long MAX_MONEY = 10_000_000_000L;
    public static final int DROP_HEIGHT = 100; // was 20
    public static final int DROP_DIFF = 25;
    public static final int DROP_REMAIN_ON_GROUND_TIME = 120; // 2 minutes
    public static final int DROP_REMOVE_OWNERSHIP_TIME = 30; // 30 sec
    public static final short DAMAGE_SKIN_MAX_SIZE = 100;
    public static long[] charExp = new long[251];

    static {
        initCharExp();
    }

    private static void initCharExp() {
        // NEXTLEVEL::NEXTLEVEL
        charExp[1] = 15;
        charExp[2] = 32;
        charExp[3] = 57;
        charExp[4] = 92;
        charExp[5] = 135;
        charExp[6] = 372;
        charExp[7] = 560;
        charExp[8] = 840;
        charExp[9] = 1242;
        for (int i = 10; i <= 14; i++) {
            charExp[i] = charExp[i-1];
        }
        for(int i = 15; i <= 29; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.2);
        }
        for(int i = 30; i <= 34; i++) {
            charExp[i] = charExp[i-1];
        }
        for(int i = 35; i <= 39; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.2);
        }
        for(int i = 40; i <= 59; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.08);
        }
        for(int i = 60; i <= 64; i++) {
            charExp[i] = charExp[i-1];
        }
        for(int i = 65; i <= 74; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.08);
        }
        for(int i = 75; i <= 99; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.07);
        }
        for(int i = 100; i <= 104; i++) {
            charExp[i] = charExp[i-1];
        }
        for(int i = 105; i <= 159; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.07);
        }
        for(int i = 160; i <= 199; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.06);
        }
        charExp[200] = charExp[199] * 2;
        for(int i = 201; i <= 209; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.2);
        }
        charExp[210] = (long) (charExp[209] * 1.06 * 2);
        for(int i = 211; i <= 219; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.06);
        }
        charExp[220] = (long) (charExp[219] * 1.04 * 2);
        for(int i = 221; i <= 229; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.04);
        }
        charExp[230] = (long) (charExp[229] * 1.02 * 2);
        for(int i = 231; i <= 239; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.02);
        }
        charExp[240] = (long) (charExp[239] * 1.01 * 2);
        for(int i = 241; i <= 249; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.01);
        }
    }
}
