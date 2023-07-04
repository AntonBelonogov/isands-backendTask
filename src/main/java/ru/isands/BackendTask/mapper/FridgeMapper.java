package ru.isands.BackendTask.mapper;

import ru.isands.BackendTask.dto.ComputerDto;
import ru.isands.BackendTask.dto.FridgeDto;
import ru.isands.BackendTask.model.Model;

public class FridgeMapper {

    public static FridgeDto toDto(Model model) {
        return FridgeDto.builder()
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

    public static Model toModel(FridgeDto fridgeDto) {
        Model model = ModelMapper.toModel(fridgeDto);
        model.setCompressorType(fridgeDto.getCompressorType());
        model.setNumberOfDoors(fridgeDto.getNumberOfDoors());
        return model;
    }

    public static Model updateModel(Model model, FridgeDto fridgeDto) {
        Model updatedModel = ModelMapper.updateModel(model, fridgeDto);
        updatedModel.setCompressorType(model.getCompressorType() == null ?
                model.getCompressorType() : fridgeDto.getCompressorType());
        updatedModel.setNumberOfDoors(model.getNumberOfDoors() == null ?
                model.getNumberOfDoors() : fridgeDto.getNumberOfDoors());
        return updatedModel;
    }
}
