package ru.mifi.stepan.shortlink.domain.impl;

import ru.mifi.stepan.shortlink.data.Storages;
import ru.mifi.stepan.shortlink.domain.IUserService;
import ru.mifi.stepan.shortlink.exception.UserNotFoundException;

import java.util.List;
import java.util.UUID;

public class UserService implements IUserService {
    private final Storages storages;

    public UserService(Storages storages) {
        this.storages = storages;
    }


    @Override
    public UUID createUser() {
        UUID uuid = UUID.randomUUID();
        storages.getUserRepository().saveUser(uuid);
        return uuid;
    }

    @Override
    public void check(UUID userId) {
        boolean exists = storages.getUserRepository().isExists(userId);
        if (!exists) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<UUID> getAllUsers() {
        return storages.getUserRepository().getAll().stream().toList();
    }


}
