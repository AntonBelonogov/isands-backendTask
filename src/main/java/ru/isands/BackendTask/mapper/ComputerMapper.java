package ru.isands.BackendTask.mapper;

import ru.isands.BackendTask.dto.ComputerDto;
import ru.isands.BackendTask.model.Model;

public class ComputerMapper {
    public static ComputerDto toDto(Model model) {
        return ComputerDto.builder()
                .name(model.getName())
                .serialNumber(model.getSerialNumber())
                .color(model.getColor())
                .size(model.getSize())
                .price(model.getPrice())
                .isAvailable(model.getAvailable())
                .computerCategory(model.getComputerCategory())
                .processorType(model.getProcessorType())
                .appliance(ModelAppliancesMapper.toDto(model.getAppliance()))
                .build();
    }

    public static Model toModel(ComputerDto computerDto) {
        Model model = ModelMapper.toModel(computerDto);
        model.setProcessorType(computerDto.getProcessorType());
        model.setComputerCategory(computerDto.getComputerCategory());
        return model;
    }

    public static Model updateModel(Model model, ComputerDto computerDto) {
        Model updatedModel = ModelMapper.updateModel(model, computerDto);
        updatedModel.setProcessorType(model.getProcessorType() == null ?
                model.getProcessorType() : computerDto.getProcessorType());
        updatedModel.setComputerCategory(model.getComputerCategory() == null ?
                model.getComputerCategory() : computerDto.getComputerCategory());
        return updatedModel;
    }
}
