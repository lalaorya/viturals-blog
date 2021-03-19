package com.hhj.blogbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;

@SpringBootApplication
public class BlogBackendApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BlogBackendApplication.class, args);
        DataSource bean = run.getBean(DataSource.class);
        System.out.println(bean.getClass());
    }

}
