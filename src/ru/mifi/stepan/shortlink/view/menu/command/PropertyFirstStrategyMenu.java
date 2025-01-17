package ru.mifi.stepan.shortlink.view.menu.command;

import ru.mifi.stepan.shortlink.view.menu.IMenu;

public class PropertyFirstStrategyMenu extends CommandMenu {

    public PropertyFirstStrategyMenu(IMenu targetMenu) {
        super(targetMenu);
    }

    @Override
    protected String getCommandResult() {
        return "Strategy PROPERTY_FIRST was successfully chosen";
    }

    @Override
    protected void init(String initialText) {

    }
}
