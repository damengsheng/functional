/// Java Class Byte Code Struct
pub struct RawClass {
    magic: u8,
    minor_version: u8,
    major_version: u8,
    constant_pool_count: u8,
    constant_pool: Vec<CpInfo>,
    access_flags: u8,
    this_class: u8,
    super_class: u8,
    interfaces_count: u8,
    interfaces: u8,
    fields_count: u8,
    fields: Vec<FieldInfo>,
    methods_count: u8,
    methods: Vec<MethodInfo>,
    attributes_count: u8,
    attributes: Vec<AttributeInfo>,
}

pub struct CpInfo {}

pub struct FieldInfo {}

pub struct MethodInfo {}

pub struct AttributeInfo {}

/// Read Class File
pub fn read_class() {
    println!("{}", "read class file");
}
