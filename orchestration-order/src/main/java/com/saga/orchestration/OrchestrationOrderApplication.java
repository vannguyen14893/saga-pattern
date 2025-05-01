package com.saga.orchestration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class OrchestrationOrderApplication {


    public static void main(String[] args) {
        SpringApplication.run(OrchestrationOrderApplication.class, args);
    }


}
