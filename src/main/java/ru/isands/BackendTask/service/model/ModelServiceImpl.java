package ru.isands.BackendTask.service.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.isands.BackendTask.dto.ModelDto;
import ru.isands.BackendTask.dto.inputDto.ModelInputDto;
import ru.isands.BackendTask.exception.NotFoundException;
import ru.isands.BackendTask.mapper.ModelMapper;
import ru.isands.BackendTask.model.Appliance;
import ru.isands.BackendTask.model.Model;
import ru.isands.BackendTask.repository.ApplianceRepository;
import ru.isands.BackendTask.repository.ModelRepository;
import ru.isands.BackendTask.service.searchAndFilter.SearchFilterService;
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
    private final SearchFilterService searchFilterService;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, ApplianceRepository applianceRepository, SearchFilterService searchFilterService) {
        this.modelRepository = modelRepository;
        this.applianceRepository = applianceRepository;
        this.searchFilterService = searchFilterService;
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
        Appliance appliance = applianceRepository.findById(model.getAppliance().getId())
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND));
        ModelMapper.updateModel(model, modelInputDto);
        AttributeValidator.compareModel(appliance, model);
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
        return searchFilterService.getWithSearch(name,applianceName,color, minPrice,maxPrice,attributeMap).stream()
                .map(ModelMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ModelDto> getWithFilter(String alphabet, String price) {
        Sort sort = searchFilterService.getSort(alphabet, price);
        return null;
    }
}
