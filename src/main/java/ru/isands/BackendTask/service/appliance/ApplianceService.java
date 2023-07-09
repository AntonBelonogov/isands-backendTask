package ru.isands.BackendTask.service.appliance;

import ru.isands.BackendTask.dto.ApplianceDto;
import ru.isands.BackendTask.dto.ModelInfoDto;
import ru.isands.BackendTask.dto.inputDto.ApplianceInputDto;
import ru.isands.BackendTask.model.Model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ApplianceService {
    List<ApplianceDto> getAppliances();

    ApplianceDto getApplianceById(Long applianceId);

    ApplianceDto addAppliance(ApplianceInputDto applianceDto);

    ApplianceDto updateAppliance(Long applianceId, ApplianceInputDto applianceInputDto);

    Boolean deleteAppliance(Long applianceId);

    List<Model> getWithSearch(
            String name,
            String applianceName,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Map<String, Object> attributeMap
    );

    List<ModelInfoDto> getWithFilter(String alphabet, String price);
}
