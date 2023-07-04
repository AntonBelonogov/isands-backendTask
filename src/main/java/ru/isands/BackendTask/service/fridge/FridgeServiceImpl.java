package ru.isands.BackendTask.service.fridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.isands.BackendTask.dto.FridgeDto;
import ru.isands.BackendTask.exception.ConflictException;
import ru.isands.BackendTask.exception.NotFoundException;
import ru.isands.BackendTask.mapper.FridgeMapper;
import ru.isands.BackendTask.model.Appliance;
import ru.isands.BackendTask.model.Model;
import ru.isands.BackendTask.repository.ApplianceRepository;
import ru.isands.BackendTask.repository.ModelRepository;
import ru.isands.BackendTask.service.searchAndFilter.SearchFilterService;
import ru.isands.BackendTask.validator.FridgeValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FridgeServiceImpl implements FridgeService {

    private static final String APPLIANCE_NAME = "Холодильник";
    private static final String ENTITY_NOT_FOUND = "Fridge not found.";
    private final ModelRepository fridgeRepository;
    private final ApplianceRepository applianceRepository;
    private final SearchFilterService searchFilterService;

    @Autowired
    public FridgeServiceImpl(ModelRepository fridgeRepository, ApplianceRepository applianceRepository, SearchFilterService applianceService) {
        this.fridgeRepository = fridgeRepository;
        this.applianceRepository = applianceRepository;
        this.searchFilterService = applianceService;
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
        FridgeValidator.isFridgeValid(fridgeDto);
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
        List<Model> models = searchFilterService.getWithSearch(name, APPLIANCE_NAME, color, minPrice, maxPrice);

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
        Sort sort = searchFilterService.getSort(alphabet, price);
        return fridgeRepository.findAllByAppliance_Name(APPLIANCE_NAME, sort).stream()
                .map(FridgeMapper::toDto)
                .collect(Collectors.toList());
    }
}
