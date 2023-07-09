package ru.isands.BackendTask.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.isands.BackendTask.dto.ApplianceDto;
import ru.isands.BackendTask.dto.ModelInfoDto;
import ru.isands.BackendTask.dto.inputDto.ApplianceInputDto;
import ru.isands.BackendTask.mapper.ModelMapper;
import ru.isands.BackendTask.service.appliance.ApplianceService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class ApplianceController {

    private final ApplianceService applianceService;

    @Autowired
    public ApplianceController(ApplianceService applianceService) {
        this.applianceService = applianceService;
    }

    @Operation(summary = "Получить реестр видов техники.")
    @GetMapping
    public List<ApplianceDto> getAppliances() {
        return applianceService.getAppliances();
    }

    @Operation(summary = "Получить вид техники по его ID.")
    @GetMapping("/{applianceId}")
    public ApplianceDto getAppliance(@PathVariable Long applianceId) {
        return applianceService.getApplianceById(applianceId);
    }

    @Operation(summary = "Добавить вид техники.")
    @PostMapping
    public ApplianceDto addAppliance(@RequestBody ApplianceInputDto applianceInputDto) {
        return applianceService.addAppliance(applianceInputDto);
    }

    @Operation(summary = "Обновить вид техники.")
    @PatchMapping("/{applianceId}")
    public ApplianceDto updateAppliance(@PathVariable Long applianceId, @RequestBody ApplianceInputDto applianceInputDto) {
        return applianceService.updateAppliance(applianceId, applianceInputDto);
    }

    @Operation(summary = "Удалить вид техники.")
    @DeleteMapping("/{applianceId}")
    public Boolean deleteAppliance(@PathVariable Long applianceId) {
        return applianceService.deleteAppliance(applianceId);
    }
}
