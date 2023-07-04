package ru.isands.BackendTask.validator;

import ru.isands.BackendTask.dto.TelevisionDto;
import ru.isands.BackendTask.exception.EntityNotValidException;

import static ru.isands.BackendTask.validator.ModelValidator.isModelValid;
import static ru.isands.BackendTask.validator.ModelValidator.stringChecker;

public class TelevisionValidator {
    public static void isTelevisionValid(TelevisionDto televisionDto) {
        isModelValid(televisionDto);
        if (stringChecker(televisionDto.getTelevisionCategory())) {
            throw new EntityNotValidException("Model television category isn't valid.");
        }
        if (stringChecker(televisionDto.getTelevisionTechnology())) {
            throw new EntityNotValidException("Model television technology isn't valid.");
        }
    }
}
