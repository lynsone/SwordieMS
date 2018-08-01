package net.swordie.ms.enums;

public enum LoginType {

    SUCCESS(0),
    BLOCKED_FOR_HACKING(2),
    DELETED_OR_BLOCKED(3),
    INVALID_PASSWORD(4),
    HAVING_TROUBLE_LOGGIN_IN(6),
    REGION_BLOCK(14),
    HAVING_TROUBLE_LOGGIN_IN_3(23),
    CANNOT_CLICK_BUTTON(25),
    CANNOT_CLICK_BUTTON_2(26),
    CANNOT_CLICK_BUTTON_3(29),
    CANNOT_CLICK_BUTTON_4(27), // 38
    CANNOT_CLICK_BUTTON_5(36),
    CANNOT_CLICK_BUTTON_6(35),
    NOT_A_REGISTERED_ID(5),
    MUST_BE_20(11),
    HAVING_TROUBLE(12),
    NOT_REGISTERED_WITH_LINK(15),
    NEEDS_VERIFICATION(16),
    IDK_4(18), // 38
    NOTHING(20),
    NOTHING_2(38),
    NOTHING_3(51),
    USE_MAPLE_OR_EMAIL_ID(41),
    NOTHING_4(42),
    AVAILABLE_UNTIL(83),
    MUST_CREATE_PIC(53),
    NOT(54),
    NOTT(46),
    NOTTT(84),
    OVERSEAS_BLOCKED(64),
    IDK_16(65),
    IDK_17(74),
    IDK_18(93),
    IDK_19(69),
    TROUBLE_LOGGING_IN(8),
    ACCOUNT_LIST(43),
    CANNOT_DELETE_CHAR_ON_MAPLE_TOGETHER(45),

    ;

    /**
     * Created on 4/30/2017.
     */
    private byte value;

    LoginType(int value) {
        this.value = (byte) value;
    }

    public byte getValue() {
        return value;
    }

    public static void main(String[] args) {
        for(LoginType lt : values()) {
            System.out.println(lt + "(" + lt.getValue() + "), ");
        }
    }
}
