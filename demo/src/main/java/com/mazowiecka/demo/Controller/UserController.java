package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        if (userService.isUsernameTaken(user.getUsername())) {
            model.addAttribute("usernameError", "Podana nazwa użytkownika już istnieje.");
            return "fragments/createUser";
        }
        if (userService.isEmailTaken(user.getEmail())) {
            model.addAttribute("emailError", "Podany adres e-mail już istnieje.");
            return "fragments/createUser";
        }
        if (!userService.isPasswordStrong(user.getPassword())) {
            model.addAttribute("passwordError", "Hasło musi mieć co najmniej 8 znaków, zawierać cyfrę i znak specjalny.");
            return "fragments/createUser";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/logowanie")
    public String showLoginForm() {
        return "fragments/login";
    }

    @PostMapping("/logowanie")
    public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        User user = userService.getUserByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("error", "Nieprawidłowa nazwa użytkownika lub hasło");
//            return "redirect:/logowanie";
            return "redirect:/";
        }
    }


}
