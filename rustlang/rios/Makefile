default: all

all: build

prepare:
	cargo install cargo-lipo -f
	cargo install cbindgen -f
	rustup target add aarch64-apple-ios
	rustup target add x86_64-apple-ios
	rustup target add armv7-apple-ios
	rustup target add armv7s-apple-ios
	rustup target add i386-apple-ios

build:
	cargo lipo --release
	cbindgen src/lib.rs -l c > rios.h

init:
	mkdir -p rios-em/rios-em/{include,libs}
	cp rios.h rios-em/rios-em/include
	cp ../target/universal/release/librios.a rios-em/rios-em/libs

clean:
	cargo clean
	cd rios-em && xcodebuild -project rios-em.xcodeproj -scheme rios-em -configuration Release clean ; cd -