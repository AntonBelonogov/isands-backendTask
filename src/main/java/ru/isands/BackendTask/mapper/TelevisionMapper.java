package ru.isands.BackendTask.mapper;

import ru.isands.BackendTask.dto.TelevisionDto;
import ru.isands.BackendTask.dto.inputDto.TelevisionInputDto;
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

    public static Model toModel(TelevisionInputDto televisionInputDto) {
        Model model = ModelMapper.toModel(televisionInputDto);
        model.setTelevisionCategory(televisionInputDto.getTelevisionCategory());
        model.setTelevisionTechnology(televisionInputDto.getTelevisionTechnology());
        return model;
    }

    public static Model updateModel(Model model, TelevisionInputDto televisionInputDto) {
        Model updatedModel = ModelMapper.updateModel(model, televisionInputDto);
        updatedModel.setTelevisionCategory(model.getTelevisionCategory() == null ?
                model.getProcessorType() : televisionInputDto.getTelevisionCategory());
        updatedModel.setTelevisionTechnology(model.getTelevisionTechnology() == null ?
                model.getTelevisionTechnology() : televisionInputDto.getTelevisionTechnology());
        return updatedModel;
    }
}
