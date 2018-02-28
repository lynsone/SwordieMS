package client.character;

import client.Client;
import client.character.items.*;
import client.character.skills.Skill;
import client.character.skills.TemporaryStatManager;
import client.field.Field;
import client.field.Portal;
import client.jobs.Job;
import client.jobs.JobManager;
import client.jobs.resistance.WildHunterInfo;
import client.life.AffectedArea;
import client.life.Drop;
import connection.OutPacket;
import constants.GameConstants;
import constants.JobConstants;
import constants.SkillConstants;
import enums.ChatMsgColour;
import enums.DBChar;
import enums.InvType;
import enums.Stat;
import loaders.SkillData;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cascade;
import packet.Stage;
import packet.UserLocal;
import packet.WvsContext;
import server.Server;
import util.FileTime;
import loaders.ItemData;
import util.Position;
import util.Rect;

import javax.persistence.*;
import java.util.*;

import static enums.InvType.EQUIP;
import static enums.InvType.EQUIPPED;
import static enums.InventoryOperation.*;

/**
 * Created on 11/17/2017.
 */
@Entity
@Table(name = "characters")
public class Char {

    @Transient
    private Client client;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "accId")
    private int accId;

    @JoinColumn(name = "equippedInventory")
    @OneToOne
    private Inventory equippedInventory = new Inventory(InvType.EQUIPPED, 50);

    @JoinColumn(name = "equipInventory")
    @OneToOne
    private Inventory equipInventory = new Inventory(InvType.EQUIP, 50);

    @JoinColumn(name = "consumeInventory")
    @OneToOne
    private Inventory consumeInventory = new Inventory(InvType.CONSUME, 50);

    @JoinColumn(name = "etcInventory")
    @OneToOne
    private Inventory etcInventory = new Inventory(InvType.ETC, 50);

    @JoinColumn(name = "installInventory")
    @OneToOne
    private Inventory installInventory = new Inventory(InvType.INSTALL, 50);

    @JoinColumn(name = "cashInventory")
    @OneToOne
    private Inventory cashInventory = new Inventory(InvType.CASH, 50);

    @JoinColumn(name = "avatarData")
    @OneToOne
    private AvatarData avatarData;
    @OneToOne
    private FuncKeyMap funcKeyMap;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "charId")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Skill> skills;

    @Transient
    private Ranking ranking;
    @Transient
    private int combatOrders;
    @Transient
    private List<Skill> stolenSkills;
    @Transient
    private List<Skill> chosenSkills;
    @Transient
    private QuestManager questManager;
    @Transient
    private List<ItemPot> itemPots;
    @Transient
    private List<Pet> pets;
    @Transient
    private List<FriendRecord> friends;
    @Transient
    private List<ExpConsumeItem> expConsumeItems;
    @Transient
    private List<MonsterBattleMobInfo> monsterBattleMobInfos;
    @Transient
    private MonsterBattleLadder monsterBattleLadder;
    @Transient
    private MonsterBattleRankInfo monsterBattleRankInfo;
    @Transient
    private Position position;
    @Transient
    private Position oldPosition;
    @Transient
    private Field field;
    @Transient
    private byte moveAction;
    @Transient
    private TemporaryStatManager temporaryStatManager;
    @Transient
    private Job jobHandler;
    @Transient
    private boolean left;
    @Transient
    private MarriageRecord marriageRecord;
    @Transient
    private WildHunterInfo wildHunterInfo;
    @Transient
    private ZeroInfo zeroInfo;
    @Transient
    private int nickItem;
    @Transient
    private int damageSkin;
    @Transient
    private int premiumDamageSkin;
    @Transient
    private boolean partyInvitable;
    @Transient
    private ScriptManager scriptManager = new ScriptManager(this);

    public Char() {
        this(0, "", 0, 0, 0, (short) 0, (byte) -1, (byte) -1, new int[]{});
    }

    public Char(int accId, String name, int keySettingType, int eventNewCharSaleJob, int job, short curSelectedSubJob,
                byte gender, byte skin, int[] items) {
        this.accId = accId;
        avatarData = new AvatarData();
        avatarData.setAvatarLook(new AvatarLook());
        AvatarLook avatarLook = avatarData.getAvatarLook();
        avatarLook.setGender(gender);
        avatarLook.setSkin(skin);
        avatarLook.setFace(items.length > 0 ? items[0] : 0);
        avatarLook.setHair(items.length > 1 ? items[1] : 0);
        List<Integer> hairEquips = new ArrayList<>();
        for (int itemId : items) {
            Equip equip = ItemData.getEquipDeepCopyFromId(itemId);
            if (equip != null) {
                hairEquips.add(itemId);
                if ("Wp".equals(equip.getiSlot())) {
                    if (!equip.isCash()) {
                        avatarLook.setWeaponId(itemId);
                    } else {
                        avatarLook.setWeaponStickerId(itemId);
                    }
                }
//                equip.setBagIndex(-ItemConstants.getBodyPartFromItem(equip.getItemId(), getAvatarData().getAvatarLook().getGender()));
//                addItemToInventory(InvType.EQUIPPED, equip);
            }
        }
        avatarLook.setHairEquips(hairEquips);
        avatarLook.setJob(job);
        CharacterStat characterStat = new CharacterStat(name, job);
        characterStat.setLevel(1);
        characterStat.setStr(4);
        characterStat.setDex(4);
        characterStat.setInt(4);
        characterStat.setLuk(4);
        characterStat.setHp(50);
        characterStat.setMaxHp(50);
        characterStat.setMp(50);
        characterStat.setMaxMp(50);
        characterStat.setGender(gender);
        characterStat.setSkin(skin);
        characterStat.setFace(items.length > 0 ? items[0] : 0);
        characterStat.setHair(items.length > 1 ? items[1] : 0);
        characterStat.setPosMap(100000000);
        getAvatarData().setCharacterStat(characterStat);
        ranking = new Ranking();
        pets = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            pets.add(new Pet());
        }
        stolenSkills = new ArrayList<>();
        chosenSkills = new ArrayList<>();
        questManager = new QuestManager();
        itemPots = new ArrayList<>();
        friends = new ArrayList<>();
        expConsumeItems = new ArrayList<>();
        skills = new ArrayList<>();
        temporaryStatManager = new TemporaryStatManager(this);
