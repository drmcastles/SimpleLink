package ru.mifi.stepan.shortlink.dto;

public class ResultDto {
    public static final ResultDto ALREADY_EXISTS = new ResultDto(false, "Available link already exists");
    public static final ResultDto LINK_NOT_FOUND = new ResultDto(false, "Ссылка не найдена");
    public static final ResultDto NO_RIGHTS = new ResultDto(false, "У пользователя нет прав на выполнение данной операции");

    private final boolean success;
    private final String description;

    private ResultDto(boolean success, String description) {
        this.success = success;
        this.description = description;
    }

    public ResultDto (String description) {
        success = true;
        this.description = description;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getDescription() {
        return description;
    }
}
