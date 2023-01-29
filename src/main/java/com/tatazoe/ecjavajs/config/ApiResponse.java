package com.tatazoe.ecjavajs.config;

import java.time.LocalDateTime;

public class ApiResponse {
    private final boolean success;
    private final String message;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    // 固定化できる物は get Method を定義すれば取得してくれそう。getYahoo にすると、Key が yahoo になる。
    public String getTimeStamp() {
        return LocalDateTime.now().toString();
    }
}
