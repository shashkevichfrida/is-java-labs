package org.example.Services;

import org.apache.ibatis.session.SqlSession;
import org.example.DAO.CarBrandDAO;
import org.example.Entities.CarBrand;
import org.example.Utils.MyBatisUtil;

import java.util.List;

public class CarBrandService implements CarBrandDAO {


    @Override
    public void createNewTableIfNotExists(String tableName) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            CarBrandDAO carBrandMapper = sqlSession.getMapper(CarBrandDAO.class);
            carBrandMapper.createNewTableIfNotExists(tableName);
            sqlSession.commit();
        }
    }

    public void insertCarBrand(CarBrand carBrand) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            CarBrandDAO carBrandMapper = sqlSession.getMapper(CarBrandDAO.class);
            carBrandMapper.insertCarBrand(carBrand);
            sqlSession.commit();
        }
    }

    @Override
    public CarBrand save(CarBrand entity) {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()) {
            CarBrandDAO carBrand = sqlSession.getMapper(CarBrandDAO.class);

            carBrand.save(entity);

            sqlSession.commit();
            return entity;
        }
    }

    @Override
    public void deleteById(long id) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            CarBrandDAO carBrandMapper = sqlSession.getMapper(CarBrandDAO.class);
            carBrandMapper.deleteById(id);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteByEntity(CarBrand entity) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            CarBrandDAO carBrandMapper = sqlSession.getMapper(CarBrandDAO.class);
            carBrandMapper.deleteByEntity(entity);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            CarBrandDAO carBrandMapper = sqlSession.getMapper(CarBrandDAO.class);
            carBrandMapper.deleteAll();
            sqlSession.commit();
        }
    }

    @Override
    public CarBrand update(CarBrand entity) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            CarBrandDAO carBrandMapper = sqlSession.getMapper(CarBrandDAO.class);
            carBrandMapper.update(entity);

            sqlSession.commit();
            sqlSession.close();
            return entity;
        }
    }

    @Override
    public CarBrand getById(long id) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            CarBrandDAO carBrandMapper = sqlSession.getMapper(CarBrandDAO.class);
            return carBrandMapper.getById(id);
        }
    }

    @Override
    public List<CarBrand> getAll() {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            CarBrandDAO carBrandMapper = sqlSession.getMapper(CarBrandDAO.class);
            return carBrandMapper.getAll();
        }
    }
}
