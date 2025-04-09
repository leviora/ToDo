package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Entity.Project;
import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Repository.ProjectRepository;
import com.mazowiecka.demo.Repository.TaskRepository;
import com.mazowiecka.demo.Service.ProjectService;
import com.mazowiecka.demo.Service.TaskService;
import com.mazowiecka.demo.Service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@PreAuthorize("hasRole('USER')")
@Controller
@RequestMapping("/projekty")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;
    private final TaskService taskService;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public ProjectController(ProjectService projectService,
                             UserService userService,
                             TaskService taskService,
                             ProjectRepository projectRepository,
                             TaskRepository taskRepository) {
        this.projectService = projectService;
        this.userService = userService;
        this.taskService = taskService;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public String getAllProjects(Model model, @AuthenticationPrincipal User loggedUser) {
        List<Project> projects = projectRepository.findByCompleted(false);
        model.addAttribute("projects", projects);
        return "pages/projects/projectList";
    }

    @GetMapping("/utworz-projekt")
    public String showCreateProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "pages/projects/createProject";
    }

    @PostMapping("/utworz-projekt")
    public String createProject(@ModelAttribute Project project, Model model, @AuthenticationPrincipal User loggedUser) {
        try {
            project.setUser(loggedUser);
            projectService.saveProject(project);
            return "redirect:/projekty";
        } catch (Exception e) {
            model.addAttribute("error", "Wystąpił błąd podczas tworzenia projektu.");
            return "pages/projects/createProject";
        }
    }

    @GetMapping("/edytowanie/{project_id}")
    public String showEditProjectForm(@PathVariable Long project_id, Model model) {
        Optional<Project> project = projectService.getProjectById(project_id);
        if (project.isPresent()) {
            model.addAttribute("project", project.get());
            return "pages/projects/editProject";
        } else {
            model.addAttribute("error", "Nie znaleziono projektu o podanym ID.");
            return "error";
        }
    }

    @PostMapping("/edytowanie/{project_id}")
    public String editProject(@PathVariable Long project_id, @ModelAttribute Project updatedProject, Model model) {
        Optional<Project> optionalProject = projectService.getProjectById(project_id);
        if (optionalProject.isEmpty()) {
            model.addAttribute("error", "Nie znaleziono projektu o podanym ID.");
            return "error";
        }

        try {
            Project existingProject = optionalProject.get();
            existingProject.setProject_name(updatedProject.getProject_name());
            existingProject.setProject_description(updatedProject.getProject_description());
            existingProject.setCompleted(updatedProject.isCompleted());

            projectService.saveProject(existingProject);
            return "redirect:/projekty";
        } catch (Exception e) {
            model.addAttribute("error", "Wystąpił błąd podczas edycji projektu.");
            return "pages/projects/editProject";
        }
    }

    @PostMapping("/zakoncz/{project_id}")
    public String completeProject(@PathVariable Long project_id, Model model, RedirectAttributes redirectAttributes, @AuthenticationPrincipal User loggedUser) {
        Optional<Project> optionalProject = projectService.getProjectById(project_id);
        if (optionalProject.isEmpty()) {
            model.addAttribute("error", "Nie znaleziono projektu o podanym ID.");
            return "error";
        }

        Project project = optionalProject.get();

//        if (!project.getUser().getId().equals(loggedUser.getId())) {
//            model.addAttribute("error", "Nie masz uprawnień do tej operacji.");
//            return "error";
//        }

        project.setCompleted(true);
        projectService.saveProject(project);
        redirectAttributes.addFlashAttribute("message", "Super! Udało Ci się ukończyć projekt.");
        return "redirect:/projekty";
    }

    @PostMapping("/usun/{project_id}")
    public String deleteProject(@PathVariable Long project_id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Project> projectOptional = projectService.getProjectById(project_id);
        if (projectOptional.isEmpty()) {
            model.addAttribute("error", "Projekt o podanym ID nie istnieje.");
            return "error";
        }

        try {
            projectService.deleteProject(project_id);
            redirectAttributes.addFlashAttribute("success", "Projekt został pomyślnie usunięty.");
            return "redirect:/projekty";
        } catch (Exception e) {
            model.addAttribute("error", "Wystąpił błąd podczas usuwania projektu.");
            return "error";
        }
    }

    @GetMapping("/dodaj-zadanie")
    public String showAddTaskForm(Model model, @AuthenticationPrincipal User loggedUser) {
        model.addAttribute("task", new Task());
        model.addAttribute("projects", projectService.getProjectsByUser(loggedUser));
        return "pages/projects/addTask";
    }

    @PostMapping("/dodaj-zadanie")
    public String addTaskToProject(@RequestParam("projectId") Long projectId, @RequestParam("description") String description, @AuthenticationPrincipal User loggedUser) {
        projectService.addTaskToProject(projectId, description, loggedUser);
        return "redirect:/projekty";
    }
}
