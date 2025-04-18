package com.mazowiecka.demo.ServiceImpl;

import com.mazowiecka.demo.Entity.Project;
import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Repository.ProjectRepository;
import com.mazowiecka.demo.Repository.TaskRepository;
import com.mazowiecka.demo.Repository.UserRepository;
import com.mazowiecka.demo.Service.ProjectService;
import com.mazowiecka.demo.Service.ProjectUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectUserService projectUserService;
    private final TaskRepository taskRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              UserRepository userRepository,
                              ProjectUserService projectUserService,
                              TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.projectUserService = projectUserService;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> getProjectById(Long project_id) {
        return projectRepository.findById(project_id);
    }

    @Override
    public Project updateProject(Project updatedProject,
                                 Long projectId) {
        return projectRepository.save(updatedProject);
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public List<Project> getCompletedProjects() {
        return projectRepository.findByCompleted(true);
    }

    @Override
    public List<Project> getProjectsByUser(User user) {
        return projectRepository.findByUser(user);
    }

    @Override
    public void addTaskToProject(Long projectId,
                                 String description,
                                 User user) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();

            Task task = new Task();
            task.setDescription(description);
            task.setProject(project);
            task.setUser(user);

            taskRepository.save(task);
        }
    }

}
