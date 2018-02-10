package client.character.skills;

import connection.OutPacket;

/**
 * Created on 2/3/2018.
 */
public class PartyBooster extends TwoStateTemporaryStat {

    private int currentTime;

    public PartyBooster() {
        super(false);
        currentTime = 0;
        expireTerm = 0;
    }

    @Override
    public int getExpireTerm() {
        return 1000 * expireTerm;
    }

    @Override
    public boolean hasExpired(long tCur) {
        return getExpireTerm() < tCur - getCurrentTime();
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public void reset() {
        super.reset();
        setCurrentTime(0);
    }

    @Override
    public void encode(OutPacket outPacket) {
        super.encode(outPacket);
        outPacket.encodeFT(getCurrentTime());
        outPacket.encodeShort(getExpireTerm());
    }
}
