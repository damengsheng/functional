default: all

export out_dir=../target/debug/deps

all: build
	../target/debug/helloc_r

clean:
	RUST_BACKTRACE=1 cargo clean -v

build: clean
	RUST_BACKTRACE=1 cargo build -v
