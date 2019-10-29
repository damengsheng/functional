package standard.netty.components;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;

import java.io.File;
import java.io.FileInputStream;

/**
 * FileRegionInitializer
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/04/10 23:24.
 */
public class FileRegionInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        FileInputStream fis        = new FileInputStream(new File(""));
        FileRegion      fileRegion = new DefaultFileRegion(fis.getChannel(), 0, fis.available());
        ch.writeAndFlush(fileRegion)
                .addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (!future.isSuccess()) {
                            // 处理失败
                            Throwable cause = future.cause();
                        }
                    }
                });
    }
}
