#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "yson.h"

static int main_ret = 0;
static int test_count = 0;
static int test_pass = 0;

#define EXPECT_EQ_BASE(equality, expect, actual, format)                                                           \
    do                                                                                                             \
    {                                                                                                              \
        test_count++;                                                                                              \
        if (equality)                                                                                              \
            test_pass++;                                                                                           \
        else                                                                                                       \
        {                                                                                                          \
            fprintf(stderr, "%s:%d: expect: " format " actual: " format "\n", __FILE__, __LINE__, expect, actual); \
            main_ret = 1;                                                                                          \
        }                                                                                                          \
    } while (0)

#define EXPECT_EQ_INT(expect, actual) EXPECT_EQ_BASE((expect) == (actual), expect, actual, "%d")

static void test_parse_null()
{
    yson_value v;
    v.type = YSON_FALSE;
    EXPECT_EQ_INT(YSON_PARSE_OK, yson_parse(&v, "null"));
    EXPECT_EQ_INT(YSON_NULL, yson_get_type(&v));
}

static void test_parse_expect_value()
{
    yson_value v;

    v.type = YSON_FALSE;
    EXPECT_EQ_INT(YSON_PARSE_EXPECT_VALUE, yson_parse(&v, ""));
    EXPECT_EQ_INT(YSON_NULL, yson_get_type(&v));

    v.type = YSON_FALSE;
    EXPECT_EQ_INT(YSON_PARSE_EXPECT_VALUE, yson_parse(&v, " "));
    EXPECT_EQ_INT(YSON_NULL, yson_get_type(&v));
}

static void test_parse_invalid_value()
{
    yson_value v;
    v.type = YSON_FALSE;
    EXPECT_EQ_INT(YSON_PARSE_INVALID_VALUE, yson_parse(&v, "nul"));
    EXPECT_EQ_INT(YSON_NULL, yson_get_type(&v));

    v.type = YSON_FALSE;
    EXPECT_EQ_INT(YSON_PARSE_INVALID_VALUE, yson_parse(&v, "?"));
    EXPECT_EQ_INT(YSON_NULL, yson_get_type(&v));
}

static void test_parse_root_not_singular()
{
    yson_value v;
    v.type = YSON_FALSE;
    EXPECT_EQ_INT(YSON_PARSE_ROOT_NOT_SINGULAR, yson_parse(&v, "null x"));
    EXPECT_EQ_INT(YSON_NULL, yson_get_type(&v));
}

static void test_parse_true()
{
    yson_value v;
    v.type = YSON_FALSE;
    EXPECT_EQ_INT(YSON_PARSE_OK, yson_parse(&v, "true"));
    EXPECT_EQ_INT(YSON_TRUE, yson_get_type(&v));
}

static void test_parse_false()
{
    yson_value v;
    v.type = YSON_TRUE;
    EXPECT_EQ_INT(YSON_PARSE_OK, yson_parse(&v, "false"));
    EXPECT_EQ_INT(YSON_FALSE, yson_get_type(&v));
}

static void test_parse()
{
    test_parse_null();
    test_parse_expect_value();
    test_parse_invalid_value();
    test_parse_root_not_singular();
    test_parse_invalid_value();
    test_parse_root_not_singular();
}

int main()
{
    test_parse();
    printf("%d/%d (%3.2f%%) passed\n", test_pass, test_count, test_pass * 100.0 / test_count);
    return main_ret;
}
