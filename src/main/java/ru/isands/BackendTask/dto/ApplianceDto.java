package ru.isands.BackendTask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isands.BackendTask.converter.HashMapConverter;

import javax.persistence.Convert;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplianceDto {
    @Schema(description = "ID вида.")
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

    @Schema(description = "Атрибуты вида техники.")
    @Convert(converter = HashMapConverter.class)
    private Map<String, String> applianceAttributes;

    @Schema(description = "Модели в наличии.")
    private List<ModelInfoDto> models;
}
