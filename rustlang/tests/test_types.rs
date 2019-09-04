use example::types;

#[cfg(test)]
#[test]
fn test_types_casting() {
    types::casting();
}

#[test]
fn test_types_literal() {
    types::literal();
}

#[test]
fn test_types_inference() {
    types::inference();
}

#[test]
fn test_types_alias() {
    types::alias();
}
