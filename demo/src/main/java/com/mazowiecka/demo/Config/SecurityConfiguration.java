package com.mazowiecka.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Wyłączenie CSRF, jeśli nie używasz formularzy chronionych sesjami
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Pozwól na wszystkie zapytania bez logowania
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)) // Wyłączenie zarządzania sesjami przez Spring Security
                .securityContext(context -> context.disable()) // Wyłączenie zarządzania SecurityContext
                .anonymous(anonymous -> anonymous.disable()) // Wyłączenie anonimizacji użytkowników
                .formLogin(form -> form.disable()) // Wyłączenie domyślnej logiki logowania Spring
                .logout(logout -> logout.disable()); // Wyłączenie domyślnego wylogowywania

//                .formLogin(form -> form
//                        .loginPage("/logowanie")  // Własna strona logowania
//                        .failureUrl("/logowanie?error")
//                        .defaultSuccessUrl("/", true)  // Strona po zalogowaniu
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/logowanie?logout")
//                        .permitAll()
//
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // lub .always
//                );



        return http.build();
    }
}
