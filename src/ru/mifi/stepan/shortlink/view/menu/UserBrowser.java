package ru.mifi.stepan.shortlink.view.menu;

import ru.mifi.stepan.shortlink.domain.ServiceFabric;
import ru.mifi.stepan.shortlink.view.menu.command.CommandMenu;

public class UserBrowser extends CommandMenu {
    public UserBrowser(IMenu parentMenu) {
        super(parentMenu);
    }

    @Override
    protected String getCommandResult() {
        StringBuilder result = new StringBuilder();
        ServiceFabric.getUserService().getAllUsers().forEach(user -> {
                    result.append("\n\r");
                    result.append(user);
                }
        );
        return result.toString();
    }
}
