//!
//! 转换，两个 traits
//!
//! [Trait std::convert::From](https://doc.rust-lang.org/std/convert/trait.From.html)
//! [Trait std::convert::Into](https://doc.rust-lang.org/std/convert/trait.Into.html)
//!
//! std::convert::From
//!
//! std::convert::Into

#[derive(Debug)]
struct Number {
    value: isize,
}

impl std::convert::From<isize> for Number {
    fn from(item: isize) -> Self {
        Number { value: item }
    }
}

impl std::convert::Into<isize> for Number {
    fn into(self) -> isize {
        self.value
    }
}

pub fn string_convert() {
    let num = Number::from(77777_isize);
    println!("Number From: {:?}", num);

    let num_value: isize = num.into();
    println!("Number Into: {:?}", num_value);
}

pub fn parsing_string() {
    let parsed: i32 = "7".parse().unwrap();

    let turbo_parsed = "10".parse::<i32>().unwrap();

    let sum = parsed + turbo_parsed;

    println!("Sum: {:?}", sum);
}
