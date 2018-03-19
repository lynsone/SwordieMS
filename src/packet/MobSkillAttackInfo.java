package packet;

import util.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 3/19/2018.
 */
public class MobSkillAttackInfo {
    public byte actionAndDirMask;
    public byte actionAndDir;
    public int targetInfo;
    public short skillID;
    public List<Position> multiTargetForBalls = new ArrayList<>();
    public List<Short> randTimeForAreaAttacks = new ArrayList<>();
    public Position oldPos;
    public Position oldVPos;
    public int encodedGatherDuration;
}
