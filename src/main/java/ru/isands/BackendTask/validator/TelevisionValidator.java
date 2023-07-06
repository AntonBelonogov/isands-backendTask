package ru.isands.BackendTask.validator;

import ru.isands.BackendTask.dto.TelevisionDto;
import ru.isands.BackendTask.dto.inputDto.TelevisionInputDto;
import ru.isands.BackendTask.exception.EntityNotValidException;

import static ru.isands.BackendTask.validator.ModelValidator.isModelValid;
import static ru.isands.BackendTask.validator.ModelValidator.stringChecker;

public class TelevisionValidator {
    public static void isTelevisionValid(TelevisionInputDto televisionInputDto) {
        isModelValid(televisionInputDto);
        if (stringChecker(televisionInputDto.getTelevisionCategory())) {
            throw new EntityNotValidException("Model television category isn't valid.");
        }
        if (stringChecker(televisionInputDto.getTelevisionTechnology())) {
            throw new EntityNotValidException("Model television technology isn't valid.");
        }
    }
}
