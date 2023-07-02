package ru.isands.BackendTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.isands.BackendTask.dto.ComputerDto;
import ru.isands.BackendTask.enums.SortType;
import ru.isands.BackendTask.exception.ConflictException;
import ru.isands.BackendTask.exception.NotFoundException;
import ru.isands.BackendTask.exception.UnknownSortTypeException;
import ru.isands.BackendTask.mapper.ComputerMapper;
import ru.isands.BackendTask.model.Appliance;
import ru.isands.BackendTask.model.Model;
import ru.isands.BackendTask.repository.ApplianceRepository;
import ru.isands.BackendTask.repository.ComputerRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComputerService {

    private static final String APPLIANCE_NAME = "Компьютер";
    private static final String ENTITY_NOT_FOUND = "Computer not found.";
    private final ComputerRepository computerRepository;
    private final ApplianceRepository applianceRepository;
    private final ApplianceService modelService;

    @Autowired
    public ComputerService(ComputerRepository computerRepository, ApplianceRepository applianceRepository, ApplianceService modelService) {
        this.computerRepository = computerRepository;
        this.applianceRepository = applianceRepository;
        this.modelService = modelService;
    }

    public List<ComputerDto> getComputers() {
        return computerRepository.findAllByAppliance_NameAndAvailable(APPLIANCE_NAME, true).stream()
                .map(ComputerMapper::toDto)
                .collect(Collectors.toList());
    }

    public ComputerDto getComputerById(Long computerId) {
        return ComputerMapper.toDto(computerRepository.findByAppliance_NameAndId(APPLIANCE_NAME, computerId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND)));
    }

    public ComputerDto addComputer(Long applianceId, ComputerDto computerDto) {
        Appliance appliance = applianceRepository.findById(applianceId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND));
        if (!appliance.getName().equals(APPLIANCE_NAME)) {
            throw new ConflictException("Appliance is not for computer.");
        }
        Model model = ComputerMapper.toModel(computerDto);
        model.setAppliance(appliance);
        return ComputerMapper.toDto(computerRepository.save(model));
    }

    public ComputerDto updateComputer(Long computerId, ComputerDto computerDto) {
        Model model = computerRepository.findByAppliance_NameAndId(APPLIANCE_NAME, computerId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND));
        model = computerRepository.save(ComputerMapper.updateModel(model, computerDto));
        return ComputerMapper.toDto(model);
    }

    public Boolean deleteComputer(Long computerId) {
        if (!computerRepository.existsByAppliance_NameAndId(APPLIANCE_NAME, computerId)) {
            throw new NotFoundException(ENTITY_NOT_FOUND);
        }
        computerRepository.deleteById(computerId);
        return !computerRepository.existsByAppliance_NameAndId(APPLIANCE_NAME, computerId);
    }

    public List<ComputerDto> getWithSearch(
            String name,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            String computerCategory,
            String processorType
    ) {
        List<Model> models = modelService.getWithSearch(name, APPLIANCE_NAME, color, minPrice, maxPrice);

        if (computerCategory != null) {
            models.retainAll(computerRepository.findAllByAppliance_NameAndComputerCategoryIgnoreCase(APPLIANCE_NAME, computerCategory));
        }
        if (processorType != null) {
            models.retainAll(computerRepository.findAllByAppliance_NameAndProcessorTypeIgnoreCase(APPLIANCE_NAME, computerCategory));
        }
        return models.stream()
                .map(ComputerMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ComputerDto> getWithFilter(String alphabet, String price) {
        Sort sort = getSort(alphabet, price);
        return computerRepository.findAllByAppliance_Name(APPLIANCE_NAME, sort).stream()
                .map(ComputerMapper::toDto)
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
