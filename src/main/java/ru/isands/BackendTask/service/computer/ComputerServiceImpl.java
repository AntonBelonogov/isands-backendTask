package ru.isands.BackendTask.service.computer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.isands.BackendTask.dto.ComputerDto;
import ru.isands.BackendTask.exception.ConflictException;
import ru.isands.BackendTask.exception.NotFoundException;
import ru.isands.BackendTask.mapper.ComputerMapper;
import ru.isands.BackendTask.model.Appliance;
import ru.isands.BackendTask.model.Model;
import ru.isands.BackendTask.repository.ApplianceRepository;
import ru.isands.BackendTask.repository.ModelRepository;
import ru.isands.BackendTask.service.searchAndFilter.SearchFilterService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComputerServiceImpl implements ComputerService {

    private static final String APPLIANCE_NAME = "Компьютер";
    private static final String ENTITY_NOT_FOUND = "Computer not found.";
    private final ModelRepository computerRepository;
    private final ApplianceRepository applianceRepository;
    private final SearchFilterService searchFilterService;

    @Autowired
    public ComputerServiceImpl(ModelRepository computerRepository, ApplianceRepository applianceRepository, SearchFilterService searchFilterService) {
        this.computerRepository = computerRepository;
        this.applianceRepository = applianceRepository;
        this.searchFilterService = searchFilterService;
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
        List<Model> models = searchFilterService.getWithSearch(name, APPLIANCE_NAME, color, minPrice, maxPrice);

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
        Sort sort = searchFilterService.getSort(alphabet, price);
        return computerRepository.findAllByAppliance_Name(APPLIANCE_NAME, sort).stream()
                .map(ComputerMapper::toDto)
                .collect(Collectors.toList());
    }
}
