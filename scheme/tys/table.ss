;; 生成表

;; 表元素--Cons单元
;; 调用cons函数生成Cons单元(点对)
(cons 1 2)
;; (1 . 2)
;; 函数cons (construction 构造器)给两个值分配内存空间,
;; 指向1的地址放在叫car的空间 (Contents of the Address part of the Register)
;; 指向2的地址的空间叫cdr (Contents of the Decrement part of the Register)

;; 多个Cons单元
(cons 1 (cons 2 3))
;; (1 2 . 3)
(cons (cons 0 1) (cons 1 2))
;; ((0 . 1) 1 . 2)

;; 不同类型的数据存放
(cons 1 "Hello")
;; (1 . "Hello")
(cons #\y "yakir")
;; (#\y . "yakir")
;; #\y 表示的是字符y , #\a表示的是字符a

;; 表
;; 表是通过Cons单元的cdr部分连接到下一个Cons单元开头的实现
;; 空表的描述'()
;; 空表
(quote ())
;; 替代写法
'()

;; 原子(atom)
;; 不使用Cons单元的数据结构称为原子(atom)
;; 数字，字符，字符串，向量和空表都是原子
;; '()既是原子又是表

;; 练习👻
;; ("hi" . "everybody") -- (cons "hi" "everybody")
;; (0) -- (cons 0 '()) 
;; (1 10 . 100) -- (cons 1 (cons 10 100))
;; (1 10 100) -- (cons 0 (cons 10 (cons 100 '()))) 
;; (#\I "saw" 3 "girls") -- (cons #\I (cons "saw" (cons 3 (cons "girls" '()))))
;; ("Sum of" (1 2 3 4) "is" 10) -- (cons "Sum of" (cons (cons 1 (cons 2 (cons 3 (cons 4 '())))) (cons "is" (cons 10 '())))) 
;; 引用
;; 所有记号都会从最内层的括号开始依次向外层括号求值，且最外层括号返回的值将作为S-表达式的值
;; 引用可以用来阻止记号被求值
;; 它用来将符号或者表原封不动传递给程序，而不是求值后变成其他

;; CAR操作符
