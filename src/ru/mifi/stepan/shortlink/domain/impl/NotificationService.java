package ru.mifi.stepan.shortlink.domain.impl;

import ru.mifi.stepan.shortlink.data.INotificationRepository;
import ru.mifi.stepan.shortlink.data.entity.LinkEntity;
import ru.mifi.stepan.shortlink.data.entity.NotificationEntity;
import ru.mifi.stepan.shortlink.data.entity.NotificationType;
import ru.mifi.stepan.shortlink.domain.INotificationService;
import ru.mifi.stepan.shortlink.domain.ServiceFabric;
import ru.mifi.stepan.shortlink.dto.NotificationDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class NotificationService implements INotificationService {
    private final INotificationRepository notificationRepository;

    public NotificationService(INotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<NotificationDto> readNotifications() {
        UUID userId = ServiceFabric.getUserManagement().getUserId();
        if (userId == null) {
            return null;
        }
        Set<NotificationEntity> unreadNotifications = notificationRepository.getUnreadNotifications(userId);
        notificationRepository.setReadComplete(userId);
        return unreadNotifications.stream().map(entity -> new NotificationDto(entity.getLink(), entity.getType())).collect(Collectors.toList());
    }

    @Override
    public void createNotifications(List<LinkEntity> links, NotificationType type) {
        links.forEach(link -> notificationRepository.register(link.getLink(), link.getUserId(), type));
    }
}
