package ru.isands.BackendTask.service.vacuum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.isands.BackendTask.dto.VacuumDto;
import ru.isands.BackendTask.exception.ConflictException;
import ru.isands.BackendTask.exception.NotFoundException;
import ru.isands.BackendTask.mapper.VacuumMapper;
import ru.isands.BackendTask.model.Appliance;
import ru.isands.BackendTask.model.Model;
import ru.isands.BackendTask.repository.ApplianceRepository;
import ru.isands.BackendTask.repository.ModelRepository;
import ru.isands.BackendTask.service.searchAndFilter.SearchFilterServiceImpl;
import ru.isands.BackendTask.validator.VacuumValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacuumServiceImpl implements VacuumService {

    private static final String APPLIANCE_NAME = "Пылесос";
    private static final String ENTITY_NOT_FOUND = "Vacuum not found.";

    private final ModelRepository modelRepository;
    private final ApplianceRepository applianceRepository;
    private final SearchFilterServiceImpl searchFilterService;

    @Autowired
    public VacuumServiceImpl(ModelRepository modelRepository, ApplianceRepository applianceRepository, SearchFilterServiceImpl searchFilterService) {
        this.modelRepository = modelRepository;
        this.applianceRepository = applianceRepository;
        this.searchFilterService = searchFilterService;
    }

    public List<VacuumDto> getVacuums() {
        return modelRepository.findAllByAppliance_NameAndAvailable(APPLIANCE_NAME, true).stream()
                .map(VacuumMapper::toDto)
                .collect(Collectors.toList());
    }

    public VacuumDto getVacuumById(Long vacuumId) {
        return VacuumMapper.toDto(modelRepository.findByAppliance_NameAndId(APPLIANCE_NAME, vacuumId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND)));
    }

    public VacuumDto addVacuum(Long applianceId, VacuumDto vacuumDto) {
        Appliance appliance = applianceRepository.findById(applianceId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND));
        if (!appliance.getName().equals(APPLIANCE_NAME)) {
            throw new ConflictException("Appliance is not for vacuum.");
        }
        VacuumValidator.isTelevisionValid(vacuumDto);
        Model model = VacuumMapper.toModel(vacuumDto);
        model.setAppliance(appliance);
        return VacuumMapper.toDto(modelRepository.save(model));
    }

    public VacuumDto updateVacuum(Long vacuumId, VacuumDto vacuumDto) {
        Model model = modelRepository.findByAppliance_NameAndId(APPLIANCE_NAME, vacuumId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND));
        model = modelRepository.save(VacuumMapper.updateModel(model, vacuumDto));
        return VacuumMapper.toDto(model);
    }

    public Boolean deleteVacuum(Long vacuumId) {
        if (!modelRepository.existsByAppliance_NameAndId(APPLIANCE_NAME, vacuumId)) {
            throw new NotFoundException(ENTITY_NOT_FOUND);
        }
        modelRepository.deleteById(vacuumId);
        return !modelRepository.existsByAppliance_NameAndId(APPLIANCE_NAME, vacuumId);
    }

    public List<VacuumDto> getWithSearch(
            String name,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Float dustBagVolume,
            Integer numberOfModes
    ) {
        List<Model> models = searchFilterService.getWithSearch(name, APPLIANCE_NAME, color, minPrice, maxPrice);

        if (dustBagVolume != null) {
            models.retainAll(modelRepository.
                    findAllByAppliance_NameAndDustBagVolume(APPLIANCE_NAME, dustBagVolume));
        }
        if (numberOfModes != null) {
            models.retainAll(modelRepository
                    .findAllByAppliance_NameAndNumberOfModes(APPLIANCE_NAME, numberOfModes));
        }

        return models.stream()
                .map(VacuumMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<VacuumDto> getWithFilter(String alphabet, String price) {
        Sort sort = searchFilterService.getSort(alphabet, price);
        return modelRepository.findAllByAppliance_Name(APPLIANCE_NAME, sort).stream()
                .map(VacuumMapper::toDto)
                .collect(Collectors.toList());
    }
}
