package ru.mifi.stepan.shortlink.domain;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    UUID createUser();

    void check(UUID userId);

    List<UUID> getAllUsers();
}
