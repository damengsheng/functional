package binlog.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * SocketChannelManager
 *
 * @author yakir on 2019/11/29 12:13.
 */
public class SocketChannelManager {

    private static final Logger log = LogManager.getLogger(SocketChannelManager.class);

    private static final EventLoopGroup group     = new NioEventLoopGroup();
    private static final Bootstrap      bootstrap = new Bootstrap();

    static {

        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(1024))
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new MySQLChannelHandler());
                    }
                });

    }

    public static Optional<Channel> open(SocketAddress address) throws InterruptedException {
        ChannelFuture future = bootstrap.connect(address).sync();
        return Optional.ofNullable(future)
                .filter(Future::isSuccess)
                .map(ChannelFuture::channel);
    }

    private static class MySQLChannelHandler extends SimpleChannelInboundHandler<ByteBuf> {

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            super.channelInactive(ctx);
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(Unpooled.copiedBuffer("Channel Active", StandardCharsets.UTF_8));
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
            log.info("Client Received: {}", msg.toString(StandardCharsets.UTF_8));

        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            log.error("Mysql Channel Handle Error", cause);
            ctx.close();
        }
    }

}
