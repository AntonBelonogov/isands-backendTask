package ru.isands.BackendTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isands.BackendTask.model.Model;

import java.util.List;

@Repository
public interface VacuumRepository extends JpaRepository<Model, Long>, ModelsCrudInterface {
    List<Model> findAllByAppliance_NameAndDustBagVolume(String name, Float dustBagVolume);
    List<Model> findAllByAppliance_NameAndNumberOfModes(String name, Integer numberOfModes);
}
