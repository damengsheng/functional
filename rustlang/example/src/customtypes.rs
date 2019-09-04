//! 自定义数据类型
//!
//! struct
//!
//! enum

pub fn struct_person() {
    #[derive(Debug)]
    struct Person<'a> {
        name: &'a str,
        age: u8,
    }

    let pp = Person {
        name: "🐽", age: 1
    };
    println!("Person: {:?}", pp);

    let pp = Person {
        name: pp.name,
        age: pp.age + 1,
    };
    println!("Person: {:?}", pp);
}

pub enum WebEvent {
    PageLoad,
    PageUnload,
    KeyPress(char),
    Paste(String),
    Click { x: i64, y: i64 },
}

pub fn inspect(event: WebEvent) {
    match event {
        WebEvent::PageLoad => println!("Page Loaded "),
        WebEvent::PageUnload => println!("Page Unloaded"),
        WebEvent::KeyPress(c) => println!("pressed '{}' .", c),
        WebEvent::Paste(s) => println!("pressed \"{}\" .", s),
        WebEvent::Click { x, y } => {
            println!("Clicked at [{}, {}]", x, y);
        }
    }
}

enum RGB {
    Red = 0xff0000,
    Green = 0x00ff00,
    Blue = 0x0000ff,
}

pub fn rgb() {
    println!("Red #{:06x}", RGB::Red as i32);
    println!("Green #{:06x}", RGB::Green as i32);
    println!("Blue #{:06x}", RGB::Blue as i32);
}

enum List {
    Cons(u32, Box<List>),
    Nil,
}

impl List {
    fn new() -> List {
        List::Nil
    }

    fn prepend(self, elem: u32) -> List {
        List::Cons(elem, Box::new(self))
    }

    fn len(&self) -> u32 {
        match *self {
            List::Cons(_, ref tail) => 1 + tail.len(),
            List::Nil => 0,
        }
    }

    fn stringfiy(&self) -> String {
        match *self {
            List::Cons(head, ref tail) => format!("{}, {}", head, tail.stringfiy()),
            List::Nil => format!("Nil"),
        }
    }
}

pub fn linked_list() {
    let mut list = List::new();

    list = list.prepend(9);
    list = list.prepend(8);
    list = list.prepend(7);
    list = list.prepend(6);
    list = list.prepend(5);

    println!("linked list has length {}", list.len());
    println!("{}", list.stringfiy());
}
