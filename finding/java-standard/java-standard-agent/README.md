# Java Agent

## Agent加载方式
1. 静态加载 (premain)

```java
public class Agent {
    public static void premain(String agentArgs, Instrumentation inst){}
    public static void premain(String agentArgs){}
}
```

2. 动态加载 (agentmain)

```java
public class Agent {
    public static void agentmain(String agentArgs, Instrumentation inst){}
    public static void agentmain(String agentArgs){}
}
```

## MANIFEST.MF
```properties
Premain-Class	When an agent is specified at JVM launch time this attribute defines the Java agent class: the class containing the premain method. When an agent is specified at JVM launch time this attribute is required. If the attribute is not present the JVM will abort.
Agent-Class	If an implementation supports a mechanism to start Java agents sometime after the JVM has started then this attribute specifies the agent class: the class containing the agentmain method. This attribute is required and the agent will not be started if this attribute is not present.
Boot-Class-Path	A list of paths to be searched by the bootstrap class loader. Paths represent directories or libraries.
Can-Redefine-Classes	A value of true or false, case-insensitive and defines if the ability to redefine classes needed by this agent. This attribute is optional, the default is false.
Can-Retransform-Classes	A value of true or false, case-insensitive and defines if the ability to retransform classes needed by this agent. This attribute is optional, the default is false.
Can-Set-Native-Method-Prefix	A value of true or false, case-insensitive and defines if the ability to set native method prefix needed by this agent. This attribute is optional, the default is false.
```