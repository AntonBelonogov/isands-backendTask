package ru.isands.BackendTask.dto.inputDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplianceInputDto {
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

    private Map<String, Object> applianceAttributes;
}
