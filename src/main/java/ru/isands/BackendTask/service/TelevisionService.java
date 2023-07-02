package ru.isands.BackendTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.isands.BackendTask.dto.TelevisionDto;
import ru.isands.BackendTask.enums.SortType;
import ru.isands.BackendTask.exception.ConflictException;
import ru.isands.BackendTask.exception.NotFoundException;
import ru.isands.BackendTask.exception.UnknownSortTypeException;
import ru.isands.BackendTask.mapper.TelevisionMapper;
import ru.isands.BackendTask.model.Appliance;
import ru.isands.BackendTask.model.Model;
import ru.isands.BackendTask.repository.ApplianceRepository;
import ru.isands.BackendTask.repository.TelevisionRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelevisionService {

    private static final String APPLIANCE_NAME = "Телевизор";
    private static final String ENTITY_NOT_FOUND = "Television not found.";
    private final TelevisionRepository televisionRepository;
    private final ApplianceRepository applianceRepository;
    private final ApplianceService modelService;

    @Autowired
    public TelevisionService(TelevisionRepository televisionRepository, ApplianceRepository applianceRepository, ApplianceService modelService) {
        this.televisionRepository = televisionRepository;
        this.applianceRepository = applianceRepository;
        this.modelService = modelService;
    }

    public List<TelevisionDto> getTelevision() {
        return televisionRepository.findAllByAppliance_NameAndAvailable(APPLIANCE_NAME, true).stream()
                .map(TelevisionMapper::toDto)
                .collect(Collectors.toList());
    }

    public TelevisionDto getTelevisionById(Long televisionId) {
        return TelevisionMapper.toDto(televisionRepository.findByAppliance_NameAndId(APPLIANCE_NAME, televisionId)
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
        return TelevisionMapper.toDto(televisionRepository.save(model));
    }

    public TelevisionDto updateTelevision(Long televisionId, TelevisionDto televisionDto) {
        Model model = televisionRepository.findByAppliance_NameAndId(APPLIANCE_NAME, televisionId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND));
        model = televisionRepository.save(TelevisionMapper.updateModel(model, televisionDto));
        return TelevisionMapper.toDto(model);
    }

    public Boolean deletePhone(Long televisionId) {
        if (!televisionRepository.existsByAppliance_NameAndId(APPLIANCE_NAME, televisionId)) {
            throw new NotFoundException(ENTITY_NOT_FOUND);
        }
        televisionRepository.deleteById(televisionId);
        return !televisionRepository.existsByAppliance_NameAndId(APPLIANCE_NAME, televisionId);
    }

    public List<TelevisionDto> getWithSearch(
            String name,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            String televisionCategory,
            String televisionTechnology
    ) {
        List<Model> models = modelService.getWithSearch(name, APPLIANCE_NAME, color, minPrice, maxPrice);

        if (televisionCategory != null) {
            models.retainAll(televisionRepository
                    .findAllByAppliance_NameAndTelevisionCategoryIgnoreCase(APPLIANCE_NAME, televisionCategory));
        }
        if (televisionTechnology != null) {
            models.retainAll(televisionRepository
                    .findAllByAppliance_NameAndTelevisionTechnologyIgnoreCase(APPLIANCE_NAME, televisionTechnology));
        }

        return models.stream()
                .map(TelevisionMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TelevisionDto> getWithFilter(String alphabet, String price) {
        Sort sort = getSort(alphabet, price);

        return televisionRepository.findAllByAppliance_Name(APPLIANCE_NAME, sort).stream()
                .map(TelevisionMapper::toDto)
                .collect(Collectors.toList());
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
