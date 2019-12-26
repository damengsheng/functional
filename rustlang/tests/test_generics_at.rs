use example::generics_associated_types::{difference, Container, Contains};

#[cfg(test)]
#[test]
fn test_associated_types() {
    let no1 = 11;
    let no2 = 100;

    let container = Container::new(no1, no2);
    println!(
        "Does Container Contain {} and {}: {}",
        &no1,
        &no2,
        container.contains(&no1, &no2)
    );
    println!(
        "First Number: {} Last Number: {}",
        container.first(),
        container.last()
    );

    println!("The Difference Is: {}", difference(&container));
}
