package app.mail.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import app.mail.dto.ExecMailDto;
import app.mail.entities.Email;

import jakarta.validation.Valid;

@Service
public class MailService {
    @Autowired
    private MailHandler mailhandler;

    public MailService(MailHandler mailhandler) {
        this.mailhandler = mailhandler;
    }

    @SuppressWarnings("null")
    @RabbitListener(queues = "mail-queue")
    public void exec(@Payload @Valid ExecMailDto message) {

        Email email = new Email();

        BeanUtils.copyProperties(message.toEmail(), email);

        this.mailhandler.sendEmail(email);
    }
}
