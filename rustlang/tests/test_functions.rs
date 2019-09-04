use example::domain::{Point, Rectangle};
use example::functions;

#[cfg(test)]
#[test]
fn test_fn_fizzbuzz_to() {
    functions::fizzbuzz_to(100);
}

#[test]
fn test_func_method() {
    let point = Point::new(0.0, 1.1);
    point.print();
    let mut rect: Rectangle = Rectangle::new(0.1, 1.0, 1.1, 2.0);
    let area: f64 = rect.area();
    println!("rect area: {}", area);
    let perimeter: f64 = rect.perimeter();
    rect.translate(888.0f64, 111.0f64);
    let area: f64 = rect.area();
    println!("area: {} perimeter: {}", area, perimeter);
}

#[test]
fn test_anonymous_func() {
    let rt = functions::anonymous_func(999);
    println!("anonymous closures {}", rt);
}

#[test]
fn test_closures() {
    functions::closures();
}

#[test]
fn test_capture() {
    functions::capture();
}

#[test]
fn test_closures_input_parameters() {
    functions::closures_input_parameters();
}

#[test]
fn test_closures_input_function() {
    functions::closures_input_function();
}

#[test]
fn test_closures_output_parameters() {
    functions::closures_output_parameters();
}

#[test]
fn test_closures_std_any() {
    functions::closures_std_any();
}

#[test]
fn test_closures_std_find() {
    functions::closures_std_find();
}

#[test]
fn test_higher_order_functions() {
    functions::higher_order_functions();
}

#[test]
fn test_diverging_sum_odd_numbers() {
    let diverging_sum_odd_numbers = functions::diverging_sum_odd_numbers(9);
    println!(
        "sum of odd numbers up tp 9 (excluding): {} ",
        diverging_sum_odd_numbers
    );
}
