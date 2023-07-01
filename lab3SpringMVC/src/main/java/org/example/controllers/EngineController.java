package org.example.controllers;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.dto.CarModelDto;
import org.example.dto.EngineDto;
import org.example.services.impl.CarModelServiceImpl;
import org.example.services.impl.EngineServiceImpl;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/engine")
@ApiResponse(description = "engine")
public class EngineController {
    private final CarModelServiceImpl carModelService;

    private final EngineServiceImpl engineService;

    public EngineController(CarModelServiceImpl carModelService, EngineServiceImpl engineService) {
        this.carModelService = carModelService;
        this.engineService = engineService;
    }


    @PostMapping ("/save")
    public EngineDto save(@RequestBody EngineDto engineDto) {
        return engineService.save(engineDto);
    }

    @PostMapping("/getAll")
    public List<EngineDto> getAll(){
        return engineService.getAll();
    }

    @PostMapping("/getById")
    public EngineDto getById(@RequestParam("id") Long id) {
        return engineService.getById(id);
    }

    @PostMapping("/deleteAll")
    public List<EngineDto> deleteAll() {
        engineService.deleteAll();
        return engineService.getAll();
    }
    @PostMapping("/update")
    public EngineDto update(@RequestParam("OldId") Long oldId, @RequestBody EngineDto engineDto) {
        return engineService.update(engineDto, oldId);
    }

    @PostMapping("/deleteByEntity")
    public void deleteByEntity(@RequestParam("id") Long id) {
        engineService.deleteByEntity(id);
    }

    @PostMapping("/deleteById")
    public void deleteById(@RequestParam("id") Long id) {
        engineService.deleteById(id);
    }

    @PostMapping("/getAllByVId")
    public List<CarModelDto> getAllByVId(@RequestParam("id") Long id) {

        return engineService.getAllByVId(id);
    }
}
