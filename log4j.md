log4j.md

#### - 3个实体

- 日志记录器（Logger）
- 输出端（Appender）
- 日志格式化器（Layout）

一个 Logger 可以对应 N 个 Appender ，一个 Appender 对应一个 Layout。
<table>
    <tr>
        <td rowspan="3">Logger</td>
        <td><-- Appender1 <-- Layout1</td>
    </tr>
    <tr>
        <td><-- Appender2 <-- Layout2</td>
    </tr>
    <tr>
        <td><-- Appender3 <-- Layout3</td>
    </tr>
</table>

#### 1. Logger
Logger 被指定为实体，用一个 String 类的名字标识，这个名字是大小写敏感的。    
Logger 具有继承关系，通过名字表示，例如 x.y 是 x.y.z 的父级。    
root Logger 是所有 Logger 的祖先，它总是存在，而且它 **不可以通过名字获得**。 

root Logger 可以通过以下方法获得： ** TODO:检查一下源码！ **
```java 
public Logger{
    // 方法1：
    public static Logger getRootLogger();

    // 方法2：这是最理想的方法
    public static Logger getLogger(Class clazz);
}
```

>Q1: 我们平常用的都是 root Logger ？

root Logger 在配置文件中的配置：
```
log4j.rootLogger = [level], appenderName1, appenderName2, ...
```

其中的 level 就是日志的级别。
Log4j 建议的日志级别有4种：优先级由高到低分别是：error，warn，info，debug。

```java
package log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jTest {
    public static void main(String[] args) {
       
        Logger logger = Logger.getLogger(Log4jTest.class);
        
        //使用默认的配置信息，不需要写log4j.properties
        BasicConfigurator.configure();
        //设置日志输出级别为info，这将覆盖配置文件中设置的级别
        logger.setLevel(Level.INFO);
        //下面的消息将被输出
        logger.info("this is an info");
        logger.warn("this is a warn");
        logger.error("this is an error");
        logger.fatal("this is a fatal");

    }
}
```

#### 2. Appender
Appender 用来指定日志信息输出的位置。
几个常用的输出位置：

```java
// 1. 输出到控制台
org.apache.log4j.ConsoleAppender
// 2. 输出到一个文件
org.apache.log4j.FileAppender
// 3. 输出到日志文件，文件超过一定大小时自动生成一个新的文件
org.apache.log4j.RollingFileAppender
// 4. 每天生成一个日志文件
org.apache.log4j.DailyRollingFileAppender
// 5. 以流的形式输出到任意指定位置
org.apache.log4j.WriteAppender
// 6. 以 JDBC 的方式输出到数据库
org.apache.log4j.jdbc.JDBCAppender
```

#### 3. Layout








--- 
原文地址：
[Log4j 使用笔记](http://www.cnblogs.com/eflylab/archive/2007/01/11/618001.html)
