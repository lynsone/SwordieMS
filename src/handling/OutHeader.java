package handling;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 2/18/2017.
 */
public enum OutHeader {

    // CLogin::OnPacket
    CHECK_PASSWORD_RESULT(0),
    WORLD_INFORMATION(1),
    LATEST_CONNECTED_WORLD(2),
    RECOMMENDED_WORLD_MESSAGE(3),
    SET_CLIENT_KEY(4),
    SET_PHYSICAL_WORLD_ID(5),
    SELECT_WORLD_RESULT(6),
    SELECT_CHARACTER_RESULT(7),
    ACCOUNT_INFO_RESULT(8),
    CREATE_MAPLE_ACCOUNT_RESULT(9),
    CHECK_DUPLICATED_ID_RESULT(10),
    CREATE_NEW_CHARACTER_RESULT(11),
    DELETE_CHARACTER_RESULT(12),
    RESERVED_DELETE_CHARACTER_RESULT(13),
    RESERVED_DELETE_CHARACTER_CANCEL_RESULT(14),
    RENAME_CHARACTER_RESULT(15),
    PING(18),
    HEARTBEAT_RESPONSE(23),
    CHANGE_SPW_RESULT(24),
    ALBA_RESULT(29),
    SET_AUTO_SELECTED_WORLD(32),
    WORLD_INFO_FOR_SHINING_RESULT(33),
    CLIENT_START(36),
    SERVER_STATUS(38),
    AUTH_SERVER(47),

    // CWvsContext::UI_Toggle
    INVENTORY_OPERATION(71),
    INVENTORY_GROW(72),
    STAT_CHANGED(73),
    GIVE_BUFF(74),
    CANCEL_BUFF(75),
    TEMP_STATS(76),
    TEMP_STATS_RESET(77),
    UPDATE_SKILLS(78),
    UPDATE_STOLEN_SKILLS(79),
    TARGET_SKILL(86),
    FAME_RESPONSE(88),
    SHOW_STATUS_INFO(89),
    SHOW_NOTES(90),
    TROCK_LOCATIONS(91),
    LIE_DETECTOR(92),
    REPORT_RESPONSE(95),
    REPORT_TIME(96),
    REPORT_STATUS(97),
    UPDATE_MOUNT(99),
    SHOW_QUEST_COMPLETION(100),
    SEND_TITLE_BOX(101),
    USE_SKILL_BOOK(102),
    SP_RESET(103),
    AP_RESET(104),
    EXPAND_CHARACTER_SLOTS(107),
    FINISH_GATHER(109),
    FINISH_SORT(110),
    EXP_POTION(67),// # needs updating.
    CHAR_INFO(113),
    PARTY_OPERATION(114),
    MEMBER_SEARCH(115),
    PARTY_SEARCH(90),// # needs updating.
    BOOK_INFO(91),// # needs updating.
    CODEX_INFO_RESPONSE(92),// # needs updating.
    EXPEDITION_OPERATION(122),
    BUDDYLIST(123),
    GUILD_OPERATION(127),
    ALLIANCE_OPERATION(128),
    SPAWN_PORTAL(129),
    SERVERMESSAGE(130),
    PIGMI_REWARD(132),
    OWL_OF_MINERVA(134),
    OWL_RESULT(135),
    ENGAGE_REQUEST(138),
    ENGAGE_RESULT(139),
    WEDDING_GIFT(140),
    WEDDING_MAP_TRANSFER(141),
    USE_CASH_PET_FOOD(142),
    YELLOW_CHAT(147),
    SHOP_DISCOUNT(148),
    CATCH_MOB(149),
    PLAYER_NPC(151),
    DISABLE_NPC(153),
    GET_CARD(154),
    CARD_SET(155),
    ON_HOUR_CHANGED(156),
    BOOK_STATS(129), //# needs updating.
    FAMILIAR_INFO(132), //# needs updating.
    WEB_BOARD_UPDATE(160),
    SESSION_VALUE(161),
    PARTY_VALUE(162),
    MAP_VALUE(163),
    EXP_BONUS(165),
    SEND_PEDIGREE(166),
    OPEN_FAMILY(167),
    FAMILY_MESSAGE(168),
    FAMILY_INVITE(169),
    FAMILY_JUNIOR(170),
    SENIOR_MESSAGE(171),
    FAMILY(172),
    REP_INCREASE(173),
    FAMILY_LOGGEDIN(174),
    FAMILY_BUFF(175),
    FAMILY_USE_REQUEST(176),
    LEVEL_UPDATE(177),
    MARRIAGE_UPDATE(178),
    JOB_UPDATE(179),
    SLOT_UPDATE(180),
    FOLLOW_REQUEST(181),
    TOP_MSG(183),
    MID_MSG(185),
    CLEAR_MID_MSG(186),
    SPECIAL_MSG(187),
    MAPLE_ADMIN_MSG(181),// # needs updating.
    UPDATE_JAGUAR(192),
    YOUR_INFORMATION(185),// # needs updating.
    FIND_FRIEND(186),// # needs updating.
    VISITOR(187),// # needs updating.
    ULTIMATE_EXPLORER(196),
    SPECIAL_STAT(198),
    UPDATE_IMP_TIME(199),
    ITEM_POT(200),
    MULUNG_DOJO_RANKING(209),
    REPLACE_SKILLS(213),
    INNER_ABILITY_MSG(216),// # needs updating.
    ENABLE_INNER_ABILITY(217),
    DISABLE_INNER_ABILITY(218),
    UPDATE_HONOUR(219),
    AZWAN_KILLED(222),
    SILENT_CRUSADE_MSG(226),
    SILENT_CRUSADE_SHOP(227),
    UNLOCK_CHARGE_SKILL(255),
    LOCK_CHARGE_SKILL(256),
    EVOLVING_ACTION(259),
    CANDY_RANKING(264),
    MESSENGER_OPEN(269),// # needs updating.
    AVATAR_MEGA(274),
    AVATAR_MEGA_REMOVE(275),
    EVENT_NAME_TAG(280),
    ITEM_UPGRADE_UI(317),
    UPDATE_GENDER(383),
    BBS_OPERATION(384),
    CARD_DROPS(391),
    GM_POLICE(398),
    GM_STORY_BOARD(406),
    PINKBEAN_CHOCO(408),
    PAM_SONG(409),
    DISALLOW_DELIVERY_QUEST(411),
    MAGIC_WHEEL(418),
    REWARD(419),
    SKILL_MACRO(427),
    ON_RED_CUBE_RESULT(534),
    ON_BLACK_CUBE_RESULT(251),
    SHOW_POTENTIAL_BLACK_CUBE(529),
    // CStage::OnPacket
    SET_FIELD(428),

    // CMobPool::OnPacket
    MOB_ENTER_FIELD(908),
    ;

    private short value;

    OutHeader(int value) {
        this.value = (short) value;
    }

    public short getValue() {
        return value;
    }

    public static OutHeader getOutHeaderByOp(int op) {
        for (OutHeader outHeader : OutHeader.values()) {
            if (outHeader.getValue() == op) {
                return outHeader;
            }
        }
        return null;
    }

    public static boolean isSpamHeader(OutHeader outHeader) {
        List<OutHeader> spam = Arrays.asList(
                OutHeader.PING,
                OutHeader.HEARTBEAT_RESPONSE

                );
        return spam.contains(outHeader);
    }

}
