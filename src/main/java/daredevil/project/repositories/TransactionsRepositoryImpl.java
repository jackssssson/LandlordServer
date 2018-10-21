package daredevil.project.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionsRepositoryImpl implements TransactionsRepository{
    private final SessionFactory sessionFactory;

    @Autowired
    public TransactionsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
