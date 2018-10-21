package daredevil.project.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContentTypeRepositoryImpl implements ContentTypeRepository{
    private final SessionFactory sessionFactory;

    @Autowired
    public ContentTypeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
