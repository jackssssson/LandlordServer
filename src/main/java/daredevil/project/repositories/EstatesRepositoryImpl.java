package daredevil.project.repositories;

import daredevil.project.models.Estates;
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

    }

    @Override
    public Estates getEstateById(int id) {
        return null;
    }

    @Override
    public void updateEstate(int id, Estates estate) {

    }

    @Override
    public void deleteEstate(int id) {

    }
}
