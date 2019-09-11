use std::collections::HashMap;
use std::env;
use std::io::{self, Write};

use hyper::rt::{self, Future, Stream};
use hyper::Client;
use hyper::Uri;

async fn main() -> std::result::Result<T, Box<dyn std::error::Error + Send + Sync>> {
    let resp: HashMap<String, String> = reqwest::get("https://www.baidu.com")?.json()?;

    println!("{:#?}", resp)
}
