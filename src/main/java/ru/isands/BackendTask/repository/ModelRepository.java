package ru.isands.BackendTask.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.isands.BackendTask.model.Model;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping
public interface ModelRepository extends JpaRepository<Model, Long> {

    List<Model> findAllByAvailable(Boolean available);

    List<Model> findAllByAppliance_Name(String applianceName, Sort sort);
    @Query("SELECT m FROM Model m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Model> findAllByNameIgnoreCase(@Param("name") String name);

    @Query("SELECT m FROM Model m WHERE m.modelAttributes LIKE CONCAT('%\"', :key, '\"%') AND m.modelAttributes LIKE CONCAT('%', :value, '%')")
    List<Model> search(@Param("key") String key, @Param("value") String value);

    List<Model> findAllByColorIgnoreCase(String color);

    List<Model> findAllByAppliance_Name(String applianceName);

    List<Model> findAllByPriceBetween(BigDecimal min, BigDecimal max);

}
