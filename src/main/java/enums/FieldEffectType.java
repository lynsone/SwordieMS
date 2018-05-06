package enums;

/**
 * Created on 3/26/2018.
 */
public enum FieldEffectType {
    FromString(0),
    Tremble(1),
    ObjectStateByString(2),
    DisableEffectObject(3),
    Unk0(4),
    PlaySound(5),
    MobHPTag(6),
    ChangeBGM(7),
    BGMVolumeOnly(8),
    SetBGMVolume(9),
    RewardRoullet(10),
    Unk1(11),
    Unk2(12),
    TopScreenEffect(13),
    ScreenEffect(14),
    ScreenFloatingEffect(15),
    CreateWindowMaybeidk(16),
    SetGrey(17),
    OnOffLayer(18),
    SomeAnimation(19),
    MoreAnimation(20),
    RemoveAnimation(21),
    ChangeColor(22),
    StageClearExpOnly(23),
    EvenMoreAnimation(24),
    SkeletonAnimation(25),
    OneTimeSkeletonAnimation(26),


    ;

    private byte val;

    FieldEffectType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
