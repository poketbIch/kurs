package com.example.AttributesREU.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Sha512PasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                        .
                antMatchers("/Login","/Registration","/header","/static/**","/resources/**"
                    ).permitAll()
                .antMatchers("/resources/templates/header.html","/images/**","/style.css").permitAll()
                .antMatchers(HttpMethod.GET,"/PoiskAnon","/PoiskAnon/AnonClientSearch/{categoryName}","/PoiskAnon/AnonClientSearchName/{name}").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/Login").permitAll()
                .usernameParameter("username")

                .and()
                .formLogin(formLogin -> formLogin
                        .successHandler(new AuthentificationSuccess())
                )
                .sessionManagement().maximumSessions(4).and()
                .and()
                .logout().invalidateHttpSession(true).permitAll()
                .and()
                .csrf().disable().cors().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().
                dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT username, password, active FROM user WHERE username=?")
                .authoritiesByUsernameQuery("SELECT u.username, ur.role FROM user u INNER JOIN role ur ON u.id=ur.userid WHERE username=?")

        ;

    }



}