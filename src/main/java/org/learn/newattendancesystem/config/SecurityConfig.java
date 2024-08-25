package org.learn.newattendancesystem.config;

import org.learn.newattendancesystem.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    @Autowired
    private UserDetailService userDetailsService;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/attendance/**").hasAnyRole("teacher", "admin", "student")
                                .requestMatchers("/user/**").hasRole("admin")
                                .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(formLogin ->
                        formLogin
                                .defaultSuccessUrl("/welcome", true)
                                .permitAll() // Allow everyone to access the login page
                )
                .exceptionHandling(accessDenied -> accessDenied.accessDeniedPage("/accessDenied"))
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable); // Enable HTTP Basic authentication using defaults

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
