package hello

import (
	"fmt"
	"testing"
	"time"
)

func TestHello(t *testing.T) {

	var rt int64 = Constant()
	println(rt)

	var env map[string]string = Env()
	for envk, envv := range env {
		fmt.Println(envk, envv)
	}

}

func TestString(t *testing.T) {
	String()
}

func TestDate(t *testing.T) {
	Date(time.Now())
}

func TestPoint(t *testing.T) {
	Point()
}

func TestControlFlowIfElse(t *testing.T) {

	remainder := *ControlFlowIfElse()
	for iter := remainder.Front(); iter != nil; iter = iter.Next() {
		val := iter.Value
		if nil != val {
			fmt.Printf("%d ", val)
		}

	}
	fmt.Printf("\n")
}

func TestControlFlowSwitch(t *testing.T) {

	remainder := *ControlFlowSwitchCase()
	for iter := remainder.Front(); iter != nil; iter = iter.Next() {
		val := iter.Value
		switch val {
		case val != nil:
			fmt.Printf("%d ", val)
		}

	}
	fmt.Printf("\n")
}

func TestForGoto(t *testing.T) {

	ForGoto()
	fmt.Printf("\n")

}

func TestMultiplyExec(t *testing.T) {

	MultiplyExec()
	fmt.Printf("\n")

}

func TestVarargs(t *testing.T) {

	Varargs(575659, 6870, 6806, 96809680, 7808, 60680, 8087, 3634, 634, 63, 456, 354, 735, 7, 5, 74, 57, 645, 74, 6, 76, 574, 4, 7373)
	fmt.Printf("\n")

}

func TestFibonacci(t *testing.T) {

	result := 0
	for i := 0; i <= 10; i++ {
		result = Fibonacci(i)
		fmt.Printf("Fibonacci(%d) Result: %d \n", i, result)
	}

}

func TestFuncAsArg(t *testing.T) {

	FuncAsArg(100, Add)

	fmt.Printf("\n")

}

// 测试匿名函数创建、调用
func TestAnonymityFunction(t *testing.T) {

	a, b := 1, 2
	// 匿名函数
	rt := func(a, b int) int { return a + b }(a, b)
	fmt.Printf("%d %d %d \n", a, b, rt)

}

// 测试defer function
func TestDeferFunc(t *testing.T) {
	fmt.Printf("%d \n", DeferFunc(10))
}
