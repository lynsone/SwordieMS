package client;

import client.character.Char;
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
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "accountTypeMask")
    private int accountType;
    @Column(name = "age")
    private int age;
    @Column(name = "vipGrade")
    private int vipGrade;
    @Column(name = "nBlockReason")
    private int nBlockReason;
    @Column(name = "gmLevel")
    private int gmLevel;
    @Column(name = "gender")
    private byte gender;
    @Column(name = "msg2")
    private byte msg2;
    @Column(name = "purchaseExp")
    private byte purchaseExp;
    @Column(name = "pBlockReason")
    private byte pBlockReason;
    @Column(name = "gradeCode")
    private byte gradeCode;
    @Column(name = "chatUnblockDate")
    private long chatUnblockDate;
    @Column(name = "hasCensoredNxLoginID")
    private boolean hasCensoredNxLoginID;
    @Column(name = "censoredNxLoginID")
    private String censoredNxLoginID;
    @Column(name = "pic")
    private String pic;
    @Column(name = "characterSlots")
    private int characterSlots;
    @Column(name = "creationDate")
    private long creationDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "accId")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Char> characters = new ArrayList<>();

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

    public void updateDB() {
        Server.getInstance().cleanSessions();
        Session session = Server.getInstance().getNewDatabaseSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(this);
            tx.commit();
        } finally {
            session.close();
        }
    }

    public void deleteCharacter(Char chr) {
        getCharacters().remove(chr);
        updateDB();
    }
}
