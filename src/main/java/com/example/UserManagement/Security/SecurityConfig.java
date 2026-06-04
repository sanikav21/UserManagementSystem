package com.example.UserManagement.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Bean
    SecurityFilterChain security(

            HttpSecurity http

            

    ) throws Exception {

        return http

                .csrf(csrf ->
                        csrf.disable())

                .headers(headers ->

                        headers.frameOptions(
                                frame ->
                                        frame.disable()
                        )
                )

                .authorizeHttpRequests(auth ->

                        auth

                                .requestMatchers(

                                        "/auth/**",

                                        "/h2-console/**"

                                )

                                .permitAll()

                                

                                .requestMatchers("/admin/**")
                                .permitAll()

                                .requestMatchers("/manager/**")
                                .permitAll()

                                .anyRequest()
                                .authenticated()
                )

                
                .build();
    }
}