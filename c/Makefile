VERSION = 0.0.1

SRC := $(wildcard c/*.c)
OBJ := $(patsubst c/%.c, build/%.o, $(SRC))
# INCLUDE = -I./h

# LIBS = -ldylib         # 库文件名字
# LIB_PATH = -L./lib     # 库文件地址

DEBUG = -D_MACRO
CFLAGS = -std=c17 -Wall -Wformat -m64 -O3

CC = clang

default: all

info:
	@echo $(SRC)
	@echo $(OBJ)

all: prepare $(OBJ)
	@ls -l build/
	# $(CC) $(OBJ) $(LIB_PATH) $(LIBS) -o build/$(TARGET).$(VERSION)

.PHONY: prepare
prepare:
	@mkdir -p build/

build/%.o: c/%.c
	$(CC) $(DEBUG) $(CFLAGS) $< -o $@
	# $(CC) $(INCLUDE) $(DEBUG) $(CFLAGS) $< -o $@


.PHONY: clean
clean:
	rm -rf $(OBJ) build/