package ru.kpechenenko.register.bot;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kpechenenko.register.bot.service.log.RegistrationHistoryService;
import ru.kpechenenko.register.bot.service.registration.UserRegistrationService;

@Component
@PropertySource("classpath:application.properties")
public final class PrintServiceRegisterBot extends TelegramLongPollingBot {
    private static final String START_COMMAND = "/start";
    private static final String REGISTER_EMAIL_COMMAND = "/register";
    private static final int VALID_NUMBER_OF_WORDS_ON_REGISTER_EMAIL_COMMAND = 3;

    private final String botUsername;
    private final String botToken;
    private final EmailValidator emailValidator;
    private final UserRegistrationService registrationService;
    private final RegistrationHistoryService registrationHistoryService;

    public PrintServiceRegisterBot(@Value("${bot.username}") String botUsername,
                                   @Value("${bot.token}") String botToken,
                                   EmailValidator emailValidator,
                                   UserRegistrationService registrationService,
                                   RegistrationHistoryService registrationHistoryService) {
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.emailValidator = emailValidator;
        this.registrationService = registrationService;
        this.registrationHistoryService = registrationHistoryService;
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }
        var chatId = update.getMessage().getChatId();
        var messageText = update.getMessage().getText();
        var answerText = "Unknown command.";
        if (messageText.startsWith(START_COMMAND)) {
            answerText = "Simple bot for register client's accounts in print service." +
                " To register email in print service send: `/register userEmail companyOwnerEmail`";
        } else if (messageText.startsWith(REGISTER_EMAIL_COMMAND)) {
            try {
                var components = messageText.split(" ");
                if (components.length < VALID_NUMBER_OF_WORDS_ON_REGISTER_EMAIL_COMMAND) {
                    throw new IllegalArgumentException("Validation failed. Send `/confirm userEmail companyOwnerEmail`.");
                }
                var userEmailForRegistration = components[1];
                if (!this.emailValidator.isValid(userEmailForRegistration)) {
                    throw new IllegalArgumentException("Validation failed. User email for registration is not email.");
                }
                var ownerCompanyEmail = components[2];
                if (!this.emailValidator.isValid(ownerCompanyEmail)) {
                    throw new IllegalArgumentException("Validation failed. Owner company email is not email.");
                }
                var isSuccessUserRegistration = this.registrationService.registerUserOnCompanyService(
                    userEmailForRegistration,
                    ownerCompanyEmail
                );
                var isUserRegistrationLogged = this.registrationHistoryService.logUserRegistrationOnCompanyPrintService(
                    userEmailForRegistration,
                    ownerCompanyEmail,
                    isSuccessUserRegistration
                );
                answerText = isSuccessUserRegistration ? "Success." : "Fail. Try later.";
                answerText += isUserRegistrationLogged ? " Logged." : " Fail to log.";
            } catch (IllegalArgumentException e) {
                answerText = e.getMessage();
            }
        }
        try {
            this.sendMessage(chatId, answerText);
        } catch (TelegramApiException e) {
            System.err.println(e.getMessage());
        }
    }

    private void sendMessage(Long chatId, String answerText) throws TelegramApiException {
        this.execute(new SendMessage(chatId.toString(), answerText));
    }
}