//        monsterBattleMobInfos = new ArrayList<>();
//        monsterBattleLadder = new MonsterBattleLadder();
//        monsterBattleRankInfo = new MonsterBattleRankInfo();

    }

    public void updateDB() {
        Session session = Server.getInstance().getNewDatabaseSession();
        Transaction tx = session.beginTransaction();
        getAvatarData().updateDB(session, tx);
        getFuncKeyMap().updateDB(session, tx);
        for (Inventory inventory : getInventories()) {
            inventory.updateDB(session, tx);
        }
        for (Skill s : getSkills()) {
            s.updateDB(session, tx);
        }
        session.saveOrUpdate(this);
        tx.commit();
        session.close();
    }

    public void createInDB() {
        Session session = Server.getInstance().getNewDatabaseSession();
        Transaction tx = session.beginTransaction();
        getAvatarData().createInDB(session, tx);
        for (Inventory inventory : getInventories()) {
            inventory.createInDB(session, tx);
        }
        session.save(this);
        tx.commit();
        session.close();
    }

    public void deleteFromDB() {
        Session session = Server.getInstance().getNewDatabaseSession();
        Transaction tx = session.beginTransaction();
        getAvatarData().deleteFromDB(session, tx);
        for (Inventory inventory : getInventories()) {
            inventory.deleteInDB(session, tx);
        }
        session.delete(this);
        tx.commit();
        session.close();
    }

    public static Char getFromDBById(int userId) {
        Char chr;
        Session session = Server.getInstance().getNewDatabaseSession();
        Transaction transaction = session.beginTransaction();
        chr = session.get(Char.class, userId);
        transaction.commit();
        session.close();
        return chr;
    }

    public AvatarData getAvatarData() {
        return avatarData;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public int getId() {
        return id;
    }

    public void setAvatarData(AvatarData avatarData) {
        this.avatarData = avatarData;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public Inventory getEquippedInventory() {
        return equippedInventory;
    }

    public void addItemToInventory(InvType type, Item item, boolean hasCorrectBagIndex) {
        Inventory inventory = getInventoryByType(type);
        if (inventory != null) {
            Item existingItem = inventory.getItemByItemID(item.getItemId());
            if (existingItem != null && existingItem.getInvType().isStackable()) {
                existingItem.addQuantity(item.getQuantity());
                write(WvsContext.inventoryOperation(true, false,
                        UPDATE_BAG_QUANTITY, (short) existingItem.getBagIndex(), (byte) -1, 0, existingItem));
            } else {
                item.setInventoryId(inventory.getId());
                if (!hasCorrectBagIndex) {
                    item.setBagIndex(inventory.getFirstOpenSlot());
                }
                inventory.addItem(item);
                if (item.getId() == 0) {
                    item.updateDB();
                }
                write(WvsContext.inventoryOperation( true, false,
                        ADD, (short) item.getBagIndex(), (byte) -1,0, item));
            }
        }
    }

    public void addItemToInventoryAndUpdateClient(Item item) {
        addItemToInventory(item);
    }

    public void addItemToInventory(Item item) {
        addItemToInventory(item.getInvType(), item, false);
    }

    public void setEquippedInventory(Inventory equippedInventory) {
        this.equippedInventory = equippedInventory;
    }

    public Inventory getEquipInventory() {
        return equipInventory;
    }

    public void setEquipInventory(Inventory equipInventory) {
        this.equipInventory = equipInventory;
    }

    public Inventory getConsumeInventory() {
        return consumeInventory;
    }

    public void setConsumeInventory(Inventory consumeInventory) {
        this.consumeInventory = consumeInventory;
    }

    public Inventory getEtcInventory() {
        return etcInventory;
    }

    public void setEtcInventory(Inventory etcInventory) {
        this.etcInventory = etcInventory;
    }

    public Inventory getInstallInventory() {
        return installInventory;
    }

    public void setInstallInventory(Inventory installInventory) {
        this.installInventory = installInventory;
    }

    public Inventory getCashInventory() {
        return cashInventory;
    }

    public void setCashInventory(Inventory cashInventory) {
        this.cashInventory = cashInventory;
    }

    /**
     * Encodes this Char's info inside a given {@link OutPacket}, with given info.
     * @param outPacket The OutPacket this method should encode to.
     * @param mask Which info should be encoded.
     */
    public void encode(OutPacket outPacket, DBChar mask) {

        // CharacterData::Decode
        outPacket.encodeLong(mask.get());
        outPacket.encodeByte(getCombatOrders());
        for (Pet pet : getPets()) {
            outPacket.encodeInt(pet.getActiveSkillCoolTime());
        }
        outPacket.encodeByte(0); // unk, not in kmst
        byte sizeByte = 0;
        outPacket.encodeByte(sizeByte);
        for (int i = 0; i < sizeByte; i++) {
            outPacket.encodeInt(0);
        }

        int sizee = 0;
        outPacket.encodeInt(sizee);
        for (int i = 0; i < sizee; i++) {
            outPacket.encodeInt(0); // nKey
            outPacket.encodeLong(0); // pInfo
        }
        outPacket.encodeByte(0); // again unsure
        if (mask.isInMask(DBChar.Character)) {
            getAvatarData().getCharacterStat().encode(outPacket);
            outPacket.encodeByte(getFriends().size());
            boolean hasBlessingOfFairy = getBlessingOfFairy() != null;
            outPacket.encodeByte(hasBlessingOfFairy);
            if (hasBlessingOfFairy) {
                outPacket.encodeString(getBlessingOfFairy());
            }
            boolean hasBlessingOfEmpress = getBlessingOfEmpress() != null;
            outPacket.encodeByte(hasBlessingOfEmpress);
            if (hasBlessingOfEmpress) {
                outPacket.encodeString(getBlessingOfEmpress());
            }
            outPacket.encodeByte(false); // ultimate explorer, deprecated
        }
        if (mask.isInMask(DBChar.Money)) {
            outPacket.encodeLong(getMoney());
        }
        if (mask.isInMask(DBChar.ItemSlotConsume) || mask.isInMask(DBChar.ExpConsumeItem)) {
            outPacket.encodeInt(getExpConsumeItems().size());
            for (ExpConsumeItem eci : getExpConsumeItems()) {
                eci.encode(outPacket);
            }
        }
        if (mask.isInMask(DBChar.ItemSlotConsume) || mask.isInMask(DBChar.ShopBuyLimit)) {
            int size = 0;
            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeLong(0);
                outPacket.encodeLong(0);
            }
        }
        if (mask.isInMask(DBChar.MonsterBattleInfo)) {
            int count = 0; // MonsterBattle_MobInfo
            outPacket.encodeInt(count);
            if (getMonsterBattleMobInfos() != null) {
                for (MonsterBattleMobInfo mbmi : getMonsterBattleMobInfos()) {
                    mbmi.encode(outPacket);
                    // int int int int int int byte int int
                }
            }
            outPacket.encodeInt(getId());

            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0); // ^-- idk

            boolean hasMonsterBattleLadder = getMonsterBattleLadder() != null;
            outPacket.encodeByte(hasMonsterBattleLadder);
            if (hasMonsterBattleLadder) {
                getMonsterBattleLadder().encode(outPacket); // GW_MonsterBattleLadder_UserInfo::Decode
            }
            boolean hasMonsterBattleRankInfo = getMonsterBattleRankInfo() != null;
            outPacket.encodeByte(hasMonsterBattleRankInfo);
            if (hasMonsterBattleRankInfo) {
                getMonsterBattleRankInfo().encode(outPacket); // GW_MonsterBattleRankInfo::Decode(&dummyBLD, nSlotHyper);
            }
            outPacket.encodeByte(hasMonsterBattleRankInfo);
            // again?
            if (hasMonsterBattleRankInfo) {
                getMonsterBattleRankInfo().encode(outPacket); // GW_MonsterBattleRankInfo::Decode(&dummyBLD, nSlotHyper);
            }
        }
        if (mask.isInMask(DBChar.InventorySize)) {
            outPacket.encodeByte(getEquipInventory().getSlots());
            outPacket.encodeByte(getConsumeInventory().getSlots());
            outPacket.encodeByte(getEtcInventory().getSlots());
            outPacket.encodeByte(getInstallInventory().getSlots());
            outPacket.encodeByte(getCashInventory().getSlots());
        }

        if (mask.isInMask(DBChar.AdminShopCount)) {
            outPacket.encodeInt(0); // ???
            outPacket.encodeInt(0);
        }
        if (mask.isInMask(DBChar.ItemSlotEquip)) {
            outPacket.encodeByte(0); // ?
            List<Item> equippedItems = getEquippedInventory().getItems();
            equippedItems.sort(Comparator.comparingInt(Item::getBagIndex));
            for (Item item : equippedItems) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() > 0 && item.getBagIndex() < 100) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() > 100 && item.getBagIndex() < 1000) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);
            for (Item item : getEquipInventory().getItems()) {
                Equip equip = (Equip) item;
                outPacket.encodeShort(equip.getBagIndex());
                equip.encode(outPacket);
            }
            outPacket.encodeShort(0);
            // NonBPEquip::Decode
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() > 1000 && item.getBagIndex() < 1100) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);
            // VirtualEquipInventory::Decode
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() >= 1100 && item.getBagIndex() < 1200) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);
            outPacket.encodeShort(0);
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() >= 1200 && item.getBagIndex() <= 1300) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);
            outPacket.encodeShort(0);
            outPacket.encodeShort(0);
            outPacket.encodeShort(0);
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() >= 5000 && item.getBagIndex() <= 5003) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);
            outPacket.encodeShort(0);
            outPacket.encodeShort(0);
            outPacket.encodeShort(0);
            outPacket.encodeShort(0);
        }
        if (mask.isInMask(DBChar.ItemSlotConsume)) {
            for (Item item : getConsumeInventory().getItems()) {
                outPacket.encodeByte(item.getBagIndex());
                item.encode(outPacket);
            }
            outPacket.encodeByte(0);
        }
        if (mask.isInMask(DBChar.ItemSlotInstall)) {
            for (Item item : getInstallInventory().getItems()) {
                outPacket.encodeByte(item.getBagIndex());
                item.encode(outPacket);
            }
            outPacket.encodeByte(0);
        }
        if (mask.isInMask(DBChar.ItemSlotEtc)) {
            for (Item item : getEtcInventory().getItems()) {
                outPacket.encodeByte(item.getBagIndex());
                item.encode(outPacket);
            }
            outPacket.encodeByte(0);
        }
        if (mask.isInMask(DBChar.ItemSlotCash)) {
            for (Item item : getCashInventory().getItems()) {
                outPacket.encodeByte(item.getBagIndex());
                item.encode(outPacket);
            }
            outPacket.encodeByte(0);
        }
        // BagDatas
        if (mask.isInMask(DBChar.ItemSlotConsume)) {
            // TODO
            outPacket.encodeInt(0);
        }
        if (mask.isInMask(DBChar.ItemSlotInstall)) {
            // TODO
            outPacket.encodeInt(0);
        }
        if (mask.isInMask(DBChar.ItemSlotEtc)) {
            // TODO
            outPacket.encodeInt(0);
        }
        if (mask.isInMask(DBChar.ItemSlotCash)) {
            // TODO
            outPacket.encodeInt(0);
        }
        // End bagdatas
        if (mask.isInMask(DBChar.CoreAura)) {
            int val = 0;
            outPacket.encodeInt(val);
            for (int i = 0; i < val; i++) {
                outPacket.encodeInt(0);
                outPacket.encodeLong(0);
            }
        }
