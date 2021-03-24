package com.hhj.blogbackend.config;

import io.swagger.annotations.ApiOperation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.env.Profiles;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {




    // 配置Swagger的Docket的Bean实例：
    // 每一个Docket的Bean实例对应于一个分组，这样可以方便协同开发
    @Bean
    public Docket createRestApiGroup1() {

        // 添加 head 参数配置 start
        ParameterBuilder token = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        token.name("Authorization").description("token 信息").modelRef(new ModelRef("String"))
                .parameterType("header").required(false).build();
        pars.add(token.build());


        // 设置要显示的Swagger环境
//        Profiles profiles = Profiles.of("dev", "test");
        // 获取项目的环境
//        boolean isDevAndTest = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 是否启动Swagger，若为false，则Swagger不能在浏览器中访问
                .enable(true) // 可以控制Swagger在开发及测试环境中使用，在生产环境不使用
                .select().apis(RequestHandlerSelectors.basePackage("com.hhj.blogbackend")) //扫描指定的包
                // RequestHandlerSelectors.any()：扫描全部
                // RequestHandlerSelectors.none()：不扫描
                // RequestHandlerSelectors.withMethodAnnotation(注解.class)：扫描方法上的注解
                // RequestHandlerSelectors.withClassAnnotation(注解.class)：扫描类上的注解
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) // 配置要扫描接口的方式
                // PathSelectors.any()：放行所有路径
                // PathSelectors.ant("/article")：只放行/article路径
                //             .paths(PathSelectors.none()) // 过滤映射路径 过滤所有等于不过滤
                .build()
                .groupName("virtualHj")
                // 注意一下 globalOperationParameters 这行配置
                .globalOperationParameters(pars);

        // 可以由使用者设置全局token（一般登录成功后都会设置一个token作为同行证）放置到HTTP请求头中，在跨域访问时作为通行证
//                .securitySchemes(Arrays.asList(securitySchemes()))
//                .securityContexts(Arrays.asList(securityContexts()));
    }

    // 配置网站相关信息
    private ApiInfo apiInfo() {
        // 作者信息
        Contact contact=new Contact("hhj","hh.com","111@qq.com");
        return new ApiInfoBuilder()
                .title("个人博客API文档")
                .description("描述如标题")
//                .termsOfServiceUrl("http://blog.progzc.com")
                .version("v1.0")
                .contact(contact)
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

}
