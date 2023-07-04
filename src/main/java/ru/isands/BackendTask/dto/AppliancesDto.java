package ru.isands.BackendTask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppliancesDto {

    @Schema(description = "Наиминование вида техники.")
    private String name;

    @Schema(description = "Страна производитель.")
    private String country;

    @Schema(description = "Фирма производитель.")
    private String manufacturer;

    @Schema(description = "Возможность заказа онлайн.")
    private Boolean onlineOrder;

    @Schema(description = "Возможность оформления рассрочки.")
    private Boolean installment;

    @Schema(description = "Модели в наличии.")
    private List<ModelInfoDto> models;
}
