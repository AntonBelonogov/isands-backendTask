package ru.isands.BackendTask.mapper;

import ru.isands.BackendTask.dto.AppliancesDto;
import ru.isands.BackendTask.model.Appliance;

import java.util.Collections;
import java.util.stream.Collectors;

public class ApplianceMapper {

    public static AppliancesDto toDto(Appliance appliance) {
        return AppliancesDto.builder()
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

    public static Appliance toAppliance(AppliancesDto appliancesDto) {
        Appliance appliance = new Appliance();
        appliance.setName(appliancesDto.getName());
        appliance.setCountry(appliancesDto.getCountry());
        appliance.setManufacturer(appliancesDto.getManufacturer());
        appliance.setOnlineOrder(appliancesDto.getOnlineOrder());
        appliance.setInstallment(appliancesDto.getInstallment());
        appliance.setModels(appliancesDto.getModels() != null ? appliancesDto.getModels()
                .stream()
                .map(ModelMapper::toModelFromModelInfo).
                collect(Collectors.toList()) : Collections.emptyList());
        return appliance;
    }

    public static Appliance updateAppliance(Appliance appliance, AppliancesDto appliancesDto) {
        appliance.setName(appliancesDto.getName() == null ?
                appliance.getName() : appliancesDto.getName());
        appliance.setCountry(appliancesDto.getCountry() == null ?
                appliance.getCountry() : appliancesDto.getCountry());
        appliance.setManufacturer(appliancesDto.getManufacturer() == null ?
                appliance.getManufacturer() : appliancesDto.getManufacturer());
        appliance.setOnlineOrder(appliancesDto.getOnlineOrder() == null ?
                appliance.getOnlineOrder() : appliancesDto.getOnlineOrder());
        appliance.setInstallment(appliancesDto.getInstallment() == null ?
                appliance.getInstallment() : appliancesDto.getInstallment());
        return appliance;
    }
}
