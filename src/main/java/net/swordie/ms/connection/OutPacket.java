package net.swordie.ms.connection;

import net.swordie.ms.handlers.header.OutHeader;
import org.apache.log4j.LogManager;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class OutPacket extends Packet {
    private ByteArrayOutputStream baos;
    private boolean loopback = false;
    private boolean encryptedByShanda = false;
    private short op;
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    /**
     * Creates a new OutPacket with a given op. Immediately encodes the op.
     * @param op The opcode of this OutPacket.
     */
    public OutPacket(short op) {
        super(new byte[]{});
        baos = new ByteArrayOutputStream();
        encodeShort(op);
        this.op = op;
    }

    /**
     * Creates a new OutPacket with a given op. Immediately encodes the op.
     * @param op The opcode of this OutPacket.
     */
    public OutPacket(int op) {
        this((short) op);
    }

    /**
     * Creates a new OutPacket, and initializes the data as empty.
     */
    public OutPacket() {
        super(new byte[]{});
        baos = new ByteArrayOutputStream();
    }

    /**
     * Creates a new OutPacket with given data.
     * @param data The data this net.swordie.ms.connection.packet has to be initialized with.
     */
    public OutPacket(byte[] data) {
        super(data);
        baos = new ByteArrayOutputStream();
        encodeArr(data);
    }

    /**
     * Creates a new OutPacket with a given header. Immediately encodes the header's short value.
     * @param header The header of this OutPacket.
     */
    public OutPacket(OutHeader header) {
        this(header.getValue());
    }

    /**
     * Returns the header of this OutPacket.
     * @return the header of this OutPacket.
     */
    @Override
    public int getHeader() {
        return op;
    }

    /**
     * Encodes a single byte to this OutPacket.
     * @param b The int to encode as a byte. Will be downcast, so be careful.
     */
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
     * Named like this to prevent autocompletion of "by" to "byteArray" or similar names.
     * @param bArr The byte array to encode.
     */
    public void encodeArr(byte[] bArr) {
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
            log.error("Tried to encode a string that is too big.");
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
        encodeArr(nD);
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
        return String.format("%s, %s/0x%s\t| %s", OutHeader.getOutHeaderByOp(op), op, Integer.toHexString(op).toUpperCase()
                , Util.readableByteArray(Arrays.copyOfRange(getData(), 2, getData().length)));
    }

    public void encodeShort(int value) {
        encodeShort((short) value);
    }

    public void encodeString(String name, int length) {
        encodeString(name, (short) length);
    }

    public void encodeFT(FileTime fileTime) {
        if(fileTime == null) {
            encodeLong(0);
        } else {
            fileTime.encode(this);
        }
    }

    public void encodePosition(Position position) {
        if(position != null) {
            encodeShort(position.getX());
            encodeShort(position.getY());
        } else {
            encodeShort(0);
            encodeShort(0);
        }
    }

    public void encodeRectInt(Rect rect) {
        encodeInt(rect.getLeft());
        encodeInt(rect.getTop());
        encodeInt(rect.getRight());
        encodeInt(rect.getBottom());
    }

    public void encodePositionInt(Position position) {
        encodeInt(position.getX());
        encodeInt(position.getY());
    }

    public void encodeFT(long currentTime) {
        encodeFT(new FileTime(currentTime));
    }

    public void encodeTime(boolean dynamicTerm, int time) {
        encodeByte(dynamicTerm);
        encodeInt(time);
    }

    public void encodeTime(int time) {
        encodeByte(false);
        encodeInt(time);
    }
}
