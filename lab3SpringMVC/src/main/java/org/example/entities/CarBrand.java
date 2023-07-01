package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Entity
@Table(name = "carBrand")
@Getter
@Setter
@NoArgsConstructor
public class CarBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="Id")
    private Long carBrandId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Date")
    private Date date;

}
