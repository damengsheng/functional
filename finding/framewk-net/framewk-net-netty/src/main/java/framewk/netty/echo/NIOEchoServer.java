package framewk.netty.echo;

import framewk.netty.utils.Inet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;

import static framewk.netty.utils.Inet.SERVER_NETTY_ECHO;

/**
 * NIOEchoServer
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 04/05/2019 00:04.
 */
public class NIOEchoServer {

    private static final Logger log = LogManager.getLogger(NIOEchoServer.class);

    public NIOEchoServer build() throws Exception {

        EventLoopGroup  group     = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(Inet.LOCAL_V4, SERVER_NETTY_ECHO))
                    .handler(new LoggingHandler(LogLevel.TRACE))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new ChannelInboundHandlerAdapter() {
                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                            ctx.write(msg);
                                        }

                                        @Override
                                        public void channelReadComplete(ChannelHandlerContext ctx) {
                                            ctx.flush();
                                        }

                                        @Override
                                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                                            log.error("Server Handle Err", cause);
                                            ctx.close();
                                        }
                                    });
                        }
                    });
            log.info("Echo Server Listening {} ...", SERVER_NETTY_ECHO);
            ChannelFuture f = bootstrap
                    .bind()
                    .addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            if (future.isSuccess()) {

                            }
                        }
                    })
                    .sync()
                    .channel()
                    .closeFuture()
                    .sync();
        } finally {
            group.shutdownGracefully()
                    .sync();
        }
        return this;
    }

    public static void main(String[] args) throws Exception {
        new NIOEchoServer().build();
    }
}
