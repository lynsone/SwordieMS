package net.swordie.ms.life.npc;

/**
 * Created on 2/19/2018.
 */
public enum NpcMessageType {
    SayOk(0, false, false),
    SayNext(0, false, true),
    SayPrev(0, true, false),
    Say(0, true, true),
    SayImage(1),
    AskYesNo(2),
    AskText(3),
    AskNumber(4),
    AskMenu(5),
    InitialQuiz(6),
    InitialSpeedQuiz(7),
    ICQuiz(8),
    AskAvatar(9),
    AskAndroid(10),
    AskPet(11),
    AskPetAll(12),
    AskActionPetEvolution(13),
    AskAccept(15),
    AskBoxtext(16),
    AskSlideMenu(18),
    AskSelectMenu(21),
    AskAngelicBuster(22),
    SayIllustration(23),
    SayDualIllustration(24),
    AskYesNoIllustration(25),
    AskAcceptIllustration(26),
    AskMenuIllustration(27),
    AskYesNoDualIllustration(28),
    AskAcceptDualIllustration(29),
    AskMenuDualIllustration(30),
    AskAvatarZero(32),
    AskWeaponBox(34),
    AskBoxTextBgImg(35),
    AskUserSurvey(36),
    AskMixHair(38),
    AskMixHairExZero(39),
    OnAskCustomMixHair(40),
    OnAskCustomMixHairAndProb(41),
    OnAskMixHairNew(42),
    OnAskMixHairNewExZero(43),
    OnAskScreenShinningStarMsg(45),
    OnAskNumberUseKeyPad(47),
    OnSpinOffGuitarRhythmGame(48),
    OnGhostParkEnter(49),
    None(-1),
    ;

    private byte val;
    private boolean prevPossible, nextPossible;
    private int delay;

    NpcMessageType(int val) {
        this.val = (byte) val;
        prevPossible = false;
        nextPossible = false;
    }

    NpcMessageType(int val, boolean prev, boolean next) {
        this.val = (byte) val;
        prevPossible = prev;
        nextPossible = next;
    }

    public byte getVal() {
        return val;
    }

    public boolean isPrevPossible() {
        return prevPossible;
    }

    public boolean isNextPossible() {
        return nextPossible;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
