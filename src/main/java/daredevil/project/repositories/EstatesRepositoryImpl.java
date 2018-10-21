package daredevil.project.repositories;

import daredevil.project.models.Estates;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EstatesRepositoryImpl implements EstatesRepository{
    private final SessionFactory sessionFactory;

    @Autowired
    public EstatesRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void createEstate(Estates estate) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();

            session.save(estate);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "estate");
            throw new RuntimeException(e);
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
}
