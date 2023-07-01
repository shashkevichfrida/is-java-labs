package org.example.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarModel {
    private String name;
    private Long carBrandId;
    private String BodyType;
    private Long id;
    private Double length;
    private Double weight;


    public CarModel(){
    }

}
