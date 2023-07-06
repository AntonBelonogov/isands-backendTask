package ru.isands.BackendTask.mapper;

import ru.isands.BackendTask.dto.ComputerDto;
import ru.isands.BackendTask.dto.inputDto.ComputerInputDto;
import ru.isands.BackendTask.model.Model;

public class ComputerMapper {
    public static ComputerDto toDto(Model model) {
        return ComputerDto.builder()
                .id(model.getId())
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

    public static Model toModel(ComputerInputDto computerInputDto) {
        Model model = ModelMapper.toModel(computerInputDto);
        model.setProcessorType(computerInputDto.getProcessorType());
        model.setComputerCategory(computerInputDto.getComputerCategory());
        return model;
    }

    public static Model updateModel(Model model, ComputerInputDto computerInputDto) {
        Model updatedModel = ModelMapper.updateModel(model, computerInputDto);
        updatedModel.setProcessorType(model.getProcessorType() == null ?
                model.getProcessorType() : computerInputDto.getProcessorType());
        updatedModel.setComputerCategory(model.getComputerCategory() == null ?
                model.getComputerCategory() : computerInputDto.getComputerCategory());
        return updatedModel;
    }
}
