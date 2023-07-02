package ru.isands.BackendTask.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.isands.BackendTask.dto.PhoneDto;
import ru.isands.BackendTask.dto.TelevisionDto;
import ru.isands.BackendTask.service.TelevisionService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/television")
public class TelevisionController {

    private final TelevisionService televisionService;

    @Autowired
    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @Operation(summary = "Получить все модели телевизоров (в наличии).")
    @GetMapping
    public List<TelevisionDto> getTelevisions() {
        return televisionService.getTelevision();
    }

    @Operation(summary = "Получить модель телевизора по ID.")
    @GetMapping("/{televisionId}")
    public TelevisionDto getTelevisionById(@PathVariable Long televisionId) {
        return televisionService.getTelevisionById(televisionId);
    }

    @Operation(summary = "Добавить модель телевизора по реестра ID.")
    @PostMapping("/{applianceId}")
    public TelevisionDto addTelevision(@PathVariable Long applianceId, @RequestBody @Valid TelevisionDto televisionDto) {
        return televisionService.addTelevision(applianceId, televisionDto);
    }

    @Operation(summary = "Обновить модель телевизора по ID.")
    @PatchMapping("/{televisionId}")
    public TelevisionDto updatePhone(@PathVariable Long televisionId, @RequestBody TelevisionDto televisionDto) {
        return televisionService.updateTelevision(televisionId, televisionDto);
    }

    @Operation(summary = "Удалить модель телевизора по ID.")
    @DeleteMapping("/{televisionId}")
    public Boolean deletePhone(@PathVariable Long televisionId) {
        return televisionService.deletePhone(televisionId);
    }

    @Operation(summary = "Поиск по моделям телевизора с использованием фильтов.")
    @GetMapping("/search")
    public List<TelevisionDto> getWithSearch(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String televisionCategory,
            @RequestParam(required = false) String televisionTechnology
    ) {
        return televisionService.getWithSearch(name, color, minPrice, maxPrice, televisionCategory, televisionTechnology);
    }

    @Operation(summary = "Сортировка моделей телевизора по алфавиту и по стоимости.")
    @GetMapping("/filter")
    public List<TelevisionDto> getWithFilter(
            @RequestParam(required = false, defaultValue = "asc") String alphabet,
            @RequestParam(required = false) String price
    ) {
        return televisionService.getWithFilter(alphabet, price);
    }
}
