package com.teamthree.studentevaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication
@EntityScan(basePackages = {"com.teamthree.studentevaluation"})
@ComponentScan(basePackages = {"com.teamthree.studentevaluation.user, com.teamthree.studentevaluation.login, com.teamthree.studentevaluation.student"})
public class StudentEvaluationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentEvaluationServerApplication.class, args);
    }

}
