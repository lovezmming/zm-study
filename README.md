# zm-study
spring cloud and eureka,
a simple distributed architecture,
we need a service center and multiple services to achieve,
then, 
on this basis, 
we should perfect it.

版本：

结构：
1.server: providing eureka client;
2.model: providing entity;
3.data: providing datasource services;
4.service: providing interface services;
5.common: common service;

2018.11.22
1.spring cloud基础架构实现
2.增加eureka作为服务注册中心
3.实现hibernate访问数据库操作

2019.1.9
1.增加kafka实现
2.增加elasticsearch索引实现
3.增加qiniu文件处理接口
4.增加mybatis

2019.4.25
1.微调配置与结构
2.调整部分properties为yml