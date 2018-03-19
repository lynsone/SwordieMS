package constants;

/**
 * Created on 12/18/2017.
 */
public class SkillConstants {

    public static boolean isSkillNeedMasterLevel(int skillId) {
        if (isIgnoreMasterLevel(skillId)
                || (skillId / 1000000 == 92 && (skillId % 10000 == 0))
                || isMakingSkillRecipe(skillId)
                || isCommonSkill(skillId)
                || isNoviceSkill(skillId)
                || isFieldAttackObjSkill(skillId)) {
            return false;
        }
        int job = getSkillRootFromSkill(skillId);
        return isAddedSpDualAndZeroSkill(skillId) || JobConstants.getJobLevel((short) job) == 4 && !JobConstants.isZero((short) job);
    }

    public static boolean isAddedSpDualAndZeroSkill(int skillId) {
        if (skillId > 101100101) {
            if (skillId > 101110203) {
                if (skillId == 101120104)
                    return true;
                return skillId == 101120204;
            } else {
                if (skillId == 101110203 || skillId == 101100201 || skillId == 101110102)
                    return true;
                return skillId == 101110200;
            }
        } else {
            if (skillId == 101100101)
                return true;
            if (skillId > 4331002) {
                if (skillId == 4340007 || skillId == 4341004)
                    return true;
                return skillId == 101000101;
            } else {
                if (skillId == 4331002 || skillId == 4311003 || skillId == 4321006)
                    return true;
                return skillId == 4330009;
            }
        }
    }

    public static int getSkillRootFromSkill(int skillId) {
        int prefix = skillId / 10000;
        if (prefix == 8000) {
            prefix = skillId / 100;
        }
        return prefix;
    }

    private static boolean isFieldAttackObjSkill(int skillId) {
        int v1; // eax

        if (skillId <= 0)
            return false;
        v1 = skillId / 10000;
        if (skillId / 10000 == 8000)
            v1 = skillId / 100;
        return v1 == 9500;
    }

    private static boolean isNoviceSkill(int skillId) {
        int prefix; // eax

        prefix = skillId / 10000;
        if (skillId / 10000 == 8000)
            prefix = skillId / 100;
        return JobConstants.isBeginnerJob((short) prefix);
    }

    private static boolean isCommonSkill(int skillId) {
        int prefix; // eax
        prefix = skillId / 10000;
        if (skillId / 10000 == 8000)
            prefix = skillId / 100;
        return prefix >= 800000 && prefix <= 800099;
    }

    private static boolean isMakingSkillRecipe(int recipeId) {
        boolean result = false;
        if (recipeId / 1000000 != 92 || recipeId % 10000 == 1) {
            int v1 = 10000 * (recipeId / 10000);
            if (v1 / 1000000 == 92 && (v1 % 10000 == 0))
                result = true;
        }
        return result;
    }

    public static boolean isIgnoreMasterLevel(int skillId) {
        // is_ignore_master_level(int skillId)
        if (skillId > 5321004) {
            if (skillId > 23120011) {
                if (skillId <= 35054478) {
                    return skillId == 35054478 || skillId == 23120013 || skillId == 23121008;
                }
                if (skillId != 51120000) {
                    return skillId == 80001913;
                }
            } else if (skillId != 23120011) {
                if (skillId <= 21120021) {
                    return skillId >= 21120020 || skillId == 5321006 || (skillId - 5321006 == 15799005) ||
                            (skillId - 5321006 - 15799005 == 3);
                }
                if (skillId != 21121008) {
                    return skillId == 22171069;
                }
            }
            return true;
        }
        if (skillId == 5321004)
            return true;
        if (skillId > 4210012) {
            if (skillId > 5220012) {
                if (skillId != 5220014) {
                    return skillId == 5320007;
                }
            } else if (skillId != 5220012) {
                if (skillId > 4340012) {
                    if (skillId < 5120011 || skillId > 5120012)
                        return false;
                } else if (skillId != 4340012) {
                    return skillId == 4340010;
                }
            }
            return true;
        }
        if (skillId == 4210012)
            return true;
        if (skillId > 2221009) {
            if (skillId == 2321010 || skillId == 3210015)
                return true;
            return skillId == 4110012;
        } else {
            if (skillId == 2221009 || skillId == 1120012 || skillId == 1320011)
                return true;
            return skillId == 2121009;
        }
    }

