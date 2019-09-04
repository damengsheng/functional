//! variable bindings
//!
//! 变量绑定

/// 变量声明
pub fn variable_binding() {
    let an_integer = 1u8;
    let copied_integer = an_integer;

    println!("Integer 变量 {}", copied_integer);
    // 变量名前缀 `_` 表示变量未被使用
    let _unused_var = String::from("未使用的变量");
}

/// 变量可变性
pub fn mutability() {
    let _immutable_binding = -1i8;

    let mut mutable_binding = -1i8;

    println!("可变绑定 {} Before Mutation ", mutable_binding);

    mutable_binding += 1;

    println!("可变绑定 {} After Mutation ", mutable_binding);
}

pub fn socp_shadowing() {
    {
        // 同一个作用域
        let short_lived_binding = 0i8;
        println!("inner short live binding: {}", short_lived_binding);
        let short_lived_binding = 0_i8;
        println!("inner short live binding: {}", short_lived_binding);
    }

    // 外层声明 & 内层初始化
    let lived_binding;
    {
        lived_binding = 777_u16;
        println!("lived_binding : {}", lived_binding);
    }
    println!("lived_binding : {}", lived_binding);
    // 外层声明 & 初始化
    let mut lived_binding = 666_u16;
    {
        println!("lived_binding : {}", lived_binding);
        lived_binding = 777_u16;
        println!("lived_binding : {}", lived_binding);
    }
    println!("lived_binding : {}", lived_binding);
}
