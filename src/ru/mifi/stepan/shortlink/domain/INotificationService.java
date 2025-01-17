package ru.mifi.stepan.shortlink.domain;

import ru.mifi.stepan.shortlink.data.entity.LinkEntity;
import ru.mifi.stepan.shortlink.data.entity.NotificationType;
import ru.mifi.stepan.shortlink.dto.NotificationDto;

import java.util.List;

public interface INotificationService {
    List<NotificationDto> readNotifications();

    void createNotifications(List<LinkEntity> links, NotificationType type);
}
