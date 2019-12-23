package hello

import (
	"testing"
)

func TestHttpClientGet(t *testing.T) {
	get("http://baidu.com")
}
