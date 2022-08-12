package com.example.meli.commons.constants;

public enum ExceptionEnum {
    DELETE_INFO("Not found number key in cache redis"),
    DELETE_MESSAGE("DONT_DELETE"),
    DELETE_URL("https://httpstatuses.com/404"),
    URL("https://httpstatuses.com/409"),
    REDIS_INFO("Redis Server Disabled"),
    REDIS_MESSAGE("CONFLICT_REDIS_DISABLED"),
    RANDOM_INFO("Random parameter would cause overflow"),
    RANDOM_MESSAGE("CONFLICT_RANDOM_NOT_VALID");

    private final String data;

    ExceptionEnum(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}



