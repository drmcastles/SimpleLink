package ru.mifi.stepan.shortlink.dto;

import java.time.LocalDateTime;

public class LimitExpiredInfoDto {
    private final int limit;
    private final LocalDateTime expiredAt;

    public LimitExpiredInfoDto(int limit, LocalDateTime expiredAt) {
        this.limit = limit;
        this.expiredAt = expiredAt;
    }

    public int getLimit() {
        return limit;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }
}
