package org.example.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@Entity
@Table(name = "CarBrand")
public class CarBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="Id")
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Date")
    private Date date;
}
