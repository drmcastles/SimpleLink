package ru.mifi.stepan.shortlink.dto;

import java.util.UUID;

public class CreateLinkDto {
    private final String link;
    private final int limits;
    private final UUID userId;

    public CreateLinkDto(String link, int limits, UUID userId) {
        this.link = link;
        this.limits = limits;
        this.userId = userId;
    }

    public String getLink() {
        return link;
    }

    public int getLimits() {
        return limits;
    }

    public UUID getUserId() {
        return userId;
    }
}
