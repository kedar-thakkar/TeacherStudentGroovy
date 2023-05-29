//package com.ts.config
//
//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
//import org.springframework.context.annotation.Bean
//import org.springframework.stereotype.Component
//import springfox.documentation.service.ApiInfo
//import springfox.documentation.service.ApiKey
//import springfox.documentation.service.AuthorizationScope
//import springfox.documentation.service.SecurityReference
//import springfox.documentation.spi.service.contexts.SecurityContext
//import springfox.documentation.spring.web.plugins.Docket
//
//@Component
//class SwaggerConfig {
//    public static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);
//
//    @Bean
//    Docket swaggerApi() {
//        logger.info("swagger url :" + "http://localhost:9999/-ui.hswaggertml#/");
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .securityContexts(Arrays.asList(securityContext()))
//                .securitySchemes(Arrays.asList(apiKey()))
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfo(
//                "Teacher-Student's REST APIs",
//                "Some custom description of API.",
//                "1.0",
//                "Terms of service",
//                "License of API",
//                "API license URL",
//                "http://localhost:9999");
//    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("JWT", "Authorization", "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(defaultAuth()).build();
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
//    }
//}
//
