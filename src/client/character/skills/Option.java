package client.character.skills;

import loaders.SkillData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 1/3/2018.
 */
public class Option {
    public int nOption;
    public int rOption;
    public int tOption;
    public int xOption;
    public int mOption;
    public int wOption;
    public int uOption;
    public int zOption;
    public int bOption;
    public int sOption;
    public int ssOption;
    public int cOption;
    public int yOption;
    public int nReason;
    public int nValue;
    public int nKey = Integer.MAX_VALUE;
    public int tStart;
    public int tTerm;
    public int pOption;
    public List<Option> extraOpts = new ArrayList<>();

    public Option(int skillID) {
        this.nReason = skillID;
        this.rOption = skillID;
    }

    public Option(int itemID, long duration) {
        // hack to have a constructorfor items
            this.tTerm = (int) duration;
            this.nReason = itemID;
            this.rOption = itemID;
            this.tOption = (int) duration;
    }

    public Option(int skillID, byte slv) {
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        rOption = skillID;
        tOption = si.getValue(SkillStat.time, slv);
    }

    public Option() {
    }

    public Option deepCopy() {
        Option copy = new Option();
        copy.nOption = nOption;
        copy.rOption = rOption;
        copy.tOption = tOption;
        copy.xOption = xOption;
        copy.mOption = mOption;
        copy.wOption = wOption;
        copy.uOption = uOption;
        copy.zOption = zOption;
        copy.bOption = bOption;
        copy.sOption = sOption;
        copy.ssOption = ssOption;
        copy.cOption = cOption;
        copy.pOption = pOption;
        copy.yOption = yOption;
        copy.nReason = nReason;
        copy.nValue = nValue;
        copy.nKey = nKey;
        copy.tStart = tStart;
        copy.tTerm = tTerm;
        extraOpts.forEach(o -> extraOpts.add(o.deepCopy()));
        return copy;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Option &&
                ((((Option) obj).rOption == rOption && ((Option) obj).rOption > 0) ||
                        ((((Option) obj).nReason == nReason && ((Option) obj).nReason > 0)));
    }

    @Override
    public String toString() {
        if (nReason == 0) {
            return "Indie: false, skill: " + rOption + ", val: " + nOption + ", time: " + tOption;
        } else {
            return "Indie: true, skill: " + nReason + ", val: " + nValue + ", time: " + tTerm;
        }
    }
}
