package ru.isands.BackendTask.mapper;

import ru.isands.BackendTask.dto.VacuumDto;
import ru.isands.BackendTask.model.Model;

public class VacuumMapper {
    public static VacuumDto toDto(Model model) {
        return VacuumDto.builder()
                .id(model.getId())
                .name(model.getName())
                .serialNumber(model.getSerialNumber())
                .color(model.getColor())
                .size(model.getSize())
                .price(model.getPrice())
                .isAvailable(model.getAvailable())
                .dustBagVolume(model.getDustBagVolume())
                .numberOfModes(model.getNumberOfModes())
                .appliance(ModelAppliancesMapper.toDto(model.getAppliance()))
                .build();
    }

    public static Model toModel(VacuumDto vacuumDto) {
        Model model = ModelMapper.toModel(vacuumDto);
        model.setDustBagVolume(vacuumDto.getDustBagVolume());
        model.setNumberOfModes(vacuumDto.getNumberOfModes());
        return model;
    }

    public static Model updateModel(Model model, VacuumDto vacuumDto) {
        Model updatedModel = ModelMapper.updateModel(model, vacuumDto);
        updatedModel.setDustBagVolume(model.getDustBagVolume() == null ?
                model.getDustBagVolume() : vacuumDto.getDustBagVolume());
        updatedModel.setNumberOfModes(model.getNumberOfModes() == null ?
                model.getNumberOfModes() : vacuumDto.getNumberOfModes());
        return updatedModel;
    }
}
