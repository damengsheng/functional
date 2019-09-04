## Netty

### Channel、EventLoop、ChannelFuture
+ Channel - Socket
+ EventLoop - 控制流、多线程处理、并发
+ ChannelFuture - 异步通知

### Transports
+ OIO (io.netty.channel.socket.oio)
+ NIO (io.netty.channel.socket.nio)
+ Epoll (io.netty.channel.epoll)
+ Local (io.netty.channel.local)
+ Embedded (io.netty.channel.embedded)


### SelectionKey
+ OP_ACCEPT     请求在接受新连接并创建Channel时获得通知
+ OP_CONNECT    请求在建立连接时获得通知
+ OP_READ       请求当数据已经就绪,可以从Channel中读取时获得通知
+ OP_WRITE      请求当可以向Channel中写更多的数据时获得通知


### Channel、EventLoop、Thread、EventLoopGroup之间的关系
+ 一个`EventLoopGroup`包含一个或者多个`EventLoop`,
+ 一个`EventLoop`在他的生命周期内只和一个`Thread`绑定
+ 所有由`EventLoop`处理的I/O事件都在它`专有的Thread`上处理
+ 一个`Channel`在它的**生命周期**内只注册于一个`EventLoop`
+ 一个`EventLoop`**可能**会被分配给**一个或者多个Channel**


### Channel `io.netty.channel.Channel`
#### Channel生命周期

|状态|描述|
|:---|:---|
|ChannelUnregistered|Channel已经被创建,但还未注册到EventLoop|
|ChannelRegistered|Channel已经被注册到EventLoop|
|ChannelActive|Channel处于活动状态(已经连接到它的远程节点)。它现在可以接收和发送数据|
|ChannelInactive|Channel没有连接到远程节点|


### EventLoop `io.netty.channel.EventLoop`


### ChannelFuture `io.netty.channel.ChannelFuture`


### ChannelHandler `io.netty.channel.ChannelHandler`
#### ChannelHandler生命周期

|类型|描述|
|:---|:---|
|handlerAdded|当ChannelHandler添加到ChannelPipeline时被调用|
|handlerRemoved|当ChannelHandler从ChannelPipeline移除时被调用|
|exceptionCaught|当在ChannelPipeline处理过程中发生错误时被调用|

#### ChannelInboundHandler
处理入站数据以及各种状态变化

#### ChannelOutboundHandler
处理出站数据并且允许拦截所有的操作

### ChannelPipeline `io.netty.channel.ChannelPipeline`

### Bootstrap ServerBootstrap
+ ServerBootstrap server端起动器
+ Bootstrap client端起动器


### ByteBuf Netty数据容器
`ByteBuf`维护了两个不同的索引：一个用于读取,一个用于写入  
1. 堆缓冲区      将数据存储在JVM堆中,这种模式称为**支撑数组(backing array)**
```java
ByteBuf buf = ...
if(buf.hasArray()){ // 检查ByteBuf是否有一个支撑数组
    byte[] array = buf.array(); // 如果有,则获取对该数组的引用
    int offset = buf.arrayOffset() + buf.readerIndex(); // 计算第一个字节的偏移量
    int length = buf.readableBytes(); // 获取可读字节数
    doSomething(array, offset, length); // 使用数组、偏移量、长度作为参数使用
}
```
2. 直接缓冲区     数据不在堆上,使用数据时需要对数据进行一次复制
```java
ByteBuf buf = ...
if(!buf.hasArray()){ // 检查ByteBuf是否为一个数组支撑,如果不是,则这是一个直接缓冲区
    int length   = buf.readableBytes(); // 获取可读字节数
    byte[] array = new byte[length];
    buf.getBytes(buf.readerIndex(), array); //将字节复制到该数组
    doSomething(array, 0, length); // 使用数组、偏移量、长度作为参数使用
}
```
3. 复合缓冲区(CompositeByteBuf )
  复合缓冲区为多个`ByteBuf`提供一个聚合视图, 可以根据需要添加或者删除ByteBuf实例

### 泄漏
#### 检测泄漏
```bash
java -Dio.netty.leakDetectionLevel=检测级别
```

#### 泄漏检测级别

|级别|描述|
|:---|:---|
|DISABLED|禁用泄漏检测|
|SIMPLE|默认级别,使用1%的默认采样率检测并报告任何发现的泄漏|
|ADVANCED|使用默认采样率,报告所发现的任何泄漏以及对应的消息被访问的位置|
|PARANOID|类似ADVANCED,对消息的每次访问都进行采样(应该只在调试阶段使用)|

### 编解码器
+ 编码器: 处理入站数据,将消息转换为更适合传输的格式(比如**字节流**)
  - MessageToByteEncoder
  - MessageToMessageEncoder
