package org.example.controllers;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.dto.CarBrandDto;
import org.example.dto.CarModelDto;
import org.example.dto.EngineDto;
import org.example.services.impl.CarBrandServiceImpl;
import org.example.services.impl.CarModelServiceImpl;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/carModel")
@ApiResponse(description = "carModel")
public class CarModelController {

    private final CarModelServiceImpl carModelService;

    private final CarBrandServiceImpl carBrandService;

    public CarModelController(CarModelServiceImpl carModelService, CarBrandServiceImpl carBrandService) {
        this.carModelService = carModelService;
        this.carBrandService = carBrandService;
    }

    @PostMapping("/save")
    public CarModelDto save(@RequestBody CarModelDto carModelDto) {
        return carModelService.save(carModelDto);
    }

    @PostMapping("/getAll")
    public List<CarModelDto> getAll() {
        return carModelService.getAll();
    }

    @PostMapping("/getById")
    public CarModelDto getById(@RequestParam("id") Long id) {
        return carModelService.getById(id);
    }

    @PostMapping("/deleteAll")
    public String deleteAll() {
        carModelService.deleteAll();
        return "entity deleted";
    }

    @PostMapping("/update")
    public CarModelDto update(@RequestBody CarModelDto carModelDto, @RequestParam("OldId") Long oldId) {
        return carModelService.update(carModelDto, oldId);
    }

    @PostMapping("/deleteByEntity")
    public void deleteByEntity(@RequestParam("id") Long id) {
        carModelService.deleteByEntity(id);
    }

    @PostMapping("/deleteById")
    public void deleteById(@RequestParam("id") Long id) {
        carModelService.deleteById(id);
    }

    @PostMapping("/getAllByVId")
    public CarBrandDto getAllByVId(@RequestParam("id") Long id) {
        return carModelService.getAllByVId(id);
    }

    @PostMapping("/getAllByName")
    public EngineDto getAllByName(@RequestParam("id") Long id) {
        return carModelService.getAllByName(id);
    }
}
