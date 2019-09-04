package main

type I interface {
    f()
}

type S struct {
    a int64
}

func (this *S) f() {
    this.a++
}

func main() {
    var s S
    var i I = &s
    for j := 0; j < 1000000000; j++ {
        i.f()
    }
    println(s.a)
}
