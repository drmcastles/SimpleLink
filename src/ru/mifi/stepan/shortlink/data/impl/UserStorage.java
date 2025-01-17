package ru.mifi.stepan.shortlink.data.impl;

import ru.mifi.stepan.shortlink.data.IUserRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserStorage implements IUserRepository {
    public static final String USER_ALREADY_EXISTS = "User Already exists";
    private final Set<UUID> userRepository = new HashSet<>();

    @Override
    public void saveUser(UUID uuid) {
        if (userRepository.contains(uuid)) {
            throw new RuntimeException(USER_ALREADY_EXISTS);
        }
        userRepository.add(uuid);
    }

    @Override
    public boolean isExists(UUID userId) {
        return userRepository.contains(userId);
    }

    @Override
    public Set<UUID> getAll() {
        return userRepository;
    }
}
