package net.swordie.ms.life.npc;

/**
 * Created on 2/19/2018.
 */
public enum NpcMessageType {
    SayOk(0, false, false, ResponseType.Response),
    SayNext(0, false, true, ResponseType.Response),
    SayPrev(0, true, false, ResponseType.Response),
    Say(0, true, true, ResponseType.Response),
    SayImage(1, ResponseType.Response),
    AskYesNo(2, ResponseType.Response),
    AskText(3, ResponseType.Text),
    AskNumber(4, ResponseType.Answer),
    AskMenu(5, ResponseType.Answer),
    InitialQuiz(6, ResponseType.Answer),
    InitialSpeedQuiz(7, ResponseType.Answer),
    ICQuiz(8, ResponseType.Answer),
    AskAvatar(9, ResponseType.Answer),
    AskAndroid(10, ResponseType.Answer),
    AskPet(11, ResponseType.Answer),
    AskPetAll(12, ResponseType.Answer),
    AskActionPetEvolution(13, ResponseType.Answer),
    AskAccept(16, ResponseType.Response),
    AskBoxtext(17, ResponseType.Answer),
    AskSlideMenu(18, ResponseType.Answer),
    InGameDirectionsAnswer(19, ResponseType.Response),
    AskSelectMenu(21, ResponseType.Answer),
    AskAngelicBuster(22, ResponseType.Answer),
    SayIllustration(23, ResponseType.Answer),
    SayDualIllustration(24, ResponseType.Answer),
    AskYesNoIllustration(25, ResponseType.Answer),
    AskAcceptIllustration(26, ResponseType.Answer),
    AskMenuIllustration(27, ResponseType.Answer),
    AskYesNoDualIllustration(28, ResponseType.Answer),
    AskAcceptDualIllustration(29, ResponseType.Answer),
    AskMenuDualIllustration(30, ResponseType.Answer),
    AskAvatarZero(32, ResponseType.Answer),
    AskWeaponBox(34, ResponseType.Answer),
    AskBoxTextBgImg(35, ResponseType.Answer),
    AskUserSurvey(36, ResponseType.Answer),
    Monologue(37, ResponseType.Response),
    AskMixHair(38, ResponseType.Answer),
    AskMixHairExZero(39, ResponseType.Answer),
    OnAskCustomMixHair(40, ResponseType.Answer),
    OnAskCustomMixHairAndProb(41, ResponseType.Answer),
    OnAskMixHairNew(42, ResponseType.Answer),
    OnAskMixHairNewExZero(43, ResponseType.Answer),
    OnAskScreenShinningStarMsg(45, ResponseType.Answer),
    OnAskNumberUseKeyPad(47, ResponseType.Answer),
    OnSpinOffGuitarRhythmGame(48, ResponseType.Answer),
    OnGhostParkEnter(49, ResponseType.Answer),
    None(-1, ResponseType.Answer),
    ;

    private byte val;
    private boolean prevPossible, nextPossible;
    private int delay;
    private ResponseType responseType;

    NpcMessageType(int val, ResponseType responseType) {
        this.val = (byte) val;
        prevPossible = false;
        nextPossible = false;
        this.responseType = responseType;
    }

    NpcMessageType(int val, boolean prev, boolean next, ResponseType responseType) {
        this.val = (byte) val;
        prevPossible = prev;
        nextPossible = next;
        this.responseType = responseType;
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


    public ResponseType getResponseType() {
        return responseType;
    }

    public enum ResponseType {
        Response, Answer, Text
    }
}