+ 解码器: 操作出站数据,将网络字节流转换回应用程序的消息格式
  - ByteToMessageDecoder
  - ReplayingDecoder
  - MessageToMessageDecoder
  - TooLongFrameException
+ 编解码器(整合编码、解码)
  - ByteToMessageCodec
  - MessageToMessageCodec
  - CombinedChannelDuplexHandler(ChannelInboundHandler,ChannelOutboundHandler的容器)

### HTTP
+ HttpRequestEncoder
+ HttpRequestDecoder
+ HttpResponseEncoder
+ HttpResponseDecoder
+ HttpClientCodec, HttpServerCodec 
  将HTTP请求或者响应的多个组成部分合并成`FullHttpRequest`或者`FullHttpResponse`,  
  聚合成一个完整的请求或者一个完整的响应,通过这样的方式,消息内容总是完整的
+ HttpContentCompressor HTTP压缩,支持 `gzip` `deflate`压缩编码
+ SslHandler 提供HTTPS加解密

### WebSocket (Server <-> Client 双向数据交换)
Client通过`HTTP`与`Server`发起`WebSocket握手`确认, Server确认通信
+ BinaryWebSocketFrame          数据帧,二进制数据
+ TextWebSocketFrame            数据帧,文本数据
+ ContinuationWebSocketFrame    数据帧,属于上一个`BinaryWebSocketFrame`,`TextWebSocketFrame`的文本或者二进制数据
+ CloseWebSocketFrame           控制帧,一个`CLOSE`请求,关闭的状态码以及关闭的原因
+ PingWebSocketFrame            控制帧,请求一个 `PongWebSocketFrame`
+ PongWebSocketFrame            控制帧,对`PingWebSocketFrame`请求的响应

### 空闲的连接和超时
+ IdleStateHandler
  当连接空闲时间太长时,将会触发一个`IdleStateEvent`事件,
  可以在`ChannelInboundHandler`中Override `userEventTriggered`方法来处理`IdleStateEvent`事件
+ ReadTimeoutHandler
  如果在指定的时间间隔内没有收到任何的入站数据,**抛出**一个`ReadTimeoutException`并**关闭**对应的`Channel`,
  可以Override `ChannelHandler`中的`exceptionCaught`方法来检测`ReadTimeoutException`
+ WriteTimeoutHandler
  如果在指定的时间间隔内没有任何的出站数据写出,**抛出**一个`WriteTimeoutException`并**关闭**对应的`Channel`,
  可以Override `ChannelHandler`中的`exceptionCaught`方法来检测`WriteTimeoutException`
  
### 解码基于分隔符(delimited)的协议和基于长度的协议
+ DelimiterBasedFrameDecoder    由用户指定的分隔符来提取帧的通用解码器
+ LineBasedFrameDecoder         由换行符(`\n`,`\r\n`)分割的解码,比`DelimiterBasedFrameDecoder`快
+ FixedLengthFrameDecoder       提取在调用构造行数时指定的_**定帧长**_
+ LengthFieldBasedFrameDecoder  根据编码进帧头部中的长度值提取帧,**_该字段_**的**偏移量**以及**长度**在构造函数中指定


### FileRegion
通过支持`zero-copy`的文件传输Channel来发送文件区域
示例: `yakir.netty.components.FileRegionInitializer`  
只适用于文件内容的直接传输,不包括应用程序对数据的任何操作,在需要将数据从文件系统复制到用户内存中时,
可以使用`ChunkedWriteHandler`,它支持异步写大型的数据,而又不会消耗大量的内存


### 序列化数据
#### JDK序列化
+ CompatibleObjectDecoder       使用JDK序列化的非基于Netty的远程节点进行互操作的解码器(Netty3.1中已废弃,Netty4中不存在)
+ CompatibleObjectEncoder       使用JDK序列化的非基于Netty的远程节点进行互操作的编码器
+ ObjectDecoder                 构建基于JDK序列化之上的使用自定义的序列化来解码的解码器;当没有其他的外部依赖时,它提供了速度上的改进,其他的序列化实现更加可取
+ ObjectEncoder                 构建基于JDK序列化之上的使用自定义的序列化来解码的编码器;当没有其他的外部依赖时,它提供了速度上的改进,其他的序列化实现更加可取

#### JBoss Marshalling
+ CompatibleMarshallingDecoder  只使用JDK序列化的远程节点兼容
+ CompatibleMarshallingEncoder
+ MarshallingDecoder            适用于使用JBoss Marshalling的节点,这些类必须一起使用
+ MarshallingEncoder


#### Protocal Buffers
+ ProtobufDecoder 
+ ProtobufEncoder 
+ ProtobufVarint32FrameDecoder
+ ProtobufVarint32LengthFieldPrepender