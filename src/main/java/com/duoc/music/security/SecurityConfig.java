package com.duoc.music.security;


import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain filters(HttpSecurity http,AuthenticationProvider proveedor,
            @Value("${music.cors.origin}") String origin) throws Exception {
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(List.of(origin));
        cors.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        cors.setAllowedHeaders(List.of("Authorization","Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",cors);
        http.csrf(AbstractHttpConfigurer::disable)
            .cors(c -> c.configurationSource(source))
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(proveedor)
            .authorizeHttpRequests(a -> a
                .requestMatchers(HttpMethod.GET,"/api/albums","/api/albums/**").permitAll()
                .requestMatchers("/api/albums","/api/albums/**").hasRole("ADMIN")
                .anyRequest().denyAll())
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    
}
