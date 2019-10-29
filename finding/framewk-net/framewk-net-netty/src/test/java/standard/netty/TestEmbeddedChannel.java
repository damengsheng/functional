package standard.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import yakir.netty.components.decoder.FixedLengthFrameDecoder;

/**
 * TestEmbeddedChannel
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/04/10 03:28.
 */
public class TestEmbeddedChannel {

    @Test
    public void testFixedLengthFrameDecoder() {

        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(
                new FixedLengthFrameDecoder(3));
        channel.writeInbound(input.retain());
        channel.finish();

        ByteBuf readedBuf = channel.readInbound();
        Assertions.assertEquals(buf.readSlice(3), readedBuf);
        readedBuf.release();
    }
}
