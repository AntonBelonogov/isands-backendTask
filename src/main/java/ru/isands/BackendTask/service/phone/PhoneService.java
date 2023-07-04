package ru.isands.BackendTask.service.phone;

import ru.isands.BackendTask.dto.PhoneDto;

import java.math.BigDecimal;
import java.util.List;

public interface PhoneService {
    List<PhoneDto> getPhones();

    PhoneDto getPhoneById(Long phoneId);

    PhoneDto addPhone(Long applianceId, PhoneDto phoneDto);

    PhoneDto updatePhone(Long phoneId, PhoneDto phoneDto);

    Boolean deletePhone(Long phoneId);

    List<PhoneDto> getWithSearch(
            String name,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Integer memory,
            Integer numberOfCameras
    );

    List<PhoneDto> getWithFilter(String alphabet, String price);
}
