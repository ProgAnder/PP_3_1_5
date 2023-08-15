package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TypedQuery<User> query = em.createQuery("select u from User u left join fetch u.roles where u.username=:username", User.class);
        User user = query.setParameter("username", username).getSingleResult();
        if (user == null)
            throw new UsernameNotFoundException("Username not found!");
        return user;
    }

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public List<User> findAllUsers() {
        return em.createQuery("select distinct u from User u left join fetch u.roles", User.class).getResultList();
    }

    @Override
    public User findUserById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void deleteUserById(int id) {
        em.remove(findUserById(id));
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        em.merge(user);
    }
}
