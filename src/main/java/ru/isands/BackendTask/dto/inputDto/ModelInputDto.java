package ru.isands.BackendTask.dto.inputDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelInputDto {

    @Schema(description = "Наиминование модели.")
    private String name;

    @Schema(description = "Серийный номер модели.")
    private String serialNumber;

    @Schema(description = "Цвет модели.")
    private String color;

    @Schema(description = "Размер модели.")
    private Float size;

    @Schema(description = "Цена модели.")
    private BigDecimal price;

    @Schema(description = "Наличие модели.")
    private Boolean isAvailable;

    @Schema(description = "Атрибуты модели.")
    Map<String, Object> attributes;
}
