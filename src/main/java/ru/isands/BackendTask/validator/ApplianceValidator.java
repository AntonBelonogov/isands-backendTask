package ru.isands.BackendTask.validator;

import ru.isands.BackendTask.dto.AppliancesDto;
import ru.isands.BackendTask.exception.EntityNotValidException;

public class ApplianceValidator {
    public static void isApplianceValid(AppliancesDto appliancesDto) {

        if (stringChecker(appliancesDto.getName())) {
            throw new EntityNotValidException("Appliance name isn't valid.");
        }
        if (stringChecker(appliancesDto.getCountry())) {
            throw new EntityNotValidException("Appliance country isn't valid.");
        }
        if (stringChecker(appliancesDto.getManufacturer())) {
            throw new EntityNotValidException("Appliance manufacturer isn't valid.");
        }
        if (appliancesDto.getOnlineOrder() == null) {
            throw new EntityNotValidException("Appliance online order can't be null.");
        }
        if (appliancesDto.getInstallment() == null) {
            throw new EntityNotValidException("Appliance online order can't be null.");
        }
    }

    private static boolean stringChecker(String string) {
       return string == null || string.isEmpty() || string.trim().isEmpty();
    }
}
