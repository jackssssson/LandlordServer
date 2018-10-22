package daredevil.project.repositories;

import daredevil.project.models.UserType;
import daredevil.project.repositories.base.UserTypeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserTypeRepositoryImpl implements UserTypeRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserTypeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserType getUserTypeById(int id) {
        UserType result;

        try (
                Session session = sessionFactory.openSession()

        ) {
            session.beginTransaction();
            result = session.get(UserType.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void createUserType(UserType userType) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();

            session.save(userType);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "userType");
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserType getUserTypeByType(String type) {
        UserType result;

        try (
                Session session = sessionFactory.openSession()

        ) {
            session.beginTransaction();
            result = session.createQuery(
                    "from UserType where type = :nameType", UserType.class)
            .setParameter("nameType", type)
            .getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void updateUserType(int id, UserType userType) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();
            UserType userTypeToChange = session.get(UserType.class, id);

            userTypeToChange.setType(userType.getType());

            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUserType(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(getUserTypeById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
