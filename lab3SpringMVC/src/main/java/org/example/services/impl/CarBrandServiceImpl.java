package org.example.services.impl;

import org.example.dto.CarBrandDto;
import org.example.entities.CarBrand;
import org.example.entities.CarModel;
import org.example.repositories.CarBrandRepository;
import org.example.services.CarBrandService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarBrandServiceImpl implements CarBrandService<CarBrand, CarBrandDto, CarModel> {
    private final CarBrandRepository carBrandRepository;

    private final ModelMapper modelMapper;

    public CarBrandServiceImpl(CarBrandRepository carBrandRepository, ModelMapper modelMapper) {
        this.carBrandRepository = carBrandRepository;
        this.modelMapper = modelMapper;
    }

    public CarBrandDto save(CarBrandDto entity) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        CarBrand carBrand = modelMapper.map(entity, CarBrand.class);
        carBrandRepository.save(carBrand);
        return getById(carBrand.getCarBrandId());
    }
    @Override
    public void deleteById(long id) {
        carBrandRepository.deleteById(id);
    }

    @Override
    public void deleteByEntity(CarBrandDto entity, Long id) {
        CarBrandDto carBrandDto = getById(id);
        CarBrand carBrand = modelMapper.map(carBrandDto, CarBrand.class);
        carBrandRepository.delete(carBrand);
    }
    @Override
    public void deleteAll() {
        carBrandRepository.deleteAll();
    }
    @Override
    public CarBrandDto update(CarBrandDto entity, Long oldId) {
        CarBrand carBrand = modelMapper.map(entity, CarBrand.class);
        carBrand.setCarBrandId(oldId);
        carBrandRepository.save(carBrand);
        return getById(oldId);
    }

    @Override
    public CarBrandDto getById(long id) {
        Optional<CarBrand> carBrand = carBrandRepository.findById(id);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        CarBrandDto carBrandDto = modelMapper.map(carBrand.get(), CarBrandDto.class);
        return carBrandDto;
    }


    @Override
    public List<CarBrandDto> getAll(){
        List<CarBrand> carBrands = carBrandRepository.findAll();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        List<CarBrandDto> carBrandDto = carBrands.stream().map(carBrand -> modelMapper.map(carBrand, CarBrandDto.class)).collect(Collectors.toList());
        return carBrandDto;
    }

}

