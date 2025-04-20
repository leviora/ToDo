package com.mazowiecka.demo.ServiceImpl;

import com.mazowiecka.demo.Entity.ProjectUser;
import com.mazowiecka.demo.Repository.ProjectUserRepository;
import com.mazowiecka.demo.Service.ProjectUserService;
import org.springframework.stereotype.Service;

@Service
public class ProjectUserServiceImpl implements ProjectUserService {
    private final ProjectUserRepository projectUserRepository;

    public ProjectUserServiceImpl(ProjectUserRepository projectUserRepository) {
        this.projectUserRepository = projectUserRepository;
    }

    @Override
    public ProjectUser saveProjectUser(ProjectUser projectUser) {
        return projectUserRepository.save(projectUser);
    }
}
