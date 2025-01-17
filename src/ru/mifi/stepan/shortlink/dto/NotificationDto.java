package ru.mifi.stepan.shortlink.dto;

import ru.mifi.stepan.shortlink.data.entity.NotificationType;

public class NotificationDto {
    private final String link;
    private final NotificationType notificationType;

    public NotificationDto(String link, NotificationType notificationType) {
        this.link = link;
        this.notificationType = notificationType;
    }

    public String getLink() {
        return link;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }
}
