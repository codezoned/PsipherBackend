package com.psipher.application;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class PsipherApplication extends SpringBootServletInitializer {
    public static String SWAGGER_ENDPOINT;

    public static void main(String[] args) {
        for (String arg : args) {
            String[] attribute = arg.trim().split("=");
            if (attribute.length == 2 && attribute[0].equals("--swaggerurl") && StringUtils.isNotBlank(attribute[1])) {
                SWAGGER_ENDPOINT = attribute[1];
            }
        }
        SpringApplication.run(PsipherApplication.class, args);
    }
}
