use example::printfmt;

#[cfg(test)]
#[test]
fn test_fmt_print() {
    printfmt::positional_args();
    printfmt::named_args();
    printfmt::special_fmt();
    printfmt::right_align_fmt();
    printfmt::pad_extra_fmt();
    printfmt::array_display();
    printfmt::brace_display();
    printfmt::struct_debug_display();
    printfmt::struct_impl_display();
    printfmt::list_display();
    printfmt::struct_list_display()
}
