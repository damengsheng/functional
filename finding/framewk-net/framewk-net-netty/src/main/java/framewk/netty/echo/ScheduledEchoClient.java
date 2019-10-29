package standard.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import standard.utils.Inet;
import standard.utils.Times;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledEchoClient
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 04/05/2019 00:45.
 */
public class ScheduledEchoClient {

    private static final Logger log = LogManager.getLogger(ScheduledEchoClient.class);

    public ScheduledEchoClient build(int serverPort) throws Exception {

        EventLoopGroup eventGroup = new NioEventLoopGroup();
        eventGroup.scheduleAtFixedRate(() -> log.info("6 seconds later"), 100L, 6000L, TimeUnit.MILLISECONDS);
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(eventGroup)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(Inet.LOCAL_V4, serverPort)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new SimpleChannelInboundHandler<ByteBuf>() {
                                        @Override
                                        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
                                            ctx.writeAndFlush(Unpooled.copiedBuffer(msg.toString().concat(Times.nowString()), StandardCharsets.UTF_8));
                                        }
                                    });
                        }
                    });
            ChannelFuture f = bootstrap
                    .connect()
                    .sync()
                    .channel()
                    .closeFuture()
                    .sync();
        } finally {
            eventGroup.shutdownGracefully()
                    .sync();
        }

        return this;
    }

    public static void main(String[] args) throws Exception {

        int port = Optional.ofNullable(args)
                .filter(_args -> _args.length > 0)
                .map(_args -> _args[0])
                .map(Integer::valueOf)
                .orElse(Inet.SERVER_NETTY_SCHEDULED);
        log.info("client connect [{}]", port);

        new ScheduledEchoClient().build(port);
    }
}
