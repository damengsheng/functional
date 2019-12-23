use std::io::Error;

use async_std::prelude::*;
use async_std::task;

#[async_std::main]
async fn main() -> Result<(), Error> {
    let mut app = tide::new();
    app.at("/").get(|_| async move { "Hello, world!" });
    app.listen("127.0.0.1:8080").await?;
    Ok(())
}
