package main

type E struct {
	a int32
}

func main() {
	const ARRAY_COUNT int64 = 10000
	const TEST_COUNT int64 = ARRAY_COUNT * 100000

	var es [ARRAY_COUNT]*E
	for i := int64(0); i < TEST_COUNT; i++ {
		es[i*123456789%ARRAY_COUNT] = &E{a: int32(i)}
	}

	n := int64(0)
	for i := int64(0); i < ARRAY_COUNT; i++ {
		e := es[i]
		if e != nil {
			n += int64(e.a)
		}
	}
	println(n)
}
