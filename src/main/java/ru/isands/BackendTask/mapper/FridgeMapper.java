package ru.isands.BackendTask.mapper;

import ru.isands.BackendTask.dto.ComputerDto;
import ru.isands.BackendTask.dto.FridgeDto;
import ru.isands.BackendTask.dto.inputDto.FridgeInputDto;
import ru.isands.BackendTask.model.Model;

public class FridgeMapper {

    public static FridgeDto toDto(Model model) {
        return FridgeDto.builder()
                .id(model.getId())
                .name(model.getName())
                .serialNumber(model.getSerialNumber())
                .color(model.getColor())
                .size(model.getSize())
                .price(model.getPrice())
                .isAvailable(model.getAvailable())
                .compressorType(model.getCompressorType())
                .numberOfDoors(model.getNumberOfDoors())
                .appliance(ModelAppliancesMapper.toDto(model.getAppliance()))
                .build();
    }

    public static Model toModel(FridgeInputDto fridgeInputDto) {
        Model model = ModelMapper.toModel(fridgeInputDto);
        model.setCompressorType(fridgeInputDto.getCompressorType());
        model.setNumberOfDoors(fridgeInputDto.getNumberOfDoors());
        return model;
    }

    public static Model updateModel(Model model, FridgeInputDto fridgeInputDto) {
        Model updatedModel = ModelMapper.updateModel(model, fridgeInputDto);
        updatedModel.setCompressorType(model.getCompressorType() == null ?
                model.getCompressorType() : fridgeInputDto.getCompressorType());
        updatedModel.setNumberOfDoors(model.getNumberOfDoors() == null ?
                model.getNumberOfDoors() : fridgeInputDto.getNumberOfDoors());
        return updatedModel;
    }
}
