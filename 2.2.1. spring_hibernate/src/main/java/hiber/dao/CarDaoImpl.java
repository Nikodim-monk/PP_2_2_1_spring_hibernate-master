package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao{
    @Autowired
    private SessionFactory sessionFactory;
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
