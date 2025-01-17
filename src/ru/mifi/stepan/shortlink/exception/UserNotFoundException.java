package ru.mifi.stepan.shortlink.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Пользователь не найден");
    }
}
