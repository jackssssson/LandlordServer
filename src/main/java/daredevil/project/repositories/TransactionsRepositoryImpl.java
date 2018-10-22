package daredevil.project.repositories;

import daredevil.project.models.Transactions;
import daredevil.project.repositories.base.TransactionsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionsRepositoryImpl implements TransactionsRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public TransactionsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createTransaction(Transactions transaction) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();

            session.save(transaction);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "transaction");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Transactions getTransactionById(int id) {
        Transactions result;

        try (
                Session session = sessionFactory.openSession()

        ) {
            session.beginTransaction();
            result = session.get(Transactions.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void updateTransaction(int id, Transactions transaction) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();
            Transactions transactionToChange = session.get(Transactions.class, id);

            transactionToChange.setAmount(transaction.getAmount());
            transactionToChange.setTimestamp(transaction.getTimestamp());

            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTransaction(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(getTransactionById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
