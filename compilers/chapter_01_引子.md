# 第一章: 引子

## 1.1 为什么要制作编程语言

1. 帮助理解编程语言的内部运行机制
2. 能制作领域专用语言
3. 用编程语言扩展应用程序
4. 名人 ? 松本行弘 ?
5. 有趣 ?
> 4、5两点待验证 🤔

## 1.2 自制编程语言并不是很难

赋值语句
```
a1 = b1 + b2 * 0.5;
```

考虑的要点:

1. 需要将变量名a1,b1,b2解析出来
   (检测变量名的命名规则，例如C语言变量名只能允许字母和下划线打头，
    变量名第二个字符开始才允许出现数字)

2. 0.5是一个含有小数点的常数，在提取这些常量时，
   能否用“数字组合+小数点+数字组合”来概括左右常量的特征呢
    (还要考虑是否允许00.10这样的数值，当然还有不含小数点的整数)

3. 乘法运算符*比+拥有更高的的运算优先级，语句必须被解析为 b1 + (b2 * 0.5).

4. b2 * 0.5的计算结果，必须在与b1进行加法运算前就应该取得
   (对于复杂计算，需要保存很多类似的这样的临时运算结果)

## 1.3 本书的构成与面向读者

就算条件不满足也会看下去吧 (逃

## 1.4 用什么语言来制作

C... C语言 🙈

## 1.5 要制作怎么样的语言
### 1.5.1 要设计怎样的语法

1. 面向过程
2. 面向对象

FizzBuzz 示例

1. crowbar版本
```c
if(i = 1; i <= 100; i++){
                        
    if(i % 15 == 0) {
        print("FizzBuzz\n");
    }else if(i % 3 == 0){
        print("Fizz\n");
    }else if(i % 5 == 0){
        print("Buzz\n")
    }else{
        print(" " + i + "\n");
    }
}
```

2. Diksam 版本
```c
int i;
if(i = 1; i <= 100; i++){
                        
    if(i % 15 == 0) {
        println("FizzBuzz");
    }else if(i % 3 == 0){
        println("Fizz");
    }else if(i % 5 == 0){
        println("Buzz")
    }else{
        println(" " + i);
    }
}
```

探讨 🐶
1. == 与 := 作为比较运算符对不同人群的接受程度(倾向类C,也就是==)
2. if条件执行语句只有一条的时候{}可以省略
3. switch case 中break的方式,是否需要default
4. 代码风格约束

### 1.5.2 要设计怎样的运行方式

解释执行? 编译执行? 🤔

 补充: 
  “用户”指的是谁？
  _制作编程语言的人_

  解释器并不会进行翻译
- 编译器会将源代码一次性全部翻译成机器码
- 解释器将源码或者语法树(分析树/语法分析树/...)解析为字节码这种中间形态,
    并且一边解析一边运行，但是解释器并不会将源码翻译成机器码
    具备在运行的同时将字节码转换成机器码的功能(JIT [Just-In-Time]编译技术)，但是这部分不属于解释器

(不明白 🙈  continue ... 

## 1.6 环境搭建
### 1.6.1 搭建开发环境
 
 补充:
  关于bison和flex的安装

macOS装个Xcode，yacc/bison 、lex/flex 、 clang都有了

```shell
% yacc --version 
bison (GNU Bison) 2.3
Written by Robert Corbett and Richard Stallman.

Copyright (C) 2006 Free Software Foundation, Inc.
This is free software; see the source for copying conditions.  There is NO
warranty; not even for MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

% lex --version  
flex 2.5.35 Apple(flex-31)

% clang --version
Apple LLVM version 8.1.0 (clang-802.0.42)
Target: x86_64-apple-darwin16.6.0
Thread model: posix
InstalledDir: /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin

```



### 1.6.2 本书涉及的源代码以及编译器
[本书涉及源代码 http://avnpc.com/pages/devlang#download](http://avnpc.com/pages/devlang#download)