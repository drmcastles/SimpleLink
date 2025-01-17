package ru.mifi.stepan.shortlink.view.menu;


import ru.mifi.stepan.shortlink.domain.ServiceFabric;
import ru.mifi.stepan.shortlink.view.constants.ErrorConstants;
import ru.mifi.stepan.shortlink.view.menu.option.MenuOption;
import ru.mifi.stepan.shortlink.view.utils.ConsoleUtil;

import java.util.List;

/**
 * Абстрактный класс для меню по которым отображается выборка пукнтов
 */
public abstract class AbstractMenu implements IMenu{
    public void show(String initialText) {
        preCreate();
        init(initialText);
        printNotification();
        printOption();
        postCreate();
    }

    protected void preCreate() {

    }

    protected void printNotification() {

    }

    protected void printOption() {
        if (optionExists()) {
            for (int i = 0; i < getOption().size(); i++) {
                getOption().get(i).print();
            }
        }
    }

    private boolean optionExists() {
        return getOption() != null && getOption().size() > 0;
    }

    protected abstract List<MenuOption> getOption();
    protected void postCreate() {
        ServiceFabric.getCleanManagerService().monitor();
        String command = ConsoleUtil.readFromConsole();
        MenuOption selectedOption = findOption(command);
        if (selectedOption == null) {
            show(ErrorConstants.WRONG_OPTION);
            return;
        }
        IMenu linkedMenu = selectedOption.getLinkedMenu();
        if (linkedMenu != null) {
            linkedMenu.show(linkedMenu.getName());
        }
    }

    private MenuOption findOption(String command) {
        if (optionExists()) {
            for (int i = 0; i < getOption().size(); i++) {
                if (command.equals(getOption().get(i).getCommand())) {
                    return getOption().get(i);
                }
            }
        }
        return null;
    }
    protected void init(String initialText) {
        ConsoleUtil.clear();
        if (initialText != null) {
            System.out.println(initialText);
        }
    }
}
