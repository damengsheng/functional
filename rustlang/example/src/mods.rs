#[allow(unused)]
/// use 取别名
//use crate::mods::mod_0::public_fn as pfn;
use self::mod_0::public_fn as pfn;

mod mod_0 {
    fn private_fn() {
        println!("模块私有");
    }

    pub fn public_fn() {
        println!("public function");
        private_fn();
    }

    pub mod nested_mod {
        pub fn function() {
            println!("nested_mod::function");
        }
    }
}

mod struct_visibility {
    pub struct OpenBox<T> {
        pub contents: T,
    }

    pub struct CloseBox<T> {
        contents: T,
    }

    impl<T> self::CloseBox<T> {
        pub fn new(contents: T) -> self::CloseBox<T> {
            self::CloseBox { contents: contents }
        }
    }
}

pub fn mods() {
    pfn();

    //    use crate::mods::struct_visibility::{CloseBox, OpenBox};
    use super::mods::struct_visibility::{CloseBox, OpenBox};
    let open_box = OpenBox {
        contents: "public information",
    };

    println!("the open box contains: {}", open_box.contents);

    let _close_box = CloseBox::new("public information");

    // Err
    // println!("the close box contains: {}", _close_box.contents);
}
