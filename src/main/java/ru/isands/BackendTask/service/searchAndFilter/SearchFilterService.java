package ru.isands.BackendTask.service.searchAndFilter;

import org.springframework.data.domain.Sort;
import ru.isands.BackendTask.model.Model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface SearchFilterService {
    List<Model> getWithSearch(
            String name,
            String color,
            String applianceName,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Map<String, Object> attributeMap
    );

    Sort getSort(String alphabet, String price);

}
