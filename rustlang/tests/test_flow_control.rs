#[cfg(test)]
use example::flow_control;

#[test]
fn test_flow_control_if_else() {
    flow_control::fc_if_else(5);
    flow_control::fc_if_else(-5);
}

#[test]
fn test_flow_control_loop() {
    flow_control::fc_loop();
}

#[test]
fn test_nesting_and_label() {
    flow_control::fc_nesting_and_label();
}

#[test]
fn test_returning_from_loop() {
    let result = flow_control::fc_return_from_loop();
    assert_eq!(result, 20);
}

#[test]
fn test_while() {
    flow_control::fc_while();
}

#[test]
fn test_for_in() {
    flow_control::fc_for_in();
}

#[test]
fn test_for_iterators() {
    flow_control::fc_for_iterators();
}

#[test]
fn test_fc_match() {
    flow_control::fc_match();
    flow_control::fc_match_destructure();
}

#[test]
fn test_match_guard() {
    flow_control::match_guard();
}

#[test]
fn test_match_binding() {
    flow_control::match_binding();
}

#[test]
fn test_if_let() {
    flow_control::if_let();
}

#[test]
fn test_while_let() {
    flow_control::while_let();
}
