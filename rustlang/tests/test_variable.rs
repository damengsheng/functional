use example::variable;

#[cfg(test)]
#[test]
fn test_variable() {
    variable::variable_binding();
    variable::mutability();
    variable::socp_shadowing();
}
