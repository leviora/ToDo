package com.mazowiecka.demo.Service;

import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean isPasswordHashed(String password) {
        return password.startsWith("$2a$") || password.startsWith("$2b$");
    }

    public void saveUser(User user) {
        try {
            // Walidacja hasła
            if (!isPasswordStrong(user.getPassword())) {
                throw new IllegalArgumentException("Hasło nie spełnia wymagań siły.");
            }
            // Haszuj hasło przed zapisaniem
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Nie udało się zapisać użytkownika", e);
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean isLoginCorrectFormat(String login) {
        // Walidacja: minimum 3 znaki, tylko litery i cyfry
        return login.matches("[a-zA-Z0-9]{3,}");
    }

    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public boolean isPasswordStrong(String password) {
        // walidacja: hasło musi składać się z co najmniej 8 znaków, mieć co najmniej 1 cyfrę i znak specjalny
        boolean isLongEnough = password.length() >= 8;
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*,.].*");

        return isLongEnough && hasDigit && hasSpecialChar;
    }

    public boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email) != null;
    }




}