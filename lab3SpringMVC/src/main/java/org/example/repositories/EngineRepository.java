package org.example.repositories;

import org.example.entities.CarModel;
import org.example.entities.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Long> {
    @Query("SELECT DISTINCT m FROM Engine e JOIN e.carModelId m WHERE e.id = :engineId")
    List<CarModel> getAllByVId(@Param("engineId") Long engineId);

}
