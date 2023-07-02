package ru.isands.BackendTask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplianceModelDto {
    private Long id;

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
}
