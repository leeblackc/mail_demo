package com.lhc.mail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lhc.mail.mapper")
public class MailDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailDemoApplication.class, args);
    }

}
