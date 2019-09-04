package yakir.netty.components;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * HeartbeatInitialier
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/04/10 18:05.
 */
public class HeartbeatInitialier extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        pipeline
                .addLast(new IdleStateHandler(0, 0, 60, TimeUnit.SECONDS))
                .addLast(new HeartbeatHandler());
    }


    public static final class HeartbeatHandler extends ChannelInboundHandlerAdapter {

        private static final ByteBuf HB_BUF = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("HEARTBEAT", StandardCharsets.US_ASCII));

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

            if (evt instanceof IdleStateEvent) {
                ctx.writeAndFlush(HB_BUF.duplicate())
                        .addListener(ChannelFutureListener.CLOSE_ON_FAILURE); // 发送失败时关闭该连接
            } else {
                super.userEventTriggered(ctx, evt);
            }
        }
    }
}
