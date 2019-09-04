//! generics 泛型

use std::fmt::{Display, Error, Formatter};
use std::intrinsics::write_bytes;

trait HasArea<T> {
    fn area(self, _: T) -> f64;
}

impl<T, U> HasArea<T> for U {
    fn area(self, _: T) -> f64 {
        1.1f64
    }
}

pub struct Point<T> {
    x: T,
    y: T,
}
