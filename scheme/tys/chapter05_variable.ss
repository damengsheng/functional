;;;; 词法变量
;; 作用域
;; 修改变量的值
;; 全局修改(全局变量)与在lambda过程体中(局部变量)修改
(set! x 10)
;; 定义过程add3
(define add3
    (lambda (x)
      (+ x 3)
      x))
(add3 20)
; 20
;; 修改值
;; 过程体中修改值不会影响全局变量
(define var 10)
(define add3
    (lambda (x)
      (set! x (+ x 3))
      x))
(add3 var)
; 13
;; 没有参数的过程 lambda()中没有传入参数
(define add4
    (lambda ()
      (set! var4 (+ var4 4))
      var4))
(add4)
; 第一次调用 8
(add4)
; 第二次调用 12
(add4)
; 第三次调用 16
;; let　和 let*
;; 用于创建局部变量
;; 
(let ((x 1)
        (y 2)
        (z 3))
    (list x y z))
; (1 2 3)
;; let* 用先创建的变量来为后创建的变量赋值
(let* ((x 100)
         (y x))
    (list x y))
; (100 100)
;; lambda过程作为值赋值给变量
(let ((cons (lambda (x y) (+ x y))))
	(cons 1 2))
; 3
;; fluid-let
;; 将一个词法变量临时设置为一个固定值
(fluid-let ((counter 99))
    (display (counter))
    (newline)
    (display (counter))
    (newline)
    (display (counter))
    (newline))
