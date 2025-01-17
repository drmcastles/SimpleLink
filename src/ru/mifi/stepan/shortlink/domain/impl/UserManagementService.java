package ru.mifi.stepan.shortlink.domain.impl;

import ru.mifi.stepan.shortlink.domain.IUserManagement;

import java.util.UUID;

public class UserManagementService implements IUserManagement {
    private UUID userId;

    @Override
    public UUID getUserId() {
        return userId;
    }

    @Override
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
