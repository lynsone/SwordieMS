package client.character.quest.QuestProgressRequirement;

import loaders.DatSerializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created on 3/2/2018.
 */
@Entity
@Table(name = "questProgressMoneyRequirements")
public class QuestProgressMoneyRequirement extends QuestProgressRequirement {

    @Column(name = "money")
    private int money;
    @Column(name = "curMoney")
    private int curMoney;

    public QuestProgressMoneyRequirement() {
    }

    public QuestProgressMoneyRequirement(int money){
        this.money = money;
    }

    @Override
    public boolean isComplete() {
        return getCurMoney() >= getMoney();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCurMoney() {
        return curMoney;
    }

    public void setCurMoney(int curMoney) {
        this.curMoney = curMoney;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(getMoney());
    }

    @Override
    public DatSerializable load(DataInputStream dis) throws IOException {
        return new QuestProgressMoneyRequirement(dis.readInt());
    }
}
