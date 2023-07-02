package ru.isands.BackendTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.isands.BackendTask.dto.AppliancesDto;
import ru.isands.BackendTask.dto.ModelInfoDto;
import ru.isands.BackendTask.enums.SortType;
import ru.isands.BackendTask.exception.NotFoundException;
import ru.isands.BackendTask.exception.UnknownSortTypeException;
import ru.isands.BackendTask.mapper.ApplianceMapper;
import ru.isands.BackendTask.mapper.ModelMapper;
import ru.isands.BackendTask.model.Appliance;
import ru.isands.BackendTask.model.Model;
import ru.isands.BackendTask.repository.ApplianceRepository;
import ru.isands.BackendTask.repository.ModelRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplianceService {

    private static final String ENTITY_NOT_FOUND = "Appliance not found.";
    private final ApplianceRepository applianceRepository;
    private final ModelRepository modelRepository;

    @Autowired
    public ApplianceService(ApplianceRepository applianceRepository, ModelRepository modelRepository) {
        this.applianceRepository = applianceRepository;
        this.modelRepository = modelRepository;
    }

    public List<AppliancesDto> getAppliances() {
        return applianceRepository.findAll()
                .stream()
                .map(ApplianceMapper::toDto)
                .collect(Collectors.toList());
    }

    public AppliancesDto getApplianceById(Long applianceId) {
        return ApplianceMapper.toDto(applianceRepository.findById(applianceId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND)));
    }

    public AppliancesDto addAppliance(AppliancesDto appliancesDto) {
        Appliance appliance = ApplianceMapper.toAppliance(appliancesDto);
        return ApplianceMapper.toDto(applianceRepository.save(appliance));
    }

    public AppliancesDto updateAppliance(Long applianceId, AppliancesDto appliancesDto) {
        Appliance updatedAppliance = applianceRepository.findById(applianceId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND));
        updatedAppliance = applianceRepository.save(ApplianceMapper.updateAppliance(updatedAppliance, appliancesDto));
        return ApplianceMapper.toDto(updatedAppliance);
    }

    public Boolean deleteAppliance(Long applianceId) {
        if (!applianceRepository.existsById(applianceId)) {
            throw new NotFoundException(ENTITY_NOT_FOUND);
        }
        applianceRepository.deleteById(applianceId);
        return !applianceRepository.existsById(applianceId);
    }

    public List<Model> getWithSearch(
            String name,
            String applianceName,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice
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
        return models;
    }

    public List<ModelInfoDto> getWithFilter(String alphabet, String price) {
        Sort sort = getSort(alphabet, price);
        return modelRepository.findAll(sort).stream()
                .map(ModelMapper::toInfoDto)
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
