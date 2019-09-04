//! Function 函数
//! Method 方法
//! + [`domain::Point::origin`]
//! + [`domain::Point::new`]
//! + [`domain::Rectangle::area`]
//! + [`domain::Rectangle::perimeter`]
//! + [`domain::Rectangle::translate`]
//! + [`domain::Pair::destroy`]

/// 函数入参，返回值
fn is_divisible_by(lhs: u32, rhs: u32) -> bool {
    if rhs == 0 {
        return false;
    }

    lhs % rhs == 0
}

/// 函数返回 unit 类型  `()`
fn fizzbuzz(n: u32) -> () {
    if is_divisible_by(n, 15) {
        println!("fizzbuzz");
    } else if is_divisible_by(n, 3) {
        println!("fizz");
    } else if is_divisible_by(n, 5) {
        println!("buzz");
    } else {
        println!("{}", n);
    }
}

/// 函数返回值是 `()` , 函数签名定义可以省略返回值类型
pub fn fizzbuzz_to(n: u32) -> () {
    //pub fn fizzbuzz_to(n: u32) {
    for n in 1..=n {
        fizzbuzz(n);
    }
}

/// closures 匿名函数
pub fn anonymous_func(i: i32) -> i32 {
    i + 1
}

pub fn closures() {
    // 定义了入参，返回值类型的闭包表达式
    let closure_annotated = |i: i32| -> i32 { i + 1 };
    // 类型推断入参，返回值
    let closure_inferred = |i| i + 1;

    let one = || 1;

    let crt0 = closure_annotated(100);
    let crt1 = closure_inferred(100);

    println!("{} {} {}", crt0, crt1, one());
}

/// capture
pub fn capture() {
    let color = "green";
    let print = || println!("`color`: {}", color);

    print();
    print();

    let mut count = 0;
    let mut inc = || {
        count += 1;
        println!("`count`: {}", count);
    };

    inc();
    inc();

    let movable = Box::new(3);
    let consume = || {
        println!("`movable`: {:?}", movable);
        std::mem::drop(movable);
    };

    consume();

    let haystack = vec![1, 2, 3];
    let contains = move |needle| haystack.contains(needle);
    println!("{}", contains(&1));
    println!("{}", contains(&4));
}

fn apply<F>(f: F)
where
    F: FnOnce(),
{
    f();
}

fn apply_to_3<F>(f: F) -> i32
where
    F: Fn(i32) -> i32,
{
    f(3)
}

/// 参数传入函数
pub fn closures_input_parameters() {
    let greeting = "hello";
    let mut farewell = "goodbye".to_owned();

    let diary = || {
        println!("i said {} .", greeting);

        farewell.push_str("!!!");
        println!("then i screamed {} .", farewell);
        println!("now i can sleep zzzzz");
        std::mem::drop(farewell);
    };

    apply(diary);

    // 函数入参匿名类型
    let double = |x| 2 * x;
    println!(" 3 doubled: {}", apply_to_3(double));
}

fn create_fn() -> Box<dyn Fn()> {
    let text = "Fn".to_owned();

    Box::new(move || println!("this is a: {}", text))
}

fn create_fnmut() -> Box<dyn FnMut()> {
    let text = "FnMut".to_owned();

    Box::new(move || println!("this is a: {}", text))
}

/// 函数返回值
pub fn closures_output_parameters() {
    let fn_plain = create_fn();
    let mut fn_mut = create_fnmut();

    fn_plain();
    fn_mut();
}

fn call_me<F: Fn()>(f: F) {
    f();
}

fn function() {
    println!("i am a function !");
}

/// 函数作为参数
pub fn closures_input_function() {
    call_me(function);
}

pub fn closures_std_any() {
    let vec1 = vec![1, 2, 3];
    let vec2 = vec![4, 5, 6];

    let iter_any = vec1.iter().any(|&x| x == 2);
    let into_iter_any = vec2.into_iter().any(|x| x == 2);
    println!("2 in vec1: {}", iter_any);
    println!("2 in vec1: {}", into_iter_any);

    let array1 = [1, 2, 3];
    let array2 = [4, 5, 6];
    let array_iter_any = array1.iter().any(|&x| x == 2);
    let array_into_iter_any = array2.into_iter().any(|&x| x == 2);

    println!("2 in array1: {}", array_iter_any);
    println!("2 in array2: {}", array_into_iter_any);
}

pub fn closures_std_find() {
    let vec1 = vec![1, 2, 3];
    let vec2 = vec![4, 5, 6];

    let mut iter = vec1.iter();
    let mut into_iter = vec2.into_iter();
    println!("find 2 in vec1: {:?}", iter.find(|&&x| x == 2));
    println!("find 2 in vec1: {:?}", into_iter.find(|&x| x == 2));

    let array1 = [1, 2, 3];
    let array2 = [4, 5, 6];

    println!("Find 2 in array1: {:?}", array1.iter().find(|&&x| x == 2));
    // `into_iter()` for arrays unusually yields `&i32`
    println!(
        "Find 2 in array2: {:?}",
        array2.into_iter().find(|&&x| x == 2)
    );
}

fn is_odd(n: u32) -> bool {
    n % 2 == 1
}

/// 高阶函数
pub fn higher_order_functions() {
    let upper = 1000;
    let mut acc = 0;

    for n in 0.. {
        let n_squared = n * n;
        if n_squared >= upper {
            break;
        } else if is_odd(n_squared) {
            acc += n_squared;
        }
    }

    println!("imperative style: {}", acc);

    let sum_of_squared_odd_numbers: u32 = (0..)
        .map(|n| n * n)
        .take_while(|&n_squared| n_squared < upper)
        .filter(|&n_squared| is_odd(n_squared))
        .fold(0, |acc, n_squared| acc + n_squared);

    println!("functional style: {}", sum_of_squared_odd_numbers);
}

pub fn diverging_sum_odd_numbers(up_to: u32) -> u32 {
    let mut acc = 0;
    for i in 0..=up_to {
        let addition: u32 = match i % 2 == 1 {
            true => i,
            false => continue,
        };
        acc += addition;
    }
    acc
}