//        if (mask.isInMask(DBChar.Unsure)) {
//            int val = 0;
//            outPacket.encodeInt(val);
//            for (int i = 0; i < val; i++) {
//                new FileTime(0).encode(outPacket);
//                outPacket.encodeLong(0);
//            }
//        }
        if (mask.isInMask(DBChar.ItemPot)) {
            boolean hasItemPot = getItemPots() != null;
            outPacket.encodeByte(hasItemPot);
            if (hasItemPot) {
                for (int i = 0; i < getItemPots().size(); i++) {
                    getItemPots().get(i).encode(outPacket);
                    outPacket.encodeByte(i != getItemPots().size() - 1);
                }
            }
        }

        if (mask.isInMask(DBChar.SkillRecord)) {
            boolean encodeSkills = getSkills().size() > 0;
            outPacket.encodeByte(encodeSkills);
            if (encodeSkills) {
                short size = (short) getSkills().size();
                outPacket.encodeShort(size);
                for (Skill skill : getSkills()) {
                    outPacket.encodeInt(skill.getSkillId());
                    outPacket.encodeInt(skill.getCurrentLevel());
                    outPacket.encodeFT(FileTime.getFileTimeFromType(FileTime.Type.PERMANENT));
                    if (SkillConstants.isSkillNeedMasterLevel(skill.getSkillId())) {
                        outPacket.encodeInt(skill.getMasterLevel());
                    }
                }
                short size2 = 0;
                outPacket.encodeShort(size2);
                for (int i = 0; i < size2; i++) {
                    outPacket.encodeInt(0); // another nCount
                    outPacket.encodeShort(0); // idk
                }
            } else {
                short size = 0;
                outPacket.encodeShort(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodeInt(0); // nTI
                    outPacket.encodeInt(0); // sValue
                }
                short size2 = 0;
                outPacket.encodeShort(size2);
                for (int i = 0; i < size2; i++) {
                    outPacket.encodeInt(0); // nTI
                }

                short size3 = 0;
                outPacket.encodeShort(size3);
                for (int i = 0; i < size3; i++) {
                    outPacket.encodeInt(0); // nTI
                    outPacket.encodeFT(new FileTime(0)); // pInfo
                }
                short size4 = 0;
                outPacket.encodeShort(size2);
                for (int i = 0; i < size2; i++) {
                    outPacket.encodeInt(0); // nTI
                }

                short size5 = 0;
                outPacket.encodeShort(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodeInt(0); // nTI
                    outPacket.encodeInt(0); // sValue
                }
                short size6 = 0;
                outPacket.encodeShort(size2);
                for (int i = 0; i < size2; i++) {
                    outPacket.encodeInt(0); // nTI
                }
            }
        }

        if (mask.isInMask(DBChar.SkillCooltime)) {
            short size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0); // nSkillId
                outPacket.encodeInt(0); // nSkillCooltime
            }
        }

        if (mask.isInMask(DBChar.QuestRecord)) {
            // modified/deleted, not completed anyway
            boolean init = true;
            outPacket.encodeByte(init);
            short size = (short) getQuestManager().getQuests().size();
            outPacket.encodeShort(size);
            for (Quest quest : getQuestManager().getQuests()) {
                outPacket.encodeInt(quest.getQRKey());
                outPacket.encodeString(quest.getQRValue());
            }
            if (!init) {
                short size2 = 0;
                outPacket.encodeShort(size2);
                for (int i = 0; i < size2; i++) {
                    outPacket.encodeInt(0); // nQRKey
                }
            }
            outPacket.encodeShort(0); // ? mushy
        }
        if (mask.isInMask(DBChar.QuestComplete)) {
            boolean init = true;
            outPacket.encodeByte(init);
            List<Quest> completedQuests = getQuestManager().getCompletedQuests();
            outPacket.encodeShort(completedQuests.size());
            for (Quest quest : completedQuests) {
                outPacket.encodeInt(quest.getQRKey());
                outPacket.encodeInt(0); // Timestamp of completion
            }
            if (!init) {
                short size = 0;
                outPacket.encodeShort(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodeInt(0); // nQRKey?
                }
            }
        }
        if (mask.isInMask(DBChar.MinigameRecord)) {
            int size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                new MiniGameRecord().encode(outPacket);
            }
        }
        if (mask.isInMask(DBChar.CoupleRecord)) {
            int coupleSize = 0;
            outPacket.encodeShort(coupleSize);
            for (int i = 0; i < coupleSize; i++) {
                new CoupleRecord().encode(outPacket);
            }
            int friendSize = 0;
            outPacket.encodeShort(friendSize);
            for (int i = 0; i < friendSize; i++) {
                new FriendRecord().encode(outPacket);
            }
            int marriageSize = 0;
            outPacket.encodeShort(marriageSize);
            for (int i = 0; i < marriageSize; i++) {
                new MarriageRecord().encode(outPacket);
            }
        }

        if (mask.isInMask(DBChar.MapTransfer)) {
            for (int i = 0; i < 5; i++) {
                outPacket.encodeInt(0);
            }
            for (int i = 0; i < 10; i++) {
                outPacket.encodeInt(0);
            }
            for (int i = 0; i < 13; i++) {
                outPacket.encodeInt(0);
            }
            for (int i = 0; i < 13; i++) {
                outPacket.encodeInt(0);
            }
        }
        if (mask.isInMask(DBChar.MonsterBookCover)) {
            outPacket.encodeInt(0); // nMonsterBookCoverID?
        }
        if (mask.isInMask(DBChar.MonsterBookCard)) {
            boolean isCompleted = false;
            outPacket.encodeByte(isCompleted);
            if (!isCompleted) {
                short size = 0;
                outPacket.encodeShort(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodeShort(0);
                    outPacket.encodeByte(0);
                }
            } else {
                outPacket.encodeShort(0); // card list size
                short encSize = 0;
                outPacket.encodeShort(encSize);
                outPacket.encodeBytes(new byte[encSize]);
                encSize = 0;
                outPacket.encodeShort(encSize);
                outPacket.encodeBytes(new byte[encSize]);
            }
            outPacket.encodeInt(-1); // monsterbook set
        }
        if (mask.isInMask(DBChar.QuestCompleteOld)) {
            short size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeShort(0);
            }
        }
        if (mask.isInMask(DBChar.Familiar)) {
            outPacket.encodeInt(0);
            // if int is > 0: sub_72AEB0
        }
        if (mask.isInMask(DBChar.NewYearCard)) {
            short size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeString("");
                outPacket.encodeByte(0);
                outPacket.encodeLong(0);
                outPacket.encodeInt(0);
                outPacket.encodeString("");
                outPacket.encodeByte(0);
                outPacket.encodeByte(0);
                outPacket.encodeLong(0);
                outPacket.encodeString("");
            }
        }
        if (mask.isInMask(DBChar.QuestRecordEx)) {
            outPacket.encodeShort(getQuestManager().getEx().size());
            for (Quest quest : getQuestManager().getEx()) {
                outPacket.encodeInt(quest.getQRKey());
                outPacket.encodeString(quest.getQRValue());
            }
        }
        if (mask.isInMask(DBChar.Avatar)) {

            short size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0); // sValue
                new AvatarLook().encode(outPacket);
            }
        }
        if (mask.isInMask(DBChar.MapTransfer)) {
            int size = 0;
            outPacket.encodeInt(0);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
            }
        }
        if (mask.isInMask(DBChar.WildHunterInfo)) {
            if (JobConstants.isWildHunter(getAvatarData().getCharacterStat().getJob())) {
                getWildHunterInfo().encode(outPacket); // GW_WildHunterInfo::Decode
            }
        }
        if (mask.isInMask(DBChar.ZeroInfo)) {
            if (JobConstants.isZero(getAvatarData().getCharacterStat().getJob())) {
                if(getZeroInfo() == null) {
                    initZeroInfo();
                }
                getZeroInfo().encode(outPacket); //ZeroInfo::Decode
            }
        }
        if (mask.isInMask(DBChar.ShopBuyLimit)) {
            short size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                // Encode shop buy limit
            }
        }
        if (mask.isInMask(DBChar.StolenSkills)) {
            if (JobConstants.isPhantom(getAvatarData().getCharacterStat().getJob())) {
                for (Skill skill : getStolenSkills()) {
                    outPacket.encodeInt(skill.getSkillId());
                }
            } else {
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);

                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);

                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);

                outPacket.encodeInt(0);
                outPacket.encodeInt(0);

                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
            }
        }
        if (mask.isInMask(DBChar.ChosenSkills)) {
            if (JobConstants.isPhantom(getAvatarData().getCharacterStat().getJob())) {
                for (Skill skill : getChosenSkills()) {
                    outPacket.encodeInt(skill.getSkillId());
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    outPacket.encodeInt(0);
                }
            }
        }
        if (mask.isInMask(DBChar.CharacterPotentialSkill)) {
            short size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeByte(0); // nKey
                outPacket.encodeInt(0); // nSkillID
                outPacket.encodeByte(0); // nSLV
                outPacket.encodeByte(0); // nGrade
            }
        }
        if (mask.isInMask(DBChar.SoulCollection)) {
            short size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0); //
                outPacket.encodeInt(0); //
            }
        }
        sizee = 0;
        outPacket.encodeInt(sizee);
        for (int i = 0; i < sizee; i++) {
            outPacket.encodeString("");
            // sub_73A1A0
            outPacket.encodeInt(0);
            outPacket.encodeString("");
            int size = 0;
            outPacket.encodeInt(size);
            for (int j = 0; j < size; j++) {
                outPacket.encodeByte(0);
            }
        }
        outPacket.encodeByte(0); // idk
        // MonsterLifeInviteInfo
