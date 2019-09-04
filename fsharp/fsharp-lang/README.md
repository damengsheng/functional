# Learning F\# #

## 基本语法

### 注释

```F#
(* 
    块
    注释
*)
// 行注释
```

### `let`修饰符

`let` 修饰符可以用来修饰标识符也可以修饰函数


### 函数和模块
`F#` 中

### String
string 类型是 `System.String` 类型的别名
```F#
// 1.
open System
let name: String = "yakir"
// 2.
let name: System:String = "yakir"
// 3.
let name: string = "yakir"
// 三种方式声明字符串类型变量

let name = "yakir"
let name = @"yakir """
let name = """yakir """
```


##### References
[fsharp.org](http://fsharp.org)
[fsharp-cheatsheet](http://dungpa.github.io/fsharp-cheatsheet/)
[F# 语言概念](https://docs.microsoft.com/zh-cn/dotnet/fsharp/language-reference/index)