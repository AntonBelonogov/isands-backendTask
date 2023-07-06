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
public class ComputerDto extends ModelDto {

    @Schema(description = "Категория компьютера.")
    private String computerCategory;

    @Schema(description = "Тип процесора (название).")
    private String processorType;

    @Schema(description = "Вид техники к которой он относиться.")
    private ApplianceModelDto appliance;

}
