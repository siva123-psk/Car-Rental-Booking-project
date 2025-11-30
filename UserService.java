package Service
;

import dao.*;
import models.*;
import java.util.*;

public class UserService {
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final CarDAO carDAO = new CarDAO();
    private final BookingDAO bookingDAO = new BookingDAO();

    public Customer register(String name, String email, String password) {
        Customer c = new Customer(name, email, password);
        if (customerDAO.registerCustomer(c))
            System.out.println("✅ Registration successful!");
        else
            System.out.println("❌ Registration failed!");
        return c;
    }

    public Customer login(String email, String password) {
        return customerDAO.loginCustomer(email, password);
    }

    public void viewCars() {
        List<Car> cars = carDAO.getAllCars();
        for (Car c : cars) {
            System.out.println("🚘 " + c.getId() + " | " + c.getName() + " (" + c.getModel() + ") | ₹" + c.getRentPerDay());
        }
    }

    public void bookCar(int customerId, int carId, Date returnDate) {
        if (bookingDAO.createBooking(customerId, carId, returnDate))
            System.out.println("✅ Car booked successfully!");
        else
            System.out.println("❌ Booking failed!");
    }
    
    public void viewBookings(int customerId) {
        List<Booking> list = bookingDAO.getBookingsByCustomer(customerId);
        for (Booking b : list) {
            System.out.println("🧾 BookingID: " + b.getBookingId() + " | CarID: " + b.getCarId() + " | Return: " + b.getReturnDate());
        }
    }
    public void cancelBooking(int bookingId) {
        boolean cancelled = bookingDAO.cancelBooking(bookingId);

        if (cancelled) {
            System.out.println("✅ Booking cancelled successfully.");
        } else {
            System.out.println("❌ Cancellation failed. Check booking ID.");
        }
    }
}
