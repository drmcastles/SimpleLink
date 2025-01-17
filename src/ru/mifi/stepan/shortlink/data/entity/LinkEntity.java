package ru.mifi.stepan.shortlink.data.entity;

import ru.mifi.stepan.shortlink.property.SystemProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class LinkEntity implements ILinkInfo {

    public void setLink(String link) {
        this.link = link;
    }

    private String link;
    private final String shortLink;
    private final UUID userId;
    private boolean isActive;
    private final LocalDateTime expiredAt;
    private int limit;

    public String getLink() {
        return link;
    }

    @Override
    public String getShortLink() {
        return shortLink;
    }

    public UUID getUserId() {
        return userId;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    @Override
    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    public LinkEntity(String link, String shortLink, UUID userId, int limit) {
        this.link = link;
        this.shortLink = shortLink;
        this.userId = userId;
        this.limit = limit;
        this.isActive = true;
        expiredAt = LocalDateTime.now().plusMinutes(SystemProperty.duration);
    }
}
