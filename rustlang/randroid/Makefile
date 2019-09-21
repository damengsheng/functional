default: all

export JNI_LIBS=randroidem/app/src/main/jniLibs

all: build

prepare:
	rustup target add aarch64-linux-android
	rustup target add armv7-linux-androideabi
	rustup target add i686-linux-android

ndk:
	/Volumes/To/repos/android/sdk/ndk-bundle/build/tools/make_standalone_toolchain.py --force --api 29 --arch arm64 --install-dir /Volumes/To/repos/android/NDK/arm64;
	/Volumes/To/repos/android/sdk/ndk-bundle/build/tools/make_standalone_toolchain.py --force --api 29 --arch arm --install-dir /Volumes/To/repos/android/NDK/arm;
	/Volumes/To/repos/android/sdk/ndk-bundle/build/tools/make_standalone_toolchain.py --force --api 29 --arch x86 --install-dir /Volumes/To/repos/android/NDK/x86;

build:
	cp config ~/local/rust/config
	cargo build --target aarch64-linux-android --release
	cargo build --target armv7-linux-androideabi --release
	cargo build --target i686-linux-android --release
	rm -rf $$JNI_LIBS
	mkdir -p $$JNI_LIBS/{arm64-v8a,armeabi-v7a,x86}
	cp ../target/aarch64-linux-android/release/librandroid.so randroidem/app/src/main/jniLibs/arm64-v8a/librandroid.so
	cp ../target/armv7-linux-androideabi/release/librandroid.so randroidem/app/src/main/jniLibs/armeabi-v7a/librandroid.so
	cp ../target/i686-linux-android/release/librandroid.so randroidem/app/src/main/jniLibs/x86/librandroid.so
	rm ~/local/rust/config

clean:
	cargo clean
	cd randroidem && gradle clean ; gradle --stop ; cd -
