package com.mazowiecka.demo.Service;

import com.mazowiecka.demo.Entity.Project;
import com.mazowiecka.demo.Entity.ProjectUser;
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

    private final ProjectUserService projectUserService;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository, ProjectUserService projectUserService) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.projectUserService = projectUserService;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long project_id) {
        return projectRepository.findById(project_id);
    }

    public Project updateProject(Project updatedProject, Long projectId) {
       return projectRepository.save(updatedProject);
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long projectId) {

        if (!projectRepository.existsById(projectId)) {
            throw new IllegalArgumentException("Projekt o podanym ID nie istnieje.");
        }
        projectRepository.deleteById(projectId);
    }





    public List<Project> getCompletedProjects() {
        return projectRepository.findByCompleted(true);
    }

//    public Project createProjectForUser(Long userId, Project project) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("Nie znaleziono użytkownika o ID: " + userId));
//
//        project.setUser(user); // Przypisanie użytkownika do projektu
//        return projectRepository.save(project);
//    }

//    public Project createProjectForUser(Long userId, Project project) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("Nie znaleziono użytkownika o ID: " + userId));
//
//        project = projectRepository.save(project);
//
//        ProjectUser projectUser = new ProjectUser();
//        projectUser.setProject(project);
//        projectUser.setUser(user);
//        projectUserService.saveProjectUser(projectUser);
//
//        return project;
//    }

}






