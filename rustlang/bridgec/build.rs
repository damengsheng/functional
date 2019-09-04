extern crate cc;

use std::env;
use std::path::Path;
use std::process::Command;

fn main() {
    let out_dir: String = env::var("out_dir").unwrap();
    println!("out put dir {}", out_dir);
    Command::new("gcc")
        .args(&["src/helloc.c", "-c", "-fPIC", "-o"])
        .arg(&format!("{}/helloc.o", out_dir))
        .status()
        .unwrap();

    Command::new("ar")
        .args(&["crus", "libhelloc.a", "helloc.o"])
        .current_dir(&Path::new(&out_dir))
        .status()
        .unwrap();

    println!("cargo:rustc-link-search=native={}", out_dir);
    println!("cargo:rustc-link-lib=static=helloc");

    cc::Build::new().file("src/helloc.c").compile("helloc");
}
