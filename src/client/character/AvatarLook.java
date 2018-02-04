package client.character;

import connection.OutPacket;
import constants.ItemConstants;
import constants.JobConstants;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    @Column(name = "id")
    private int id;
    @Column(name = "gender")
    private int gender;
    @Column(name = "secondGender")
    private int secondGender;
    @Column(name = "skin")
    private int skin;
    @Column(name = "secondSkin")
    private int secondSkin;
    @Column(name = "face")
    private int face;
    @Column(name = "secondFace")
    private int secondFace;
    @Column(name = "hair")
    private int hair;
    @Column(name = "secondHair")
    private int secondHair;
    @Column(name = "weaponStickerId")
    private int weaponStickerId;

    @Column(name = "weaponId")
    private int weaponId;
    @Column(name = "subWeaponId")
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
    @Column(name = "job")
    private int job;
    @Column(name = "drawElfEar")
    private boolean drawElfEar;
    @Column(name = "demonSlayerDefFaceAcc")
    private int demonSlayerDefFaceAcc;
    @Column(name = "xenonDefFaceAcc")
    private int xenonDefFaceAcc;
    @Column(name = "beastTamerDefFaceAcc")
    private int beastTamerDefFaceAcc;
    @Column(name = "isZeroBetaLook")
    private boolean isZeroBetaLook;
    @Column(name = "mixedHairColor")
    private int mixedHairColor;
    @Column(name = "mixHairPercent")
    private int mixHairPercent;
    @ElementCollection
    @CollectionTable(name = "totems", joinColumns = @JoinColumn(name = "alId"))
    @Column(name = "totemId")
    private List<Integer> totems;
    @Column(name = "ears")
    private int ears;
    @Column(name = "tail")
    private int tail;

    public AvatarLook() {
        hairEquips = new ArrayList<>();
        unseenEquips = new ArrayList<>();
        petIDs = Arrays.asList(0, 0, 0);
        totems = new ArrayList<>();
    }


    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getSecondGender() {
        return secondGender;
    }

    public void setSecondGender(int secondGender) {
        this.secondGender = secondGender;
    }

    public int getSkin() {
        return skin;
    }

    public void setSkin(int skin) {
        this.skin = skin;
    }

    public int getSecondSkin() {
        return secondSkin;
    }

    public void setSecondSkin(int secondSkin) {
        this.secondSkin = secondSkin;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public int getSecondFace() {
        return secondFace;
    }

    public void setSecondFace(int secondFace) {
        this.secondFace = secondFace;
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

    public int getBeastTamerFaceAcc() {
        return beastTamerDefFaceAcc;
    }

    public void setBeastTamerDefFaceAcc() {
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

    public void encode(OutPacket outPacket, boolean mega, boolean isZeroBetaLook) {
      //  outPacket.encodeByte(getGender());

        outPacket.encodeByte(isZeroBetaLook ? getSecondGender() : getGender());

      //  System.out.println(getSecondGender());
      //  System.out.println(getGender());
        //outPacket.encodeByte(getSkin());
        outPacket.encodeByte(isZeroBetaLook ? getSecondSkin() : getSkin());
       // outPacket.encodeInt(getFace());
        outPacket.encodeInt(isZeroBetaLook ? getSecondFace() : getFace());
        outPacket.encodeInt(getJob());
        outPacket.encodeByte(mega ? 0 : 1); // ?
        //outPacket.encodeInt(getHair());
        outPacket.encodeInt(isZeroBetaLook ? getSecondHair() : getHair());

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
        //outPacket.encodeInt(getWeaponId());

       if(!JobConstants.isZero((short) getJob())) {
            outPacket.encodeInt(getWeaponId());
            outPacket.encodeInt(getSubWeaponId());
        }else{
            outPacket.encodeInt(getWeaponId());
            System.out.println(getWeaponId());
            System.out.println(getSubWeaponId());
        }
        outPacket.encodeByte(isDrawElfEar());
        // outPacket.encodeByte(-1); //smth to do with a new class
        for (int i = 0; i < 3; i++) {
            outPacket.encodeInt(getPetIDs().get(i)); // always 3
        }
        if (JobConstants.isZero((short) getJob())) {
             outPacket.encodeByte(isZeroBetaLook);
          //  outPacket.encodeByte(1);
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
            outPacket.encodeInt(getBeastTamerFaceAcc());
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

    public int getSecondHair() {
        return secondHair;
    }

    public void setSecondHair(int secondHair) {
        this.secondHair = secondHair;
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

    public void updateDB(Session session, Transaction tx) {
        session.saveOrUpdate(this);
    }

    public void createInDB(Session session, Transaction tx) {
        session.save(this);
    }

    public void deleteFromDB(Session session, Transaction tx) {
        session.delete(this);
    }
}