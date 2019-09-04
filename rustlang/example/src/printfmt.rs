//! Formatted Print 格式化输出
//!
//! `std::fmt`宏(macros)定义
//!
//! + `format!`
//! + `print!`
//! + `println!`
//! + `eprint!`
//! + `eprintln!`
//!
//! `std::fmt`定义的两个显示文本的特质(`traits`)
//!
//! + `fmt::Debug`   **`{:?}`**
//! + `fmt::Display` **`{}`**

use std::fmt;

use crate::domain::Color;

/// 位置参数
pub fn positional_args() {
    println!("{0} {0} {0} {0}， {0} {0} {1}", "1", "2");
}

/// 参数命名
pub fn named_args() {
    println!(
        "{subject} {verb} {object}",
        subject = "c",
        verb = "y",
        object = "k"
    );
}

/// 特殊格式
pub fn special_fmt() {
    println!("{} {:b}", 1, 2);
}

/// 右对齐
pub fn right_align_fmt() {
    println!("{nu:>width$}", nu = 2, width = 6);
}

/// 右对齐占位填充空白
pub fn pad_extra_fmt() {
    println!("{nu:>0width$}", nu = "yakir", width = 10)
}

/// Display i8 Array Use Debug Fmt
pub fn array_display() {
    let num_arr: [i8; 4] = [0, 2, 3, 3];
    println!("{:?}", num_arr)
}

/// 大括号转义
pub fn brace_display() {
    println!("{{ {{{{}}}}包裹输出大括号 {}}}", "yakir")
}

/// `struct` 格式化成字符串
pub fn struct_debug_display() {
    #[derive(Debug)]
    struct Person<'a> {
        name: &'a str,
        age: u8,
    }

    let yakir = Person {
        name: "yakir",
        age: 18u8,
    };

    println!("{:#?}", yakir)
}

/// `struct` 实现 `Display` trait， 实现字符串格式化
pub fn struct_impl_display() {
    #[derive(Debug)]
    struct Person<'a> {
        name: &'a str,
        age: u8,
    }
    let yakir = Person {
        name: "yakir",
        age: 18u8,
    };
    println!("{:?}", yakir);
}

/// `List` 格式化成字符串
pub fn list_display() {
    struct List(Vec<u8>);

    impl fmt::Display for List {
        fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
            let vec = &self.0;
            write!(f, "[")?;

            for (count, v) in vec.iter().enumerate() {
                if count != 0 {
                    write!(f, " ")?;
                }
                write!(f, "{}", v)?;
            }
            write!(f, "]")
        }
    }

    let letter = vec![1u8, 2u8, 3u8];
    let list = List(letter);
    println!("{}", list)
}

pub fn struct_list_display() {
    for color in [
        Color::new(25u16, 10u16, 255u16),
        Color::new(1u16, 60u16, 111u16),
        Color::new(245u16, 190u16, 0u16),
    ]
    .iter()
    {
        println!("{}", *color);
    }
}
