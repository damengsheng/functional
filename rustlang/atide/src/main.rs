#![feature(async_await)]
#![feature(async_closure)]

use std::error::Error;

fn main() {
    let mut app = tide::App::new();
    app.at("/").get(async move |_| "Hello, world!");
    app.run("127.0.0.1:8000");
}
