package br.fai.add.api.config.beans;

import br.fai.add.db.dao.*;
import br.fai.add.db.dao.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddRestApiConfig {

    @Bean
    public AnswerDao getAnswerDao() {
        return new AnswerDaoImpl();
    }

    @Bean
    public CollaboratorDao getCollaboratorDao() {
        return new CollaboratorDaoImpl();
    }

    @Bean
    public FormDao getFormDao() {
        return new FormDaoImpl();
    }

    @Bean
    public OptionDao getOptionDao() {
        return new OptionDaoImpl();
    }

    @Bean
    public OrganizationDao getOrganizationDao() {
        return new OrganizationDaoImpl();
    }

    @Bean
    public QuestionDao getQuestionDao() {
        return new QuestionDaoImpl();
    }

}
