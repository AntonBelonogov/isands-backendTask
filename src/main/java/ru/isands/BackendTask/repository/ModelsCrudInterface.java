package ru.isands.BackendTask.repository;

import org.springframework.data.domain.Sort;
import ru.isands.BackendTask.model.Model;

import java.util.List;
import java.util.Optional;

public interface ModelsCrudInterface {
    List<Model> findAllByAppliance_NameAndAvailable(String name, Boolean available);

    Optional<Model> findByAppliance_NameAndId(String name, Long id);

    Boolean existsByAppliance_NameAndId(String name, Long id);

    List<Model> findAllByAppliance_Name(String applianceName, Sort sort);
}
