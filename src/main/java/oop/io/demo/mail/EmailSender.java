package oop.io.demo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import oop.io.demo.csvhandler.Response;
import oop.io.demo.mail.payload.BookingRequest;
import oop.io.demo.mail.payload.ConfirmRequest;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

@Controller
@RequestMapping("/send")
public class EmailSender {
    @Autowired
    EmailService emailService;
    
    // Sending Email Templates With/Without Attachments
    @PostMapping("/booking")
    // YOU NEED TO ADD THE DATE IN THE MODEL AND GET AS REQUEST
    public ResponseEntity sendAttachmentMessage(@Valid @RequestBody BookingRequest bookingRequest) throws Exception {
        try{
            Email email = new Email();
            email.setTo(bookingRequest.getEmail());
            email.setFrom("oopg2t4@outlook.com");
            email.setSubject("Booking Confirmation");
            email.setContent("Sending mail");
            Map<String, Object> model = new HashMap<>();
            model.put("name", bookingRequest.getName());
            model.put("attractionName", bookingRequest.getAttractionName());
            model.put("corpPassNumber", bookingRequest.getCorpPassNumber());
            email.setModel(model);
    
            String template = bookingRequest.getTemplate();
            
            if (bookingRequest.getAttachment() == null){
                emailService.sendEmailTemplate(email, template);
            } else {
                String attachment = bookingRequest.getAttachment();
                emailService.sendEmailWithAttachment(email, template, attachment);
            }
            return ResponseEntity.ok("Check your email for your booking information!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Booking was unsuccessful.");
        }
    }
    // Sending Pass Collected Emails
    @PostMapping("/collected")
    public ResponseEntity sendCollectedMessage(@Valid @RequestBody ConfirmRequest confirmRequest) throws Exception {
        try {
            Email email = new Email();
            email.setTo(confirmRequest.getEmail());
            email.setFrom("oopg2t4@outlook.com");
            email.setSubject("[Notification] Pass Collected");
            email.setContent("Sending mail");
    
            String t = "Pass Collected Email.html";
            emailService.sendSimpleEmailTemplate(email, t);
            return ResponseEntity.ok("Collection noted!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Email is not valid.");
        }
    }

    //Reminder Emails


    /* Sending Email Templates Without Attachmenet--> just need to find attraction and get the templates from there!
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
    */

    

}