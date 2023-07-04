package ru.isands.BackendTask.validator;

import ru.isands.BackendTask.dto.FridgeDto;
import ru.isands.BackendTask.exception.EntityNotValidException;

import static ru.isands.BackendTask.validator.ModelValidator.*;

public class FridgeValidator {
    public static void isFridgeValid(FridgeDto fridgeDto) {
        isModelValid(fridgeDto);
        if (stringChecker(fridgeDto.getCompressorType())) {
            throw new EntityNotValidException("Model fridge compressor type isn't valid.");
        }
        if (intChecker(fridgeDto.getNumberOfDoors())) {
            throw new EntityNotValidException("Model fridge number of doors isn't valid.");
        }
    }
}
