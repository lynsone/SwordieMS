package util;

import io.netty.buffer.ByteBuf;
import io.netty.util.internal.StringUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created on 2/28/2017.
 */
public class Util {

    /**
     * Gets a random element from a given List. This is done by utilizing {@link #getRandom(int)}.
     * @param list The list to select the element from
     * @param <T> The type of elements of the list
     * @return A random element from the list, or null if the list is null or empty.
     */
    public static <T> T getRandomFromList(List<T> list) {
        if(list != null && list.size() > 0) {
            return list.get(getRandom(list.size() - 1));
        }
        return null;
    }

    /**
     * Gets a random element from a given List. This is done by utilizing {@link #getRandom(int)}.
     * @param list The list to select the element from
     * @param <T> The type of elements of the list
     * @return A random element from the list, or null if the list is null or empty.
     */
    public static <T> T getRandomFromList(T[] list) {
        if(list != null && list.length > 0) {
            return list[getRandom(list.length - 1)];
        }
        return null;
    }

    /**
     * Reads a file and returns the contents as a single String.
     * @param path The path to the file
     * @param encoding The encoding the file is in.
     * @return The contents of the File as a single String.
     * @throws IOException If the file cannot be found (usually)
     */
    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    /**
     * Returns a bitwise OR of two arrays. Takes the length of arr1 as the return array size. If arr2 is smaller,
     * will return an {@link ArrayIndexOutOfBoundsException}.
     * @param arr1 The first array
     * @param arr2 The second array
     * @return The result of using bitwise OR on all contents of arr1 to arr2 such that for all index i with i < arr1.length:
     *  res[i] == arr1[i] | arr2[i]
     */
    public static int[] bitwiseOr(int[] arr1, int[] arr2) {
        int[] res = new int[arr1.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = arr1[i] | arr2[i];
        }
        return res;
    }

    /**
     * Returns the current time as an int. See System.currentTimeMillis().
     * @return the current time as an int.
     */
    public static int getCurrentTime() {
        return (int) System.currentTimeMillis();
    }

    /**
     * Returns the current time. Simply calls System.currentTimeMillis().
     * @return
     */
    public static long getCurrentTimeLong() {
        return System.currentTimeMillis();
    }

    /**
     * Returns a random number from 0 up to (and <b>including</b>) inclBound. Creates a new Random class upon call.
     * @param inclBound the upper bound of the random number
     * @return A random number from 0 up to and including inclBound
     */
    public static int getRandom(int inclBound) {
        return new Random().nextInt(inclBound + 1);
    }

    /**
     * Checks if some action succeeds, given a chance and maximum number.
     * @param chance The threshold at which something is classified as success
     * @param max The maximum number that is generated, exclusive
     * @return Whether or not the test succeeded
     */
    public static boolean succeedProp(int chance, int max) {
        Random random = new Random();
        return random.nextInt(max) < chance;
    }

    /**
     * Checks of some action succeeds, given a chance out of a 100.
     * @param chance The threshold at which something is classified as success
     * @return Whether or not the test succeeded
     */
    public static boolean succeedProp(int chance) {
        return succeedProp(chance, 100);
    }

    // https://www.programcreek.com/2014/03/leetcode-reverse-bits-java/

    /**
     * Reverses all the bits of an integer.
     * @param n The number to reverse the bits of
     * @return The reversed bits
     */
    public static int reverseBits(int n) {
        for (int i = 0; i < 16; i++) {
            n = swapBits(n, i, 32 - i - 1);
        }

        return n;
    }

    /**
     * Swaps two bits of a given number.
     * @param n The number that the bits should be swapped of
     * @param i The first swapping index
     * @param j The second swapping index
     * @return The number with the bits reversed
     */
    private static int swapBits(int n, int i, int j) {
        int a = (n >> i) & 1;
        int b = (n >> j) & 1;

        if ((a ^ b) != 0) {
            n ^= (1 << i) | (1 << j);
        }
        return n;
    }

    /**
     * Checks if a String is a number ((negative) natural or decimal).
     * @param string The String to check
     * @return Whether or not the String is a number
     */
    public static boolean isNumber(String string) {
        return string.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Creates a byte array given a string. Ignores spaces and the '|' character.
     * @param s The String to transform
     * @return The byte array that the String contained (if there is any, some RuntimeException otherwise)
     */
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

    /**
     * Turns a byte array into a readable String (e.g., 3A 00 89 BF).
     * @param arr The array to transform
     * @return The readable byte array
     */
    public static String readableByteArray(byte[] arr) {
        StringBuilder res = new StringBuilder();
        for(byte b : arr) {
            res.append(String.format("%02X ",b));
        }
        return res.toString();
    }

    /**
     * Turns a ByteBuf into a readable String (e.g., 3A 00 89 BF).
     * @param buf The ByteBuf to transform
     * @return The readable byte array
     */
    public static String readableByteArrayFromByteBuf(ByteBuf buf) {
        byte[] bytes = new byte[buf.capacity()];
        for(int i = buf.readableBytes(); i < buf.capacity(); i++) {
            bytes[i] = buf.getByte(i);
        }
        return Util.readableByteArray(bytes);
    }

    /**
     * Transforms an integer into a byte array of length 4, Little Endian.
     * @param n The number to turn into a byte array
     * @return The created byte array (Little Endian)
     */
    public static byte[] IntToByteArrayLE(int n) {
        byte[] res = new byte[Integer.BYTES];
        res[0] = (byte) n;
        res[1] = (byte) (n >>> 8);
        res[2] = (byte) (n >>> 16);
        res[3] = (byte) (n >>> 24);
        return res;
    }

    /**
     * Creates a directory if there is none.
     * @param dir The directory to create
     */
    public static void makeDirIfAbsent(String dir) {
        File file = new File(dir);
        if(!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * Adds right padding given an initial String, padding character and maximum length. If the input String is longer
     * than the given maximum length, the String length is taken instead (effectively doing nothing, as there is
     * nothing to pad.
     * @param totalLength The total length the String should amount to
     * @param c The padding character
     * @param value The initial value of the String
     * @return The right padded String
     */
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

    /**
     * Adds left padding given an initial String, padding character and maximum length. If the input String is longer
     * than the given maximum length, the String length is taken instead (effectively doing nothing, as there is
     * nothing to pad.
     * @param totalLength The total length the String should amount to
     * @param c The padding character
     * @param value The initial value of the String
     * @return The left padded String
     */
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
