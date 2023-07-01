package org.example.Entities;

import lombok.Getter;
import lombok.Setter;
//@Entity
@Getter
@Setter
public class CarModel {

    private String name;
    private Long carBrand;
    private String BodyType;
    private Long id;
    private double length;
    private double weight;

    public CarModel(){
    }
}
