;;;; forms代码结构
;; procedures(过程)
;; 例如cons string-list .....
;; 使用lambda创建自定义过程
(lambda (x) (+ x 2))
; #<procedure>
;; 定义并使用这个过程
((lambda (x y) (+ x y)) 4 5)
(define add2 (lambda (x) (+ x 2))) ; 给(lambda (x) (+ x 2))这个过程取个名称叫add2
; 使用自定义过程
(add2 5)
; 7
(lambda (x y) (+ x y))
;; 自定义过程简化声明(省略lambda)
(define (add3 x)
	(+ x 3))
(add3 3)
;; 过程的参数
;; lambda过程的参数由它的第一个子结构定义 
;; (lambda (x) (+ x 2)) (lambda (x y) (+ x y)) 这两个过程中 
;; (x) (x y) 定义的就是参数
;; 可变数量的参数(不定长参数)
;; apply过程
;; 允许我们直接传递一个装有参数的list给一个过程来完成对这个过程的批量操作
(apply + '(1 2 3 4 5 6 7 8 9 10))
; 55
;; 顺序执行
;; begin的使用
(define display_str
    (lambda (arg arg0 arg1 arg2)
      (begin
        (display arg)
        (display " ")
        (display arg0)
        (display " ")
        (display arg1)
        (display " ")
        (display arg2)
        (display " ")
        (newline))))
(display_str 1 2 3 4)
; 1 2 3 4
