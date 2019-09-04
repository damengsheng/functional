;; 内容主体语言(Lisp方言Scheme sicp不是讲scheme语法哈，有除语法之外的东东要探求🤓)

;; 1 构造过程抽象

;; 心智的活动，除了尽力产生各种简单的认识之外，主要表现在如下三个方面：
;; 1) 将若干简单认识组合为一个复合认识，由此产生出各种复杂的认识。
;; 2) 将两个认识放在一起对照，不管它们如何简单或者复杂。在这样做时并不将它们合而为一。由此得到有关他们的相互关系的认识。
;; 3) 将有关认识与那些在实际和它们同在的所有其他认识隔离开，这就是抽象。

;; 有别于传统对程序“被动的”数据、“主动的”过程的划分
;; 使用Lisp描述的计算过程称为“过程”，这个“过程”本身又可以作为Lisp的数据来表示和操作。

;; 1.1 程序设计的基本元素
;; 语言: 1). 指挥计算机执行任务 2). 成为一种框架，能够在其中组织有关计算过程(将简单的认识组合起来形成更复杂认识或者叫方法)
;; 语言提供的三种机制: 
;; 1). 基本表达形式 	
;; 2). 组合方法 		简单方法构造复合元素
;; 3). 抽象方法		为复杂对象命名，当作单元去操作

;; 1.1.1 表达式
;; 基本表达式(组合式、复合表达式)
10

(+ 10 1)

(+ (* 3 
        (- 4 (* 0.5 2)))
    (+ (- 10 7)
        6))
        
;; 1.1.2 命名和环境
;; 使用define定义变量,允许使用一个简单的名称去引用一个组合运算的结果(给符号关联一个值)
;; 解释器必须维护某种存储能力，以便保持有关的名称-值对。这种存储成为“环境”(全局环境)
(define pi 3.1415926)
(begin
    (display pi)
    (newline))
 
(define res
    (+ (* 3
          (- 4 (* 0.5 2)))
       (+ (- 10 7)
          6)))
(begin
    (display res)
    (newline))

;; 1.1.3 组合式的求值(树形图表示)
;; 1). 求该组合式的各个子表达式
;; 2). 将作为醉左子表达式(运算符)的值的那个过程应用于相应的实际参数，所谓实际参数也就是其他子表达式(运算对象)的值。 (读不通顺嘛😂...

;; 1.1.4 复合过程
;; 1). 数和算术运算是基本的数据和过程。
;; 2). 组合是的嵌套提供了一种组织起多个操作的方法。
;; 3). 定义是一种首先的抽象手段，它为名字关联相应的值。
;; 复合过程定义
;(define (<name> <formal parmaters>)
;    <body>)
(begin
    (define (square num)
        (* num num))
    (define (sum-of-squares x y)
        (+ (square x) (square y)))
    (display
        (sum-of-squares 2 3)))

;; square 实现平方， sum-of-squares实现平方和
;; 分而治之，原本写这个demo时的想法是平方运算嵌套在加法运算中，现在的想法是功能要单一.... 功能要单一...功能要单一...
;; sum-of-squares 可以进一步和其他过程组合
(begin
    (define (func x)
        (sum-of-squares (+ x 1) (* x 5)))
    (display
        (func 10)))

;; 1.1.5 过程应用的代换模型
;; 过程中的形参用具体值代替
;; 上一章中的func过程接收参数x
;; (func 10)传入参数10,实参代换sum-of-squars过程中的形参，
;; (sum-of-squares (+ x 1) (* y 5))
;; (sum-of-squares (+ 10 1) (* 10 5))
;; 归约为对另一个组合式的求值，其中有两个运算对象，有关的运算符是sum-of-squares，执行相加过程
;; (+ (* 11 11) (* 50 50))
;; 得到(+ 121 2500) 最后得到 2621

;; 确定过程应用的“意义”的一种模型：
;; 1). 代换的作用只是为了帮助我们领会过程中调用情况，而不是对解释器实际工作方式的具体描述。通常的解释器都不采用直接操作过程的正文，
;; 用值去代换形式参数的方式，产生“代换”的效果。
;; 2). 会有更精化的模型取代它(代换模型)

;; 应用序和正则序
;; 1). 解释器首先对运算符和各个运算对象求值，而后将得到的过程应用于得到的实际参数
;; 2). 先不求出运算对象的值，直到实际需要他们的值时再去做。采用这种求值方式，必须首先运用对象表达式去代换形参，直到得到一个只包含基本运算符的表达式，然后再去执行求值。

;; 应用序求值：(参考上👆🏿)

;; 正则序求值：
;; (sum-of-supares (+ 10 1) (+ 10 5))
;; (+ (square 10 1) (square 10 5))
;; (+ (* (+ 10 1) (+ 10 1)) (* (* 10 5) (* 10 5)))
;; (+ (* 11 11) (* 50 50))
;; (+ 121 2500)
;; 2621