    public static boolean isKeyDownSkill(int skillId) {
        return skillId == 2321001 || skillId == 80001836 || skillId == 37121052 || skillId == 36121000 ||
                skillId == 37121003 || skillId == 36101001 || skillId == 33121114 || skillId == 33121214 ||
                skillId == 35121015 || skillId == 33121009 || skillId == 32121003 || skillId == 31211001 ||
                skillId == 31111005 || skillId == 30021238 || skillId == 31001000 || skillId == 31101000 ||
                skillId == 80001887 || skillId == 80001836 || skillId == 80001880 || skillId == 80001629 ||
                skillId == 60011216 || skillId == 65121003 || skillId == 80001587 || skillId == 131001008 ||
                skillId == 142111010 || skillId == 131001004 || skillId == 95001001 || skillId == 101110100 ||
                skillId == 101110101 || skillId == 101110102 || skillId == 27111100 || skillId == 12121054 ||
                skillId == 11121052 || skillId == 11121055 || skillId == 5311002 || skillId == 4341002 ||
                skillId == 5221004 || skillId == 5221022 || skillId == 3121020 || skillId == 3101008 ||
                skillId == 3111013 || skillId == 2321001 || skillId == 1311011 || skillId == 2221011 ||
                skillId == 2221052 || skillId == 22171083 || skillId == 25111005 || skillId == 25121030 ||
                skillId == 27101202 || skillId == 25111005 || skillId == 23121000 || skillId == 22171083 ||
                skillId == 14121004 || skillId == 13111020 || skillId == 13121001 || skillId == 14111006 ||
                skillId == 20041226 || (skillId >= 80001389 && skillId <= 80001392);

    }

    public static boolean isEvanForceSkill(int skillId) {
        return skillId == 22110022 || skillId == 22110023 || skillId == 22141011 || skillId == 22140022 ||
                skillId == 22171062 || skillId == 80001849;
    }

    public static boolean isSuperNovaSkill(int skillID) {
        return skillID == 4221052 || skillID == 65121052;
    }

    public static boolean isRushBombSkill(int skillID) {
        return skillID == 27121201 || skillID == 101120205 || skillID == 101120200 || skillID == 101120203 ||
                skillID == 61111218 || skillID == 27121201 || skillID == 14111022 || skillID == 22140015 ||
                skillID == 22140024 || skillID == 12121001 || skillID == 5101014 || skillID == 2221012 ||
                skillID == 5101012;
    }

    public static boolean isZeroSkill(int skillID) {
        int prefix = skillID / 10000;
        if(prefix == 8000) {
            prefix = skillID / 100;
        }
        return prefix == 10000 || prefix == 10100 || prefix == 10110 || prefix == 10111 || prefix == 10112;
    }

    public static boolean isUsercloneSummonedAbleSkill(int skillID) {
        return skillID == 14111020 || skillID == 14101019 || (skillID >= 14101019 && skillID <= 14101021) ||
                     skillID == 14120045 || (skillID >= 14121000 && skillID == 14121002);
    }

    public static boolean isNoconsumeUsebulletMeleeAttack(int skillID) {
        return skillID == 14121052 || skillID == 14121003 || skillID == 14000028 || skillID == 14000029;
    }

    public static boolean isScreenCenterAttackSkill(int skillID) {
        return skillID == 80001431 || skillID == 100001283 || skillID == 21121057 || skillID == 13121052 ||
                skillID == 14121052 || skillID == 15121052;
    }

    public static boolean isAranFallingStopSkill(int skillID) {
        switch(skillID) {
            case 21110028:
            case 21120025:
            case 21110026:
            case 21001010:
            case 21000006:
            case 21000007:
            case 21110022:
            case 21110023:
            case 80001925:
            case 80001926:
            case 80001927:
            case 80001936:
            case 80001937:
            case 80001938:
                return true;
            default:
                return false;
        }
    }

