package net.swordie.ms.client.party;

/**
 * Created on 3/19/2018.
 */
public enum PartyType {
    PartyReq_LoadParty(0),
    PartyReq_CreateNewParty(1),
    PartyReq_WithdrawParty(2),
    PartyReq_JoinParty(3),
    PartyReq_InviteParty(4),
    PartyReq_InviteIntrusion(5), // member -> party request
    PartyReq_KickParty(6), // party -> member request
    PartyReq_ChangePartyBoss(7),
    PartyReq_ApplyParty(8),
    PartyReq_SetAppliable(9),
    PartyReq_ClearIntrusion(10),
    PartyReq_CreateNewParty_Group(11),
    PartyReq_JoinParty_Group(12),
    PartyReq_PartySetting(13),
    PartyReq_LoadStarPlanetPoint(14),

    PartyRes_LoadParty_Done(15),
    PartyRes_CreateNewParty_Done(16),
    PartyRes_CreateNewParty_AlreayJoined(17),
    PartyRes_CreateNewParty_Beginner(18),
    PartyRes_CreateNewParty_Unknown(19),
    PartyRes_CreateNewParty_byNonBoss(20),

    PartyRes_WithdrawParty_Done(21),
    PartyRes_WithdrawParty_NotJoined(22),
    PartyRes_WithdrawParty_Unknown(23),

    PartyRes_JoinParty_Done(24),
    PartyRes_JoinParty_Done2(25), // join msg

    PartyRes_JoinParty_AlreadyJoined(26),
    PartyRes_JoinParty_AlreadyFull(27),
    PartyRes_JoinParty_OverDesiredSize(28),
    PartyRes_JoinParty_UnknownUser(29),
    PartyRes_JoinParty_Unknown(30),

    PartyRes_JoinIntrusion_Done(31),
    PartyRes_JoinIntrusion_UnknownParty(32),

    PartyRes_InviteParty_Sent(33),
    PartyRes_InviteParty_BlockedUser(34),
    PartyRes_InviteParty_AlreadyInvited(35),
    PartyRes_InviteParty_AlreadyInvitedByInviter(36),
    PartyRes_InviteParty_Rejected(37),
    PartyRes_InviteParty_Accepted(38),

    PartyRes_InviteIntrusion_Sent(39),
    PartyRes_InviteIntrusion_BlockedUser(40),
    PartyRes_InviteIntrusion_AlreadyInvited(41),
    PartyRes_InviteIntrusion_AlreadyInvitedByInviter(42),
    PartyRes_InviteIntrusion_Rejected(43),
    PartyRes_InviteIntrusion_Accepted(44),

    PartyRes_KickParty_Done(45),
    PartyRes_KickParty_FieldLimit(46),
    PartyRes_KickParty_Unknown(47),

    PartyRes_ChangePartyBoss_Done(48),
    PartyRes_ChangePartyBoss_NotSameField(49),
    PartyRes_ChangePartyBoss_NoMemberInSameField(50),
    PartyRes_ChangePartyBoss_NotSameChannel(51),
    PartyRes_ChangePartyBoss_Unknown(52),

    PartyRes_AdminCannotCreate(53),
    PartyRes_AdminCannotInvite(54),

    PartyRes_InAnotherWorld(55),
    PartyRes_InAnotherChanelBlockedUser(56),

    PartyRes_UserMigration(57),
    PartyRes_ChangeLevelOrJob(58),
    PartyRes_UpdateShutdownStatus(59),
    PartyRes_SetAppliable(60),
    PartyRes_SetAppliableFailed(61),
    PartyRes_SuccessToSelectPQReward(62),
    PartyRes_FailToSelectPQReward(63),
    PartyRes_ReceivePQReward(64),
    PartyRes_FailToRequestPQReward(65),
    PartyRes_CanNotInThisField(66),

    PartyRes_ApplyParty_Sent(67),
    PartyRes_ApplyParty_UnknownParty(68),
    PartyRes_ApplyParty_BlockedUser(69),
    PartyRes_ApplyParty_AlreadyApplied(70),
    PartyRes_ApplyParty_AlreadyAppliedByApplier(71),
    PartyRes_ApplyParty_AlreadyFull(72),
    PartyRes_ApplyParty_Rejected(73),
    PartyRes_ApplyParty_Accepted(74),

    PartyRes_FoundPossibleMember(75),
    PartyRes_FoundPossibleParty(76),

    PartyRes_PartySettingDone(77),
    PartyRes_Load_StarGrade_Result(78),
    PartyRes_Load_StarGrade_Result2(79),
    PartyRes_Member_Rename(80),

    PartyInfo_TownPortalChanged(81),
    PartyInfo_OpenGate(82),

    ExpeditionReq_Load(83),
    ExpeditionReq_CreateNew(84),
    ExpeditionReq_Invite(85),
    ExpeditionReq_ResponseInvite(86),
    ExpeditionReq_Withdraw(87),
    ExpeditionReq_Kick(88),
    ExpeditionReq_ChangeMaster(89),
    ExpeditionReq_ChangePartyBoss(90),
    ExpeditionReq_RelocateMember(91),

    ExpeditionNoti_Load_Done(92),
    ExpeditionNoti_Load_Fail(93),
    ExpeditionNoti_CreateNew_Done(94),
    ExpeditionNoti_Join_Done(95),
    ExpeditionNoti_You_Joined(96),
    ExpeditionNoti_You_Joined2(97),
    ExpeditionNoti_Join_Fail(98),
    ExpeditionNoti_Withdraw_Done(99),
    ExpeditionNoti_You_Withdrew(100),
    ExpeditionNoti_Kick_Done(101),
    ExpeditionNoti_You_Kicked(102),
    ExpeditionNoti_Removed(103),
    ExpeditionNoti_MasterChanged(104),
    ExpeditionNoti_Modified(105),
    ExpeditionNoti_Modified2(106),
    ExpeditionNoti_Invite(107),
    ExpeditionNoti_ResponseInvite(108),
    ExpeditionNoti_Create_Fail_By_Over_Weekly_Counter(109),
    ExpeditionNoti_Invite_Fail_By_Over_Weekly_Counter(110),
    ExpeditionNoti_Apply_Fail_By_Over_Weekly_Counter(111),
    ExpeditionNoti_Invite_Fail_By_Blocked_Behavior(112),

    AdverNoti_LoadDone(113),
    AdverNoti_Change(114),
    AdverNoti_Remove(115),
    AdverNoti_GetAll(116),
    AdverNoti_Apply(117),
    AdverNoti_ResultApply(118),
    AdverNoti_AddFail(119),
    AdverReq_Add(120),
    AdverReq_Remove(121),
    AdverReq_GetAll(122),
    AdverReq_RemoveUserFromNotiList(123),
    AdverReq_Apply(124),
    AdverReq_ResultApply(125),

    ;

    private byte val;

    PartyType(int val) {
        this.val = (byte) val;
    }

    public static PartyType getByVal(byte type) {
        if (type >= 0 && type <= values().length) {
            return values()[type];
        }
        return null;
    }

    public byte getVal() {
        return val;
    }
}
