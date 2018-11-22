package com.spring.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ZmStudyServiceApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ZmStudyServiceApplication.class, args);
    }
}
