# 开篇

## JDK诊断工具

+ jinfo
+ jstack
+ jmap
+ jhat(1.9+之后移除了)
+ jstat
+ jcmd
+ jconsole
+ jvisualvm(1.9+之后独立成[VisualVM](https://visualvm.github.io/))
+ mat([Eclipse Memory Analyzer](https://www.eclipse.org/mat/))
+ hsdis
+ jstatd


```bash
pid=`jps -l | grep Hello | awk  -F ' ' '{print $1}'`
```

### jinfo 

查看和配置[manageable]运行时JVM配置参数

```bash
jinfo -flag +PrintGC $pid   # 添加配置参数
jinfo -flags $pid           # 打印配置参数
jinfo -systemprops $pid     # 打印系统环境参数
```

### jstack

打印Java运行时线程栈

```bash
jstack -l $pid  # 输出线程栈
```

### jmap

JVM运行时信息

```bash
jmap -clstats $pid                                     # 类加载器统计信息
jmap -histo $pid                                       # 统计类实例数
jmap -histo:live $pid                                  # 统计存活的类实例数
jmap -dump:live,format=b,file=`pwd`/$pid.hprof $pid    # dump堆中存活的对象格式为二进制存放到当前目录下以进程id为文件名的hprof

```

### jstat

`jstat 参数 PID 间隔时间[ms] 循环次数`

```bash
jstat -class $pid                   # 类加载器统计信息
jstat -compiler $pid                # jit编译状态信息
jstat -gc $pid                      # 堆gc信息
jstat -gccapacity $pid              # 堆中对象生命周期的占比
jstat -gcmetaspace $pid             # metaspace统计
jstat -printcompilation $pid        # 编译方法统计信息
jstat -gcutil $pid                  # gc统计信息
jstat -gcutil $pid                  # gc统计信息
```

|||
|:---|:---|
|S0C| Current survivor space 0 capacity (KB). |
|S1C| Current survivor space 1 capacity (KB).|
|S0U| Survivor space 0 utilization (KB).|
|S1U| Survivor space 1 utilization (KB).|
|EC| Current eden space capacity (KB).|
|EU| Eden space utilization (KB).|
|OC| Current old space capacity (KB).|
|OU| Old space utilization (KB).|
|MC| Metaspace Committed Size (KB).|
|MU| Metaspace utilization (KB).|
|CCSC| Compressed class committed size (KB).|
|CCSU| Compressed class space used (KB).|
|YGC| Number of young generation garbage collection (GC) events.|
|YGCT| Young generation garbage collection time.|
|FGC| Number of full GC events.|
|FGCT| Full garbage collection time.|
|GCT| Total garbage collection time.|


### jcmd
`jcmd $pid help`

```bash
Compiler.CodeHeap_Analytics
Compiler.codecache
Compiler.codelist
Compiler.directives_add
Compiler.directives_clear
Compiler.directives_print
Compiler.directives_remove
Compiler.queue
GC.class_histogram
GC.class_stats
GC.finalizer_info
GC.heap_dump
GC.heap_info
GC.run
GC.run_finalization
JFR.check
JFR.configure
JFR.dump
JFR.start
JFR.stop
JVMTI.agent_load
JVMTI.data_dump
ManagementAgent.start
ManagementAgent.start_local
ManagementAgent.status
ManagementAgent.stop
Thread.print
VM.check_commercial_features
VM.class_hierarchy
VM.classloader_stats
VM.classloaders
VM.command_line
VM.dynlibs
VM.flags
VM.info
VM.log
VM.metaspace
VM.native_memory
VM.print_touched_methods
VM.set_flag
VM.stringtable
VM.symboltable
VM.system_properties
VM.systemdictionary
VM.unlock_commercial_features
VM.uptime
VM.version
help
```


```bash
jcmd $pid GC.class_histogram 
jcmd $pid GC.class_stats
jcmd $pid Thread.print
jcmd $pid GC.heap_dump -all `pwd`/$pid.hprof
jcmd $pid GC.run
jcmd $pid VM.log
jcmd $pid JVMTI.agent_load $agentpath
```

### jhsdb
