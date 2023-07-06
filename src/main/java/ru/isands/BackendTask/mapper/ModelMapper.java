package ru.isands.BackendTask.mapper;

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
                .build();
    }

    public static Model toModel(ModelInputDto modelDto) {
        Model model = new Model();
        model.setName(modelDto.getName());
        model.setSerialNumber(modelDto.getSerialNumber());
        model.setColor(modelDto.getColor());
        model.setSize(modelDto.getSize());
        model.setPrice(modelDto.getPrice());
        model.setAvailable(modelDto.getIsAvailable());
        return model;
    }

    public static Model updateModel(Model model, ModelInputDto modelDto) {
        model.setName(modelDto.getName() == null ?
                model.getName() : modelDto.getName());
        model.setSerialNumber(modelDto.getSerialNumber() == null ?
                model.getSerialNumber() : modelDto.getSerialNumber());
        model.setColor(modelDto.getColor() == null ?
                model.getColor() : modelDto.getColor());
        model.setSize(modelDto.getSize() == null ?
                model.getSize() : modelDto.getSize());
        model.setPrice(modelDto.getPrice() == null ?
                model.getPrice() : modelDto.getPrice());
        model.setAvailable(modelDto.getIsAvailable() == null ?
                model.getAvailable() : modelDto.getIsAvailable());
        return model;
    }
}
