package ru.isands.BackendTask.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.isands.BackendTask.dto.PhoneDto;
import ru.isands.BackendTask.service.phone.PhoneService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/phone")
public class PhoneController {

    private final PhoneService phoneService;

    @Autowired
    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @Operation(summary = "Получить все модели телефонов (в наличии).")
    @GetMapping
    public List<PhoneDto> getPhones() {
        return phoneService.getPhones();
    }

    @Operation(summary = "Получить модель телефона по ID.")
    @GetMapping("/{phoneId}")
    public PhoneDto getPhoneById(@PathVariable Long phoneId) {
        return phoneService.getPhoneById(phoneId);
    }

    @Operation(summary = "Добавить модель телефона по ID реестра.")
    @PostMapping("/{applianceId}")
    public PhoneDto addPhone(@PathVariable Long applianceId, @RequestBody @Valid PhoneDto phoneDto) {
        return phoneService.addPhone(applianceId, phoneDto);
    }

    @Operation(summary = "Обновить модель телефона по ID.")
    @PatchMapping("/{phoneId}")
    public PhoneDto updatePhone(@PathVariable Long phoneId, @RequestBody PhoneDto phoneDto) {
        return phoneService.updatePhone(phoneId, phoneDto);
    }

    @Operation(summary = "Удалить модель телефона по ID.")
    @DeleteMapping("/{phoneId}")
    public Boolean deletePhone(@PathVariable Long phoneId) {
        return phoneService.deletePhone(phoneId);
    }

    @Operation(summary = "Поиск по моделям телефона с использованием фильтов.")
    @GetMapping("/search")
    public List<PhoneDto> getWithSearch(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Integer memory,
            @RequestParam(required = false) Integer numberOfCameras
    ) {
        return phoneService.getWithSearch(name, color, minPrice, maxPrice, memory, numberOfCameras);
    }

    @Operation(summary = "Сортировка моделей телефона по алфавиту и по стоимости.")
    @GetMapping("/filter")
    public List<PhoneDto> getWithFilter(
            @RequestParam(required = false, defaultValue = "asc") String alphabet,
            @RequestParam(required = false) String price
    ) {
        return phoneService.getWithFilter(alphabet, price);
    }
}
