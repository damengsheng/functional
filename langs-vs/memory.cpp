#include <memory.h>
#include <stdio.h>

struct E
{
    int a;
    E(int aa) : a(aa) {}
};

int main()
{
    static const long long ARRAY_COUNT = 10000L;
    static const long long TEST_COUNT = ARRAY_COUNT * 100000L;

    E **es = new E *[ARRAY_COUNT];
    memset(es, 0, sizeof(E *) * ARRAY_COUNT);
    for (long long i = 0; i < TEST_COUNT; i++)
    {
        long long idx = i * 123456789L % ARRAY_COUNT;
        E *e = es[idx];
        if (e)
            delete e;
        es[idx] = new E((int)i);
    }

    long long n = 0;
    for (long long i = 0; i < ARRAY_COUNT; i++)
    {
        E *e = es[i];
        if (e)
            n += e->a;
    }
    printf("%lld\n", n);
    return 0;
}
