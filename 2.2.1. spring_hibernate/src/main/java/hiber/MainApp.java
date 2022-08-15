package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        User chel = null;
        for (int i = 1; i < 5; i++) {
            chel = new User("User" + i, "Lastname" + i, "user" + i + "@mail.ru");
            chel.setCar(new Car("Model" + i, i * 100));
            userService.add(chel);
        }

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Model = " + user.getCar().getModel());
            System.out.println("Series = " + user.getCar().getSeries());
            System.out.println();
        }

        try {
            User user = userService.getUser("Model2", 200);
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
        } catch (NoResultException e) {
            System.out.println("Нет такого пользователя");
        }

        List<Car> cars = carService.listCars();
        for(Car car:cars) {
            System.out.println("Model = " + car.getModel()+"  Series = " + car.getSeries());
        }

        try {
            Car car = carService.getCar("Model2", 200);
            System.out.println(car);
        } catch (NoResultException e) {
            System.out.println("Нет такой машины");
        }

        context.close();
    }
}
