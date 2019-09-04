# C Primer Plus

## C语言概述

### 语言标准
|||
|:---|:---|
|c89, c90, iso9899:1990 | ISO C 1990 |
|iso9899:199409 | ISO C 1990 with amendment 1 |
|gnu89, gnu90 | ISO C 1990 with GNU extensions |
|c99, iso9899:1999 | ISO C 1999 |
|gnu99 | ISO C 1999 with GNU extensions |
|c11, iso9899:2011 | ISO C 2011 |
|gnu11 | ISO C 2011 with GNU extensions |
|c17, iso9899:2017 | ISO C 2017 |
|gnu17 | ISO C 2017 with GNU extensions |

#### 6种语句
1) 标号语句 2) 复合语句 3) 表达式语句 4) 选择语句 5) 迭代语句 6) 跳转语句

#### 标识符(identifier)
_TODO: 标识符位数限制_

#### printf占位符

|占位符|说明|
|:---|:---|
|%c| 单个字符|
|%o| 无符号八进制整数|
|%d %i| 有符号十进制整数|
|%u| 无符号十进制整数|
|%x %X| 无符号十六进制整数，使用十六进制数0f(0F)|
|%a %A| 浮点数、十六进制数和p计数法|
|%e %E| 浮点数，e计数法 |
|%f| 浮点数，十进制计数法|
|%g %G| 根据值的不同，自动选择%f或%e(%E)。%e(%E)格式用于指数小于-4或者大于等于精度时 |
|%p| 指针 |
|%s| 字符串 |
|%%| 百分号 |

### 原始数据类型

#### 数据类型关键字
`int`, `long`, `short`, `unsigned`, `char`, `double`, `signed`, `void`, `_Bool`, `_Complex(复数)`, `_Imaginary(虚数)`

#### 数字类型常量
+ unsigned int `3u`
+ long `3L`
+ unsigned long `3UL`
+ long long `3LL`
+ unsigned long long `3ULL` `3LLU`
+ float
+ double
+ long double

#### char类型
+ 转义字符 `\a` `\b` `\f` `\n` `\r` `\t` `\v` `\\` `\'` `\"` `\?` `\0oo` `\xhh`

#### 可移植类型
+ <stdint.h>
+ <inttypes.h>

#### 复数和虚数类型
```c++
// 包含头文件
#include <complex.h>
```

+ 复数类型
`float _Complex` `double _Complex` `long double_Complex`

+ 虚数类型
`float _Imaginary` `double _Imaginary` `long double_Imaginary`

#### sizeof()函数
在当前系统中类型占内存大小，返回类型是 `size_t`


### 语句 (statement)
大部分语句以分号(;)结尾，, 区别于表达式
```c
legs = 4            // 赋值表达式语句
;                   // 空语句语句
8;                  // 表达式语句
3 + 4;              // 表达式语句
int i;              // 声明(不是语句)
printf("%d\n", 1);    // 表达式语句(函数调用)
```

#### 带参函数
+ actual argument 实参
+ formal argument 形参