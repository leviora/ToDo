package com.mazowiecka.demo.Config;

import com.mazowiecka.demo.Entity.Role;
import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadAdminData(UserRepository userRepository,
                                           BCryptPasswordEncoder passwordEncoder) {
        return args -> {

            if (userRepository.existsByRole(Role.ADMIN)) {
                System.out.println("Administrator już istnieje. Nie dodano nowego użytkownika.");
                return;
            }


            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .email("admin@example.com")
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(admin);
            System.out.println("Dodano użytkownika ADMIN.");
        };
    }
}
