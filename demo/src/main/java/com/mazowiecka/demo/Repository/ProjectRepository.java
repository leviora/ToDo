package com.mazowiecka.demo.Repository;

import com.mazowiecka.demo.Entity.Project;
import com.mazowiecka.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findById(Long project_id);

    List<Project> findByCompleted(boolean b);

    List<Project> findByUserId(Long userId);

    List<Project> findByUser(User user);

}
