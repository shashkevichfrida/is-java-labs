package org.example.services.impl;

import org.example.dto.CarModelDto;
import org.example.dto.EngineDto;
import org.example.entities.CarModel;
import org.example.entities.Engine;
import org.example.repositories.EngineRepository;
import org.example.services.EngineService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EngineServiceImpl implements EngineService<Engine, CarModel, EngineDto, CarModelDto> {
    private final EngineRepository engineRepository;
    private final ModelMapper modelMapper;

    public EngineServiceImpl(EngineRepository engineRepository, ModelMapper modelMapper) {
        this.engineRepository = engineRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public EngineDto save(EngineDto entity) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Engine engine = modelMapper.map(entity, Engine.class);
        engineRepository.save(engine);
        return getById(engine.getId());
    }

    @Override
    public void deleteById(Long id) {
        engineRepository.deleteById(id);
    }


    @Override
    public void deleteByEntity(Long id) {
        Optional<Engine> engine = engineRepository.findById(id);
        engineRepository.delete(engine.get());
    }

    @Override
    public void deleteAll() {
        engineRepository.deleteAll();
    }

    @Override
    public EngineDto update(EngineDto entity, Long oldId) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Engine engine = modelMapper.map(entity, Engine.class);
        engine.setId(oldId);
        engineRepository.save(engine);
        return getById(oldId);
    }

    @Override
    public EngineDto getById(Long id) {
        Optional<Engine> engineOptional = engineRepository.findById(id);
        EngineDto engineDto = modelMapper.map(engineOptional.get(), EngineDto.class);
        return engineDto;
    }

    @Override
    public List<EngineDto> getAll() {
        List<Engine> engines = engineRepository.findAll();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        List<EngineDto> engineDto = engines.stream().map(engine -> modelMapper.map(engine, EngineDto.class)).collect(Collectors.toList());
        return engineDto;
    }

    @Override
    public List<CarModelDto> getAllByVId(Long entityId) {
        Optional<Engine> entity = engineRepository.findById(entityId);
        List<CarModel> carModels = engineRepository.getAllByVId(entity.get().getId());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        List<CarModelDto> carModelDto = carModels.stream().map(carModel -> modelMapper.map(carModel, CarModelDto.class)).collect(Collectors.toList());
        return carModelDto;
    }
}

