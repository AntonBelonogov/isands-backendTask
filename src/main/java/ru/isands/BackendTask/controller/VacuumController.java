package ru.isands.BackendTask.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.isands.BackendTask.dto.VacuumDto;
import ru.isands.BackendTask.service.vacuum.VacuumService;
import ru.isands.BackendTask.service.vacuum.VacuumServiceImpl;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/vacuum")
public class VacuumController {

    private final VacuumService vacuumService;

    @Autowired
    public VacuumController(VacuumService vacuumService) {
        this.vacuumService = vacuumService;
    }

    @Operation(summary = "Получить все модели холодильников (в наличии).")
    @GetMapping
    public List<VacuumDto> getVacuums() {
        return vacuumService.getVacuums();
    }

    @Operation(summary = "Получить модель холодильника по ID.")
    @GetMapping("/{vacuumId}")
    public VacuumDto getVacuumById(@PathVariable Long vacuumId) {
        return vacuumService.getVacuumById(vacuumId);
    }

    @Operation(summary = "Добавить модель холодильника в реестр по ID.")
    @PostMapping("/{applianceId}")
    public VacuumDto addVacuum(@PathVariable Long applianceId, @Valid @RequestBody VacuumDto vacuumDto) {
        return vacuumService.addVacuum(applianceId, vacuumDto);
    }

    @Operation(summary = "Обновить модель холодильника по ID.")
    @PatchMapping("/{vacuumId}")
    public VacuumDto updateVacuum(@PathVariable Long vacuumId, @RequestBody VacuumDto vacuumDto) {
        return vacuumService.updateVacuum(vacuumId, vacuumDto);
    }

    @Operation(summary = "Удалить модель холодильника по ID.")
    @DeleteMapping("/{vacuumId}")
    public Boolean deleteVacuum(@PathVariable Long vacuumId) {
        return vacuumService.deleteVacuum(vacuumId);
    }

    @Operation(summary = "Поиск по моделям холодильника с использованием фильтов.")
    @GetMapping("/search")
    public List<VacuumDto> getWithSearch(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Float dustBagVolume,
            @RequestParam(required = false) Integer numberOfModes
    ) {
        return vacuumService.getWithSearch(name, color, minPrice, maxPrice, dustBagVolume, numberOfModes);
    }

    @Operation(summary = "Сортировка моделей холодильника по алфавиту и по стоимости.")
    @GetMapping("/filter")
    public List<VacuumDto> getWithFilter(
            @RequestParam(required = false, defaultValue = "asc") String alphabet,
            @RequestParam(required = false) String price
    ) {
        return vacuumService.getWithFilter(alphabet, price);
    }
}
