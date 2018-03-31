package client.character.skills;

import util.Position;
import util.Rect;

public class MobAttackInfo {
    public int mobId;
    public byte idk1;
    public byte idk2;
    public byte idk3;
    public byte idk4;
    public byte idk5;
    public byte calcDamageStatIndex;
    public short rcDstX;
    public short rectRight;
    public short oldPosX;
    public short oldPosY;
    public short hpPerc;
    public int[] damages;
    public int mobUpDownYRange;
    public byte type;
    public String currentAnimationName;
    public int animationDeltaL;
    public String[] hitPartRunTimes;
    public int templateID;
    public short idk6;
    public boolean isResWarriorLiftPress;
    public Position pos1;
    public Position pos2;
    public Rect rect;
    public int idkInt;
    public byte byteIdk1;
    public byte byteIdk2;
    public byte byteIdk3;
    public byte byteIdk4;
    public byte byteIdk5;
    public int psychicLockInfo;
    public byte rocketRushInfo;

    public MobAttackInfo deepCopy() {
        MobAttackInfo mai = new MobAttackInfo();
        mai.mobId = mobId;
        mai.idk1 = idk1;
        mai.idk2 = idk2;
        mai.idk3 = idk3;
        mai.idk4 = idk4;
        mai.idk5 = idk5;
        mai.calcDamageStatIndex = calcDamageStatIndex;
        mai.rcDstX = rcDstX;
        mai.rectRight = rectRight;
        mai.oldPosX = oldPosX;
        mai.oldPosY = oldPosY;
        mai.hpPerc = hpPerc;
        mai.damages = new int[damages.length];
        if(damages != null && damages.length > 0) {
            System.arraycopy(damages, 0, mai.damages, 0, damages.length);
        }
        mai.mobUpDownYRange = mobUpDownYRange;
        mai.animationDeltaL = animationDeltaL;
        if(hitPartRunTimes != null && hitPartRunTimes.length > 0) {
            System.arraycopy(hitPartRunTimes, 0, mai.hitPartRunTimes, 0, hitPartRunTimes.length);
        }
        mai.templateID = templateID;
        mai.idk6 = idk6;
        mai.isResWarriorLiftPress = isResWarriorLiftPress;
        mai.pos1 = pos1 != null ? pos1.deepCopy() : null;
        mai.pos2 = pos2 != null ? pos2.deepCopy() : null;
        mai.rect = rect != null ? rect.deepCopy() : null;
        mai.idkInt = idkInt;
        mai.byteIdk1 = byteIdk1;
        mai.byteIdk2 = byteIdk2;
        mai.byteIdk3 = byteIdk3;
        mai.byteIdk4 = byteIdk4;
        mai.byteIdk5 = byteIdk5;
        mai.psychicLockInfo = psychicLockInfo;
        mai.rocketRushInfo = rocketRushInfo;
        return mai;
    }
}
