VERSION = 0.0.1

SRC := $(wildcard src/*.cpp)
OBJ := $(patsubst src/%.cpp, build/%.o, $(SRC))
INCLUDE = -I/Volumes/To/github/json/simdjson/include/
# INCLUDE = -I/Volumes/To/github/json/simdjson/singleheader/
# INCLUDE = -I./include

LIBS = /Volumes/To/github/json/simdjson/build/libsimdjson.dylib
# LIB_PATH = /Volumes/To/github/json/simdjson/build/

DEBUG = -D_MACRO
CPPFLAGS = -std=c++17 -m64 -O3

JAR_BASE = ./libs
FASTJSON_LIB = ./libs/fastjson-1.2.56.jar
DEPS = ./libs/fastjson-1.2.56.jar:./libs/javax.servlet-api-3.1.0.jar:./libs/javax.ws.rs-api-2.0.1.jar:./libs/cxf-rt-transports-http-3.1.2.jar:./libs/cxf-rt-frontend-jaxrs-3.1.2.jar:./libs/spring-websocket-4.3.7.RELEASE.jar:./libs/spring-webmvc-4.3.7.RELEASE.jar:./libs/spring-messaging-4.3.7.RELEASE.jar:./libs/spring-data-redis-1.8.6.RELEASE.jar:./libs/retrofit-2.1.0.jar:./libs/okhttp-3.6.0.jar:./libs/springfox-spring-web-2.6.1.jar:./libs/javaslang-2.0.6.jar:./libs/jersey-common-2.23.2.jar:./libs/joda-time-2.10.jar
# JSON_FILE = /Volumes/To/github/json/simdjson/jsonexamples/twitter.json
JSON_FILE = /Volumes/To/github/json/simdjson/jsonexamples/canada.json
LIB_FASTJSON = /Volumes/To/java/libFastJson.so
LIB_JAVA_BASE = /Volumes/To/java/libjava.base.so
JVM_DEFAULT_OPTS = -Xms1G -Xmx1G -XX:+UseG1GC -XX:+UseAOT -XX:+AlwaysPreTouch -XX:+UseNUMA -XX:+UseCompressedOops -XX:+UnlockExperimentalVMOptions -XX:+UnlockDiagnosticVMOptions -XX:+UseAOTStrictLoading #-XX:+EnableJVMCI -XX:+UseJVMCICompiler
# JVM_OPTS = $(JVM_DEFAULT_OPTS) -XX:AOTLibrary=$(LIB_JAVA_BASE),$(LIB_FASTJSON)
JVM_OPTS = $(JVM_DEFAULT_OPTS) -XX:+PrintAOT -XX:AOTLibrary=$(LIB_JAVA_BASE)
JVM_LOG_OPTS = -Xlog:class+load=info #-Xlog:gc

CC = clang++

default: all

info:
	@echo $(SRC)
	@echo $(OBJ)

all: prepare build/main.o
	@ls -l build/
	# $(CC) $(OBJ) $(LIB_PATH) $(LIBS) -o build/$(TARGET).$(VERSION)

.PHONY: prepare
prepare:
	@mkdir -p build/

build/main.o: src/main.cpp
	# $(CC) $(INCLUDE) $(CPPFLAGS) $< -o $@
	$(CC) $(INCLUDE) $(DEBUG) $(CPPFLAGS) $(LIBS) $< -o $@
	# $(CC) $(INCLUDE) $(DEBUG) $(CPPFLAGS) $(LIB_PATH) $(LIBS) $< -o $@

sindjsonrun: build/main.o
	./build/main.o $(JSON_FILE)

fastjson:
	/usr/libexec/java_home -v 11 --exec javac -g:none -cp $(FASTJSON_LIB) src/FastJson.java -d build

aotpre:
	/usr/libexec/java_home -v 11 --exec jaotc --output $(LIB_JAVA_BASE) --module java.base

aot: fastjson
	/usr/libexec/java_home -v 11 --exec jaotc --compile-threads 12 -J-cp -J./build:$(DEPS) --output $(LIB_FASTJSON) FastJson.class

fastjsonrun: aot
	/usr/libexec/java_home -v 11 --exec java -cp build:$(FASTJSON_LIB) $(JVM_OPTS) $(JVM_LOG_OPTS) FastJson $(JSON_FILE)


.PHONY: clean
clean:
	rm -rf $(OBJ) build/