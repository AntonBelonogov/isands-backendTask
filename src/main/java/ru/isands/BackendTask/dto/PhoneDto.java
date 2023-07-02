package ru.isands.BackendTask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class PhoneDto extends ModelDto {

    @Schema(description = "Количество памяти телефона.")
    @NotBlank
    private Integer memory;

    @Schema(description = "Колличество камер телефона.")
    @NotBlank
    private Integer numberOfCameras;

    @Schema(description = "Вид техники к которой он относиться.")
    @NotBlank
    private ApplianceModelDto appliance;
}
