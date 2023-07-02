package ru.isands.BackendTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isands.BackendTask.model.Appliance;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {

}
