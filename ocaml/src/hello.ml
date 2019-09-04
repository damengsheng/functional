(* hello world *)

let name = "yakir"

(* 
 * 多行
 * 注释
 *)

(* 嵌套注释
  let num = 
    (* 如果 x = 1 *) 1
    (* 如果 x = 2 *) 2
*)

(* 函数定义 *)
let rec sayHi name = 
  "hello " ^ name

print_endline (sayHi name)