package ru.isands.BackendTask.service.appliance;

import ru.isands.BackendTask.dto.AppliancesDto;
import ru.isands.BackendTask.dto.ModelInfoDto;
import ru.isands.BackendTask.model.Model;

import java.math.BigDecimal;
import java.util.List;

public interface ApplianceService {
    List<AppliancesDto> getAppliances();

    AppliancesDto getApplianceById(Long applianceId);

    AppliancesDto addAppliance(AppliancesDto appliancesDto);

    AppliancesDto updateAppliance(Long applianceId, AppliancesDto appliancesDto);

    Boolean deleteAppliance(Long applianceId);

    List<Model> getWithSearch(
            String name,
            String applianceName,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice
    );

    List<ModelInfoDto> getWithFilter(String alphabet, String price);
}
