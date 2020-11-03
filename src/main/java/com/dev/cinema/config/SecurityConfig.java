package com.dev.cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .and()
                .authorizeRequests()
                .antMatchers(
                        HttpMethod.POST,
                        "/movies/**",
                        "/cinema-halls/**",
                        "/movie-sessions/**").hasRole("ADMIN")
                .antMatchers(
                        HttpMethod.GET,
                        "/movies/**",
                        "/cinema-halls/**",
                        "/movie-sessions/available/**").permitAll()
                .antMatchers(
                        "/orders/**",
                        "/shopping-carts/**").hasRole("USER")
                .antMatchers(
                        HttpMethod.POST,
                        "/registration/**").permitAll()
                .antMatchers(
                        HttpMethod.GET,
                        "/users/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .and()
                .httpBasic()
                    .and()
                .csrf().disable();
    }
}
