package com.example.demo.repositories;

import com.example.demo.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SqlUserRepository implements UserRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public SqlUserRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createUser(User user) {
        try(
                Session session = sessionFactory.openSession()
        ){
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();

        try(
                Session session = sessionFactory.openSession()
        ){
            session.beginTransaction();
            Query<User> query =  session.createQuery("from User");
            result = query.list();
            session.getTransaction().commit();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public User getUserById(int id) {
        User result;

        try(
                Session session = sessionFactory.openSession()

        ){
            session.beginTransaction();
            result = session.get(User.class, id);
            session.getTransaction().commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw  new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void update(int id, User user) {
        try(
                Session session = sessionFactory.openSession()
        ){
            session.beginTransaction();
            User userToChange = session.get(User.class, id);

            userToChange.setUserName(user.getUserName());
            userToChange.setUserEmail(user.getUserEmail());
            userToChange.setUserPassword(user.getUserPassword());
            userToChange.setUserRating(user.getUserRating());
            //userToChange.setUserType(user.getUserType());

            session.getTransaction().commit();

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

    }
}
