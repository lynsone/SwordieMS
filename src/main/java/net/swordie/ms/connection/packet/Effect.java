package net.swordie.ms.connection.packet;


import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.TextEffectType;
import net.swordie.ms.enums.UserEffectType;

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
                outPacket.encodeInt(getArg2()); //Delay per letter
                outPacket.encodeInt(getArg1()); //Duration of Box
                outPacket.encodeInt(getArg3()); //Positioning on Client  ( 4 = middle )
                outPacket.encodeInt(getArg4()); // xPos

                outPacket.encodeInt(getArg5()); // yPos

                outPacket.encodeInt(getArg6()); // Align
                outPacket.encodeInt(getArg3()); // Line space
                outPacket.encodeInt(getArg7()); // Type
                outPacket.encodeInt(getArg4()); // Text Box enter ( 0 = fade in)
                outPacket.encodeInt(getArg4()); // Text Box enter ( 0 = fade in)
                break;
            case ResetOnStateForOnOffSkill:
                outPacket.encodeByte(getArg1());
                break;
        }
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


    public static Effect createBurningFieldTextEffect(String string) {
        Effect effect = new Effect();

        effect.setUserEffectType(TextEffect);
        effect.setString(string);
        effect.setArg1(2000);
        effect.setArg2(50);
        effect.setArg3(4);
        effect.setArg4(0);
        effect.setArg5(0xFFFFFF38);
        effect.setArg6(1);
        effect.setArg7(TextEffectType.BurningField.getVal());// Type

        return effect;
    }

    public static Effect createABRechargeEffect() {
        Effect effect = new Effect();

        effect.setArg1(1);

        return effect;
    }
}
