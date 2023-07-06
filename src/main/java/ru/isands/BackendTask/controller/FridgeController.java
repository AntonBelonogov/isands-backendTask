package ru.isands.BackendTask.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.isands.BackendTask.dto.FridgeDto;
import ru.isands.BackendTask.dto.inputDto.FridgeInputDto;
import ru.isands.BackendTask.service.fridge.FridgeService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/fridge")
public class FridgeController {
    private final FridgeService fridgeService;

    @Autowired
    public FridgeController(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
    }

    @Operation(summary = "Получить реестр холодильников (в наличии).")
    @GetMapping
    public List<FridgeDto> getFridges() {
        return fridgeService.getFridges();
    }

    @Operation(summary = "Получить реестр холодильника по ID.")
    @GetMapping("/{fridgeId}")
    public FridgeDto getFridge(@PathVariable Long fridgeId) {
        return fridgeService.getFridge(fridgeId);
    }

    @Operation(summary = "Добавить в реестр по ID холодильник.")
    @PostMapping("/{applianceId}")
    public FridgeDto addFridge(@PathVariable Long applianceId, @RequestBody FridgeInputDto fridgeInputDto) {
        return fridgeService.addFridge(applianceId, fridgeInputDto);
    }

    @Operation(summary = "Обновить холодильник по ID.")
    @PatchMapping("/{fridgeId}")
    public FridgeDto updateFridge(@PathVariable Long fridgeId, @RequestBody FridgeInputDto fridgeInputDto) {
        return fridgeService.updateFridge(fridgeId, fridgeInputDto);
    }

    @Operation(summary = "Удалить холодильник по ID.")
    @DeleteMapping("/{fridgeId}")
    public Boolean deleteFridge(@PathVariable Long fridgeId) {
        return fridgeService.deleteFridge(fridgeId);
    }

    @Operation(summary = "Поиск по моделям с использованием фильтов.")
    @GetMapping("/search")
    public List<FridgeDto> getWithSearch(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Integer numberOfDoors,
            @RequestParam(required = false) String compressorType
    ) {
        return fridgeService.getWithSearch(name, color, minPrice, maxPrice, numberOfDoors, compressorType);
    }

    @Operation(summary = "Сортировка моделей техники по алфавиту и по стоимости.")
    @GetMapping("/filter")
    public List<FridgeDto> getWithFilter(
            @RequestParam(required = false, defaultValue = "asc") String alphabet,
            @RequestParam(required = false) String price
    ) {
        return fridgeService.getWithFilter(alphabet, price);
    }
}
