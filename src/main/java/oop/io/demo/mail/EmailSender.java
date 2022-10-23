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

        String t = "Pass Collected Email.html";

        emailService.sendSimpleEmailTemplate(email, t);
    }

    // Send Email for Password Reset and Verification
    @PostMapping("/password")
    public void sendPasswordMessage(String toEmail, String token, String subject) throws Exception {
        Email email = new Email();
        email.setTo(toEmail);
        email.setFrom("oopg2t4@outlook.com");
        email.setSubject(subject);
        email.setContent("Sending mail");
        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        email.setModel(model);

        String t = "Password Email.html";

        emailService = new EmailService();
        emailService.sendEmailTemplate(email, t);
    }
    // Sending Email Templates Without Attachmenet--> just need to find attraction and get the templates from there!
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

        String t = "email.html";

        emailService.sendEmailTemplate(email, t);
    }

    // Sending Email Templates With Attachments
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
        model.put("attractionName", "Mandai Wildlife Reserves");
        model.put("corpPassNumber", "S123456Z");
        email.setModel(model);

        String template = "Physical Email Confirmation.html";
        String attachment = "Authorisation Letter - Premium Corporate Friends of the Zoo.pdf";

        emailService.sendEmailWithAttachment(email, template, attachment);
    }

    

}