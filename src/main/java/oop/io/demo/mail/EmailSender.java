package oop.io.demo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/send")
public class EmailSender {
    @Autowired
    EmailService emailService;

    @PostMapping("/email")
    public void sendMessage() {
        Email email = new Email();
        email.setTo("oopg2t4@outlook.com");
        email.setFrom("oopg2t4@outlook.com");
        email.setSubject("Learning how to send mail");
        email.setContent("Sending mail");
        Map<String, Object> model = new HashMap<>();
        model.put("firstName", "oop");
        model.put("lastName", "g2t4slay");
        emailService.send(email);
    }

}