package net.swordie.ms.loaders;

import net.swordie.ms.util.container.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MechAviv on 23/11/2018.
 */
public class MakingSkillRecipe {
    private int recipeID;
    private List<TargetElem> target = new ArrayList<>();// rewards
    private int weatherItemID;
    private int incSkillProficiency;//
    private int incSkillProficiencyOnFailure;
    private int incFatigability;
    private int incSkillMasterProficiency;
    private int incSkillMasterProficiencyOnFailure;
    private boolean needOpenItem;
    private int reqSkillID;
    private int recommandedSkillLevel;
    private int reqSkillProficiency;
    private int reqMeso;
    private String reqMapObjectTag = "";
    private List<Tuple<Integer, Integer>> ingredient = new ArrayList<>();// itemid, count
    private int addedCoolProb;
    private int coolTimeSec;
    private int addedSecForMaxGauge;
    private int expiredPeriod;
    private boolean premiumItem;

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public List<TargetElem> getTarget() {
        return target;
    }

    public void setTarget(List<TargetElem> target) {
        this.target = target;
    }

    public void addTarget(TargetElem target) {
        this.target.add(target);
    }

    public int getWeatherItemID() {
        return weatherItemID;
    }

    public void setWeatherItemID(int weatherItemID) {
        this.weatherItemID = weatherItemID;
    }

    public int getIncSkillProficiency() {
        return incSkillProficiency;
    }

    public void setIncSkillProficiency(int incSkillProficiency) {
        this.incSkillProficiency = incSkillProficiency;
    }

    public int getIncSkillProficiencyOnFailure() {
        return incSkillProficiencyOnFailure;
    }

    public void setIncSkillProficiencyOnFailure(int incSkillProficiencyOnFailure) {
        this.incSkillProficiencyOnFailure = incSkillProficiencyOnFailure;
    }

    public int getIncFatigability() {
        return incFatigability;
    }

    public void setIncFatigability(int incFatigability) {
        this.incFatigability = incFatigability;
    }

    public int getIncSkillMasterProficiency() {
        return incSkillMasterProficiency;
    }

    public void setIncSkillMasterProficiency(int incSkillMasterProficiency) {
        this.incSkillMasterProficiency = incSkillMasterProficiency;
    }

    public int getIncSkillMasterProficiencyOnFailure() {
        return incSkillMasterProficiencyOnFailure;
    }

    public void setIncSkillMasterProficiencyOnFailure(int incSkillMasterProficiencyOnFailure) {
        this.incSkillMasterProficiencyOnFailure = incSkillMasterProficiencyOnFailure;
    }

    public boolean isNeedOpenItem() {
        return needOpenItem;
    }

    public void setNeedOpenItem(boolean needOpenItem) {
        this.needOpenItem = needOpenItem;
    }

    public int getReqSkillID() {
        return reqSkillID;
    }

    public void setReqSkillID(int reqSkillID) {
        this.reqSkillID = reqSkillID;
    }

    public int getRecommandedSkillLevel() {
        return recommandedSkillLevel;
    }

    public void setRecommandedSkillLevel(int recommandedSkillLevel) {
        this.recommandedSkillLevel = recommandedSkillLevel;
    }

    public int getReqSkillProficiency() {
        return reqSkillProficiency;
    }

    public void setReqSkillProficiency(int reqSkillProficiency) {
        this.reqSkillProficiency = reqSkillProficiency;
    }

    public int getReqMeso() {
        return reqMeso;
    }

    public void setReqMeso(int reqMeso) {
        this.reqMeso = reqMeso;
    }

    public String getReqMapObjectTag() {
        return reqMapObjectTag;
    }

    public void setReqMapObjectTag(String reqMapObjectTag) {
        this.reqMapObjectTag = reqMapObjectTag;
    }

    public List<Tuple<Integer, Integer>> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Tuple<Integer, Integer>> ingredient) {
        this.ingredient = ingredient;
    }

    public void addIngredient(int itemID, int count) {
        this.ingredient.add(new Tuple<>(itemID, count));
    }

    public int getAddedCoolProb() {
        return addedCoolProb;
    }

    public void setAddedCoolProb(int addedCoolProb) {
        this.addedCoolProb = addedCoolProb;
    }

    public int getCoolTimeSec() {
        return coolTimeSec;
    }

    public void setCoolTimeSec(int coolTimeSec) {
        this.coolTimeSec = coolTimeSec;
    }

    public int getAddedSecForMaxGauge() {
        return addedSecForMaxGauge;
    }

    public void setAddedSecForMaxGauge(int addedSecForMaxGauge) {
        this.addedSecForMaxGauge = addedSecForMaxGauge;
    }

    public int getExpiredPeriod() {
        return expiredPeriod;
    }

    public void setExpiredPeriod(int expiredPeriod) {
        this.expiredPeriod = expiredPeriod;
    }

    public boolean isPremiumItem() {
        return premiumItem;
    }

    public void setPremiumItem(boolean premiumItem) {
        this.premiumItem = premiumItem;
    }

    public static class TargetElem {
        private int itemID;
        private int count;
        private int probWeight;

        public int getItemID() {
            return itemID;
        }

        public void setItemID(int itemID) {
            this.itemID = itemID;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getProbWeight() {
            return probWeight;
        }

        public void setProbWeight(int probWeight) {
            this.probWeight = probWeight;
        }
    }
}
