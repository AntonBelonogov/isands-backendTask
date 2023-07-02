package ru.isands.BackendTask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
public class AppliancesDto {

    private Long id;

    @NotBlank
    @Schema(description = "Наиминование вида техники.")
    private String name;

    @NotBlank
    @Schema(description = "Страна производитель.")
    private String country;

    @NotBlank
    @Schema(description = "Фирма производитель.")
    private String manufacturer;

    @NotBlank
    @Schema(description = "Возможность заказа онлайн.")
    private Boolean onlineOrder;

    @NotBlank
    @Schema(description = "Возможность оформления рассрочки.")
    private Boolean installment;

    @Schema(description = "Модели в наличии.")
    private List<ModelInfoDto> models;
}
