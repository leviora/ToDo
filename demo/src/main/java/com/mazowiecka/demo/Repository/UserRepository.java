package com.mazowiecka.demo.Repository;

import com.mazowiecka.demo.Entity.Role;
import com.mazowiecka.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findByRole(Role role);
    boolean existsByRole(Role role);

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = 'USER'")
    long countRegularUsers();

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = 'ADMIN'")
    long countAdmins();
}
