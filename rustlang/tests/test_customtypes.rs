use example::customtypes;

#[cfg(test)]
#[test]
fn test_customtypes() {
    customtypes::struct_person();
    customtypes::inspect(customtypes::WebEvent::PageLoad);
    customtypes::inspect(customtypes::WebEvent::Paste("hello".to_owned()));
    customtypes::inspect(customtypes::WebEvent::KeyPress('x'));
    customtypes::inspect(customtypes::WebEvent::Click { x: 100, y: 135 });
    customtypes::rgb();
    customtypes::linked_list();
}
