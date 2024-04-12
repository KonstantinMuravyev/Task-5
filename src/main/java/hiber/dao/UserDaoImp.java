package hiber.dao;

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

    // Аннотация SuppressWarnings() позволяет нам указать, какие предупреждения игнорировать.
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserCar(String model, int series) {
        String hql = "from User where car.model =: carModelParam and car.series =: carSeriesParam";
        return sessionFactory.getCurrentSession().createQuery(hql, User.class)
                .setParameter("carModelParam", model)
                .setParameter("carSeriesParam", series)
                .getSingleResult();
    }

}
