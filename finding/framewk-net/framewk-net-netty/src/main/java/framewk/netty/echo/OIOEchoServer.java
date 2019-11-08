package framewk.netty.echo;

import framewk.netty.utils.Inet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import static framewk.netty.utils.Inet.SERVER_NETTY_OIO_ECHO;

/**
 * NIOEchoServer
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 04/05/2019 00:04.
 */
public class OIOEchoServer {

    private static final Logger log = LogManager.getLogger(OIOEchoServer.class);

    @SuppressWarnings("deprecation")
    public OIOEchoServer build() throws Exception {

        EventLoopGroup  oiogroup  = new OioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(oiogroup)
                    .channel(OioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(Inet.LOCAL_V4, SERVER_NETTY_OIO_ECHO))
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new ChannelInboundHandlerAdapter() {
                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                            ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Greeting From Server !\r\n", StandardCharsets.UTF_8));
                                            ctx.writeAndFlush(buf.duplicate())
                                                    .addListener(ChannelFutureListener.CLOSE);
                                        }
                                    });
                        }
                    });
            log.info("Echo Server (obstruct) Listening {} ...", SERVER_NETTY_OIO_ECHO);
            ChannelFuture f = bootstrap
                    .bind()
                    .sync()
                    .channel()
                    .closeFuture()
                    .sync();
        } finally {
            oiogroup.shutdownGracefully()
                    .sync();
        }
        return this;
    }

    public static void main(String[] args) throws Exception {

        new OIOEchoServer().build();

    }
}
