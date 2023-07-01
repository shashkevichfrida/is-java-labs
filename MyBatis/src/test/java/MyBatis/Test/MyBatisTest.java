package MyBatis.Test;

import org.example.Entities.CarBrand;
import org.example.Entities.CarModel;
import org.example.Services.CarBrandService;
import org.example.Services.CarModelService;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.Random;

public class MyBatisTest {

    private CarBrand brand = new CarBrand();
    private CarModel carModel = new CarModel();



    public MyBatisTest(){
        brand.setName("toyota");
        brand.setDate(new Date(1l));
        brand.setId(new Random().nextLong());
        carModel.setName("poh");
        carModel.setBodyType("body");
        carModel.setWeight(60.0);
        carModel.setLength(70.0);
        carModel.setId(new Random().nextLong());
        carModel.setCarBrandId(brand.getId());
    }
    CarBrandService carBrandService = new CarBrandService();
    CarModelService carModelService = new CarModelService();

    @Test
    public void get() {
        carBrandService.createNewTableIfNotExists("CarBrand");
        carModelService.createNewTableIfNotExists("CarModel");
        carBrandService.insertCarBrand(brand);
        carModelService.insertCarModel(carModel);

        CarBrand getCarBrand  = carBrandService.getById(brand.getId());
        CarModel getCarModel  = carModelService.getById(carModel.getId());
        CarBrand carBrandGet = carModelService.getAllByVId(carModel.getCarBrandId());

        carModelService.deleteAll();
        carBrandService.deleteAll();

        Assert.assertEquals(getCarBrand.getId(), brand.getId());
        Assert.assertEquals(getCarModel.getId(), carModel.getId());
        Assert.assertEquals(carBrandGet.getId(), brand.getId());
    }
    @Test
    public void update() {
        carBrandService.createNewTableIfNotExists("CarBrand");
        carModelService.createNewTableIfNotExists("CarModel");

        CarBrand carBrand2 = new CarBrand();
        CarModel carModel2 = new CarModel();

        carBrand2.setName("car brand 2");
        carBrand2.setDate(new Date(1l));
        carBrand2.setId(new Random().nextLong());

        carModel2.setName("poh");
        carModel2.setBodyType("body");
        carModel2.setWeight(60.0);
        carModel2.setLength(70.0);
        carModel2.setId(new Random().nextLong());
        carModel2.setCarBrandId(carBrand2.getId());

        String newName = "newName";

        carBrandService.insertCarBrand(carBrand2);
        carModelService.insertCarModel(carModel2);

        carModel2.setName(newName);
        carBrand2.setName(newName);

        carModelService.update(carModel2);
        carBrandService.update(carBrand2);


        Assert.assertEquals(newName, carModelService.getById(carModel2.getId()).getName());
    }

    @Test
    public void deleteAll() {
        carBrandService.createNewTableIfNotExists("CarBrand");
        carModelService.createNewTableIfNotExists("CarModel");

        carBrandService.insertCarBrand(brand);
        carModelService.insertCarModel(carModel);

        carModelService.deleteAll();
        carBrandService.deleteAll();

        Assert.assertNull(carBrandService.getById(brand.getId()));
        Assert.assertNull(carModelService.getById(brand.getId()));
    }

    @Test
    public void deleteById() {
        carBrandService.createNewTableIfNotExists("CarBrand");
        carModelService.createNewTableIfNotExists("CarModel");

        CarBrand carBrand2 = new CarBrand();
        CarModel carModel2 = new CarModel();

        carBrand2.setName("car brand 2");
        carBrand2.setDate(new Date(1l));
        carBrand2.setId(new Random().nextLong());

        carModel2.setName("poh");
        carModel2.setBodyType("body");
        carModel2.setWeight(60.0);
        carModel2.setLength(70.0);
        carModel2.setId(new Random().nextLong());
        carModel2.setCarBrandId(carBrand2.getId());


        carBrandService.insertCarBrand(carBrand2);
        carModelService.insertCarModel(carModel2);

        carModelService.deleteById(carModel2.getId());
        carBrandService.deleteById(carBrand2.getId());

        Assert.assertNull(carBrandService.getById(carBrand2.getId()));
        Assert.assertNull(carModelService.getById(carModel2.getId()));
    }

    @Test
    public void deleteByEntity() {
        carBrandService.createNewTableIfNotExists("CarBrand");
        carModelService.createNewTableIfNotExists("CarModel");

        carBrandService.insertCarBrand(brand);
        carModelService.insertCarModel(carModel);

        carModelService.deleteByEntity(carModel);
        carBrandService.deleteByEntity(brand);

        Assert.assertNull(carBrandService.getById(brand.getId()));
        Assert.assertNull(carModelService.getById(carModel.getId()));
    }
    }
