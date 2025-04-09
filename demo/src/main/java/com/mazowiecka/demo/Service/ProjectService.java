package com.mazowiecka.demo.Service;

import com.mazowiecka.demo.Entity.*;
import java.util.List;
import java.util.Optional;

public interface ProjectService {

    public List<Project> getAllProjects();

    public Optional<Project> getProjectById(Long project_id);

    public Project updateProject(Project updatedProject, Long projectId);

    public Project saveProject(Project project);

    public void deleteProject(Long projectId);

    public List<Project> getCompletedProjects();

    public List<Project> getProjectsByUser(User user);

    public void addTaskToProject(Long projectId, String description, User user);
}






