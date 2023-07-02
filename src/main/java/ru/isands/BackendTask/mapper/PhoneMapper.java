package ru.isands.BackendTask.mapper;

import ru.isands.BackendTask.dto.FridgeDto;
import ru.isands.BackendTask.dto.PhoneDto;
import ru.isands.BackendTask.model.Model;

public class PhoneMapper {
    public static PhoneDto toDto(Model model) {
        return PhoneDto.builder()
                .id(model.getId())
                .name(model.getName())
                .serialNumber(model.getSerialNumber())
                .color(model.getColor())
                .size(model.getSize())
                .price(model.getPrice())
                .isAvailable(model.getAvailable())
                .numberOfCameras(model.getNumberOfCameras())
                .memory(model.getMemory())
                .appliance(ModelAppliancesMapper.toDto(model.getAppliance()))
                .build();
    }

    public static Model toModel(PhoneDto phoneDto) {
        Model model = ModelMapper.toModel(phoneDto);
        model.setNumberOfCameras(phoneDto.getNumberOfCameras());
        model.setMemory(phoneDto.getMemory());
        return model;
    }

    public static Model updateModel(Model model, PhoneDto phoneDto) {
        Model updatedModel = ModelMapper.updateModel(model, phoneDto);
        updatedModel.setNumberOfCameras(model.getNumberOfCameras() == null ?
                model.getNumberOfCameras() : phoneDto.getNumberOfCameras());
        updatedModel.setMemory(model.getMemory() == null ?
                model.getMemory() : phoneDto.getMemory());
        return updatedModel;
    }
}
