package ru.mifi.stepan.shortlink.data;

import java.util.Set;
import java.util.UUID;

public interface IUserRepository {
    void saveUser(UUID uuid);

    boolean isExists(UUID userId);

    Set<UUID> getAll();
}
