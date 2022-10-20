package oop.io.demo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

@Controller
@RequestMapping("/send")
public class EmailSender {
    @Autowired
    EmailService emailService;

    // SUCCESSFUL EMAILS------------------------------------------------------------------------------------------

    // Sending Pass Collected Emails
    @PostMapping("/collected")
    public void sendCollectedMessage() throws Exception {
        Email email = new Email();
        email.setTo("oopg2t4@outlook.com");
        email.setFrom("oopg2t4@outlook.com");
        email.setSubject("[Notification] Pass Collected");
        email.setContent("Sending mail");

        String t = "collected_email.flth";

        emailService.sendSimpleEmailTemplate(email, t);
    }
    // Sending Email Templates --> just need to find attraction and get the templates from there!
    @PostMapping("/email")
    public void sendMessage() throws Exception {
        Email email = new Email();
        email.setTo("oopg2t4@outlook.com");
        email.setFrom("oopg2t4@outlook.com");
        email.setSubject("Slay?");
        email.setContent("Sending mail");
        Map<String, Object> model = new HashMap<>();
        model.put("firstName", "oop");
        model.put("lastName", "g2t4slay");
        email.setModel(model);

        String t = "email.ftl";

        emailService.sendEmailTemplate(email, t);
    }

    // Sending Email Attachments
    @PostMapping("/attachment")
    public void sendAttachmentMessage() throws Exception {
        Email email = new Email();
        email.setTo("oopg2t4@outlook.com");
        email.setFrom("oopg2t4@outlook.com");
        email.setSubject("Slay?");
        email.setContent("Sending mail");
        Map<String, Object> model = new HashMap<>();
        model.put("firstName", "oop");
        model.put("lastName", "g2t4slay");
        email.setModel(model);

        String t = "email.ftl";

        emailService.sendEmailWithAttachment(email, t);
    }

    

}