package ru.isands.BackendTask.mapper;

import ru.isands.BackendTask.dto.ModelDto;
import ru.isands.BackendTask.dto.ModelInfoDto;
import ru.isands.BackendTask.model.Model;

public class ModelMapper {

    public static ModelDto toDto(Model model) {
        return null; //TODO Разабраться с этим
    }

    public static ModelInfoDto toInfoDto(Model model) {
        return ModelInfoDto.builder()
                .id(model.getId())
                .name(model.getName())
                .serialNumber(model.getSerialNumber())
                .color(model.getColor())
                .size(model.getSize())
                .price(model.getPrice())
                .isAvailable(model.getAvailable())
                .build();
    }

    public static Model toModel(ModelDto modelDto) {
        Model model = new Model();
        model.setId(modelDto.getId());
        model.setName(modelDto.getName());
        model.setSerialNumber(modelDto.getSerialNumber());
        model.setColor(modelDto.getColor());
        model.setSize(modelDto.getSize());
        model.setPrice(modelDto.getPrice());
        model.setAvailable(modelDto.getIsAvailable());
        return model;
    }

    public static Model toModelFromModelInfo(ModelInfoDto modelInfoDto) {
        Model model = new Model();
        model.setId(modelInfoDto.getId());
        model.setName(modelInfoDto.getName());
        model.setSerialNumber(modelInfoDto.getSerialNumber());
        model.setColor(modelInfoDto.getColor());
        model.setSize(modelInfoDto.getSize());
        model.setPrice(modelInfoDto.getPrice());
        model.setAvailable(modelInfoDto.getIsAvailable());
        return model;
    }

    public static Model updateModel(Model model, ModelDto modelDto) {
        model.setId(modelDto.getId() == null ?
                model.getId() : modelDto.getId());
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
