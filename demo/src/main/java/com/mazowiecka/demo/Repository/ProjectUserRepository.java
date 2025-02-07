package com.mazowiecka.demo.Repository;

import com.mazowiecka.demo.Entity.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {

}
