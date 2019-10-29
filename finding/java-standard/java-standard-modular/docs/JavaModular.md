# Java Modular Tutorials

## JVM Options

[1.8 flags final](docs/jvm_options/1.8_print_flags_final.txt)
[1.8 flags initial](docs/jvm_options/1.8_print_flags_initial.txt)
[11 flags final](docs/jvm_options/11_print_flags_final.txt)
[11 flags initial](docs/jvm_options/11_print_flags_initial.txt)
[12 flags final](docs/jvm_options/12_print_flags_final.txt)
[12 flags initial](docs/jvm_options/12_print_flags_initial.txt)
[13 flags final](docs/jvm_options/13_print_flags_final.txt)
[13 flags initial](docs/jvm_options/13_print_flags_initial.txt)
[14 flags final](docs/jvm_options/14_print_flags_final.txt)
[14 flags initial](docs/jvm_options/14_print_flags_initial.txt)


## 一、模块描述
_**<module path> == <module name>**_ 

- name: 模块名称
- dependencies: 模块依赖
- public packages: 模块外部可以访问到的包
- service offered: 分发服务实现供其他模块使用
- service comsumed: 使用其他模块提供给的服务
- reflection permissions: 声明可以通过反射被其他模块访问私有成员的包


## 模块类型
+ System Modules(Java SE 和 JDK 模块)

+ Application Modules(Java SE 和 JDK 模块)
模块根目录包含`module-info.java`,Named Modular(具名模块)

+ Unnamed Modular(无名模块)
**不**包含`module-info.java`(通常是jdk9之前构建)的 _jar_ 包

+ Automatic Modular(自动模块)
任何无名模块放在模块路径(module path)上会自动变为自动模块, 
生成的模块名根据以下规则: 移除文件扩展名以及版本号,然后使用`.` 替换所有非字母字符。
例如(`spring-core-5.1.9.jar` => `spring.core`)


## 分发(Distribution)
1. JAR 
2. "exploded" compiled project

每个JAR文件只能包含一个 Module

## Java Modules
JDK11 ~ JDK14所有模块
- [JDK11 Modules](docs/modules/jdk11_modules.md)
- [JDK12 Modules](docs/modules/jdk12_modules.md)
- [JDK13 Modules](docs/modules/jdk13_modules.md)
- [JDK14 Modules](docs/modules/jdk14_modules.md)


## 模块声明\指令 (Module Declarations)
```java
module module.name {
    // ...
}
```

### Requires
编译时和运行时依赖(runtime and compile-time dependency)
```java
module module.name {
    requires dependency.module.name;
}
```

### Requires Static
编译时依赖(compile-time dependency)
```java
module module.name {
    requires static dependency.module.name;
}
```

### Requires Transitive
编译时依赖(compile-time dependency)
```java
module module.name {
    requires transitive dependency.module.name;
}
```

### exports , exports to
### uses
### provides...with
### open , opens , opens...to


## JVM
### C2
1. Client: CompileThreshold * OnStackReplacePercentage / 100
2. Server: CompileThreshold * (OnStackReplacePercentage - InterpreterProfilePercentage) / 100 
InterpreterProfilePercentage 默认值是 33

##### Reference
+ [https://chriswhocodes.com/hotspot_options_jdk11.html](https://chriswhocodes.com/hotspot_options_jdk11.html)
+ [Java 9 揭秘](https://www.cnblogs.com/IcanFixIt/p/7278696.html)