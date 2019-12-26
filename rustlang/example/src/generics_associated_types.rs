//! Associated types 关联类型

pub struct Container(i32, i32);

impl Container {
    pub fn new(no1: i32, no2: i32) -> Container {
        Container(no1, no2)
    }
}

pub trait Contains {
    type A;
    type B;

    fn contains(&self, _: &Self::A, _: &Self::B) -> bool;
    fn first(&self) -> i32;
    fn last(&self) -> i32;
}

impl Contains for Container {
    type A = i32;
    type B = i32;

    fn contains(&self, no1: &i32, no2: &i32) -> bool {
        (&self.0 == no1) && (&self.1 == no2)
    }

    fn first(&self) -> i32 {
        self.0
    }

    fn last(&self) -> i32 {
        self.1
    }
}

pub fn difference<C: Contains>(container: &C) -> i32 {
    container.last() - container.first()
}
