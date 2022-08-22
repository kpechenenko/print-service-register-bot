package ru.kpechenenko.register.bot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@PropertySource("classpath:mail.properties")
@Configuration
public class MailConfig {
    private final static int INITIAL_PROPERTIES_CAPACITY = 5;

    @Value("${smtp.host}")
    private String smtpHost;
    @Value("${smtp.port}")
    private String smtpPort;
    @Value("${authentication.username}")
    private String authenticationUsername;
    @Value("${authentication.password}")
    private String authenticationPassword;

    @Bean
    public Properties mailProperties() {
        var properties = new Properties(INITIAL_PROPERTIES_CAPACITY);
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", this.smtpHost);
        properties.put("mail.smtp.port", this.smtpPort);
        properties.put("mail.smtp.ssl.trust", this.smtpHost);
        return properties;
    }

    @Bean
    public Authenticator authenticator() {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(authenticationUsername, authenticationPassword);
            }
        };
    }

    @Bean
    public Session session(Properties mailProperties, Authenticator authenticator) {
        return Session.getInstance(mailProperties, authenticator);
    }
}
