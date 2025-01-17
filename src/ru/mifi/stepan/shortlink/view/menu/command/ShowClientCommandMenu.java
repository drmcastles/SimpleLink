package ru.mifi.stepan.shortlink.view.menu.command;

import ru.mifi.stepan.shortlink.view.menu.EnterMenu;

public class ShowClientCommandMenu extends CommandMenu {
    public ShowClientCommandMenu(EnterMenu enterMenu) {
        super(enterMenu);
    }

    @Override
    protected String getCommandResult() {
        return null;
    }

    @Override
    protected void init(String initialText) {
//        ClientDataBase.showAll();
//        System.out.println("Press any key");
//        try {
//            System.in.read();
//        } catch (IOException e) {
//
//        }
    }
}
