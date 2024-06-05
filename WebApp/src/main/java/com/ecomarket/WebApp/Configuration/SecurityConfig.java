package com.ecomarket.WebApp.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурационный класс содержит настройки для авторизации и аутентификации пользователей.
 */

@EnableWebSecurity
public class SecurityConfig {

    /**
     * Создает цепочку фильтров безопасности с настройками HTTP-запросов.
     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .authorizeRequests().antMatchers("/", "/about", "/login", "/registration").permitAll()
                .anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/execute_login")
                .defaultSuccessUrl("/")
                .and()
                .logout().logoutSuccessUrl("/")
                .permitAll()
                .and().build();
    }

}
