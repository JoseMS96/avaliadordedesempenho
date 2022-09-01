package br.fai.add.api.config.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LdsRestApiConfig {

    @Bean
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

}
