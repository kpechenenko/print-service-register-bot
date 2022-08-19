package ru.kpechenenko.register.bot.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class UserRegistration {
    private Long id;
    private String userEmail;
    private String companyEmail;
    private boolean emailSendToService;
    private LocalDateTime registrationDateTime;

    public UserRegistration(String userEmail,
                            String companyEmail,
                            boolean emailSendToService,
                            LocalDateTime registrationDateTime) {
        this.userEmail = userEmail;
        this.companyEmail = companyEmail;
        this.emailSendToService = emailSendToService;
        this.registrationDateTime = registrationDateTime;
    }
}
