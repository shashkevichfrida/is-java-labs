package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entities.CarBrand;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarModelDto {
    private String name;
    private String BodyType;
    private Double length;
    private Double weight;
    private CarBrand carBrand;
    private Double height;
}
