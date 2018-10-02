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
    // 11
    AskPet(12, ResponseType.Answer),
    AskPetAll(13, ResponseType.Answer),
    AskActionPetEvolution(14, ResponseType.Answer),
    Script(15, ResponseType.Answer),
    AskAccept(16, ResponseType.Response),
    AskBoxtext(17, ResponseType.Answer),
    AskSlideMenu(18, ResponseType.Answer),
    AskIngameDirection(19, ResponseType.Response),
    PlayMovieClip(20, ResponseType.Response),
    AskCenter(21, ResponseType.Answer),
    AskAvatar2(22, ResponseType.Answer),
    AskSelectMenu(23, ResponseType.Answer),
    AskAngelicBuster(24, ResponseType.Answer),
    SayIllustration(25, ResponseType.Answer),
    SayDualIllustration(26, ResponseType.Answer),
    AskYesNoIllustration(27, ResponseType.Answer),
    AskAcceptIllustration(28, ResponseType.Answer),
    AskMenuIllustration(29, ResponseType.Answer),
    AskYesNoDualIllustration(30, ResponseType.Answer),
    AskAcceptDualIllustration(31, ResponseType.Answer),
    AskMenuDualIllustration(32, ResponseType.Answer),
    AskSNN2(33, ResponseType.Answer),
    // 34
    // 35
    AskAvatarZero(36, ResponseType.Answer),
    Monologue(37, ResponseType.Response),
    AskWeaponBox(38, ResponseType.Answer),
    AskBoxTextBgImg(39, ResponseType.Answer),
    AskUserSurvey(40, ResponseType.Answer),
    SuccessCamera(41, ResponseType.Answer),
    AskMixHair(42, ResponseType.Answer),
    AskMixHairExZero(43, ResponseType.Answer),
    OnAskCustomMixHair(44, ResponseType.Answer),
    OnAskCustomMixHairAndProb(45, ResponseType.Answer),
    OnAskMixHairNew(46, ResponseType.Answer),
    OnAskMixHairNewExZero(47, ResponseType.Answer),
    NpcAction(48, ResponseType.Answer),
    OnAskScreenShinningStarMsg(49, ResponseType.Answer),
    InputUI(50, ResponseType.Answer),
    OnAskNumberUseKeyPad(51, ResponseType.Answer),
    OnSpinOffGuitarRhythmGame(52, ResponseType.Answer),
    OnGhostParkEnter(53, ResponseType.Answer),
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
