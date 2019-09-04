;; 1.2 过程与它们所产生的计算
;; 文中举例新手下象棋，知道了下象棋过程中移动棋子的规则，却还不知道典型的开局、战术、策略。
;; 缺少对各步棋价值的思考、缺少走了这步棋的后果的预测的经验
;; 具备看清所考虑的动作的后果的能力

;; 1.2.1 线性的递归和迭代
;; 阶乘 n! = n * (n - 1)!
;; 计算过程构造起一个延迟进行的操作引起的链条，收缩阶段表现为这些运算的实际执行
(begin
	(define (factorial n)
		(if (= n 1)
        	1
        	(* n (factorial (- n 1)))))
	(display
		(factorial 10))
    (newline))
;; 迭代计算过程：状态可以用固定数目的状态变量描述的计算过程，
;; 与此同时，又存在着一套固定的规则，描述了计算过程在从一个状态到下一个状态装换时，这些变量的更新方式，
;; 还有一个结束检测(如果有的话)，它描述这一计算过程应该终止的条件
(begin
    (define (fact-iter product counter max-count)
		(if (> counter max-count)
			product
			(fact-iter (* counter product)
				(+ counter 1)
				max-count)))
    (define (factorial num)
    	(fact-iter 1 1 num))
    (display 
    	(factorial 10))
    (newline))
;; 练习1.9
;; 代换模型展示求值过程(把+号替换成plus了这样可以剔除一些误导)
;; inc: 将参数加1 dec: 将参数减1
(define (plus a b)
	(if (= a 0)
		b
		(inc (plus (dec a) b))))
; (plus 3 5)
; (inc (plus 2 5))
; (inc (inc (plus 1 5)))
; (inc (inc (inc (plus 0 5))))
; (inc (inc (inc 5)))
; (inc (inc 6))
; (inc 7)
; 8
(define (plus a b)
	(if (= a 0)
		b
		(plus (dec a) (inc b))))
; (plus 3 5)
; (plus 2 6)
; (plus 1 7)
; (plus 0 8)
; 8
;; 练习1.10
;; Ackermann函数(阿克曼函数)，求值
;; sicp_practice.md#练习1.10
(define (A x y)
	(cond ((= y 0) 0)
		((= x 0) (* 2 y))
		((= y 1) 2)
		(else (A (- x 1)
			(A x (- y 1))))))
; (A 1 10)
; (A 0 (A 1 9))
; (A 0 (A 0 (A 1 8)))
; .
; .
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 1))))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 2)))))))))
; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 4)))))))
; .
; .
; (A 0 (A 0 256)
; (A 0 512)
; 1024
(A 2 4)
(A 3 3)
;; trace版本，打出调用链
(define trace-A
    (trace-lambda A (x y)
      (cond ((= y 0) 0)
        ((= x 0) (* 2 y))
        ((= y 1) 2)
        (else (A (- x 1)
                 (A x (- y 1)))))))
(trace-A 1 10)
(trace-A 2 4)
(trace-A 3 3)
;; 给出数学表达式
; (define (f n) (A 0 n))		2 * n
; (define (g n) (A 1 n))		2^n
; (define (h n) (A 2 n))		连续求n次二次幂
; (define (k n) (* 5 n n))		5*n^2

;; 树形递归






























