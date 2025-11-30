package main;

import models.Customer;
import Service.AdminService;
import Service.UserService;
import java.util.*;

public class CarRentalSystem {
    private static final Scanner sc = new Scanner(System.in);
    private static final AdminService adminService = new AdminService();
    private static final UserService userService = new UserService();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n🚗 CAR RENTAL SYSTEM 🚗");
            System.out.println("1. Admin Login");
            System.out.println("2. User Menu");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> adminLogin();
                case 2 -> userMenu();
                case 3 -> exit = true;
                default -> System.out.println("❌ Invalid option.");
            }
        }
    }
    

    private static void adminLogin() {
        System.out.print("Admin Username: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        if (user.equals("admin") && pass.equals("admin123")) adminMenu();
        else System.out.println("❌ Invalid admin credentials.");
    }

    private static void adminMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. View Cars");
            System.out.println("2. Add Car");
            System.out.println("3. Delete Car");
            System.out.println("4. View All Bookings");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1 -> adminService.viewAllCars();
                case 2 -> {
                    System.out.print("Car Name: ");
                    String name = sc.nextLine();
                    System.out.print("Model: ");
                    String model = sc.nextLine();
                    System.out.print("Rent per day: ");
                    double rent = sc.nextDouble();
                    sc.nextLine();
                    adminService.addCar(name, model, rent);
                }
                case 3 -> {
                    System.out.print("Enter Car ID to delete: ");
                    int id = sc.nextInt();
                    adminService.deleteCar(id);
                }
                case 4 -> adminService.viewAllBookings();
                case 5 -> back = true;
                default -> System.out.println("Invalid!");
            }
        }
    }

    private static void userMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- USER MENU ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> back = true;
            }
        }
    }

    private static void register() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        userService.register(name, email, pass);
    }

    private static void login() {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        Customer c = userService.login(email, pass);
        if (c != null) {
            userDashboard(c);
        } else System.out.println("❌ Login failed.");
    }

    private static void userDashboard(Customer c) {
        boolean logout = false;
        while (!logout) {
            System.out.println("\nWelcome to booking service. " );
            System.out.println("1. View Cars");
            System.out.println("2. Book Car");
            System.out.println("3. My Bookings");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> userService.viewCars();
                case 2 -> {
                    System.out.print("Enter Car ID: ");
                    int carId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter return date (yyyy-mm-dd): ");
                    String date = sc.nextLine();
                    userService.bookCar(c.getId(), carId, java.sql.Date.valueOf(date));
                }
                case 3 -> userService.viewBookings(c.getId());
                case 4 -> {
                    System.out.print("Enter Booking ID to cancel: ");
                    int bookingId = sc.nextInt();
                    sc.nextLine();
                    userService.cancelBooking(bookingId);
                }

                case 5 -> {
                    System.out.println("👋 Logged out successfully!");
                    logout = true;
                }
            }
        }
    }
}
