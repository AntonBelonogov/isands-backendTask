package ru.isands.BackendTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.isands.BackendTask.dto.FridgeDto;
import ru.isands.BackendTask.enums.SortType;
import ru.isands.BackendTask.exception.ConflictException;
import ru.isands.BackendTask.exception.NotFoundException;
import ru.isands.BackendTask.exception.UnknownSortTypeException;
import ru.isands.BackendTask.mapper.FridgeMapper;
import ru.isands.BackendTask.model.Appliance;
import ru.isands.BackendTask.model.Model;
import ru.isands.BackendTask.repository.ApplianceRepository;
import ru.isands.BackendTask.repository.FridgeRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FridgeService {

    private static final String APPLIANCE_NAME = "Холодильник";
    private static final String ENTITY_NOT_FOUND = "Fridge not found.";
    private final FridgeRepository fridgeRepository;
    private final ApplianceRepository applianceRepository;
    private final ApplianceService modelService;

    @Autowired
    public FridgeService(FridgeRepository fridgeRepository, ApplianceRepository applianceRepository, ApplianceService applianceService) {
        this.fridgeRepository = fridgeRepository;
        this.applianceRepository = applianceRepository;
        this.modelService = applianceService;
    }

    public List<FridgeDto> getFridges() {
        return fridgeRepository.findAllByAppliance_NameAndAvailable(APPLIANCE_NAME, true).stream()
                .map(FridgeMapper::toDto)
                .collect(Collectors.toList());
    }

    public FridgeDto getFridge(Long fridgeId) {
        return FridgeMapper.toDto(fridgeRepository.findByAppliance_NameAndId(APPLIANCE_NAME, fridgeId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND)));
    }

    public FridgeDto addFridge(Long applianceId, FridgeDto fridgeDto) {
        Appliance appliance = applianceRepository.findById(applianceId)
                .orElseThrow(() -> new NotFoundException("Appliance not found."));
        if (!appliance.getName().equals(APPLIANCE_NAME)) {
            throw new ConflictException("Appliance is not for fridge.");
        }
        Model model = FridgeMapper.toModel(fridgeDto);
        model.setAppliance(appliance);
        return FridgeMapper.toDto(model);
    }

    public FridgeDto updateFridge(Long fridgeId, FridgeDto fridgeDto) {
        Model model = fridgeRepository.findByAppliance_NameAndId(APPLIANCE_NAME, fridgeId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND));
        model = fridgeRepository.save(FridgeMapper.updateModel(model, fridgeDto));
        return FridgeMapper.toDto(model);
    }

    public Boolean deleteFridge(Long fridgeId) {
        if (!fridgeRepository.existsByAppliance_NameAndId(APPLIANCE_NAME, fridgeId)) {
            throw new NotFoundException(ENTITY_NOT_FOUND);
        }
        fridgeRepository.deleteById(fridgeId);
        return !fridgeRepository.existsByAppliance_NameAndId(APPLIANCE_NAME, fridgeId);
    }

    public List<FridgeDto> getWithSearch(
            String name,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Integer numberOfDoors,
            String compressorType
    ) {
        List<Model> models = modelService.getWithSearch(name, APPLIANCE_NAME, color, minPrice, maxPrice);

        if (numberOfDoors != null) {
            models.retainAll(fridgeRepository
                    .findAllByAppliance_NameAndNumberOfDoors(APPLIANCE_NAME, numberOfDoors));
        }
        if (compressorType != null) {
            models.retainAll(fridgeRepository
                    .findAllByAppliance_NameAndCompressorTypeIgnoreCase(APPLIANCE_NAME, compressorType));
        }
        return models.stream()
                .map(FridgeMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<FridgeDto> getWithFilter(String alphabet, String price) {
        Sort sort = getSort(alphabet, price);
        return fridgeRepository.findAllByAppliance_Name(APPLIANCE_NAME, sort).stream()
                .map(FridgeMapper::toDto)
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
