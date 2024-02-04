package app.mail.services;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import app.mail.entities.Email;

import io.github.cdimascio.dotenv.Dotenv;

@Service
public class MailHandler {
    private String mailUser;
    private String mailPass;

    private Dotenv dotenv = Dotenv.load();

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public MailHandler() {
        this.mailUser = dotenv.get("MAIL_USERNAME");
        this.mailPass = dotenv.get("MAIL_APP_PASS");
    }

    public void sendEmail(Email email) {
        javaMailSender.setUsername(this.mailUser);
        javaMailSender.setPassword(this.mailPass);

        email.setSentAt(new Date());

        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(email.getFrom());
            message.setTo(email.getTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            message.setSentDate(email.getSentAt());

            javaMailSender.send(message);

            System.out.println("[" + LocalDateTime.now() + "]: " + "Mail sended successfully!");
        } catch (MailException e) {
            System.out.println("[" + LocalDateTime.now() + "]: " + e);
        }
    }
}
