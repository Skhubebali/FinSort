package com.skhubeb.FinSort.config;

import org.springframework.boot.web.embedded.undertow.HttpHandlerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests(request ->request
                .requestMatchers("/public").permitAll()
                .requestMatchers("/user/**","/expense/**").authenticated()
                .anyRequest().authenticated()).httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable).build();
    }

    @Bean
   public  PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}

