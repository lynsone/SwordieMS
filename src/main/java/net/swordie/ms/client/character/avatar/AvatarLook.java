package net.swordie.ms.client.character.avatar;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.JobConstants;

import javax.persistence.*;
import java.util.*;

/**
 * Created on 2/18/2017.
 */
@Entity
@Table(name = "avatarlook")
public class AvatarLook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int gender;
    private int skin;
    private int face;
    private int hair;
    private int weaponStickerId;

    private int weaponId;
    private int subWeaponId;
    @ElementCollection
    @CollectionTable(name = "hairEquips", joinColumns = @JoinColumn(name = "alId"))
    @Column(name = "equipId")
    private List<Integer> hairEquips;

    @ElementCollection
    @CollectionTable(name = "unseenEquips", joinColumns = @JoinColumn(name = "alId"))
    @Column(name = "equipId")
    private List<Integer> unseenEquips;
    @ElementCollection
    @CollectionTable(name = "petIDs", joinColumns = @JoinColumn(name = "alId"))
    @Column(name = "petId")
    private List<Integer> petIDs;
    private int job;
    private boolean drawElfEar;
    private int demonSlayerDefFaceAcc;
    private int xenonDefFaceAcc;
    private int beastTamerDefFaceAcc;
    private boolean isZeroBetaLook;
    private int mixedHairColor;
    private int mixHairPercent;
    @ElementCollection
    @CollectionTable(name = "totems", joinColumns = @JoinColumn(name = "alId"))
    @Column(name = "totemId")
    private List<Integer> totems;
    private int ears;
    private int tail;
    @Transient
    private int demonWingID;
    @Transient
    private int kaiserWingID;
    @Transient
    private int kaiserTailID;

    public AvatarLook() {
        hairEquips = new ArrayList<>();
        unseenEquips = new ArrayList<>();
        petIDs = Arrays.asList(0, 0, 0);
        totems = new ArrayList<>();
    }

    public AvatarLook deepCopy() {
        AvatarLook res = new AvatarLook();
        res.setGender(getGender());
        res.setSkin(getSkin());
        res.setFace(getFace());
        res.setHair(getHair());
        res.setWeaponStickerId(getWeaponStickerId());
        res.setWeaponId(getWeaponId());
        res.setSubWeaponId(getSubWeaponId());
        List<Integer> resHairEquips = new ArrayList<>(getHairEquips());
        res.setHairEquips(resHairEquips);
        List<Integer> resUnseenEquips = new ArrayList<>(getUnseenEquips());
        res.setUnseenEquips(resUnseenEquips);
        List<Integer> resPetIDs = new ArrayList<>(getPetIDs());
        res.setUnseenEquips(resPetIDs);
        res.setJob(getJob());
        res.setDrawElfEar(isDrawElfEar());
        res.setDemonSlayerDefFaceAcc(getDemonSlayerDefFaceAcc());
        res.setXenonDefFaceAcc(getXenonDefFaceAcc());
        res.setBeastTamerDefFaceAcc(getBeastTamerDefFaceAcc());
        res.setZeroBetaLook(isZeroBetaLook());
        res.setMixedHairColor(getMixedHairColor());
        res.setMixHairPercent(getMixHairPercent());
        List<Integer> resTotems = new ArrayList<>(getTotems());
        res.setTotems(resTotems);
        res.setEars(getEars());
        res.setTail(getTail());
        return res;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
    public int getSkin() {
        return skin;
    }

    public void setSkin(int skin) {
        this.skin = skin;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public int getWeaponStickerId() {
        return weaponStickerId;
    }

    public void setWeaponStickerId(int weaponStickerId) {
        this.weaponStickerId = weaponStickerId;
    }

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    public int getSubWeaponId() {
        return subWeaponId;
    }

    public void setSubWeaponId(int subWeaponId) {
        this.subWeaponId = subWeaponId;
    }

    public List<Integer> getHairEquips() {
        return hairEquips;
    }

    public void setHairEquips(List<Integer> hairEquips) {
        this.hairEquips = hairEquips;
    }

    public List<Integer> getUnseenEquips() {
        return unseenEquips;
    }

    public void setUnseenEquips(List<Integer> unseenEquips) {
        this.unseenEquips = unseenEquips;
    }

    public List<Integer> getPetIDs() {
        return petIDs;
    }

    public void setPetIDs(List<Integer> petIDs) {
        this.petIDs = petIDs;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public boolean isDrawElfEar() {
        return drawElfEar;
    }

    public void setDrawElfEar(boolean drawElfEar) {
        this.drawElfEar = drawElfEar;
    }

    public int getDemonSlayerDefFaceAcc() {
        return demonSlayerDefFaceAcc;
    }

    public void setDemonSlayerDefFaceAcc(int demonSlayerDefFaceAcc) {
        this.demonSlayerDefFaceAcc = demonSlayerDefFaceAcc;
    }

    public int getXenonDefFaceAcc() {
        return xenonDefFaceAcc;
    }

    public void setXenonDefFaceAcc(int xenonDefFaceAcc) {
        this.xenonDefFaceAcc = xenonDefFaceAcc;
    }

    public int getBeastTamerDefFaceAcc() {
        return beastTamerDefFaceAcc;
    }

    public void setBeastTamerDefFaceAcc(int beastTamerDefFaceAcc) {
        this.beastTamerDefFaceAcc = beastTamerDefFaceAcc;
    }

    public boolean isZeroBetaLook() {
        return isZeroBetaLook;
    }

    public void setZeroBetaLook(boolean zeroBetaLook) {
        isZeroBetaLook = zeroBetaLook;
    }

    public int getMixedHairColor() {
        return mixedHairColor;
    }

    public void setMixedHairColor(int mixedHairColor) {
        this.mixedHairColor = mixedHairColor;
    }

    public int getMixHairPercent() {
        return mixHairPercent;
    }

    public void setMixHairPercent(int mixHairPercent) {
        this.mixHairPercent = mixHairPercent;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getGender());
        outPacket.encodeByte(getSkin());
        outPacket.encodeInt(getFace());
        outPacket.encodeInt(getJob());
        outPacket.encodeByte(isZeroBetaLook()); // ?
        outPacket.encodeInt(getHair());

        for (int i = 1; i < getHairEquips().size(); i++) {
            int itemId = getHairEquips().get(i);
            outPacket.encodeByte(ItemConstants.getBodyPartFromItem(itemId, getGender())); // body part
            outPacket.encodeInt(itemId); // item id
        }
        outPacket.encodeByte(-1);
        for (int i = 0; i < getUnseenEquips().size(); i++) {
            int itemId = getUnseenEquips().get(i);
            outPacket.encodeByte(ItemConstants.getBodyPartFromItem(itemId, getGender())); // body part
            outPacket.encodeInt(itemId);
        }
        outPacket.encodeByte(-1);
        for (int i = 0; i < getTotems().size(); i++) {
            int itemId = getTotems().get(i);
            outPacket.encodeByte(ItemConstants.getBodyPartFromItem(itemId, getGender()));
            outPacket.encodeInt(itemId);
        }
        outPacket.encodeByte(-1); //original, testing stuff
        outPacket.encodeInt(getWeaponStickerId());
        outPacket.encodeInt(getWeaponId());
        outPacket.encodeInt(getSubWeaponId());
        outPacket.encodeByte(isDrawElfEar());
        // outPacket.encodeByte(-1); //smth to do with a new class
        for (int i = 0; i < 3; i++) {
            outPacket.encodeInt(getPetIDs().get(i)); // always 3
        }
        if (JobConstants.isZero((short) getJob())) {
             outPacket.encodeByte(isZeroBetaLook());
        }
        if (JobConstants.isXenon((short) getJob())) {
            outPacket.encodeInt(getXenonDefFaceAcc());
        }
        if (JobConstants.isDemon((short) getJob())) {
            outPacket.encodeInt(getDemonSlayerDefFaceAcc());
        }
        if (JobConstants.isBeastTamer((short) getJob())) {
            boolean hasEars = getEars() > 0;
            boolean hasTail = getTail() > 0;
            outPacket.encodeInt(getBeastTamerDefFaceAcc());
            outPacket.encodeByte(hasEars);
            outPacket.encodeInt(getEars());
            outPacket.encodeByte(hasTail);
            outPacket.encodeInt(getTail());
        }
        outPacket.encodeByte(getMixedHairColor());
        outPacket.encodeByte(getMixHairPercent());
    }

    public int getHair() {
        return hair;
    }

    public void setHair(int hair) {
        this.hair = hair;
    }

    public List<Integer> getTotems() {
        return totems;
    }

    public int getEars() {
        return ears;
    }

    public void setEars(int ears) {
        this.ears = ears;
    }

    public int getTail() {
        return tail;
    }

    public void setTail(short tail) {
        this.tail = tail;
    }

    public void setTotems(List<Integer> totems) {
        this.totems = totems;
    }

    public void setTail(int tail) {
        this.tail = tail;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDemonWingID() {
        return demonWingID;
    }

    public void setDemonWingID(int demonWingID) {
        this.demonWingID = demonWingID;
    }

    public int getKaiserWingID() {
        return kaiserWingID;
    }

    public void setKaiserWingID(int kaiserWingID) {
        this.kaiserWingID = kaiserWingID;
    }

    public int getKaiserTailID() {
        return kaiserTailID;
    }

    public void setKaiserTailID(int kaiserTailID) {
        this.kaiserTailID = kaiserTailID;
    }
}