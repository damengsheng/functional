;;;; 条件语句
;; if 条件判断基本结构
; (if (条件测试)
; 	(then分支)
; 	(else分支))
(define compare
   (lambda (x) 
     (if ( > x 10)
        (display "true")
        (display "false"))))
(compare 3)
; false
(compare 11)
; true
;; while
(define a 10)
(define b 20)
(when (< a b)
	(display "a是")
    (display a)
    (newline)
    (display "b是")
    (display b)
    (newline)
    (display "a大于b"))
;; unless
;; 与while表达的意思相反
(define a 10)
(define b 20)
(unless (>= a b)
    (display "a是")
    (display a)
    (newline)
    (display "b是")
    (display b)
    (newline)
    (display "a大于b" ) )
;; cond
;; cond是多重if条件判断语句的一种替代方式
(if (< 100 10)-1
      (if (= 100 10)0
          1))
; 1
 (cond ((< 100 10)-1)
         ((= 100 10)0)
         (else 1)))
; 1
;; case 表达式
(define c #\b)
(case c
  ((#\a) 1)
  ((#\b) 2)
  ((#\c) 3)
  (else 4))
; 2
;;; and 和 or (逻辑与和逻辑或运算)
;; and 运算返回值是真
;; and运算结果是最后一个子结构的值,如果任何一个子结构的值都是假，则返回#f
;; or返回他的第一个值为真的子结构的结果。如果任何一个子结构的值都为假，则返回#f
;; 短路，当某个子结构可以决定最终结果时，and和or会忽略剩余的子结构
(and 1 2)
; 2
(and #f #t)
; #f
 (or #t #t)
; #t
(or #f #t)
; #t
(or #t #f)
; #t
(or #f #f)
; #f
