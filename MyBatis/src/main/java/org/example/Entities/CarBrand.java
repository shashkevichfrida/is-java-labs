package org.example.Entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CarBrand {

    private Long id;
    private String name;
    private Date date;

    public CarBrand() {

    }
}
