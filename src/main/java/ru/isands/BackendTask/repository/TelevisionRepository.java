package ru.isands.BackendTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isands.BackendTask.model.Model;

import java.util.List;

@Repository
public interface TelevisionRepository extends JpaRepository<Model, Long>, ModelsCrudInterface {
    List<Model> findAllByAppliance_NameAndTelevisionCategoryIgnoreCase(String name, String televisionCategory);
    List<Model> findAllByAppliance_NameAndTelevisionTechnologyIgnoreCase(String name, String televisionTechnology);
}
