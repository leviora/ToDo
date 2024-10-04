package com.mazowiecka.demo.Config;

import com.mazowiecka.demo.Service.CustomUserDetailsService;
import com.mazowiecka.demo.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(UserService userService, CustomUserDetailsService customUserDetailsService) {
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/tworzenieUzytkownika", "/logowanie", "/css/**", "/js/**", "/").permitAll()  // Publiczne strony
                        .anyRequest().authenticated()  // Reszta wymaga logowania
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Strona logowania
                        .defaultSuccessUrl("/", true)  // Gdzie przekierować po udanym logowaniu
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")  // Gdzie przekierować po wylogowaniu
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable());  // Wyłączenie CSRF (opcjonalnie)

        return http.build();
    }

    // Dodanie konfiguracji dla autoryzacji
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
