package Service;

import dao.*;
import models.*;
import java.util.List;

public class AdminService {
    private final CarDAO carDAO = new CarDAO();
    private final BookingDAO bookingDAO = new BookingDAO();

    public void viewAllCars() {
        List<Car> cars = carDAO.getAllCars();
        for (Car c : cars) {
            System.out.println("🚗 " + c.getId() + " | " + c.getName() + " - " + c.getModel() + " | ₹" + c.getRentPerDay());
        }
    }

    public void addCar(String name, String model, double rent) {
        Car c = new Car();
        c.setName(name);
        c.setModel(model);
        c.setAvailable(true);
        c.setRentPerDay(rent);
        if (carDAO.addCar(c)) System.out.println("✅ Car added!");
        else System.out.println("❌ Failed to add car.");
    }

    public void deleteCar(int id) {
        if (carDAO.deleteCar(id)) System.out.println("✅ Car deleted!");
        else System.out.println("❌ Failed to delete.");
    }

    public void viewAllBookings() {
        List<Booking> list = bookingDAO.getAllBookings();
        for (Booking b : list) {
            System.out.println("📘 BookingID: " + b.getBookingId() + " | CarID: " + b.getCarId() + " | CustomerID: " + b.getCustomerId());
        }
    }
}
