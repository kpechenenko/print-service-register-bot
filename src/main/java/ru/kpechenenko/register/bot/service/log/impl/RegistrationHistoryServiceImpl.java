package ru.kpechenenko.register.bot.service.log.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpechenenko.register.bot.dao.UserRegistrationDao;
import ru.kpechenenko.register.bot.model.UserRegistration;
import ru.kpechenenko.register.bot.service.log.RegistrationHistoryService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public final class RegistrationHistoryServiceImpl implements RegistrationHistoryService {
    private final UserRegistrationDao userRegistrationDao;

    @Override
    public boolean logUserRegistrationOnCompanyPrintService(String userEmail, String ownerCompanyEmail, boolean emailSend) {
        return this.userRegistrationDao.create(
            new UserRegistration(userEmail, ownerCompanyEmail, emailSend, LocalDateTime.now())
        );
    }
}
