package ru.isands.BackendTask.validator;

import ru.isands.BackendTask.dto.ModelDto;
import ru.isands.BackendTask.exception.EntityNotValidException;

import java.math.BigDecimal;

public class ModelValidator {
    public static void isModelValid(ModelDto modelDto) {
        if (stringChecker(modelDto.getName())) {
            throw new EntityNotValidException("Model name isn't valid.");
        }
        if (stringChecker(modelDto.getSerialNumber())) {
            throw new EntityNotValidException("Model serial number isn't valid.");
        }
        if (stringChecker(modelDto.getColor())) {
            throw new EntityNotValidException("Model color isn't valid.");
        }
        if (floatChecker(modelDto.getSize())) {
            throw new EntityNotValidException("Model color isn't valid.");
        }
        if (bigDecimalChecker(modelDto.getPrice())) {
            throw new EntityNotValidException("Model price isn't valid.");
        }
        if (modelDto.getIsAvailable() == null) {
            throw new EntityNotValidException("Model is available isn't valid.");
        }
    }

    public static boolean stringChecker(String string) {
        return string == null || string.isEmpty() || string.trim().isEmpty();
    }

    public static boolean floatChecker(Float number) {
        return number == null || number < 0;
    }

    public static boolean bigDecimalChecker(BigDecimal number) {
        return number == null || number.floatValue() < BigDecimal.ZERO.floatValue();
    }
    public static boolean intChecker(Integer number) {
        return number == null || number < 0;
    }
}
