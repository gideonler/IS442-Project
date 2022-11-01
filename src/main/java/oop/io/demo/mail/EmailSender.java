package oop.io.demo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import oop.io.demo.attraction.Attraction;
import oop.io.demo.attraction.AttractionRepository;
import oop.io.demo.csvhandler.Response;
import oop.io.demo.loan.Loan;
import oop.io.demo.loan.LoanRepository;
import oop.io.demo.mail.payload.BookingRequest;
import oop.io.demo.mail.payload.ConfirmRequest;
import oop.io.demo.user.User;
import oop.io.demo.user.UserRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

@Controller
@RequestMapping("/email")
public class EmailSender {
    @Autowired
    EmailService emailService;

    private final UserRepository userRepository;

    private final LoanRepository loanRepository;

    private final AttractionRepository attractionRepository;

    public EmailSender(UserRepository userRepository, LoanRepository loanRepository, AttractionRepository attractionRepository) {
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
        this.attractionRepository = attractionRepository;
    }
    
    // Sending Email Templates With/Without Attachments --> For Confirmed Bookings
    @PostMapping("/booking")
    public ResponseEntity sendAttachmentMessage(@Valid @RequestBody BookingRequest bookingRequest) throws Exception {
        try{
            //Take user email and loanId out of Booking Request
            String emailTo = bookingRequest.getEmail();
            String loanId = bookingRequest.getLoanId();
            User user = userRepository.findByEmail(emailTo).get();
            Loan loan = loanRepository.findByLoanId(loanId);

            //Create email
            Email email = new Email();
            email.setTo(emailTo);
            email.setFrom("oopg2t4@outlook.com");
            email.setSubject("Booking Confirmation");
            email.setContent("Sending mail");

            //Set values into email template - name, attractionname, passno, loandate
            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getName());
            String attractionName = loan.getAttractionName();
            model.put("attractionName", attractionName);
            model.put("corpPassNumber", loan.getPassNo());
            Date loanDate = loan.getLoanDate();
            SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy");
            String strDate = formatter.format(loanDate);  
            model.put("loanDate", strDate);
            email.setModel(model);
    
            // Prepare template path and attachment path (if found)
            Attraction attraction = attractionRepository.findByAttractionName(attractionName).get();
            String template = attraction.getTemplateFilename();
            String templatePath = attraction.getTemplateFilePath();
            String attachment = attraction.getAttachmentPDFFilename();

            // two ways to send email dependent on whether there is attachment or not 
            if (attachment == null){
                emailService.sendEmailTemplate(email, template, templatePath);
            } else {
                String attachmentPath = attraction.getAttachmentPDFFilePath();
                emailService.sendEmailWithAttachment(email, template, templatePath, attachment, attachmentPath);
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
            return ResponseEntity.ok("Email sent!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Email is not sent.");
        }
    }

    //Reminder Emails
    @PostMapping("/tocollect")
    public ResponseEntity sendToCollectMessage(@Valid @RequestBody ConfirmRequest confirmRequest) throws Exception {
        try {
            Email email = new Email();
            email.setTo(confirmRequest.getEmail());
            email.setFrom("oopg2t4@outlook.com");
            email.setSubject("[Notification] Collect your Pass");
            email.setContent("Sending mail");
    
            String t = "To Collect Pass Email.html";
            emailService.sendSimpleEmailTemplate(email, t);
            return ResponseEntity.ok("Email sent!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Email is not sent.");
        }
    }

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