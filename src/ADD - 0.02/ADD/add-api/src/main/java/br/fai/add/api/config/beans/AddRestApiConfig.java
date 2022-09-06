package br.fai.add.api.config.beans;

import br.fai.add.db.dao.UserDao;
import br.fai.add.db.dao.impl.UserDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddRestApiConfig {

    @Bean
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

}
