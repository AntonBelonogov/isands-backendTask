package ru.isands.BackendTask.mapper;

import ru.isands.BackendTask.dto.ModelDto;
import ru.isands.BackendTask.dto.ModelInfoDto;
import ru.isands.BackendTask.dto.inputDto.ModelInputDto;
import ru.isands.BackendTask.model.Model;

public class ModelMapper {

    public static ModelInfoDto toInfoDto(Model model) {
        return ModelInfoDto.builder()
                .name(model.getName())
                .serialNumber(model.getSerialNumber())
                .color(model.getColor())
                .size(model.getSize())
                .price(model.getPrice())
                .isAvailable(model.getAvailable())
                .attributes(model.getModelAttributes())
                .build();
    }

    public static ModelDto toDto(Model model) {
        return ModelDto.builder()
                .id(model.getId())
                .name(model.getName())
                .serialNumber(model.getSerialNumber())
                .color(model.getColor())
                .size(model.getSize())
                .price(model.getPrice())
                .isAvailable(model.getAvailable())
                .attributes(model.getModelAttributes())
                .appliance(ApplianceMapper.toModelDto(model.getAppliance()))
                .build();
    }

    public static Model toModel(ModelInputDto modelInputDto) {
        Model model = new Model();
        model.setName(modelInputDto.getName());
        model.setSerialNumber(modelInputDto.getSerialNumber());
        model.setColor(modelInputDto.getColor());
        model.setSize(modelInputDto.getSize());
        model.setPrice(modelInputDto.getPrice());
        model.setAvailable(modelInputDto.getIsAvailable());
        model.setModelAttributes(modelInputDto.getAttributes());
        return model;
    }

    public static Model updateModel(Model model, ModelInputDto modelInputDto) {
        model.setName(modelInputDto.getName() == null ?
                model.getName() : modelInputDto.getName());
        model.setSerialNumber(modelInputDto.getSerialNumber() == null ?
                model.getSerialNumber() : modelInputDto.getSerialNumber());
        model.setColor(modelInputDto.getColor() == null ?
                model.getColor() : modelInputDto.getColor());
        model.setSize(modelInputDto.getSize() == null ?
                model.getSize() : modelInputDto.getSize());
        model.setPrice(modelInputDto.getPrice() == null ?
                model.getPrice() : modelInputDto.getPrice());
        model.setAvailable(modelInputDto.getIsAvailable() == null ?
                model.getAvailable() : modelInputDto.getIsAvailable());
        model.setModelAttributes(modelInputDto.getAttributes() == null ?
                model.getModelAttributes() : modelInputDto.getAttributes());
        return model;
    }
}
