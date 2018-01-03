package client.character.skills;

/**
 * Created on 1/2/2018.
 */
public class CharacterTemporaryStat {

    private int val;
    private int pos;
    public static final int length = 15;

    public CharacterTemporaryStat(int val, int pos) {
        this.val = val;
        this.pos = pos;
    }

    public boolean isEncodeInt() {
        /*
        v2 = get_CTS_from_RidingTS(&result);
    v3 = CFlag<480>::operator|(v2, &v35, &CTS_CarnivalDefence);
    v4 = CFlag<480>::operator|(v3, &v34, &CTS_SpiritLink);
    v5 = CFlag<480>::operator|(v4, &v33, &CTS_DojangLuckyBonus);
    v6 = CFlag<480>::operator|(v5, &v32, &CTS_SoulGazeCriDamR);
    v7 = CFlag<480>::operator|(v6, &v31, &CTS_PowerTransferGauge);
    v8 = CFlag<480>::operator|(v7, &v30, &CTS_ReturnTeleport);
    v9 = CFlag<480>::operator|(v8, &v29, &CTS_ShadowPartner);
    v10 = CFlag<480>::operator|(v9, &v28, &CTS_SetBaseDamage);
    v11 = CFlag<480>::operator|(v10, &v27, &CTS_QuiverCatridge);
    v12 = CFlag<480>::operator|(v11, &v26, &CTS_ImmuneBarrier);
    v13 = CFlag<480>::operator|(v12, &v25, &CTS_NaviFlying);
    v14 = CFlag<480>::operator|(v13, &v24, &CTS_Dance);
    v15 = CFlag<480>::operator|(v14, &v23, &CTS_SetBaseDamageByBuff);
    v16 = CFlag<480>::operator|(v15, &v22, &CTS_DotHealHPPerSecond);
    v17 = CFlag<480>::operator|(v16, &v21, &CTS_MagnetArea);
         */
        return false;
    }
}
