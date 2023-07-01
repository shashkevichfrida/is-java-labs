IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID('[car_brand]'))
    BEGIN CREATE TABLE car_brand
(
    Id bigint NOT NULL,
    Name nvarchar(max),
    Date date,
    PRIMARY KEY (Id)
);
    END

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID('[car_brand]'))
    BEGIN CREATE TABLE car_engine
(
    Id bigint NOT NULL,
    Name nvarchar(max),
    Volume int,
    Number_Of_Cylinders int,
    Height float,
    PRIMARY KEY (Id)
);
END

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID('[car_brand]'))
BEGIN CREATE TABLE car_model
(
    id bigint NOT NULL,
    Name nvarchar(max),
    Length float,
    Weight float,
    BodyType nvarchar(255),
    car_brand_id bigint,
    car_model_id bigint,
    FOREIGN KEY(car_brand_id) REFERENCES car_brand( Id ),
    FOREIGN KEY(car_model_id) REFERENCES car_engine( Id ),
    PRIMARY KEY (id)
);
END




