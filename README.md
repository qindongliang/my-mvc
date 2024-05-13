Tomcat + IDEA 2023.2 + Spring MVC 项目演示

使用Java 做企业级Web开发，十多年前，SSH（Spring + Struts + Hibernate）框架很火，当时流行的 IDE 是 Eclipse 和 MyEclipse，后来过了几年变成了 SSM（Spring + Spring MVC + Mybatis)，直到 2014 年 4 月，Spring 发布 Spring Boot 第一个正式版本后，后续新的微服务 web 项目基本都用 Spring Boot 开发了，其遵循的约定大于配置的理念，极大提升了开发效率，再配合上 JetBrains 公司的 IDE 全家桶 IntelliJ IDEA 真是香的一批。

当然，自从用了spring boot后，我基本没再用过纯 spring mvc 项目，因为真的太麻烦了需要配置一大堆xml，虽然也能搭配注解版本开发，但还是效率太低且容易出错。

最近帮同事排查一个问题，由于是历史老Spring Mvc 项目，为了方便复现问题，我需要在本地 IDEA 中搭建一个最小化测试环境，然后 debug 调试，本来以为很 easy，结果还折腾了挺久，问题最终解决，特此记录一下，以帮助后人避坑。
前置依赖
spring mvc 项目的运行，需要部署到 tomcat 或 jetty这类的web容器中才行，因为其不像spring boot 默认已经内置了tomcat来运行服务，所以我们还需要到tomcat到官网去单独下载，这里我推荐：
jdk8 项目：下载 tomcat 9
jdk11 或 jdk17：下载 tomcat 10
下载解压之后，熟悉的目录结构如下：


总体来说，当前使用 IDEA 创建纯 Spring MVC 项目不算难，但是结合 tomcat + idea + xml 配置运行部署起来，还是太复杂了，中间每一步配置错误都有可能导致访问不了，这也是为什么流行微服务都用 spring boot 或spring cloud 项目的原因，通过约定大于配置的理念，开箱即用，大大提升了开发效率。

有任何问题或者疑问都可关注公众号我是攻城师获取（woshigcs），可留言咨询
