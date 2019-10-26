# exit.s
    .section    __TEXT,__text   # 可以使用 .text 代替
    .globl      _main
    .equ        zero    0x0
_main:                          # 执行起点
    movq    $zero, %rax
    retq
