# Бот для регистрации пользователей в сервисе печати компании

Telegram бот, позволяющий регистрировать пользователей в сервисах печати принтеров **компании Х** при помощи отправки письма
с почтового сервера компании продавца принтеров.

Информация о регистрации пользователей записывается в базу данных.

<img src="assets/sent_email_command.png" alt="send email" width="600px">
<br>
<img src="assets/content_of_received_email.png" alt="email content" width="600px">

## Запуск

1. Установить значения необходимых свойств в [`applicaiton.properties`](src/main/resources/application.properties)
   , [`mail.properties`](src/main/resources/mail.properties).
2. Инициализировать структуру базы данных, запустив [`init.sql`](src/main/resources/db/init.sql).
3. Создать архив:
   ```bash
   $ mvn package
   ```
4. Запустить архив:
    ```bash
   $ java -jar target/print-service-register-bot-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

## Стек

- Java 17.
- Maven.
- PostgreSQL.
- Sping (context, data-jdbc).
- Lombok.
- Commons-validator.
- telegrambots.
- javaxmail.
