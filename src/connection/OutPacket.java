package connection;

import handling.OutHeader;
import net.Packet;
import util.FileTime;
import util.Position;
import util.Util;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class OutPacket extends Packet {
    private ByteArrayOutputStream baos;
    private boolean loopback = false;
    private boolean encryptedByShanda = false;
    private short op;

    public OutPacket(short op) {
        super(new byte[]{});
        baos = new ByteArrayOutputStream();
        encodeShort(op);
        this.op = op;
    }

    public OutPacket(int op) {
        this((short) op);
    }

    public OutPacket() {
        super(new byte[]{});
        baos = new ByteArrayOutputStream();
    }

    public OutPacket(byte[] data) {
        super(data);
        baos = new ByteArrayOutputStream();
        encodeBytes(data);
    }

    public OutPacket(OutHeader header) {
        this(header.getValue());
    }

    @Override
    public int getHeader() {
        return op;
    }

    public void encodeByte(int b) {
        encodeByte((byte) b);
    }

    /**
     * Encodes a byte to this OutPacket.
     * @param b The byte to encode.
     */
    public void encodeByte(byte b) {
        baos.write(b);
    }

    /**
     * Encodes a byte array to this OutPacket.
     * @param bArr The byte array to encode.
     */
    public void encodeBytes(byte[] bArr) {
        for (byte b : bArr) {
            baos.write(b);
        }
    }

    /**
     * Encodes a character to this OutPacket.
     * @param c The character to encode
     */
    public void encodeChar(char c) {
        baos.write(c);
    }

    /**
     * Encodes a boolean to this OutPacket.
     * @param b The boolean to encode (0/1)
     */
    public void encodeByte(boolean b) {
        baos.write(b ? 1 : 0);
    }

    /**
     * Encodes a short to this OutPacket, in little endian.
     * @param s The short to encode.
     */
    public void encodeShort(short s) {
        baos.write((byte) s);
        baos.write((byte) (s >>> 8));
    }

    public void encodeShortBE(short s) {
        baos.write(s);
    }

    public void encodeIntBE(int i) {
        baos.write((byte) (i >>> 24));
        baos.write((byte) (i >>> 16));
        baos.write((byte) (i >>> 8));
        baos.write((byte) i);
    }

    /**
     * Encodes an integer to this OutPacket, in little endian.
     * @param i The integer to encode.
     */
    public void encodeInt(int i) {
        baos.write((byte) i);
        baos.write((byte) (i >>> 8));
        baos.write((byte) (i >>> 16));
        baos.write((byte) (i >>> 24));
    }

    /**
     * Encodes a long to this OutPacket, in little endian.
     * @param l The long to encode.
     */
    public void encodeLong(long l) {
        baos.write((byte) l);
        baos.write((byte) (l >>> 8));
        baos.write((byte) (l >>> 16));
        baos.write((byte) (l >>> 24));
        baos.write((byte) (l >>> 32));
        baos.write((byte) (l >>> 40));
        baos.write((byte) (l >>> 48));
        baos.write((byte) (l >>> 56));
    }

    /**
     * Encodes a FileTime to this OutPacket.
     * @param fileTime The FileTime to encode.
     */
    public void encodeLong(FileTime fileTime) {
        encodeFT(fileTime);
    }

    /**
     * Encodes a String to this OutPacket.
     * Structure: short(size) + char array of <code>s</code>.
     * @param s The String to encode.
     */
    public void encodeString(String s) {
        if(s == null) {
            s = "";
        }
        if(s.length() > Short.MAX_VALUE) {
            System.err.println("Tried to encode a string that is too big.");
            return;
        }
        encodeShort((short) s.length());
        encodeString(s, (short) s.length());
    }

    /**
     * Writes a String as a character array to this OutPacket.
     * If <code>s.length()</code> is smaller than length, the open spots are filled in with zeros.
     * @param s The String to encode.
     * @param length The maximum length of the buffer.
     */
    public void encodeString(String s, short length) {
        if(s == null) {
            s = "";
        }
        if(s.length() > 0) {
            for (char c : s.toCharArray()) {
                encodeChar(c);
            }
        }
        for(int i = s.length(); i < length; i++) {
            encodeByte((byte) 0);
        }
    }

    @Override
    public void setData(byte[] nD) {
        super.setData(nD);
        baos.reset();
        encodeBytes(nD);
    }

    @Override
    public byte[] getData() {
        return baos.toByteArray();
    }

    @Override
    public Packet clone() {
        return new OutPacket(getData());
    }

    /**
     * Returns the length of the ByteArrayOutputStream.
     * @return The length of baos.
     */
    @Override
    public int getLength() {
        return baos.size();
    }

    public boolean isLoopback() {
        return loopback;
    }

    public boolean isEncryptedByShanda() {
        return encryptedByShanda;
    }

    @Override
    public String toString() {
        return OutHeader.getOutHeaderByOp(op) + ", " + op + "/0x" + Integer.toHexString(op) + "\t| " + Util.readableByteArray(Arrays.copyOfRange(getData(), 2, getData().length));
    }

    public void encodeShort(int value) {
        encodeShort((short) value);
    }

    public void encodeString(String name, int length) {
        encodeString(name, (short) length);
    }

    public void encodeFT(FileTime fileTime) {
        fileTime.encode(this);
    }

    public void encodePosition(Position position) {
        encodeShort(position.getX());
        encodeShort(position.getY());
    }
}
