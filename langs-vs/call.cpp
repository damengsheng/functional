#include <stdio.h>

struct I
{
    virtual void f() = 0;
    virtual long long get() = 0;
};

class S1 : public I
{
    long long a;

public:
    virtual void f() override
    {
        a++;
    }

    virtual long long get() override
    {
        return a;
    }
};

class S2 : public I
{
    long long a;

public:
    virtual void f() override
    {
        a--;
    }

    virtual long long get() override
    {
        return a;
    }
};

int main()
{
    I *ia[3] = {new S1(), new S2(), new S1()};
    for (long long j = 0; j < 1000000000; j++)
        ia[j % 3]->f();
    printf("%lld\n", ia[0]->get() + ia[1]->get() + ia[2]->get());
    return 0;
}
