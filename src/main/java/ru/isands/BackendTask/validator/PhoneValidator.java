package ru.isands.BackendTask.validator;

import ru.isands.BackendTask.dto.PhoneDto;
import ru.isands.BackendTask.dto.inputDto.PhoneInputDto;
import ru.isands.BackendTask.exception.EntityNotValidException;

import static ru.isands.BackendTask.validator.ModelValidator.intChecker;
import static ru.isands.BackendTask.validator.ModelValidator.isModelValid;

public class PhoneValidator {
    public static void isPhoneValid(PhoneInputDto phoneInputDto) {
        isModelValid(phoneInputDto);
        if (intChecker(phoneInputDto.getMemory())) {
            throw new EntityNotValidException("Model phone memory isn't valid.");
        }
        if (intChecker(phoneInputDto.getNumberOfCameras())) {
            throw new EntityNotValidException("Model phone number of cameras isn't valid.");
        }
    }
}
