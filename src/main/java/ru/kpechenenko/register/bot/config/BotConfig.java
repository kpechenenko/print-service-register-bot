package ru.kpechenenko.register.bot.config;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.sql.DataSource;

@Configuration
@ComponentScan("ru.kpechenenko.register.bot")
@PropertySource("classpath:application.properties")
public class BotConfig {
    @Value("${database.url}")
    private String dbUrl;
    @Value("${database.password}")
    private String dbPassword;
    @Value("${database.username}")
    private String dbUsername;
    @Value("${database.driver}")
    private String dbDriver;

    @Bean
    DataSource dataSource() {
        var driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(this.dbUrl);
        driverManagerDataSource.setUsername(this.dbUsername);
        driverManagerDataSource.setPassword(this.dbPassword);
        driverManagerDataSource.setDriverClassName(this.dbDriver);
        return driverManagerDataSource;
    }

    @Bean
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        return new TelegramBotsApi(DefaultBotSession.class);
    }

    @Bean
    public EmailValidator emailValidator() {
        return EmailValidator.getInstance();
    }
}
