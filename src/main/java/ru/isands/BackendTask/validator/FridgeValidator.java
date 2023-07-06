package ru.isands.BackendTask.validator;

import ru.isands.BackendTask.dto.inputDto.FridgeInputDto;
import ru.isands.BackendTask.exception.EntityNotValidException;

import static ru.isands.BackendTask.validator.ModelValidator.*;

public class FridgeValidator {
    public static void isFridgeValid(FridgeInputDto fridgeInputDto) {
        isModelValid(fridgeInputDto);
        if (stringChecker(fridgeInputDto.getCompressorType())) {
            throw new EntityNotValidException("Model fridge compressor type isn't valid.");
        }
        if (intChecker(fridgeInputDto.getNumberOfDoors())) {
            throw new EntityNotValidException("Model fridge number of doors isn't valid.");
        }
    }
}
