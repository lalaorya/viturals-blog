package com.hhj.blogbackend;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhj.blogbackend.dto.ArticleDetail;
import com.hhj.blogbackend.service.ArticleService;
import com.hhj.blogbackend.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@SpringBootApplication(scanBasePackages = "com.hhj.blogbackend")
@EnableScheduling
public class BlogBackendApplication {

    public static void main(String[] args) {
       SpringApplication.run(BlogBackendApplication.class, args);



    }

}
