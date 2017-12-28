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

    private static boolean isAddedSpDualAndZeroSkill(int skillId) {
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

    private static int getSkillRootFromSkill(int skillId) {
        int prefix = skillId / 10000;
        if (prefix == 8000) {
            prefix = skillId / 100;
        }
        return prefix / 100;
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
        return skillId == 2321001 || skillId == 80001836;
    }

    public static boolean isEvanForceSkill(int skillId) {
        return skillId == 22110022 || skillId == 22110023 || skillId == 22141011 || skillId == 22140022 ||
                skillId == 22171062 || skillId == 80001849;
    }
}
