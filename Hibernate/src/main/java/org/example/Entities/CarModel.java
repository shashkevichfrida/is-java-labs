package org.example.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CarModel")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="Id")
    private Long id;
    @Column(name="Name")
    private String name;
    @Column(name="Body Type")
    private String BodyType;
    @Column(name="Length")
    private Double length;
    @Column(name="Weight")
    private Double weight;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "carBrandId")
    private CarBrand carBrand;

    public CarModel(){
    }

}
