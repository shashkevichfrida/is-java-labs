package org.example.repositories;

import org.example.entities.CarBrand;
import org.example.entities.CarModel;
import org.example.entities.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    @Query("SELECT DISTINCT carModel.carBrand FROM CarModel carModel WHERE carModel.carBrand.carBrandId = :carBrandId")
    public CarBrand getAllByVId(@Param("carBrandId") Long carBrandId);
    @Query("SELECT carModel.engine FROM CarModel carModel WHERE carModel.engine.id = :modelId")
    Engine getAllByName(@Param("modelId") Long modelId);
}
