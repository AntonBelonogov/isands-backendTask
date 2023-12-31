package ru.isands.BackendTask.dto.inputDto;

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
public class FridgeInputDto extends ModelInputDto {

    @Schema(description = "Колличество дверей.")
    private Integer numberOfDoors;

    @Schema(description = "Тип компрессора.")
    private String compressorType;
}
