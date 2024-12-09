package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Entity.Category;
import com.mazowiecka.demo.Entity.Project;
import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Repository.ProjectRepository;
import com.mazowiecka.demo.Service.ProjectService;
import com.mazowiecka.demo.Service.TaskService;
import com.mazowiecka.demo.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projekty")
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectController(ProjectService projectService, UserService userService, TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.taskService = taskService;
    }

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @GetMapping
    public String getAllProjects(Model model) {
        List<Project> projects = projectRepository.findByCompleted(false);
        model.addAttribute("projects", projects);
        return "projects/projectList";
    }

    @GetMapping("/utworz-projekt")
    public String showCreateProjectForm(Model model) {
        model.addAttribute("project", new Project());

        return "projects/createProject";
    }

    @PostMapping("/utworz-projekt")
    public String createProject(@ModelAttribute Project project, HttpSession session, Model model) {
        Long loggedUserId = (Long) session.getAttribute("loggedUserId");

        if (loggedUserId == null) {
            model.addAttribute("error", "Musisz być zalogowany, aby utworzyć projekt.");
            return "redirect:/logowanie";
        }

        try {
            Optional<User> optionalUser = userService.getUserById(loggedUserId);

            if (optionalUser.isPresent()) {
                User loggedUser = optionalUser.get();
                project.setUser(loggedUser);

                projectService.saveProject(project);

                return "redirect:/projekty";
            } else {
                model.addAttribute("error", "Nie znaleziono zalogowanego użytkownika.");
                return "projects/createProject";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Wystąpił błąd podczas tworzenia projektu. Spróbuj ponownie.");
            return "projects/createProject";
        }
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
    public String editProject(@PathVariable Long project_id,
                              @ModelAttribute Project updatedProject,
                              HttpSession session,
                              Model model) {

        Long loggedUserId = (Long) session.getAttribute("loggedUserId");

        if (loggedUserId == null) {
            model.addAttribute("error", "Musisz być zalogowany, aby edytować projekt.");
            return "redirect:/logowanie";
        }

        Optional<Project> optionalProject = projectService.getProjectById(project_id);
        if (optionalProject.isEmpty()) {
            model.addAttribute("error", "Nie znaleziono projektu o podanym ID.");
            return "error";
        }

        Project existingProject = optionalProject.get();

        if (!existingProject.getUser().getId().equals(loggedUserId)) {
            model.addAttribute("error", "Nie masz uprawnień do edycji tego projektu.");
            return "error";
        }

        try {

            existingProject.setProject_name(updatedProject.getProject_name());
            existingProject.setProject_description(updatedProject.getProject_description());
            existingProject.setCompleted(updatedProject.isCompleted());


            projectService.saveProject(existingProject);

            return "redirect:/projekty";
        } catch (Exception e) {
            logger.error("Błąd podczas edytowania projektu", e);
            model.addAttribute("error", "Wystąpił błąd podczas edycji projektu.");
            return "projects/editProject";
        }
    }

    @PostMapping("/zakoncz/{project_id}")
    public String completeProject(@PathVariable Long project_id, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Long loggedUserId = (Long) session.getAttribute("loggedUserId");

        if (loggedUserId == null) {
            model.addAttribute("error", "Musisz być zalogowany, aby zakończyć projekt.");
            return "redirect:/logowanie";
        }

        Optional<Project> optionalProject = projectService.getProjectById(project_id);
        if (optionalProject.isEmpty()) {
            model.addAttribute("error", "Nie znaleziono projektu o podanym ID.");
            return "error";
        }

        Project project = optionalProject.get();

        if (!project.getUser().getId().equals(loggedUserId)) {
            model.addAttribute("error", "Nie masz uprawnień do tej operacji.");
            return "error";
        }

        project.setCompleted(true);
        projectService.saveProject(project);
        redirectAttributes.addFlashAttribute("message", "Super! Udało Ci się ukończyć projekt.");
        return "redirect:/projekty";
    }


    @PostMapping("/usun/{project_id}")
    public String deleteProject(@PathVariable Long project_id, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Long loggedUserId = (Long) session.getAttribute("loggedUserId");

        if (loggedUserId == null) {
            model.addAttribute("error", "Musisz być zalogowany, aby usunąć projekt.");
            return "redirect:/logowanie";
        }

        System.out.println("Rozpoczęto usuwanie projektu o ID: " + project_id + " przez użytkownika o ID: " + loggedUserId);

        Optional<Project> projectOptional = projectService.getProjectById(project_id);

        if (projectOptional.isEmpty()) {
            model.addAttribute("error", "Projekt o podanym ID nie istnieje.");
            return "error";
        }

        Project project = projectOptional.get();

        if (!project.getUser().getId().equals(loggedUserId)) {
            model.addAttribute("error", "Nie masz uprawnień do usunięcia tego projektu.");
            System.out.println("Nieautoryzowana próba usunięcia projektu!");
            return "error";
        }

        try {
            projectService.deleteProject(project_id);
            System.out.println("Projekt o ID: " + project_id + " został pomyślnie usunięty.");
            redirectAttributes.addFlashAttribute("success", "Projekt został pomyślnie usunięty.");
            return "redirect:/projekty";
        } catch (Exception e) {
            System.out.println("Wystąpił błąd podczas usuwania projektu: " + e.getMessage());
            model.addAttribute("error", "Wystąpił błąd podczas usuwania projektu.");
            return "error";
        }
    }

    @GetMapping("/dodaj-zadanie")
    public String showAddTaskForm(Model model, HttpSession session) {
        String username = (String) session.getAttribute("loggedUser");
        Optional<User> optionalUser = userService.getUserByUsername(username);

        if (optionalUser.isPresent()) {
            User loggedUser = optionalUser.get();
            model.addAttribute("task", new Task());
            model.addAttribute("projects", projectService.getProjectsByUser(loggedUser));
        }
        return "projects/addTask";
    }

    @PostMapping("/dodaj-zadanie")
    public String addTaskToProject(@RequestParam("projectId") Long projectId,
                                   @RequestParam("description") String description,
                                   HttpSession session) {
        String username = (String) session.getAttribute("loggedUser");
        Optional<User> optionalUser = userService.getUserByUsername(username);

        if (optionalUser.isPresent()) {
            User loggedUser = optionalUser.get();
            projectService.addTaskToProject(projectId, description, loggedUser);
        }
        return "redirect:/projekty";

    }


}


