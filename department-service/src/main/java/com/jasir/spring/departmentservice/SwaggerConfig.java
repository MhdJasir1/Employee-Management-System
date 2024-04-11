//package com.jasir.spring.departmentservice;
//
//import jdk.javadoc.doclet.Doclet;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//
//@Configuration
//@EnableWebMvc
//public class SwaggerConfig implements WebMvcConfigurer {
//
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.jasir.spring.departmentservice"))
//                .paths(PathSelectors.regex("/api/department.*"))
//                .build()
//                .apiInfo(apiInfoMetaInfo());
//    }
//
//    private ApiInfo apiInfoMetaInfo() {
//
//        return new ApiInfoBuilder().title("EMS")
//                .description("Api Document ")
//                .contact(new Contact("Jasir","localhost://8081/","mhdjasir4565@gmail.com"))
//                .license("Apache 2.0")
//                .licenseUrl("http://www.apache.licenses/LICENSE-2.0.html")
//                .version("1.0.0")
//                .build();
//    }
//}
//
//
