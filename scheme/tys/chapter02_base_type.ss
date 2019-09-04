;;;; chapter 02 基本数据类型
;; boolean(布尔类型)
;; number(数字类型)
;; character(字符类型)
;; symbol(标识符类型)

;;; boolean类型 #t #f 标记 true和false
;; boolean? 检测参数是否为boolean类型
(boolean? #t)
; #t
(boolean? #f)
; #t
(boolean? "hello")
; #f
;; not 取相反值
(not #t)
; #f
(not #f)
; #t
(not "hello")
; #f
; 在一个需要boolean类型的上下文中，scheme会将任何非#f的值看成是true

;;; number类型
; integer(整数 1)  rational(有理数 1/3) real(实数 3.1415926) complex(复数 1+1i)
; 整数 -> 有理数 -> 实数 -> 复数 -> 数字类型
;; number? 检测参数是否为数字类型
(integer? 1/3)
; #f
(integer? 1)
; #t
(rational? 1/3)
; #t
(rational? 1+1i)
; #f
(real? 3.1415926)
; #t
(real? 1+1i)
; #f
(complex? 1+1i)
; #t
;; 二进制表示		#b 
;; 八进制表示  	#o
;; 十六进制表示 	#x
;; decimal前缀 	#d (可选项)
(+ #b10000000)
; 128
;; 比较
;; eqv?		引用判断
;; = 		数字之间的比较
(eqv? #t 0)
; #f
(eqv? 1 1.000000000)
; #f
(= 1 1.000000000)
; #t 
;; 1 与 1.000000000 比较 对比 eqv? 与 = 的区别
;(= 42 #f)
; 抛出异常
; Exception in =: #f is not a number
(>= 4.5 3)
; #t
(< 3 2)
; #f
;; 加、减、乘、除、平方根、指数、对数、绝对值
(+ 1 2)
(+ 1 2 3)
(- 0 999)
(* 10 0)
(/ 10 1)
(sqrt 8)
(exp 2/3)
(log 1000)
(abs -3) ; 3
;; 三角函数 sin, cos, tan, asin, acos, atan
;; 最大值、最小值
(max 1 4 5 -9 0 13 5 2 10 9)
; 13
(min 1 4 5 -9 0 13 5 2 10 9)
; -9

;;; charactor 字符类型
;; char? 检查参数是否为字符类型
(char? #\,)
; #t
(char? #\a)
; #t
(char? 1)
; #f
;; 比较判断码表顺序 char=?, char<?, char<=?, char>?, char>=?
(char>=? #\c #\c)
; #t
(char<? #\b #\c)
; #t
;; 忽略大小写的比较 char-ci
(char-ci=? #\A #\a)
; #t
;; 大小写转换  char-downcase转换成小写 char-upcase转换成大写
(char-downcase #\A)
; #\a
(char-upcase #\b)
; #\B

;;; symbol 标识符类型(引用类型)
;; 与字符串的区别(不局限于这几点): 
;; "symbol"这是字符串(单纯理解为使用了引号), symbol这是symbol类型 (是不是很单纯😑

;; 简单类型有一个"自运算"的概念,意思是在Term输入这些类型的数据，运算后返回和输入是一样的内容
;; symbol通常用来做变量的标识,承载运算后的值
;; * 创建symbol类型标识符而非变量
(quote not_variable)
; 等价于 'not_variable
; not_variable
;; 检查symbol类型标识符
(symbol? (quote 标识符))
; #t
(symbol? 1)
; #f
;; 标识符比较 
;; 注： 教程指明symbol类型不区分大小写 实际测试使用 Chez Scheme是区分下小写的
;; 教程中的语句(eqv? 'Calorie 'calorie) 返回 #t 实际测试返回 #f
(eqv? 'A 'a)
; #f
(eqv? 'a 'a)
; #t
;; 全局变量声明
(define i 9)
i ; 调用
; 9 返回9
;; 修改全局变量的值，i没有声明的情况下，声明初始化
(set! i #\c)
i ; 调用
; #\c 返回值#\c

;;; 复合类型
;; String 类型
;; string 参数类型检查
(string? "字符串")
; #t
(string? #\a)
; #f
;; 字符合并成字符串
(begin 
	(display (string #\H #\e #\l #\l #\o #\  #\S #\c #\h #\e #\m #\e #\  #\!))
	(newline))
; 显示 Hello Scheme !
(begin 
	(define name "yakirChen")
	(display name)
	(newline))
;; 从字符串中获取指定索引号的字符
(string-ref name 5)
; #\C
;; 字符串连接
(begin 
	(define name "Scheme")
	(define greeting "Hello")
	(define spilt " " )
	(display (string-append greeting spilt name " !"))
	(newline))
;; 定义一个指定长度的字符串使用默认值填充
(define three_char_string (make-string 3))
; "\x0;\x0;\x0;"
;; 定义一个指定长度的字符串使用字符a填充
(define three_char_string (make-string 20 #\a))
; "aaaaaaaaaaaaaaaaaaaa"
;; 替换字符串中的字符
(string-set! three_char_string 1 #\b)
(string-set! three_char_string 2 #\c)
; "abcaaaaaaaaaaaaaaaaa"
;; 注: 调用string make-string string-append获得的字符串都是可以更改的
;; 使用 string-set! 就可以替换字符串指定索引出的字符

;;; vector 向量
;; 检查向量
(vector? (vector 0 1 2 3 4 5))
; #t
;; 创建向量
(vector 0 1 2 3 4)
; #(0 1 2 3 4)
;; 构造指定长度的向量,使用默认值填充
(define vector_var (make-vector 5))
; #(0 0 0 0 0)
;; 修改向量指定索引位置的值
(vector-set! vector_var 3 1)
; #(0 0 0 1 0)
;; 获取向量中索引位置的值
(vector-ref vector_var 3)
; 1

;;; 点对 -- 把两个任意类型的值有序地链接起来，成为一个点对
;;; 点对的第一个值称为car , 点对的第二个值称作cdr，江两个值组合成点对的过程是cons
;; 构造点对(声明)
(cons 1 #t)
; 或者
'(1 . #t)
; (1 . #t)
(define cc '(1 . #t))
;; 访问点对中的元素
(car cc)
; 1
(cdr cc)
; #t
;; 修改点对元素的值
(set-car! cc 2) ; 修改cc第一个元素的值为2
(car cc) 
; 2
(set-cdr! cc #f)
(cdr cc)
; #f
;; 点对包含点对
(define x '((1 . 2) . 3))
; (define x (cons(cons 1 2) 3)) ; 等同上
; 获取值
; 使用组合的方式获取值, 最多支持四级操作例如 cdaddr
; (caar x) 等同于 (car (car x))
; (cdar x) 等同于 (cdr (car x))

(car (car x)) ; (caar x)
; 1
(cdr (car x)) ; (cdar x)
; 2
(cdr x)
; 3
;; 表达式简化
'(1 . (2 . (3 . (4 . 5)))) ; (cons 1 (cons 2 (cons 3 (cons 4 5))))
; (1 2 3 4 . 5) 是对 (1 . (2 . (3 . (4 . 5))))的一种简化
; 最后一个元素为空表
(define x '(1 . (2 . (3 . (4 . ())))))
; (1 2 3 4)
;; 指定索引号访问列表元素
(list-ref x 1)
; 2
(list-tail x 2)
; (3 4)

;; 数据类型转换
;; char->integer 字符转换成整型， 
;; integer->char 整型转换成字符(ascii码)
;; string->list  字符串转换成字符列表
;; list->string， vector->list list->vector  number->string symbol->string string->symbol .....
(char->integer #\a)
; 97
(integer->char 50)
; #\2
(string->list "yakir")
; (#\y #\a #\k #\i #\r)
(symbol->string 'a)
; "a"
(string->symbol "string hello")
; string\x20;hello

;;; 其他数据类型
;; procedure(过程)

(display "Hello Scheme .")
; display过程，向控制台输出字符串
(display "Hello Scheme ." (current-output-port))


;;; S-expression (S表达式)
;; 所有讨论过的数据类型统一成一种数据类型叫作s-expression (符号表达式或者s-表达式)

