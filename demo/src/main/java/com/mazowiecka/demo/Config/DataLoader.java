package com.mazowiecka.demo.Config;

import com.mazowiecka.demo.Entity.Role;
import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Repository.RoleRepository;
import com.mazowiecka.demo.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository,
                      RoleRepository roleRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        createRoles();
        createAdminUser();
        createUser();
    }

    private void createRoles() {
        if (roleRepository.findByName(Role.RoleName.ROLE_ADMIN).isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName(Role.RoleName.ROLE_ADMIN);
            roleRepository.save(adminRole);
        }

        if (roleRepository.findByName(Role.RoleName.ROLE_USER).isEmpty()) {
            Role userRole = new Role();
            userRole.setName(Role.RoleName.ROLE_USER);
            roleRepository.save(userRole);
        }
    }

    private void createAdminUser() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            Optional<Role> adminRole = roleRepository.findByName(Role.RoleName.ROLE_ADMIN);

            if (adminRole.isPresent()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("password"));
                admin.setEmail("admin@op.pl");
                admin.setRoles(Set.of(adminRole.get()));

                userRepository.save(admin);
            }
        }
    }

    private void createUser() {
        if (userRepository.findByUsername("user").isEmpty()) {
            Optional<Role> userRole = roleRepository.findByName(Role.RoleName.ROLE_USER);

            if (userRole.isPresent()) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("password"));
                user.setEmail("user@op.pl");
                user.setRoles(Set.of(userRole.get()));

                userRepository.save(user);
            }
        }
    }
}

