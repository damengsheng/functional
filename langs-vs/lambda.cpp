#include <stdio.h>
#include <functional>

int main()
{
    long long a = 0;
    std::function<void()> ia[3] = {
        [&]() { a++; },
        [&]() { a--; },
        [&]() { a++; },
    };
    for (long long j = 0; j < 1000000000; j++)
        ia[j % 3]();
    printf("%lld\n", a);
    return 0;
}
