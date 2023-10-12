import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Car {
    private int id;
    private String make;
    private String model;
    private int year;
    private String color;
    private double price;
    private String registrationNumber;

    public Car(int id, String make, String model, int year, String color, double price, String registrationNumber) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.price = price;
        this.registrationNumber = registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Car ID: " + id + ", Make: " + make + ", Model: " + model + ", Year: " + year +
                ", Color: " + color + ", Price: " + price + ", Registration Number: " + registrationNumber;
    }
}

public class CarListManager {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();

        // Populate the list of cars with some data (you can add more cars)
        cars.add(new Car(1, "Toyota", "Camry", 2020, "Silver", 25000.0, "ABC123"));
        cars.add(new Car(2, "Honda", "Civic", 2018, "Blue", 20000.0, "XYZ456"));
        cars.add(new Car(3, "Toyota", "Corolla", 2019, "White", 22000.0, "DEF789"));

        // Save a list of cars of a given brand
        saveCarsByMake(cars, "Toyota");

        // Save a list of cars of a given model that have been in use for more than n years
        saveCarsByModelAndAge(cars, "Camry", 2);

        // Save a list of cars of a given year of manufacture, the price of which is higher than the specified one
        saveCarsByYearAndPrice(cars, 2019, 21000.0);
    }

    public static void saveCarsByMake(List<Car> cars, String makeToFilter) {
        saveCarsToFile(cars, "cars_by_make.txt", car -> car.getMake().equals(makeToFilter));
    }

    public static void saveCarsByModelAndAge(List<Car> cars, String modelToFilter, int minAge) {
        saveCarsToFile(cars, "cars_by_model_and_age.txt",
            car -> car.getModel().equals(modelToFilter) && (2023 - car.getYear()) > minAge);
    }

    public static void saveCarsByYearAndPrice(List<Car> cars, int yearToFilter, double minPrice) {
        saveCarsToFile(cars, "cars_by_year_and_price.txt",
            car -> car.getYear() == yearToFilter && car.getPrice() > minPrice);
    }

    public static void saveCarsToFile(List<Car> cars, String filename, CarFilter filter) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Car car : cars) {
                if (filter.includeCar(car)) {
                    writer.println(car);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    interface CarFilter {
        boolean includeCar(Car car);
    }
}
