# baas-station-booking-artifact
## 0x1. Swaager

### a. Maven依赖

可使用封装好的Swagger库或直接依赖crud-dev，其中已依赖Swagger，如下所示：

```xml
<!-- 其中crud-dev依赖已包含swagger-ui -->
<!--swagger ui-->
<dependency>
    <groupId>com.jfeat</groupId>
    <artifactId>swagger-ui</artifactId>
    <version>0.0.1</version>
</dependency>

<!--curd-dev-->
<dependency>
    <groupId>com.jfeat</groupId>
    <artifactId>crud-dev</artifactId>
    <version>0.0.1</version>
</dependency>
```

### b. 使用方法

待项目启动后，可通过`http://ipaddress:port/swagger-ui.html#/`直接在线查阅已说明的API接口，同时可通过该在线页面发起HTTP请求进行接口调用测试。
