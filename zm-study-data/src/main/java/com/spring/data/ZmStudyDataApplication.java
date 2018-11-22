package com.spring.data;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = MybatisAutoConfiguration.class)
@EnableEurekaClient
@MapperScan(basePackages = "com.spring.data.database.mybatis.mapper")
@EntityScan(basePackages = "com.spring.data.database.mybatis.model")
public class ZmStudyDataApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ZmStudyDataApplication.class, args);
    }

}
