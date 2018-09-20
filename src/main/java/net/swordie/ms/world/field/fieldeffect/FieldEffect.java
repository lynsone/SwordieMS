package net.swordie.ms.world.field.fieldeffect;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.util.Util;

/**
 * Created on 3/26/2018.
 */
public class FieldEffect {

    private FieldEffectType fieldEffectType;
    private String string;
    private int arg1;
    private int arg2;
    private int arg3;
    private int arg4;
    private int arg5;
    private int arg6;

    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getFieldEffectType().getVal());
        switch (getFieldEffectType()) {

            case ObjectStateByString:
                outPacket.encodeString(getString());// String
                break;
            case DisableEffectObject:
                outPacket.encodeString(getString());// String
                outPacket.encodeByte(getArg1());    // boolean: ON/OFF
                break;
            case MobHPTag:
                outPacket.encodeInt(getArg1());     // Mob Template ID
                outPacket.encodeInt(getArg2());     // Mob HP
                outPacket.encodeInt(getArg3());     // Mob max HP
                outPacket.encodeByte(getArg4());    // HP Tag Colour
                outPacket.encodeByte(getArg5());    // HP Tab BG Colour
                break;
            case RewardRoulette:
                outPacket.encodeInt(getArg1());     // Reward Job ID
                outPacket.encodeInt(getArg2());     // Reward Part ID
                outPacket.encodeInt(getArg3());     // Reward Level ID
                break;
            case TopScreen:
                outPacket.encodeString(getString());// Directory to the Effect
                break;
            case TopScreenEffect:                   // Goes over other effects
                outPacket.encodeString(getString());// Directory to the Effect
                outPacket.encodeInt(getArg1());     // Delay in ms
                break;
            case ScreenEffect:
                outPacket.encodeString(getString());// Path to the Effect
                outPacket.encodeInt(getArg1());     // Delay in ms
                break;
            case BackScreen:
                outPacket.encodeString(getString());// Directory to the Effect
                outPacket.encodeInt(getArg1());     // Delay in ms
                break;
            case Blind:
                outPacket.encodeByte(getArg1());
                outPacket.encodeShort(getArg2());
                outPacket.encodeShort(getArg3());
                outPacket.encodeShort(getArg4());
                outPacket.encodeShort(getArg5());
                outPacket.encodeInt(getArg6());
                break;
            case SetGrey:
                outPacket.encodeShort(getArg1());   // GreyField Type
                outPacket.encodeByte(getArg2());    // boolean: ON/OFF
                break;
            case ChangeColor:
                outPacket.encodeShort(getArg1());   // GreyField Type (but doesn't contain Reactor
                outPacket.encodeShort(getArg2());   // red      (250 is normal value)
                outPacket.encodeShort(getArg3());   // green    (250 is normal value)
                outPacket.encodeShort(getArg4());   // blue     (250 is normal value)
                outPacket.encodeInt(getArg5());     // time in ms, that it takes to transition from old colours to the new colours
                outPacket.encodeInt(0);          // is in queue
                break;
            case OverlapScreen:                    // Takes a Snapshot of the Client and slowly fades away
                outPacket.encodeInt(getArg1());     // Duration of the overlap (ms)
                break;
            case OverlapScreenDetail:
                outPacket.encodeInt(getArg1());     // Fade In
                outPacket.encodeInt(getArg2());     // wait time
                outPacket.encodeInt(getArg3());     // Fade Out
                outPacket.encodeByte(getArg4());    // some boolean
                break;
            case RemoveOverlapScreen:
                outPacket.encodeInt(getArg1());     // Fade Out duration
            case StageClearExpOnly:
                outPacket.encodeInt(getArg1());     // Exp Number given
                break;
            case PlaySound:
                outPacket.encodeString(getString());// Sound
                outPacket.encodeInt(getArg1());// Volume
                break;
        }
    }

    public static FieldEffect mobHPTagFieldEffect(Mob mob) {
        FieldEffect fieldEffect = new FieldEffect();
        fieldEffect.setFieldEffectType(FieldEffectType.MobHPTag);

        fieldEffect.setArg1(mob.getTemplateId());
        int maxHP = Util.maxInt(mob.getMaxHp());
        double ratio = mob.getMaxHp() / (double) Integer.MAX_VALUE;
        fieldEffect.setArg2(ratio > 1 ? (int) (mob.getHp() / ratio) : (int) mob.getHp());
        fieldEffect.setArg3(maxHP);
        fieldEffect.setArg4(mob.getHpTagColor());
        fieldEffect.setArg5(mob.getHpTagBgcolor());

        return fieldEffect;
    }

    public static FieldEffect getFieldEffectFromWz(String dir, int delay) {
        FieldEffect fieldEffect = new FieldEffect();
        fieldEffect.setFieldEffectType(FieldEffectType.ScreenEffect);

        fieldEffect.setString(dir);
        fieldEffect.setArg1(delay);

        return fieldEffect;
    }

    public static FieldEffect getFieldBackgroundEffectFromWz(String dir, int delay) {
        FieldEffect fieldEffect = new FieldEffect();
        fieldEffect.setFieldEffectType(FieldEffectType.BackScreen);

        fieldEffect.setString(dir);
        fieldEffect.setArg1(delay);

        return fieldEffect;
    }

    public static FieldEffect getOffFieldEffectFromWz(String dir, int delay) {
        FieldEffect fieldEffect = new FieldEffect();
        fieldEffect.setFieldEffectType(FieldEffectType.TopScreenEffect);

        fieldEffect.setString(dir);
        fieldEffect.setArg1(delay);

        return fieldEffect;
    }

    public static FieldEffect setFieldGrey(GreyFieldType greyFieldType, boolean setGrey) {
        FieldEffect fieldEffect = new FieldEffect();
        fieldEffect.setFieldEffectType(FieldEffectType.SetGrey);

        fieldEffect.setArg1(greyFieldType.getVal());
        fieldEffect.setArg2(setGrey ? 1 : 0);

        return fieldEffect;
    }

    public static FieldEffect setFieldColor(GreyFieldType colorFieldType, short red, short green, short blue, int time) {
        FieldEffect fieldEffect = new FieldEffect();
        fieldEffect.setFieldEffectType(FieldEffectType.ChangeColor);

        fieldEffect.setArg1(colorFieldType.getVal());
        fieldEffect.setArg2(red);
        fieldEffect.setArg3(green);
        fieldEffect.setArg4(blue);
        fieldEffect.setArg5(time);

        return fieldEffect;
    }

    public static FieldEffect showClearStageExpWindow(int expNumber) {
        FieldEffect fieldEffect = new FieldEffect();
        fieldEffect.setFieldEffectType(FieldEffectType.StageClearExpOnly);

        fieldEffect.setArg1(expNumber);

        return fieldEffect;
    }

    public static FieldEffect takeSnapShotOfClient(int duration) {
        FieldEffect fieldEffect = new FieldEffect();
        fieldEffect.setFieldEffectType(FieldEffectType.OverlapScreen);

        fieldEffect.setArg1(duration);

        return fieldEffect;
    }

    public static FieldEffect takeSnapShotOfClient2(int transitionDurationToSnapShot, int inBetweenDuration, int transitionBack, boolean someBoolean) {
        FieldEffect fieldEffect = new FieldEffect();
        fieldEffect.setFieldEffectType(FieldEffectType.OverlapScreenDetail);

        fieldEffect.setArg1(transitionDurationToSnapShot);
        fieldEffect.setArg2(inBetweenDuration);
        fieldEffect.setArg3(transitionBack);
        fieldEffect.setArg4(someBoolean ? 1 : 0);

        return fieldEffect;
    }

    public static FieldEffect playSound(String sound, int vol) {
        FieldEffect fieldEffect = new FieldEffect();
        fieldEffect.setFieldEffectType(FieldEffectType.PlaySound);

        fieldEffect.setString(sound);
        fieldEffect.setArg1(vol);

        return fieldEffect;
    }

    public static FieldEffect blind(int enable, int x, int color, int unk1, int unk2, int time) {
        FieldEffect fieldEffect = new FieldEffect();
        fieldEffect.setFieldEffectType(FieldEffectType.Blind);

        fieldEffect.setArg1(enable);
        fieldEffect.setArg2(x);
        fieldEffect.setArg3(color);
        fieldEffect.setArg4(unk1);
        fieldEffect.setArg5(unk2);
        fieldEffect.setArg6(time);

        return fieldEffect;
    }

    public FieldEffectType getFieldEffectType() {
        return fieldEffectType;
    }

    public void setFieldEffectType(FieldEffectType fieldEffectType) {
        this.fieldEffectType = fieldEffectType;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
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

}
