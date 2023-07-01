package org.example.Entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CarBrand {
    private String name;
    private Date date;
    private Long id;
    public CarBrand()
    {
    }
}
