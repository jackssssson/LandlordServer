package daredevil.project.repositories;

import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoUserFountEsception;
import daredevil.project.models.*;
import daredevil.project.models.DTO.UserDTO;
import daredevil.project.models.Models.LoginModel;
import daredevil.project.repositories.base.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public void createUser(User user) throws CantCreateUserException {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();

            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CantCreateUserException();
        }
    }

    @Override
    public User getUserById(int id) throws NoUserFountEsception {
        User result;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result = session.get(User.class, id);
//            result=session.createQuery("FROM User where id=:id", User.class).setParameter("id", id).getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new NoUserFountEsception();
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
            userToChange.setIban(user.getIban());
            userToChange.setEstates(user.getEstates());
            userToChange.setUser_ratings(user.getUser_ratings());
            userToChange.setUser_type(user.getUser_type());
            userToChange.setSenderMessage(user.getSenderMessage());
            userToChange.setRecipientMessage(user.getRecipientMessage());

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
            result=session.createQuery("from User where user_type = :typeStr", User.class).setParameter("typeStr", type).list();
            session.getTransaction().commit();
            System.out.println("User deleted successfully.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User getUserByName(String name) throws CantCreateUserException {
        User result;
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            result=session.createQuery("from User where name =:userNameStr", User.class).setParameter("userNameStr", name).getSingleResult();
            session.getTransaction().commit();
            System.out.println("User:"+result.getName());
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new CantCreateUserException();
        }
        return result;


    }


    @Override
    public User getUserByLoginModel(String name, String password) {
        User result;
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            result=session.createQuery("from User where name =:userNameStr and password =:userPasswordStr", User.class)
                    .setParameter("userNameStr", name)
                    .setParameter("userPasswordStr", password).getSingleResult();
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public boolean checkUserLogin(String name, String password){
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            session.createQuery("from User where name =:userNameStr and password =:userPasswordStr", User.class)
                    .setParameter("userNameStr", name)
                    .setParameter("userPasswordStr", password).getSingleResult();
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public String isUserFree(UserDTO userDTO){
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            List<User> lu;
            lu=session.createQuery("from User where name=:UserName", User.class).setParameter("UserName", userDTO.getUserName()).list();
            if(lu.size()>0)
                return "Username exists!";
            lu=null;
            lu=session.createQuery("from User where email=:UserEmail", User.class).setParameter("UserEmail", userDTO.getUserEmail()).list();
            if(lu.size()>0)
                return "Email exists!";
            return "User is free";
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }
}
