package hello

import (
	"fmt"
	"io/ioutil"
	"net/http"
)

func get(url string) {

	response, err := http.Get(url)
	if err != nil {
		// handler err
	}

	defer response.Body.Close()
	body, _ := ioutil.ReadAll(response.Body)
	fmt.Print(string(body))
}
