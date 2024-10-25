package com.tickets.api.tickets_service.config;

import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public JaxbAnnotationModule jaxbModule() {
        return new JaxbAnnotationModule();
    }

}
