package com.dem.lincut.api.config;

import com.dem.lincut.api.resources.LinkModelAssembler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {
    @Bean
    public LinkModelAssembler getLinkModelAssembler() {
        return new LinkModelAssembler();
    }
}
