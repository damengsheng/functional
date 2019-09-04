# SICP Practice

## 练习1.10

[Ackermann 函数](https://en.wikipedia.org/wiki/Ackermann_function)
[阿克曼函数](https://zh.wikipedia.org/wiki/阿克曼函數)

$A(m,n)={\begin{cases}n+1&{\mbox{if }}m=0\\A(m-1,1)&{\mbox{if }}m>0{\mbox{ and }}n=0\\A(m-1,A(m,n-1))&{\mbox{if }}m>0{\mbox{ and }}n>0.\end{cases}}$