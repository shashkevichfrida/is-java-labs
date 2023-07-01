package org.example.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.dto.CarBrandDto;
import org.example.services.impl.CarBrandServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/carBrand")
@Tag(name = "carBrand", description = "car brand APIs")
@PreAuthorize("hasAnyAuthority('developers:read', 'developers:write')")
public class CarBrandController {
    private final CarBrandServiceImpl carBrandServices;

    public CarBrandController(CarBrandServiceImpl carBrandServices) {
        this.carBrandServices = carBrandServices;
    }

    @PostMapping("/save")
    public CarBrandDto save(@RequestBody CarBrandDto carBrandDto) {
        return carBrandServices.save(carBrandDto);
    }

    @PostMapping("/getAll")
    public List<CarBrandDto> getAll(){
        return carBrandServices.getAll();
    }

    @PostMapping("/getById")
    public CarBrandDto getById(@RequestParam("id") Long id) {
        return carBrandServices.getById(id);
    }

    @PostMapping("/deleteAll")
    public List<CarBrandDto> deleteAll() {
        carBrandServices.deleteAll();
        return carBrandServices.getAll();
    }
    @PostMapping("/update")
    public CarBrandDto update(@RequestParam("OldId") Long oldId, @RequestBody CarBrandDto carBrandDto) {
        return carBrandServices.update(carBrandDto, oldId);
    }

    @PostMapping("/deleteByEntity")
    public void deleteByEntity(@RequestParam("id") Long id) {
        carBrandServices.deleteByEntity(id);
    }

    @PostMapping("/deleteById")
    public void deleteById(@RequestParam("id") Long id) {
        carBrandServices.deleteById(id);
    }
}
