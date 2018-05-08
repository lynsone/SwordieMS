package net.swordie.ms.enums;

import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created on 5/4/2018.
 */
public enum BaseStat {
    unk,
    str,
    strR,
    dex,
    dexR,
    inte,
    intR,
    luk,
    lukR,
    pad,
    padR,
    mad,
    madR,
    pdd,
    pddR,
    mdd,
    mddR,
    mhp,
    mhpR,
    mmp,
    mmpR,
    cr, // Crit rate
    minCd, // Min crit damage
    maxCd, // Max crit damage
    fd, // Final damage
    bd, // Boss damage
    ied, // Ignore enemy defense
    asr, // All status resistance
    ter, // Status time minus
    acc,
    accR,
    eva,
    evaR,
    jump,
    speed,
    expR,
    dropR,
    booster,
    stance,
    mastery
    ;

    private static Map<CharacterTemporaryStat, Set<BaseStat>> ctsToBaseStat = new HashMap<>();

    public static BaseStat getFromStat(Stat s) {
        switch (s) {
            case str:
                return str;
            case dex:
                return dex;
            case inte:
                return inte;
            case luk:
                return luk;
            case mhp:
                return mhp;
            case mmp:
                return mmp;
            default:
                return unk;
        }
    }

    public static Set<BaseStat> getFromCTS(CharacterTemporaryStat ctsArg) {
        if (ctsToBaseStat.size() == 0) {
            // init
            for (CharacterTemporaryStat cts : CharacterTemporaryStat.values()) {
                Set<BaseStat> stats = new HashSet<>();
                // TODO: Left at "Holy Symbol" in CTS
                switch (cts) {
                    case IndiePAD:
                    case PAD:
                        stats.add(pad);
                        break;
                    case IndieMAD:
                    case MAD:
                        stats.add(mad);
                        break;
                    case IndiePDD:
                    case PDD:
                        stats.add(pdd);
                        break;
                    case IndieMDD:
                    case MDD:
                        stats.add(mdd);
                        break;
                    case IndiePADR:
                        stats.add(padR);
                        break;
                    case IndieMADR:
                        stats.add(madR);
                        break;
                    case IndiePDDR:
                        stats.add(pddR);
                        break;
                    case IndieMDDR:
                        stats.add(mddR);
                        break;
                    case IndieMHP:
                        stats.add(mhp);
                        break;
                    case IndieMHPR:
                    case MaxHP:
                    case IncMaxHP:
                        stats.add(mhpR);
                        break;
                    case IndieMMP:
                    case MaxMP:
                    case IncMaxMP:
                        stats.add(mmp);
                        break;
                    case IndieMMPR:
                        stats.add(mmpR);
                        break;
                    case IndieACC:
                    case ACC:
                        stats.add(acc);
                        break;
                    case ACCR:
                        stats.add(accR);
                        break;
                    case IndieEVA:
                    case EVA:
                        stats.add(eva);
                        break;
                    case IndieEVAR:
                    case EVAR:
                        stats.add(evaR);
                        break;
                    case Speed:
                    case IndieSpeed:
                        stats.add(speed);
                        break;
                    case IndieJump:
                    case Jump:
                        stats.add(jump);
                        break;
                    case IndieAllStat:
                        stats.add(str);
                        stats.add(dex);
                        stats.add(inte);
                        stats.add(luk);
                        break;
                    case IndieDodgeCriticalTime:
                    case IndieCr:
                        stats.add(cr);
                        break;
                    case IndieCrMax:
                    case IndieCrMaxR:
                        stats.add(maxCd);
                        break;
                    case IndieEXP:
                    case IndieRelaxEXP:
                    case HolySymbol:
                        stats.add(expR);
                        break;
                    case IndieBooster:
                    case Booster:
                    case PartyBooster:
                    case HayatoBooster:
                        stats.add(booster);
                        break;
                    case STR:
                    case ZeroAuraStr:
                    case IndieSTR:
                        stats.add(str);
                        break;
                    case IndieDEX:
                        stats.add(dex);
                        break;
                    case IndieINT:
                        stats.add(inte);
                        break;
                    case IndieLUK:
                        stats.add(luk);
                        break;
                    case IndieStatR:
                        stats.add(strR);
                        stats.add(dexR);
                        stats.add(intR);
                        stats.add(lukR);
                        break;
                    case IndieDamR:
                    case DamR:
                        stats.add(fd);
                        break;
                    case IndieAsrR:
                        stats.add(asr);
                        break;
                    case IndieTerR:
                        stats.add(ter);
                        break;
                    case IndieBDR:
                        stats.add(bd);
                        break;
                    case IndieStance:
                        stats.add(stance);
                        break;
                    case IndieIgnoreMobpdpR:
                        stats.add(ied);
                        break;
                    default:
                        stats.add(unk);
                }
                ctsToBaseStat.put(cts, stats);
            }
        }
        return ctsToBaseStat.getOrDefault(ctsArg, new HashSet<>());
    }

    public Stat toStat() {
        switch(this) {
            case str:
                return Stat.str;
            case dex:
                return Stat.dex;
            case inte:
                return Stat.inte;
            case luk:
                return Stat.luk;
            case mhp:
                return Stat.mhp;
            case mmp:
                return Stat.mmp;
            default:
                return null;
        }
    }
}
