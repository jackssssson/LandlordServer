package daredevil.project.repositories;

import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.models.Estates;
import daredevil.project.repositories.base.EstatesRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EstatesRepositoryImpl implements EstatesRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public EstatesRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void createEstate(Estates estate) throws CantCreateEstateException {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();

            session.save(estate);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "estate");
            throw new CantCreateEstateException();
        }
    }

    @Override
    public Estates getEstateById(int id) {
        Estates result;

        try (
                Session session = sessionFactory.openSession()

        ) {
            session.beginTransaction();
            result = session.get(Estates.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void updateEstate(int id, Estates estate) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();
            Estates estateToChange = session.get(Estates.class, id);

            estateToChange.setPrice(estate.getPrice());
            estateToChange.setEstateName(estate.getEstateName());
            estateToChange.setOccupied(estate.isOccupied());

            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEstate(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(getEstateById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Estates getEstateByUserName(String name) {
        Estates result;

        try (
                Session session = sessionFactory.openSession()

        ) {
            session.beginTransaction();
            result = session.createQuery("from Estates where id in(select estates from User where name = :userNameStr)", Estates.class).setParameter("userNameStr", name).getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }
}
