package org.example.Services;

import org.apache.ibatis.session.SqlSession;
import org.example.DAO.CarBrandDAO;
import org.example.DAO.CarModelDAO;
import org.example.Entities.CarBrand;
import org.example.Entities.CarModel;
import org.example.Utils.MyBatisUtil;

import java.util.List;

public class CarModelService implements CarModelDAO {
    @Override
    public void createNewTableIfNotExists(String tableName) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()) {
            CarModelDAO carModelMapper = sqlSession.getMapper(CarModelDAO.class);
            carModelMapper.createNewTableIfNotExists(tableName);
            sqlSession.commit();
        }
    }

    public void insertCarModel(CarModel carModel) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            CarModelDAO carModelMapper = sqlSession.getMapper(CarModelDAO.class);
            carModelMapper.insertCarModel(carModel);
            sqlSession.commit();
        }
    }

    @Override
    public CarModel save(CarModel entity) {
        return null;
    }

    @Override
    public void deleteById(long id) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            CarModelDAO carModelMapper = sqlSession.getMapper(CarModelDAO.class);
            carModelMapper.deleteById(id);
            sqlSession.commit();
        }

    }

    @Override
    public void deleteByEntity(CarModel entity) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            CarModelDAO carModelMapper = sqlSession.getMapper(CarModelDAO.class);
            carModelMapper.deleteByEntity(entity);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            CarModelDAO carModelMapper = sqlSession.getMapper(CarModelDAO.class);
            carModelMapper.deleteAll();
            sqlSession.commit();
        }
    }

    @Override
    public CarModel update(CarModel entity) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            CarModelDAO carModelMapper = sqlSession.getMapper(CarModelDAO.class);
            sqlSession.commit();
            carModelMapper.update(entity);

            sqlSession.commit();
            sqlSession.close();
            return entity;
        }
    }

    @Override
    public CarModel getById(long id) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            CarModelDAO carModelMapper = sqlSession.getMapper(CarModelDAO.class);
            return carModelMapper.getById(id);
        }
    }

    @Override
    public List<CarModel> getAll() {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            CarModelDAO carModelMapper = sqlSession.getMapper(CarModelDAO.class);
            return carModelMapper.getAll();
        }
    }

    @Override
    public CarBrand getAllByVId(long id) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            CarBrandDAO carBrandMapper = sqlSession.getMapper(CarBrandDAO.class);
            return carBrandMapper.getById(id);
        }
    }
}
