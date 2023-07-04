package ru.isands.BackendTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.isands.BackendTask.enums.SortType;
import ru.isands.BackendTask.exception.UnknownSortTypeException;
import ru.isands.BackendTask.model.Model;
import ru.isands.BackendTask.repository.ModelRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SearchFilterService {
    private final ModelRepository modelRepository;

    @Autowired
    public SearchFilterService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<Model> getWithSearch(
            String name,
            String applianceName,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice
    ) {
        List<Model> models;

        if (applianceName != null) {
            models = modelRepository.findAllByAppliance_Name(applianceName);
        } else {
            models = modelRepository.findAll();
        }
        if (name != null) {
            models.retainAll(modelRepository.findAllByNameIgnoreCase(name));
        }
        if (color != null) {
            models.retainAll(modelRepository.findAllByColorIgnoreCase(color));
        }
        if (minPrice != null || maxPrice != null) {
            if (minPrice == null) {
                minPrice = BigDecimal.valueOf(0);
            }
            if (maxPrice == null) {
                maxPrice = BigDecimal.valueOf(99999999);
            }
            models.retainAll(modelRepository.findAllByPriceBetween(minPrice, maxPrice));
        }
        return models;
    }

    public Sort getSort(String alphabet, String price) {
        if (alphabet != null && price == null) {
            return Sort.by(checkType(alphabet) == SortType.ASC ?
                    Sort.Direction.ASC : Sort.Direction.DESC, "name");
        }
        if (price != null && alphabet == null) {
            return Sort.by(checkType(price) == SortType.ASC ?
                    Sort.Direction.ASC : Sort.Direction.DESC, "price");
        }
        if (alphabet != null && price != null) {
            return Sort.by(checkType(alphabet) == SortType.ASC ? Sort.Order.asc("name") : Sort.Order.desc("name"),
                    checkType(price) == SortType.ASC ? Sort.Order.asc("price") : Sort.Order.desc("price"));
        }
        return Sort.by(Sort.Direction.ASC, "name");
    }

    private SortType checkType(String type) {
        try {
            return SortType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new UnknownSortTypeException(String.format("unknown state: %s", type));
        }
    }
}
