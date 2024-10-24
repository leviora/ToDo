package com.mazowiecka.demo.Service;

import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            System.out.println("Znaleziono użytkownika: " + user.get().getUsername());
        } else {
            System.out.println("Nie znaleziono użytkownika o nazwie: " + username);
        }
        return user;
    }
    public void saveUser(User user) {
        if (isUsernameTaken(user.getUsername())) {
            throw new IllegalArgumentException("Nazwa użytkownika jest już zajęta.");
        }

        if (!isPasswordStrong(user.getPassword())) {
            throw new IllegalArgumentException("Hasło nie spełnia wymagań siły.");
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean isPasswordStrong(String password) {
        boolean isLongEnough = password.length() >= 8;
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*,.].*");
        return isLongEnough && hasDigit && hasSpecialChar;
    }

    public boolean checkLoginCredentials(String username, String rawPassword) {
        return userRepository.findByUsername(username)
                .map(user -> {
                    System.out.println("Sprawdzanie hasła: " + rawPassword + " vs " + user.getPassword());
                    return passwordEncoder.matches(rawPassword, user.getPassword());
                })
                .orElseGet(() -> {
                    System.out.println("Nie znaleziono użytkownika: " + username);
                    return false;
                });
    }

    public boolean login(String username, String password) {
        return checkLoginCredentials(username, password);
    }

    public boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("loggedUser") != null;
    }






}
