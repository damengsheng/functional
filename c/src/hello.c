//
//  hello.c
//  c
//
//  Created by yakir on 2019/2/25.
//  Copyright © 2019 yakir. All rights reserved.
//

#include <stdio.h>

int main()
{
    float height, weight, bmi;

    printf("身高(m) ?\n");
    printf("$_.__\b\b\b\b");
    scanf("%f", &height);

    printf("\n体重(kg) ?\n");
    printf("$__.__\b\b\b\b\b");
    scanf("%f", &weight);

    bmi = weight / (height * height);
    // 精准控制输出, 保留4位小数点
    printf("\nBMI %.4f\n", bmi);

    // 调用两次
    // 1. 读取换行符
    getchar();
    // 2. 让程序暂停等待输入
    getchar();

    return 0;
}

// 这是注释
/* 这是注释 */
/*
 这是注释
 */
