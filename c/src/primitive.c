//
//  primitive.c
//  c
//
//  Created by yakir on 2019/2/25.
//  Copyright © 2019 yakir. All rights reserved.
//

#include <stdio.h>
#include <stdint.h>
#include <inttypes.h>
#include <complex.h> // 复数和虚数
#include <limits.h>  // 整型限制
#include <float.h>   // 浮点型限制

void printf_number();
void printf_chars();
void portable_types(); // 可移植类型
void type_size();      // 类型大小
void printf_float();
void convert();

int main()
{
    printf_number();

    printf_chars();

    portable_types();

    type_size();

    printf_float();
    
    convert();

    return 0;
}

void printf_number()
{
    int ten = 10;
    short sh = 0;
    unsigned int uni = 1;
    long lo = 2000000000L;
    long long ll = 3000000000LL;
    unsigned long ul = 4000000000UL;
    float aboat = .05;
    double abet = 2.14e9;
    long double dip = 5.32e-5;

    printf("short %%ho %ho\n", sh);
    printf("%d  八进制 %o \t十六进制 %x \t十六进制大写 %X\n", ten, ten, ten, ten);
    printf("前缀 %d 八进制 %#o \t十六制 %#x \t十六进制大写 %#X\n", ten, ten, ten, ten);
    printf("unsigned int %u\n", uni);
    printf("long %ld long十六进制 %lx long八进制 %lo\n", lo, lo, lo);
    printf("long long %lld\n", ll);
    printf("unsigned long %lu\n", ul);
    printf("float %a %f %A %e %E\n", aboat, aboat, aboat, aboat, aboat);
    printf("double %a %f %A %e %E\n", abet, abet, abet, abet, abet);
    printf("double %Lf %Le %La\n", dip, dip, dip);
}

/*
输出: 
1234.560000
1.234560e+03
1234.56
1234.6
  1234.560
 1.235E+03
+1234.560
0001234.56
*/
void printf_float()
{
    const double RENT = 1234.56;

    printf("%f\n", RENT);
    printf("%e\n", RENT);
    printf("%4.2f\n", RENT);
    printf("%3.1f\n", RENT);
    printf("%10.3f\n", RENT);
    printf("%10.3E\n", RENT);
    printf("%+4.3f\n", RENT);
    printf("%010.2f\n", RENT);
}

void printf_chars()
{
    char a;

    a = 65;
    printf("65 %src\n", a);

    a = 'A';
    printf("A %hhd\n", a);
}

void portable_types()
{
    int32_t is32; // 有符号整型
    is32 = 45933945;
    printf("signed int %d\n", is32);
    printf("宏(macro) %" PRId32 "\n", is32);
    printf("等价于 %"
           "d"
           "\n",
           is32);
}

void type_size()
{
    size_t int_size = sizeof(int);
    printf("int size of %zu bytes .\n", int_size);

    printf("Biggest int %d\n", INT_MAX);
    printf("Float Epsilon %e\n", FLT_EPSILON);
}


void convert()
{
    int i = (int)1.1;
    printf("1.1 case to int %d\n", i);
}