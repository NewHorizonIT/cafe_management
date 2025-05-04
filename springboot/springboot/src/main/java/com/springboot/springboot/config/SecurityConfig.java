package com.springboot.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final String[] PUBLIC_ENDPOINTS = {
            "/api/v1/**"
    };

    private final CustomJwtDecoder customJwtDecoder;

    @Autowired
    public SecurityConfig(CustomJwtDecoder customJwtDecoder) {
        this.customJwtDecoder = customJwtDecoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                        // Public endpoints
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                // .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS).permitAll()
                // .requestMatchers(HttpMethod.GET, "/db_cafe_management/users").authenticated()
                // .requestMatchers(HttpMethod.PUT, "/db_cafe_management/users").authenticated()
                // Admin-only endpoints with permissions
                // .requestMatchers("/db_cafe_management/users").hasAuthority("VIEW_USERS")
                // .requestMatchers("/db_cafe_management/users/{id}").hasAuthority("VIEW_USERS")
                // .requestMatchers(HttpMethod.POST,
                // "/db_cafe_management/users")
                // .hasAuthority("EDIT_USERS")
                // .requestMatchers(HttpMethod.PUT,
                // "/db_cafe_management/users/{id}")
                // .hasAuthority("EDIT_USERS")
                // .requestMatchers(HttpMethod.DELETE, "/db_cafe_management/users/{id}")
                // .hasAuthority("DELETE_USERS")
                // .requestMatchers("/db_cafe_management/users/{id}/roles/**").hasAuthority("MANAGE_ROLES")
                // // All other requests require authentication
                // .anyRequest().authenticated())
                // .oauth2ResourceServer(oauth2 -> oauth2
                // .jwt(jwtConfigurer -> jwtConfigurer
                // .decoder(customJwtDecoder)
                // .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                // .authenticationEntryPoint(new JwtAuthenticationEntryPoint()));
                );
        return httpSecurity.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
