#ifndef YSON_H__
#define YSON_H__

/* yakir's  json */

typedef enum yson_type
{
    YSON_NULL,
    YSON_FALSE,
    YSON_TRUE,
    YSON_NUMBER,
    YSON_STRING,
    YSON_ARRAY,
    YSON_OBJECT
} yson_type;

typedef struct
{
    yson_type type;
} yson_value;

enum
{
    YSON_PARSE_OK = 0,
    YSON_PARSE_EXPECT_VALUE,
    YSON_PARSE_INVALID_VALUE,
    YSON_PARSE_ROOT_NOT_SINGULAR
};

int yson_parse(yson_value *v, const char *json);

yson_type yson_get_type(const yson_value *v);

#endif /* YSON_H__ */
