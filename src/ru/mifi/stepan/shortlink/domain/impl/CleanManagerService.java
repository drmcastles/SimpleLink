package ru.mifi.stepan.shortlink.domain.impl;

import ru.mifi.stepan.shortlink.data.ILinkRepository;
import ru.mifi.stepan.shortlink.data.Storages;
import ru.mifi.stepan.shortlink.data.entity.LinkEntity;
import ru.mifi.stepan.shortlink.data.entity.NotificationType;
import ru.mifi.stepan.shortlink.domain.ICleanManager;
import ru.mifi.stepan.shortlink.domain.ServiceFabric;

import java.util.List;

public class CleanManagerService implements ICleanManager {
    private final Storages storages;

    public CleanManagerService(Storages storages) {
        this.storages = storages;
    }

    @Override
    public void monitor() {
        ILinkRepository linkRepository = storages.getLinkRepository();
        cleanExpiredByDate(linkRepository.getExpiredByDateLinks());
        cleanExpiredByLimit(linkRepository.getExpiredByLimitLinks());
    }

    private void cleanExpiredByLimit(List<LinkEntity> expiredByLimitLinks) {
        if (expiredByLimitLinks.isEmpty()) {
            return;
        }
        storages.getLinkRepository().clear(expiredByLimitLinks.stream().map(LinkEntity::getShortLink).toList());
    }

    private void cleanExpiredByDate(List<LinkEntity> expiredByDateLinks) {
        if (expiredByDateLinks.isEmpty()) {
            return;
        }
        storages.getLinkRepository().clear(expiredByDateLinks.stream().map(LinkEntity::getShortLink).toList());
        ServiceFabric.getNotificationService().createNotifications(expiredByDateLinks, NotificationType.EXPIRED_DATE);
    }



}
