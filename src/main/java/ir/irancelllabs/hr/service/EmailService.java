package ir.irancelllabs.hr.service;

public interface EmailService {

    void sendEmail(String to, String subject, String body);
}
