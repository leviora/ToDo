package com.mazowiecka.demo.Service;

import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean isLoginCorrect(String login) {
        return true;
    }
    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public boolean isPasswordStrong(String password) {
        boolean isLongEnough = password.length() >= 8;
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*].*");

        return isLongEnough && hasDigit && hasSpecialChar;
    }

}