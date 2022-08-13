package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   User getUser(String model, int series);

   List<Car> listCars();

   Car getCar(String model, int series);


}

