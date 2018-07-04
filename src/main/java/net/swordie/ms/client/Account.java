package net.swordie.ms.client;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.damage.DamageSkinSaveData;
import net.swordie.ms.client.friend.Friend;
import net.swordie.ms.client.trunk.Trunk;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.PicStatus;
import net.swordie.ms.loaders.StringData;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.world.shop.cashshop.CashItemInfo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tim on 4/30/2017.
 */
@Entity
@Table(name = "accounts")
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    @Column(name = "accountTypeMask")
    private int accountType;
    private int age;
    private int vipGrade;
    private int nBlockReason;
    private int gmLevel;
    private byte gender;
    private byte msg2;
    private byte purchaseExp;
    private byte pBlockReason;
    private byte gradeCode;
    private long chatUnblockDate;
    private boolean hasCensoredNxLoginID;
    private String censoredNxLoginID;
    private String pic;
    private int characterSlots;
    private long creationDate;
    @JoinColumn(name = "trunkID")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Trunk trunk;
    // no eager -> sometimes get a "resultset closed" when fetching friends/damage skins
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "ownerAccID")
    private Set<Friend> friends;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "accID")
    private Set<DamageSkinSaveData> damageSkins = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "accID")
    private Set<Char> characters = new HashSet<>();
    private String lastLoggedIn;
    @Transient
    private Char currentChr;
    private int NXCredit;
    private int maplePoints;
    private int NXPrepaid;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "accID")
    private Set<LinkSkill> linkSkills = new HashSet<>();

    public Account(String username, int accountId, String pic, int accountType, int age, int vipGrade, int nBlockReason, byte gender, byte msg2,
                   byte purchaseExp, byte pBlockReason, long chatUnblockDate, boolean hasCensoredNxLoginID,
                   byte gradeCode, String censoredNxLoginID, int characterSlots, long creationDate) {
        this.username = username;
        this.id = accountId;
        this.pic = pic;
        this.accountType = accountType;
        this.age = age;
        this.vipGrade = vipGrade;
        this.gender = gender;
        this.msg2 = msg2;
        this.purchaseExp = purchaseExp;
        this.nBlockReason = nBlockReason;
        this.pBlockReason = pBlockReason;
        this.chatUnblockDate = chatUnblockDate;
        this.hasCensoredNxLoginID = hasCensoredNxLoginID;
        this.gradeCode = gradeCode;
        this.censoredNxLoginID = censoredNxLoginID;
        this.characterSlots = characterSlots;
        this.creationDate = creationDate;
        friends = new HashSet<>();
        trunk = new Trunk((byte) 20);
        setManager();
    }

    public Account(String id, int accountId) {
        this(id, accountId, null, 0, 0, 0, 0, (byte) 0, (byte) 0, (byte) 0, (byte) 3,
                0, false, (byte) 0, "", 16,
                System.currentTimeMillis());
    }

    public Account(){
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setManager() {
        accountType |= 1 << 4;
    }

    public boolean isManager() {
        return accountType >> 4 == 1;
    }

    public void setTester() {
        accountType |= 1 << 5;
    }

    public boolean isTester() {
        return accountType >> 5 == 1;
    }

    public void setSubTester() {
        accountType |= 1 << 13;
    }

    public boolean isSubTester() {
        return accountType >> 13 == 1;
    }

    public int getAge() {
        return age;
    }

    public int getVipGrade() {
        return vipGrade;
    }

    public byte getGender() {
        return gender;
    }

    public byte getMsg2() {
        return msg2;
    }

    public byte getPurchaseExp() {
        return purchaseExp;
    }

    public byte getpBlockReason() {
        return pBlockReason;
    }

    public long getChatUnblockDate() {
        return chatUnblockDate;
    }

    public boolean hasCensoredNxLoginID() {
        return hasCensoredNxLoginID;
    }

    public byte getGradeCode() {
        return gradeCode;
    }

    public String getCensoredNxLoginID() {
        return censoredNxLoginID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getnBlockReason() {
        return nBlockReason;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public int getCharacterSlots() {
        return characterSlots;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static Account getFromDBById(int accountId) {
        return (Account) DatabaseManager.getObjFromDB(Account.class, accountId);
    }

    public Set<Char> getCharacters() {
        return characters;
    }

    public void addCharacter(Char character) {
       getCharacters().add(character);
    }

    public String getPic() {
        // security is overrated
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public PicStatus getPicStatus() {
        PicStatus picStatus;
        String pic = getPic();
        if(pic == null || pic.length() == 0) {
            picStatus = PicStatus.CREATE_PIC;
        } else {
            picStatus = PicStatus.ENTER_PIC;
        }
        return picStatus;
    }

    public int getGmLevel() {
        return gmLevel;
    }

    public void setGmLevel(int gmLevel) {
        this.gmLevel = gmLevel;
    }

    public void setVipGrade(int vipGrade) {
        this.vipGrade = vipGrade;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public void setMsg2(byte msg2) {
        this.msg2 = msg2;
    }

    public void setnBlockReason(int nBlockReason) {
        this.nBlockReason = nBlockReason;
    }

    public void setPurchaseExp(byte purchaseExp) {
        this.purchaseExp = purchaseExp;
    }

    public void setpBlockReason(byte pBlockReason) {
        this.pBlockReason = pBlockReason;
    }

    public void setGradeCode(byte gradeCode) {
        this.gradeCode = gradeCode;
    }

    public void setChatUnblockDate(long chatUnblockDate) {
        this.chatUnblockDate = chatUnblockDate;
    }

    public void setHasCensoredNxLoginID(boolean hasCensoredNxLoginID) {
        this.hasCensoredNxLoginID = hasCensoredNxLoginID;
    }

    public void setCensoredNxLoginID(String censoredNxLoginID) {
        this.censoredNxLoginID = censoredNxLoginID;
    }

    public void setCharacterSlots(int characterSlots) {
        this.characterSlots = characterSlots;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isHasCensoredNxLoginID() {
        return hasCensoredNxLoginID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Friend> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friend> friends) {
        this.friends = friends;
    }

    public void addFriend(Friend friend) {
        if(getFriendByAccID(friend.getFriendAccountID()) == null) {
            getFriends().add(friend);
        }
    }

    public Friend getFriendByAccID(int accID) {
        return getFriends().stream().filter(f -> f.getFriendAccountID() == accID).findAny().orElse(null);
    }

    public void removeFriend(int accID) {
        removeFriend(getFriendByAccID(accID));
    }

    public void removeFriend(Friend f) {
        if(f != null) {
            getFriends().remove(f);
        }
    }

    public String getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(String lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    public Char getCurrentChr() {
        return currentChr;
    }

    public void setCurrentChr(Char currentChr) {
        this.currentChr = currentChr;
    }

    public Set<DamageSkinSaveData> getDamageSkins() {
        return damageSkins;
    }

    public void setDamageSkins(Set<DamageSkinSaveData> damageSkins) {
        this.damageSkins = damageSkins;
    }

    public void addDamageSkin(DamageSkinSaveData dssd) {
        if(getDamageSkinByItemID(dssd.getItemID()) == null) {
            getDamageSkins().add(dssd);
        }
    }

    public void removeDamageSkin(DamageSkinSaveData dssd) {
        if(dssd != null) {
            getDamageSkins().remove(dssd);
        }
    }

    public void removeDamageSkin(int itemID) {
        removeDamageSkin(getDamageSkinByItemID(itemID));
    }

    public void addDamageSkinByItemID(int itemID) {
        addDamageSkin(new DamageSkinSaveData(ItemConstants.getDamageSkinIDByItemID(itemID), itemID, false,
                StringData.getItemStringById(itemID)));
    }

    public DamageSkinSaveData getDamageSkinByItemID(int itemID) {
        return getDamageSkins().stream().filter(d -> d.getItemID() == itemID).findAny().orElse(null);
    }

    public Trunk getTrunk() {
        if(trunk == null) {
            trunk = new Trunk((byte) 20);
        }
        return trunk;
    }

    public void setTrunk(Trunk trunk) {
        this.trunk = trunk;
    }

    public int getNXCredit() {
        return NXCredit;
    }

    public void setNXCredit(int nxCredit) {
        this.NXCredit = nxCredit;
    }

    public int getMaplePoints() {
        return maplePoints;
    }

    public void setMaplePoints(int maplePoints) {
        this.maplePoints = maplePoints;
    }

    public int getNXPrepaid() {
        return NXPrepaid;
    }

    public void setNXPrepaid(int nxPrepaid) {
        this.NXPrepaid = nxPrepaid;
    }

    public void addLinkSkill(LinkSkill linkSkill) {
        removeLinkSkillByOwnerID(linkSkill.getOwnerID());
        getLinkSkills().add(linkSkill);
    }

    public void addLinkSkill(Char originChar, int sonID, int linkedSkill) {
        int level = SkillConstants.getLinkSkillLevelByCharLevel(originChar.getLevel());
        LinkSkill ls = new LinkSkill(originChar.getId(), linkedSkill, level);
        addLinkSkill(ls);
    }

    public void removeLinkSkillByOwnerID(int ownerID) {
        getLinkSkills().stream().filter(l -> l.getOwnerID() == ownerID).findFirst()
                .ifPresent(ls -> getLinkSkills().remove(ls));
    }

    public Set<LinkSkill> getLinkSkills() {
        return linkSkills;
    }

    public void setLinkSkills(Set<LinkSkill> linkSkills) {
        this.linkSkills = linkSkills;
    }
}
