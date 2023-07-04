package ru.isands.BackendTask.exception;

public class EntityNotValidException extends RuntimeException {
    public EntityNotValidException(String message) {
        super(message);
    }
}
