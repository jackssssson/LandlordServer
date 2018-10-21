package daredevil.project.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageContentImpl implements MessageContentRepository{
    private final SessionFactory sessionFactory;

    @Autowired
    public MessageContentImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
