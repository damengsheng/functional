package standard.netty.components;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * HttpPipelineInitializer
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/04/10 17:16.
 */
public class HttpPipelineInitializer extends ChannelInitializer<Channel> {

    private final boolean    isClient;
    private final SslContext sslContext;

    public HttpPipelineInitializer(boolean isClient, SslContext sslContext) {
        this.isClient   = isClient;
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        SSLEngine       engine   = sslContext.newEngine(ch.alloc());
        pipeline.addFirst("ssl", new SslHandler(engine));
        if (isClient) {
            // isClient
            // 编码请求
            // 解码server的响应
            pipeline
                    .addLast("decoder", new HttpResponseDecoder())
                    .addLast("encoder", new HttpRequestEncoder());
            // 多个消息合并成 FullHttpRequest，FullHttpResponse
            // pipeline.addLast("codec",new HttpClientCodec());
            // HTTP压缩
            // pipeline.addLast("compressor", new HttpContentCompressor());
        } else {
            // server
            // 编码响应传输给client
            // 解码client传递给server的请求
            pipeline
                    .addLast("decoder", new HttpRequestDecoder())
                    .addLast("encoder", new HttpResponseEncoder());
            // 多个消息合并成 FullHttpRequest，FullHttpResponse
            // pipeline.addLast("codec", new HttpServerCodec());
            // HTTP压缩
            // pipeline.addLast("compressor", new HttpContentCompressor());
        }
    }
}
