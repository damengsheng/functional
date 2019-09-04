#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "yson.h"

#define EXPECT(c, ch)             \
    do                            \
    {                             \
        assert(*c->json == (ch)); \
        c->json++;                \
    } while (0)

typedef struct
{
    const char *json;
} yson_context;

static void yson_parse_whitespace(yson_context *c)
{
    const char *p = c->json;
    while (*p == ' ' || *p == '\t' || *p == '\n' || *p == '\r')
        p++;
    c->json = p;
}

static int yson_parse_null(yson_context *c, yson_value *v)
{
    EXPECT(c, 'n');

    if (c->json[0] != 'u' || c->json[1] != 'l' || c->json[2] != 'l')
        return YSON_PARSE_INVALID_VALUE;

    c->json += 3;
    v->type = YSON_NULL;
    return YSON_PARSE_OK;
}

static int yson_parse_value(yson_context *c, yson_value *v)
{
    switch (*c->json)
    {
    case 'n':
        return yson_parse_null(c, v);
    case '\0':
        return YSON_PARSE_EXPECT_VALUE;
    default:
        return YSON_PARSE_INVALID_VALUE;
    }
}

int yson_parse(yson_value *v, const char *json)
{
    yson_context c;
    int ret;
    assert(v != NULL);
    c.json = json;
    v->type = YSON_NULL;
    yson_parse_whitespace(&c);
    if ((ret = yson_parse_value(&c, v)) == YSON_PARSE_OK)
    {
        yson_parse_whitespace(&c);
        if (*c.json != '\0')
        {
            ret = YSON_PARSE_ROOT_NOT_SINGULAR;
        }
        }
    return ret;
}

yson_type yson_get_type(const yson_value *v)
{
    assert(v != NULL);
    return v->type;
}
