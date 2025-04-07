//package com.mazowiecka.demo.Service;
//
//import com.mazowiecka.demo.Entity.*;
//import com.mazowiecka.demo.Repository.ProjectRepository;
//import com.mazowiecka.demo.Repository.TaskRepository;
//import com.mazowiecka.demo.Repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ProjectService {
//    private final ProjectRepository projectRepository;
//    private final UserRepository userRepository;
//    private final ProjectUserService projectUserService;
//    private final TaskRepository taskRepository;
//    public ProjectService(ProjectRepository projectRepository,
//                          UserRepository userRepository,
//                          ProjectUserService projectUserService,
//                          TaskRepository taskRepository) {
//        this.projectRepository = projectRepository;
//        this.userRepository = userRepository;
//        this.projectUserService = projectUserService;
//        this.taskRepository = taskRepository;
//
//    }
//
//    public List<Project> getAllProjects() {
//        return projectRepository.findAll();
//    }
//
//    public Optional<Project> getProjectById(Long project_id) {
//        return projectRepository.findById(project_id);
//    }
//    public Project updateProject(Project updatedProject, Long projectId) {
//        return projectRepository.save(updatedProject);
//    }
//    public Project saveProject(Project project) {
//        return projectRepository.save(project);
//    }
//    public void deleteProject(Long projectId) {
//
//        if (!projectRepository.existsById(projectId)) {
//            throw new IllegalArgumentException("Projekt o podanym ID nie istnieje.");
//        }
//        projectRepository.deleteById(projectId);
//    }
//    public List<Project> getCompletedProjects() {
//        return projectRepository.findByCompleted(true);
//    }
//    public List<Project> getProjectsByUser(User user) {
//        return projectRepository.findByUser(user);
//    }
//    public void addTaskToProject(Long projectId, String description, User user) {
//        Optional<Project> optionalProject = projectRepository.findById(projectId);
//
//        if (optionalProject.isPresent()) {
//            Project project = optionalProject.get();
//
//            Task task = new Task();
//            task.setDescription(description);
//            task.setProject(project);
//
//            if (project.getUser().equals(user)) {
//                taskRepository.save(task);
//            } else {
//                throw new IllegalArgumentException("Nie możesz dodać zadania do projektu, który nie należy do ciebie!");
//            }
//        } else {
//            throw new IllegalArgumentException("Nie znaleziono projektu o podanym ID");
//        }
//    }
//
//}
//
//
//
//
//
//
