package ru.isands.BackendTask.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.isands.BackendTask.model.Model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequestMapping
public interface ModelRepository extends JpaRepository<Model, Long> {

    List<Model> findAllByAppliance_NameAndAvailable(String name, Boolean available);

    Optional<Model> findByAppliance_NameAndId(String name, Long id);

    Boolean existsByAppliance_NameAndId(String name, Long id);

    List<Model> findAllByAppliance_Name(String applianceName, Sort sort);

    @Query("SELECT m FROM Model m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Model> findAllByNameIgnoreCase(@Param("name") String name);

    List<Model> findAllByColorIgnoreCase(String color);

    List<Model> findAllByAppliance_Name(String applianceName);

    List<Model> findAllByPriceBetween(BigDecimal min, BigDecimal max);

    //Computer
    List<Model> findAllByAppliance_NameAndComputerCategoryIgnoreCase(String name, String computerCategory);

    List<Model> findAllByAppliance_NameAndProcessorTypeIgnoreCase(String name, String processorType);

    //Fridge
    List<Model> findAllByAppliance_NameAndNumberOfDoors(String name, Integer numberOfDoors);

    List<Model> findAllByAppliance_NameAndCompressorTypeIgnoreCase(String name, String compressorType);

    //Phone
    List<Model> findAllByAppliance_NameAndMemory(String name, Integer memory);

    List<Model> findAllByAppliance_NameAndNumberOfCameras(String name, Integer numberOfCameras);

    //Television
    List<Model> findAllByAppliance_NameAndTelevisionCategoryIgnoreCase(String name, String televisionCategory);

    List<Model> findAllByAppliance_NameAndTelevisionTechnologyIgnoreCase(String name, String televisionTechnology);

    //Vacuum
    List<Model> findAllByAppliance_NameAndDustBagVolume(String name, Float dustBagVolume);

    List<Model> findAllByAppliance_NameAndNumberOfModes(String name, Integer numberOfModes);

}
