package com.psipher.application.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;

import static com.psipher.application.PsipherApplication.SWAGGER_ENDPOINT;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
        if (StringUtils.isNotBlank(SWAGGER_ENDPOINT)) {
            docket.host(SWAGGER_ENDPOINT);
        }
        HashSet<String> protocolSet = new HashSet<>();
        protocolSet.add("https");
        protocolSet.add("http");
        docket.protocols(protocolSet);
        return docket;
    }
}