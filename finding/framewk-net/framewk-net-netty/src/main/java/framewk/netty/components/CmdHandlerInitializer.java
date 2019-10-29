package standard.netty.components;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * CmdHandlerInitializer
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/04/10 22:25.
 */
public class CmdHandlerInitializer extends ChannelInitializer<Channel> {

    private static final byte SPACE = (byte) ' ';

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                .addLast(new CmdDecoder(64 * 1024))
                .addLast(new CmdHandler());
    }

    public static final class Cmd {
        private final ByteBuf name;
        private final ByteBuf age;

        public Cmd(ByteBuf name, ByteBuf age) {
            this.name = name;
            this.age  = age;
        }

        public ByteBuf name() {
            return name;
        }

        public ByteBuf age() {
            return age;
        }
    }

    public static final class CmdDecoder extends LineBasedFrameDecoder {

        public CmdDecoder(int maxLength) {
            super(maxLength);
        }

        @Override
        protected Object decode(ChannelHandlerContext ctx, ByteBuf buffer) throws Exception {
            ByteBuf frame = (ByteBuf) super.decode(ctx, buffer);
            if (null == frame) {
                return null;
            }

            int index = frame.indexOf(frame.readerIndex(),
                    frame.writerIndex(), SPACE);
            return new Cmd(frame.slice(frame.readerIndex(), index),
                    frame.slice(index + 1, frame.writerIndex()));
        }
    }

    public static final class CmdHandler extends SimpleChannelInboundHandler<Cmd> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Cmd msg) throws Exception {
            // do something
        }
    }
}
