package ru.kpechenenko.register.bot.dao;

import ru.kpechenenko.register.bot.model.UserRegistration;

public interface UserRegistrationDao {
    boolean create(UserRegistration userRegistration);
}
