package ru.mifi.stepan.shortlink.domain;

import java.util.UUID;

public interface IUserManagement {
    UUID getUserId();

    void setUserId(UUID userId);
}
