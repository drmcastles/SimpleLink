package ru.mifi.stepan.shortlink.view.menu;

import ru.mifi.stepan.shortlink.domain.ServiceFabric;
import ru.mifi.stepan.shortlink.dto.CreateLinkDto;
import ru.mifi.stepan.shortlink.dto.ResultDto;
import ru.mifi.stepan.shortlink.view.menu.command.CommandMenu;
import ru.mifi.stepan.shortlink.view.utils.ConsoleUtil;

public class LinkEditMenu extends CommandMenu {
    public LinkEditMenu(IMenu parentMenu) {
        super(parentMenu);
    }

    @Override
    protected String getCommandResult() {
        System.out.println("Укажите короткую ссылку:");
        String shortLink = ConsoleUtil.readFromConsole();
        System.out.println("Укажите ссылку перехода:");
        String link = ConsoleUtil.readFromConsole();
        System.out.println("Укажите лимит переходов:");
        int limit = ConsoleUtil.getLimits();

        ResultDto resultDto = ServiceFabric.getLinkService().updateByShortLink(shortLink, new CreateLinkDto(
                link,
                limit,
                ServiceFabric.getUserManagement().getUserId()));
        return resultDto.getDescription();
    }
}
