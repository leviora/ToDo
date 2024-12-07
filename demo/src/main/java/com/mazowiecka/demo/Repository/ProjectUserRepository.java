package com.mazowiecka.demo.Repository;

import com.mazowiecka.demo.Entity.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {

}
