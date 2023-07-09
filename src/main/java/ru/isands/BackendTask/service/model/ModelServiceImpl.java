package ru.isands.BackendTask.service.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.isands.BackendTask.dto.ModelDto;
import ru.isands.BackendTask.dto.inputDto.ModelInputDto;
import ru.isands.BackendTask.enums.SortType;
import ru.isands.BackendTask.exception.NotFoundException;
import ru.isands.BackendTask.exception.UnknownSortTypeException;
import ru.isands.BackendTask.mapper.ModelMapper;
import ru.isands.BackendTask.model.Appliance;
import ru.isands.BackendTask.model.Model;
import ru.isands.BackendTask.repository.ApplianceRepository;
import ru.isands.BackendTask.repository.ModelRepository;
import ru.isands.BackendTask.validator.AttributeValidator;
import ru.isands.BackendTask.validator.ModelValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {

    private static final String ENTITY_NOT_FOUND = "Model not found.";
    private final ModelRepository modelRepository;
    private final ApplianceRepository applianceRepository;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, ApplianceRepository applianceRepository) {
        this.modelRepository = modelRepository;
        this.applianceRepository = applianceRepository;
    }

    @Override
    public List<ModelDto> getModels() {
        return modelRepository.findAllByAvailable(true).stream()
                .map(ModelMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ModelDto> getModelByCategory(String category) {
        return modelRepository.findAllByAppliance_Name(category).stream()
                .map(ModelMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ModelDto getModelById(Long modelId) {
        return ModelMapper.toDto(modelRepository.findById(modelId).orElseThrow(() ->
                new NotFoundException(ENTITY_NOT_FOUND)));
    }

    @Override
    public ModelDto addModel(Long applianceId, ModelInputDto modelInputDto) {
        Appliance appliance = applianceRepository.findById(applianceId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND));
        ModelValidator.isModelValid(modelInputDto);
        Model model = ModelMapper.toModel(modelInputDto);
        AttributeValidator.compareModel(appliance, model);
        model.setAppliance(appliance);
        return ModelMapper.toDto(modelRepository.save(model));
    }

    @Override
    public ModelDto updateModel(Long modelId, ModelInputDto modelInputDto) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND));
        ModelMapper.updateModel(model, modelInputDto);
        AttributeValidator.compareModel(model.getAppliance(), model);
        return ModelMapper.toDto(modelRepository.save(model));
    }

    @Override
    public Boolean deleteModel(Long modelId) {
        if (!modelRepository.existsById(modelId)) {
            throw new NotFoundException(ENTITY_NOT_FOUND);
        }
        modelRepository.deleteById(modelId);
        return !modelRepository.existsById(modelId);
    }

    public List<ModelDto> getWithSearch(
            String name,
            String color,
            String applianceName,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Map<String, Object> attributeMap
    ) {
        return this.search(name, color, applianceName, minPrice, maxPrice, attributeMap).stream()
                .map(ModelMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ModelDto> getWithFilter(String alphabet, String price, String applianceName) {
        Sort sort = this.getSort(alphabet, price);

        List<Model> models;

        if (applianceName != null) {
            models = modelRepository.findAllByAppliance_Name(applianceName, sort);
        } else {
            models = modelRepository.findAll(sort);
        }

        return models.stream()
                .map(ModelMapper::toDto)
                .collect(Collectors.toList());
    }

    private List<Model> search(
            String name,
            String color,
            String applianceName,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Map<String, Object> attributeMap
    ) {
        List<Model> models;

        if (applianceName != null) {
            models = modelRepository.findAllByAppliance_Name(applianceName);
        } else {
            models = modelRepository.findAll();
        }
        if (name != null) {
            models.retainAll(modelRepository.findAllByNameIgnoreCase(name));
        }
        if (color != null) {
            models.retainAll(modelRepository.findAllByColorIgnoreCase(color));
        }
        if (minPrice != null || maxPrice != null) {
            if (minPrice == null) {
                minPrice = BigDecimal.valueOf(0);
            }
            if (maxPrice == null) {
                maxPrice = BigDecimal.valueOf(99999999);
            }
            models.retainAll(modelRepository.findAllByPriceBetween(minPrice, maxPrice));
        }
        if (attributeMap != null) {
            for (Map.Entry<String, Object> entry :
                    attributeMap.entrySet()) {
                models.retainAll(modelRepository.search(entry.getKey(), entry.getValue().toString()));
            }
        }
        return models;
    }

    private Sort getSort(String alphabet, String price) {
        if (alphabet != null && price == null) {
            return Sort.by(checkType(alphabet) == SortType.ASC ?
                    Sort.Direction.ASC : Sort.Direction.DESC, "name");
        }
        if (price != null && alphabet == null) {
            return Sort.by(checkType(price) == SortType.ASC ?
                    Sort.Direction.ASC : Sort.Direction.DESC, "price");
        }
        if (alphabet != null && price != null) {
            return Sort.by(checkType(alphabet) == SortType.ASC ? Sort.Order.asc("name") : Sort.Order.desc("name"),
                    checkType(price) == SortType.ASC ? Sort.Order.asc("price") : Sort.Order.desc("price"));
        }
        return Sort.by(Sort.Direction.ASC, "name");
    }

    private SortType checkType(String type) {
        try {
            return SortType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new UnknownSortTypeException(String.format("unknown state: %s", type));
        }
    }
}
