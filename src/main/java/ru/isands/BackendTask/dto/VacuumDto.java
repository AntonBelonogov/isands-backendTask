package ru.isands.BackendTask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class VacuumDto extends ModelDto {

    @Schema(description = "Вместимость мешка пылесоса.")
    private Float dustBagVolume;

    @Schema(description = "Колличество режимов пылесоса.")
    private Integer numberOfModes;

    @Schema(description = "Вид техники к которой он относиться.")
    private ApplianceModelDto appliance;
}
