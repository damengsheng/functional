# OCaml

## Ocaml 基本环境

### `OCaml` **REPL**环境退出
```ocaml
exit 0
```

*REPL*: 交互式解释器(Read Evaluate Print Loop)

## 基本语法

### 注释

```ocaml
(* 单行注释 *)

(*
 * 多行
 * 注释
 *)

(* 嵌套注释
  let num = 
    (* 如果 x = 1 *) 1
    (* 如果 x = 2 *) 2
*)
```

### 基本类型

| 基本类型   | 类型描述                       |
| :----- | :------------------------- |
| int    | 31bit/63bit有符号整型(取决于运行的系统) |
| float  | IEEE 双精度浮点类型               |
| bool   | 布尔型 true/false             |
| char   | 8bit字符型                    |
| string | 字符串类型                      |
| unit   | ()                         |

*注*:  
- `int` 类型中需要有 **1bit** 来做内存管理(垃圾收集), 所以 `int` 类型所占空间是 `31bit` 或者是 `63bit`  
- `float` 是双精度浮点类型, `OCaml`没有单精度浮点类型
- `char` **不支持Unicode/ UTF\-8**

### 操作基本类型

`OCaml` 在涉及到操作数类型不一致的情况下都需要显式类型转换  
```ocaml
(* 整数和整数相加 *)
1 + 1

(* 浮点数和整数相加, float_of_int 是显式转换操作数类型 *)
float_of_int 1 +. 2.3
```

### 函数

#### 普通函数和递归函数

普通函数的定义使用 `let` 修饰, 递归函数使用 `let rec` 定义. 普通函数和递归函数有_定义域_的区别.  
当使用 `let` 声明的一个函数, 在调用这个函数时会试图寻找这个已经存在的(之前定义过的)函数而不是现在正在被定义的函数

#### 函数类型

对于一个函数, 函数输入参数是 `arg1`, `arg2`, `arg3` ... `argn` 返回类型是 `rettype`  
可以表示为函数类型: `f : arg1 -> arg2 -> arg3 -> ... -> argn -> rettype` _所谓的**Currying『科里化』**_  
例如 `repeated` 函数, 需要输入参数是一个字符串和一个整数返回一个字符串  
函数类型为 `repeated : string -> int -> string`

#### 多态函数

示例:  
```ocaml
let always_return_three x = 3;;
```
这个函数**接收任意类型**的参数, 但总是返回`3`  
`ocaml` 把这个函数解释成(函数类型) `val always_return_three : 'a -> int = <fun>`, `'a` 表示任意类型

### 类型推导

在方法声明的时候不需要指定参数类型, 声明变量的时候不需要指定变量类型  
示例:  
```ocaml
let average a b = 
  (a +. b) /. 2.0;;
```

`+.` 、 `/.` 操作符操作的是两个浮点型数字  
函数类型是: `average : float -> float -> float`

### 程序结构