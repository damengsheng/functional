%%%hello.erl

-module(greet).
-export([hello/1,hi/0]).

%%@doc say hello ~
hello(Name) -> 
    io:format("Hello ~w ! ~n", [Name]).

hi() ->
    "hi !".