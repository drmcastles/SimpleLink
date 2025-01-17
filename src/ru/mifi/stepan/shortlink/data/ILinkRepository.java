package ru.mifi.stepan.shortlink.data;

import ru.mifi.stepan.shortlink.data.entity.LinkEntity;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ILinkRepository {
    boolean existsActiveByUserId(String link, UUID userId);

    List<LinkEntity> getActiveLinksByUserId(UUID userId);

    String getActiveLinkOrFail(String shortLink);

    List<LinkEntity> getExpiredByDateLinks();

    List<LinkEntity> getExpiredByLimitLinks();

    void setInActive(Collection<String> links);

    void clear(Collection<String> links);

    boolean existsShortLink(String shortLink);

    void save(LinkEntity linkEntity);

    void decreaseLimit(String shortLink);

    LinkEntity getLinkEntity(String shortLink);
}
