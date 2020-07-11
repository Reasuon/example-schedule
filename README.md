# Schedule-Demo

## 项目介绍

这是一个使用线程池管理定时任务的 Demo.

- 语言: Java
- 框架: Spring Boot
- 数据库: Mysql(非必要，只是手贱加进来的，可以直接删除相关配置和代码)
- 数据库组件: Mybatis Plus + jdbc

其他相关工具请直接查看 `pom.xml`

### 重要代码

`DynamicScheduleManage` --> 负责动态创建定时任务，并加入线程池中  
`ExampleSchedulingConfig` --> 核心配置文件，用于开启定时任务和配置需要的线程池  
`ScheduleDemoApplicationTests` --> 测试文件，包含Demo的定时任务用法与写法  

\*: 线程池一共有两个，一个专门用来做动态的定时任务，另一个专门用来处理所有未指定线程池的定时任务  
`DynamicTaskPool`: 动态定时任务线程池  
`TaskPool`: 默认定时任务线程池  
两个线程池并无特别的差别，只是为了方便对动态任务做展示和管理，才专门新建了一个线程池，不同场景需求也能存在多个定时任务线程池，看需求了

### 可以删除的代码

package: controller + entity + mapper + service  
这四个包以及底下的类都可以删除，只是用来从数据库内读取 cron 的方法，可以直接在 Test 类或配置文件写死  

如果删除了上面的代码，还要做以下几个改动:
1. 启动类 `ScheduleDemoApplication` 中删除 `@MapperScan`[13,13]
2. 测试类 `ScheduleDemoApplicationTests` 中删除从数据库读取数据的代码

	[27,28]
	```java
	@Autowired
    CronMapper cronMapper;
	```
	
	[38,38] 改成其他方式加载 cron 即可
	```
	List<Cron> cronList = cronMapper.selectList(null);
	```
3. 配置文件文件 `application.yml` 中删除数据库配置 [3,8]

	```yml
	spring:
      datasource:
        driverClassName: com.mysql.jdbc.Driver
        url: jdbc:mysql://localhost:3306/example?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC # allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=true
        username: root
        password: admin
	```

4. 配置文件文件 `pom.xml` 中删除数据库相关工具 [27,38]

	```xml
	<!-- Mybatis Plus -->
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.3.2</version>
    </dependency>
    <!-- mysql -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
	```
