package org.example.repositories;

import jakarta.transaction.Transactional;
import org.example.entities.CarModel;
import org.example.entities.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Long> {
    @Query("SELECT DISTINCT m FROM Engine e JOIN e.carModelId m WHERE e.id = :engineId")
    List<CarModel> getAllByVId(@Param("engineId") Long engineId);
    @Query("SELECT engine FROM Engine engine JOIN engine.carModelId carModel JOIN carModel.carBrand carbrand WHERE carbrand.id = :carBrandId")
    List<Engine> getAllById(@Param("carBrandId") Long carBrandId);

    @Modifying
    @Transactional
    @Query("UPDATE CarModel c SET c.engine = NULL WHERE c.carBrand.id = :carBrandId")
    void deleteEnginesByCarBrandIdWithNullingCarModels(@Param("carBrandId") Long carBrandId);


    @Query("SELECT e.id FROM Engine e WHERE e.id IN (SELECT c.engine.id FROM CarModel c WHERE c.carBrand.id = :carBrandId)")
    List<Long> findEnginesToRemove(@Param("carBrandId") Long carBrandId);

    @Modifying
    @Transactional
    @Query("UPDATE CarModel c SET c.engine = NULL WHERE c.carBrand.id = :carBrandId")
    void removeEngineFromCarModels(@Param("carBrandId") Long carBrandId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Engine e WHERE e.id IN (:engineIds)")
    void deleteEnginesByIds(@Param("engineIds") List<Long> engineIds);

}
