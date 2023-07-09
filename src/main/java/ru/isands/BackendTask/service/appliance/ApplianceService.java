package ru.isands.BackendTask.service.appliance;

import ru.isands.BackendTask.dto.ApplianceDto;
import ru.isands.BackendTask.dto.inputDto.ApplianceInputDto;

import java.util.List;

public interface ApplianceService {
    List<ApplianceDto> getAppliances();

    ApplianceDto getApplianceById(Long applianceId);

    ApplianceDto addAppliance(ApplianceInputDto applianceDto);

    ApplianceDto updateAppliance(Long applianceId, ApplianceInputDto applianceInputDto);

    Boolean deleteAppliance(Long applianceId);
}
