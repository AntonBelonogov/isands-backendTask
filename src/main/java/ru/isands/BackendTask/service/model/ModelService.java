package ru.isands.BackendTask.service.model;

import ru.isands.BackendTask.dto.ModelDto;
import ru.isands.BackendTask.dto.inputDto.ModelInputDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ModelService {
    List<ModelDto> getModels();

    List<ModelDto> getModelByCategory(String category);

    ModelDto getModelById(Long computerId);

    ModelDto addModel(Long applianceId, ModelInputDto modelInputDto);

    ModelDto updateModel(Long modelId, ModelInputDto modelInputDto);

    Boolean deleteModel(Long modelId);

    List<ModelDto> getWithSearch(
            String name,
            String color,
            String applianceName,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Map<String, Object> attributeMap
    );

    List<ModelDto> getWithFilter(String alphabet, String price);
}
