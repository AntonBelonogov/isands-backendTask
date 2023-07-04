package ru.isands.BackendTask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class FridgeDto extends ModelDto {

    @Schema(description = "Колличество дверей.")
    private Integer numberOfDoors;

    @Schema(description = "Тип компрессора.")
    private String compressorType;

    @Schema(description = "Вид техники к которой он относиться.")
    private ApplianceModelDto appliance;
}
