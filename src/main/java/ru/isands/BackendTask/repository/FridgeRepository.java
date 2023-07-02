package ru.isands.BackendTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isands.BackendTask.model.Model;

import java.util.List;

@Repository
public interface FridgeRepository extends JpaRepository<Model, Long>, ModelsCrudInterface {
    List<Model> findAllByAppliance_NameAndNumberOfDoors(String name, Integer numberOfDoors);
    List<Model> findAllByAppliance_NameAndCompressorTypeIgnoreCase(String name, String compressorType);
}
