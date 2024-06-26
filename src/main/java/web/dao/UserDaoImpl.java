package web.dao;

import org.springframework.stereotype.Repository;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {

        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {

        return entityManager.find(User.class, id);
    }


    @Override
    public void saveUser(User user) {

        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {

        entityManager.merge(user);
    }

    @Override
    public void deleteUser(long id) {

        User user = getUserById(id);
        entityManager.remove(user);
    }

}
