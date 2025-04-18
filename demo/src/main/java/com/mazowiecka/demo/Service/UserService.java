package com.mazowiecka.demo.Service;

import com.mazowiecka.demo.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User createUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    User getCurrentUser();

    Optional<User> findByUsername(String username);

    public void updateUsername(String newUsername);

    void updateEmail(String username, String newEmail);

    void changePassword(String username, String currentPassword, String newPassword, String confirmPassword);


}
