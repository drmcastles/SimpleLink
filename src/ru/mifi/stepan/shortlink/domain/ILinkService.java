package ru.mifi.stepan.shortlink.domain;

import ru.mifi.stepan.shortlink.dto.CreateLinkDto;
import ru.mifi.stepan.shortlink.dto.LimitExpiredInfoDto;
import ru.mifi.stepan.shortlink.dto.LinkInfoDto;
import ru.mifi.stepan.shortlink.dto.ResultDto;

import java.util.Collection;

public interface ILinkService {
    String getOriginalLink(String shortLink);
    LimitExpiredInfoDto decreaseLimit(String shortLink);

    Collection<LinkInfoDto> showLinks();

    ResultDto registerNewLink(CreateLinkDto createLinkDto);

    ResultDto updateByShortLink(String shortLink, CreateLinkDto createLinkDto);

    ResultDto deleteByShortLink(String shortLink);
}
