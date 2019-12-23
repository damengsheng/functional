export GO111MODULE=on

export GOPROXY=https://goproxy.io
#export GOPROXY=https://gocenter.io
# 设置不走 proxy 的私有仓库，多个用逗号相隔
# go env -w GOPRIVATE=*.corp.example.com

# Go Checksum Database
export GOSUMDB=gosum.io+ce6e7565+AY5qEHUk/qmHc5btzW45JVoENfazw8LielDsaI+lEbq6
#export GOSUMDB='off'

default: clean all

all: clean fmt list

env:
	go env

fmt:
	go fmt .
	go mod tidy
	go mod verify

update:
	go get -u

list:
	go list -m -json all

clean:
	go clean