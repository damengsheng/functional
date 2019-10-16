# exit.s
    .section    __TEXT,__text   # 可以使用 .text 代替
    .globl      _main
_main:                          # 执行起点
    movq    $0, %rax
    retq
