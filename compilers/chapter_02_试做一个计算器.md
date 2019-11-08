# 第二章: 试做一个计算器

##  2.1 yacc/lex是什么

1. 词法分析 -- 词法分析器(lexical analyzer)/扫描器(lexer/scanner)来完成这部操作
   将源代码分割为若干记号(token)的处理

2. 语法分析 -- 解析器(paser)来完成这部操作
   从记号树构建分析树(parse tree)的处理.
   分析树也叫语法树(syntax tree)、抽象语法树(abstract syntax tree ,AST)
   (包含代码中所有标记的叫语法树/分析树，将一些无用标记剔除的才叫作抽象语法树)

3. 语义分析
   数据类型的解析以及错误检查.
   经过语法分析生成的分析树，并不包含数据类型等语义信息.因此在语义分析阶段，
   会检查程序中是否含有语法正确但是存在逻辑问题的程序.
4. 生成代码
```c
if(a == 10){                                       
    printf("hoge\n");
}else{
    printf("piyo\n");
} 
```
`if(a == 10){printf("hoge\n");}else{printf("piyo\n");}`

 补充:
* 词法分析器与解析器是各自独立的

## 2.2 试做一个计算器

计算器: mycalc

### 2.2.1 lex

lex使用`.l`作为后缀
词法分析器是将输入的字符串分割为记号的程序，因此首先需要定义mycalc代码中需要使用到的记号

- 运算符 +，-，*，\
- 整数 1，2，3，4 ...
- 实数 0.2, 123.1, 1.0
- 换行符(一个算数表达式结束后使用换行触发执行)

[calculator.l](calculator/calculator.l)

```flex
%{
#include <stdio.h>
#include "y.tab.h"

int
yywrap(void)
{
    return 1;
}
%}
%%
"+"     return ADD;
"-"     return SUB;
"*"     return MUL;
"/"     return DIV;
"\n"    return CR;
([1-9][1-9]*)|0|(0-9]+\.[0-9]+) {
    double temp;
    sscanf(yytext, "%lf", &temp);
    yylval.double_value = temp;
    return DOUBLE_LITERAL;
}
[ \t] ;
. {
    fprintf(stderr, "lexical error. \n");
	exit(1)
}
%%
```

第11行`%%`之前的部分叫作**定义区块**，可以定义初始状态或者为正则表达式命名.

`%{`和`%}`包裹的部分，是想让生成的词法分析器将这部分代码原样输出.

后续程序所需的头文件`#include`等都包含在这里.

第三行`#include "y.tab.h"`头文件*y.tab.h*就是yacc自动生成的头文件
ADD、SUB、MUL、DIV、CR、DOUBLE_LITERAL等都是在*y.tab.h*头文件中定义的宏.

两个`%%`(11行到28行)之间定义的是**规则区块**
这一部分是用正则表达式去描述记号，如果输入能匹配正则表达式，则执行后边的C代码(C代码称为动作[action])

28行%%规则区块结束后的区域称为**用户代码区块**,可以自由编写C代码

### 2.2.2 简单正则表达式讲座

```java
// TODO 详解
```

### 2.2.3 yacc

[calculator.y](calculator/calculator.y)

yacc/bison自动生成语法分析器的工具，输入文件后缀为`.y`文件,输出语法分析器的C语言代码

```bison
 #define YYDEBUG 1 /* 设置为非0值，开启debug模式，可以看到程序中语法分析的状态 */
```

移进(shift)
归约(reduce)


### 2.2.4 生成执行文件

### 2.2.5 理解冲突所代表的含义

### 2.2.6 错误处理

## 2.3 不借助工具编写计算器

### 2.3.1 自制词法分析器
 补充:
* 保留字(关键字)
* 避免重复包含

### 2.3.2 自制语法分析器

 补充: 
* 预读记号的处理
## 2.4 少许理论知识--LL(1)与LALR(1)
 补充:
* Pascal/C中的语法处理诀窍
## 2.5 习题:扩展计算器

### 2.5.1 让计算器支持括号

### 2.5.2 让计算器支持负数