package ru.isands.BackendTask.mapper;

import ru.isands.BackendTask.dto.TelevisionDto;
import ru.isands.BackendTask.model.Model;

public class TelevisionMapper {
    public static TelevisionDto toDto(Model model) {
        return TelevisionDto.builder()
                .id(model.getId())
                .name(model.getName())
                .serialNumber(model.getSerialNumber())
                .color(model.getColor())
                .size(model.getSize())
                .price(model.getPrice())
                .isAvailable(model.getAvailable())
                .televisionCategory(model.getTelevisionCategory())
                .televisionTechnology(model.getTelevisionTechnology())
                .appliance(ModelAppliancesMapper.toDto(model.getAppliance()))
                .build();
    }

    public static Model toModel(TelevisionDto televisionDto) {
        Model model = ModelMapper.toModel(televisionDto);
        model.setTelevisionCategory(televisionDto.getTelevisionCategory());
        model.setTelevisionTechnology(televisionDto.getTelevisionTechnology());
        return model;
    }

    public static Model updateModel(Model model, TelevisionDto televisionDto) {
        Model updatedModel = ModelMapper.updateModel(model, televisionDto);
        updatedModel.setTelevisionCategory(model.getTelevisionCategory() == null ?
                model.getProcessorType() : televisionDto.getTelevisionCategory());
        updatedModel.setTelevisionTechnology(model.getTelevisionTechnology() == null ?
                model.getTelevisionTechnology() : televisionDto.getTelevisionTechnology());
        return updatedModel;
    }
}
