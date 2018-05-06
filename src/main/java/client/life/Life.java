package client.life;

import client.character.Char;
import client.field.Field;
import loaders.MobData;
import loaders.NpcData;
import util.Position;
import util.Rect;

public class Life {
    private Position position;
    private int objectId;
    protected int cy, f, fh, templateId, mobTime, rx0, rx1, type, x, y;
    private String lifeType = "";
    private boolean hide;
    private String limitedName = "";
    private boolean useDay;
    private boolean useNight;
    private boolean hold;
    private boolean noFoothold;
    private int regenStart;
    private int mobAliveReq;
    private boolean dummy;
    private boolean spine;
    private boolean mobTimeOnDie;
    private boolean notRespawnable;
    private byte moveAction;
    private Field field;
    private Position homePosition;
    private Position vPosition;

    public Life(int objectId) {
        this.objectId = objectId;
        this.position = new Position(0, 0);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getCy() {
        return cy;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getFh() {
        return fh;
    }

    public void setFh(int fh) {
        this.fh = fh;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateID) {
        this.templateId = templateID;
    }

    public int getMobTime() {
        return mobTime;
    }

    public void setMobTime(int mobTime) {
        this.mobTime = mobTime;
    }

    public int getRx0() {
        return rx0;
    }

    public void setRx0(int rx0) {
        this.rx0 = rx0;
    }

    public int getRx1() {
        return rx1;
    }

    public void setRx1(int rx1) {
        this.rx1 = rx1;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getX() {
        return getPosition().getX();
    }

    public void setX(int x) {
        getPosition().setX(x);
    }

    public int getY() {
        return getPosition().getY();
    }

    public void setY(int y) {
        getPosition().setY(y);
    }

    public String getLifeType() {
        return lifeType;
    }

    public void setLifeType(String lifeType) {
        this.lifeType = lifeType;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public boolean isHide() {
        return hide;
    }

    public void setLimitedName(String limitedName) {
        this.limitedName = limitedName;
    }

    public String getLimitedName() {
        return limitedName;
    }

    public void setUseDay(boolean useDay) {
        this.useDay = useDay;
    }

    public boolean isUseDay() {
        return useDay;
    }

    public void setUseNight(boolean useNight) {
        this.useNight = useNight;
    }

    public boolean isUseNight() {
        return useNight;
    }

    public void setHold(boolean hold) {
        this.hold = hold;
    }

    public boolean isHold() {
        return hold;
    }

    public void setNoFoothold(boolean noFoothold) {
        this.noFoothold = noFoothold;
    }

    public boolean isNoFoothold() {
        return noFoothold;
    }

    public void setRegenStart(int regenStart) {
        this.regenStart = regenStart;
    }

    public int getRegenStart() {
        return regenStart;
    }

    public void setMobAliveReq(int mobAliveReq) {
        this.mobAliveReq = mobAliveReq;
    }

    public int getMobAliveReq() {
        return mobAliveReq;
    }

    public void setDummy(boolean dummy) {
        this.dummy = dummy;
    }

    public boolean isDummy() {
        return dummy;
    }

    public void setSpine(boolean spine) {
        this.spine = spine;
    }

    public boolean isSpine() {
        return spine;
    }

    public void setMobTimeOnDie(boolean mobTimeOnDie) {
        this.mobTimeOnDie = mobTimeOnDie;
    }

    public boolean isMobTimeOnDie() {
        return mobTimeOnDie;
    }

    public boolean isNotRespawnable() {
        return notRespawnable;
    }

    public void setNotRespawnable(boolean notRespawnable) {
        this.notRespawnable = notRespawnable;
    }

    public boolean isLeft() {
        return moveAction > 0 && (moveAction % 2) == 1;
    }

    public Life deepCopy() {
        Life copy = new Life(getObjectId());
        copy.setLifeType(getLifeType());
        copy.setTemplateId(getTemplateId());
        copy.setX(getX());
        copy.setY(getY());
        copy.setMobTime(getMobTime());
        copy.setF(getF());
        copy.setHide(isHide());
        copy.setFh(getFh());
        copy.setCy(getCy());
        copy.setRx0(getRx0());
        copy.setRx1(getRx1());
        copy.setLimitedName(getLimitedName());
        copy.setUseDay(isUseDay());
        copy.setUseNight(isUseNight());
        copy.setHold(isHold());
        copy.setNoFoothold(isNoFoothold());
        copy.setDummy(isDummy());
        copy.setSpine(isSpine());
        copy.setMobTimeOnDie(isMobTimeOnDie());
        copy.setRegenStart(getRegenStart());
        copy.setMobAliveReq(getMobAliveReq());
        return copy;
    }

    public Mob createMobFromLife() {
        Mob mob = null;
        if (getLifeType().equalsIgnoreCase("m")) {
            mob = MobData.getMobDeepCopyById(getTemplateId());
            mob.setObjectId(getObjectId());
            mob.setLifeType(getLifeType());
            mob.setTemplateId(getTemplateId());
            mob.setX(getX());
            mob.setY(getY());
            mob.setHomePosition(new Position(getX(), getY()));
            mob.setPosition(new Position(getX(), getY()));
            mob.setMobTime(getMobTime());
            mob.setF(getF());
            mob.setHide(isHide());
            mob.setFh(getFh());
            mob.setCy(getCy());
            mob.setRx0(getRx0());
            mob.setRx1(getRx1());
            mob.setLimitedName(getLimitedName());
            mob.setUseDay(isUseDay());
            mob.setUseNight(isUseNight());
            mob.setHold(isHold());
            mob.setNoFoothold(isNoFoothold());
            mob.setDummy(isDummy());
            mob.setSpine(isSpine());
            mob.setMobTimeOnDie(isMobTimeOnDie());
            mob.setRegenStart(getRegenStart());
            mob.setMobAliveReq(getMobAliveReq());
        }
        return mob;
    }

    @Override
    public String toString() {
        return String.format("TemplateID: %d, ObjectID: %d, Position:" + getPosition(), getTemplateId(), getObjectId());
    }

    public void setMoveAction(byte moveAction) {
        this.moveAction = moveAction;
    }

    public byte getMoveAction() {
        return moveAction;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Rect getRectAround(Rect rect) {
        int x = getPosition().getX();
        int y = getPosition().getY();
        return new Rect(x + rect.getLeft(), y + rect.getTop(), x + rect.getRight(), y + rect.getBottom());
    }

    public void setCurFoothold() {

    }

    public Npc createNpcFromLife() {
        Npc npc = null;
        if("n".equalsIgnoreCase(getLifeType())) {
            npc = NpcData.getNpcDeepCopyById(getTemplateId());
            npc.setObjectId(getObjectId());
            npc.setLifeType(getLifeType());
            npc.setX(getX());
            npc.setY(getY());
            npc.setPosition(new Position(getX(), getY()));
            npc.setMobTime(getMobTime());
            npc.setF(getF());
            npc.setHide(isHide());
            npc.setFh(getFh());
            npc.setCy(getCy());
            npc.setRx0(getRx0());
            npc.setRx1(getRx1());
            npc.setLimitedName(getLimitedName());
            npc.setUseDay(isUseDay());
            npc.setUseNight(isUseNight());
            npc.setHold(isHold());
            npc.setNoFoothold(isNoFoothold());
            npc.setDummy(isDummy());
            npc.setSpine(isSpine());
            npc.setMobTimeOnDie(isMobTimeOnDie());
            npc.setRegenStart(getRegenStart());
            npc.setMobAliveReq(getMobAliveReq());
        }
        return npc;
    }

    public void setHomePosition(Position homePosition) {
        this.homePosition = homePosition;
    }

    public Position getHomePosition() {
        if(homePosition == null) {
            homePosition = getPosition().deepCopy();
        }
        return homePosition;
    }

    public void broadcastSpawnPacket(Char onlyChar) {
        // Life itself doesn't have a spawn apcket
    }

    public Position getVPosition() {
        return vPosition;
    }

    public void setvPosition(Position vPosition) {
        this.vPosition = vPosition;
    }
}
