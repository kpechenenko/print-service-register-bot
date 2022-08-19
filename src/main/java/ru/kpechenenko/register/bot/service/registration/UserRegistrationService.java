package ru.kpechenenko.register.bot.service.registration;

public interface UserRegistrationService {
    boolean registerUserOnCompanyService(String userEmailForRegistration, String ownerCompanyEmail);
}
