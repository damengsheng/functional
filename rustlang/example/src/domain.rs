//! domain

use std::fmt::{Display, Formatter, Result};

#[allow(dead_code)]
/// Point
pub struct Point {
    x: f64,
    y: f64,
}

impl Point {
    pub fn origin() -> Point {
        Point { x: 0.0, y: 0.0 }
    }

    pub fn new(x: f64, y: f64) -> Point {
        Point { x, y }
    }

    pub fn print(&self) {
        println!("point x: {} y: {}", self.x, self.y);
    }
}

/// Rectangle
pub struct Rectangle {
    p0: Point,
    p1: Point,
}

impl Rectangle {
    pub fn new(x0: f64, y0: f64, x1: f64, y1: f64) -> Rectangle {
        Rectangle {
            p0: Point { x: x0, y: y0 },
            p1: Point { x: x1, y: y1 },
        }
    }

    /// method Rectangle::area
    pub fn area(&self) -> f64 {
        let Point { x: x0, y: y0 } = self.p0;
        let Point { x: x1, y: y1 } = self.p0;

        ((x0 - x1) * (y0 - y1)).abs()
    }

    /// method Rectangle::perimeter
    pub fn perimeter(&self) -> f64 {
        let Point { x: x0, y: y0 } = self.p0;
        let Point { x: x1, y: y1 } = self.p1;

        ((x0 - x1).abs() + (y0 - y1).abs()) * 2.0
    }

    /// method Rectangle::translate
    pub fn translate(&mut self, x: f64, y: f64) {
        self.p0.x += x;
        self.p1.x += x;

        self.p0.y += y;
        self.p1.y += y;
    }
}

/// Pair
pub struct Pair(Box<i32>, Box<i32>);

impl Pair {
    pub fn destroy(self) {
        let Pair(first, second) = self;

        println!("first {} second {}", first, second);
    }
}

/// Color
pub struct Color {
    red: u16,
    green: u16,
    blue: u16,
}

impl Color {
    pub fn new(red: u16, green: u16, blue: u16) -> Color {
        Color { red, green, blue }
    }
}

impl Display for Color {
    fn fmt(&self, f: &mut Formatter) -> Result {
        write!(
            f,
            "[ r: {:03o} g: {:02o} b: {:02o} 0x{:02X}{:02X}{:02X} ]",
            &self.red, &self.green, &self.blue, &self.red, &self.green, &self.blue
        )
    }
}

/// Person
#[derive(Debug)]
pub struct Person<'a> {
    name: &'a str,
    age: u8,
}

impl std::fmt::Display for Person<'_> {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        write!(
            f,
            "{{ \"name\": \"{}\", \"age\": {} }}",
            self.name, self.age
        )
    }
}
