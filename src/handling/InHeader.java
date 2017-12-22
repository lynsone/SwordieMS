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
    WORLD_STATUS_REQUEST(157),
    WORLD_LIST_REQUEST(160),
    CHAR_SELECT_NO_PIC(166),
    CHANGE_PIC_REQUEST(170),
    USE_AUTH_SERVER(171),

    CHAT(203),
    INVENTORY_OPERATION(241),
    USE_BUTTON(587),

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
                InHeader.USE_BUTTON,
                InHeader.PONG,
                InHeader.LOAD,
                InHeader.LOCALE,
                InHeader.HEARTBEAT_REQUEST
        );
        return spam.contains(inHeaderByOp);
    }
}
