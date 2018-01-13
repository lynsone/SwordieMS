package util;

import io.netty.buffer.ByteBuf;

import java.io.File;
import java.util.Random;

/**
 * Created on 2/28/2017.
 */
public class Util {

    public static int getCurrentTime() {
        return (int) System.currentTimeMillis();
    }

    public static boolean succeedProp(int chance) {
        Random random = new Random();
        return random.nextInt(100) < chance;
    }

    // https://www.programcreek.com/2014/03/leetcode-reverse-bits-java/
    public static int reverseBits(int n) {
        for (int i = 0; i < 16; i++) {
            n = swapBits(n, i, 32 - i - 1);
        }

        return n;
    }

    private static int swapBits(int n, int i, int j) {
        int a = (n >> i) & 1;
        int b = (n >> j) & 1;

        if ((a ^ b) != 0) {
            n ^= (1 << i) | (1 << j);
        }
        return n;
    }

    public static boolean isNumber(String string) {
        return string.matches("-?\\d+(\\.\\d+)?");
    }

    public static byte[] getByteArrayByString(String s) {
        s = s.replace(" ", "");
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String readableByteArray(byte[] arr) {
        StringBuilder res = new StringBuilder();
        for(byte b : arr) {
            res.append(String.format("%02X ",b));
        }
        return res.toString();
    }

    public static String readableByteArrayFromByteBuf(ByteBuf buf) {
        byte[] bytes = new byte[buf.capacity()];
        for(int i = buf.readableBytes(); i < buf.capacity(); i++) {
            bytes[i] = buf.getByte(i);
        }
        return Util.readableByteArray(bytes);
    }

    public static byte[] IntToByteArrayLE(int n) {
        byte[] res = new byte[Integer.BYTES];
        res[0] = (byte) n;
        res[1] = (byte) (n >>> 8);
        res[2] = (byte) (n >>> 16);
        res[3] = (byte) (n >>> 24);
        return res;
    }

    public static void makeDirIfAbsent(String dir) {
        File file = new File(dir);
        if(!file.exists()) {
            file.mkdir();
        }
    }
}
