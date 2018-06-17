package net.swordie.ms.connection.packet;


import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.TextEffectType;
import net.swordie.ms.enums.UserEffectType;
import net.swordie.ms.util.Position;

import static net.swordie.ms.enums.UserEffectType.*;

public class Effect {

    private UserEffectType userEffectType;
    private String string;

    private int arg1;
    private int arg2;
    private int arg3;
    private int arg4;
    private int arg5;
    private int arg6;
    private int arg7;
    private int arg8;
    private int arg9;
    private int arg10;


    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getUserEffectType().getVal());

        switch (getUserEffectType()) {
            case TextEffect:
                outPacket.encodeString(getString());
                outPacket.encodeInt(getArg1()); // letter delay
                outPacket.encodeInt(getArg2()); // box duration
                outPacket.encodeInt(getArg3()); // Positioning on Client  ( 4 = middle )

                outPacket.encodeInt(getArg4()); // xPos
                outPacket.encodeInt(getArg5()); // yPos

                outPacket.encodeInt(getArg6()); // Align
                outPacket.encodeInt(getArg7()); // Line space
                outPacket.encodeInt(getArg8()); // Enter type (0 = fade in)
                outPacket.encodeInt(getArg9()); // Leave type?
                outPacket.encodeInt(getArg10()); // Type
                break;
            case FieldExpItemConsumed:
                outPacket.encodeInt(getArg1()); // Exp Gained
                break;
            case SkillMode:
                outPacket.encodeInt(getArg1()); // Skill ID
                outPacket.encodeInt(getArg2()); // Rotate (?)
                outPacket.encodeInt(getArg3()); // Skip Frame (?)
                break;
            case RobbinsBomb:
                outPacket.encodeByte(getArg1()); // Reset/Delete
                outPacket.encodeInt(getArg2()); // BombCount
                outPacket.encodeByte(getArg3()); // number (unknown)
                break;
            case PetBuff:
            case ResetOnStateForOnOffSkill:
            case SoulStoneUse:
            case ItemLevelUp:
            case ExpItemConsumed:
            case QuestComplete:
            case JobChanged:
            case BuffItemEffect:
            case Resist:
            case LevelUp:
                break;
            case LeftMonsterNumber:
                outPacket.encodeInt(getArg1()); // Number on Arrow
                break;
            case JewelCraft:
                outPacket.encodeByte(getArg1()); // Result  0,2 = Success,     5 = Unk error,      Other = Fail
                outPacket.encodeInt(getArg2()); // Item ID
                break;
            case AswanSiegeAttack:
                outPacket.encodeByte(getArg1()); // 0 = Red Colour,     1 = Orange Colour
                break;
            case BlindEffect:
                outPacket.encodeByte(getArg1()); //  true/false
                break;
            case ItemMaker:
                outPacket.encodeInt(getArg1());  // success or failure      0 = Success,  1 = Failure
                break;
            case IncDecHPEffect:
                outPacket.encodeByte(getArg1()); // amount being healed     0 = Miss
        }
    }


    public static Effect createFieldTextEffect(String msg, int letterDelay, int showTime, int clientPosition,
                                               Position boxPos, int align, int lineSpace, TextEffectType type,
                                               int enterType, int leaveType) {
        Effect effect = new Effect();
        effect.setUserEffectType(TextEffect);

        effect.setString(msg);
        effect.setArg1(letterDelay);
        effect.setArg2(showTime);
        effect.setArg3(clientPosition);
        effect.setArg4(boxPos.getX());
        effect.setArg5(boxPos.getY());
        effect.setArg6(align);
        effect.setArg7(lineSpace);
        effect.setArg8(type.getVal());
        effect.setArg9(enterType);
        effect.setArg10(leaveType);

        return effect;
    }

    public static Effect createABRechargeEffect() { // Angelic Buster's Recharge userEffect
        Effect effect = new Effect();
        effect.setUserEffectType(ResetOnStateForOnOffSkill);

        return effect;
    }

    public static Effect fieldItemConsumed(int expGained) { // [exp]EXP+  effect from looting ExpOrbs
        Effect effect = new Effect();
        effect.setUserEffectType(FieldExpItemConsumed);

        effect.setArg1(expGained);

        return effect;
    }

    public static Effect skillModeEffect(int skillID, int mode, int modeStatus) { // Unknown Effect
        Effect effect = new Effect();
        effect.setUserEffectType(SkillMode);

        effect.setArg1(skillID);
        effect.setArg2(mode); //rotate
        effect.setArg3(modeStatus); //skip frame

        return effect;
    }

    public static Effect robbinsBombEffect(boolean reset, int bombCount, byte number) { //Displays bomb with [bombCount] on the Bomb, above user,   Unsure what '[int] number' does
        Effect effect = new Effect();
        effect.setUserEffectType(RobbinsBomb);

        effect.setArg1(reset ? 1 : 0);  // if true, resets/delets bombs
        effect.setArg2(bombCount);      // Number displayed on the Bomb
        effect.setArg3(number);         // unknown

        return effect;
    }

    public static Effect lefMonsterNumberEffect(int count) { //Displays arrow pointing towards the user, with [count] on the arrow,  Maximum count is 5
        Effect effect = new Effect();
        effect.setUserEffectType(LeftMonsterNumber);

        effect.setArg1(count); // number/counter on the arrow

        return effect;
    }

    public static Effect petBuffEffect() { //Displays the PetBuff Effect onto the user
        Effect effect = new Effect();
        effect.setUserEffectType(PetBuff);

        return effect;
    }

    public static Effect jewelCraftResultEffect(byte result, int itemID) { // result = Displays Success/Fail effect above player head with smile/frown respectively, itemID will show itemName in chat
        Effect effect = new Effect();
        effect.setUserEffectType(JewelCraft);

        effect.setArg1(result); // 0, 2  = Success/Smile,       5 = Request denied due to unk error,        Everything else = Fail/Frown
        effect.setArg2(itemID);

        return effect;
    }

    public static Effect azwanSpearEffect(byte colour) { // 0 = red,    1 = orange
        Effect effect = new Effect();
        effect.setUserEffectType(AswanSiegeAttack);

        effect.setArg1(colour);

        return effect;
    }

    public static Effect blindEffect(boolean active) {  // gives User the Blind Status Effect
        Effect effect = new Effect();
        effect.setUserEffectType(BlindEffect);

        effect.setArg1(active ? 1 : 0);

        return effect;
    }

    public static Effect soulStoneUseEffect() { // gives the SoulStone Used Chat
        Effect effect = new Effect();
        effect.setUserEffectType(SoulStoneUse);

        return effect;
    }

    public static Effect expItemConsumedEffect() { // gives exp item consumption effect
        Effect effect = new Effect();
        effect.setUserEffectType(ExpItemConsumed);

        return effect;
    }

    public static Effect itemMakerEffect(boolean success) { // displays itemMaker result
        Effect effect = new Effect();
        effect.setUserEffectType(ItemMaker);

        effect.setArg1(success ? 0 : 1); // 0 = Success,    1 = Failure

        return effect;
    }

    public static Effect itemLevelUpEffect() { //displays the Equipment Level Up effect
        Effect effect = new Effect();
        effect.setUserEffectType(ItemLevelUp);

        return effect;
    }

    public static Effect questCompleteEffect() { //displays the Quest Complete effect
        Effect effect = new Effect();
        effect.setUserEffectType(QuestComplete);

        return effect;
    }

    public static Effect incDecHPEffect(byte amount) { //displays the HP healing  effect
        Effect effect = new Effect();
        effect.setUserEffectType(ItemLevelUp);

        effect.setArg1(amount); // amount shown being healed,  0 = miss

        return effect;
    }

    public static Effect changeJobEffect() { //displays the JobAdvancement Effect
        Effect effect = new Effect();
        effect.setUserEffectType(JobChanged);

        return effect;
    }

    public static Effect buffItemEffect() { //displays the Buff Item Effect
        Effect effect = new Effect();
        effect.setUserEffectType(BuffItemEffect);

        return effect;
    }

    public static Effect resistDamageEffect() { //displays the "Resist" Hit
        Effect effect = new Effect();
        effect.setUserEffectType(Resist);

        return effect;
    }

    public static Effect levelUpEffect() { //displays the Level Up  Effect
        Effect effect = new Effect();
        effect.setUserEffectType(LevelUp);

        return effect;
    }





    public void setUserEffectType(UserEffectType userEffectType) {
        this.userEffectType = userEffectType;
    }

    public UserEffectType getUserEffectType() {
        return userEffectType;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public int getArg1() {
        return arg1;
    }

    public void setArg1(int arg1) {
        this.arg1 = arg1;
    }

    public int getArg2() {
        return arg2;
    }

    public void setArg2(int arg2) {
        this.arg2 = arg2;
    }

    public int getArg3() {
        return arg3;
    }

    public void setArg3(int arg3) {
        this.arg3 = arg3;
    }

    public int getArg4() {
        return arg4;
    }

    public void setArg4(int arg4) {
        this.arg4 = arg4;
    }

    public int getArg5() {
        return arg5;
    }

    public void setArg5(int arg5) {
        this.arg5 = arg5;
    }

    public int getArg6() {
        return arg6;
    }

    public void setArg6(int arg6) {
        this.arg6 = arg6;
    }

    public int getArg7() {
        return arg7;
    }

    public void setArg7(int arg7) {
        this.arg7 = arg7;
    }

    public int getArg8() {
        return arg8;
    }

    public void setArg8(int arg8) {
        this.arg8 = arg8;
    }

    public int getArg9() {
        return arg9;
    }

    public void setArg9(int arg9) {
        this.arg9 = arg9;
    }

    public int getArg10() {
        return arg10;
    }

    public void setArg10(int arg10) {
        this.arg10 = arg10;
    }
}
