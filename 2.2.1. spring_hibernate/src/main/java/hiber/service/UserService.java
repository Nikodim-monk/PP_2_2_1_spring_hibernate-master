package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();

    User getUser(String model, int series);

    List<Car> listCars();

    Car getCar(String model, int series);
}
