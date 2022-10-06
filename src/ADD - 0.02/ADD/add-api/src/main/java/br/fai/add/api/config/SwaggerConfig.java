package br.fai.add.api.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {

        // para acessar a pagina do swagger, utilizar:
        // http://localhost:8081/swagger-ui.html

        return GroupedOpenApi.builder()
                .group("add-api-public")
                .pathsToMatch("/api/**")
                .build();
    }

}
