package org.example.Services;

import org.example.DAO.CarModelDAO;
import org.example.Entities.CarBrand;
import org.example.Entities.CarModel;
import org.example.Sql.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarModelService extends Util implements CarModelDAO<CarModel> {
    @Override
    public CarModel save(CarModel entity) {
        String sql = "INSERT INTO CarModel(Id, Name, Length, Width, BodyType, CarBrandId) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setDouble(3, entity.getLength());
            preparedStatement.setDouble(4, entity.getWeight());
            preparedStatement.setString(5, entity.getBodyType());
            preparedStatement.setLong(6, entity.getCarBrand());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void deleteById(long id) {
        String sql = "DELETE FROM CarModel WHERE Id=?";

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteByEntity(CarModel entity) {
        String sql = "DELETE FROM CarModel WHERE Id=?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM CarModel";
        try (Connection conn = getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CarModel update(CarModel entity) {
        String sql = "UPDATE CarModel SET Name=?, Length=?, Width=?, BodyType=?, CarBrandId=? WHERE Id=?";
        try (Connection conn = getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDouble(2, entity.getLength());
            preparedStatement.setDouble(3, entity.getWeight());
            preparedStatement.setString(4, entity.getBodyType());
            preparedStatement.setLong(5, entity.getCarBrand());
            preparedStatement.setLong(6, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public CarModel getById(long id) {
        CarModel carModel = new CarModel();
        String sql = "SELECT * FROM CarModel WHERE Id=?";
        try (Connection conn = getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                carModel.setId(rs.getLong("Id"));
                carModel.setName(rs.getString("Name"));
                carModel.setLength(rs.getDouble("Length"));
                carModel.setWeight(rs.getDouble("Width"));
                carModel.setBodyType(rs.getString("BodyType"));
                carModel.setCarBrand(rs.getLong("CarBrandId"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carModel;
    }

    @Override
    public List<CarModel> getAll() {
        List<CarModel> carModels = new ArrayList<>();
        String sql = "SELECT Id, Name, Length, Width, BodyType, CarBrandId FROM CarModel";
        try (Connection conn = getConnection(); Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                CarModel carModel = new CarModel();
                carModel.setId(rs.getLong("Id"));
                carModel.setName(rs.getString("Name"));
                carModel.setLength(rs.getDouble("Length"));
                carModel.setWeight(rs.getDouble("Width"));
                carModel.setBodyType(rs.getString("BodyType"));
                carModel.setCarBrand(rs.getLong("CarBrandId"));
                carModels.add(carModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carModels;
    }

    @Override
    public CarBrand getAllByVId(long carModelId) {
        CarBrand carBrand = new CarBrand();
        String sql = "SELECT * FROM CarBrand WHERE Id=?";
        try (Connection conn = getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, carModelId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {

                carBrand.setId(rs.getLong("Id"));
                carBrand.setName(rs.getString("Name"));
                carBrand.setDate(rs.getDate("Date"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carBrand;
    }


}
