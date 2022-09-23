package br.fai.add.api.config.beans;

import br.fai.add.db.dao.CollaboratorDao;
import br.fai.add.db.dao.impl.CollaboratorDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddRestApiConfig {

    @Bean
    public CollaboratorDao getUserDao() {
        return new CollaboratorDaoImpl();
    }

}
