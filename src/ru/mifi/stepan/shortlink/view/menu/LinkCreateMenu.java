package ru.mifi.stepan.shortlink.view.menu;


import ru.mifi.stepan.shortlink.domain.ILinkService;
import ru.mifi.stepan.shortlink.domain.IUserService;
import ru.mifi.stepan.shortlink.domain.ServiceFabric;
import ru.mifi.stepan.shortlink.dto.CreateLinkDto;
import ru.mifi.stepan.shortlink.dto.ResultDto;
import ru.mifi.stepan.shortlink.view.menu.command.CommandMenu;

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
        int limits = getLimits();

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

    private int getLimits() {
        System.out.println("Введите лимит переходов");
        try {
            int result = Integer.valueOf(readFromConsole());
            if (result > 0 && result < 100) {
                return result;
            }
        } catch (NumberFormatException e) {
            // обработка ниже так же как и в случае отсутствия ошибок
        }
        System.out.println("Необходимо ввести число от 0 до 100");
        return getLimits();
    }
}
