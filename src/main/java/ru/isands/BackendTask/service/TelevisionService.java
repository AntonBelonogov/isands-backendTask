package ru.isands.BackendTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.isands.BackendTask.dto.TelevisionDto;
import ru.isands.BackendTask.exception.ConflictException;
import ru.isands.BackendTask.exception.NotFoundException;
import ru.isands.BackendTask.mapper.TelevisionMapper;
import ru.isands.BackendTask.model.Appliance;
import ru.isands.BackendTask.model.Model;
import ru.isands.BackendTask.repository.ApplianceRepository;
import ru.isands.BackendTask.repository.ModelRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelevisionService {

    private static final String APPLIANCE_NAME = "Телевизор";
    private static final String ENTITY_NOT_FOUND = "Television not found.";
    private final ModelRepository modelRepository;
    private final ApplianceRepository applianceRepository;
    private final SearchFilterService searchFilterService;

    @Autowired
    public TelevisionService(ModelRepository modelRepository, ApplianceRepository applianceRepository, SearchFilterService searchFilterService) {
        this.modelRepository = modelRepository;
        this.applianceRepository = applianceRepository;
        this.searchFilterService = searchFilterService;
    }

    public List<TelevisionDto> getTelevision() {
        return modelRepository.findAllByAppliance_NameAndAvailable(APPLIANCE_NAME, true).stream()
                .map(TelevisionMapper::toDto)
                .collect(Collectors.toList());
    }

    public TelevisionDto getTelevisionById(Long televisionId) {
        return TelevisionMapper.toDto(modelRepository.findByAppliance_NameAndId(APPLIANCE_NAME, televisionId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND)));
    }

    public TelevisionDto addTelevision(Long applianceId, TelevisionDto televisionDto) {
        Appliance appliance = applianceRepository.findById(applianceId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND));
        if (!appliance.getName().equals(APPLIANCE_NAME)) {
            throw new ConflictException("Appliance is not for television.");
        }
        Model model = TelevisionMapper.toModel(televisionDto);
        model.setAppliance(appliance);
        return TelevisionMapper.toDto(modelRepository.save(model));
    }

    public TelevisionDto updateTelevision(Long televisionId, TelevisionDto televisionDto) {
        Model model = modelRepository.findByAppliance_NameAndId(APPLIANCE_NAME, televisionId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND));
        model = modelRepository.save(TelevisionMapper.updateModel(model, televisionDto));
        return TelevisionMapper.toDto(model);
    }

    public Boolean deletePhone(Long televisionId) {
        if (!modelRepository.existsByAppliance_NameAndId(APPLIANCE_NAME, televisionId)) {
            throw new NotFoundException(ENTITY_NOT_FOUND);
        }
        modelRepository.deleteById(televisionId);
        return !modelRepository.existsByAppliance_NameAndId(APPLIANCE_NAME, televisionId);
    }

    public List<TelevisionDto> getWithSearch(
            String name,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            String televisionCategory,
            String televisionTechnology
    ) {
        List<Model> models = searchFilterService.getWithSearch(name, APPLIANCE_NAME, color, minPrice, maxPrice);

        if (televisionCategory != null) {
            models.retainAll(modelRepository
                    .findAllByAppliance_NameAndTelevisionCategoryIgnoreCase(APPLIANCE_NAME, televisionCategory));
        }
        if (televisionTechnology != null) {
            models.retainAll(modelRepository
                    .findAllByAppliance_NameAndTelevisionTechnologyIgnoreCase(APPLIANCE_NAME, televisionTechnology));
        }

        return models.stream()
                .map(TelevisionMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TelevisionDto> getWithFilter(String alphabet, String price) {
        Sort sort = searchFilterService.getSort(alphabet, price);

        return modelRepository.findAllByAppliance_Name(APPLIANCE_NAME, sort).stream()
                .map(TelevisionMapper::toDto)
                .collect(Collectors.toList());
    }
}
