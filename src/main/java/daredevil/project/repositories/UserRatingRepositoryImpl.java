package daredevil.project.repositories;

import daredevil.project.models.UserRating;
import daredevil.project.repositories.base.UserRatingRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRatingRepositoryImpl implements UserRatingRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserRatingRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createUserRating(UserRating userRating) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();

            session.save(userRating);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "userRating");
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserRating getUserRatingById(int id) {
        UserRating result;

        try (
                Session session = sessionFactory.openSession()

        ) {
            session.beginTransaction();
            result = session.get(UserRating.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void updateUserRating(int id, UserRating userRating) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();
            UserRating userRatingToChange = session.get(UserRating.class, id);

            userRatingToChange.setRating(userRating.getRating());
            userRatingToChange.setRater(userRating.getRater());
            userRatingToChange.setUsers(userRating.getUsers());

            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUserRating(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(getUserRatingById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public double getUserRatingByUserName(String name){
        double result;
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            result=(double)session.createQuery("select avg(rating) from UserRating where users in(from User where name=:nameStr)").setParameter("nameStr", name).getSingleResult();
            session.getTransaction();
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
        return result;
    }
}
