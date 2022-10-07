package br.fai.add.client.config.security;

import br.fai.add.client.config.security.providers.AddAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AddAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/scss/**").permitAll()
                .antMatchers("/vendor/**").permitAll()
                .antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/**").hasRole("REVIEWER") //só reviewer pode deletar
//                .antMatchers(HttpMethod.PUT, "/**").hasRole("REVIEWER") // só reviewer pode atualizar
//                .antMatchers("/account/register-employee").hasRole("REVIEWER") //
//                .antMatchers("/form/create-form").hasRole("REVIEWER") //
//                .antMatchers("/home/reviewer-home").hasRole("REVIEWER") //
//                .antMatchers("/home/reviewer-layout").hasRole("REVIEWER") //
//                .antMatchers("/home/employee-home").hasRole("EMPLOYEE") //
//                .antMatchers("/home/employee-layout").hasRole("EMPLOYEE") //
                .anyRequest().authenticated() // só permite que usuário autenticado possa acessar qualquer pagina, tirando as paginas do permitall acima
                .and()
                .formLogin()
                .loginPage("/account/sign-in")
                .loginProcessingUrl("/account/login").permitAll() //redireciona pra essa pagina qnd não é autenticado?
                .defaultSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/common/access-denied");
    }

}