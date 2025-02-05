package controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.EmailService;
import org.springframework.web.bind.annotation.CrossOrigin;

import static config.EmailConfig.emailBccList;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5501") // fixme: Replace with your client's origin
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/api/email/send")
    public String sendEmail(@RequestBody EmailRequest request) {
        try {
            emailService.sendSimpleEmail(
                    request.getEmail(),
                    request.getName(),
                    request.getMessage(),
                    emailBccList
            );
            return "Email sent successfully!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Sorry! Your mail has NOT been send to the user!";
        }
    }

    // DTO for request body
    @Setter
    @Getter
    public static class EmailRequest {
        @Setter
        private String email;
        @Setter
        private String name;
        @Setter
        private String message;

        // Getters and setters
        public String getEmail() {
            return email;
        }

        public String getName() {
            return name;
        }

        public String getMessage() {
            return message;
        }
    }
}
