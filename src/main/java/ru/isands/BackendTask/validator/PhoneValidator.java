package ru.isands.BackendTask.validator;

import ru.isands.BackendTask.dto.PhoneDto;
import ru.isands.BackendTask.exception.EntityNotValidException;

import static ru.isands.BackendTask.validator.ModelValidator.intChecker;
import static ru.isands.BackendTask.validator.ModelValidator.isModelValid;

public class PhoneValidator {
    public static void isPhoneValid(PhoneDto phoneDto) {
        isModelValid(phoneDto);
        if (intChecker(phoneDto.getMemory())) {
            throw new EntityNotValidException("Model phone memory isn't valid.");
        }
        if (intChecker(phoneDto.getNumberOfCameras())) {
            throw new EntityNotValidException("Model phone number of cameras isn't valid.");
        }
    }
}
