package client.field;

import util.Point;

import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on 12/14/2017.
 */
public class Field {
    private Rectangle rect;
    private double mobRate;
    private int id, returnMap, forcedReturn, createMobInterval, timeOut, timeLimit, lvLimit, lvForceMove;
    private int consumeItemCoolTime, link;
    private long uniqueId;
    private boolean town, swim, fly, reactorShuffle, expeditionOnly, partyOnly, needSkillForFly;
    private Set<Portal> portals;
    private Set<Foothold> footholds;
    private String onFirstUserEnter = "", onUserEnter = "";
    private int fixedMobCapacity;

    public Field(int fieldID, long uniqueId) {
        this.id = fieldID;
        this.uniqueId = uniqueId;
        this.rect = new Rectangle(800,600);
        this.portals = new HashSet<>();
        this.footholds = new HashSet<>();
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public int getWidth() {
        return (int) getRect().getWidth();
    }

    public int getHeight() {
        return (int) getRect().getHeight();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Set<Portal> getPortals() {
        return portals;
    }

    public void setPortals(Set<Portal> portals) {
        this.portals = portals;
    }

    public void addPortal(Portal portal) {
        getPortals().add(portal);
    }

    public int getReturnMap() {
        return returnMap;
    }

    public void setReturnMap(int returnMap) {
        this.returnMap = returnMap;
    }

    public int getForcedReturn() {
        return forcedReturn;
    }

    public void setForcedReturn(int forcedReturn) {
        this.forcedReturn = forcedReturn;
    }

    public double getMobRate() {
        return mobRate;
    }

    public void setMobRate(double mobRate) {
        this.mobRate = mobRate;
    }

    public int getCreateMobInterval() {
        return createMobInterval;
    }

    public void setCreateMobInterval(int createMobInterval) {
        this.createMobInterval = createMobInterval;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getLvLimit() {
        return lvLimit;
    }

    public void setLvLimit(int lvLimit) {
        this.lvLimit = lvLimit;
    }

    public int getLvForceMove() {
        return lvForceMove;
    }

    public void setLvForceMove(int lvForceMove) {
        this.lvForceMove = lvForceMove;
    }

    public int getConsumeItemCoolTime() {
        return consumeItemCoolTime;
    }

    public void setConsumeItemCoolTime(int consumeItemCoolTime) {
        this.consumeItemCoolTime = consumeItemCoolTime;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public boolean isTown() {
        return town;
    }

    public void setTown(boolean town) {
        this.town = town;
    }

    public boolean isSwim() {
        return swim;
    }

    public void setSwim(boolean swim) {
        this.swim = swim;
    }

    public boolean isFly() {
        return fly;
    }

    public void setFly(boolean fly) {
        this.fly = fly;
    }

    public boolean isReactorShuffle() {
        return reactorShuffle;
    }

    public void setReactorShuffle(boolean reactorShuffle) {
        this.reactorShuffle = reactorShuffle;
    }

    public boolean isExpeditionOnly() {
        return expeditionOnly;
    }

    public void setExpeditionOnly(boolean expeditionONly) {
        this.expeditionOnly = expeditionONly;
    }

    public boolean isPartyOnly() {
        return partyOnly;
    }

    public void setPartyOnly(boolean partyOnly) {
        this.partyOnly = partyOnly;
    }

    public boolean isNeedSkillForFly() {
        return needSkillForFly;
    }

    public void setNeedSkillForFly(boolean needSkillForFly) {
        this.needSkillForFly = needSkillForFly;
    }

    public String getOnFirstUserEnter() {
        return onFirstUserEnter;
    }

    public void setOnFirstUserEnter(String onFirstUserEnter) {
        this.onFirstUserEnter = onFirstUserEnter;
    }

    public String getOnUserEnter() {
        return onUserEnter;
    }

    public void setOnUserEnter(String onUserEnter) {
        this.onUserEnter = onUserEnter;
    }

    public Portal getPortalByName(String name) {
        return getPortals().stream().filter(portal -> portal.getName().equals(name)).findAny().orElse(null);
    }

    public Foothold findFootHoldBelow(Point pos) {
        Set<Foothold> footholds = getFootholds().stream().filter(fh -> fh.getX1() <= pos.getX() && fh.getX2() >= pos.getX()).collect(Collectors.toSet());
        Foothold res = null;
        int lastY = Integer.MIN_VALUE;
        for(Foothold fh : footholds) {
            if(res == null) {
                res = fh;
            } else {
                // interpolate between the two foothold ends for the y value below pos.x
                int x1 = fh.getX1();
                int x2 = fh.getX2() - x1;
                int x = pos.getX() - x1;
                double perc = (double) x / (double) x2;
                System.out.println("Percentage: " + perc);
                int y = (int) (fh.getY1() + (perc * (fh.getY1() - fh.getY2())));
                if(y > lastY && y <= pos.getY()) {
                    res = fh;
                    lastY = y;
                }
            }
        }
        return res;
    }

    public Set<Foothold> getFootholds() {
        return footholds;
    }

    public void setFootholds(Set<Foothold> footholds) {
        this.footholds = footholds;
    }

    public void addFoothold(Foothold foothold) {
        getFootholds().add(foothold);
    }

    public void setFixedMobCapacity(int fixedMobCapacity) {
        this.fixedMobCapacity = fixedMobCapacity;
    }

    public int getFixedMobCapacity() {
        return fixedMobCapacity;
    }
}
