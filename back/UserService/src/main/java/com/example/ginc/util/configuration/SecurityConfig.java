//package com.example.ginc.util.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.HttpStatusEntryPoint;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@EnableWebSecurity
//@Configuration
//public class SecurityConfig {
//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring()
//                .requestMatchers("/h2-console/**")
//                .requestMatchers("/static/**");
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//        return httpSecurity
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(requests -> {
//                    requests
//                            .requestMatchers("/h2-console/**").permitAll()
//                            .requestMatchers("/login", "/api/account/signup", "/api/account/login").permitAll()
//                            .anyRequest()
//                            .authenticated();
//
//                })
//                .sessionManagement(
//                        sessionManagement ->
//                                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//
//                .exceptionHandling(exceptionHandling -> {
//                    exceptionHandling
//                            .defaultAuthenticationEntryPointFor(
//                                    new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
//                                    new AntPathRequestMatcher("/api/**")
//                            );
//                })
//                .build();
//    }
//
//}
