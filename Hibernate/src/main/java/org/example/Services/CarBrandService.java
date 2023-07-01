package org.example.Services;

import org.example.BL.SessionUtil;
import org.example.DAO.CarBrandDAO;
import org.example.Entities.CarBrand;
import org.hibernate.Session;

import java.util.List;

public class CarBrandService extends SessionUtil implements CarBrandDAO {


    public CarBrand save(CarBrand entity) {
        openTransactionSession();
        Session session = getSession();
        session.save(entity);
        closeTransactionSesstion();
        return entity;
    }


    @Override
    public void deleteById(long id) {
        openTransactionSession();
        Session session = getSession();
        CarBrand brand = session.get(CarBrand.class, id);
        session.remove(brand);
        closeTransactionSesstion();
    }
    @Override
    public void deleteByEntity(CarBrand entity) {
        openTransactionSession();
        Session session = getSession();
        session.remove(entity);
        closeTransactionSesstion();

    }


    @Override
    public void deleteAll() {
        openTransactionSession();
        Session session = getSession();
        session.createQuery("delete from CarBrand").executeUpdate();
        closeTransactionSesstion();
    }

    @Override
    public CarBrand update(CarBrand entity) {
        openTransactionSession();
        Session session = getSession();
        session.update(entity);
        closeTransactionSesstion();
        return entity;
    }

    @Override
    public CarBrand getById(long id) {
        openTransactionSession();
        Session session = getSession();
        return session.get(CarBrand.class, id);
    }

    @Override
    public List<CarBrand> getAll() {
        openTransactionSession();
        Session session = getSession();
        return session.createQuery("from CarBrand", CarBrand.class).getResultList();
    }

}