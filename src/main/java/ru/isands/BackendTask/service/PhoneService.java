package ru.isands.BackendTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.isands.BackendTask.dto.PhoneDto;
import ru.isands.BackendTask.enums.SortType;
import ru.isands.BackendTask.exception.ConflictException;
import ru.isands.BackendTask.exception.NotFoundException;
import ru.isands.BackendTask.exception.UnknownSortTypeException;
import ru.isands.BackendTask.mapper.PhoneMapper;
import ru.isands.BackendTask.model.Appliance;
import ru.isands.BackendTask.model.Model;
import ru.isands.BackendTask.repository.ApplianceRepository;
import ru.isands.BackendTask.repository.PhoneRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneService {

    private static final String APPLIANCE_NAME = "Смартфон";
    private static final String ENTITY_NOT_FOUND = "Phone not found.";
    private final PhoneRepository phoneRepository;
    private final ApplianceRepository applianceRepository;

    private final ApplianceService modelService;

    @Autowired
    public PhoneService(PhoneRepository phoneRepository, ApplianceRepository applianceRepository, ApplianceService modelService) {
        this.phoneRepository = phoneRepository;
        this.applianceRepository = applianceRepository;
        this.modelService = modelService;
    }

    public List<PhoneDto> getPhones() {
        return phoneRepository.findAllByAppliance_NameAndAvailable(APPLIANCE_NAME, true).stream()
                .map(PhoneMapper::toDto)
                .collect(Collectors.toList());
    }

    public PhoneDto getPhoneById(Long phoneId) {
        return PhoneMapper.toDto(phoneRepository.findByAppliance_NameAndId(APPLIANCE_NAME, phoneId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND)));
    }

    public PhoneDto addPhone(Long applianceId, PhoneDto phoneDto) {
        Appliance appliance = applianceRepository.findById(applianceId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND));
        if (!appliance.getName().equals(APPLIANCE_NAME)) {
            throw new ConflictException("Appliance is not for phone.");
        }
        Model model = PhoneMapper.toModel(phoneDto);
        model.setAppliance(appliance);
        return PhoneMapper.toDto(phoneRepository.save(model));
    }

    public PhoneDto updatePhone(Long phoneId, PhoneDto phoneDto) {
        Model model = phoneRepository.findByAppliance_NameAndId(APPLIANCE_NAME, phoneId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND));
        model = phoneRepository.save(PhoneMapper.updateModel(model, phoneDto));
        return PhoneMapper.toDto(model);
    }

    public Boolean deletePhone(Long phoneId) {
        if (!phoneRepository.existsByAppliance_NameAndId(APPLIANCE_NAME, phoneId)) {
            throw new NotFoundException(ENTITY_NOT_FOUND);
        }
        phoneRepository.deleteById(phoneId);
        return !phoneRepository.existsByAppliance_NameAndId(APPLIANCE_NAME, phoneId);
    }

    public List<PhoneDto> getWithSearch(
            String name,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Integer memory,
            Integer numberOfCameras
    ) {
        List<Model> models = modelService.getWithSearch(name, APPLIANCE_NAME, color, minPrice, maxPrice);

        if (memory != null) {
            models.retainAll(phoneRepository
                    .findAllByAppliance_NameAndMemory(APPLIANCE_NAME, memory));
        }
        if (numberOfCameras != null) {
            models.retainAll(phoneRepository
                    .findAllByAppliance_NameAndNumberOfCameras(APPLIANCE_NAME, numberOfCameras));
        }

        return models.stream()
                .map(PhoneMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<PhoneDto> getWithFilter(String alphabet, String price) {
        Sort sort = getSort(alphabet, price);
        return phoneRepository.findAllByAppliance_Name(APPLIANCE_NAME, sort).stream()
                .map(PhoneMapper::toDto)
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
