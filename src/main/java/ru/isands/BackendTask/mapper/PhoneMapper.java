package ru.isands.BackendTask.mapper;

import ru.isands.BackendTask.dto.FridgeDto;
import ru.isands.BackendTask.dto.PhoneDto;
import ru.isands.BackendTask.dto.inputDto.PhoneInputDto;
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

    public static Model toModel(PhoneInputDto phoneInputDto) {
        Model model = ModelMapper.toModel(phoneInputDto);
        model.setNumberOfCameras(phoneInputDto.getNumberOfCameras());
        model.setMemory(phoneInputDto.getMemory());
        return model;
    }

    public static Model updateModel(Model model, PhoneInputDto phoneInputDto) {
        Model updatedModel = ModelMapper.updateModel(model, phoneInputDto);
        updatedModel.setNumberOfCameras(model.getNumberOfCameras() == null ?
                model.getNumberOfCameras() : phoneInputDto.getNumberOfCameras());
        updatedModel.setMemory(model.getMemory() == null ?
                model.getMemory() : phoneInputDto.getMemory());
        return updatedModel;
    }
}
