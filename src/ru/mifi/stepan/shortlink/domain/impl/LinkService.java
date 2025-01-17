package ru.mifi.stepan.shortlink.domain.impl;

import ru.mifi.stepan.shortlink.data.Storages;
import ru.mifi.stepan.shortlink.data.entity.LinkEntity;
import ru.mifi.stepan.shortlink.data.entity.NotificationType;
import ru.mifi.stepan.shortlink.domain.ILinkService;
import ru.mifi.stepan.shortlink.domain.ServiceFabric;
import ru.mifi.stepan.shortlink.dto.CreateLinkDto;
import ru.mifi.stepan.shortlink.dto.LimitExpiredInfoDto;
import ru.mifi.stepan.shortlink.dto.LinkInfoDto;
import ru.mifi.stepan.shortlink.dto.ResultDto;
import ru.mifi.stepan.shortlink.exception.LinkNotFoundException;
import ru.mifi.stepan.shortlink.property.SystemProperty;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LinkService implements ILinkService {

    private final Storages storages;

    public LinkService(Storages storages) {
        this.storages = storages;
    }

    @Override
    public String getOriginalLink(String shortLink) {
        String link = storages.getLinkRepository().getActiveLinkOrFail(shortLink);
        if (link.isBlank()) {
            throw new LinkNotFoundException("Ссылки " + shortLink + " не существует или она неактивна");
        }
        return link;
    }

    @Override
    public LimitExpiredInfoDto decreaseLimit(String shortLink) {
        ServiceFabric.getCleanManagerService().monitor();
        LinkEntity linkEntity = storages.getLinkRepository().getLinkEntity(shortLink);
        if (linkEntity.getLimit() > 0) {
            if (linkEntity.getLimit() == 1) {
                storages.getLinkRepository().setInActive(List.of(shortLink));
                ServiceFabric.getNotificationService().createNotifications(List.of(linkEntity), NotificationType.EXPIRED_LIMIT);
            }
            storages.getLinkRepository().decreaseLimit(shortLink);
        }
        return new LimitExpiredInfoDto(linkEntity.getLimit(), linkEntity.getExpiredAt());
    }

    @Override
    public Collection<LinkInfoDto> showLinks() {
        UUID userId = ServiceFabric.getUserManagement().getUserId();
        if (userId == null) {
            return null;
        }
        List<LinkEntity> activeLinks = storages.getLinkRepository().getActiveLinksByUserId(userId);
        return activeLinks.stream().map(linkEntity -> new LinkInfoDto(linkEntity.getShortLink(),linkEntity.getLink())).collect(Collectors.toList());
    }

    @Override
    public ResultDto registerNewLink(CreateLinkDto createLinkDto) {
        if (storages.getLinkRepository().existsActiveByUserId(createLinkDto.getLink(), createLinkDto.getUserId())) {
            return ResultDto.ALREADY_EXISTS;
        }
        int i = 0;
        String shortLink = generateShortLink(createLinkDto, i++);
        while (storages.getLinkRepository().existsShortLink(SystemProperty.host + shortLink)) {
            shortLink = generateShortLink(createLinkDto, i++);
        }
        storages.getLinkRepository().save(
                new LinkEntity(createLinkDto.getLink(),
                        SystemProperty.host + shortLink,
                        createLinkDto.getUserId(),
                        createLinkDto.getLimits())
        );
        ServiceFabric.getUserManagement().setUserId(createLinkDto.getUserId());
        return new ResultDto(SystemProperty.host + shortLink);
    }

    @Override
    public ResultDto updateByShortLink(String shortLink, CreateLinkDto createLinkDto) {
        LinkEntity linkEntity = storages.getLinkRepository().getLinkEntity(shortLink);
        if (linkEntity == null) {
            return ResultDto.LINK_NOT_FOUND;
        }
        linkEntity.setLimit(createLinkDto.getLimits());
        linkEntity.setLink(createLinkDto.getLink());
        return new ResultDto("Ссылка " + shortLink + " успешно отредактирована");
    }

    @Override
    public ResultDto deleteByShortLink(String shortLink) {
        UUID userId = ServiceFabric.getUserManagement().getUserId();
        if (userId == null) {
            return ResultDto.NO_RIGHTS;
        }
        LinkEntity linkEntity = storages.getLinkRepository().getLinkEntity(shortLink);
        if (linkEntity == null) {
            return ResultDto.LINK_NOT_FOUND;
        }
        if (!linkEntity.getUserId().equals(userId)) {
            return ResultDto.NO_RIGHTS;
        }
        storages.getLinkRepository().clear(List.of(shortLink));
        return new ResultDto("Ссылка " + shortLink + " успешно удалена");
    }

    private String generateShortLink(CreateLinkDto createLinkDto, int salt) {
        int hash = new StringBuilder()
                .append(createLinkDto.getUserId())
                .append("_")
                .append(createLinkDto.getLink())
                .append(salt)
                .toString()
                .hashCode();
        return Integer.toString(hash, 32).replace('-','m');
    }
}
