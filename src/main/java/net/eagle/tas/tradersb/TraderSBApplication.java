package net.eagle.tas.tradersb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TraderSBApplication {

    public static void main(String[] args) {
        SpringApplication.run(TraderSBApplication.class, args);
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("net.eagle"))
                .paths(PathSelectors.any()).build() ;

    }

    // http://localhost:10203/swagger-ui.html#
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("TraderSB").description("TraderSB REST API").version("1.0.0")
                .license("Copyright (c) 2018 Robert Eaglestone.  All rights reserved.")
                .build();
    }
}
