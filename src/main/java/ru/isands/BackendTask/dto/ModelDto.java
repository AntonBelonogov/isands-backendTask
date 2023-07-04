package ru.isands.BackendTask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class ModelDto {

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
