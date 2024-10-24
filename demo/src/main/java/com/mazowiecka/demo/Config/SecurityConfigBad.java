//package com.mazowiecka.demo.Config;
//
//
//
////import com.mazowiecka.demo.Service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    private final CustomUserDetailsService customUserDetailsService;
//    @Autowired
//    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
//        this.customUserDetailsService = customUserDetailsService;
//    }
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authz -> authz
//                        .anyRequest().permitAll()  // Zezwolenie na dostęp do wszystkich URL
//                )
//
//                .formLogin(form -> form
//                        .loginPage("/logowanie")  // Strona logowania
//                        .defaultSuccessUrl("/", true)  // Po pomyślnym zalogowaniu przekierowanie na stronę główną
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/logowanie?logout")  // Po wylogowaniu przekierowanie na stronę logowania
//                        .permitAll()
//                )
//
//                .csrf(csrf -> csrf.disable()); // Możesz również wyłączyć CSRF, jeśli jest to potrzebne
//
//        return http.build();
//    }
//
//
//
//
//
//
//
//}
