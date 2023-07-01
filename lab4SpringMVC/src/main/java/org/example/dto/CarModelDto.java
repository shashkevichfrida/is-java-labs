package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarModelDto {
    private Long id;
    private String name;
    private String BodyType;
    private Double length;
    private Double weight;
    private CarBrandDto carBrand;
    private Double height;
}
