package org.example.services.impl;

import lombok.AllArgsConstructor;
import org.example.dto.CarBrandDto;
import org.example.entities.CarBrand;
import org.example.entities.CarModel;
import org.example.entities.User;
import org.example.models.Role;
import org.example.repositories.CarBrandRepository;
import org.example.repositories.UserRepository;
import org.example.services.CarBrandService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarBrandServiceImpl implements CarBrandService {

    private final CarBrandRepository carBrandRepository;

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    public CarBrandDto save(CarBrandDto entity) {
        User user = userRepository.findByName(getAuthentication().getName());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CarBrand carBrand = modelMapper.map(entity, CarBrand.class);
        carBrand.setUser(user);
        carBrandRepository.save(carBrand);
        return getById(carBrand.getId());// кинуть искл если уже с таким айди есть
    }
    @Override
    public void deleteById(long id) {
        User user = userRepository.findByName(getAuthentication().getName());

        if (user.getRole().equals(Role.ADMIN) || user.getId() == id){
            carBrandRepository.deleteById(id);
        }
    }

    @Override
    public void deleteByEntity(Long id) {
        User user = userRepository.findByName(getAuthentication().getName());

        if (user.getRole().equals(Role.ADMIN) || user.getId() == id) {
            CarBrand carBrand = carBrandRepository.findById(id).get();
            carBrandRepository.delete(carBrand);
        }
    }
    @Override
    public void deleteAll() {
        User user = userRepository.findByName(getAuthentication().getName());

        if (user.getRole().equals(Role.ADMIN)) {
            carBrandRepository.deleteAll();
        }

        else {
            carBrandRepository.deleteById(user.getId());
        }
    }
    @Override
    public CarBrandDto update(CarBrandDto entity, Long oldId) {
        User user = userRepository.findByName(getAuthentication().getName());

        if (user.getRole().equals(Role.ADMIN) || user.getId() == oldId) {
            CarBrand carBrand = carBrandRepository.findById(oldId).get();
            carBrand.setName(entity.getName());
            carBrand.setDate(entity.getDate());
            List<CarModel> carModels = entity.getCarModelId().stream().map(model -> modelMapper.map(model, CarModel.class)).collect(Collectors.toList());
            carBrand.setCarModelId(carModels);
            carBrandRepository.save(carBrand);
        }

        return getById(oldId);
    }

    @Override
    public CarBrandDto getById(long id) {
        CarBrandDto carBrandDto = new CarBrandDto();
        User user = userRepository.findByName(getAuthentication().getName());

        if (user.getRole().equals(Role.ADMIN) || user.getId() == id) {
            Optional<CarBrand> carBrand = carBrandRepository.findById(id);
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
            carBrandDto = modelMapper.map(carBrand.get(), CarBrandDto.class);
        }

        return carBrandDto;
    }


    @Override
    public List<CarBrandDto> getAll(){
        User user = userRepository.findByName(getAuthentication().getName());

        if (user.getRole().equals(Role.ADMIN)) {
            List<CarBrand> carBrands = carBrandRepository.findAll();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
            List<CarBrandDto> carBrandDto = carBrands.stream().map(carBrand -> modelMapper.map(carBrand, CarBrandDto.class)).collect(Collectors.toList());
            return carBrandDto;
        }

        CarBrandDto carBrandDto = getById(user.getId());
        List<CarBrandDto> cars = new ArrayList<>();
        cars.add(carBrandDto);

        return cars;
    }
    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }



}

