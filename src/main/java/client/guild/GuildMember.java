package client.guild;

import client.character.Char;
import connection.OutPacket;
import util.FileTime;

import javax.persistence.*;

@Entity
@Table(name = "guildmembers")
public class GuildMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "charID")
    private int charID;
    @Transient
    private Char chr;
    @Column(name = "grade")
    private int grade;
    @Column(name = "allianceGrade")
    private int allianceGrade;
    @Column(name = "commitment")
    private int commitment;
    @Column(name = "dayCommitment")
    private int dayCommitment;
    @Column(name = "igp")
    private int igp;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "commitmentIncTime")
    private FileTime commitmentIncTime;
    private String name;
    private int job;
    private int level;
    @Column(name = "loggedIn")
    private boolean online;

    public GuildMember() {
    }

    public GuildMember(Char chr) {
        this.chr = chr;
        updateInfoFromChar(chr);
    }

    public void updateInfoFromChar(Char chr) {
        setName(chr.getName());
        setCharID(chr.getId());
        setJob(chr.getJob());
        setLevel(chr.getLevel());
        setOnline(chr.isOnline());
    }

    public Char getChr() {
        return chr;
    }

    public void setChr(Char chr) {
        this.chr = chr;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getAllianceGrade() {
        return allianceGrade;
    }

    public void setAllianceGrade(int allianceGrade) {
        this.allianceGrade = allianceGrade;
    }

    public int getCommitment() {
        return commitment;
    }

    public void setCommitment(int commitment) {
        this.commitment = commitment;
    }

    public int getDayCommitment() {
        return dayCommitment;
    }

    public void setDayCommitment(int dayCommitment) {
        this.dayCommitment = dayCommitment;
    }

    public int getIgp() {
        return igp;
    }

    public void setIgp(int igp) {
        this.igp = igp;
    }

    public FileTime getCommitmentIncTime() {
        return commitmentIncTime;
    }

    public void setCommitmentIncTime(FileTime commitmentIncTime) {
        this.commitmentIncTime = commitmentIncTime;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeString(getName(), 13);
        outPacket.encodeInt(getJob());
        outPacket.encodeInt(getLevel());
        outPacket.encodeInt(getGrade());
        outPacket.encodeInt(isOnline() ? 1 : 0);
        outPacket.encodeInt(getAllianceGrade());
        outPacket.encodeInt(getCommitment());
        outPacket.encodeInt(getDayCommitment());
        outPacket.encodeInt(getIgp());
        outPacket.encodeFT(getCommitmentIncTime());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof GuildMember && ((GuildMember) obj).getChr().equals(getChr());
    }

    public int getCharID() {
        return charID;
    }

    public void setCharID(int charID) {
        this.charID = charID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
