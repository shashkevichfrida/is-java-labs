package JDBC.Test;

import org.example.Entities.CarBrand;
import org.example.Entities.CarModel;
import org.example.Services.CarBrandService;
import org.example.Services.CarModelService;
import org.example.Sql.Util;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.Random;

public class JDBCTest {
    private CarBrand brand = new CarBrand();
    private CarModel carModel = new CarModel();
    private Util ct = new Util();



    public JDBCTest(){
        brand.setName("toyota");
        brand.setDate(new Date(1l));
        brand.setId(new Random().nextLong());
        carModel.setName("poh");
        carModel.setBodyType("body");
        carModel.setWeight(60);
        carModel.setLength(70);
        carModel.setId(new Random().nextLong());
        carModel.setCarBrand(brand.getId());
    }
    CarBrandService carBrandService = new CarBrandService();
    CarModelService carModelService = new CarModelService();

    @Test
    public void get() {
        ct.createTableCarBrand();
        ct.createTableCarModel();
        carBrandService.save(brand);
        carModelService.save(carModel);

        CarBrand getCarBrand  = carBrandService.getById(brand.getId());
        CarModel getCarModel  = carModelService.getById(carModel.getId());
        CarBrand carBrandGet = carModelService.getAllByVId(carModel.getCarBrand());

        carModelService.deleteAll();
        carBrandService.deleteAll();

        Assert.assertEquals(getCarBrand.getId(), brand.getId());
        Assert.assertEquals(getCarModel.getId(), carModel.getId());
        Assert.assertEquals(carBrandGet.getId(), brand.getId());
    }
    @Test
    public void update() {
        ct.createTableCarBrand();
        ct.createTableCarModel();

        CarBrand carBrand2 = new CarBrand();
        CarModel carModel2 = new CarModel();

        carBrand2.setName("car brand 2");
        carBrand2.setDate(new Date(1l));
        carBrand2.setId(new Random().nextLong());

        carModel2.setName("poh");
        carModel2.setBodyType("body");
        carModel2.setWeight(60);
        carModel2.setLength(70);
        carModel2.setId(new Random().nextLong());
        carModel2.setCarBrand(carBrand2.getId());

        String newName = "newName";

        carBrandService.save(carBrand2);
        carModelService.save(carModel2);

        carModel2.setName(newName);
        carBrand2.setName(newName);

        carModelService.update(carModel2);
        carBrandService.update(carBrand2);


        Assert.assertEquals(newName, carBrandService.getById(carBrand2.getId()).getName());
        Assert.assertEquals(newName, carModelService.getById(carModel2.getId()).getName());
    }

    @Test
    public void deleteAll() {
        ct.createTableCarBrand();
        ct.createTableCarModel();

        carBrandService.save(brand);
        carModelService.save(carModel);

        carModelService.deleteAll();
        carBrandService.deleteAll();

        Assert.assertNull(carBrandService.getById(brand.getId()).getId());
        Assert.assertNull(carModelService.getById(brand.getId()).getId());
    }

    @Test
    public void deleteById() {
        ct.createTableCarBrand();
        ct.createTableCarModel();

        carBrandService.save(brand);
        carModelService.save(carModel);

        carModelService.deleteById(carModel.getId());
        carBrandService.deleteById(brand.getId());

        Assert.assertNull(carBrandService.getById(brand.getId()).getId());
        Assert.assertNull(carModelService.getById(carModel.getId()).getId());
    }

    @Test
    public void deleteByEntity() {
        ct.createTableCarBrand();
        ct.createTableCarModel();

        carBrandService.save(brand);
        carModelService.save(carModel);

        carModelService.deleteByEntity(carModel);
        carBrandService.deleteByEntity(brand);

        Assert.assertNull(carBrandService.getById(brand.getId()).getId());
        Assert.assertNull(carModelService.getById(carModel.getId()).getId());
    }
}
