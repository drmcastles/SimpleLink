package ru.mifi.stepan.shortlink.view.menu.command;
import java.util.List;
import ru.mifi.stepan.shortlink.view.menu.AbstractMenu;
import ru.mifi.stepan.shortlink.view.menu.IMenu;
import ru.mifi.stepan.shortlink.view.menu.option.MenuOption;

/**
 * Абстрактный класс для меню по которым выборка не отображается, но
 * появляется возможность получения команд от пользователя или отображения дополнительной информации
 */
public abstract class CommandMenu extends AbstractMenu {
    private final IMenu targetMenu;

    @Override
    protected List<MenuOption> getOption() {
        return null;
    }

    public CommandMenu(IMenu targetMenu) {
        this.targetMenu = targetMenu;
    }
    @Override
    public String getName() {
        return null;
    }

    @Override
    protected void postCreate() {
        targetMenu.show(getCommandResult());
    }

    protected abstract String getCommandResult();
}
