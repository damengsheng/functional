//! generics 泛型
//! 参数多态 (parametric polymorphism)

use std::fmt::{Debug, Display, Error};
use std::marker::PhantomData;

// 固定类型
pub struct Concrete {
    val: f64,
}

impl Concrete {
    pub fn new(val: f64) -> Concrete {
        Concrete { val: val }
    }

    pub fn val(&self) -> &f64 {
        &self.val
    }
}

// 泛型
pub struct Genericity<T> {
    val: T,
}

impl<T> Genericity<T> {
    pub fn new(val: T) -> Genericity<T> {
        Genericity { val: val }
    }

    pub fn val(&self) -> &T {
        &self.val
    }
}

pub struct Empty;

pub struct Null;

// Traits 泛型
pub trait DoubleDrop<T> {
    fn double_drop(self, _: T);
}

impl<T, U> DoubleDrop<T> for U {
    fn double_drop(self, _: T) {}
}

// 泛型约束 (bounds)
fn pointer<T: Display>(t: T) {
    println!("{}", t);
}

pub trait HasArea {
    fn area(&self) -> f64;
}

#[derive(Debug)]
pub struct Rectangle {
    length: f64,
    height: f64,
}

impl Rectangle {
    pub fn new(length: f64, height: f64) -> Rectangle {
        Rectangle { length, height }
    }
}

#[allow(dead_code)]
pub struct Triangle {
    length: f64,
    height: f64,
}

impl Triangle {
    pub fn new(length: f64, height: f64) -> Triangle {
        Triangle { length, height }
    }
}

impl HasArea for Rectangle {
    fn area(&self) -> f64 {
        self.height * self.length
    }
}

pub fn print_debug<T: Debug>(t: &T) {
    println!("{:?}", t);
}

pub fn area<T: HasArea>(t: &T) -> f64 {
    t.area()
}

// empty bounds

pub struct Cardinal;

pub struct BlueJay;

pub struct Turkey;

pub trait Red {}

pub trait Blue {}

impl Red for Cardinal {}

impl Blue for BlueJay {}

pub fn red<T: Red>(_: &T) -> &'static str {
    "red"
}

pub fn blue<T: Blue>(_: &T) -> &'static str {
    "blue"
}

// multiple bounds

pub fn compare_prints<T: Debug + Display>(t: &T) {
    println!("Debug `{:?}`", t);
    println!("Display `{}`", t);
}

pub fn compare_types<T: Debug, U: Debug>(t: &T, u: &U) {
    println!("t: `{:?}`", t);
    println!("u: `{:?}`", u);
}

pub trait PrintInOption {
    fn print_in_option(self);
}

impl<T> PrintInOption for T
where
    Option<T>: Debug,
{
    fn print_in_option(self) {
        println!("{:?}", Some(self));
    }
}

pub struct Container(i32, i32);

pub trait Contains<A, B> {
    fn contains(&self, _: &A, _: &B) -> bool;

    fn first(&self) -> i32;

    fn last(&self) -> i32;
}

impl Container {
    pub fn new(no1: i32, no2: i32) -> Container {
        Container(no1, no2)
    }
}

impl Contains<i32, i32> for Container {
    fn contains(&self, no1: &i32, no2: &i32) -> bool {
        (&self.0 == no1) && (&self.1 == no2)
    }

    fn first(&self) -> i32 {
        self.0
    }

    fn last(&self) -> i32 {
        self.1
    }
}

pub fn difference<A, B, C>(container: &C) -> i32
where
    C: Contains<A, B>,
{
    container.last() - container.first()
}

/// Phantom Type

#[derive(Debug, Clone, Copy)]
enum Inch {}
#[derive(Debug, Clone, Copy)]
enum Mm {}

#[derive(Debug, Clone, Copy)]
struct Length<Unit>(f64, PhantomData<Unit>);

impl<Unit> std::ops::Add for Length<Unit> {
    type Output = Length<Unit>;

    fn add(self, rhs: Length<Unit>) -> Length<Unit> {
        Length(self.0 + rhs.0, PhantomData)
    }
}

pub fn phantom_data_type0() {
    let one_foot: Length<Inch> = Length(12.0, PhantomData);
    let one_meter: Length<Mm> = Length(1000.0, PhantomData);

    let two_feet = one_foot + one_foot;
    let two_meters = one_meter + one_meter;

    println!("one foot + one foot = {:?} in", two_feet.0);
    println!("one meter + one meter = {:?} mm", two_meters.0);
}
