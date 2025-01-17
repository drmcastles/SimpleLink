package ru.mifi.stepan.shortlink.data.impl;

import ru.mifi.stepan.shortlink.data.ILinkRepository;
import ru.mifi.stepan.shortlink.data.entity.LinkEntity;
import ru.mifi.stepan.shortlink.dto.LinkInfoDto;
import ru.mifi.stepan.shortlink.exception.LimitExpiredException;
import ru.mifi.stepan.shortlink.exception.LinkNotFoundException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class LinkStorage implements ILinkRepository {
    private final HashMap<String, LinkEntity> linkRepository = new HashMap<String, LinkEntity>();

    @Override
    public boolean existsActiveByUserId(String link, UUID userId) {
        return linkRepository.values().stream().anyMatch(entity -> entity.isActive()
                && entity.getLink().equals(link)
                && entity.getUserId().equals(userId)
        );
    }

    @Override
    public List<LinkEntity> getActiveLinksByUserId(UUID userId) {
        return linkRepository.values().stream().filter(entity -> entity.isActive()
                && entity.getUserId().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public String getActiveLinkOrFail(String shortLink) {
        LinkEntity linkEntity = linkRepository.get(shortLink);
        if (linkEntity == null || !linkEntity.isActive()) {
            throw new LinkNotFoundException("Ссылка " + shortLink + " не активна");
        }
        if (linkEntity.getLimit() == 0) {
            throw new LimitExpiredException("Превышено кол-во переходов по ссылке");
        }
        return linkEntity.getLink();
    }

    @Override
    public List<LinkEntity> getExpiredByDateLinks() {
        return linkRepository.values().stream().filter(entity -> entity.isActive()
                        && entity.getExpiredAt().compareTo(LocalDateTime.now()) < 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<LinkEntity> getExpiredByLimitLinks() {
        return linkRepository.values().stream().filter(entity -> entity.isActive()
                        && entity.getLimit() == 0)
                .collect(Collectors.toList());
    }

    @Override
    public void setInActive(Collection<String> links) {
        links.forEach(key -> linkRepository.get(key).setActive(false));
    }

    @Override
    public void clear(Collection<String> links) {
        links.forEach(link -> linkRepository.remove(link));
    }

    @Override
    public boolean existsShortLink(String shortLink) {
        return linkRepository.containsKey(shortLink);
    }

    @Override
    public void save(LinkEntity linkEntity) {
        linkRepository.put(linkEntity.getShortLink(), linkEntity);
    }

    @Override
    public void decreaseLimit(String shortLink) {
        LinkEntity linkEntity = linkRepository.get(shortLink);
        linkEntity.setLimit(linkEntity.getLimit() - 1);
        if (linkEntity.getLimit() == 0) {
            linkEntity.setActive(false);
        }
    }

    @Override
    public LinkEntity getLinkEntity(String shortLink) {
        return linkRepository.get(shortLink);
    }
}
