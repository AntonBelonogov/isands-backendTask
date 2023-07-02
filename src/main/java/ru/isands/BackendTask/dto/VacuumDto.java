package ru.isands.BackendTask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class VacuumDto extends ModelDto {

    @Schema(description = "Вместимость мешка пылесоса.")
    @NotBlank
    private Float dustBagVolume;

    @Schema(description = "Колличество режимов пылесоса.")
    @NotBlank
    private Integer numberOfModes;

    @Schema(description = "Вид техники к которой он относиться.")
    @NotBlank
    private ApplianceModelDto appliance;
}
