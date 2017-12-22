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
package net.netty;

import handling.OutHeader;
import net.crypto.MapleCrypto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.Packet;

/**
 * Implementation of a Netty encoder pattern so that encryption of MapleStory
 * packets is possible. Follows steps using the special MapleAES as well as
 * ShandaCrypto (which became non-used after v149.2 in GMS).
 *
 * @author Zygon
 */
public final class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext chc, Packet in, ByteBuf bb) throws Exception {
        byte[] data = in.getData();
        NettyClient c = chc.channel().attr(NettyClient.CLIENT_KEY).get();
        MapleCrypto mCr = chc.channel().attr(NettyClient.CRYPTO_KEY).get();

        if (c != null) {
            if(!OutHeader.isSpamHeader(OutHeader.getOutHeaderByOp(data[0] + (data[1] >> 4)))) {
                System.out.println("[Out]\t| " + in);
            }
            byte[] iv = c.getSendIV();
            byte[] head = MapleCrypto.getHeader(data.length, iv);

//            ShandaCrypto.encrypt(data); // pre-149

            c.acquireEncoderState();
            try {
                mCr.crypt(data, iv);
                c.setSendIV(MapleCrypto.getNewIv(iv));
            } finally {
                c.releaseEncodeState();
            }
            
            bb.writeBytes(head);
            bb.writeBytes(data);
            
        } else {
            System.out.println("[PacketEncoder] | Plain sending " + in);
            bb.writeBytes(data);
        }
    }
}
