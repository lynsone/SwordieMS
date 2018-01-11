package handling;

import java.util.Arrays;
import java.util.List;

public enum InHeader {

    //
    PONG(147),

    // Login ops
    REDISPLAY_SERVERLIST(7),
    GUEST_LOGIN(22),
    TOS(29),
    VIEW_SERVERLIST(33),
    VIEW_REGISTER_PIC(50),
    VIEW_SELECT_PIC(53),
    CLIENT_FAILED(57),
    PART_TIME_JOB(59),
    CHARACTER_CARD(60),
    ENABLE_LV50_CHAR(61),
    CREATE_LV50_CHAR(62),
    ENABLE_SPECIAL_CREATION(62),
    CREATE_SPECIAL_CHAR(65),
    LOCALE(102), // I guess
    CONNECT(103),
    LOGIN_PASSWORD(105),
    CHARLIST_REQUEST(106),
    AUTH_SECOND_PASSWORD(107),
    CHAR_SELECT(108),
    CHAR_LOGIN(110),
    LAST_SELECTED_WORLD(114),
    CHECK_DUPLICATE_ID(116),
    WORLD_LIST_RE_REQUEST(117),
    CREATE_CHAR(125),
    DELETE_CHAR(128),
    HEARTBEAT_REQUEST(134),
    CLIENT_ERROR(149),
    CLIENT_START(152),
    SERVERSTATUS_REQUEST(-1), // disconnects upon sending message (no error given)
    LOAD(154),
    CRASH(155),
    WORLD_STATUS_REQUEST(157),
    WORLD_LIST_REQUEST(160),
    WORLD_LIST_REQ(162),
    CHAR_SELECT_NO_PIC(166),
    CHANGE_PIC_REQUEST(170),
    USE_AUTH_SERVER(171),

    // Field
    CHANGE_FIELD_REQUEST(175),
    CHANGE_CHANNEL_REQUEST(176),
    MOVE(190),
    MELEE_ATTACK(195),
    SHOOT_ATTACK(196),
    MAGIC_ATTACK(197),
    BODY_ATTACK(198),

    HIT(201),
    ATTACK_USER(202),
    CHAT(203),
    AD_BOARD_CLOSE(204),
    EMOTION(205),
    INVENTORY_OPERATION(241),
    ABILITY_POINT_DISTRIBUTE(298),
    AUTO_ABILITY_POINT_DISTRIBUTE(299),
    STAT_RECOVERY(301),
    SKILL_RECORD_UPDATE_REQUEST(304),
    SKILL(305),
    TEMPORARY_STAT_RESET_REQUEST(306),
    KEYMAP_UPDATE_REQUEST(414),

    SUMMONED_MOVE(564),
    SUMMONED_ATTACK(565),
    USE_BUTTON(587),

    RW_CLEAR_CURRENT_ATTACK_REQUEST(731),
    RW_MULTI_CHARGE_CANCEL_REQUEST(732),

    MOVE_LIFE(825),

    ;
    /**
     * Created on 2/18/2017.
     */
    private short value;

    InHeader(int op) {
        this.value = (short) op;
    }

    public short getValue() {
        return value;
    }

    public static InHeader getInHeaderByOp(int op) {
        for (InHeader inHeader : InHeader.values()) {
            if (inHeader.getValue() == op) {
                return inHeader;
            }
        }
        return null;
    }

    public static boolean isSpamHeader(InHeader inHeaderByOp) {
        List<InHeader> spam = Arrays.asList(
                USE_BUTTON,
                PONG,
                LOAD,
                LOCALE,
                HEARTBEAT_REQUEST,
                MOVE_LIFE,
                MOVE,
                STAT_RECOVERY
        );
        return spam.contains(inHeaderByOp);
    }
}
