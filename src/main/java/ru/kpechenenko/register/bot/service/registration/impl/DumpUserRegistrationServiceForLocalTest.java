package ru.kpechenenko.register.bot.service.registration.impl;

import org.springframework.stereotype.Component;
import ru.kpechenenko.register.bot.service.registration.UserRegistrationService;

// todo replace it in future
@Component
public class DumpUserRegistrationServiceForLocalTest implements UserRegistrationService {
    @Override
    public boolean registerUserOnCompanyService(String userEmailForRegistration, String ownerCompanyEmail) {
        return true;
    }
}
