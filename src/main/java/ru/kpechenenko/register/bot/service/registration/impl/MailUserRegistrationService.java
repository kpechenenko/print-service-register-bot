package ru.kpechenenko.register.bot.service.registration.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.kpechenenko.register.bot.service.registration.UserRegistrationService;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Primary
@Service
@RequiredArgsConstructor
public final class MailUserRegistrationService implements UserRegistrationService {
    private final Session mailSession;

    @Override
    public boolean registerUserOnCompanyService(String userEmailForRegistration, String ownerCompanyEmail) {
        try {
            var message = new MimeMessage(this.mailSession);
            message.setFrom(new InternetAddress(userEmailForRegistration));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ownerCompanyEmail));
            message.setSubject(this.getDefaultMessageSubject());
            var contentBody = new MimeBodyPart();
            contentBody.setContent(this.getDefaultMessageText(), "text/html; charset=utf-8");
            var allContent = new MimeMultipart();
            allContent.addBodyPart(contentBody);
            message.setContent(allContent);
            Transport.send(message);
            return true;
        } catch (Exception e) {
            System.err.printf("ERROR! %s\n", e.getMessage());
            return false;
        }
    }

    private String getDefaultMessageText() {
        return "Success";
    }

    private String getDefaultMessageSubject() {
        return "Registration in print service";
    }
}

