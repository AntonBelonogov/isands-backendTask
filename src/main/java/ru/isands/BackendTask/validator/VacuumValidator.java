package ru.isands.BackendTask.validator;

import ru.isands.BackendTask.dto.inputDto.VacuumInputDto;
import ru.isands.BackendTask.exception.EntityNotValidException;

import static ru.isands.BackendTask.validator.ModelValidator.*;

public class VacuumValidator {
    public static void isTelevisionValid(VacuumInputDto vacuumInputDto) {
        isModelValid(vacuumInputDto);
        if (floatChecker(vacuumInputDto.getDustBagVolume())) {
            throw new EntityNotValidException("Model vacuum dust bug isn't valid.");
        }
        if (intChecker(vacuumInputDto.getNumberOfModes())) {
            throw new EntityNotValidException("Model vacuum modes number isn't valid.");
        }
    }
}
