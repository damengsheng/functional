# Rust Language


## rustup &  cargo Environment Variable

+ RUSTUP_HOME: used for storing installed toolchains and configuration options.
+ RUSTUP_TOOLCHAIN
+ RUSTUP_DIST_SERVER: https://static.rust-lang.org
+ RUSTUP_DIST_ROOT: https://static.rust-lang.org/dist
+ RUSTUP_UPDATE_ROOT: https://static.rust-lang.org/rustup

+ CARGO_HOME: local cache of the registry index and of git checkouts of crates.
+ CARGO_TARGET_DIR
+ RUSTC
+ RUSTC_WRAPPER
+ RUSTDOC
+ RUSTDOCFLAGS
+ RUSTFLAGS
+ CARGO_INCREMENTAL
+ CARGO_CACHE_RUSTC_INFO


## Cargo Package Layout

```bash
.
├── Cargo.lock
├── Cargo.toml
├── benches
│   └── large-input.rs
├── examples
│   └── simple.rs
├── src
│   ├── bin
│   │   └── another_executable.rs
│   ├── lib.rs
│   └── main.rs
└── tests
    ├── my_test.rs
    ├── some-integration-tests.rs
    └── my_other_test.rs
```

### Cargo Package

```bash
cargo build -p xxx 
cargo build --all
```

```bash
export out_dir=../target/debug/deps
cd hello-from-c && cargo test --test test-hello-c -- --test-threads=2
```

### Feature
```rust
#![feature(rust_2018_preview)]
#![feature(raw_identifiers)]
```

### Cargo test

```bash
cargo test -- --nocapture # 测试的时候显示print的打印
cargo test 测试模块名 -- --nocapture
cargo test 测试模块名::测试函数名 -- --nocapture
```

### Crate

```shell
rustc --crate-type=lib rary.rs # library.rlib
rustc executable.rs --extern rary=library.rlib && ./executable
```

### lint

+ `dead_code`   unused functions  `#[allow(dead_code)]`
+ `crate_type`  `#![crate_type = "lib"]`
+ `crate_name`  `#![crate_name = "rary"]`
+ `cfg`         

###### READ

+ [rust-book](https://doc.rust-lang.org/nightly/book/index.html)
+ [unstable-book](https://doc.rust-lang.org/nightly/unstable-book/index.html)
+ [edition-guide](https://doc.rust-lang.org/nightly/edition-guide/index.html)
+ [rust-by-example](https://doc.rust-lang.org/nightly/rust-by-example/generics/gen_trait.html)
+ [rust-reference](https://doc.rust-lang.org/reference/index.html)
+ [std](https://doc.rust-lang.org/std/fmt/#named-parameters)

###### Reference
+ [learn](https://www.rust-lang.org/learn)
+ [rust-by-example: stable](https://doc.rust-lang.org/stable/rust-by-example/)
+ [rust-by-example: nightly](https://doc.rust-lang.org/nightly/rust-by-example/)
+ [reference: stable](https://doc.rust-lang.org/stable/reference/)
+ [reference: nightly](https://doc.rust-lang.org/nightly/reference/)
+ [edition-guide](https://doc.rust-lang.org/edition-guide/index.html)
+ [unstable-book](https://doc.rust-lang.org/unstable-book/index.html)
+ [nomicon](https://doc.rust-lang.org/nomicon/README.html)
+ [wasm-pack](https://rustwasm.github.io/wasm-pack/)

+ [Does println! borrow or own the variable?](https://stackoverflow.com/questions/30450399/does-println-borrow-or-own-the-variable)  
+ [How to print structs and arrays?](https://stackoverflow.com/questions/30253422/how-to-print-structs-and-arrays)  
