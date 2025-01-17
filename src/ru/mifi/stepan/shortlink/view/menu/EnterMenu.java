package ru.mifi.stepan.shortlink.view.menu;

import ru.mifi.stepan.shortlink.domain.ServiceFabric;
import ru.mifi.stepan.shortlink.view.menu.command.CommandMenu;
import ru.mifi.stepan.shortlink.view.utils.ConsoleUtil;

import java.util.UUID;

public class EnterMenu extends CommandMenu {

    private final IMenu errorMenu;

    public EnterMenu(IMenu targetMenu, IMenu errorMenu) {
        super(targetMenu);
        this.errorMenu = errorMenu;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    protected String getCommandResult() {
        try {
            System.out.println("Введите UUID пользователя:");
            UUID userId = UUID.fromString(ConsoleUtil.readFromConsole());
            ServiceFabric.getUserService().check(userId);
            ServiceFabric.getUserManagement().setUserId(userId);
        } catch (Exception e) {
            errorMenu.show("Пользователь не найден");
        }
        return "";
    }
}
