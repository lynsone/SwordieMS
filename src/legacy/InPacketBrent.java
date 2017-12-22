/*
    This file is part of Desu: MapleStory v62 Server Emulator
    Copyright (C) 2014  Zygon <watchmystarz@hotmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package legacy;

import net.Packet;

import java.nio.charset.Charset;

/**
 * Artifact from Invictus. Unlike OutPacket, this is able to be used for
 * reading data from a client over and over again without needing a lock. It is
 * still practical to have on per session rather than creating new generations
 * for each new received packet.
 * 
 * @author Zygon
 */
public final class InPacketBrent {

    private int offset;
    private byte[] data;
    private static  Charset ASCII = Charset.forName("US-ASCII");

    public InPacketBrent() {
        offset = -1;
        data = null;
    }

    public InPacketBrent next(byte[] d) {
        offset = 0;
        data = d;
        return this;
    }

    public InPacketBrent next(Packet p) {
        return next(p.getData());
    }

    public int read() {
        try {
            return 0xFF & data[offset++];
        } catch (Exception e) {
            return -1;
        }
    }

    public void read(byte[] in) {
        read(in, 0, in.length);
    }

    public void read(byte[] in, int off, int len) {
        for (int i = off; i < len; i++) {
            in[i] = readByte();
        }
    }

    public byte[] read(int num) {
        byte[] ret = new byte[num];
        for (int i = 0; i < num; i++) {
            ret[i] = readByte();
        }
        return ret;
    }

    public boolean readBool() {
        return read() > 0;
    }

    public byte readByte() {
        return (byte) read();
    }

    public short readShort() {
        return (short) (read() + (read() << 8));
    }

    public char readChar() {
        return (char) (read() + (read() << 8));
    }

    public int readInteger() {
        return read() + (read() << 8) + (read() << 16)
                + (read() << 24);
    }

    public float readFloat() {
        return Float.intBitsToFloat(readInteger());
    }

    public long readLong() {
        return read() + (read() << 8) + (read() << 16)
                + (read() << 24) + (read() << 32)
                + (read() << 40) + (read() << 48)
                + (read() << 56);
    }

    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public String readString(int len) {
        byte[] sd = new byte[len];
        for (int i = 0; i < len; i++) {
            sd[i] = readByte();
        }
        return new String(sd, ASCII);
    }

    public String readMapleString() {
        return readString(readShort());
    }

    public String readNullTerminatedString() {
        int c = 0;
        while (read() != 0) {
            c++;
        }
        offset -= (c + 1);
        return readString(c);
    }

    public InPacketBrent skip(int num) {
        offset += num;
        return this;
    }

    public int available() {
        return data.length - offset;
    }

    public int getOffset() {
        return offset;
    }

    public byte[] getData() {
        return data;
    }

    public void close() {
        offset = -1;
        data = null;
    }
}
