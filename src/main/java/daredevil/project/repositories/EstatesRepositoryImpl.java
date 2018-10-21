package daredevil.project.repositories;

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
}
