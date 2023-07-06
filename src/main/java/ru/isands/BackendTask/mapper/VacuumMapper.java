package ru.isands.BackendTask.mapper;

import ru.isands.BackendTask.dto.VacuumDto;
import ru.isands.BackendTask.dto.inputDto.VacuumInputDto;
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

    public static Model toModel(VacuumInputDto vacuumInputDto) {
        Model model = ModelMapper.toModel(vacuumInputDto);
        model.setDustBagVolume(vacuumInputDto.getDustBagVolume());
        model.setNumberOfModes(vacuumInputDto.getNumberOfModes());
        return model;
    }

    public static Model updateModel(Model model, VacuumInputDto vacuumInputDto) {
        Model updatedModel = ModelMapper.updateModel(model, vacuumInputDto);
        updatedModel.setDustBagVolume(model.getDustBagVolume() == null ?
                model.getDustBagVolume() : vacuumInputDto.getDustBagVolume());
        updatedModel.setNumberOfModes(model.getNumberOfModes() == null ?
                model.getNumberOfModes() : vacuumInputDto.getNumberOfModes());
        return updatedModel;
    }
}
