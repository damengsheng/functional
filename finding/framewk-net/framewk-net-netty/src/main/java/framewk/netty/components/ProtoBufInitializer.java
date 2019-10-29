package standard.netty.components;

import com.google.protobuf.MessageLite;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

/**
 * ProtoBufInitializer
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 04/11/2019 00:32.
 */
public class ProtoBufInitializer extends ChannelInitializer<Channel> {

    private final MessageLite msgLite;

    public ProtoBufInitializer(MessageLite msgLite) {
        this.msgLite = msgLite;
    }

    @Override
    protected void initChannel(Channel ch) {
        ch.pipeline()
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufEncoder())
                .addLast(new ProtobufDecoder(msgLite))
                .addLast();
    }

    public static final class ObjectHandler extends SimpleChannelInboundHandler<Object> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
            // do something ...
        }
    }
}
