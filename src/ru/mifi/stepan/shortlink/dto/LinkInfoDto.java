package ru.mifi.stepan.shortlink.dto;

public class LinkInfoDto {
    private final String shortLink;
    private final String link;

    public LinkInfoDto(String shortLink, String link) {
        this.shortLink = shortLink;
        this.link = link;
    }

    public String getShortLink() {
        return shortLink;
    }

    public String getLink() {
        return link;
    }
}
