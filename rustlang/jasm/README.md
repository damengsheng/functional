# Java Byte Code



Java Byte Code Decompiler
```bash
cd build/1.8 && javap -v -p -c -s -constants -l JavaCode > docs/cls_8_decompiler.asm && cd -
```

Class File Hex
```bash
hexyl --color never build/1.8/JavaCode.class > docs/cls_8_hex
hexyl --color never build/11/JavaCode.class > docs/cls_11_hex
hexyl --color never build/12/JavaCode.class > docs/cls_12_hex
hexyl --color never build/13/JavaCode.class > docs/cls_13_hex
```

##### References
[Java Language and Virtual Machine Specifications](https://docs.oracle.com/javase/specs/)
