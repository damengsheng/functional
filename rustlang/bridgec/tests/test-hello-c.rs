extern "C" {
    fn hello();
}

#[cfg(test)]
#[test]
fn test_hfc() {
    unsafe {
        hello();
    }
}
