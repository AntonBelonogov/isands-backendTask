package ru.isands.BackendTask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class FridgeDto extends ModelDto {

    @Schema(description = "Колличество дверей.")
    @NotBlank
    private Integer numberOfDoors;

    @Schema(description = "Тип компрессора.")
    @NotBlank
    private String compressorType;

    @Schema(description = "Вид техники к которой он относиться.")
    private ApplianceModelDto appliance;
}
