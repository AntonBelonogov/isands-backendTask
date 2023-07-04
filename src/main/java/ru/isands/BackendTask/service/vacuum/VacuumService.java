package ru.isands.BackendTask.service.vacuum;

import ru.isands.BackendTask.dto.VacuumDto;

import java.math.BigDecimal;
import java.util.List;

public interface VacuumService {
    List<VacuumDto> getVacuums();

    VacuumDto getVacuumById(Long vacuumId);

    VacuumDto addVacuum(Long applianceId, VacuumDto vacuumDto);

    VacuumDto updateVacuum(Long vacuumId, VacuumDto vacuumDto);

    Boolean deleteVacuum(Long vacuumId);

    List<VacuumDto> getWithSearch(
            String name,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Float dustBagVolume,
            Integer numberOfModes
    );

    List<VacuumDto> getWithFilter(String alphabet, String price);
}
