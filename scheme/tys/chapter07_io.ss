;;;; 输入输出
;; 读取内容 可以是一个字符、一行数据、S表达式
;; 没有更多内容可读，读取程序将会返回一个特殊的数据--文件结束符或EOF对象
;; 文件结束符或EOF对象使用eof-object?来判断
;; read-char 从端口读取下一个字符
;; read-line 读取下一行数据，返回一个字符串(不包括换行符)
;; write-char 向输出端口写入一个给定的字符(不包括\#)
;; write 和 display 都可以向端口写入一个给定的S表达式
;; 文件端口 (current-input-port和current-output-port零参数过程)
(display 10 )
; 10
(display 10 (current-output-port))
; 10
;; 文件端口自动打开和关闭


