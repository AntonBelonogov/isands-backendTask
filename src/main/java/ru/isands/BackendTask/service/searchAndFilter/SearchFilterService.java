package ru.isands.BackendTask.service.searchAndFilter;

import org.springframework.data.domain.Sort;
import ru.isands.BackendTask.model.Model;

import java.math.BigDecimal;
import java.util.List;

public interface SearchFilterService {
    List<Model> getWithSearch(
            String name,
            String applianceName,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice
    );

    Sort getSort(String alphabet, String price);

}
