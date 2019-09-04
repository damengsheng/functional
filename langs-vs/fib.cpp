#include <stdio.h>

long long fib(long long n)
{
    return n <= 2 ? 1 : fib(n - 1) + fib(n - 2);
}

int main()
{
    printf("%lld\n", fib(45));
    return 0;
}
