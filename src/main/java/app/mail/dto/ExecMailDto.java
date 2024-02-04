package app.mail.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import app.mail.entities.Email;

public class ExecMailDto {
    @NotNull(message = "subject can't be null.")
    @NotBlank(message = "subject can't be empty.")
    private String subject;

    @NotNull(message = "text can't be null.")
    @NotBlank(message = "text can't be empty.")
    private String text;

    @jakarta.validation.constraints.Email
    @NotNull(message = "from can't be null.")
    @NotBlank(message = "from can't be empty.")
    private String from;

    @jakarta.validation.constraints.Email
    @NotNull(message = "to can't be null.")
    @NotBlank(message = "to can't be empty.")
    private String to;

    public ExecMailDto() {}

    public Email toEmail() {
        return new Email(this.from, this.to, this.subject, this.text);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
