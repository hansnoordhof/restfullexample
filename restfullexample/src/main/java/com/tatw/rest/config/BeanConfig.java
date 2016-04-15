package com.tatw.rest.config;

import com.tatw.rest.service.CountryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hans on 15-04-16.
 */
@Configuration
public class BeanConfig {

    @Bean
    public CountryService countryService() {
        CountryService countryService = new CountryService();
        return countryService;
    }

}
