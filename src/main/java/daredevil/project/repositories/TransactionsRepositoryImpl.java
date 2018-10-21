package daredevil.project.repositories;

import daredevil.project.models.Transactions;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionsRepositoryImpl implements TransactionsRepository{
    private final SessionFactory sessionFactory;

    @Autowired
    public TransactionsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createTransaction(Transactions transaction) {

    }

    @Override
    public Transactions getTransactionById(int id) {
        return null;
    }

    @Override
    public void updateTransaction(int id, Transactions transaction) {

    }

    @Override
    public void deleteTransaction(int id) {

    }
}
