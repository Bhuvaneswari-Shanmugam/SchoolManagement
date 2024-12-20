package com.school.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.school.management.dto.ResponseDTO;

@Configuration
public class AppConfig {

    @Bean
    ResponseDTO responseDTO() {
	        return new ResponseDTO();
	    }
	}


