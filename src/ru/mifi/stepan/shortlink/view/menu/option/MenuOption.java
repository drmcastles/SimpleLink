package ru.mifi.stepan.shortlink.view.menu.option;

import ru.mifi.stepan.shortlink.view.menu.IMenu;

public class MenuOption {
    String command;

    String description;

    IMenu linkMenu;

    public MenuOption(String command, String description, IMenu linkMenu) {
        this.command = command;
        this.description = description;
        this.linkMenu = linkMenu;
    }

    public void print() {
        System.out.println(command + "\t" + description);
    }

    public String getCommand() {
        return command;
    }

    public IMenu getLinkedMenu() {
        return linkMenu;
    }
}
