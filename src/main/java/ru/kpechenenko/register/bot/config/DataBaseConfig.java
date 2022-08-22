package ru.kpechenenko.register.bot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@PropertySource("classpath:application.properties")
@Configuration
public class DataBaseConfig {
    @Value("${database.url}")
    private String dbUrl;
    @Value("${database.password}")
    private String dbPassword;
    @Value("${database.username}")
    private String dbUsername;
    @Value("${database.driver}")
    private String dbDriver;

    @Bean
    public DataSource dataSource() {
        var driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(this.dbUrl);
        driverManagerDataSource.setUsername(this.dbUsername);
        driverManagerDataSource.setPassword(this.dbPassword);
        driverManagerDataSource.setDriverClassName(this.dbDriver);
        return driverManagerDataSource;
    }
}
