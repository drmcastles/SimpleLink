package ru.mifi.stepan.shortlink.view.menu;

import ru.mifi.stepan.shortlink.domain.ServiceFabric;
import ru.mifi.stepan.shortlink.dto.LinkInfoDto;
import ru.mifi.stepan.shortlink.view.menu.command.CommandMenu;

import java.util.Collection;

public class LinkShowMenu extends CommandMenu {

    private static final String LINK_PATTERN = "%s\t%s" ;

    public LinkShowMenu(IMenu parentMenu) {
        super(parentMenu);
    }

    @Override
    protected String getCommandResult() {
        Collection<LinkInfoDto> linkInfoDtos = ServiceFabric.getLinkService().showLinks();
        StringBuilder result = new StringBuilder();
        linkInfoDtos.forEach(linkInfoDto -> {
            result.append(String.format(LINK_PATTERN, linkInfoDto.getLink(), linkInfoDto.getShortLink()));
            result.append("\n\r");
            result.append("______________________________________________________________________________");
        });
        return result.toString();
    }
}
