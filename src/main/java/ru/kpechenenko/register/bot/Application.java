package ru.kpechenenko.register.bot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import ru.kpechenenko.register.bot.config.BotConfig;

public final class Application {
    public static void main(String[] args) {
        try {
            var applicationContext = new AnnotationConfigApplicationContext(BotConfig.class);
            var currencyBot = applicationContext.getBean(PrintServiceRegisterBot.class);
            var telegramBotsApi = applicationContext.getBean(TelegramBotsApi.class);
            telegramBotsApi.registerBot(currencyBot);
        } catch (Exception e) {
            System.err.printf("ERROR! %s\n", e.getMessage());
        }
    }
}