    public static boolean isFlipAffectAreaSkill(int skillID) {
        return skillID == 33111013 || skillID == 33121016 || skillID == 33121012 || skillID == 131001207 ||
                skillID == 131001107 || skillID == 4121015 || skillID == 51120057;
    }

    public static boolean isShootSkillNotConsumingBullets(int skillID) {
        int job = skillID / 10000;
        if (skillID / 10000 == 8000) {
            job = skillID / 100;
        }
        switch (skillID) {
            case 80001279:
            case 80001914:
            case 80001915:
            case 80001880:
            case 80001629:
            case 33121052:
            case 33101002:
            case 14101006:
            case 13101020:
            case 1078:
                return true;
            default:
                return getDummyBulletItemIDForJob(job, 0, 0) > 0
                        || isShootSkillNotUsingShootingWeapon(skillID, false)
                        || isFieldAttackObjSkill(skillID);

        }
    }

    private static boolean isShootSkillNotUsingShootingWeapon(int skillID, boolean bySteal) {
        if(bySteal || (skillID >= 80001848 && skillID <= 80001850)) {
            return true;
        }
        switch (skillID) {
            case 80001863:
            case 80001880:
            case 80001914:
            case 80001915:
            case 80001939:
            case 101110204:
            case 101110201:
            case 101000202:
            case 101100202:
            case 80001858:
            case 80001629:
            case 80001829:
            case 80001838:
            case 80001856:
            case 80001587:
            case 80001418:
            case 80001387:
            case 61111215:
            case 80001279:
            case 61001101:
            case 51121008:
            case 51111007:
            case 36121001:
            case 51001004:
            case 36111010:
            case 36101009:
            case 31111005:
            case 31111006: // ? was 26803624, guessing it's just a +1
            case 31101000:
            case 22110024:
            case 22110014:
            case 21120006:
            case 21100007:
            case 21110027:
            case 21001009:
            case 21000004:
            case 5121013:
            case 1078:
            case 1079:
                return true;
            default:
                return false;

        }
    }

    private static int getDummyBulletItemIDForJob(int job, int subJob, int skillID) {
        if ( job / 100 == 35 )
            return 2333000;
        if ( job / 10 == 53 || job == 501 || (job / 1000) == 0 && subJob == 2 )
            return 2333001;
        if ( JobConstants.isMercedes((short) job) )
            return 2061010;
        if ( JobConstants.isAngelicBuster(job) )
            return 2333001;
        // TODO:
//        if ( !JobConstants.isPhantom((short) job)
//                || !is_useable_stealedskill(skillID)
//                || (result = get_vari_dummy_bullet_by_cane(skillID), result <= 0) )
//        {
//            result = 0;
//        }
        return 0;
    }

    public static boolean isKeydownSkillRectMoveXY(int skillID) {
        return skillID == 13111020;
    }

    public static int getOriginalOfLinkedSkill(int skillID) {
        int result = 0;
        switch(skillID) {
            case 80001040:
                result = 20021110;
                break;
            case 80001140:
                result = 50001214;
                break;
            case 80001155:
                result = 60011219;
                break;
            case 80000378:
                result = 30000077;
                break;
            case 80000334:
                result = 30000075;
                break;
            case 80000335:
                result = 30000076;
                break;
            case 80000369:
                result = 20010294;
                break;
            case 80000370:
                result = 20000297;
                break;
            case 80000333:
                result = 30000074;
                break;
            case 80000000:
                result = 110;
                break;
            case 80000001:
//                result = &_unwindfunclet___1CField_PvP__UAE_XZ_13;
                break;
            case 80000002:
                result = 20030204;
                break;
            case 80000005:
                result = 20040218;
                break;
            case 80000006:
                result = 60000222;
                break;
            case 80000047:
//                result = &_unwindfunclet__ShowOneTimeEffect_CField_RhythmGame__AAEXV__ZXString_G___Z_8 + 1;
                break;
            case 80000050:
                result = 30010241;
                break;
            case 80000066:
                result = 10000255;
                break;
            case 80000067:
                result = 10000256;
                break;
            case 80000068:
                result = 10000257;
                break;
            case 80000069:
                result = 10000258;
                break;
            case 80000070:
                result = 10000259;
                break;
            case 80000110:
                result = 100000271;
                break;
            case 80000169:
                result = 20050286;
                break;
            case 80000188:
                result = 140000292;
                break;
        }
        return result;
    }

