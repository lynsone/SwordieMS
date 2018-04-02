package client;

import client.character.Char;
import client.friend.Friend;
import enums.PicStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cascade;
import org.hibernate.query.Query;
import server.Server;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ownerAccID")
    private List<Friend> friends;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "accId")
    private List<Char> characters = new ArrayList<>();
    private String lastLoggedIn;
    @Transient
    private Char currentChr;

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
        friends = new ArrayList<>();
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
        Account account = null;
        Session session = Server.getInstance().getNewDatabaseSession();
        Transaction transaction = session.beginTransaction();
        String query = "FROM " + Account.class.getName() + " WHERE id = :id";
        Query sql = session.createQuery(query);
        sql.setParameter("id", accountId);
        List list = sql.list();
        if(list.size() > 0) {
            account = (Account) list.get(0);
        }
        transaction.commit();
        session.close();
        return account;
    }

    public List<Char> getCharacters() {
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

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
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
        if(f != null && getFriends().contains(f)) {
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
}
