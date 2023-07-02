package ru.isands.BackendTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isands.BackendTask.model.Model;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Model, Long>, ModelsCrudInterface {
    List<Model> findAllByAppliance_NameAndMemory(String name, Integer memory);
    List<Model> findAllByAppliance_NameAndNumberOfCameras(String name, Integer numberOfCameras);
}
