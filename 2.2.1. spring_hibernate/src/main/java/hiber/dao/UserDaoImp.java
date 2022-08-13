package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUser(String model, int series) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT i FROM User i WHERE i.car.model=:m and i.car.series=:s", User.class)
                .setParameter("m", model)
                .setParameter("s", series)
                .getSingleResult();
    }

    @Override
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    @Override
    public Car getCar(String model, int series) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT i FROM Car i WHERE i.model=:m and i.series=:s", Car.class)
                .setParameter("m", model)
                .setParameter("s", series)
                .getSingleResult();
    }
}
