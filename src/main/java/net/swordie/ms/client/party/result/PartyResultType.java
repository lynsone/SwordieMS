package net.swordie.ms.client.party.result;

/**
 * Created on 3/19/2018.
 */
public enum PartyResultType {
    DefaultErrorMsg(0),
    ApplyRequest(4),
    PartyApplyRequestPurple(5), // member -> party request
    PartyJoinRequestBlue(8), // party -> member request

    UserUpdate(15),
    Create(16),
    AlreadyJoinedPartyMsg(17),
    BeginnerCantCreatePartyMsg(18),

    Leave(21),
    YetToJoinAPartyMsg(22),

    Join(24),
    JoinMsg(25),
    AlreadyJoinedPartyMsg2(26),
    FullPartyMsg(27),
    JoinMsg4(28),
    YouInvitedMsg1(33),
    YouInvitedMsg2(39),
    CannotKickInThisMapMsg(46),

    LeadershipTransfer(48),
    CanOnlyBeGivenToVicinityMsg(49),
    UnableToHandOverLeadershipMsg(50),
    OnlyChangeLeadershipOnSameChannelMsg(51),
    GMForbiddenCreatePartyMsg(53),
    DifferentWorldCannotJoinMsg(55),
    CannotFindRequestChannelCharMsg(56),

    UpdateUserLevelAndJob(58),
    UpdateMemberLoggedIn(59),
    ChangedPartyJoinRequestMsg(60),
    PartySettingsCouldNotBeChangedMsg(61),

    PartyReward(62), // unsure
    RewardFail(63),
    ReceiveReward(63),
    FailToRequestReward(63),
    CannotBeDoneInCurrentMapMsg(66),
    YouRequestToJoinPartyMsg(67),
    PossiblePartyMatch(75),
    PossiblePartyMatch2(76),
    PartySettingsChanged(77),
    SetPartyStarGrade(78),
    SetPartyStarGrade2(79),
    UpdatePartyHPBox(80),
    TownPortalCreated(81),
    NewLeaderMsg(82),
    ApplyResult(117),
    // TabPartyAdver::OnPacket

    ;

    private byte val;

    PartyResultType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }

    public void setVal(byte val) {
        this.val = val;
    }
}
