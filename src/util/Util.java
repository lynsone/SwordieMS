package util;

import io.netty.buffer.ByteBuf;

/**
 * Created on 2/28/2017.
 */
public class Util {
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

    public static String ReadableByteArray(byte[] arr) {
        StringBuilder res = new StringBuilder();
        for(byte b : arr) {
            res.append(String.format("%02X ",b));
        }
        return res.toString();
    }

    public static String ReadableByteArrayFromByteBuf(ByteBuf buf) {
        byte[] bytes = new byte[buf.capacity()];
        for(int i = buf.readableBytes(); i < buf.capacity(); i++) {
            bytes[i] = buf.getByte(i);
        }
        return Util.ReadableByteArray(bytes);
    }

    public static byte[] IntToByteArrayLE(int n) {
        byte[] res = new byte[Integer.BYTES];
        res[0] = (byte) n;
        res[1] = (byte) (n >>> 8);
        res[2] = (byte) (n >>> 16);
        res[3] = (byte) (n >>> 24);
        return res;
    }
}
