package com.mazowiecka.demo.Service;

import com.mazowiecka.demo.Entity.ProjectUser;
import com.mazowiecka.demo.Repository.ProjectUserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectUserService {
    private final ProjectUserRepository projectUserRepository;

    public ProjectUserService(ProjectUserRepository projectUserRepository) {
        this.projectUserRepository = projectUserRepository;
    }
    public ProjectUser saveProjectUser(ProjectUser projectUser) {
        return projectUserRepository.save(projectUser);
    }
}
