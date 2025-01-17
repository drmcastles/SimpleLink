package ru.mifi.stepan.shortlink.data;

import ru.mifi.stepan.shortlink.data.entity.NotificationEntity;
import ru.mifi.stepan.shortlink.data.entity.NotificationType;

import java.util.Set;
import java.util.UUID;

public interface INotificationRepository {
    Set<NotificationEntity> getUnreadNotifications(UUID userId);

    void setReadComplete(UUID userId);

    void register(String link, UUID userId, NotificationType type);
}
