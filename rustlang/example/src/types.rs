//! + 类型转换  casting
//!
//! + 字面量    literal
//!
//! + 类型推断  inference
//!
//! + 类型别名  alias
//!

#[allow(unused_variables)]
pub fn casting() {
    let decimal = 1.11_f32;
    // let integer: u8 = decimal; // 类型转换❌️

    let double = decimal as f64;
    let _1 = -1_i8 as u8;
    println!("-1 i8 as u8 {}", _1); // -1 i8 as u8 255

    let thousand_mod = 1000 % 256;
    println!("1000 mod 256 is : {}", thousand_mod);
}

pub fn literal() {
    // Suffixed literals, their types are known at initialization
    let x = 1u8;
    let y = 2u32;
    let z = 3f32;

    // Unsuffixed literal, their types depend on how they are used
    let i = 1;
    let f = 1.0;

    // `size_of_val` returns the size of a variable in bytes
    println!("size of `x` in bytes: {}", std::mem::size_of_val(&x));
    println!("size of `y` in bytes: {}", std::mem::size_of_val(&y));
    println!("size of `z` in bytes: {}", std::mem::size_of_val(&z));
    println!("size of `i` in bytes: {}", std::mem::size_of_val(&i));
    println!("size of `f` in bytes: {}", std::mem::size_of_val(&f));
}

pub fn inference() {
    let elem = 5_u8;
    let mut vec = Vec::new();
    vec.push(elem);
    println!("{:?}", vec);
}

/// 类型别名
pub fn alias() {
    type Y64 = u64;
    let double: Y64 = 9999;
    println!("类型别名 Y64=u64 {}", double);
}
