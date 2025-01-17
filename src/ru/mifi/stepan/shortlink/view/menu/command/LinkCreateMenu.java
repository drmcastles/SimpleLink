package ru.mifi.stepan.shortlink.view.menu.command;


import ru.mifi.stepan.shortlink.domain.ILinkService;
import ru.mifi.stepan.shortlink.domain.IUserService;
import ru.mifi.stepan.shortlink.domain.ServiceFabric;
import ru.mifi.stepan.shortlink.dto.CreateLinkDto;
import ru.mifi.stepan.shortlink.dto.ResultDto;
import ru.mifi.stepan.shortlink.view.menu.IMenu;
import ru.mifi.stepan.shortlink.view.menu.command.CommandMenu;
import ru.mifi.stepan.shortlink.view.utils.ConsoleUtil;

import java.util.UUID;

import static ru.mifi.stepan.shortlink.view.utils.ConsoleUtil.readFromConsole;

public class LinkCreateMenu extends CommandMenu {
    public static final String ADD_LINK = "Добавление длинной ссылки";
    public static final String LINK_ADDED = "Ссылка %s зарегистрирована для пользователя %s";

    private final IMenu mainMenu;

    public LinkCreateMenu(IMenu mainMenu) {
        super(mainMenu);
        this.mainMenu = mainMenu;
    }


    @Override
    public String getName() {
        return ADD_LINK;
    }

    @Override
    protected String getCommandResult() {
        System.out.println("Введите ссылку");
        String link = readFromConsole();
        int limits = ConsoleUtil.getLimits();

        IUserService userService = ServiceFabric.getUserService();
        ILinkService linkService = ServiceFabric.getLinkService();

        UUID user = getUser(userService);

        ResultDto resultDto = linkService.registerNewLink(new CreateLinkDto(link, limits, user));

        if (!resultDto.isSuccess()) {
            return "Ссылка не создана " + resultDto.getDescription();
        }
        return String.format(LINK_ADDED, resultDto.getDescription() , user);
    }

    private static UUID getUser(IUserService userService) {
        UUID userId = ServiceFabric.getUserManagement().getUserId();
        if (userId == null) {
            userId = userService.createUser();
            ServiceFabric.getUserManagement().setUserId(userId);
        }
        return userId;
    }
}
