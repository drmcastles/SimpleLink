package ru.mifi.stepan.shortlink.view.menu.command;

import ru.mifi.stepan.shortlink.domain.ServiceFabric;
import ru.mifi.stepan.shortlink.dto.CreateLinkDto;
import ru.mifi.stepan.shortlink.dto.ResultDto;
import ru.mifi.stepan.shortlink.view.menu.IMenu;
import ru.mifi.stepan.shortlink.view.menu.UserMenu;
import ru.mifi.stepan.shortlink.view.utils.ConsoleUtil;

public class LinkDeleteMenu extends CommandMenu {
    public LinkDeleteMenu(IMenu parentMenu) {
        super(parentMenu);
    }

    @Override
    protected String getCommandResult() {
        System.out.println("Укажите короткую ссылку:");
        String shortLink = ConsoleUtil.readFromConsole();
        ResultDto resultDto = ServiceFabric.getLinkService().deleteByShortLink(shortLink);
        return resultDto.getDescription();
    }
}
