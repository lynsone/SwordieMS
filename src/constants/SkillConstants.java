package constants;

/**
 * Created on 12/18/2017.
 */
public class SkillConstants {

    public static boolean isSkillNeedMasterLevel(int skillId) {
        if (isIgnoreMasterLevel(skillId)
                || skillId / 1000000 == 92 && (skillId % 10000 == 0)
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
        System.out.println(String.format("Job of %d is %d.", skillId, prefix));
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
}