//        int sizeInt = 0;
//        for(int i = 0; i < sizeInt; i++) {
//            getMonsterLifeInviteInfo().encode(outPacket);
//        }
//        outPacket.encodeBytes(Util.getByteArrayByString("01 00 00 00 00 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 00 00 00 00 00 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0B 00 43 72 65 61 74 69 6E 67 2E 2E 2E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 02 00 00 00 00 00 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 40 E0 FD 3B 37 4F 01 00 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 40 E0 FD 3B 37 4F 01 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 01 00 00 00 00 00 00 00 64 00 00 00 00 80 05 BB 46 E6 17 02 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 40 E0 FD 3B 37 4F 01 00 01 00 00 01 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 40 64 2B 70 84 7A D3 01 64 00 00 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00"));/*
        if (mask.isInMask(DBChar.Character)) {
            outPacket.encodeInt(0); // honor level
            outPacket.encodeInt(0); // honor exp
        }
        if (mask.isInMask(DBChar.Money)) {
            boolean shouldIEncodeThis = true;
            outPacket.encodeByte(shouldIEncodeThis);
            if (shouldIEncodeThis) {
                short size = 0;
                outPacket.encodeShort(size);
                for (int i = 0; i < size; i++) {
                    short category = 0;
                    outPacket.encodeShort(category);
                    short size2 = 0;
                    outPacket.encodeShort(size2);
                    for (int i2 = 0; i2 < size2; i2++) {
                        outPacket.encodeInt(0); // nItemId
                        outPacket.encodeInt(0); // nCount
                    }
                }
            } else {
                short size2 = 0;
                outPacket.encodeShort(size2);
                for (int i2 = 0; i2 < size2; i2++) {
                    outPacket.encodeShort(0); // nCategory
                    outPacket.encodeInt(0); // nItemId
                    outPacket.encodeInt(0); // nCount
                }

            }
        }
        if (mask.isInMask(DBChar.ReturnEffectInfo)) {
//            getReturnEffectInfo().encode(outPacket); // ReturnEffectInfo::Decode
            outPacket.encodeByte(0);
        }
        if (mask.isInMask(DBChar.DressUpInfo)) {
            new DressUpInfo().encode(outPacket); // GW_DressUpInfo::Decode
        }
        if (mask.isInMask(DBChar.Unk2)) {
            outPacket.encodeInt(1);
            outPacket.encodeInt(0);
            outPacket.encodeLong(0);
            outPacket.encodeString("");
            outPacket.encodeInt(0);
        }
        if (mask.isInMask(DBChar.CoreInfo)) {
            // GW_Core
            short size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeShort(0); // nPos
                outPacket.encodeInt(0); // nCoreID
                outPacket.encodeInt(0); // nLeftCount
            }

            size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeShort(0); // nPos
                outPacket.encodeInt(0); // nCoreID
                outPacket.encodeInt(0); // nLeftCount
            }
        }
        if (mask.isInMask(DBChar.FarmPotential)) {
            new FarmPotential().encode(outPacket); // FARM_POTENTIAL::Decode
        }
        if (mask.isInMask(DBChar.FarmUserInfo)) {
            new FarmUserInfo().encode(outPacket); // FarmUserInfo::Decode
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
        }
        if (mask.isInMask(DBChar.MemorialCubeInfo)) {
            new MemorialCubeInfo().encode(outPacket); // MemorialCubeInfo::Decode
        }
        if (mask.isInMask(DBChar.LikePoint)) {
            new LikePoint().encode(outPacket);
        }
        if (mask.isInMask(DBChar.RunnerGameRecord)) {
            new RunnerGameRecord().encode(outPacket);
        }
        short sizeO = 0;
        outPacket.encodeShort(sizeO);
        for (int i = 0; i < sizeO; i++) {
            outPacket.encodeInt(0);
            outPacket.encodeString("");

        }
        if (mask.isInMask(DBChar.Unk2)) { // Mushy: Monster Collection
            int size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0); // same as above?
                outPacket.encodeString("");
            }
        }
        boolean farmOnline = false;
        outPacket.encodeByte(farmOnline);
        int sizeInt = 0;
        // CharacterData::DecodeTextEquipInfo
        outPacket.encodeInt(sizeInt);
        for (int i = 0; i < sizeInt; i++) {
            outPacket.encodeInt(0);
            outPacket.encodeString("");
        }

        if (mask.isInMask(DBChar.VisitorLog4)) {
            // mushy
            outPacket.encodeByte(1);
            outPacket.encodeByte(0);
            outPacket.encodeInt(1);
            outPacket.encodeInt(0);
            outPacket.encodeInt(100);
            outPacket.encodeLong(FileTime.getFileTimeFromType(FileTime.Type.MAX_TIME));
            outPacket.encodeShort(0);
            outPacket.encodeShort(0);
        }

        if (mask.isInMask(DBChar.Unk4)) {
            outPacket.encodeByte(0);
        }

        if (mask.isInMask(DBChar.Unk)) {
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
        }

        if (mask.isInMask(DBChar.CoreAura)) {
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);

            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);

            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);

            outPacket.encodeLong(0);
            outPacket.encodeByte(0);

            outPacket.encodeByte(1);
        }

        if (mask.isInMask(DBChar.EquipExt)) {
            short size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeShort(0);
                outPacket.encodeShort(0);
            }
        }

        if (mask.isInMask(DBChar.RedLeafInfo)) {
            // red leaf information
            outPacket.encodeInt(getAccId());
            outPacket.encodeInt(getId());
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
        }
        outPacket.encodeBytes(new byte[32]); // real

    }

    private String getBlessingOfEmpress() {
        return null;
    }

    private String getBlessingOfFairy() {
        return null;
    }

    public void setCombatOrders(int combatOrders) {
        this.combatOrders = combatOrders;
    }

    public int getCombatOrders() {
        return combatOrders;
    }

    public List<Skill> getStolenSkills() {
        return stolenSkills;
    }

    public List<Skill> getChosenSkills() {
        return chosenSkills;
    }

    public void setChosenSkills(List<Skill> chosenSkills) {
        this.chosenSkills = chosenSkills;
    }

    public QuestManager getQuestManager() {
        return questManager;
    }

    public void setQuests(QuestManager questManager) {
        this.questManager = questManager;
    }

    public List<ItemPot> getItemPots() {
        return null;
    }

    public void setItemPots(List<ItemPot> itemPots) {
        this.itemPots = itemPots;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<FriendRecord> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendRecord> friends) {
        this.friends = friends;
    }

    public long getMoney() {
        return getAvatarData().getCharacterStat().getMoney();
    }

    public List<ExpConsumeItem> getExpConsumeItems() {
        return expConsumeItems;
    }

    public void setExpConsumeItems(List<ExpConsumeItem> expConsumeItems) {
        this.expConsumeItems = expConsumeItems;
    }

    public List<MonsterBattleMobInfo> getMonsterBattleMobInfos() {
        return monsterBattleMobInfos;
    }

    public void setMonsterBattleMobInfos(List<MonsterBattleMobInfo> monsterBattleMobInfos) {
        this.monsterBattleMobInfos = monsterBattleMobInfos;
    }

    public MonsterBattleLadder getMonsterBattleLadder() {
        return monsterBattleLadder;
    }

    public void setMonsterBattleLadder(MonsterBattleLadder monsterBattleLadder) {
        this.monsterBattleLadder = monsterBattleLadder;
    }

    public MonsterBattleRankInfo getMonsterBattleRankInfo() {
        return monsterBattleRankInfo;
    }

    public void setMonsterBattleRankInfo(MonsterBattleRankInfo monsterBattleRankInfo) {
        this.monsterBattleRankInfo = monsterBattleRankInfo;
    }

    public List<Inventory> getInventories() {
        return new ArrayList<>(Arrays.asList(getEquippedInventory(), getEquipInventory(),
                getConsumeInventory(), getEtcInventory(), getInstallInventory(), getCashInventory()));
    }

    public Inventory getInventoryByType(InvType invType) {
        switch (invType) {
            case EQUIPPED:
                return getEquippedInventory();
            case EQUIP:
                return getEquipInventory();
            case CONSUME:
                return getConsumeInventory();
            case ETC:
                return getEtcInventory();
            case INSTALL:
                return getInstallInventory();
            case CASH:
                return getCashInventory();
            default:
                return null;
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getFieldID() {
        return (int) getAvatarData().getCharacterStat().getPosMap();
    }

    private void setFieldID(long fieldId) {
        setFieldID((int) fieldId);
    }

    private void setFieldID(int fieldID) {
        getAvatarData().getCharacterStat().setPosMap(fieldID);
    }

    public boolean hasBuffProtector() {
        // TODO
        return false;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setField(Field field) {
        this.field = field;
        setFieldID(field.getId());
    }

    public Field getField() {
        return field;
    }

    /**
     * Sets the job of this Char with a given id. Does nothing if the id is invalid.
     * If it is valid, will set this Char's job, add all Skills that the job should have by default, and sends the info to the client.
     * @param id
     */
    public void setJob(int id) {
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById((short) id);
        if (job == null) {
            return;
        }
        setJobHandler(JobManager.getJobById(getJob(), this));
        getAvatarData().getCharacterStat().setJob(id);
        List<Skill> skills = SkillData.getSkillsByJob((short) id);
        skills.forEach(this::addSkill);
        getClient().write(WvsContext.changeSkillRecordResult(skills, true, false, false, false));
    }

    public short getJob() {
        return getAvatarData().getCharacterStat().getJob();
    }

    /**
     * Sets the SP to the current job level.
     * @param num The new SP amount.
     */
    public void setSpToCurrentJob(int num) {
        if (JobConstants.isExtendSpJob(getJob())) {
            byte jobLevel = (byte) JobConstants.getJobLevel(getJob());
            getAvatarData().getCharacterStat().getExtendSP().setSpToJobLevel(jobLevel, num);
        } else {
            getAvatarData().getCharacterStat().setSp(num);
        }
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    /**
     * Adds a {@link Skill} to this Char. Overrides the old Skill if the Char already had a Skill with the same id.
     * @param skill The Skill this Char should get.
     */
    public void addSkill(Skill skill) {
        skill.setCharId(getId());
        if (getSkills().stream().noneMatch(s -> s.getSkillId() == skill.getSkillId())) {
            getSkills().add(skill);
        } else {
            Skill oldSkill = getSkill(skill.getSkillId());
            oldSkill.setCurrentLevel(skill.getCurrentLevel());
            oldSkill.setMasterLevel(skill.getMasterLevel());
        }
    }

    /**
     * Returns whether or not this Char has a {@link Skill} with a given id.
     * @param id The id of the Skill.
     * @return Whether or not this Char has a Skill with the given id.
     */
    public boolean hasSkill(int id) {
        return getSkills().stream().anyMatch(s -> s.getSkillId() == id) && getSkill(id, false).getCurrentLevel() > 0;
    }

    /**
     * Gets a {@link Skill} of this Char with a given id.
     * @param id The id of the requested Skill.
     * @return The Skill corresponding to the given id of this Char, or null if there is none.
     */
    public Skill getSkill(int id) {
        return getSkill(id, false);
    }

    /**
     * Gets a {@link Skill} with a given ID. If <code>createIfNull</code> is true, creates the Skill if it doesn't exist yet.
     * If it is false, will return null if this Char does not have the given Skill.
     * @param id The id of the requested Skill.
     * @param createIfNull Whether or not this method should create the Skill if it doesn't exist.
     * @return The Skill that the Char has, or <code>null</code> if there is no such skill and <code>createIfNull</code> is false.
     */
    public Skill getSkill(int id, boolean createIfNull) {
        for (Skill s : getSkills()) {
            if (s.getSkillId() == id) {
                return s;
            }
        }
        return createIfNull ? createAndReturnSkill(id) : null;
    }

    /**
     * Creates a new {@link Skill} for this Char.
     * @param id The skillID of the Skill to be created.
     * @return The new Skill.
     */
    private Skill createAndReturnSkill(int id) {
        Skill skill = SkillData.getSkillDeepCopyById(id);
        addSkill(skill);
        return skill;
    }

    public void setStat(Stat charStat, int amount) {
        switch (charStat) {
            case str:
                getAvatarData().getCharacterStat().setStr(amount);
                break;
            case dex:
                getAvatarData().getCharacterStat().setDex(amount);
                break;
            case inte:
                getAvatarData().getCharacterStat().setInt(amount);
                break;
            case luk:
                getAvatarData().getCharacterStat().setLuk(amount);
                break;
            case hp:
                getAvatarData().getCharacterStat().setHp(amount);
                break;
            case mhp:
                getAvatarData().getCharacterStat().setMaxHp(amount);
                break;
            case mp:
                getAvatarData().getCharacterStat().setMp(amount);
                break;
            case mmp:
                getAvatarData().getCharacterStat().setMaxMp(amount);
                break;
            case ap:
                getAvatarData().getCharacterStat().setAp(amount);
                break;
            case level:
                getAvatarData().getCharacterStat().setLevel(amount);
                break;
        }
    }

    public int getStat(Stat charStat) {
        CharacterStat cs = getAvatarData().getCharacterStat();
        switch (charStat) {
            case str:
                return cs.getStr();
            case dex:
                return cs.getDex();
            case inte:
                return cs.getInt();
            case luk:
                return cs.getLuk();
            case hp:
                return cs.getHp();
            case mhp:
                return cs.getMaxHp();
            case mp:
                return cs.getMp();
            case mmp:
                return cs.getMaxMp();
            case ap:
                return cs.getAp();
            case level:
                return cs.getLevel();
        }
        return -1;
    }

    public void addStat(Stat charStat, int amount) {
        setStat(charStat, getStat(charStat) + amount);
    }

    /**
     * Adds a certain amount of money to the current character. Also sends the packet to update the client's state.
     *
     * @param amount The amount of money to add. May be negative.
     */
    public void addMoney(long amount) {
        CharacterStat cs = getAvatarData().getCharacterStat();
        long money = cs.getMoney();
        long newMoney = money + amount;
        if (newMoney >= 0) {
            newMoney = Math.min(GameConstants.MAX_MONEY, newMoney);
            Map<Stat, Object> stats = new HashMap<>();
            cs.setMoney(newMoney);
            stats.put(Stat.money, newMoney);
            write(WvsContext.statChanged(stats));
        }
    }

    /**
     * The same as addMoney, but negates the amount.
     *
     * @param amount The money to deduct. May be negative.
     */
    public void deductMoney(long amount) {
        addMoney(-amount);
    }

    public Position getOldPosition() {
        return oldPosition;
    }

    public void setOldPosition(Position oldPosition) {
        this.oldPosition = oldPosition;
    }

    public void setMoveAction(byte moveAction) {
        this.moveAction = moveAction;
    }

    public byte getMoveAction() {
        return moveAction;
    }

    /**
     * Sends a message to this Char with a given {@link ChatMsgColour colour}.
     * @param clr The Colour this message should be in.
     * @param msg The message to display.
     */
    public void chatMessage(ChatMsgColour clr, String msg) {
        getClient().write(UserLocal.chatMsg(clr, msg));
    }

    /**
     * Unequips an {@link Item}. Ensures that the hairEquips and both inventories get updated.
     * @param item The Item to equip.
     */
    public void unequip(Item item) {
        getInventoryByType(EQUIPPED).removeItem(item);
        getInventoryByType(EQUIP).addItem(item);
        List<Integer> hairEquips = getAvatarData().getAvatarLook().getHairEquips();
        if (hairEquips.contains(item.getItemId())) {
            hairEquips.remove((Integer) item.getItemId());
        }
    }

    /**
     * Equips an {@link Item}. Ensures that the hairEquips and both inventories get updated.
     * @param item The Item to equip.
     */
    public void equip(Item item) {
        getInventoryByType(EQUIP).removeItem(item);
        getInventoryByType(EQUIPPED).addItem(item);
        List<Integer> hairEquips = getAvatarData().getAvatarLook().getHairEquips();
        if (!hairEquips.contains(item.getItemId())) {
            hairEquips.add(item.getItemId());
        }
    }

    public TemporaryStatManager getTemporaryStatManager() {
        return temporaryStatManager;
    }

    public void setTemporaryStatManager(TemporaryStatManager temporaryStatManager) {
        this.temporaryStatManager = temporaryStatManager;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJobHandler(Job jobHandler) {
        this.jobHandler = jobHandler;
    }

    public Job getJobHandler() {
        return jobHandler;
    }

    public FuncKeyMap getFuncKeyMap() {
        return funcKeyMap;
    }

    public void setFuncKeyMap(FuncKeyMap funcKeyMap) {
        this.funcKeyMap = funcKeyMap;
    }

    /**
     * Creates a {@link Rect} with regard to this character. Adds all values to this Char's position.
     * @param rect The rectangle to use.
     * @return The new rectangle.
     */
    public Rect getRectAround(Rect rect) {
        int x = getPosition().getX();
        int y = getPosition().getY();
        return new Rect(x + rect.getLeft(), y + rect.getTop(), x + rect.getRight(), y + rect.getBottom());
    }

    /**
     * Returns the Equip equipped at a certain {@link BodyPart}.
     * @param bodyPart The requested bodyPart.
     * @return The Equip corresponding to <code>bodyPart</code>. Null if there is none.
     */
    public Item getEquippedItemByBodyPart(BodyPart bodyPart) {
        List<Item> items = getEquippedInventory().getItemsByBodyPart(bodyPart);
        return items.size() > 0 ? items.get(0) : null;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isLeft() {
        return left;
    }

    public MarriageRecord getMarriageRecord() {
        return marriageRecord;
    }

    public void setMarriageRecord(MarriageRecord marriageRecord) {
        this.marriageRecord = marriageRecord;
    }

    /**
     * Warps this character to a given field, at the starting position.
     * See {@link #warp(Field, Portal) warp}.
     * @param toField The field to warp to.
     */
    public void warp(Field toField) {
        warp(toField, toField.getPortalByName("sp"));
    }

    /**
     * Warps this character to a given field, at a given portal.
     * Ensures that the previous map does not contain this Char anymore, and that the new field does.
     * Ensures that all Lifes are immediately spawned for the new player.
     * @param toField
     * @param portal
     */
    public void warp(Field toField, Portal portal) {
        TemporaryStatManager tsm = getTemporaryStatManager();
        for(AffectedArea aa : tsm.getAffectedAreas()) {
            tsm.removeStatsBySkill(aa.getSkillID());
        }
        getField().removeChar(this);
        setField(toField);
        field.removeChar(this);
        toField.addChar(this);
        getClient().write(Stage.setField(this, toField, getClient().getChannel(), false, 0, false, hasBuffProtector(),
                (byte) portal.getId(), false, 100, null, false, -1));
        toField.spawnLifesForChar(this);
    }

    /**
     * Adds a given amount of exp to this Char. Immediately checks for level-up possibility, and sends the updated
     * stats to the client. Allows multi-leveling.
     * @param amount The amount of exp to add.
     */
    public void addExp(long amount) {
        CharacterStat cs = getAvatarData().getCharacterStat();
        long curExp = cs.getExp();
        int level = getStat(Stat.level);
        if(level >= GameConstants.charExp.length - 1) {
            return;
        }
        long newExp = curExp + amount;
        Map<Stat, Object> stats = new HashMap<>();
        while (newExp > GameConstants.charExp[level]) {
            newExp -= GameConstants.charExp[level];
            addStat(Stat.level, 1);
            stats.put(Stat.level, (byte) getStat(Stat.level));
            getJobHandler().handleLevelUp();
            level++;
        }
        cs.setExp(newExp);
        stats.put(Stat.exp, newExp);

        getClient().write(WvsContext.statChanged(stats));
    }

    /**
     * Writes a packet to this Char's client.
     * @param outPacket The OutPacket to write.
     */
    public void write(OutPacket outPacket) {
        if(getClient() != null) {
            getClient().write(outPacket);
        }
    }

    public ExpIncreaseInfo getExpIncreaseInfo() {
        return new ExpIncreaseInfo();
    }

    public WildHunterInfo getWildHunterInfo() {
        return wildHunterInfo;
    }


    public void setWildHunterInfo(WildHunterInfo wildHunterInfo) {
        this.wildHunterInfo = wildHunterInfo;
    }

    public ZeroInfo getZeroInfo() {
        return zeroInfo;
    }

    public void setZeroInfo(ZeroInfo zeroInfo) {
        this.zeroInfo = zeroInfo;
    }

    public int getNickItem() {
        return nickItem;
    }

    public void setNickItem(int nickItem) {
        this.nickItem = nickItem;
    }

    public void setDamageSkin(int damageSkin) {
        this.damageSkin = damageSkin;
    }

    public int getDamageSkin() {
        return damageSkin;
    }

    public int getPremiumDamageSkin() {
        return premiumDamageSkin;
    }

    public void setPremiumDamageSkin(int premiumDamageSkin) {
        this.premiumDamageSkin = premiumDamageSkin;
    }

    public void setPartyInvitable(boolean partyInvitable) {
        this.partyInvitable = partyInvitable;
    }

    /**
     * Returns if this Char can be invited to a party.
     * @return Whether or not this Char can be invited to a party.
     */
    public boolean isPartyInvitable() {
        return partyInvitable;
    }

    /**
     * Returns if this character is currently in its beta state.
     * @return true if this Char is in a beta state.
     */
    public boolean isZeroBeta() {
        return getZeroInfo() != null && getZeroInfo().isZeroBetaState();
    }

    /**
     * Zero only.
     * Goes into Beta form if Alpha, and into Alpha if Beta.
     */
    public void swapZeroState() {
        if(!(JobConstants.isZero(getJob())) || getZeroInfo() == null) {
            return;
        }
        ZeroInfo oldInfo = getZeroInfo().deepCopy();
        ZeroInfo currentInfo = getZeroInfo();
        CharacterStat cs = getAvatarData().getCharacterStat();
        currentInfo.setZeroBetaState(!oldInfo.isZeroBetaState());
        currentInfo.setSubHP(cs.getHp());
        currentInfo.setSubMHP(cs.getMaxHp());
        currentInfo.setSubMP(cs.getMp());
        currentInfo.setSubMMP(cs.getMaxMp());
        cs.setHp(oldInfo.getSubHP());
        cs.setMaxHp(oldInfo.getSubMHP());
        cs.setMp(oldInfo.getSubMP());
        cs.setMaxMp(oldInfo.getSubMMP());
        Map<Stat, Object> updatedStats = new HashMap<>();
        updatedStats.put(Stat.hp, cs.getHp());
        updatedStats.put(Stat.mhp, cs.getHp());
        updatedStats.put(Stat.mp, cs.getHp());
        updatedStats.put(Stat.mmp, cs.getHp());
        System.out.println("Old = " + oldInfo.isZeroBetaState());
        System.out.println("New = " + currentInfo.isZeroBetaState());
        write(WvsContext.statChanged(updatedStats));
//        write(WvsContext.zeroInfo(currentInfo));
    }

    /**
     * Initializes zero info with HP values.
     */
    public void initZeroInfo() {
        ZeroInfo zeroInfo = new ZeroInfo();
        CharacterStat cs = getAvatarData().getCharacterStat();
        zeroInfo.setSubHP(cs.getHp());
        zeroInfo.setSubMHP(cs.getMaxHp());
        zeroInfo.setSubMP(cs.getMp());
        zeroInfo.setSubMMP(cs.getMaxMp());
        setZeroInfo(zeroInfo);
    }

    public ScriptManager getScriptManager() {
        return scriptManager;
    }

    /**
     * Adds a {@link Drop} to this Char.
     * @param drop The Drop that has been picked up.
     */
    public void addDrop(Drop drop) {
        if(drop.isMoney()) {
            addMoney(drop.getMoney());
            write(WvsContext.dropPickupMessage(drop.getMoney(), (short) 0, (short) 0));
        } else {
            Item item = drop.getItem();
            addItemToInventoryAndUpdateClient(item);
            write(WvsContext.dropPickupMessage(item, (short) item.getQuantity()));
        }
    }

    /**
     * Returns the Char's name.
     * @return The Char's name.
     */
    public String getName() {
        return getAvatarData().getCharacterStat().getName();
    }

    /**
     * Checks whether or not this Char has a given quest in progress.
     * @param questReq The quest ID of the requested quest.
     * @return Whether or not this char is in progress with the quest.
     */
    public boolean hasQuestInProgress(int questReq) {
        return getQuestManager().hasQuestInProgress(questReq);
    }

    /**
     * Disposes this Char, allowing it to send packets to the server again.
     */
    public void dispose() {
        write(WvsContext.exclRequest());
    }

    /**
     * Returns the current HP of this Char.
     * @return the current HP of this Char.
     */
    public int getHP() {
        return getStat(Stat.hp);
    }

    /**
     * Returns the current MP of this Char.
     * @return the current MP of this Char.
     */
    public int getMP() {
        return getStat(Stat.mp);
    }

    /**
     * Gets the max hp of this Char. TODO: factor in skills, items, etc...
     * @return The max hp of this Char
     */
    public int getMaxHP() {
        return getStat(Stat.mhp);
    }

    /**
     * Gets the max mp of this Char. TODO: factor in skills, items, etc...
     * @return The max mp of this Char
     */
    public int getMaxMP() {
        return getStat(Stat.mmp);
    }

    /**
     * Heals this Char's HP for a certain amount. Caps off at maximum HP.
     * @param amount The amount to heal.
     */
    public void heal(int amount) {
        int curHP = getHP();
        int maxHP = getMaxHP();
        int newHP = curHP + amount > maxHP ? maxHP : curHP + amount;
        Map<Stat, Object> stats = new HashMap<>();
        setStat(Stat.hp, newHP);
        stats.put(Stat.hp, newHP);
        write(WvsContext.statChanged(stats));
    }

    /**
     * "Heals" this Char's MP for a certain amount. Caps off at maximum MP.
     * @param amount The amount to heal.
     */
    public void healMP(int amount) {
        int curMP = getMP();
        int maxMP = getMaxMP();
        int newMP = curMP + amount > maxMP ? maxMP : curMP + amount;
        Map<Stat, Object> stats = new HashMap<>();
        setStat(Stat.mp, newMP);
        stats.put(Stat.mp, newMP);
        write(WvsContext.statChanged(stats));
    }

    /**
     * Consumes a single {@link Item} from this Char's {@link Inventory}. Will remove the Item if it has a quantity of 1.
     * @param item The Item to consume.
     */
    public void consumeItem(Item item) {
        Inventory inventory = getInventoryByType(item.getInvType());
        // data race possible
        if(item.getQuantity() <= 1) {
            item.setQuantity(0);
            inventory.removeItem(item);
            write(WvsContext.inventoryOperation(true, false,
                    REMOVE, (short) item.getBagIndex(), (byte) -1, 0, item));
        } else {
            item.setQuantity(item.getQuantity() - 1);
            write(WvsContext.inventoryOperation(true, false,
                    UPDATE_QUANTITY, (short) item.getBagIndex(), (byte) -1, 0, item));
        }
    }
}
