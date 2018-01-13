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
import util.IntegerValue;
import util.Util;

import java.awt.*;
import java.nio.charset.Charset;

/**
 * Artifact from Invictus. Modified because this is relatively cheap enough
 * and the addition of locks and keeping one for a session was probably overkill 
 * for something this simple.
 *
 * @author Zygon
 */
public final class OutPacketBrent {

    private int offset;
    private byte[] data;
    private static final Charset ASCII = Charset.forName("US-ASCII");

    public OutPacketBrent(int size) {
        offset = 0;
        data = new byte[size];
    }

    private void expand(int size) {
        byte[] nd = new byte[size];
        System.arraycopy(data, 0, nd, 0, offset);
        data = nd;
    }

    private void trim() {
        expand(offset);
    }

    public final OutPacketBrent write(int b) {
        if (offset + 1 >= data.length) {
            expand(data.length * 2);
        }
        data[offset++] = (byte) b;
        return this;
    }

    private OutPacketBrent write(long lb) {
        return write((int) lb);
    }

    public final OutPacketBrent write(byte[] in) {
        return write(in, 0, in.length);
    }

    public final OutPacketBrent write(byte[] in, int off, int len) {
        for (int i = off; i < len; i++) {
            write(in[i]);
        }
        return this;
    }

    public final OutPacketBrent write(int... b) {
        for (int i = 0; i < b.length; i++) {
            write(b[i]);
        }
        return this;
    }

    public final OutPacketBrent writeByte(byte b) {
        return write(b);
    }

    public final OutPacketBrent writeHeader(IntegerValue i) {
        return writeShort(i.getValue());
    }

    public final OutPacketBrent writeShort(int s) {
        return write(s & 0xFF).write(s >>> 8);
    }

    public final OutPacketBrent writeShort(short s) {
        return write(s & 0xFF).write(s >>> 8);
    }

    public final OutPacketBrent writeChar(char c) {
        return writeShort(c);
    }

    public final OutPacketBrent writeInteger(int i) {
        return write(i & 0xFF).write(i >>> 8).write(i >>> 16).
                write(i >>> 24);
    }

    public final OutPacketBrent writeFloat(float f) {
        return writeInteger(Float.floatToIntBits(f));
    }

    public final OutPacketBrent writeLong(long l) {
        return write(l & 0xFF).write(l >>> 8).write(l >>> 16).
                write(l >>> 24).write(l >>> 32).write(l >>> 40).
                write(l >>> 48).write(l >>> 56);
    }

    public final OutPacketBrent writeDouble(double d) {
        return writeLong(Double.doubleToLongBits(d));
    }

    public final OutPacketBrent writeString(String s) {
        return write(s.getBytes(ASCII));
    }

    public final OutPacketBrent writeMapleString(String s) {
        return writeShort(s.length()).writeString(s);
    }

    public final OutPacketBrent writeNullTerminatedString(String s) {
        return writeString(s).write(0);
    }

    public final OutPacketBrent writeHex(String s) {
        return write(Util.getByteArrayByString(s));
    }

    public final OutPacketBrent writeBool(boolean b) {
        return write(b ? 1 : 0);
    }

    public final OutPacketBrent fill(int val, int num) {
        for (int i = 0; i < num; i++) {
            write(val);
        }
        return this;
    }

    public final OutPacketBrent writePosition(Point p) {
        return writeShort(p.x).writeShort(p.y);
    }

    public final int getOffset() {
        return offset;
    }

    public final byte[] getData() {
        return data;
    }

    public final void close() {
        offset = -1;
        data = null;
    }

    @Override
    public final String toString() {
        return "[SndBEREWRE] | " + getData()[0] + " | Data: " + Util.readableByteArray(getData());

    }

    public final byte[] data() {
        if (data != null) {
            if (data.length > offset) {
                trim();
            }
            return data;
        }
        return null;
    }

    public final Packet createPacket() {
        if (data != null) {
            if (data.length > offset) {
                trim();
            }
            return new Packet(data);
        }
        return null;
    }
}
