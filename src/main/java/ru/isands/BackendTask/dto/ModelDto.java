package ru.isands.BackendTask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder(toBuilder = true)
public abstract class ModelDto {
    private Long id;

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
}
