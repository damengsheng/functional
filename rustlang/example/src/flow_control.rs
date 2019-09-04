//! 流程控制

/// if else
pub fn fc_if_else(n: isize) {
    if n < 0 {
        println!("{} is negative ", n);
    } else if n > 0 {
        println!("{} is positive ", n);
    } else {
        println!("{} is zero", n);
    }

    let big_n = if n < 10 && n > -10 { 10 * n } else { n / 2 };

    println!("{} - {}", n, big_n);
}

/// loop
pub fn fc_loop() {
    let mut count = 0_u32;
    loop {
        count += 1;

        if count == 3 {
            continue;
        }

        println!("count {}", count);

        if count == 5 {
            println!("Exit This Loop .");
            break;
        }
    }
}

/// 内嵌loop和label
#[allow(unreachable_code)]
pub fn fc_nesting_and_label() {
    'outer: loop {
        println!("Entered The Outer Loop");

        'inner: loop {
            println!("Entered The Inner Loop");

            break 'outer;
        }

        // #[warn(unreachable_code)]
        println!("This point will never be reached .");
    }

    println!("Exited the outer loop .");
}

/// Returning from Loop
pub fn fc_return_from_loop() -> i32 {
    let mut counter = 0;

    let result = loop {
        counter += 1;

        if counter == 10 {
            break counter * 2;
        }
    };

    println!("Return From Loop {} ", result);

    result
}

/// while
pub fn fc_while() {
    let mut n = 1;
    while n < 101 {
        if n % 15 == 0 {
            println!("fizz buzz");
        } else if n % 3 == 0 {
            println!("fizz");
        } else if n % 5 == 0 {
            println!("buzz");
        } else {
            println!("{}", n);
        }

        n += 1
    }
}

/// for in
pub fn fc_for_in() {
    for n in 1..101 {
        if n % 15 == 0 {
            println!("fizz buzz");
        } else if n % 3 == 0 {
            println!("fizz");
        } else if n % 5 == 0 {
            println!("buzz");
        } else {
            println!("{}", n);
        }
    }

    for n in 1..=100 {
        if n % 15 == 0 {
            println!("fizz buzz");
        } else if n % 3 == 0 {
            println!("fizz");
        } else if n % 5 == 0 {
            println!("buzz");
        } else {
            println!("{}", n);
        }
    }
}

/// for and iterators
pub fn fc_for_iterators() {
    let vec = vec![1, 2, 3];

    for v in vec.iter() {
        match v {
            &1 => println!("1"),
            _ => println!("{}", v),
        }
    }

    let vec = vec![1, 2, 3];
    for v in vec.into_iter() {
        match v {
            1 => println!("1"),
            _ => println!("{}", v),
        }
    }

    let mut vec = vec![1, 2, 3];
    for v in vec.iter_mut() {
        *v = match v {
            &mut 1 => 100,
            _ => 0,
        }
    }

    println!("vec: {:?}", vec);
}

/// match
pub fn fc_match() {
    let number = 13;

    println!("Tell me about {}", number);

    match number {
        1 => println!("One !"),
        2 | 3 | 5 | 7 | 11 => println!("This is a prime "),
        13..=19 => println!("a teen"),
        _ => println!("Ain't special"),
    }

    let boolean = true;
    let binary = match boolean {
        false => 0,
        true => 1,
    };

    println!("{} - {}", boolean, binary);
}

/// match destructure
pub fn fc_match_destructure() {
    let pair = (0, -1);
    // match tuples
    match pair {
        (0, y) => println!("x 0, y {}", y),
        (x, 0) => println!("x {}, y 0", x),
        _ => println!("it doesn't matter what they are"),
    }

    #[allow(dead_code)]
    enum Color {
        Red,
        Blue,
        Green,
        RGB(u8, u8, u8),
        HSV(u32, u32, u32),
        HSL(u32, u32, u32),
        CMY(u32, u32, u32),
        CMYK(u32, u32, u32, u32),
    }

    let color = Color::RGB(255, 255, 255);
    // match enum
    match color {
        Color::Red => println!("The color is Red!"),
        Color::Blue => println!("The color is Blue!"),
        Color::Green => println!("The color is Green!"),
        Color::RGB(r, g, b) => println!("Red: {}, green: {}, and blue: {}!", r, g, b),
        Color::HSV(h, s, v) => println!("Hue: {}, saturation: {}, value: {}!", h, s, v),
        Color::HSL(h, s, l) => println!("Hue: {}, saturation: {}, lightness: {}!", h, s, l),
        Color::CMY(c, m, y) => println!("Cyan: {}, magenta: {}, yellow: {}!", c, m, y),
        Color::CMYK(c, m, y, k) => println!(
            "Cyan: {}, magenta: {}, yellow: {}, key (black): {}!",
            c, m, y, k
        ),
    }

    let reference = &1;
    // match pointer & ref
    match reference {
        &val => println!("Got a value via destructing {:?}", val),
    }

    match *reference {
        val => println!("Got a value via dereferencing {:?}", val),
    }

    let _not_a_reference = 3;
    let mut _is_a_reference = 3;
    let val = 5;
    let mut mut_val = 5;
    match val {
        ref r => println!("Got a reference to a value: {:?}", r),
    }
    match mut_val {
        ref mut m => {
            *m += 10;
            println!("added 10 `mut_val`：{:?}", m);
        }
    }

    struct Foo {
        x: (u32, u32),
        y: u32,
    };

    let foo = Foo { x: (1, 2), y: 3 };
    // match struct
    match foo {
        Foo { x: (1, b), y } => println!("First of x is 1, b = {},  y = {} ", b, y),
        Foo { y: 2, x: i } => println!("y is 2, i = {:?}", i),
        Foo { y, .. } => println!("y = {}, we don't care about x", y),
        // this will give an error: pattern does not mention field `x`
        //        Foo { y } => println!("y = {}", y),
    }
}

