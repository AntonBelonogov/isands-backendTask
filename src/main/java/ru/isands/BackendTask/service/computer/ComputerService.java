package ru.isands.BackendTask.service.computer;

import ru.isands.BackendTask.dto.ComputerDto;
import ru.isands.BackendTask.dto.inputDto.ComputerInputDto;

import java.math.BigDecimal;
import java.util.List;

public interface ComputerService {
    List<ComputerDto> getComputers();

    ComputerDto getComputerById(Long computerId);

    ComputerDto addComputer(Long applianceId, ComputerInputDto computerInputDto);

    ComputerDto updateComputer(Long computerId, ComputerInputDto computerInputDto);

    Boolean deleteComputer(Long computerId);

    List<ComputerDto> getWithSearch(
            String name,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            String computerCategory,
            String processorType
    );

    List<ComputerDto> getWithFilter(String alphabet, String price);
}
