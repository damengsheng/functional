/// 在linux上运行
#[cfg(target_os = "linux")]
fn are_you_on_linux() {
    println!("You are running linux!");
}

/// 在**非**linux上运行
#[cfg(not(target_os = "linux"))]
fn are_you_on_linux() {
    println!("You are *not* running linux!");
}

/// rustc --cfg yakir_custom_cfg attributes.rs && ./attributes
#[cfg(yakir_custom_cfg)]
fn yakir_custom_cfg() {
    println!("yakir custom cfg");
}

fn main() {
    are_you_on_linux();

    println!("Are you sure?");
    if cfg!(target_os = "linux") {
        println!("Yes. It's definitely linux!");
    } else {
        println!("Yes. It's definitely *not* linux!");
    }

    //yakir_custom_cfg();
}
