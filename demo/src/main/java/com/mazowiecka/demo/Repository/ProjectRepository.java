package com.mazowiecka.demo.Repository;

import com.mazowiecka.demo.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findAllById(Long projectId);
}
