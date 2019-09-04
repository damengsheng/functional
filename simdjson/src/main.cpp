//
//  main.cpp
//  simdjson example
//
//  Created by yakir on 2019/2/26.
//  Copyright © 2019 yakir. All rights reserved.
//

#include <iostream>
#include <chrono>
#include <stdio.h>
// #include <sys/time.h>
// #include <time.h>
#include "simdjson/jsonparser.h"

using std::chrono::duration;
using std::chrono::duration_cast;
using std::chrono::high_resolution_clock;
using std::chrono::milliseconds;
using std::chrono::system_clock;

int main(int argc, char *argv[])
{

    milliseconds start, end;
    start = duration_cast<milliseconds>(system_clock::now().time_since_epoch());
    const char *filename = argv[1];
    std::string_view p = get_corpus(filename);
    ParsedJson pj = build_parsed_json(p);
    if (!pj.isValid())
    {
        std::cout << "not valid" << std::endl;
    }
    else
    {
        std::cout << "valid" << std::endl;
    }
    end = duration_cast<milliseconds>(system_clock::now().time_since_epoch());
    milliseconds total = duration_cast<milliseconds>(end - start);

    std::cout << "start " << start.count()
              << " end " << end.count()
              << " total " << total.count()
              << std::endl;
    return EXIT_SUCCESS;
}
