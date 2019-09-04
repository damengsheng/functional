# Scheme Knowledge Stack

## 环境初始化

### Chez Scheme 

1. 获取代码
```shell
git clone --recursive git@github.com:cisco/ChezScheme.git
```

2. 构建
```shell
./configure --installprefix=$HOME/Developer/server/chez_scheme
make
make install
```

macOS 在没有X11的情况下，在make阶段会报找不到头文件, _生怕不知道它有GUI功能似的_ (摊手

```shell
expeditor.c:886:10: fatal error: 'X11/Xlib.h' file not found
#include <X11/Xlib.h>
         ^
1 error generated.
```

解决手段--注释引用头文件的代码
文件路径 ChezScheme/c/version.h
代码大约在283行 (vim下输`283G`
```c
#define LIBX11 "/usr/X11R6/lib/libX11.dylib"
// 变为
// #define LIBX11 "/usr/X11R6/lib/libX11.dylib"
```

2. 引入环境变量
```shell
export CHEZ_SCHEME_HOME=$HOME/Developer/server/chez_scheme
export PATH=$CHEZ_SCHEME_HOME/bin:$PATH
```

4. 验证
```shell
scheme --version
# 现在代码编译版本是 9.4.1

# 运行
scheme

# 退出
(exit)
```

## 入门文档 (感谢 🤓

[yast-cn](http://deathking.github.io/yast-cn)

[维基教科书:Lisp 入门](https://zh.wikibooks.org/wiki/Lisp_入門)

[ANSI Common Lisp 中文翻譯版](http://acl.readthedocs.io/en/latest/)

[Scheme Note](http://irw.ncut.edu.tw/peterju/scheme.html)

[Scheme语言简明教程](https://www.gitbook.com/read/book/wizardforcel/teach-yourself-scheme)或者[Scheme语言简明教程](http://songjinghe.github.io/TYS-zh-translation/)

## 《计算机程序的构造和解释》(SICP)章节

1. 第1章 构造过程抽象
- [x] [1.1 程序设计的基本元素](sicp/chapter01.1_basic_element.ss)
- [ ] [1.2 过程与它们所产生的计算](sicp/chapter01.2_process_compute.ss)
- [ ] [1.3 用高阶函数做抽象](sicp/)
2. 第2章 构造数据现象
- [ ] [2.1 数据抽象导引](sicp/)
- [ ] [2.2 层次性数据和闭包性质](sicp/)
- [ ] [2.3 符号数据](sicp/)
- [ ] [2.4 抽象数据的多重表示](sicp/)
- [ ] [2.5 带有通用型操作的系统](sicp/)
3. 第3章 模块化、对象和状态
- [ ] [3.1 赋值和局部状态](sicp/)
- [ ] [3.2 求值的环境模型](sicp/)
- [ ] [3.3 用变动数据做模拟](sicp/)
- [ ] [3.4 并发：时间是一个本质问题](sicp/)
- [ ] [3.5 流](sicp/)
4. 第4章 元语言抽象
- [ ] [4.1 元循环求值器](sicp/)
- [ ] [4.2 Scheme的变形——惰性求值](sicp/)
- [ ] [4.3 Scheme的变形——非确定性计算](sicp/)
- [ ] [4.4 逻辑程序设计](sicp/)
5. 第5章 寄存器机器里的计算
- [ ] [5.1 寄存器机器的设计](sicp/)
- [ ] [5.2 一个寄存器机器模拟器](sicp/)
- [ ] [5.3 存储分配和废料收集](sicp/)
- [ ] [5.4 显式控制的求值器](sicp/)
- [ ] [5.5 编译](sicp/)
6. 参考文献
7. [练习表](sicp/sicp_practice.md)
8. 索引

[SICP解题集](http://sicp.readthedocs.io/en/latest/) (做完了记得参考下，没思路也记得参考下🤓

## 《Scheme语言简明教程》(TYS)章节
- [x] [chapter01: hello world](tys/chapter01_hello.ss)
- [x] [chapter02: 数据类型](tys/chapter02_base_type.ss)
- [x] [chapter03: 代码结构](tys/chapter03_forms.ss)
- [x] [chapter04: 条件语句](tys/chapter04_conditional_statement.ss)
- [x] [chapter05: 词法变量](tys/chapter05_variable.ss)
- [x] [chapter06: 递归](tys/chapter06_recursion.ss)
- [x] [chapter07: 输入输出](tys/)
- [ ] [chapter08: 宏](tys/)
- [ ] [chapter09: 结构](tys/)
- [ ] [chapter10: 关联列表和表格](tys/)
- [ ] [chapter11: 系统接口](tys/)
- [ ] [chapter12: 对象和类](tys/)
- [ ] [chapter13: 跳转](tys/)
- [ ] [chapter14: 不确定性](tys/)
- [ ] [chapter15: 引擎](tys/)
- [ ] [chapter16: 命令行脚本](tys/)
- [ ] [chapter17: CGI脚本](tys/)
- [ ] [附录 A: Scheme方言](tys/)
- [ ] [附录 B: DOS批处理](tys/)
- [ ] [附录 C: 数值运算](tys/)
- [ ] [附录 D: 可设为infinity的时钟](tys/)
- [ ] [附录 E: 参考文献](tys/)
- [ ] [附录 F: 索引](tys/)
- [ ] [无关的: 论Java语言符号表设计时的一些问题](tys/)




**不急于求成**  🙄 🤒
