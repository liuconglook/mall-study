package com.belean.mall.tiny.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by belean on 2021/7/14.
 **/
@Configuration
@EnableOpenApi
@EnableKnife4j
public class Swagger3Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                // 为当前包下controller生成API文档
                //.apis(RequestHandlerSelectors.basePackage("com.belean.mall.tiny.controller"))
                // 为有@Api注解的Controller生成API文档
                .apis(RequestHandlerSelectors.withClassAnnotation(Tag.class).or(RequestHandlerSelectors.withClassAnnotation(Schema.class)))
                // 为有@ApiOperation注解的方法生成API文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(Operation.class))
                .paths(PathSelectors.any())
                .build()
                //添加登录认证
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

   private List<SecurityScheme> securitySchemes() {
        //设置请求头信息
        List<SecurityScheme> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
        result.add(apiKey);
        return result;
    }

    private List<SecurityContext> securityContexts() {
        //设置需要登录认证的路径
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/brand/.*"));
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex){
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization", authorizationScopes));
        return result;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Mall Api Document") // 项目标题
                .description("mall-study") // 项目描述
                // 联系人 网站 邮箱
                .contact(new Contact("belean", "https://github.com/liuconglook", "liuconglook@gmail.com"))
                .license("Apache LICENSE 2.0") // 开源协议
                .licenseUrl("http://www.apache.org/") // 开源协议地址
                .termsOfServiceUrl("") // 服务条款地址
                .version("1.0") // 版本
                .build();
    }
}
