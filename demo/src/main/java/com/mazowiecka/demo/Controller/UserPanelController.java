package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('USER')")
@Controller
@RequestMapping("/panel-uzytkownika")
public class UserPanelController {

    private final UserService userService;

    public UserPanelController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUserPanel(Model model) {
        try {
            User user = userService.getCurrentUser();
            model.addAttribute("loggedUser", user.getUsername());
            return "pages/user-panel";
        } catch (Exception e) {
            return "redirect:/login";
        }
    }

    @GetMapping("/edytuj-profil")
    public String showEditProfileForm(Model model) {
        try {
            User user = userService.getCurrentUser();
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("loggedUsername", user.getUsername());
            model.addAttribute("loggedEmail", user.getEmail());
            return "pages/editProfile";
        } catch (Exception e) {
            return "redirect:/login";
        }
    }

    @PostMapping("/zmien-nazwe-uzytkownika")
    public String updateUsername(@RequestParam String newUsername, Model model) {
        try {
            User user = userService.getCurrentUser();
            userService.updateUsername(user.getUsername(), newUsername);
            model.addAttribute("success", "Nazwa użytkownika została zmieniona.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "pages/user-panel";
    }

    @PostMapping("/zmien-email")
    public String updateEmail(@RequestParam String newEmail, Model model) {
        try {
            User user = userService.getCurrentUser();
            userService.updateEmail(user.getUsername(), newEmail);
            model.addAttribute("success", "Adres e-mail został zmieniony.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "pages/user-panel";
    }

    @PostMapping("/zmien-haslo")
    public String changePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 Model model) {
        try {
            User user = userService.getCurrentUser();
            userService.changePassword(user.getUsername(), currentPassword, newPassword, confirmPassword);
            model.addAttribute("success", "Hasło zostało zmienione.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "pages/user-panel";
    }

    @GetMapping("/usun-konto")
    public String showDeleteAccountConfirmation(Model model) {
        try {
            User user = userService.getCurrentUser();
            model.addAttribute("loggedUser", user.getUsername());
            return "fragments/deleteAccountConfirmation";
        } catch (Exception e) {
            return "redirect:/login";
        }
    }

    @PostMapping("/usun-konto")
    public String deleteAccount(Model model) {
        try {
            User user = userService.getCurrentUser();
            userService.deleteUser(user.getId());

            org.springframework.security.core.context.SecurityContextHolder.clearContext();

            return "redirect:/logout";
        } catch (Exception e) {
            model.addAttribute("error", "Nie udało się usunąć konta.");
            return "pages/user-panel";
        }
    }
}
