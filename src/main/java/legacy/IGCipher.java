package legacy;

import net.crypto.MapleCrypto;

/**
 * Created by Tim on 3/1/2017.
 */
public class IGCipher {


    public static int innoHash(int src, int len, int key) {
        int defaultKey = -967814158;
        if(key == 0) {
            key = defaultKey;
        }
        for(int i = 0; i < len; i++) {
            key = morphKey(key, (byte) ((src >>> i*Byte.SIZE) & 0xFF));
        }
        return key;
    }

    private static int morphKey(int key, byte toHash) {
        byte[] tempKey = new byte[] {(byte)(key & 0xFF), (byte)((key >>> 8) & 0xFF), (byte)((key >>> 16) & 0xFF), (byte)((key >>> 24) & 0xFF)};
        int[] shuffleKey = MapleCrypto.SHUFFLE_BYTES;
        tempKey[0] += shuffleKey[(int) tempKey[1] & 0xFF] - toHash;
        tempKey[1] -= (byte)((tempKey[2] ^ shuffleKey[(int) toHash & 0xFF]) & 0xFF);
        tempKey[2] ^= (byte)(toHash + shuffleKey[(int) tempKey[3] & 0xFF]);
        tempKey[3] = (byte)((shuffleKey[(int) toHash & 0xFF] & 0xFF) + (byte)(tempKey[3] - (int) tempKey[0] & 0xFF));

        int nKey = ((int) tempKey[0]) & 0xFF | (tempKey[1] << 8) & 0xFF00 | (tempKey[2] << 16) & 0xFF0000 | (tempKey[3] << 24) & 0xFF000000;
        return (8 * nKey | nKey >>> 0x1D);
    }
}
