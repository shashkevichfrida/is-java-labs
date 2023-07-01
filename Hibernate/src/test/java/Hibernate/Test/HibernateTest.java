package Hibernate.Test;

import org.example.Entities.CarBrand;
import org.example.Entities.CarModel;
import org.example.Services.CarBrandService;
import org.example.Services.CarModelService;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;

public class HibernateTest {
    private CarBrand brand = new CarBrand();
    private CarModel carModel = new CarModel();
    public HibernateTest(){
        brand.setName("toyota");
        brand.setDate(new Date(1l));
        //brand.setId(1l);
        carModel.setName("poh");
        carModel.setBodyType("body");
        carModel.setWeight(60.0);
        carModel.setLength(70.0);
        //carModel.setId(1l);
        carModel.setCarBrand(brand);
    }
    CarBrandService carBrandService = new CarBrandService();
    CarModelService carModelService = new CarModelService();

    @Test
    public void get() {
        carBrandService.save(brand);
        carModelService.save(carModel);

        CarBrand getCarBrand  = carBrandService.getById(brand.getId());
        CarModel getCarModel  = carModelService.getById(carModel.getId());
        CarBrand carBrandGet = carModelService.getAllByVId(carModel);

        carModelService.deleteAll();
        carBrandService.deleteAll();


        Assert.assertEquals(getCarBrand.getId(), brand.getId());
        Assert.assertEquals(getCarModel.getId(), carModel.getId());
        Assert.assertEquals(carBrandGet.getId(), brand.getId());
    }

    @Test
    public void update() {
        CarBrand carBrand2 = new CarBrand();
        CarModel carModel2 = new CarModel();

        carBrand2.setName("car brand 2");
        carBrand2.setDate(new Date(1l));
        carModel2.setName("poh");
        carModel2.setBodyType("body");
        carModel2.setWeight(60.0);
        carModel2.setLength(70.0);
        carModel2.setCarBrand(carBrand2);


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
        carBrandService.save(brand);
        carModelService.save(carModel);

        carModelService.deleteAll();
        carBrandService.deleteAll();


        CarModel carMGet = carModelService.getById(carModel.getId());

        Assert.assertNull(carMGet);
    }

    @Test
    public void deleteById() {
        carBrandService.save(brand);
        carModelService.save(carModel);
        carModelService.deleteById(carModel.getId());
        carBrandService.deleteById(brand.getId());


        Assert.assertNull(carBrandService.getById(brand.getId()));
        Assert.assertNull(carModelService.getById(carModel.getId()));
    }

    @Test
    public void deleteByEntity() {
        carBrandService.save(brand);
        carModelService.save(carModel);
        carModelService.deleteByEntity(carModel);
        carBrandService.deleteByEntity(brand);


        Assert.assertNull(carBrandService.getById(brand.getId()));
        Assert.assertNull(carModelService.getById(carModel.getId()));
    }



}
