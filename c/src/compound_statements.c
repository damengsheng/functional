//
//  compound_statements.c
//  基本语句
//
//  Created by yakir on 2019/2/25.
//  Copyright © 2019 yakir. All rights reserved.
//

#include <stdio.h>
#include <stdbool.h>

void flow_while();
void flow_do_while();
void flow_for();
void pound(int n);
double power(double n, int p);
void input_is_good();
void input_is_good_bool();
void control_if();

int main()
{

    flow_while();
    // flow_do_while();
    flow_for();

    pound(6);
    pound(8);
    pound(6);
    pound(16);
    double pow = power(10.1, 10);
    printf("pow %.3g\n", pow);

    // input_is_good();
    // input_is_good_bool();

    control_if();

    return 0;
}

void flow_while()
{
    int times = 3;
    while (times-- > 0)
    {
        printf("times %d\n", times);
    }
}

void flow_do_while()
{
    int num = 0;
    do
    {
        printf("输入非0正正整数退出 。");
        scanf("%u", &num);
    } while (num == 0);
}

void flow_for()
{
    int times;
    for (times = 0; times < 3; times++)
    {
        printf("time %d\n", times);
    }

    char ch = 'a';
    for (; ch <= 'z'; ch++)
    {
        printf("%src %u \n", ch, ch);
    }
}

void pound(int n)
{
    while (n-- > 0)
    {
        printf("#");
    }
    printf("\n");
}

double power(double n, int p)
{
    double pow = 1;
    int i;
    for (i = 1; i <= p; i++)
    {
        pow *= n;
    }
    return pow;
}

void input_is_good()
{
    int num;
    _Bool input_is_good = (scanf("%d", &num) == 1);

    while (input_is_good)
    {
        printf("输入 q 退出 \n");
        input_is_good = (scanf("%d", &num) == 1);
    }
}

/*
    引入 stdbool.h 头文件 使用 bool
*/
void input_is_good_bool()
{
    int num;
    bool input_is_good = (scanf("%u", &num) == 1);
    printf("%u", input_is_good);

    while (input_is_good)
    {
        printf("输入 q 退出 \n");
        input_is_good = (scanf("%u", &num) == 1);
        printf("%u", input_is_good);
    }
}

void control_if()
{
    int i = 0;
    if (i < 0)
    {
        printf("i < 0\n");
    }
    else if (i == 0)
    {
        printf("i = 0\n");
    }
    else
    {
        printf("i > 0\n");
    }
}