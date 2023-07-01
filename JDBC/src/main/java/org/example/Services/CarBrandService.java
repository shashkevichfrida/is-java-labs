package org.example.Services;

import org.example.DAO.CarBrandDAO;
import org.example.Entities.CarBrand;
import org.example.Entities.CarModel;
import org.example.Sql.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarBrandService extends Util implements CarBrandDAO<CarBrand> {
    @Override
    public CarBrand save(CarBrand entity) {
        String sql = "INSERT INTO CarBrand(Id, Name, Date) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setDate(3, entity.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }


    @Override
    public void deleteById(long id) {
        String sql = "DELETE FROM CarBrand WHERE Id=?";

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void deleteByEntity(CarBrand entity) {
        String sql = "DELETE FROM CarBrand WHERE Id=?";
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
        String sql = "DELETE FROM CarBrand";
        try (Connection conn = getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CarBrand update(CarBrand entity) {
        String sql = "UPDATE CarBrand SET Name=?, Date=? WHERE Id=?";
        try (Connection conn = getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDate(2, entity.getDate());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;

    }

    @Override
    public CarBrand getById(long id) {
        CarBrand carBrand = new CarBrand();
        String sql = "SELECT * FROM CarBrand WHERE Id=?";
        try (Connection conn = getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
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

    @Override
    public List getAll() {
        List<CarBrand> carBrands = new ArrayList<>();
        String sql = "SELECT Id, Name, Date FROM CarBrand";
        try (Connection conn = getConnection(); Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                CarBrand carBrand = new CarBrand();
                carBrand.setId(rs.getLong("Id"));
                carBrand.setName(rs.getString("Name"));
                carBrand.setDate(rs.getDate("Date"));
                carBrands.add(carBrand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carBrands;
    }
}
