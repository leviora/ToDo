//package com.mazowiecka.demo.Controller;
//
//import com.mazowiecka.demo.Entity.User;
//import com.mazowiecka.demo.Service.UserService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/panel-uzytkownika")
//public class UserPanelController {
//
//    private final UserService userService;
//
//    public UserPanelController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public String showUserPanel(HttpSession session, Model model) {
//
//        String loggedUser = (String) session.getAttribute("loggedUser");
//        model.addAttribute("loggedUser", loggedUser);
//
//        return "fragments/user-panel";
//    }
//
//    @GetMapping("/edytuj-profil")
//    public String showEditProfileForm(HttpSession session, Model model) {
//
//        String loggedUsername = (String) session.getAttribute("loggedUser");
//
//        if (loggedUsername == null) {
//            return "redirect:/login";
//        }
//
//        String loggedEmail = userService.getUserByUsername(loggedUsername)
//                .map(User::getEmail)
//                .orElse(null);
//        model.addAttribute("isLoggedIn", true);
//        model.addAttribute("loggedUsername", loggedUsername);
//        model.addAttribute("loggedEmail", loggedEmail);
//
//        return "fragments/editProfile";
//    }
//
//    @PostMapping("/zmien-nazwe-uzytkownika")
//    public String updateUsername(HttpSession session,
//                                 @RequestParam String newUsername,
//                                 Model model) {
//        String loggedUsername = (String) session.getAttribute("loggedUser");
//        if (loggedUsername == null) {
//            return "redirect:/login";
//        }
//
//        try {
//            userService.updateUsername(loggedUsername, newUsername);
//            session.setAttribute("loggedUser", newUsername);
//            model.addAttribute("success", "Nazwa użytkownika została zmieniona.");
//        } catch (IllegalArgumentException e) {
//            model.addAttribute("error", e.getMessage());
//        }
//        return "fragments/user-panel";
//    }
//
//    @PostMapping("/zmien-email")
//    public String updateEmail(HttpSession session,
//                              @RequestParam String newEmail,
//                              Model model) {
//
//        String loggedUsername = (String) session.getAttribute("loggedUser");
//        if (loggedUsername == null) {
//            System.out.println("Użytkownik niezalogowany.");
//            return "redirect:/login";
//        }
//
//        try {
//            userService.updateEmail(loggedUsername, newEmail);
//            session.setAttribute("loggedEmail", newEmail);
//            model.addAttribute("success", "Adres e-mail został zmieniony.");
//        } catch (IllegalArgumentException e) {
//            System.out.println("Błąd: " + e.getMessage());
//            model.addAttribute("error", e.getMessage());
//        }
//        return "fragments/user-panel";
//    }
//
//    @PostMapping("/zmien-haslo")
//    public String changePassword(HttpSession session,
//                                 @RequestParam String currentPassword,
//                                 @RequestParam String newPassword,
//                                 @RequestParam String confirmPassword,
//                                 Model model) {
//
//        String loggedUsername = (String) session.getAttribute("loggedUser");
//        if (loggedUsername == null) {
//            model.addAttribute("error", "Nie jesteś zalogowany.");
//            return "redirect:/login";
//        }
//
//        try {
//            userService.changePassword(loggedUsername, currentPassword, newPassword, confirmPassword);
//            model.addAttribute("success", "Hasło zostało zmienione.");
//        } catch (IllegalArgumentException e) {
//            model.addAttribute("error", e.getMessage());
//        }
//
//        return "fragments/user-panel";
//    }
//
//    @RequestMapping(value = "/wylogowanie", method = {RequestMethod.GET, RequestMethod.POST})
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "redirect:/";
//    }
//
//    @GetMapping("/usun-konto")
//    public String showDeleteAccountConfirmation(HttpSession session, Model model) {
//        String loggedUsername = (String) session.getAttribute("loggedUser");
//
//        if (loggedUsername == null) {
//            return "redirect:/login";
//        }
//
//        model.addAttribute("loggedUser", loggedUsername);
//        return "fragments/deleteAccountConfirmation";
//    }
//
//    @PostMapping("/usun-konto")
//    public String deleteAccount(HttpSession session, Model model) {
//        String loggedUsername = (String) session.getAttribute("loggedUser");
//
//        if (loggedUsername == null) {
//            return "redirect:/login";
//        }
//
//        Optional<User> user = userService.getUserByUsername(loggedUsername);
//        if (user.isPresent()) {
//            userService.deleteUser(user.get().getId());
//            session.invalidate();
//            return "redirect:/";
//        }
//
//        model.addAttribute("error", "Nie udało się usunąć konta.");
//        return "fragments/user-panel";
//    }
//
//}
