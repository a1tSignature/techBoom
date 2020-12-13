package com.a1tSign.techBoom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = {"com.a1tSign.techBoom"})
public class TechBoomApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechBoomApplication.class, args);
    }

}
