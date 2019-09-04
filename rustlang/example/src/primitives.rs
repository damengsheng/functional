//! Primitives
//!
//! + 基础类型
//!
//! + 复合类型

/// 基础类型
pub fn scalar_types() {
    let a_i8: i8 = -1;
    let a_i16: i16 = -2;
    let a_i32: i32 = -3;
    let a_i64: i64 = -4;
    let a_i128: i128 = -5;
    let a_isize: isize = -6;
    println!(
        "基础类型 有符号 整型 {} {} {} {} {} {} ",
        a_i8, a_i16, a_i32, a_i64, a_i128, a_isize
    );
    let a_u8: u8 = 1;
    let a_u16: u16 = 2;
    let a_u32: u32 = 3;
    let a_u64: u64 = 4;
    let a_u128: u128 = 5;
    let a_usize: usize = 6;
    println!(
        "基础类型 无符号整型 {} {} {} {} {} {} ",
        a_u8, a_u16, a_u32, a_u64, a_u128, a_usize
    );
    let a_float = 1.1f64;
    println!("单进度浮点类型 float {:0.06}", a_float);
    let a_double = 1.1;
    println!("双进度浮点类型 double {}", a_double);
    let a_char = '🐽';
    println!("字符串类型 {}", a_char);
    let a_boolean = true;
    println!("布尔类型 {}", a_boolean);
    let a_unit = ();
    println!("unit {:?} 默认值为一个空的元组(Tuple)", a_unit);

    //    let mut str_spaces = (str)1;
    //    let str_spaces = str_spaces.len();
}

/// 复合类型
pub fn compound_types() {

    // todo!
}

/// 运算操作
pub fn literals_operators() {
    // Integer addition
    println!("1 + 2 = {}", 1u32 + 2);

    // Integer subtraction
    println!("1 - 2 = {}", 1i32 - 2);
    // TODO ^ Try changing `1i32` to `1u32` to see why the type is important

    // Short-circuiting boolean logic
    println!("true AND false is {}", true && false);
    println!("true OR false is {}", true || false);
    println!("NOT true is {}", !true);

    // Bitwise operations
    println!("0011 AND 0101 is {:04b}", 0b0011u32 & 0b0101);
    println!("0011 OR 0101 is {:04b}", 0b0011u32 | 0b0101);
    println!("0011 XOR 0101 is {:04b}", 0b0011u32 ^ 0b0101);
    println!("1 << 5 is {}", 1u32 << 5);
    println!("0x80 >> 2 is 0x{:x}", 0x80u32 >> 2);

    // Use underscores to improve readability!
    println!("One million is written as {}", 1_000_000u32);
}

/// 元组翻转
pub fn tuple_reverse(pair: (i32, bool)) -> (bool, i32) {
    let (integer, boolean) = pair;
    (boolean, integer)
}

/// 多元组
pub fn multi_tuple() {
    struct Matrix(i8, i16, u8, u16, f32);

    let matrix = Matrix(1, -1, 1, 1, 1.1);

    println!("matrix元组第0个元素 {:?}", matrix.0);
}

/// 数组
///
/// 切片
pub fn array_slice() {
    let array: [i8; 6] = [0, 1, 1, 1, 1, 10];
    println!(
        "数组长度: {} 数组分配栈长度: {} 数组元素: {:?}",
        array.len(),
        std::mem::size_of_val(&array),
        array
    );

    let slice: [i32; 500] = [0; 500];
    println!(
        "切片长度: {} 切片分配栈长度: {} 切片第0个元素: {:?}",
        slice.len(),
        std::mem::size_of_val(&slice),
        slice[0]
    );
}

/// 不可变
static LANGUAGE: &str = "Rust";

///
const THRESHOLD: i32 = 10;

/// 输入值是否大于常量 `THRESHOLD` 的值
fn is_big(n: i32) -> bool {
    n > THRESHOLD
}

pub fn constants() {
    println!("{} Program Language .", LANGUAGE);
    let n = 11;
    println!("{} is {}", n, if is_big(n) { "big" } else { "small" });
}
