package ru.mifi.stepan.shortlink.data;

import ru.mifi.stepan.shortlink.data.impl.LinkStorage;
import ru.mifi.stepan.shortlink.data.impl.NotificationStorage;
import ru.mifi.stepan.shortlink.data.impl.UserStorage;

public class Storages {

    private final IUserRepository userRepository = new UserStorage();
    private final INotificationRepository notificationRepository = new NotificationStorage();

    private final ILinkRepository linkRepository = new LinkStorage();
    public IUserRepository getUserRepository() {
        return userRepository;
    }
    public INotificationRepository getNotificationRepository() {
        return notificationRepository;
    }
    public ILinkRepository getLinkRepository() {
        return linkRepository;
    }
}
