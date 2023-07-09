package ru.isands.BackendTask.mapper;

import ru.isands.BackendTask.dto.ApplianceDto;
import ru.isands.BackendTask.dto.ApplianceModelDto;
import ru.isands.BackendTask.dto.inputDto.ApplianceInputDto;
import ru.isands.BackendTask.model.Appliance;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
                .applianceAttributes(toDtoAttribute(appliance.getApplianceAttributes()))
                .models(appliance.getModels() != null ? appliance.getModels()
                        .stream()
                        .map(ModelMapper::toInfoDto)
                        .collect(Collectors.toList()) : Collections.emptyList())
                .build();
    }

    public static ApplianceModelDto toModelDto(Appliance appliance) {
        return ApplianceModelDto.builder()
                .id(appliance.getId())
                .name(appliance.getName())
                .country(appliance.getCountry())
                .manufacturer(appliance.getManufacturer())
                .onlineOrder(appliance.getOnlineOrder())
                .installment(appliance.getInstallment())
                .build();
    }

    public static Appliance toAppliance(ApplianceInputDto applianceInputDto) {
        Appliance appliance = new Appliance();
        appliance.setName(applianceInputDto.getName());
        appliance.setCountry(applianceInputDto.getCountry());
        appliance.setManufacturer(applianceInputDto.getManufacturer());
        appliance.setOnlineOrder(applianceInputDto.getOnlineOrder());
        appliance.setInstallment(applianceInputDto.getInstallment());
        appliance.setApplianceAttributes(applianceInputDto.getApplianceAttributes() != null ?
                applianceInputDto.getApplianceAttributes() : Collections.emptyMap());
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
        appliance.setApplianceAttributes(applianceInputDto.getApplianceAttributes() == null ?
                appliance.getApplianceAttributes() : applianceInputDto.getApplianceAttributes());
        return appliance;
    }

    private static Map<String, String> toDtoAttribute(Map<String,Object> stringObjectMap) {
        Map<String, String> stringStringMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
            if (entry.getValue() instanceof Integer) {
                stringStringMap.put(entry.getKey(), "Integer");
            }
            if (entry.getValue() instanceof Float) {
                stringStringMap.put(entry.getKey(), "Float");
            }
            if (entry.getValue() instanceof String) {
                stringStringMap.put(entry.getKey(), "String");
            }
            if (entry.getValue() instanceof Boolean) {
                stringStringMap.put(entry.getKey(), "Boolean");
            }
            if (entry.getValue() instanceof Long) {
                stringStringMap.put(entry.getKey(), "Long");
            }
            if (entry.getValue() instanceof Double) {
                stringStringMap.put(entry.getKey(), "Double");
            }
        }
        return stringStringMap;
    }
}
