package com.ctl.corona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronaStatisticsApplication {

    public static void main(String[] args) {

        SpringApplication.run(CoronaStatisticsApplication.class, args);
    }

}
