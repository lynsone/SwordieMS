package connection;

import io.netty.buffer.*;
import net.Packet;
import util.Util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created on 2/18/2017.
 */
public class InPacket extends Packet {
    private ByteBuf byteBuf;

    public InPacket(ByteBuf byteBuf) {
        super(byteBuf.array());
        this.byteBuf = byteBuf;
    }

    public InPacket(){
        this(Unpooled.buffer());
    }

    public InPacket(byte[] data) {
        this(Unpooled.copiedBuffer(data));
    }

    @Override
    public int getLength() {
        return byteBuf.readableBytes();
    }

    @Override
    public byte[] getData() {
        return byteBuf.array();
    }

    @Override
    public InPacket clone() {
        return new InPacket(byteBuf);
    }

    /**
     * Reads a single byte of the ByteBuf.
     * @return The byte that has been read.
     */
    public byte decodeByte() {
        return byteBuf.readByte();
    }

    /**
     * Reads an <code>amount</code> of bytes from the ByteBuf.
     * @param amount The amount of bytes to read.
     * @return The bytes that have been read.
     */
    public byte[] decodeBytes(int amount) {
        byte[] arr = new byte[amount];
        for(int i = 0; i < amount; i++) {
            arr[i] = byteBuf.readByte();
        }
        return arr;
    }

    /**
     * Reads an integer from the ByteBuf.
     * @return The integer that has been read.
     */
    public int decodeInt() {
        return byteBuf.readIntLE();
    }

    /**
     * Reads a short from the ByteBuf.
     * @return The short that has been read.
     */
    public short decodeShort() {
        return byteBuf.readShortLE();
    }

    /**
     * Reads a char array of a given length of this ByteBuf.
     * @param amount The length of the char array
     * @return The char array as a String
     */
    public String decodeString(int amount) {
        byte[] bytes = decodeBytes(amount);
        char[] chars = new char[amount];
        for(int i = 0; i < amount; i++) {
            chars[i] = (char) bytes[i];
        }
        return String.valueOf(chars);
    }

    /**
     * Reads a String, by first reading a short, then reading a char array of that length.
     * @return The char array as a String
     */
    public String decodeString() {
        int amount = decodeShort();
        return decodeString(amount);
    }

    @Override
    public String toString() {
        return Util.ReadableByteArray(Arrays.copyOfRange(getData(), 2, getData().length));
    }


    public long decodeLong() {
        return byteBuf.readLongLE();
    }
}