/// match guard(防护装置)
pub fn match_guard() {
    let pair = (2, -2);

    println!("Tell me about {:?}", pair);
    match pair {
        (x, y) if x == y => println!("These are twins"),
        // The ^ `if condition` part is a guard
        (x, y) if x + y == 0 => println!("Antimatter, kaboom!"),
        (x, _) if x % 2 == 1 => println!("The first one is odd"),
        _ => println!("No correlation..."),
    }
}

fn age() -> u32 {
    15
}

/// match binding
pub fn match_binding() {
    println!("Tell me what type of person you are");

    match age() {
        0 => println!("I'm not born yet I guess"),
        // Could `match` 1 ... 12 directly but then what age
        // would the child be? Instead, bind to `n` for the
        // sequence of 1 .. 12. Now the age can be reported.
        n @ 1..=12 => println!("I'm a child of age {:?}", n),
        n @ 13..=19 => println!("I'm a teen of age {:?}", n),
        // Nothing bound. Return the result.
        n => println!("I'm an old person of age {:?}", n),
    }
}

///
pub fn if_let() {
    let optional = Some(7);
    match optional {
        Some(i) => {
            println!("num: {:?}", i);
        }
        _ => {}
    };

    let number = Some(7);
    let letter: Option<i32> = Option::None;
    let emoticon: Option<i32> = Option::None;

    if let Some(i) = number {
        println!("Match {:?}", i);
    }

    if let Some(i) = letter {
        println!("Matched {:?}!", i);
    } else {
        println!("Didn't match a number. Let's go with a letter!");
    }

    let i_like_letters = false;

    if let Some(i) = emoticon {
        println!("Matched {:?}!", i);
    // Destructure failed. Evaluate an `else if` condition to see if the
    // alternate failure branch should be taken:
    } else if i_like_letters {
        println!("Didn't match a number. Let's go with a letter!");
    } else {
        // The condition evaluated false. This branch is the default:
        println!("I don't like letters. Let's go with an emoticon :)!");
    };

    enum Foo {
        Bar,
        Baz,
        Qux(u32),
    }

    // Create example variables
    let a = Foo::Bar;
    let b = Foo::Baz;
    let c = Foo::Qux(100);

    // Variable a matches Foo::Bar
    if let Foo::Bar = a {
        println!("a is foobar");
    }

    // Variable b does not match Foo::Bar
    // So this will print nothing
    if let Foo::Bar = b {
        println!("b is foobar");
    }

    // Variable c matches Foo::Qux which has a value
    // Similar to Some() in the previous example
    if let Foo::Qux(value) = c {
        println!("c is {}", value);
    }
}

/// match while let
pub fn while_let() {
    // Make `optional` of type `Option<i32>`
    let mut optional = Some(0);

    // Repeatedly try this test.
    loop {
        match optional {
            // If `optional` destructures, evaluate the block.
            Some(i) => {
                if i > 9 {
                    println!("Greater than 9, quit!");
                    optional = None;
                } else {
                    println!("`i` is `{:?}`. Try again.", i);
                    optional = Some(i + 1);
                }
                // ^ Requires 3 indentations!
            }
            // Quit the loop when the destructure fails:
            _ => {
                break;
            } // ^ Why should this be required? There must be a better way!
        }
    }

    while let Some(i) = optional {
        if i > 9 {
            println!("Greater than 9, quit!");
            optional = None;
        } else {
            println!("`i` is `{:?}`. Try again.", i);
            optional = Some(i + 1);
        }
        // ^ Less rightward drift and doesn't require
        // explicitly handling the failing case.
    }
}
