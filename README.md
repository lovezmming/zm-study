# zm-study
spring cloud and eureka,
a simple distributed architecture,
we need a service center and multiple services to achieve,
then, 
on this basis, 
we should perfect it.

1.server: providing eureka client;
2.model: providing entity;
3.data: providing datasource services;
4.service: providing interface services;
5.common: common service;

we continue to improve i's functions.

spring 学习
1.spring boot：已完成单机版文件上传下载解析处理服务（eclipse），可以独立部署。
2.spring cloud：（intelliJ idea）
  简单搭建spring cloud 集群，采用一个server服务作为eureka服务注册中心，整合cache、elasticsearch、qiniu、mq等多种中间件。
  数据库采用hibernate进行简单链接mysql，也可使用mybatis与Oracle。
  拆分数据库操作与应用操作，如果是大集群服务，可以进行多server部署，以及多层次拆分实现。
  本服务整合自我学习以及工作中用到的技能点，以及学习熟悉idea工具的使用，个人还是喜欢eclipse，毕竟用了四五年，开发起来很顺手。
  主要技能点：
  1.eureka
  2.qiniu
  3.cache
  4.mq
  5.elasticsearch
  6.jiguang\im\wechat

ps：
  1.本服务为个人学习整合服务，内容比较杂，详细功能后续会慢慢完善。
  2.由于使用spring cloud，对于nginx、zk的使用基本没有涉及。
  3.服务的发布采用docker。
  4.spring cloud 服务断路器、网关等功能...

3.spring 基础
3.1栈和堆的特点
3.1.1 栈：
      1）函数中定义的基本类型变量，对象的引用变量都在函数的栈内存中分配。
      2）栈内存特点，数数据一执行完毕，变量会立即释放，节约内存空间。
      3）栈内存中的数据，没有默认初始化值，需要手动设置。
3.1.2 堆：
      1）堆内存用来存放new创建的对象和数组。
      2）堆内存中所有的实体都有内存地址值。
      3）堆内存中的实体是用来封装数据的，这些数据都有默认初始化值。
      4）堆内存中的实体不再被指向时，JVM启动垃圾回收机制，自动清除
   注：局部变量：定义在函数中的变量、定义在函数中的参数上的变量、定义在for循环内部的变量
3.2 值与址
3.2.1 堆与栈
      1）栈：基本数据类型、数据的引用变量，这两种存放在栈内存
      2）堆：new创建的对象（包换数组），是存放在堆内存中，同时分配一个内存地址值，并将其附值给引用他的变量
3.2.2 值与址
      1）传值传的是基本类型，通过参数传递给方法，传的是值的拷贝，所以函数体内的值的变化，不会影响函数体外的值
      2）传地址传入的是对象的引用，通过参数传给方法，传的是地址的拷贝，如果在函数体内，改变传入地址所指向的对象数据，
         会影响函数体外的对象。这个事情可以这么去理解 ，我把地址的内存传给了方法，方法改变了内存中的内容，所以指向它的引用的值自然都会改变
3.3长连接和短链接
3.3.1 短连接: 连接->传输数据->关闭连接 
3.3.2 长连接: 连接->传输数据->保持连接 -> 传输数据-> ………..->直到一方关闭连接，多是客户端关闭连接。
3.4http与https
3.4.1 http：
      是互联网上应用最为广泛的一种网络协议，是一个客户端和服务器端请求和应答的标准（TCP），用于从WWW服务器传输超文本到本地浏览器的传输协议，它可以使浏览器更加高效，使网络传输减少。
3.4.2 https:
      是以安全为目标的HTTP通道，简单讲是HTTP的安全版，即HTTP下加入SSL层，HTTPS的安全基础是SSL，因此加密的详细内容就需要SSL。
3.5泛型
3.5.1 T与？
      1）T只支持单类型，？支持多类型
3.6...
      
      