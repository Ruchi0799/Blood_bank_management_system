package com.bloodbanksystem.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.bloodbanksystem.springboot.repository")
public class JpaConfig {
    // Additional JPA-related configuration can go here
}

