package dao;

import java.sql.*;
import java.util.*;
import DB.DBConnection;
import models.Car;

public class CarDAO {
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("car_id"));
                car.setName(rs.getString("car_name"));
                car.setModel(rs.getString("car_model"));
                car.setAvailable(rs.getBoolean("availability"));
                car.setRentPerDay(rs.getDouble("rent_per_day"));
                cars.add(car);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cars;
    }

    public boolean addCar(Car car) {
        String sql = "INSERT INTO cars (car_name, car_model, availability, rent_per_day) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, car.getName());
            ps.setString(2, car.getModel());
            ps.setBoolean(3, car.isAvailable());
            ps.setDouble(4, car.getRentPerDay());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCar(int id) {
        String sql = "DELETE FROM cars WHERE car_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
