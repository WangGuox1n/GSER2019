### 开发环境

-   Ubuntu 16.04 LTS
-   JDK 8
-   Maven 3.3.9

### 编译
```$bash
mvn package
```
### 测试    

    jmtrace -jar target/traceagent-tests.jar    # Change the path separator if you are on Windows.
    jmtrace -cp target/traceagent-tests.jar test.TestType
    jmtrace -cp target/traceagent-tests.jar test.TestThread
    
To run your own programs, `jmtrace` has the same usage as `java`.

## Resources
1. [ASM guide](https://asm.ow2.io/asm4-guide.pdf)
2. [Java 6 Instrument](https://www.ibm.com/developerworks/cn/java/j-lo-jse61/index.html)
3. [Java bytecode list](https://en.wikipedia.org/wiki/Java_bytecode_instruction_listings)
4. [使用ASM对Java字节码插桩](https://c0d3p1ut0s.github.io/%E8%AF%91-%E4%BD%BF%E7%94%A8ASM%E5%AF%B9Java%E5%AD%97%E8%8A%82%E7%A0%81%E6%8F%92%E6%A1%A9/)