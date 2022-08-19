package ru.kpechenenko.register.bot.service.log;

public interface RegistrationHistoryService {
    boolean logUserRegistrationOnCompanyPrintService(String userEmail, String ownerCompanyEmail, boolean emailSend);
}
