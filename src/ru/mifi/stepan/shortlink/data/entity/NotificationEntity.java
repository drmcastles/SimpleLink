package ru.mifi.stepan.shortlink.data.entity;

import java.util.Objects;
import java.util.UUID;

public class NotificationEntity {
    private final UUID userId;
    private final NotificationType type;
    private final String link;
    private boolean isRead = false;

    public NotificationEntity(UUID userId, NotificationType type, String link) {
        this.userId = userId;
        this.type = type;
        this.link = link;
    }

    public UUID getUserId() {
        return userId;
    }

    public NotificationType getType() {
        return type;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead() {
        isRead = true;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationEntity that = (NotificationEntity) o;
        return getType() == that.getType() && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), link);
    }
}
