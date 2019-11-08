# chapter 3 语法分析的概要

## 词法分析 **lexical analyze**
也称之为`扫描(scan)`
负责词法分析的模块称为**词法分析器(lexical analyzer)**,又称**扫描器(scanner)**
读入源码字符序列，组织成有意义的**词素(lexeme)**的序列, 每个词素将产生**词法单元(token)**作为输出

词法单元的形式:
`<第一分量:token-name, 第二分量:attribute-value>`

工具: 
flex/lex(生成词法分析器的工具)
源码文件后缀`.l`

## 语法分析 *syntax analysis*
工具: 
bison/yacc(生成语法分析器的工具)
源码文件后缀`.y`


## 扫描器生成器 *scanner generator*

## 解析器生成器 *parser generator*

解析器生成器的种类 (处理能力自上而下递减，处理速度自上而下递增，也就是说处理能力和处理速度成反比)
- LR解析器生成器
- LALR解析器生成器
- LL解析器生成器
