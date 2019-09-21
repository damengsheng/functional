extern crate reqwest;

use std::collections::HashMap;
use std::error::Error;

fn main() -> Result<(), Box<dyn Error>> {
    let client = reqwest::Client::new();
    let resp = client
        .get("https://dl.google.com/chrome/mac/stable/GGRO/googlechrome.dmg")
        .send()?;
    let headers = resp.headers();
    println!("{:#?}", headers);
    Ok(())
}
