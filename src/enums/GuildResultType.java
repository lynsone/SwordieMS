package enums;

/**
 * Created on 3/21/2018.
 */
public enum GuildResultType {
    // Actions
    InputGuildName(3),
    GuildCreateConfirmation(5),
    JoinRequest(7),
    CreateGuildMarkDlg(22),
    NotSureButItsImportantSomethingWithAlliancemaybe(49),
    GuildFindMaybe(50),
    Create(56),
    GuildMasterChange(69),
    Expel1(75),
    Expel2(78),
    RenameMemberChar(92),
    RenameRequestorChar(93),
    UpdateMemberInfo(94),
    UpdateMemberLogin(95),
    UpdateGradeNames(96),
    UpdateMemberGrade(98),
    UpdateMemberCommitment(100),
    UpdateGuildMark(101),
    UpdateGuildNotice(103),
    UpdateGuildPointsAndLevel(107),
    ShowGuildRankings(108),
    UpdateGGP(109),
    UpdateMemberIGP(110),
    UpdateGuildBoardAuthkey(114),
    UpdateSkill(115),
    DeleteAllSkills(118),
    UpdateGuildBattleSP(127),
    UpdateGuildRank(129),

    // Messages
    GuildCreationDisabledByMergeMsg(46),
    AllianceCreationDisabledByMergeMsg(47),
    GuildNameInUseMsg(52),
    AllianceDisagreementMsg(55),
    AlreadyJoinedGuildMsg(57),
    CannotCreateGuildByLevelMsg(59),
    GuildDisagreementMsg(60),
    ProblemDuringGuildFormMsg(62),
    JoinMsg(63),
    AlreadyJoinedGuildMsg2(64),
    ReachedMaxNumberOfUsersMsg(65),
    CharacterCannotBeFoundInChannelMsg(66),
    CannotFindRequestorCharacterMsg(67),
    CannotRequestGuildByRestrictionMsg(72),
    GuildRequestCancelMsg(74),
    NotInGuildMsg1(76),
    NotInGuildMsg2(77),
    DisbandMsg(81),
    DisbandProblemMsg(83),
    DisbandMsg2(84),
    CurrentlyNotAcceptingRequestsMsg(85),
    TakingCareOfAnotherRequestMsg(86),
    GuildInvitationDenyMsg(87),
    AdminCannotMakeGuildMsg(88),
    MemberCountIncreaseSuccessMsg(90),
    MemberCountIncreaseFailMsg(91),
    EmblemCreateFailByLevelMsg(102),
    CannotContinueGuildRunByMemberCountMsg(111),
    CannotContinueGuildRunByMemberDisconnectMsg(112),
    UpdateGPQQueueMsg(113),
    ExtendGuildSkillFailMsg(116),
    SpFailMsg(117), // true = failed to reset, false = failed to level up
    GuildSkillErrorMsg(120),
    GuildNameChangeMsg(121),
    NotAllowedNameMsg(122),
    ChangeGuildMasterOrAllianceLeaderMsg(123),
    ZeroCannotAcceptFriendsByPrologueMsg(125),
    ZeroCannotAcceptGuildsByPrologueMsg(126),
    PinkBeanCannotBeGuildLeader(131),
    ;

    private byte val;

    GuildResultType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
