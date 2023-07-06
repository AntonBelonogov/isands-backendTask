package ru.isands.BackendTask.service.television;

import ru.isands.BackendTask.dto.TelevisionDto;
import ru.isands.BackendTask.dto.inputDto.TelevisionInputDto;

import java.math.BigDecimal;
import java.util.List;

public interface TelevisionService {

    List<TelevisionDto> getTelevision();

    TelevisionDto getTelevisionById(Long televisionId);

    TelevisionDto addTelevision(Long applianceId, TelevisionInputDto televisionDto);

    TelevisionDto updateTelevision(Long televisionId, TelevisionInputDto televisionDto);

    Boolean deletePhone(Long televisionId);

    List<TelevisionDto> getWithSearch(
            String name,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            String televisionCategory,
            String televisionTechnology
    );

    List<TelevisionDto> getWithFilter(String alphabet, String price);
}
