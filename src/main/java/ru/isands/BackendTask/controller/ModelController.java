package ru.isands.BackendTask.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.isands.BackendTask.dto.ModelDto;
import ru.isands.BackendTask.dto.inputDto.ModelInputDto;
import ru.isands.BackendTask.service.model.ModelService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/model")
public class ModelController {

    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @Operation(summary = "Получить список всех моделей (в наличии).")
    @GetMapping
    public List<ModelDto> getModels() {
        return modelService.getModels();
    }

    @Operation(summary = "Получить список всех моделей из категории.")
    @GetMapping("byCategory/{category}")
    public List<ModelDto> getModelByCategory(@PathVariable String category) {
        return modelService.getModelByCategory(category);
    }

    @Operation(summary = "Получить модель по ID.")
    @GetMapping("/{modelId}")
    public ModelDto getModelById(@PathVariable Long modelId) {
        return modelService.getModelById(modelId);
    }

    @Operation(summary = "Добавить модель в Appliance.")
    @PostMapping("/{appliancesId}")
    public ModelDto addComputer(@PathVariable Long appliancesId, @RequestBody ModelInputDto modelInputDto) {
        return modelService.addModel(appliancesId, modelInputDto);
    }

    @Operation(summary = "Обновить модель по ID.")
    @PatchMapping("/{modelId}")
    public ModelDto updateComputer(@PathVariable Long modelId, @RequestBody ModelInputDto computerDto) {
        return modelService.updateModel(modelId, computerDto);
    }

    @Operation(summary = "Удалить модель по ID.")
    @DeleteMapping("/{modelId}")
    public Boolean deleteComputer(@PathVariable Long modelId) {
        return modelService.deleteModel(modelId);
    }

    @Operation(summary = "Поиск по моделям компьютера с использованием фильтов.")
    @GetMapping("/search")
    public List<ModelDto> getWithSearch(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String applianceName,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Map<String, Object> attributeMap
    ) {
        return modelService.getWithSearch(name, color, applianceName, minPrice, maxPrice, attributeMap);
    }

    @Operation(summary = "Сортировка моделей по алфавиту и по стоимости.")
    @GetMapping("/filter")
    public List<ModelDto> getWithFilter(
            @RequestParam(required = false, defaultValue = "asc") String alphabet,
            @RequestParam(required = false) String price
    ) {
        return modelService.getWithFilter(alphabet, price);
    }
}
