use std::marker::PhantomData;

use example::{
    generics::{
        self, area, blue, print_debug, red, BlueJay, Cardinal, Concrete, Container, Contains,
        DoubleDrop, Empty, Genericity, Null, Rectangle, Triangle, Turkey,
    },
    generics_associated_types,
};

#[cfg(test)]
#[test]
fn test_generics_0() {
    let concrete = Concrete::new(10.0f64);
    println!("{:?}", concrete.val());

    let genericity: Genericity<f64> = Genericity::new(10.010f64);
    println!("{:?}", genericity.val());

    let genericity: Genericity<i32> = Genericity::new(-10i32);
    println!("{:?}", genericity.val());

    let empty = Empty;
    let null = Null;

    empty.double_drop(null);
}

#[test]
fn test_generics_area() {
    let rect = Rectangle::new(3.0, 6.0);
    let _triangle = Triangle::new(3.0, 6.0);
    print_debug(&rect);
    println!("{}", area(&rect));
}

#[test]
fn test_empty_bounds() {
    let cardinal = Cardinal;
    let blue_jay = BlueJay;
    let _turkey = Turkey;

    println!("A Cardinal is {}", red(&cardinal));
    println!("A blue jay is {}", blue(&blue_jay));
}

#[test]
fn test_multiple_bounds() {
    let str = "string";
    let arr = [1, 2, 3];
    let vec = vec![1, 2, 3];

    generics::compare_prints(&str);
    generics::compare_types(&arr, &vec);
}

#[test]
fn test_container() {
    let no1 = 1;
    let no2 = 10;

    let container = Container::new(no1, no2);
    println!(
        "Does Container Contain {} and {}: {}",
        &no1,
        &no2,
        container.contains(&no1, &no2)
    );
    println!(
        "First Number: {} Last Number: {}",
        container.first(),
        container.last()
    );

    println!("The Difference Is: {}", generics::difference(&container));
}

#[test]
fn test_phantom_data_type() {
    generics::phantom_data_type0()
}
