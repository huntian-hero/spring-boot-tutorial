package com.example.tutorial.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.config.Configuration.AccessLevel;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // 配置以使用更安全的反射方式
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE)
                .setMethodAccessLevel(AccessLevel.PUBLIC) // 优先使用方法访问
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR)
                .setDestinationNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
        return modelMapper;
    }
}
