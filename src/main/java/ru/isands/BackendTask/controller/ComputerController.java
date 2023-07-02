package ru.isands.BackendTask.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.isands.BackendTask.dto.ComputerDto;
import ru.isands.BackendTask.service.ComputerService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/computer")
public class ComputerController {

    private final ComputerService computerService;

    @Autowired
    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @Operation(summary = "Получить список всех компьютеров (в наличии).")
    @GetMapping
    public List<ComputerDto> getComputers() {
        return computerService.getComputers();
    }

    @Operation(summary = "Получить компьютер по ID.")
    @GetMapping("/{computerId}")
    public ComputerDto getComputerById(@PathVariable Long computerId) {
        return computerService.getComputerById(computerId);
    }

    @Operation(summary = "Добавить компьютер в Appliance.")
    @PostMapping("/{appliancesId}")
    public ComputerDto addComputer(@PathVariable Long appliancesId, @RequestBody @Valid ComputerDto computerDto) {
        return computerService.addComputer(appliancesId, computerDto);
    }

    @Operation(summary = "Обновить модель компьютера по ID.")
    @PatchMapping("/{computerId}")
    public ComputerDto updateComputer(@PathVariable Long computerId, @RequestBody ComputerDto computerDto) {
        return computerService.updateComputer(computerId, computerDto);
    }

    @Operation(summary = "Удалить модель компьютера по ID.")
    @DeleteMapping("/{computerId}")
    public Boolean deleteComputer(@PathVariable Long computerId) {
        return computerService.deleteComputer(computerId);
    }

    @Operation(summary = "Поиск по моделям компьютера с использованием фильтов.")
    @GetMapping("/search")
    public List<ComputerDto> getWithSearch(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String computerCategory,
            @RequestParam(required = false) String processorType
    ) {
        return computerService.getWithSearch(name, color, minPrice, maxPrice, computerCategory, processorType);
    }

    @Operation(summary = "Сортировка моделей компьютера по алфавиту и по стоимости.")
    @GetMapping("/filter")
    public List<ComputerDto> getWithFilter(
            @RequestParam(required = false, defaultValue = "asc") String alphabet,
            @RequestParam(required = false) String price
    ) {
        return computerService.getWithFilter(alphabet, price);
    }
}
