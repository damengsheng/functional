VERSION = 0.0.1

#$?	当前目标所依赖的文件列表中比当前目标文件还要新的文件
#$@	当前目标的名字
#$<	当前依赖文件的名字
#$*	不包括后缀名的当前依赖文件的名字

SRC := $(PWD)/../jasm/src/JavaCode.java
FIN := JavaCode
JAVA_VER := 1.8 11 12 13 14
TARGETS := $(foreach v,$(JAVA_VER),build/$(v))
COMPILE := /usr/libexec/java_home -v $(1) --exec javac -g $(SRC) -d build/$(1)
DOC_DIR := $(PWD)

default: all

info:
	echo $(JAVA_VER)
	echo $(TARGETS)
	echo $(DOC_DIR)

all: clean prepare $(JAVA_VER)
	find build | xargs ls -leGd
#	ls -eGlHR build

.PHONY: prepare
prepare:
	mkdir -p $(TARGETS)

$(JAVA_VER):
	/usr/libexec/java_home -v $@ --exec javac -g $(SRC) -d build/$@
	cd build/$@ && /usr/libexec/java_home -v $@ --exec javap -v -p -c -s -sysinfo -constants -l $(FIN) > $(DOC_DIR)/cls_$@.asm && cd -
	hexyl --color never build/$@/$(FIN).class > $(DOC_DIR)/cls_$@.hex

.PHONY: clean
clean:
	rm -rf build/
