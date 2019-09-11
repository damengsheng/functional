default: all

export out_dir=../target/debug/deps

members=./ bridgec

all: fmt test build

clean:
	RUST_BACKTRACE=1 cargo clean -v

fmt:
	cargo verify-project --frozen
	cargo fmt
	cargo fmt --all -- --check
	cargo verify-project

build: clean fmt
	for i in $(members); do \
		cd $$i ; RUST_BACKTRACE=1 cargo build -v ; cd - ; \
	done

test: clean
	for i in $(members); do \
		cd $$i ; RUST_BACKTRACE=1 cargo test -- --test-threads 12 --nocapture ; cd - ; \
	done

update:
	cargo update

doc:
	cargo doc

metadata:
	cargo metadata --verbose --format-version 1 --all-features