    public static boolean isZeroAlphaSkill(int skillID) {
        return isZeroSkill(skillID) && skillID % 1000 / 100 == 2;
    }

    public static boolean isZeroBetaSkill(int skillID) {
        return isZeroSkill(skillID) && skillID % 1000 / 100 == 1;
    }

    public static boolean isLightmageSkill(int skillID) {
        int prefix = skillID / 10000;
        if(prefix == 8000) {
            prefix = skillID / 100;
        }
        return prefix / 100 == 27 || prefix == 2004;
    }

    public static boolean isLarknessDarkSkill(int skillID) {
        return skillID != 20041222 && isLightmageSkill(skillID) && skillID / 100 % 10 == 2;
    }

    public static boolean isLarknessLightSkill(int skillID) {
        return skillID != 20041222 && isLightmageSkill(skillID) && skillID / 100 % 10 == 1;
    }

    public static boolean isEquilibriumSkill(int skillID) {
        return skillID >= 20040219 && skillID <= 20040220;
    }

    public static int getAdvancedCountHyperSkill(int skillId) {
        switch(skillId) {
            case 4121013:
                return 4120051;
            case 5321012:
                return 5320051;
            default:
                return 0;
        }
    }

    public static int getAdvancedAttackCountHyperSkill(int skillId) {
        switch(skillId) {
            case 25121005:
                return 25120148;
            case 31121001:
                return 31120050;
            case 31111005:
                return 31120044;
            case 22140023:
                return 22170086;
            case 21120022:
            case 21121015:
            case 21121016:
            case 21121017:
                return 21120066;
            case 21120006:
                return 21120049;
            case 21110020:
            case 21111021:
                return 21120047;
            case 15121002:
                return 15120048;
            case 14121002:
                return 14120045;
            case 15111022:
            case 15120003:
                return 15120045;
            case 51121008:
                return 51120048;
            case 32111003:
                return 32120047;
            case 35121016:
                return 35120051;
            case 37110002:
                return 37120045;
            case 51120057:
                return 51120058;
            case 51121007:
                return 51120051;
            case 65121007:
            case 65121008:
            case 65121101:
                return 65120051;
            case 61121201:
            case 61121100:
                return 61120045;
            case 51121009:
                return 51120058;
            case 13121002:
                return 13120048;
            case 5121016:
            case 5121017:
                return 5120051;
            case 3121015:
                return 3120048;
            case 2121006:
                return 2120048;
            case 2221006:
                return 2220048;
            case 1221011:
                return 1220050;
            case 1120017:
            case 1121008:
                return 1120051;
            case 1221009:
                return 1220048;
            case 4331000:
                return 4340045;
            case 3121020:
                return 3120051;
            case 3221017:
                return 3220048;
            case 4221007:
                return 4220048;
            case 4341009:
                return 4340048;
            case 5121007:
                return 5120048;
            case 5321004:
                return 5320043;
            // if ( nSkillID != &loc_A9B1CF ) nothing done with line 172?
            case 12110028:
            case 12000026:
            case 12100028:
                return 12120045;
            case 12120010:
                return 12120045;
            case 12120011:
                return 12120046;
            default:
                return 0;
        }
    }

    public static boolean isKinesisPsychicLockSkill(int skillId) {
        switch(skillId) {
            case 142120000:
            case 142120001:
            case 142120002:
            case 142120014:
            case 142111002:
            case 142100010:
            case 142110003:
            case 142110015:
                return true;
            default:
                return false;
        }
    }
}
