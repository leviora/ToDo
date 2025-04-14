package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Repository.ContactMessageRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin/contact")
@Controller
public class ContactController {

    private final ContactMessageRepository contactMessageRepository;

    public ContactController(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    @GetMapping("/messages")
    public String showMessages(Model model) {
        model.addAttribute("messages", contactMessageRepository.findAll());
        return "messages"; // utworzyć widok
    }

}
