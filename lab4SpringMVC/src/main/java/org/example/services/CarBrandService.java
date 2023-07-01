package org.example.services;

import org.example.dto.CarBrandDto;

import java.util.List;

public interface CarBrandService {
    public CarBrandDto save(CarBrandDto entity);
    public void deleteById(long id);
    public void deleteByEntity(Long id);
    public void deleteAll();
    public CarBrandDto update(CarBrandDto entity, Long oldId);
    public CarBrandDto getById(long id);
    public List<CarBrandDto> getAll();
}


