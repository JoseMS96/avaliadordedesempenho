package br.fai.add.client.config.security;

import br.fai.add.client.config.security.providers.AddAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


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
                .antMatchers("/account/sign-up").permitAll()
                .antMatchers("/account/sign-up-organization").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated() // só permite que usuário autenticado possa acessar qualquer pagina, tirando as paginas do permitall acima
                .and()
                .formLogin()
                .loginPage("/account/sign-in")
                .loginProcessingUrl("/account/login").permitAll() //redireciona pra essa pagina qnd não é autenticado?
                .defaultSuccessUrl("/default-home")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/common/access-denied");
    }

}