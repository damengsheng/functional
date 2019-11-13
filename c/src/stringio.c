//
//  stringio.c
//  c
//
//  Created by yakir on 2019/2/25.
//  Copyright © 2019 yakir. All rights reserved.
//

#include <stdio.h>
#include <string.h>

#define MY_NAME "yakirChen"
#define PI 3.14159

const int MONTHS = 12;

void char_array();

int main()
{
    char_array();

    return 0;
}

void char_array()
{
    char name[20];
    printf("name ____\b\b\b\b");
    scanf("%s", name);
    short name_size = sizeof name;
    short name_len = strlen(name);
    printf("name %s\n", name);
    printf("name size %u length %u\n", name_size, name_len);

    printf("my name is %s .\n", MY_NAME);
}
