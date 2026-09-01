package ir.irancelllabs.hr.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class AWSEmailService implements EmailService {
    @Override
    public void sendEmail(String to, String subject, String body) {
        //real implement

    }
}
