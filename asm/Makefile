VERSION = 0.0.1

SRC := $(wildcard ./*.asm)
OBJ := $(patsubst ./%.asm, build/%.o, $(SRC))

CC = as

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

build/%.o: ./%.asm
	$(CC) $< -o $@
	ld $@ -o $@.exe -lSystem

.PHONY: clean
clean:
	rm -rf build/
