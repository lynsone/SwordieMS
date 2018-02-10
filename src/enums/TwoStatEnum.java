package enums;

import client.character.skills.CharacterTemporaryStat;

import java.util.Arrays;

/**
 * Created on 2/3/2018.
 * @author Eric
 */
public enum TwoStatEnum {
    EnergyCharged(0),
    DashSpeed(1),
    DashJump(2),
    RideVehicle(3),
    PartyBooster(4),
    GuidedBullet(5),
    Undead(6);
    private final int index;

    TwoStatEnum(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static TwoStatEnum getTSEByIndex(int index) {
        return Arrays.stream(TwoStatEnum.values()).filter(tse -> tse.getIndex() == index).findFirst().orElse(null);
    }

    public static CharacterTemporaryStat getCTSFromTwoStatIndex(int index) {
        switch(index) {
            case 0:
                return CharacterTemporaryStat.EnergyCharged;
            case 1:
                return CharacterTemporaryStat.DashSpeed;
            case 2:
                return CharacterTemporaryStat.DashJump;
            case 3:
                return CharacterTemporaryStat.RideVehicle;
            case 4:
                return CharacterTemporaryStat.PartyBooster;
            case 5:
                return CharacterTemporaryStat.GuidedBullet;
            case 6:
                return CharacterTemporaryStat.Undead;
            default:
                return null;
        }
    }

    public static TwoStatEnum getTSEFromCTS(CharacterTemporaryStat cts) {
        switch(cts) {
            case EnergyCharged:
                return EnergyCharged;
            case DashJump:
                return DashJump;
            case DashSpeed:
                return DashSpeed;
            case RideVehicle:
                return RideVehicle;
            case PartyBooster:
                return PartyBooster;
            case GuidedBullet:
                return GuidedBullet;
            case Undead:
                return Undead;
        }
        return null;
    }

    public static boolean isTwoStat(CharacterTemporaryStat cts) {
        return getTSEFromCTS(cts) != null;
    }
}
