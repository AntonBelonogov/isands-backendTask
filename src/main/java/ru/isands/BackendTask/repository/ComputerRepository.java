package ru.isands.BackendTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isands.BackendTask.model.Model;

import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<Model, Long>, ModelsCrudInterface {
    List<Model> findAllByAppliance_NameAndComputerCategoryIgnoreCase(String name, String computerCategory);
    List<Model> findAllByAppliance_NameAndProcessorTypeIgnoreCase(String name, String processorType);
}
