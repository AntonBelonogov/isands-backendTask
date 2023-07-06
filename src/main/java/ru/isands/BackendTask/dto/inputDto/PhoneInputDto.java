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
public class PhoneInputDto extends ModelInputDto {

    @Schema(description = "Количество памяти телефона.")
    private Integer memory;

    @Schema(description = "Колличество камер телефона.")
    private Integer numberOfCameras;
}
