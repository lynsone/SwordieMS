package net.swordie.ms.enums;

/**
 * Created by MechAviv on 23/11/2018.
 */
public enum MakingSkillResult {
    // didn't find the enum in kmst leak...
    SUCESS_1(25),// success with no message
    SUCESS_2(26),// sucesss with good message
    SUCESS_3(27),// success with cool message
    UNKNOWN_ERROR(28),// shows fail effect with error message
    CRAFTING_FAILED(29),// shows fail effect with fail message
    EXTRACTOR_DISABLED_ERROR(30),
    EXTRACTOR_OWNER_NOT_RECEIVE_FEE_ERROR(31),
    EXTRACTION_FEE_TOO_LARGE(32),
    CANNOT_AFFORD_PARTY_LISTING(33);

    private byte val;

    MakingSkillResult(int val) {this.val = (byte) val;}

    public byte getVal() {
        return val;
    }
}
