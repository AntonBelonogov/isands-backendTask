package ru.isands.BackendTask.mapper;

import ru.isands.BackendTask.dto.ApplianceModelDto;
import ru.isands.BackendTask.model.Appliance;

public class ModelAppliancesMapper {
    public static ApplianceModelDto toDto(Appliance appliance) {
        return ApplianceModelDto.builder()
                .id(appliance.getId())
                .name(appliance.getName())
                .country(appliance.getCountry())
                .manufacturer(appliance.getManufacturer())
                .onlineOrder(appliance.getOnlineOrder())
                .installment(appliance.getInstallment())
                .build();
    }
}
