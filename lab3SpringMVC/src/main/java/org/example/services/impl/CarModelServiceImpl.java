package org.example.services.impl;

import org.example.dto.CarBrandDto;
import org.example.dto.CarModelDto;
import org.example.dto.EngineDto;
import org.example.entities.CarBrand;
import org.example.entities.CarModel;
import org.example.entities.Engine;
import org.example.repositories.CarModelRepository;
import org.example.services.CarModelService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarModelServiceImpl implements CarModelService<CarModel, CarBrandDto, CarModelDto, EngineDto> {

    private final CarModelRepository carModelRepository;
    private final ModelMapper modelMapper;

    public CarModelServiceImpl(CarModelRepository carModelRepository, ModelMapper modelMapper) {
        this.carModelRepository = carModelRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CarModelDto save(CarModelDto entity) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CarModel carModel = modelMapper.map(entity, CarModel.class);
        carModelRepository.save(carModel);
        return getById(carModel.getId());
    }

    @Override
    public void deleteById(long id) {
        carModelRepository.deleteById(id);
    }

    @Override
    public void deleteByEntity(Long id) {
        Optional<CarModel> carModel = carModelRepository.findById(id);
        carModelRepository.delete(carModel.get());
    }

    @Override
    public void deleteAll() {
        carModelRepository.deleteAll();
    }

    @Override
    public CarModelDto update(CarModelDto entity, Long oldId) {
        CarModel carModel = modelMapper.map(entity, CarModel.class);
        carModel.setId(oldId);
        carModelRepository.save(carModel);
        return getById(oldId);
    }

    @Override
    public CarModelDto getById(Long id) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Optional<CarModel> carModel = carModelRepository.findById(id);
        CarModelDto carModelDto = modelMapper.map(carModel.get(), CarModelDto.class);
        return carModelDto;
    }

    @Override
    public List<CarModelDto> getAll() {
        List<CarModel> carModels = carModelRepository.findAll();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        List<CarModelDto> carModelDto = carModels.stream().map(carModel -> modelMapper.map(carModel, CarModelDto.class)).collect(Collectors.toList());
        return carModelDto;
    }

    @Override
    public CarBrandDto getAllByVId(Long carModelId) {
        Optional<CarModel> carModel = carModelRepository.findById(carModelId);
        CarBrand carBrand = carModelRepository.getAllByVId(carModel.get().getCarBrand().getCarBrandId());
        CarBrandDto carBrandDto = modelMapper.map(carBrand, CarBrandDto.class);
        return carBrandDto;
    }

    @Override
    public EngineDto getAllByName(Long id) {
        Engine engine = carModelRepository.getAllByName(id);
        EngineDto engineDto = modelMapper.map(engine, EngineDto.class);
        return engineDto;
    }
}
