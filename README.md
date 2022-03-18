# job4j_url_shortcut

- Приложение для обеспечения безопасности пользователей. Ссылки на сайте заменяются на ссылки на наш сервис.
  - <b>Функционал</b>:
    - Регистрация сайта (POST /registration). При регистрации приложением генерируется логин и пароль;
    - Авторизация использую JWT;
    - Регистрация URL (POST ./reg/url). При регистрации url, на основе его генерируется уникальный код;
    - Переадресация (GET /redirect/{уникальный_код}).
  - Стек: Spring Boot, Spring Security, Spring Data, Liquibase, Lombok.
  - Контакты: Telegram @ilya_hollow
