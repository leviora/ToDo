package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Entity.ContactMessage;
import com.mazowiecka.demo.Repository.ContactMessageRepository;
import com.mazowiecka.demo.Service.TaskService;
import com.mazowiecka.demo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    private final ContactMessageRepository contactMessageRepository;

    public HomeController(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    @GetMapping("/")
    public String homePage() {

        return "index";
    }

    @PostMapping("/send-message")
    public String handleContactForm(@RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String message,
                                    RedirectAttributes redirectAttributes) {

        ContactMessage contactMessage = new ContactMessage();
        contactMessage.setName(name);
        contactMessage.setEmail(email);
        contactMessage.setMessage(message);

        contactMessageRepository.save(contactMessage);

        redirectAttributes.addFlashAttribute("messageSent", true);

        return "redirect:/";
    }
}

