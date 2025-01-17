package ru.mifi.stepan.shortlink.data.impl;

import ru.mifi.stepan.shortlink.data.INotificationRepository;
import ru.mifi.stepan.shortlink.data.entity.NotificationEntity;
import ru.mifi.stepan.shortlink.data.entity.NotificationType;

import java.util.*;
import java.util.stream.Collectors;

public class NotificationStorage implements INotificationRepository {
    private final HashMap<UUID, Set<NotificationEntity>> linkRepository = new HashMap();

    @Override
    public Set<NotificationEntity> getUnreadNotifications(UUID userId) {
        Set<NotificationEntity> notificationEntities = linkRepository.get(userId);
        if (notificationEntities == null) {
            return Collections.emptySet();
        }
        Set<NotificationEntity> result = notificationEntities.stream().filter(entity -> !entity.isRead()).collect(Collectors.toSet());
        return result;
    }
    @Override
    public void setReadComplete(UUID userId) {
        Set<NotificationEntity> notificationEntities = linkRepository.get(userId);
        if(notificationEntities == null || notificationEntities.isEmpty()) {
            return;
        }
        notificationEntities.forEach(NotificationEntity::setRead);
    }

    @Override
    public void register(String link, UUID userId, NotificationType type) {
        init(userId);
        Set<NotificationEntity> notificationEntities = linkRepository.get(userId);
        NotificationEntity notification = new NotificationEntity(userId, type, link);
        if (!notificationEntities.contains(notification)) {
            notificationEntities.add(notification);
        }
        linkRepository.put(userId, notificationEntities);
    }

    private void init(UUID userId) {
        if (!linkRepository.containsKey(userId)) {
            linkRepository.put(userId, new HashSet<>());
        }
    }
}
