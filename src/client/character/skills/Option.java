package client.character.skills;

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
}
