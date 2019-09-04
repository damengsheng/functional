open System

(*
    这是块注释
*)
// 这是行注释
// module 修饰模块名称
module HelloFsharp =

    let i, j, k, l = 1.0F, 2.1, 3.0, 4I

    let negate x =
        x * -1
    let square x =
        x * x
    let print(num: int) =
        sprintf "运算结果: %d\n" num
    let print(str: String) =
        sprintf "运算结果: %s\n" str

    let provinces: String[] =
        [ "浙江", "江苏", "安徽" ]
        
        
        let provinces(strs: String[])=
            strs
            |> List.map print
            |> List.iter (fun province->printf "%s " province)

 //let sumOfLengths (xs : string []) =
//    xs
//    |> Array.map (fun s -> s.Length)
//    |> Array.sum

    let squareNegateThenPrint' =
        square
        >> negate
        >> print square

    let sayHi = fun bonjour name ->
        printfn "%A %A" bonjour name

    let sout() =
        sayHi print "yakir"

    [<EntryPoint>]
    let main argv =
        sout()
        0
