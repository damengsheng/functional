package framewk.netty.components;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

import java.io.Serializable;

/**
 * MarshallingInitializer
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/04/11 00:26.
 */
public class MarshallingInitializer extends ChannelInitializer<Channel> {

    private final MarshallerProvider   marshallerProvider;
    private final UnmarshallerProvider unmarshallerProvider;

    public MarshallingInitializer(MarshallerProvider marshallerProvider, UnmarshallerProvider unmarshallerProvider) {
        this.marshallerProvider   = marshallerProvider;
        this.unmarshallerProvider = unmarshallerProvider;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {

        ch.pipeline()
                .addLast(new MarshallingDecoder(unmarshallerProvider))
                .addLast(new MarshallingEncoder(marshallerProvider))
                .addLast(new ObjectHandler());
    }

    public static final class ObjectHandler extends SimpleChannelInboundHandler<Serializable> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Serializable msg) throws Exception {
            //
        }
    }
}
