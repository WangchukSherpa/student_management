package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
CommandLineRunner commandLineRunner(StudentRepository repository ){

    return args -> {
       Student jig=new Student(
                "Jigmee Wangchuk Sherpa"
                ,"sandjaw@gmail.com"
                , LocalDate.of(2000,07,28)

        );
        Student sunu=new Student(
                "Shusmita Sherpa"
                ,"sandjaw@gmail.com"
                , LocalDate.of(2001,05,9)

        );
        repository.saveAll(List.of(jig,sunu));

    };
}
}
