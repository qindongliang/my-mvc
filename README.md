Tomcat + IDEA 2023.2 + Spring MVC 项目演示

前言
使用Java 做企业级Web开发，十多年前，SSH（Spring + Struts + Hibernate）框架很火，当时流行的 IDE 是 Eclipse 和 MyEclipse，后来过了几年变成了 SSM（Spring + Spring MVC + Mybatis)，直到 2014 年 4 月，Spring 发布 Spring Boot 第一个正式版本后，后续新的微服务 web 项目基本都用 Spring Boot 开发了，其遵循的约定大于配置的理念，极大提升了开发效率，再配合上 JetBrains 公司的 IDE 全家桶 IntelliJ IDEA 真是香的一批。

当然，自从用了spring boot后，我基本没再用过纯 spring mvc 项目，因为真的太麻烦了需要配置一大堆xml，虽然也能搭配注解版本开发，但还是效率太低且容易出错。

最近帮同事排查一个问题，由于是历史老Spring Mvc 项目，为了方便复现问题，我需要在本地 IDEA 中搭建一个最小化测试环境，然后 debug 调试，本来以为很 easy，结果还折腾了挺久，问题最终解决，特此记录一下，以帮助后人避坑。
前置依赖
spring mvc 项目的运行，需要部署到 tomcat 或 jetty这类的web容器中才行，因为其不像spring boot 默认已经内置了tomcat来运行服务，所以我们还需要到tomcat到官网去单独下载，这里我推荐：
jdk8 项目：下载 tomcat 9
jdk11 或 jdk17：下载 tomcat 10
下载解压之后，熟悉的目录结构如下：


有了web容器之后，我们项目打成一个war包后，就可以丢进tomcat的webapps目录下，tomcat就可以自动帮我们解压，不过今天我是在IDEA中运行，所以 IDEA 会帮我完成部署的动作，但前提是需要在IDEA中正确配置使用Tomcat
新建项目
创建一个 spring mvc 项目和创建一个常规的 maven 项目的流程区别不大，主要是spring mvc项目创建完成之后的一系列配置：
点击 File => New => Project，然后当成常规 maven 项目创建即可：

配置项目
接着添加 spring mvc 项目的 pom 依赖，模版配置如下：
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>my-mvc</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <spring.version>4.3.2.RELEASE</spring.version>
        <hibernate.version>4.3.8.Final</hibernate.version>
        <jackson.version>2.5.0</jackson.version>
        <log4j.version>1.2.17</log4j.version>
        <slf4j.version>1.7.25</slf4j.version>
    </properties>

    <dependencies>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.10</version>
        </dependency>


        <!-- log4j -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>${log4j.version}</version>
        </dependency>

        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>${slf4j.version}</version>
        </dependency>


        <!-- spring -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>${spring.version}</version>
        </dependency>


        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- 使用SpringMVC需配置 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>


        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-orm</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- hibernate -->
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>${hibernate.version}</version>
        </dependency>

        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-ehcache</artifactId>
            <version>${hibernate.version}</version>
        </dependency>





        <!-- json -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.83</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>${jackson.version}</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>${jackson.version}</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>${jackson.version}</version>
        </dependency>

        <!-- aop -->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.8.4</version>
        </dependency>

        <!-- servlet -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>3.0-alpha-1</version>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>



    </dependencies>


</project>

然后增加 spring mvc 项目的特定目录，如下图示：


log4j.properties：
log4j.rootLogger=INFO, stdout
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d %p [%c] - %m%n

spring-mvc.xml：
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
        http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.1.xsd
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.1.xsd">

        <!-- 开启组件扫描 -->
    <context:component-scan base-package="demo.controller" >
        <!-- 开启注解 -->
    <mvc:annotation-driven />


    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
          id="internalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/WEB-INF/jsp/" />
        <!-- 后缀 -->
        <property name="suffix" value=".jsp" />
    </bean>




</beans>

web.xml:
<!DOCTYPE web-app PUBLIC
        "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
        "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
    <display-name>Archetype Created Web Application</display-name>

    <!--配置SpringMVC的前端控制器-->
    <servlet>
        <servlet-name>DispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <!--关联到spring-mvc.xml上-->
            <param-value>classpath:spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>DispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>



</web-app>

index.jsp:
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="color: red;text-align: center">欢迎来到 spring mvc demo 首页</h1>
</body>
</html>

然后注意 webapp 上有个蓝点，这需要配置一下告诉IDEA这是web项目的目录：
点击 File => Project Structure => Artifacts => 点击最上面点 + => 选择下图的类型 =>  然后点击 ok 即可


配置 Tomcat 运行
点击右上角 Current File，然后下拉框选择 Edit Configurations：

然后选择 Tomcat Server中的 Local：

绑定本地 tomcat：

绑定自己的web应用：

选择web项目：

查看web配置是否正常：

确认配置正常：

最后如果一切正常，但访问首页出现404问题，并且后端controller可以正常收到请求，那么一般是因为我们的WEB-INF 路径配置错了，可以参考下面的配置：

默认的配置生成的因为路径位置不对，会造成访问404：


正常的位置如下：

Controller代码

package demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class DemoController {


    @RequestMapping( "/")
    public String home() {
        log.info("access index2 to skip index.jsp page");
        return "index";
    }

    @RequestMapping( "/index")
    public String index() {
        log.info("access index2 to skip index.jsp page");
        return "index";
    }


    @GetMapping( "/demo2")
    @ResponseBody
    public String demo2(){
        log.info("demo2 access");
        return "demo2 access ok";
    }

}


总结
总体来说，当前使用 IDEA 创建纯 Spring MVC 项目不算难，但是结合 tomcat + idea + xml 配置运行部署起来，还是太复杂了，中间每一步配置错误都有可能导致访问不了，这也是为什么流行微服务都用 spring boot 或spring cloud 项目的原因，通过约定大于配置的理念，开箱即用，大大提升了开发效率。

完整项目代码，可关注公众号我是攻城师获取（woshigcs），回复关键词 springmvc 获取，有任何问题或者疑问都可留言咨询。
