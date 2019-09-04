package yakir.netty.components.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * FixedLengthFrameDecoder
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/04/10 03:46.
 */
public class FixedLengthFrameDecoder extends ByteToMessageDecoder {

    private final int len;

    public FixedLengthFrameDecoder(int len) {
        if (len <= 0) {
            throw new IllegalArgumentException("frame length must be a positive integer");
        }
        this.len = len;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        while (in.readableBytes() >= len) {
            ByteBuf buf = in.readBytes(len);
            out.add(buf);
        }

    }
}
