package com.mazowiecka.demo.Service;

import com.mazowiecka.demo.Entity.Project;
import com.mazowiecka.demo.Repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    public Optional<Project> getProjectById(Long projectId) {
        return projectRepository.findAllById(projectId);
    }
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }
    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }


}
