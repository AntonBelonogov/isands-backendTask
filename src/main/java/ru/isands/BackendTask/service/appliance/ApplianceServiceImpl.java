package ru.isands.BackendTask.service.appliance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isands.BackendTask.dto.ApplianceDto;
import ru.isands.BackendTask.dto.inputDto.ApplianceInputDto;
import ru.isands.BackendTask.exception.NotFoundException;
import ru.isands.BackendTask.mapper.ApplianceMapper;
import ru.isands.BackendTask.model.Appliance;
import ru.isands.BackendTask.repository.ApplianceRepository;
import ru.isands.BackendTask.repository.ModelRepository;
import ru.isands.BackendTask.validator.ApplianceValidator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplianceServiceImpl implements ApplianceService {

    private static final String ENTITY_NOT_FOUND = "Appliance not found.";
    private final ApplianceRepository applianceRepository;
    private final ModelRepository modelRepository;

    @Autowired
    public ApplianceServiceImpl(ApplianceRepository applianceRepository, ModelRepository modelRepository) {
        this.applianceRepository = applianceRepository;
        this.modelRepository = modelRepository;
    }

    public List<ApplianceDto> getAppliances() {
        List<Appliance> a = applianceRepository.findAll();

        return a
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
}
