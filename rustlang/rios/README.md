# iOS Swift 桥接 Rust

[Rust on iOS](https://medium.com/visly/rust-on-ios-39f799b3c1dd)


## Prepare

```shell script
cargo install cargo-lipo -f
cargo install cbindgen -f

rustup target add aarch64-apple-ios
rustup target add x86_64-apple-ios
```


## Build

```shell script
cd rios

cargo lipo --release
cbindgen src/lib.rs -l c > rios-em/rios-em/include/rios.h

mkdir -p rios-em/rios-em/{include,libs}
cp rios.h rios-em/rios-em/include
cp ../target/universal/release/librios.a rios-em/rios-em/libs
```

![1](../../images/rios/1.png)  

![2](../../images/rios/2.png)  

![3](../../images/rios/3.png)  

![4](../../images/rios/4.png)  

![5](../../images/rios/5.png)  
