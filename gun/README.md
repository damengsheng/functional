# Go Lang

## Go Environment

+ `GOROOT`      go安装目录
+ `GOARCH`      386、amd64、arm ...
+ `GOOS`        darwin、freebsd、linux、windows ...
+ `GOBIN`       $GOROOT/bin
+ `GOPATH`      包含Go语言源码文件(src)、包文件(pkg)、可执行文件(bin)
+ `GOARM`       专门针对arm架构的处理器他的值可以是5、6默认为6
+ `GOMAXPROCS`  设置应用程序可使用的处理器个数和核数

## init
不能被人调用，在每个包完成初始化之后自定执行,并且执行优先级比`main`函数高
每个源文件都只能包含一个`init`函数，初始化以单线程执行，并且按照包的初始化关系顺序执行