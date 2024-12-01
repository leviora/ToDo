package com.mazowiecka.demo.Service;

import com.mazowiecka.demo.Entity.Project;
import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Repository.ProjectRepository;
import com.mazowiecka.demo.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long project_id) {
        return projectRepository.findById(project_id);
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long project_id) {
        projectRepository.deleteById(project_id);
    }
    public List<Project> getCompletedProjects() {
        return projectRepository.findByCompleted(true);
    }

    public Project createProjectForUser(Long userId, Project project) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono użytkownika o ID: " + userId));

        project.setUser(user); // Przypisanie użytkownika do projektu
        return projectRepository.save(project);
    }






}
