package enums;

/**
 * Created on 1/2/2018.
 */
public enum ChatMsgColour {
    GENERAL_CHAT_WHITE(0),
    WHISPER_GREEN(1),
    PARTY_PURPLE(2),
    BUDDY_ORANGE(3),
    GUILD_PURPLE(4),
    ALLIANCE_GREEN(5),
    GREY(6),
    NOTICE_YELLOW(7),
    LESS_YELLOW(8),
    GAME_NOTICE(9),
    GM_CHAT(10),
    GAME_MESSAGE(11),
    GM_BLUE_CHAT(12),
    SMEGA(13),
    SMEGA_2(14),
    ITEM_SMEGA(15),
    DARK_BLUE(16),
    ITEM_NOTICE(17),
    MEGA(18),
    YELLOW(19),
    CYAN(20),
    ITEM_NO_ITEM_SMEGA(21),
    SWEDEN(22),
    DARK_RED(30),
    GACHA_REWARD(31),
    GACHA_RED(32),
    GACHA_RED_2(33), // same as GACHA_RED(32)
    DARK_BLUE_2(34), // same as DARK_BLUE(16)
    ITEM_NO_ITEM_SMEGA_DARK_TEXT(35),
    WHITE_ON_GREEN(36),
    CAKE_MEGAPHONE(37),
    PIE_MEGAPHONE(38),
    BLACK_ON_WHITE(39),
    ;
    private short val;

    ChatMsgColour(short val) {
        this.val = val;
    }

    ChatMsgColour(int i) {
        this((short) i);
    }

    public short getVal() {
        return val;
    }
}
