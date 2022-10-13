package oop.io.demo.mail;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailUtil {
    @Autowired      private JavaMailSender emailSender;
    @Autowired      private SpringTemplateEngine templateEngine;

    public void sendResetPassword(String username, String emailTo, String newPassword) {
        try {

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            
            Context context = new Context();
            Map<String, Object> props = new HashMap<String, Object>();
            props.put("username", username);
            props.put("newPassword", newPassword);
            context.setVariables(props);
            
            String html = templateEngine.process("email-password-reset", context);
            helper.setTo(emailTo);
            helper.setText(html, true);
            helper.setSubject("Password Reset");
            
            emailSender.send(message);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
        
    public void sendEmailVesselAlert(String username, String emailTo, String vesselName, HashMap<String, String> updates) {
        try {

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            
            Context context = new Context();
            Map<String, Object> props = new HashMap<String, Object>();
            props.put("username", username);
            props.put("updates", updates);
            context.setVariables(props);
            
            String html = templateEngine.process("email-vessel-alert", context);
            helper.setTo(emailTo);
            helper.setText(html, true);
            helper.setSubject("Vessel Update Alert: " + vesselName);
            
            emailSender.send(message);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
