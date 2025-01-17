package ru.mifi.stepan.shortlink.view.menu.command;


import ru.mifi.stepan.shortlink.view.menu.IMenu;

public class CashFirstStrategyMenu extends CommandMenu {

    public CashFirstStrategyMenu(IMenu targetMenu) {
        super(targetMenu);
    }

    @Override
    protected String getCommandResult() {
        return "Strategy CASH_FIRST was chosen";
    }

    @Override
    protected void init(String initialText) {

    }
}