;; 1.1.6 条件表达式和谓词
;; 条件表达式：根据条件检测的结果作出相应的操作
;; 条件表达式的定义：
;; 1). cond 或者 cond ... else ...
;; (cond (<p1> <e1>)
;;        (<p2> <e2>)
;;        ...
;;        (<pn> <en>))
;; else 可以放在cond的最后一个子句中的<p>位置上
;; 2). if
;; (if (predicate> <consequent> <alternative>)
;; 当一个if表达式求值时，解释器从<predicate>部分开始求值，如果<predicate>得到真值，解释器就去求值<consequent>返回其值，否则求得<alternatice>值并返回
(begin
    (define (abs num)
        (cond ((> num 0) num)
            ((= num 0) 0)
            ((< num 0) (- num))))
    (display 
        (abs -1)))

(begin
    (define (abs num)
      (cond ((>= num 0) num)
        (else (- num))))
    (display
      (abs -1))
    (newline))

(begin
    (define (between x y)
        (and (> x 0) (> y 0))
        (* x y)
        (+ x y))
    (display
        (between 10 10))
    (newline)
    (display
        (between -1 10))
    (newline))

;; 练习1.2
(begin
    (define (add num1 num2)
        (+ num1 num2))
    (define (sub num1 num2)
        (- num1 num2))
    (define (div num1 num2)
        (/ num1 num2))
    (define (mult num1 num2)
        (* num1 num2))
    (display
        (div (add (add 5 4)
                (sub 2
                     (sub 3
                          (add 6
                               (div 4 5)))))
            (mult 3
                (mult (sub 6 2)
                    (sub 2 7))))))
;; 结果: -37/150
;; 练习1.3 求三个数中较大两数的平方和
;; 比较大小: 1). 决策树 2). bigger smaller
(begin
    (define (square num)
        (* num num))
    (define (sum-of-squares num1 num2)
        (+ (square num1)
            (square num2)))
    (define (decision-sum-of-squares x y z)
        (if (> x y)
            (if (> y z)
                (sum-of-squares x y)
                (sum-of-squares x z))
            (if (> x z)
                (sum-of-squares x y)
                (sum-of-squares z y))))
    (display
        (decision-sum-of-squares 1 2 3))
    (newline)    
    (display
        (decision-sum-of-squares 3 2 1))
    (newline)    
    (display
        (decision-sum-of-squares 10 11 12))
    (newline)

    (define (bigger x y)
        (if (> x y)
            x
            y))
    (define (smaller x y)
        (if (< x y)
            x
            y))

    (define (bigger-sum-of-squares x y z)
        (sum-of-squares (bigger x y)
            (bigger z
                (smaller x y))))
    (display
        (bigger-sum-of-squares 1 2 3))
    (newline)
    (display
        (bigger-sum-of-squares 3 2 1))
    (newline)
    (display
        (bigger-sum-of-squares 10 11 12))
    (newline))
;; 练习1.4
(define
    (a-plus-abs-b a b)
    ((if (> b 0) + -) 
        a
        b))
;; 练习1.5
;; 检测解释器是以应用序还是正则序解释程序
; (begin
;     (define (p) (p))
;     (define (test x y)
;         (if (= x 0)
;             0
;             y))
;     (test 0 (p)))
;; 程序调用进入无尽循环(infinite loop) p不断调用自身
;; 应用序: 解释器无响应
;; 正则序: 使用正则序求值从始至终都没有调用(p)

;; 1.1.7 实例：采用牛顿法求平方根
;; 计算机如何算出平方根🤔(牛顿的逐步逼近法... 详解见下
;; 递归逼近
;; 平方
(define (square x)
    (* x x))
;; 取两数的平均值
(define (average x y)
    (/ (+ x y) 2))
;; 获取下一个猜测的值
(define (improve guess x)
    (average guess (/ x guess)))
;; 判断获取的值是否复合自己的容差
(define (good-enough? guess x)
    (< (abs (- (square guess) x)) 0.00001))
(define (sqrt-iter guess x)
    (if (good-enough? guess x)
        guess
        (sqrt-iter (improve guess x)
          x)))

;; 1.1.8 过程作为黑箱抽象
;; 一个过程的定义可以隐藏起一些细节 (用户可以忽略实现细节？🤔
;; 这种嵌套的定义叫块结构
(define (sqrt x)
    (define (square x)
        (* x x))
    (define (average x y)
        (/ (+ x y) 2))
    (define (improve guess x)
        (average guess (/ x guess)))
    (define (good-enough? guess x)
        (< (abs (- (square guess) x)) 0.00001))
    (define (sqrt-iter guess x)
        (if (good-enough? guess x)
            guess
            (sqrt-iter (improve guess x) x)))
    (sqrt-iter 1.0 x))
;; 词法作用域 (形参x
(define (square x)
    (* x x))
(define (average x y)
        (/ (+ x y) 2))
(define (sqrt x)
    (define (good-enough? guess)
      (< (abs (- (square guess) x)) 0.001))
      (define (improve guess)
        (average guess (/ x guess)))
      (define (sqrt-iter guess)
        (if (good-enough? guess)
            guess
            (sqrt-iter (improve guess))))
      (sqrt-iter 1.0))

