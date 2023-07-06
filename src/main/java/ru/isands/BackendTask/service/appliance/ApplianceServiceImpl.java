package ru.isands.BackendTask.service.appliance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.isands.BackendTask.dto.ApplianceDto;
import ru.isands.BackendTask.dto.ModelInfoDto;
import ru.isands.BackendTask.dto.inputDto.ApplianceInputDto;
import ru.isands.BackendTask.exception.NotFoundException;
import ru.isands.BackendTask.mapper.ApplianceMapper;
import ru.isands.BackendTask.mapper.ModelMapper;
import ru.isands.BackendTask.model.Appliance;
import ru.isands.BackendTask.model.Model;
import ru.isands.BackendTask.repository.ApplianceRepository;
import ru.isands.BackendTask.repository.ModelRepository;
import ru.isands.BackendTask.service.searchAndFilter.SearchFilterService;
import ru.isands.BackendTask.validator.ApplianceValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplianceServiceImpl implements ApplianceService {

    private static final String ENTITY_NOT_FOUND = "Appliance not found.";
    private final ApplianceRepository applianceRepository;
    private final ModelRepository modelRepository;
    private final SearchFilterService searchFilterService;

    @Autowired
    public ApplianceServiceImpl(ApplianceRepository applianceRepository, ModelRepository modelRepository, SearchFilterService searchFilterService) {
        this.applianceRepository = applianceRepository;
        this.modelRepository = modelRepository;
        this.searchFilterService = searchFilterService;
    }

    public List<ApplianceDto> getAppliances() {
        return applianceRepository.findAll()
                .stream()
                .map(ApplianceMapper::toDto)
                .collect(Collectors.toList());
    }

    public ApplianceDto getApplianceById(Long applianceId) {
        return ApplianceMapper.toDto(applianceRepository.findById(applianceId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND)));
    }

    public ApplianceDto addAppliance(ApplianceInputDto applianceDto) {
        ApplianceValidator.isApplianceValid(applianceDto);
        Appliance appliance = ApplianceMapper.toAppliance(applianceDto);
        return ApplianceMapper.toDto(applianceRepository.save(appliance));
    }

    public ApplianceDto updateAppliance(Long applianceId, ApplianceInputDto applianceInputDto) {
        Appliance updatedAppliance = applianceRepository.findById(applianceId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND));
        updatedAppliance = applianceRepository.save(ApplianceMapper.updateAppliance(updatedAppliance, applianceInputDto));
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
        return searchFilterService.getWithSearch(name, applianceName, color, minPrice, maxPrice);
    }

    public List<ModelInfoDto> getWithFilter(String alphabet, String price) {
        Sort sort = searchFilterService.getSort(alphabet, price);
        return modelRepository.findAll(sort).stream()
                .map(ModelMapper::toInfoDto)
                .collect(Collectors.toList());
    }
}
