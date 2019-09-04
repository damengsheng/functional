cmake_minimum_required (VERSION 3.14)
project (yson_test C)

if (CMAKE_C_COMPILER_ID MATCHES "GNU|Clang")
    set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -ansi -pedantic -Wall")
endif()

add_library(yson yson.c)
add_executable(yson_test yson_test.c)
target_link_libraries(yson_test yson)
