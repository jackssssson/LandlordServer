package daredevil.project.repositories;

import daredevil.project.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserRepositoryImpl implements UserRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createUser(User user) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();

            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(int id) {
        User result;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result = session.get(User.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void updateUser(int id, User user) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();
            User userToChange = session.get(User.class, id);

            userToChange.setName(user.getName());
            userToChange.setEmail(user.getEmail());
            userToChange.setPassword(user.getPassword());

            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(getUserById(id));
            session.getTransaction().commit();
            System.out.println("User deleted successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsersByType(String type) {
        List<User> result=new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result=session.createQuery("from User where user_types in(from UserType where type = :typeStr)", User.class)
                    .setParameter("typeStr", type).list();
            session.getTransaction().commit();
            System.out.println("User deleted successfully.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User getUserByName(String name) {
        User result;
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            result=session.createQuery("from User where user_name =:userNameStr", User.class).setParameter("userNameStr", name).getSingleResult();
            session.getTransaction().commit();
            System.out.println("User:"+result.getName());
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;


    }

    @Override
    public List<User> getUnoccupiedLandLords() {
        List<User> result=new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result=session.createQuery("from User where user_types in(from UserType where type = 'landlord') and estates in(from Estates where occupied = false)", User.class).list();
            session.getTransaction().commit();
            System.out.println("Unoccupied LandLords.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
