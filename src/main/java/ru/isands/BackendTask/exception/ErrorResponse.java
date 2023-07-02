package ru.isands.BackendTask.exception;

import io.swagger.v3.oas.annotations.media.Schema;

public class ErrorResponse {
    @Schema(description = "Сообщение ошибки.")
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
