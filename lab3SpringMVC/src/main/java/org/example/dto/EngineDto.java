package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entities.CarModel;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EngineDto {
    private String name;
    private int volume;
    private int numberOfCylinders;
    private Double height;
    private List<CarModel> carModelId = new ArrayList<>();
}
