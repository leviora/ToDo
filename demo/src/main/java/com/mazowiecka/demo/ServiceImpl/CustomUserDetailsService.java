package com.mazowiecka.demo.ServiceImpl;

import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Działa
//        return userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        user.getRoles().forEach(role -> {
            System.out.println("Role użytkownika: " + role.getAuthority());
        });

        // Zwracamy użytkownika z rolami
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getAuthority())) // Konwertujemy na SimpleGrantedAuthority
                        .collect(Collectors.toList()) // Kolekcjonujemy do List<SimpleGrantedAuthority>
        );

    }
}
