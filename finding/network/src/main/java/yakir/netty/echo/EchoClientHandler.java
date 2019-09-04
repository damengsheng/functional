package yakir.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

/**
 * EchoServerHandler
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 04/05/2019 01:02.
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

private static final Logger log = LogManager.getLogger(EchoClientHandler.class);
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty Rocks", StandardCharsets.UTF_8));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        log.info("Client Received: {}", msg.toString(StandardCharsets.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("Client Handle Error", cause);
        ctx.close();
    }
}
