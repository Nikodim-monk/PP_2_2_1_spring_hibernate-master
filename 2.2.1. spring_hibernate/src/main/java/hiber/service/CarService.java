package hiber.service;

import hiber.model.Car;

import java.util.List;

public interface CarService {
    List<Car> listCars();

    Car getCar(String model, int series);
}
