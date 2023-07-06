package ru.isands.BackendTask.validator;

import ru.isands.BackendTask.dto.inputDto.ComputerInputDto;
import ru.isands.BackendTask.exception.EntityNotValidException;

import static ru.isands.BackendTask.validator.ModelValidator.isModelValid;
import static ru.isands.BackendTask.validator.ModelValidator.stringChecker;

public class ComputerValidator {
    public static void isComputerValid(ComputerInputDto computerInputDto) {
        isModelValid(computerInputDto);
        if (stringChecker(computerInputDto.getComputerCategory())) {
            throw new EntityNotValidException("Model computer category isn't valid.");
        }
        if (stringChecker(computerInputDto.getProcessorType())) {
            throw new EntityNotValidException("Model processor type isn't valid.");
        }
    }
}
