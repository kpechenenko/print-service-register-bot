package ru.kpechenenko.register.bot.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.kpechenenko.register.bot.dao.UserRegistrationDao;
import ru.kpechenenko.register.bot.model.UserRegistration;

import javax.sql.DataSource;

@Component
public final class UserRegistrationDaoImpl implements UserRegistrationDao {
    private static final String SQL_INSERT_USER_REGISTRATION
        = "insert into registration_history(user_email, company_email, email_send_to_service, registration_date) values (?, ?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    public UserRegistrationDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean create(UserRegistration userRegistration) {
        return this.jdbcTemplate.update(
            SQL_INSERT_USER_REGISTRATION,
            userRegistration.getUserEmail(),
            userRegistration.getCompanyEmail(),
            userRegistration.isEmailSendToService(),
            userRegistration.getRegistrationDateTime()
        ) > 0;
    }
}
