package util;

import io.netty.buffer.ByteBuf;
import io.netty.util.internal.StringUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Created on 2/28/2017.
 */
public class Util {
    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static int[] bitwiseOr(int[] arr1, int[] arr2) {
        int[] res = new int[arr1.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = arr1[i] | arr2[i];
        }
        return res;
    }

    public static int getCurrentTime() {
        return (int) System.currentTimeMillis();
    }

    public static long getCurrentTimeLong() {
        return System.currentTimeMillis();
    }

    public static int getRandom(int inclBound) {
        Random random = new Random();
        return random.nextInt(inclBound + 1);
    }

    public static boolean succeedProp(int chance, int max) {
        Random random = new Random();
        return random.nextInt(max) < chance;
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
        s = s.replace("|", " ");
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

    public static String rightPaddedString(int totalLength, char c, String value) {
        totalLength = Math.max(totalLength, value.length());
        char[] chars = new char[totalLength];
        char[] valueChars = value.toCharArray();
        for (int i = 0; i < value.length() ; i++) {
            chars[i] = valueChars[i];
        }
        for(int i = value.length(); i < chars.length; i++) {
            chars[i] = c;
        }
        return new String(chars);
    }

    public static String leftPaddedString(int totalLength, char c, String value) {
        totalLength = Math.max(totalLength, value.length());
        char[] chars = new char[totalLength];
        char[] valueChars = value.toCharArray();
        int pad = totalLength - value.length();
        int i;
        for (i = 0; i < pad ; i++) {
            chars[i] = c;
        }
        int j = 0;
        for(i = pad; i < chars.length; i++) {
            chars[i] = valueChars[j++];
        }
        return new String(chars);
    }
}
