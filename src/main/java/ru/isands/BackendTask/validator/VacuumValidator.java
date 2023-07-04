package ru.isands.BackendTask.validator;

import ru.isands.BackendTask.dto.TelevisionDto;
import ru.isands.BackendTask.dto.VacuumDto;
import ru.isands.BackendTask.exception.EntityNotValidException;

import static ru.isands.BackendTask.validator.ModelValidator.*;

public class VacuumValidator {
    public static void isTelevisionValid(VacuumDto vacuumDto) {
        isModelValid(vacuumDto);
        if (floatChecker(vacuumDto.getDustBagVolume())) {
            throw new EntityNotValidException("Model vacuum dust bug isn't valid.");
        }
        if (intChecker(vacuumDto.getNumberOfModes())) {
            throw new EntityNotValidException("Model vacuum modes number isn't valid.");
        }
    }
}
