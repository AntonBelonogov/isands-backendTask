package ru.isands.BackendTask.service.fridge;

import ru.isands.BackendTask.dto.FridgeDto;
import ru.isands.BackendTask.dto.inputDto.FridgeInputDto;

import java.math.BigDecimal;
import java.util.List;

public interface FridgeService {
    List<FridgeDto> getFridges();

    FridgeDto getFridge(Long fridgeId);

    FridgeDto addFridge(Long applianceId, FridgeInputDto fridgeInputDto);

    FridgeDto updateFridge(Long fridgeId, FridgeInputDto fridgeInputDto);

    Boolean deleteFridge(Long fridgeId);

    List<FridgeDto> getWithSearch(
            String name,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Integer numberOfDoors,
            String compressorType
    );

    List<FridgeDto> getWithFilter(String alphabet, String price);
}
