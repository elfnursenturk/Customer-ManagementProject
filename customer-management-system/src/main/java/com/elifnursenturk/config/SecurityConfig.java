package com.elifnursenturk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors() // CORS'u etkinleştir
            .configurationSource(corsConfigurationSource()) // CORS yapılandırmasını kaynakla sağla
            .and()
            .csrf().disable() // CSRF'yi devre dışı bırak (gereksizse)
            .authorizeRequests(auth -> auth
                .requestMatchers("/rest/api/user/**").permitAll()  // Bu endpointlere izin ver
                .anyRequest().authenticated()  // Diğer tüm isteklere doğrulama
            );

        return http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // React uygulamanızın adresi
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Yalnızca gerekli HTTP metotlarına izin ver
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization", "X-Requested-With")); // Gönderilecek başlıklar
        configuration.setAllowCredentials(true); // Kimlik doğrulama için izin verir

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Tüm URL'lere CORS yapılandırmasını uygula
        return source;
    }
}
