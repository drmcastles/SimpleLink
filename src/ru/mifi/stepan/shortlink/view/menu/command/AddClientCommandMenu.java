package ru.mifi.stepan.shortlink.view.menu.command;


import ru.mifi.stepan.shortlink.view.menu.IMenu;
import ru.mifi.stepan.shortlink.view.utils.ConsoleUtil;

public class AddClientCommandMenu extends CommandMenu {
    public AddClientCommandMenu(IMenu targetMenu) {
        super(targetMenu);
    }

    @Override
    protected String getCommandResult() {
        return "Client was successfully added";
    }

    @Override
    protected void init(String initialText) {
        System.out.println("Enter client name:");
        String name = ConsoleUtil.readFromConsole();
        if (!name.isBlank()) {

        }
    }
}
