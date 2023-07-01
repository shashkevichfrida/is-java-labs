package org.example.Services;

import org.example.BL.SessionUtil;
import org.example.DAO.CarModelDAO;
import org.example.Entities.CarBrand;
import org.example.Entities.CarModel;
import org.hibernate.Session;

import java.util.List;

public class CarModelService extends SessionUtil implements CarModelDAO {

    @Override
    public CarModel save(CarModel entity) {
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
        CarModel brand = session.get(CarModel.class, id);
        session.remove(brand);
        closeTransactionSesstion();
    }

    @Override
    public void deleteByEntity(CarModel entity) {
        openTransactionSession();
        Session session = getSession();
        session.remove(entity);
        closeTransactionSesstion();
    }

    @Override
    public void deleteAll() {
        openTransactionSession();
        Session session = getSession();
        session.createQuery("delete from CarModel").executeUpdate();
        closeTransactionSesstion();
    }

    @Override
    public CarModel update(CarModel entity) {
        openTransactionSession();
        Session session = getSession();
        session.update(entity);
        closeTransactionSesstion();
        return entity;
    }

    @Override
    public CarModel getById(long id) {
        openTransactionSession();
        Session session = getSession();
        return session.get(CarModel.class, id);
    }

    @Override
    public List<CarModel> getAll() {
        openTransactionSession();
        Session session = getSession();
        return session.createQuery("from CarBrand", CarModel.class).getResultList();
    }



    @Override
    public CarBrand getAllByVId(CarModel carModel) {
        openTransactionSession();
        Session session = getSession();
        return session.get(CarBrand.class, carModel.getCarBrand().getId());

    }
}
