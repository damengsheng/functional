package hello

import (
	"container/list"
	"fmt"
	"os"
	"strconv"
	"strings"
	"time"
)

func init() {
	println("hello init")
}

const (
	One   = 1
	Two   = 2
	Three = 3
)

const OneHundredMillion int64 = 100000000

func Constant() int64 {

	return OneHundredMillion + One + Two + Three

}

func Env() map[string]string {

	var uid = os.Geteuid()
	//var uid_str = strconv.Itoa(uid)
	uid_str := strconv.Itoa(uid)
	var env = map[string]string{
		"pid":    strconv.Itoa(os.Getpid()),
		"user":   os.Getenv("USER"),
		"home":   os.Getenv("HOME"),
		"goroot": os.Getenv("GOROOT"),
		"uid":    uid_str,
	}

	return env

}

func String() {
	var ch byte = 65
	var chh byte = '\x41'
	println(ch)
	println(chh)

	var str = "yakir" + "Chen"
	println(str)

	isContains := strings.Contains(str, "y")
	println(isContains)

	var index = strings.Index(str, "C")
	println("index of C " + strconv.Itoa(index))
}

func Date(t time.Time) {
	fmt.Println(t.Format("02 Jan 2006 15:04:000"))
}

func Point() {

	var i = 10
	var ip *int = &i
	fmt.Printf("%d \n", *ip)
	fmt.Printf("%d \n", i)

	*ip = 11
	fmt.Printf("%d \n", *ip)
	fmt.Printf("%d \n", i)

}

func ControlFlowIfElse() *list.List {

	remainder := list.New()

	for i := 1; i < 10; i++ {
		if i%2 == 0 {
			remainder.PushFront(i)
		} else {
			fmt.Printf("%d ", i)
		}
	}

	fmt.Printf("\n")

	return remainder
}

func ControlFlowSwitchCase() *list.List {

	remainder := list.New()

	for i := 1; i < 10; i++ {
		r := i % 2
		switch r {
		case 0:
			remainder.PushFront(i)
		default:
			fmt.Printf("%d ", i)
		}
	}

	fmt.Printf("\n")

	return remainder
}

func ForGoto() {

	i := 0

GO:
	for {

		if i >= 20 {
			break
		}

		if i%2 == 0 {
			i++
			goto GO
		} else {

			i++
		}

		fmt.Printf("%d ", i)
	}

}

func Multiply(a, b int, reply *int) {

	*reply = a * b

}

func MultiplyExec() {

	n := 0
	reply := &n
	Multiply(10, 5, reply)
	fmt.Printf("Multiply: %d", *reply)

}

func Varargs(args ...int) {

	for arg := range args {
		fmt.Printf("%d ", args[arg])
	}

}

func Fibonacci(i int) (rt int) {

	if i <= 1 {
		rt = i
	} else {
		rt = Fibonacci(i-1) + Fibonacci(i-2)
	}

	return
}

func Add(a, b int) {
	fmt.Printf("The Sum of %d %d", a, b)
}

func FuncAsArg(y int, f func(int, int)) {
	f(y, 2)
}

func DeferFunc(ret int) (rt int) {

	defer func() {
		ret++
	}()
	return 1
}
