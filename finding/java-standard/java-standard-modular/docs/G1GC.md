# G1GC

## 基础概念
文档中的选项参数初始化值对照 `docs/jvm_options/xxx.txt`

+ `-XX:MetaspaceSize` 初始化元空间大小
+ `-XX:MaxMetaspaceSize` 最大元空间大小
+ `-XX:MinMetaspaceFreeRatio` 扩大元空间的最小比率,GC之后内存占用超过这一比率就会扩大元空间
+ `-XX:MaxMetaspaceFreeRatio` 缩小元空间的最小比率,GC之后内存占用低于这一比率就会缩小元空间

* Mixed GC Event(混合GC事件)
所有年轻代Regin和一部分老年代Regin一起被回收，混合GC事件一定是在Minor GC之后，并且混合GC只有在存活对象元数据存在的情况下才会触发

* Reclaimable(可回收)
存活率小于`-XX:G1MixedGCLiveThresholdPercent`的Region放在一个链表中,
Region的值除以整个堆区(Region)，如果大于`-XX:G1HeapWastePercent`则启动回收机制

* RSet(Remembered Set, 跟踪指向某个Region内的对象引用)

* CSet(Collection Set, 收集集合)
保存一次GC中将执行垃圾回收的Region。GC时在CSet中所有存活数据(Live Data)都会被转移(复制、移动)。
集合中的Region可以是Eden Survivor 和/或 Old Generation . CSets所占用的JVM内存小于总大小的 1% .

* G1 Pause Time Target (G1GC停顿目标时间)

* Root Region Scan
从根区间扫描开始，标记所有可达的存活对象，由于在并行标记的执行过程中移动数据会造成应用程序暂停，所以根区间扫描这个阶段需要在下一次评估中断开始执行直到结束

* PLAB (Promotion Local Allocation Buffers)
用于年轻代回收。作用是避免多线程竞争相同的数据，处理方式是每个线程拥有独立的PLAB, 用于针对幸存者和老年空间。
当应用程序开启的线程较多时，最好使用`-XX:-ResizePLAB`避免大量的线程通信导致性能下降。

* TLAB (Thread Local Allocation Buffers, 线程本地分配缓存)
一个线程专用的内存分配区域。TLAB是为了加速兑现的分配而生。由于对象一般分配在堆上，而堆是全局共享。
因此在同一个时间，可能会有多个线程在堆上申请空间。因此，每一个对象分配都必须要进行同步，而在竞争激烈的场合分配内存的效率会进一步下降。
而对象分配操作很平常，所以JVM就使用了TLAB这种线程专属的区域来避免多线程冲突，提高对象分配的效率。
TLAB本身占用了Eden区的空间，即JVM会为每一个Java线程分配一块TLAB空间。  
对G1GC来说TLAB是Eden的一个Region，被一个单一的线程用于分配资源。主要用途是让一个线程通过栈操作方式独享内存空间，这样比多个线程之间共享资源要快很多。
如果每个线程的分配内存不够，那么他会去全局内存池申请新的内存，这也就是说，TLAB设置过小容易造成频繁申请，造成GC性能下降，设置过大内存浪费。

* Lock-free Manner
TLAB调用本地线程的无锁竞争分配方式

* Region
G1垃圾收集器将堆进行分区，每次收集的时候，只收集其中几个区域，以此来控制垃圾回收产生的停顿时间。
Region表示一个区域，每个区域里面的字母代表不同分代内存空间类型 (E: Eden, O: Old, S: Survivor), 空白的区块不属于任何一个分区，G1可以在需要的时候任意指定这个区域属于Eden或者Old区

* Ergonomics Heuristic Decision
`-XX:+PrintAdaptiveSizePolicy`

