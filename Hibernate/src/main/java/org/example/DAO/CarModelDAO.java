package org.example.DAO;

import org.example.Entities.CarBrand;
import org.example.Entities.CarModel;

import java.util.List;

public interface CarModelDAO {
    public CarModel save(CarModel entity);
    public void deleteById(long id);
    public void deleteByEntity(CarModel entity);
    public void deleteAll();
    public CarModel update(CarModel entity);
    public CarModel getById(long id);
    public List<CarModel> getAll();
    public CarBrand getAllByVId(CarModel carModel);
}
