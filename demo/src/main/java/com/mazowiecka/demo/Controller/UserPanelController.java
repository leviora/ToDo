package com.mazowiecka.demo.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/panel-uzytkownika")
public class UserPanelController {

    @GetMapping
    public String showUserPanel(HttpSession session, Model model) {

        String loggedUser = (String) session.getAttribute("loggedUser");
        model.addAttribute("loggedUser", loggedUser);

        return "fragments/user-panel";
    }
}
