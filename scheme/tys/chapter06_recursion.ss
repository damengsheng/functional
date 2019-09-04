;;;; 递归
;; 一个过程自己对自己的调用
(define recursion
	(lambda (n)
		(if (= n 0)
			1
			(* n (recursion (- n 1))))))
(recurion 10) ; 调用
; 3628800 返回结果
;; 相互递归
(define is-even?
	(lambda (n)
		(if (= n 0) #t
			(is-odd? (- n 1)))))
(define is-odd?
	(lambda (n)
		(if (= n 0) #f
			(is-even? (- n 1)))))
;; letrec (局部递归，相互递归)
(letrec ((local-even? 
             (lambda (n)
               (if (= n 0)
                   (local-odd? (- n 1)))))
           (local-odd? 
             (lambda (n)
               (if (= n 0)
                   (local-even? (- n 1))))))
    (list (local-even? 23)(local-odd? 23)))
; (#<void> #<void>)
;; 命名let
;; 实现递减数列
 (letrec ((countdown (lambda (i)
                        (if (= i 0) 'end
                            (begin
                              (display i)
                              (newline)
                              (countdown (- i 1)))))))
    (countdown 10))
; 10
; 9
; 8
; 7
; 6
; 5
; 4
; 3
; 2
; 1
; end
;; 变体let 同等letrec的实现
(let countdown ((i 10))
    (if (= i 0) 'end
        (begin
          (display i)
          (newline)
          (countdown (- i 1)))))
; 10
; 9
; 8
; 7
; 6
; 5
; 4
; 3
; 2
; 1
; end
;;; 迭代，Scheme只有通过递归才能定义循环，不存在特殊的循环或迭代结构
;; 尾递归 (tail-call elimination 消除尾部调用的过程)
;;; 自定义过程映射整个列表
;; map 对给定的每个元素提供一个既定的程序，并返回一个结果列表
(define add (lambda (x)
                (+ x 2)))
(map add '(1 2 3))
; (3 4 5)
;; for-each 对列表中的每个元素提供一个既定程序，但是返回值为空(在运行过程产生副作用)
(for-each display
    (list "one " "two " "three"))
; one two three
;; map 接收多个列表
(map cons '(1 2 3) '("one" "two" "three"))
; ((1 . "one") (2 . "two") (3 . "three"))
















