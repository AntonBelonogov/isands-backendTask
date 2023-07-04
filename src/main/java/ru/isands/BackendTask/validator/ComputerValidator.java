package ru.isands.BackendTask.validator;

import ru.isands.BackendTask.dto.ComputerDto;
import ru.isands.BackendTask.exception.EntityNotValidException;

import static ru.isands.BackendTask.validator.ModelValidator.*;

public class ComputerValidator {
    public static void isComputerValid(ComputerDto computerDto) {
        isModelValid(computerDto);
        if (stringChecker(computerDto.getComputerCategory())) {
            throw new EntityNotValidException("Model computer category isn't valid.");
        }
        if (stringChecker(computerDto.getProcessorType())) {
            throw new EntityNotValidException("Model processor type isn't valid.");
        }
    }
}
