package ru.isands.BackendTask.mapper;

import ru.isands.BackendTask.dto.ApplianceDto;
import ru.isands.BackendTask.dto.inputDto.ApplianceInputDto;
import ru.isands.BackendTask.model.Appliance;

import java.util.Collections;
import java.util.stream.Collectors;

public class ApplianceMapper {

    public static ApplianceDto toDto(Appliance appliance) {
        return ApplianceDto.builder()
                .id(appliance.getId())
                .name(appliance.getName())
                .country(appliance.getCountry())
                .manufacturer(appliance.getManufacturer())
                .onlineOrder(appliance.getOnlineOrder())
                .installment(appliance.getInstallment())
                .models(appliance.getModels() != null ? appliance.getModels()
                        .stream()
                        .map(ModelMapper::toInfoDto)
                        .collect(Collectors.toList()) : Collections.emptyList())
                .build();
    }

    public static Appliance toAppliance(ApplianceInputDto applianceDto) {
        Appliance appliance = new Appliance();
        appliance.setName(applianceDto.getName());
        appliance.setCountry(applianceDto.getCountry());
        appliance.setManufacturer(applianceDto.getManufacturer());
        appliance.setOnlineOrder(applianceDto.getOnlineOrder());
        appliance.setInstallment(applianceDto.getInstallment());
        return appliance;
    }

    public static Appliance updateAppliance(Appliance appliance, ApplianceInputDto applianceInputDto) {
        appliance.setName(applianceInputDto.getName() == null ?
                appliance.getName() : applianceInputDto.getName());
        appliance.setCountry(applianceInputDto.getCountry() == null ?
                appliance.getCountry() : applianceInputDto.getCountry());
        appliance.setManufacturer(applianceInputDto.getManufacturer() == null ?
                appliance.getManufacturer() : applianceInputDto.getManufacturer());
        appliance.setOnlineOrder(applianceInputDto.getOnlineOrder() == null ?
                appliance.getOnlineOrder() : applianceInputDto.getOnlineOrder());
        appliance.setInstallment(applianceInputDto.getInstallment() == null ?
                appliance.getInstallment() : applianceInputDto.getInstallment());
        return appliance;
    }
}
