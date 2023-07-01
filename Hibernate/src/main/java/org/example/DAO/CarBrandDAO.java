package org.example.DAO;

import org.example.Entities.CarBrand;

import java.util.List;

public interface CarBrandDAO {
    public CarBrand save(CarBrand entity);
    public void deleteById(long id);
    public void deleteByEntity(CarBrand entity);
    public void deleteAll();
    public CarBrand update(CarBrand entity);
    public CarBrand getById(long id);
    public List<CarBrand> getAll();
}
