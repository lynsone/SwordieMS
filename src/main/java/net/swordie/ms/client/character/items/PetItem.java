package net.swordie.ms.client.character.items;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.life.pet.Pet;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.life.pet.PetSkill;
import org.apache.log4j.Logger;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Util;

import javax.persistence.*;

/**
 * Created on 4/14/2018.
 */
@Entity
@Table(name = "petitems")
@PrimaryKeyJoinColumn(name = "itemId")
public class PetItem extends Item {
    @Transient
    private final Logger log = Logger.getLogger(PetItem.class);

    private String name;
    private byte level;
    private short tameness;
    private byte repleteness; // hungry thing
    private short petAttribute;
    private int petSkill;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "dateDead")
    private FileTime dateDead;
    private int remainLife;
    private short attribute;
    private byte activeState;
    private int autoBuffSkill;
    private int petHue;
    private short giantRate;

    @Override
    public Type getType() {
        return Type.PET;
    }

    public void encode(OutPacket outPacket) {
        super.encode(outPacket);
        outPacket.encodeString(getName(), 13);
        outPacket.encodeByte(getLevel());
        outPacket.encodeShort(getTameness() + 1);
        outPacket.encodeByte(getRepleteness());
//        outPacket.encodeFT(FileTime.getTimeFromLong(System.currentTimeMillis() + 10000000));
        outPacket.encodeArr(Util.getByteArrayByString("00 40 E0 FD 3B 37 4F 01"));
        outPacket.encodeShort(getPetAttribute());
        outPacket.encodeShort(getPetSkill());
        outPacket.encodeInt(getRemainLife());
        outPacket.encodeShort(getAttribute());
        outPacket.encodeByte(getActiveState());
        outPacket.encodeInt(getAutoBuffSkill());
        outPacket.encodeInt(getPetHue());
        outPacket.encodeShort(getGiantRate());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public short getTameness() {
        return tameness;
    }

    public void setTameness(short tameness) {
        this.tameness = tameness;
    }

    public byte getRepleteness() {
        return repleteness;
    }

    public void setRepleteness(byte repleteness) {
        this.repleteness = repleteness;
    }

    public short getPetAttribute() {
        return petAttribute;
    }

    public void setPetAttribute(short petAttribute) {
        this.petAttribute = petAttribute;
    }

    public int getPetSkill() {
        return petSkill;
    }

    public void setPetSkill(int petSkill) {
        this.petSkill = petSkill;
    }

    public FileTime getDateDead() {
        return dateDead;
    }

    public void setDateDead(FileTime dateDead) {
        this.dateDead = dateDead;
    }

    public int getRemainLife() {
        return remainLife;
    }

    public void setRemainLife(int remainLife) {
        this.remainLife = remainLife;
    }

    public short getAttribute() {
        return attribute;
    }

    public void setAttribute(short attribute) {
        this.attribute = attribute;
    }

    public byte getActiveState() {
        return activeState;
    }

    public void setActiveState(byte activeState) {
        this.activeState = activeState;
    }

    public int getAutoBuffSkill() {
        return autoBuffSkill;
    }

    public void setAutoBuffSkill(int autoBuffSkill) {
        this.autoBuffSkill = autoBuffSkill;
    }

    public int getPetHue() {
        return petHue;
    }

    public void setPetHue(int petHue) {
        this.petHue = petHue;
    }

    public short getGiantRate() {
        return giantRate;
    }

    public void setGiantRate(short giantRate) {
        this.giantRate = giantRate;
    }

    public Pet createPet(Char chr) {
        Pet pet = new Pet(-1);
        pet.setFh(chr.getFoothold());
        pet.setPosition(chr.getPosition());
        pet.setId(getItemId());
        int chosenIdx = chr.getFirstPetIdx();
        if(chosenIdx == -1) {
            log.error("Tried to create a pet while 3 pets already exist.");
        }
        pet.setIdx(chosenIdx);
        pet.setName(getName());
        pet.setPetLockerSN(getId());
        pet.setHue(getPetHue());
        pet.setGiantRate(getGiantRate());
        pet.setItem(this);
        return pet;
    }

    public PetItem deepCopy() {
        PetItem petItem = (PetItem) super.deepCopy();
        petItem.setName(getName());
        petItem.setLevel(getLevel());
        petItem.setTameness(getTameness());
        petItem.setRepleteness(getRepleteness());
        petItem.setPetAttribute(getPetAttribute());
        petItem.setPetSkill(getPetSkill());
        petItem.setDateDead(getDateDead().deepCopy());
        petItem.setRemainLife(getRemainLife());
        petItem.setAttribute(getAttribute());
        petItem.setActiveState(getActiveState());
        petItem.setAutoBuffSkill(getAutoBuffSkill());
        petItem.setPetHue(getPetHue());
        petItem.setGiantRate(getGiantRate());

        return petItem;
    }

    public void addPetSkill(PetSkill petSkill) {
        setPetSkill(getPetSkill() | petSkill.getVal());
    }

    public void removePetSkill(PetSkill petSkill) {
        setPetSkill(getPetSkill() ^ petSkill.getVal());
    }
}
