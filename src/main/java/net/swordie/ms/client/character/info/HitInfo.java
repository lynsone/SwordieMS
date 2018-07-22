package net.swordie.ms.client.character.info;

/**
 * Created on 1/11/2018.
 */
public class HitInfo {
    public int HPDamage;
    public int templateID;
    public int mobID;
    public int MPDamage;
    public int type = -1;
    public int skillID;
    public int otherUserID;
    public boolean isCrit;
    public int action;
    public boolean isPowerGuard;
    public int hitAction;
    public int specialEffectSkill; // mask: 0x1 if true, 0x2 if custom skillID. Default is 33110000 (jaguar boost)
    public int curStanceSkill;
}
