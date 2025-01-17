package ru.mifi.stepan.shortlink.domain;

import ru.mifi.stepan.shortlink.data.Storages;
import ru.mifi.stepan.shortlink.domain.impl.*;

public class ServiceFabric {
    private static final Storages STORAGES = new Storages();
    private static final IUserService userService = new UserService(STORAGES);
    private static final ICleanManager cleanManagerService = new CleanManagerService(STORAGES);

    private static final ILinkService linkService = new LinkService(STORAGES);
    private static final IUserManagement userManagement = new UserManagementService();
    private static final INotificationService notificationService = new NotificationService(STORAGES.getNotificationRepository());

    public static IUserService getUserService() {
        return userService;
    }

    public static ICleanManager getCleanManagerService() {
        return cleanManagerService;
    }

    public static IUserManagement getUserManagement() {
        return userManagement;
    }

    public static ILinkService getLinkService() {
        return linkService;
    }

    private ServiceFabric() {

    }

    public static INotificationService getNotificationService() {
        return notificationService;
    }
}
