package ru.isands.BackendTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.isands.BackendTask.dto.VacuumDto;
import ru.isands.BackendTask.enums.SortType;
import ru.isands.BackendTask.exception.ConflictException;
import ru.isands.BackendTask.exception.NotFoundException;
import ru.isands.BackendTask.exception.UnknownSortTypeException;
import ru.isands.BackendTask.mapper.VacuumMapper;
import ru.isands.BackendTask.model.Appliance;
import ru.isands.BackendTask.model.Model;
import ru.isands.BackendTask.repository.ApplianceRepository;
import ru.isands.BackendTask.repository.VacuumRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacuumService {

    private static final String APPLIANCE_NAME = "Пылесос";
    private static final String ENTITY_NOT_FOUND = "Vacuum not found.";

    private final VacuumRepository vacuumRepository;
    private final ApplianceRepository applianceRepository;
    private final ApplianceService modelService;

    @Autowired
    public VacuumService(VacuumRepository vacuumRepository, ApplianceRepository applianceRepository, ApplianceService modelService) {
        this.vacuumRepository = vacuumRepository;
        this.applianceRepository = applianceRepository;
        this.modelService = modelService;
    }

    public List<VacuumDto> getVacuums() {
        return vacuumRepository.findAllByAppliance_NameAndAvailable(APPLIANCE_NAME, true).stream()
                .map(VacuumMapper::toDto)
                .collect(Collectors.toList());
    }

    public VacuumDto getVacuumById(Long vacuumId) {
        return VacuumMapper.toDto(vacuumRepository.findByAppliance_NameAndId(APPLIANCE_NAME, vacuumId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND)));
    }

    public VacuumDto addVacuum(Long applianceId, VacuumDto vacuumDto) {
        Appliance appliance = applianceRepository.findById(applianceId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND));
        if (!appliance.getName().equals(APPLIANCE_NAME)) {
            throw new ConflictException("Appliance is not for vacuum.");
        }
        Model model = VacuumMapper.toModel(vacuumDto);
        model.setAppliance(appliance);
        return VacuumMapper.toDto(vacuumRepository.save(model));
    }

    public VacuumDto updateVacuum(Long vacuumId, VacuumDto vacuumDto) {
        Model model = vacuumRepository.findByAppliance_NameAndId(APPLIANCE_NAME, vacuumId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND));
        model = vacuumRepository.save(VacuumMapper.updateModel(model, vacuumDto));
        return VacuumMapper.toDto(model);
    }

    public Boolean deleteVacuum(Long vacuumId) {
        if (!vacuumRepository.existsByAppliance_NameAndId(APPLIANCE_NAME, vacuumId)) {
            throw new NotFoundException(ENTITY_NOT_FOUND);
        }
        vacuumRepository.deleteById(vacuumId);
        return !vacuumRepository.existsByAppliance_NameAndId(APPLIANCE_NAME, vacuumId);
    }

    public List<VacuumDto> getWithSearch(
            String name,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Float dustBagVolume,
            Integer numberOfModes
    ) {
        List<Model> models = modelService.getWithSearch(name, APPLIANCE_NAME, color, minPrice, maxPrice);

        if (dustBagVolume != null) {
            models.retainAll(vacuumRepository.
                    findAllByAppliance_NameAndDustBagVolume(APPLIANCE_NAME, dustBagVolume));
        }
        if (numberOfModes != null) {
            models.retainAll(vacuumRepository
                    .findAllByAppliance_NameAndNumberOfModes(APPLIANCE_NAME, numberOfModes));
        }

        return models.stream()
                .map(VacuumMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<VacuumDto> getWithFilter(String alphabet, String price) {
        List<Model> models = new ArrayList<>();
        Sort sort;
        if (alphabet != null && price == null) {
            sort = Sort.by(checkType(alphabet) == SortType.ASC ?
                    Sort.Direction.ASC : Sort.Direction.DESC, "name");
            models = vacuumRepository.findAllByAppliance_Name(APPLIANCE_NAME, sort);
        }
        if (price != null && alphabet == null) {
            sort = Sort.by(checkType(price) == SortType.ASC ?
                    Sort.Direction.ASC : Sort.Direction.DESC, "price");
            models = vacuumRepository.findAllByAppliance_Name(APPLIANCE_NAME, sort);
        }
        if (alphabet != null && price != null) {
            sort = Sort.by(checkType(alphabet) == SortType.ASC ? Sort.Order.asc("name") : Sort.Order.desc("name"),
                    checkType(price) == SortType.ASC ? Sort.Order.asc("price") : Sort.Order.desc("price"));
            models = vacuumRepository.findAllByAppliance_Name(APPLIANCE_NAME, sort);
        }
        return models.stream()
                .map(VacuumMapper::toDto)
                .collect(Collectors.toList());
    }

    private SortType checkType(String type) {
        try {
            return SortType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new UnknownSortTypeException(String.format("unknown state: %s", type));
        }
    }
}
