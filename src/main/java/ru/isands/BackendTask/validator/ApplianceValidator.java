package ru.isands.BackendTask.validator;

import ru.isands.BackendTask.dto.inputDto.ApplianceInputDto;
import ru.isands.BackendTask.exception.EntityNotValidException;

public class ApplianceValidator {
    public static void isApplianceValid(ApplianceInputDto applianceDto) {
        if (stringChecker(applianceDto.getName())) {
            throw new EntityNotValidException("Appliance name isn't valid.");
        }
        if (stringChecker(applianceDto.getCountry())) {
            throw new EntityNotValidException("Appliance country isn't valid.");
        }
        if (stringChecker(applianceDto.getManufacturer())) {
            throw new EntityNotValidException("Appliance manufacturer isn't valid.");
        }
        if (applianceDto.getOnlineOrder() == null) {
            throw new EntityNotValidException("Appliance online order can't be null.");
        }
        if (applianceDto.getInstallment() == null) {
            throw new EntityNotValidException("Appliance online order can't be null.");
        }
    }

    private static boolean stringChecker(String string) {
        return string == null || string.isEmpty() || string.trim().isEmpty();
    }
}
