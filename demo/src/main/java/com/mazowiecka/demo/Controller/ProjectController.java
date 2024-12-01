package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Entity.Project;
import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Service.ProjectService;
import com.mazowiecka.demo.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projekty")
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping
    public String getAllProjects(Model model) {
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "projects/projectList";
    }

    @GetMapping("/utworz-projekt")
    public String showCreateProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "projects/createProject";
    }

//    @PostMapping("/utworz-projekt")
//    public String createProject(Project project) {
//        projectService.saveProject(project);
//        return "redirect:/projekty";
//    }

    @PostMapping("/utworz-projekt")
    public String createProject(Project project, HttpSession session) {
        // Pobierz zalogowanego użytkownika z sesji
        String loggedInUsername = (String) session.getAttribute("loggedUser");

        if (loggedInUsername == null) {
            throw new IllegalStateException("Użytkownik nie jest zalogowany");
        }

        // Pobierz użytkownika z bazy danych na podstawie nazwy użytkownika
        User loggedInUser = userService.findByUsername(loggedInUsername)
                .orElseThrow(() -> new IllegalStateException("Użytkownik nie istnieje"));

        // Przypisz użytkownika do projektu
        projectService.createProjectForUser(loggedInUser.getId(), project);

        return "redirect:/projekty";
    }



    @GetMapping("/edytowanie/{project_id}")
    public String showEditProjectForm(@PathVariable Long project_id, Model model) {
        Optional<Project> project = projectService.getProjectById(project_id);
        if (project.isPresent()) {
            model.addAttribute("project", project.get());
            return "projects/editProject";
        } else {
            model.addAttribute("error", "Nie znaleziono projektu o podanym ID.");
            return "error";
        }
    }

    @PostMapping("/edytowanie/{project_id}")
    public String editProject(@PathVariable Long project_id, @ModelAttribute Project project) {
        System.out.println("ID z URL: " + project_id);
        System.out.println("Projekt z formularza: " + project);
        project.setProject_id(project_id); // Przypisanie ID z URL
        projectService.saveProject(project);
        return "redirect:/projekty";
    }

    @PostMapping("/usun/{project_id}")
    public String deleteProject(@PathVariable Long project_id) {
        projectService.deleteProject(project_id);
        return "redirect:/projekty";
    }

    @PostMapping("/zakoncz/{project_id}")
    public String completeProject(@PathVariable Long project_id, RedirectAttributes redirectAttributes) {
        Optional<Project> project = projectService.getProjectById(project_id);
        if (project.isPresent()) {
            Project completedProject = project.get();
            completedProject.setCompleted(true);
            projectService.saveProject(completedProject);
            redirectAttributes.addFlashAttribute("message", "Super! Udało Ci się ukończyć projekt.");
        }
        return "redirect:/projekty";
    }
}
