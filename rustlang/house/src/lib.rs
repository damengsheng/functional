#[tarpc::service]
pub trait World {
    async fn hello(name: String) -> String;
}

/// 索引文档
pub trait Index {
    fn index();
}

pub mod se;

#[macro_use]
extern crate tantivy;
extern crate tempdir;
