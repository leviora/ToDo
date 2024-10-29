package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Entity.Role;
import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/tworzenieUzytkownika")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "fragments/createUser";
    }

    @PostMapping("/tworzenieUzytkownika")
    public String saveUser(@ModelAttribute User user, Model model) {
        try {
            user.setRole(Role.USER);
            userService.saveUser(user);

            return "redirect:/logowanie";
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("Nazwa użytkownika jest już zajęta")) {
                model.addAttribute("usernameError", "Nazwa użytkownika jest już zajęta.");
            } else if (e.getMessage().contains("Hasło nie spełnia wymagań")) {
                model.addAttribute("passwordError", "Hasło nie spełnia wymagań.");
            }
            return "fragments/createUser";
        }
    }

    @GetMapping("/logowanie")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Nieprawidłowa nazwa użytkownika lub hasło.");
        }
        return "fragments/login";
    }

    @PostMapping("/logowanie")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        if (userService.login(username, password)) {
            session.setAttribute("loggedUser", username);
            System.out.println("Zalogowany użytkownik: " + session.getAttribute("loggedUser"));
            return "redirect:/";
        } else {
            model.addAttribute("error", "Nieprawidłowa nazwa użytkownika lub hasło.");
            return "fragments/login";
        }
    }
    @PostMapping("/wylogowanie")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/logowanie";
    }

    @GetMapping("/testFindUser")
    @ResponseBody
    public String testFindUser(@RequestParam String username) {
        Optional<User> user = userService.getUserByUsername(username);

        if (user.isPresent()) {
            return "Znaleziono użytkownika: " + user.get().getUsername();
        } else {
            return "Nie znaleziono użytkownika o nazwie: " + username;
        }
    }


}
