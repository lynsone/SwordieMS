package client.character.quest.QuestProgressRequirement;

import loaders.DatSerializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created on 3/2/2018.
 */
public class QuestProgressMoneyRequirement implements QuestProgressRequirement {

    private int money;
    private int curMoney;

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
