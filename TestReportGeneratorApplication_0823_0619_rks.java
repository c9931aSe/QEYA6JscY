// 代码生成时间: 2025-08-23 06:19:21
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/mvc
                           http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- Enable annotation driven mvc and component scanning -->
    <context:component-scan base-package="com.testreportgenerator"/>
    <mvc:annotation-driven />

    <!-- Exception handler bean definition -->
    <bean class="com.testreportgenerator.exception.TestReportExceptionHandler"/>
# 改进用户体验
</beans>