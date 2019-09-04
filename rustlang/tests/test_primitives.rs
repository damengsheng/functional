use example::primitives;

#[cfg(test)]
#[test]
fn test_primitives() {
    primitives::scalar_types();
    primitives::literals_operators();

    let (boolean, integer) = primitives::tuple_reverse((18i32, true));
    println!("tuple reverse {} {}", boolean, integer);

    primitives::multi_tuple();
    primitives::array_slice();
    primitives::constants();
}
