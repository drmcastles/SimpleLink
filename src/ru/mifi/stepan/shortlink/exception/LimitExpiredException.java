package ru.mifi.stepan.shortlink.exception;

public class LimitExpiredException extends RuntimeException {
    public LimitExpiredException(String message) {
        super(message);
    }
}
