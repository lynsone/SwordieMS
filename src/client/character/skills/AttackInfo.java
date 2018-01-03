package client.character.skills;

import util.Position;

import java.util.ArrayList;
import java.util.List;

public class AttackInfo {
    public boolean fieldKey;
    public byte hits;
    public byte mobCount;
    public int skillId;
    public byte slv;
    public int keyDown;
    public byte idk;
    public boolean left;
    public short attackAction;
    public byte attackActionType;
    public byte idk0;
    public byte attackSpeed;
    public byte reduceCount;
    public int psdTargetPlus;
    public int someId;
    public List<MobAttackInfo> mobAttackInfo = new ArrayList<>();
    public int y;
    public int x;
    public short forcedY;
    public short forcedX;
    public short rcDstRight;
    public short rectRight;
    public int option;
    public int[] idkArr;
    public short forcedYSh;
    public short forcedXSh;
    public boolean force;
    public short delay;
    public short[] shortArr;
    public byte addAttackProc;
    public int grenadeId;
    public byte zero;
    public int bySummonedID;
    public Position ptTarget = new Position();
    public int finalAttackLastSkillID;
    public byte finalAttackByte;
    public boolean ignorePCounter;
    public int spiritCoreEnhance;
    public Position ptAttackRefPoint = new Position();
    public Position idkPos = new Position();
    public Position pos = new Position();
    public byte fh;
    public Position teleportPt = new Position();
    public short Vx;
    public Position grenadePos;
}
